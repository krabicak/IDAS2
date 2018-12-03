package view.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import controller.MainController;
import controller.MainControllerInterface;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import view.gui.libs.Dialogs;

public class FXMLGUIController implements Initializable {
    private MainControllerInterface mainController;

    @FXML
    private TableView<Workplace> tableViewPracoviste;
    @FXML
    private TableView<Teacher> tableViewUcitel;
    @FXML
    public TableView<Subject> tableViewPredmety;
    @FXML
    private Tab ucitelTab;
    @FXML
    private Button addUcitelbtn;
    @FXML
    private Button editUcitelbtn;
    @FXML
    private Button delUcitelbtn;
    @FXML
    private TableColumn<Teacher, String> ucitel_idClm;
    @FXML
    private TableColumn<Teacher, String> titul_predClm;
    @FXML
    private TableColumn<Teacher, String> ucitel_jmenoClm;
    @FXML
    private TableColumn<Teacher, String> ucitel_prijmeniClm;
    @FXML
    private TableColumn<Teacher, String> titul_zaClm;
    @FXML
    private TableColumn<Teacher, Workplace> ucitel_pracovisteClm;
    @FXML
    private TableColumn<Teacher, Obligation> ucitel_uvazekClm;
    @FXML
    private TableColumn<Teacher, String> ucitel_mailClm;
    @FXML
    private TableColumn<Teacher, String> ucitel_telefonClm;
    @FXML
    private TableColumn<Teacher, String> ucitel_mobilClm;
    @FXML
    private Tab pracovisteTab;
    @FXML
    private Button addPracovisteBtn;
    @FXML
    private Button editPracovisteBtn;
    @FXML
    private Button delPracovisteBtn;
    @FXML
    private TableColumn<Teacher, String> pracoviste_idClm;
    @FXML
    private TableColumn<Teacher, String> pracoviste_nazevClm;
    @FXML
    private TableColumn<Teacher, String> pracoviste_zkratkaClm;
    @FXML
    private Tab rozvrhTab;
    @FXML
    private Button addRozvrhBtn;
    @FXML
    private Button editRozvrhBtn;
    @FXML
    private Button delRozvrhBtn;
    @FXML
    private TableColumn<?, ?> rozvrh_denClm;
    @FXML
    private TableColumn<?, ?> rozvrh_odClm;
    @FXML
    private TableColumn<?, ?> rozvrh_doClm;
    @FXML
    private TableColumn<?, ?> rozvrh_typClm;
    @FXML
    private TableColumn<?, ?> rozvrh_predmetClm;
    @FXML
    private TableColumn<?, ?> rozvrh_zkratkaClm;
    @FXML
    private TableColumn<?, ?> rozvrh_vyucujiciClm;
    @FXML
    private Tab seznamPredmetuTab;
    @FXML
    private Button addPredmetBtn;
    @FXML
    private Button editPredmetBtn;
    @FXML
    private Button delPredmetBtn;
    @FXML
    private TableColumn<Subject, String> predmety_nazevClm;
    @FXML
    private TableColumn<Subject, String> predmety_zkratkaClm;
    @FXML
    private TableColumn<Subject, String> predmety_hodinClm;
    @FXML
    private TableColumn<Subject, List<Semester>> predmety_semestrClm;
    @FXML
    private TableColumn<Subject, CategoryOfSubject> predmety_kategorieClm;
    @FXML
    private TableColumn<Subject, ConclusionOfSubject> predmety_zakonceniClm;
    @FXML
    private TableColumn<Subject, Teacher> predmety_garantClm;
    @FXML
    private Tab oborTab;
    @FXML
    private Button addOborBtn;
    @FXML
    private Button editOborBtn;
    @FXML
    private Button delOborBtn;
    @FXML
    private TableColumn<?, ?> obor_nazevClm;
    @FXML
    private TableColumn<?, ?> obor_zkratkaClm;
    @FXML
    private TableColumn<?, ?> obor_formaClm;
    @FXML
    private Tab planTab;
    @FXML
    private Button addStudijniPlan;
    @FXML
    private Button editPlanBtn;
    @FXML
    private Button delPlanBtn;

    public void test() {

    }

    private void setTableViewUcitel() throws MainControllerInterface.DatabaseAccesException {
        ucitel_idClm.setCellValueFactory(new PropertyValueFactory<>("id"));
        ucitel_jmenoClm.setCellValueFactory(new PropertyValueFactory<>("jmeno"));
        ucitel_prijmeniClm.setCellValueFactory(new PropertyValueFactory<>("prijmeni"));
        titul_predClm.setCellValueFactory(new PropertyValueFactory<>("titulPred"));
        titul_zaClm.setCellValueFactory(new PropertyValueFactory<>("titulZa"));
        ucitel_pracovisteClm.setCellValueFactory(new PropertyValueFactory<>("pracoviste"));
        ucitel_uvazekClm.setCellValueFactory(new PropertyValueFactory<>("uvazek"));
        ucitel_mailClm.setCellValueFactory(new PropertyValueFactory<>("email"));
        ucitel_telefonClm.setCellValueFactory(new PropertyValueFactory<>("telefon"));
        ucitel_mobilClm.setCellValueFactory(new PropertyValueFactory<>("mobil"));

        ObservableList<Teacher> list = FXCollections.observableArrayList(mainController.getAllTeachers());
        tableViewUcitel.setItems(list);
    }

    private void setTableViewPracoviste() throws MainControllerInterface.DatabaseAccesException {
        pracoviste_idClm.setCellValueFactory(new PropertyValueFactory<>("id"));
        pracoviste_nazevClm.setCellValueFactory(new PropertyValueFactory<>("nazev"));
        pracoviste_zkratkaClm.setCellValueFactory(new PropertyValueFactory<>("zkratka"));

        ObservableList<Workplace> list = FXCollections.observableArrayList(mainController.getAllWorkplaces());
        tableViewPracoviste.setItems(list);
    }

    private void setTableViewPredmety() throws MainControllerInterface.DatabaseAccesException {
        predmety_garantClm.setCellValueFactory(new PropertyValueFactory<>("garant"));
        predmety_hodinClm.setCellValueFactory(new  PropertyValueFactory<>("rozsahHodin"));
        predmety_kategorieClm.setCellValueFactory(new PropertyValueFactory<>("kategorie"));
        predmety_nazevClm.setCellValueFactory(new PropertyValueFactory<>("nazev"));
        predmety_semestrClm.setCellValueFactory(new PropertyValueFactory<>("semestr"));
        predmety_zakonceniClm.setCellValueFactory(new PropertyValueFactory<>("zpusobZakonceni"));
        predmety_zkratkaClm.setCellValueFactory(new PropertyValueFactory<>("zkratka"));

        ObservableList<Subject> list = FXCollections.observableArrayList(mainController.getAllSubjects());
        tableViewPredmety.setItems(list);
    }

    public void initialize(URL url, ResourceBundle rb) {
        Thread thread = new Thread(() -> {
            try {
                mainController = new MainController();
            }catch (Exception e){
                Dialogs.showErrorMessage(e);
            }
        });
        thread.start();
        try {
            thread.join();
            setTableViewUcitel();
            setTableViewPracoviste();
            setTableViewPredmety();
        } catch (Exception e) {
            Dialogs.showErrorMessage(e);
        }
    }

}

