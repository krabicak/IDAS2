package view.gui;

import java.net.URL;
import java.util.ResourceBundle;

import controller.MainController;
import controller.MainControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;

public class FXMLGUIController implements Initializable {

    @FXML
    private Tab ucitelTab;
    @FXML
    private Button addUcitelbtn;
    @FXML
    private Button editUcitelbtn;
    @FXML
    private Button delUcitelbtn;
    @FXML
    private TableColumn<?, ?> ucitel_idClm;
    @FXML
    private TableColumn<?, ?> titul_predClm;
    @FXML
    private TableColumn<?, ?> ucitel_jmenoClm;
    @FXML
    private TableColumn<?, ?> ucitel_prijmeniClm;
    @FXML
    private TableColumn<?, ?> titul_zaClm;
    @FXML
    private TableColumn<?, ?> ucitel_pracovisteClm;
    @FXML
    private TableColumn<?, ?> ucitel_uvazekClm;
    @FXML
    private TableColumn<?, ?> ucitel_mailClm;
    @FXML
    private TableColumn<?, ?> ucitel_telefonClm;
    @FXML
    private TableColumn<?, ?> ucitel_mobilClm;
    @FXML
    private Tab pracovisteTab;
    @FXML
    private Button addPracovisteBtn;
    @FXML
    private Button editPracovisteBtn;
    @FXML
    private Button delPracovisteBtn;
    @FXML
    private TableColumn<?, ?> pracoviste_idClm;
    @FXML
    private TableColumn<?, ?> pracoviste_nazevClm;
    @FXML
    private TableColumn<?, ?> pracoviste_zkratkaClm;
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
    private TableColumn<?, ?> predmety_nazevClm;
    @FXML
    private TableColumn<?, ?> predmety_zkratkaClm;
    @FXML
    private TableColumn<?, ?> predmety_hodinClm;
    @FXML
    private TableColumn<?, ?> predmety_semestrClm;
    @FXML
    private TableColumn<?, ?> predmety_kategorieClm;
    @FXML
    private TableColumn<?, ?> predmety_zakonceniClm;
    @FXML
    private TableColumn<?, ?> predmety_zakonceniClm1;
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

    public void test(){
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {

                    MainControllerInterface mainController = new MainController();
                    mainController.login("root@root.cz", "admin");
                    System.out.println(mainController.getAllTeachers());
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
        thread.run();
    }

    public void initialize(URL url, ResourceBundle rb) {

    }

}

