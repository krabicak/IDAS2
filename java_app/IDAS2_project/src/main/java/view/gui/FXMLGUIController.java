package view.gui;

import controller.MainController;
import controller.MainControllerInterface;
import javafx.beans.property.SimpleStringProperty;
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
import view.gui.libs.Data;
import view.gui.libs.Dialogs;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

public class FXMLGUIController implements Initializable {
    private MainControllerInterface mainController;

    @FXML
    private TableView<Workplace> tableViewPracoviste;
    @FXML
    private TableView<Teacher> tableViewUcitel;
    @FXML
    private TableView<Subject> tableViewPredmety;
    @FXML
    private TableView<FieldOfStudy> tableViewObory;
    @FXML
    private TableView<LearningAction> tableViewRozvrh;
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
    private TableColumn<LearningAction, DayOfWeek> rozvrh_denClm;
    @FXML
    private TableColumn<LearningAction, String> rozvrh_odClm;
    @FXML
    private TableColumn<LearningAction, String> rozvrh_doClm;
    @FXML
    private TableColumn<LearningAction, MethodOfLearning> rozvrh_typClm;
    @FXML
    private TableColumn<LearningAction, String> rozvrh_predmetClm;
    @FXML
    private TableColumn<LearningAction, Subject> rozvrh_zkratkaClm;
    @FXML
    private TableColumn<LearningAction, Teacher> rozvrh_vyucujiciClm;
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
    private TableColumn<FieldOfStudy, String> obor_nazevClm;
    @FXML
    private TableColumn<FieldOfStudy, String> obor_zkratkaClm;
    @FXML
    private TableColumn<FieldOfStudy, FormsOfStudy> obor_formaClm;
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

    private void setTableViewUcitel(List<Teacher> list){
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

        ObservableList<Teacher> lst = FXCollections.observableArrayList(list);
        tableViewUcitel.setItems(lst);
    }

    private void setTableViewPracoviste(List<Workplace> list){
        pracoviste_idClm.setCellValueFactory(new PropertyValueFactory<>("id"));
        pracoviste_nazevClm.setCellValueFactory(new PropertyValueFactory<>("nazev"));
        pracoviste_zkratkaClm.setCellValueFactory(new PropertyValueFactory<>("zkratka"));

        ObservableList<Workplace> lst = FXCollections.observableArrayList(list);
        tableViewPracoviste.setItems(lst);
    }

    private void setTableViewPredmety(List<Subject> list){
        predmety_garantClm.setCellValueFactory(new PropertyValueFactory<>("garant"));
        predmety_hodinClm.setCellValueFactory(new PropertyValueFactory<>("rozsahHodin"));
        predmety_kategorieClm.setCellValueFactory(new PropertyValueFactory<>("kategorie"));
        predmety_nazevClm.setCellValueFactory(new PropertyValueFactory<>("nazev"));
        predmety_semestrClm.setCellValueFactory(new PropertyValueFactory<>("semestr"));
        predmety_zakonceniClm.setCellValueFactory(new PropertyValueFactory<>("zpusobZakonceni"));
        predmety_zkratkaClm.setCellValueFactory(new PropertyValueFactory<>("zkratka"));

        ObservableList<Subject> lst = FXCollections.observableArrayList(list);
        tableViewPredmety.setItems(lst);
    }

    private void setTableViewObory(List<FieldOfStudy> list){
        obor_formaClm.setCellValueFactory(new PropertyValueFactory<>("forma"));
        obor_nazevClm.setCellValueFactory(new PropertyValueFactory<>("nazev"));
        obor_zkratkaClm.setCellValueFactory(new PropertyValueFactory<>("zkratka"));

        ObservableList<FieldOfStudy> lst = FXCollections.observableArrayList(list);
        tableViewObory.setItems(lst);
    }

    private void setTableViewRozvrh(List<LearningAction> list){
        rozvrh_denClm.setCellValueFactory(new PropertyValueFactory<>("den"));
        rozvrh_odClm.setCellValueFactory(new PropertyValueFactory<>("pocatek"));
        rozvrh_doClm.setCellValueFactory(new PropertyValueFactory<>("konec"));
        rozvrh_typClm.setCellValueFactory(new PropertyValueFactory<>("zpusobVyuky"));
        rozvrh_predmetClm.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getPredmet().getNazev()));
        rozvrh_zkratkaClm.setCellValueFactory(new PropertyValueFactory<>("predmet"));
        rozvrh_vyucujiciClm.setCellValueFactory(new PropertyValueFactory<>("vyucujici"));

        ObservableList<LearningAction> lst = FXCollections.observableArrayList(list);
        tableViewRozvrh.setItems(lst);
    }

    private void setAllData(){
        CompletableFuture.supplyAsync(() -> {
            try {
                mainController = new MainController();
                Data data = new Data();
                data.setSubjects(mainController.getAllSubjects());
                data.setTeachers(mainController.getAllTeachers());
                data.setWorkplaces(mainController.getAllWorkplaces());
                data.setFieldOfStudies(mainController.getAllFieldsOfStudy());
                data.setLearningActions(mainController.getAllLearningActions());
                return data;
            } catch (Exception e) {
                Dialogs.showErrorMessage(e);
                return null;
            }
        }).thenApply(data -> {
            try {
                setTableViewUcitel(data.getTeachers());
                setTableViewPredmety(data.getSubjects());
                setTableViewPracoviste(data.getWorkplaces());
                setTableViewObory(data.getFieldOfStudies());
                setTableViewRozvrh(data.getLearningActions());
                return true;
            } catch (Exception e) {
                Dialogs.showErrorMessage(e);
                return false;
            }
        });
    }

    public void initialize(URL url, ResourceBundle rb) {
        setAllData();
    }

}

