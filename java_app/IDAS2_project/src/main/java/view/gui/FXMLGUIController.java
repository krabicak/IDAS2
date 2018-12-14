package view.gui;

import controller.MainController;
import controller.MainControllerInterface;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import model.*;
import view.gui.libs.Dialogs;

import java.net.URL;
import java.util.*;


public class FXMLGUIController implements Initializable {
    public static MainControllerInterface mainController;

    @FXML
    public GridPane rozvrhGrid;
    @FXML
    public GridPane ucecebnyGrid;
    @FXML
    public ComboBox ucebnaComboBox;
    @FXML
    private TableView<StudyPlan> tableViewPlany;
    @FXML
    private TableView<LearningAction> tableViewMujRozvrh;
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
    private Button logButton;
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
    private TableColumn<LearningAction, Room> rozvrh_mistnostClm;
    @FXML
    private TableColumn<LearningAction, Day> rozvrh_denClm;
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
    private TableColumn<Subject, String> predmety_semestrClm;
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
    private TableColumn<FieldOfStudy, String> obor_formaClm;
    @FXML
    private Tab planTab;
    @FXML
    private Button addStudijniPlan;
    @FXML
    private Button delPlanBtn;
    @FXML
    private ChoiceBox<FieldOfStudy> oborChoiceBox;
    @FXML
    private TableColumn<StudyPlan, String> studyPlan_rocnik_Clm;
    @FXML
    private TableColumn<StudyPlan, String> studyPlan_kategorie_Clm;
    @FXML
    private TableColumn<StudyPlan, Subject> studyPlan_Predmet_Clm;
    @FXML
    private TableColumn<LearningAction, Room> mujRozvrh_mistnostClm;
    @FXML
    private TableColumn<LearningAction, Day> mujRozvrh_denClm;
    @FXML
    private Tab mujRozvrhTab;
    @FXML
    private Button delMujRozvrhBtn;
    @FXML
    private Button editMujRozvrhBtn;
    @FXML
    private Button addMujRozvrhBtn;
    @FXML
    private TableColumn<LearningAction, String> mujRozvrh_od_Clm;
    @FXML
    private TableColumn<LearningAction, String> mujRozvrh_do_Clm;
    @FXML
    private TableColumn<LearningAction, MethodOfLearning> mujRozvrh_typ_Clm;
    @FXML
    private TableColumn<LearningAction, String> mujRozvrh_nazevPredmetu_Clm;
    @FXML
    private TableColumn<LearningAction, Subject> mujRozvrh_zkratka_Clm;

    private List<Button> adminButtons;
    private List<Button> infoButtons;

    private void setTableViewUcitel(List<Teacher> list) {
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
        tableViewUcitel.refresh();
    }

    private void setTableViewPracoviste(List<Workplace> list) {
        pracoviste_idClm.setCellValueFactory(new PropertyValueFactory<>("id"));
        pracoviste_nazevClm.setCellValueFactory(new PropertyValueFactory<>("nazev"));
        pracoviste_zkratkaClm.setCellValueFactory(new PropertyValueFactory<>("zkratka"));

        ObservableList<Workplace> lst = FXCollections.observableArrayList(list);
        tableViewPracoviste.setItems(lst);
        tableViewPracoviste.refresh();
    }

    private void setTableViewPredmety(List<Subject> list) {
        predmety_garantClm.setCellValueFactory(new PropertyValueFactory<>("garant"));
        predmety_hodinClm.setCellValueFactory(new PropertyValueFactory<>("rozsahHodin"));
        predmety_kategorieClm.setCellValueFactory(new PropertyValueFactory<>("kategorie"));
        predmety_nazevClm.setCellValueFactory(new PropertyValueFactory<>("nazev"));
        predmety_semestrClm.setCellValueFactory(param -> {
            StringBuilder sem = new StringBuilder();
            param.getValue().getSemestr().forEach(semester -> {
                sem.append(semester.getNazevSemestru());
                sem.append(',');
            });
            if (sem.length() > 1)
                sem.deleteCharAt(sem.length() - 1);
            return new SimpleStringProperty(sem.toString());
        });
        predmety_zakonceniClm.setCellValueFactory(new PropertyValueFactory<>("zpusobZakonceni"));
        predmety_zkratkaClm.setCellValueFactory(new PropertyValueFactory<>("zkratka"));

        ObservableList<Subject> lst = FXCollections.observableArrayList(list);
        tableViewPredmety.setItems(lst);
        tableViewPredmety.refresh();
    }

    private void setTableViewObory(List<FieldOfStudy> list) {
        obor_formaClm.setCellValueFactory(param -> {
            StringBuilder sem = new StringBuilder();
            param.getValue().getForma().forEach(form -> {
                sem.append(form.getNazevFormy());
                sem.append(',');
            });
            if (sem.length() > 1)
                sem.deleteCharAt(sem.length() - 1);
            return new SimpleStringProperty(sem.toString());
        });
        obor_nazevClm.setCellValueFactory(new PropertyValueFactory<>("nazev"));
        obor_zkratkaClm.setCellValueFactory(new PropertyValueFactory<>("zkratka"));
        ObservableList<FieldOfStudy> lst = FXCollections.observableArrayList(list);
        tableViewObory.setItems(lst);
        tableViewObory.refresh();
    }

    private void setTableViewMujRozvrh(List<LearningAction> list) {
        mujRozvrh_mistnostClm.setCellValueFactory(new PropertyValueFactory<>("ucebna"));
        mujRozvrh_denClm.setCellValueFactory(new PropertyValueFactory<>("den"));
        mujRozvrh_od_Clm.setCellValueFactory(new PropertyValueFactory<>("pocatek"));
        mujRozvrh_do_Clm.setCellValueFactory(new PropertyValueFactory<>("konec"));
        mujRozvrh_typ_Clm.setCellValueFactory(new PropertyValueFactory<>("zpusobVyuky"));
        mujRozvrh_nazevPredmetu_Clm.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getPredmet().getNazev()));
        mujRozvrh_zkratka_Clm.setCellValueFactory(new PropertyValueFactory<>("predmet"));

        ObservableList<LearningAction> lst = FXCollections.observableArrayList(list);
        tableViewMujRozvrh.setItems(lst);
        tableViewMujRozvrh.refresh();
    }

    private void setTableViewRozvrh(List<LearningAction> list) {
        rozvrh_mistnostClm.setCellValueFactory(new PropertyValueFactory<>("ucebna"));
        rozvrh_denClm.setCellValueFactory(new PropertyValueFactory<>("den"));
        rozvrh_odClm.setCellValueFactory(new PropertyValueFactory<>("pocatek"));
        rozvrh_doClm.setCellValueFactory(new PropertyValueFactory<>("konec"));
        rozvrh_typClm.setCellValueFactory(new PropertyValueFactory<>("zpusobVyuky"));
        rozvrh_predmetClm.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getPredmet().getNazev()));
        rozvrh_zkratkaClm.setCellValueFactory(new PropertyValueFactory<>("predmet"));
        rozvrh_vyucujiciClm.setCellValueFactory(new PropertyValueFactory<>("vyucujici"));

        ObservableList<LearningAction> lst = FXCollections.observableArrayList(list);
        tableViewRozvrh.setItems(lst);
        tableViewRozvrh.refresh();
    }

    private void setPlany() throws MainControllerInterface.DatabaseAccesException {
        if (oborChoiceBox.getSelectionModel().getSelectedItem() != null) {
            tableViewPlany.setItems(FXCollections.observableArrayList(mainController.getStudyPlansByFieldOfStudy(oborChoiceBox.getSelectionModel().getSelectedItem())));
            tableViewPlany.refresh();
        }
    }

    private void setTableViewPlany(List<FieldOfStudy> list) {
        studyPlan_kategorie_Clm.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getPredmet().getKategorie().getNazevKategorie()));
        studyPlan_Predmet_Clm.setCellValueFactory(new PropertyValueFactory<>("predmet"));
        studyPlan_rocnik_Clm.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getPredmet().getDoporucenyRocnik().getCisloRocniku()));

        oborChoiceBox.setItems(FXCollections.observableArrayList(list));
        oborChoiceBox.getSelectionModel().selectFirst();
        tableViewPlany.refresh();
    }

    private void setAllData() {
        try {
            setTableViewUcitel(mainController.getAllTeachers());
            setTableViewPredmety(mainController.getAllSubjects());
            setTableViewPracoviste(mainController.getAllWorkplaces());
            setTableViewObory(mainController.getAllFieldsOfStudy());
            setTableViewRozvrh(mainController.getAllLearningActions());
            setTableViewPlany(mainController.getAllFieldsOfStudy());
            if (mainController.isUserLogged())
                setTableViewMujRozvrh(mainController.getLearningActionsByTeacher(mainController.getLoggedUser()));
            mujRozvrhTab.setDisable(!mainController.isUserLogged());
            adminButtons.forEach(button -> button.setDisable(!mainController.isUserAdmin()));
            infoButtons.forEach(button -> {
                if (!mainController.isUserAdmin()) {
                    button.setText("Info");
                } else button.setText("Upravit");
            });
        } catch (Exception e) {
            Dialogs.showErrorMessage(e);
        }

    }

    public void initialize(URL url, ResourceBundle rb) {
        mainController = new MainController();
        adminButtons = Arrays.asList(
                addOborBtn, addPracovisteBtn, addPredmetBtn, addRozvrhBtn, addStudijniPlan, addUcitelbtn,
                delOborBtn, delPracovisteBtn, delPredmetBtn, delRozvrhBtn, delPlanBtn, delUcitelbtn);
        infoButtons = Arrays.asList(
                editOborBtn, editPracovisteBtn, editPredmetBtn, editRozvrhBtn, editUcitelbtn
        );
        logButton.setOnAction(this::onLoginClick);
        setAllData();
    }

    public void onLoginClick(ActionEvent actionEvent) {
        try {
            Optional<Pair<String, String>> result = Dialogs.getLoginDialog().showAndWait();
            result.ifPresent(stringStringPair -> {
                try {
                    mainController.login(stringStringPair.getKey(), stringStringPair.getValue());
                    logButton.setText("Odhlásit");
                    logButton.setOnAction(event -> {
                        mainController.logOut();
                        logButton.setText("Přihlásit");
                        logButton.setOnAction(this::onLoginClick);
                        setAllData();
                        Dialogs.showInfoDialog("Odhlášeno");
                    });
                    setAllData();
                    Dialogs.showInfoDialog("Přihlášeno");
                } catch (MainControllerInterface.LoginException e) {
                    Dialogs.showErrorMessage(e);
                }
            });
        } catch (Exception e) {
            Dialogs.showErrorMessage(e);
        }
    }

    public void addTeacher(ActionEvent actionEvent) {
        try {
            Optional<Teacher> result = Dialogs.getTeacherDialog(
                    mainController.getAllWorkplaces(),
                    mainController.getAllObligations(),
                    mainController.getAllRoles()).showAndWait();
            result.ifPresent(teacher -> {
                try {
                    mainController.addTeacher(teacher);
                    setAllData();
                    Dialogs.showInfoDialog("Uživatel " + teacher + "přidán");
                } catch (Exception e) {
                    Dialogs.showErrorMessage(e);
                }
            });
        } catch (Exception e) {
            Dialogs.showErrorMessage(e);
        }
    }

    public void deleteTeacher(ActionEvent actionEvent) {
        try {
            String name = tableViewUcitel.getSelectionModel().getSelectedItem().toString();
            mainController.deleteTeacher(tableViewUcitel.getSelectionModel().getSelectedItem());
            setAllData();
            Dialogs.showInfoDialog("Uživatel " + name + "smazán");
        } catch (Exception e) {
            Dialogs.showErrorMessage(e);
        }
    }

    public void updateTeacher(ActionEvent actionEvent) {
        try {
            Photo photo;
            try {
                photo = mainController.getPhotoByTeacher(tableViewUcitel.getSelectionModel().getSelectedItem());
            } catch (Exception e) {
                photo = new Photo();
            }
            Optional<Teacher> result = Dialogs.getTeacherDialog(
                    tableViewUcitel.getSelectionModel().getSelectedItem(),
                    photo,
                    mainController.getAllWorkplaces(),
                    mainController.getAllObligations(),
                    mainController.getAllRoles(),
                    mainController.isUserAdmin()
            ).showAndWait();
            result.ifPresent(teacher -> {
                try {
                    mainController.updateTeacher(teacher);
                    Dialogs.showInfoDialog("Uživatel " + teacher + "upraven");
                    setAllData();
                } catch (Exception e) {
                    Dialogs.showErrorMessage(e);
                }
            });
        } catch (Exception e) {
            Dialogs.showErrorMessage(e);
        }
    }

    public void addWorkplace(ActionEvent actionEvent) {
        try {
            Optional<Workplace> result = Dialogs.getWorkplaceDialog(mainController.getAllFaculties()).showAndWait();
            result.ifPresent(workplace -> {
                try {
                    mainController.addWorkplace(workplace);
                    setAllData();
                    Dialogs.showInfoDialog("Katedra " + workplace + "přidána");
                } catch (Exception e) {
                    Dialogs.showErrorMessage(e);
                }
            });
        } catch (Exception e) {
            Dialogs.showErrorMessage(e);
        }
    }

    public void updateWorkplace(ActionEvent actionEvent) {
        try {
            Optional<Workplace> result = Dialogs.getWorkplaceDialog(
                    tableViewPracoviste.getSelectionModel().getSelectedItem(), mainController.getAllFaculties(), mainController.isUserAdmin()).showAndWait();
            result.ifPresent(workplace -> {
                try {
                    mainController.updateWorkplace(workplace);
                    setAllData();
                    Dialogs.showInfoDialog("Katedra " + workplace + " upravena");
                } catch (Exception e) {
                    Dialogs.showErrorMessage(e);
                }
            });
        } catch (Exception e) {
            Dialogs.showErrorMessage(e);
        }
    }

    public void deleteWorkplace(ActionEvent actionEvent) {
        try {
            String name = tableViewPracoviste.getSelectionModel().getSelectedItem().toString();
            mainController.deleteWorkplace(tableViewPracoviste.getSelectionModel().getSelectedItem());
            setAllData();
            Dialogs.showInfoDialog("Katedra " + name + " smazána");
        } catch (Exception e) {
            Dialogs.showErrorMessage(e);
        }
    }

    public void addLearningAction(ActionEvent actionEvent) {
        try {
            Optional<LearningAction> result = Dialogs.getLearningActionDialog(
                    mainController.getAllMethodsOfLearning(),
                    mainController.getAllTeachers(),
                    mainController.getAllDays(),
                    mainController.getAllSubjects(),
                    mainController.getAllRooms()).showAndWait();
            addStudyActionResolver(result);
        } catch (Exception e) {
            Dialogs.showErrorMessage(e);
        }
    }


    public void updateLearningAction(ActionEvent actionEvent) {
        try {
            Optional<LearningAction> result = Dialogs.getLearningActionDialog(
                    tableViewRozvrh.getSelectionModel().getSelectedItem(),
                    mainController.getAllMethodsOfLearning(),
                    mainController.getAllTeachers(),
                    mainController.getAllDays(),
                    mainController.getAllSubjects(),
                    mainController.getAllRooms(),
                    mainController.isUserAdmin()).showAndWait();
            updateStudyActionResolver(result);
        } catch (Exception e) {
            Dialogs.showErrorMessage(e);
        }
    }

    public void deleteLearningAction(ActionEvent actionEvent) {
        try {
            String name = tableViewRozvrh.getSelectionModel().getSelectedItem().toString();
            mainController.deleteLearningAction(tableViewRozvrh.getSelectionModel().getSelectedItem());
            setAllData();
            Dialogs.showInfoDialog("Výuková akce " + name + " smazána");
        } catch (Exception e) {
            Dialogs.showErrorMessage(e);
        }
    }

    public void addSubject(ActionEvent actionEvent) {
        try {
            Optional<Subject> result = Dialogs.getSubjectDialog(
                    mainController.getAllSemesters(),
                    mainController.getAllCategoriesofSubjects(),
                    mainController.getAllConclusionsOfSubjects(),
                    mainController.getAllTeachers(),
                    mainController.getAllRecommendedYears()).showAndWait();
            result.ifPresent(subject -> {
                try {
                    mainController.addSubject(subject);
                    setAllData();
                    Dialogs.showInfoDialog("Předmět " + subject + " přidán");
                } catch (Exception e) {
                    Dialogs.showErrorMessage(e);
                }
            });
        } catch (Exception ex) {
            Dialogs.showErrorMessage(ex);
        }
    }

    public void updateSubject(ActionEvent actionEvent) {
        try {
            Optional<Subject> result = Dialogs.getSubjectDialog(
                    tableViewPredmety.getSelectionModel().getSelectedItem(),
                    mainController.getAllSemesters(),
                    mainController.getAllCategoriesofSubjects(),
                    mainController.getAllConclusionsOfSubjects(),
                    mainController.getAllTeachers(),
                    mainController.getAllRecommendedYears(),
                    mainController.isUserAdmin()).showAndWait();
            result.ifPresent(subject -> {
                try {
                    mainController.updateSubject(subject);
                    setAllData();
                    Dialogs.showInfoDialog("Předmět " + subject + " upraven");
                } catch (Exception e) {
                    Dialogs.showErrorMessage(e);
                }
            });
        } catch (Exception ex) {
            Dialogs.showErrorMessage(ex);
        }
    }

    public void deleteSubject(ActionEvent actionEvent) {
        try {
            String name = tableViewPredmety.getSelectionModel().getSelectedItem().toString();
            mainController.deleteSubject(tableViewPredmety.getSelectionModel().getSelectedItem());
            setAllData();
            Dialogs.showInfoDialog("Předmět " + name + " smazán");
        } catch (Exception e) {
            Dialogs.showErrorMessage(e);
        }
    }


    public void addStudyField(ActionEvent actionEvent) {
        try {
            Optional<FieldOfStudy> result = Dialogs.getFieldOfStudyDialog(
                    mainController.getAllFormsOfStudy(),
                    mainController.getAllWorkplaces()).showAndWait();
            result.ifPresent(fieldOfStudy -> {
                try {
                    mainController.addFieldOfStudy(fieldOfStudy);
                    setAllData();
                    Dialogs.showInfoDialog("Obor " + fieldOfStudy + " přidán");
                } catch (Exception e) {
                    Dialogs.showErrorMessage(e);
                }
            });
        } catch (Exception ex) {
            Dialogs.showErrorMessage(ex);
        }
    }


    public void updateStudyField(ActionEvent actionEvent) {
        try {
            Optional<FieldOfStudy> result = Dialogs.getFieldOfStudyDialog(
                    tableViewObory.getSelectionModel().getSelectedItem(),
                    mainController.getAllFormsOfStudy(),
                    mainController.getAllWorkplaces(),
                    mainController.isUserAdmin()).showAndWait();
            result.ifPresent(fieldOfStudy -> {
                try {
                    mainController.updateFieldOfStudy(fieldOfStudy);
                    setAllData();
                    Dialogs.showInfoDialog("Obor " + fieldOfStudy + " upraven");
                } catch (Exception e) {
                    Dialogs.showErrorMessage(e);
                }
            });
        } catch (Exception ex) {
            Dialogs.showErrorMessage(ex);
        }
    }

    public void deleteStudyField(ActionEvent actionEvent) {
        try {
            String name = tableViewObory.getSelectionModel().getSelectedItem().toString();
            mainController.deleteFieldOfStudy(tableViewObory.getSelectionModel().getSelectedItem());
            setAllData();
            Dialogs.showInfoDialog("Obor " + name + " smazán");
        } catch (Exception e) {
            Dialogs.showErrorMessage(e);
        }
    }

    public void addStudyPlanAct(ActionEvent actionEvent) {
        try {
            Optional<StudyPlan> result = Dialogs.getStudyPlanDialog(mainController.getAllSubjects()).showAndWait();
            result.ifPresent(studyPlan -> {
                studyPlan.setStudijniObor(oborChoiceBox.getSelectionModel().getSelectedItem());
                try {
                    mainController.addStudyPlan(studyPlan);
                    setPlany();
                    Dialogs.showInfoDialog("Předmět " + studyPlan.getPredmet() + " přidán do plánu");
                } catch (MainControllerInterface.DatabaseAccesException e) {
                    Dialogs.showErrorMessage(e);
                }
            });
        } catch (Exception ex) {
            Dialogs.showErrorMessage(ex);
        }
    }

    public void deleteStudyPlan(ActionEvent actionEvent) {
        try {
            String name = tableViewPlany.getSelectionModel().getSelectedItem().getPredmet().toString();
            mainController.deleteStudyPlan(tableViewPlany.getSelectionModel().getSelectedItem());
            setPlany();
            Dialogs.showInfoDialog("Předmět " + name + " odebrán z plánu");
        } catch (Exception ex) {
            Dialogs.showErrorMessage(ex);
        }
    }

    public void addMujRozvrhAct(ActionEvent actionEvent) {
        try {
            Optional<LearningAction> result = Dialogs.getLearningActionDialog(
                    mainController.getAllMethodsOfLearning(),
                    mainController.getAllTeachers(),
                    mainController.getAllDays(),
                    mainController.getAllSubjects(),
                    mainController.getAllRooms(),
                    mainController.getLoggedUser()).showAndWait();
            addStudyActionResolver(result);
        } catch (Exception e) {
            Dialogs.showErrorMessage(e);
        }
    }

    private void addStudyActionResolver(Optional<LearningAction> result) {
        result.ifPresent(learningAction -> {
            try {
                mainController.addLearningAction(learningAction);
                setAllData();
                Dialogs.showInfoDialog("Výuková akce " + learningAction + " přidána");
            } catch (Exception e) {
                Dialogs.showErrorMessage(e);
            }
        });
    }

    public void updateMujRozvrhAct(ActionEvent actionEvent) {
        try {
            Optional<LearningAction> result = Dialogs.getLearningActionDialog(
                    tableViewMujRozvrh.getSelectionModel().getSelectedItem(),
                    mainController.getAllMethodsOfLearning(),
                    mainController.getAllTeachers(),
                    mainController.getAllDays(),
                    mainController.getAllSubjects(),
                    mainController.getAllRooms(),
                    true,
                    mainController.getLoggedUser()).showAndWait();
            updateStudyActionResolver(result);
        } catch (Exception ex) {
            Dialogs.showErrorMessage(ex);
        }
    }

    private void updateStudyActionResolver(Optional<LearningAction> result) {
        result.ifPresent(learningAction -> {
            try {
                mainController.updateLearningAction(learningAction);
                setAllData();
                Dialogs.showInfoDialog("Výuková akce " + learningAction + " upravena");
            } catch (Exception e) {
                Dialogs.showErrorMessage(e);
            }
        });
    }

    public void deleteMujRozvrhAct(ActionEvent actionEvent) {
        deleteLearningAction(actionEvent);
    }

    public void onSubjectCommit(ActionEvent actionEvent) {
        try {
            setPlany();
        } catch (Exception e) {
            Dialogs.showErrorMessage(e);
        }

    }

    public void addRoomAct(ActionEvent actionEvent) {
        try {
            Optional<Room> result = Dialogs.getRoomDialog().showAndWait();
            result.ifPresent(room -> {
                try {
                    mainController.addRoom(room);
                    ucebnaComboBox.getItems().addAll(room);
                    setAllData();
                    Dialogs.showInfoDialog("Učebna " + room + " přidána");
                } catch (Exception e) {
                    Dialogs.showErrorMessage(e);
                }
            });
        } catch (Exception ex) {
            Dialogs.showErrorMessage(ex);
        }
    }

    public void updateRoomAct(ActionEvent actionEvent) {
        try {
            Optional<Room> result = Dialogs.getRoomDialog().showAndWait();
            result.ifPresent(room -> {
                try {
                    mainController.updateRoom(room);
                    setAllData();
                    Dialogs.showInfoDialog("Učebna " + room + " upravena");
                } catch (Exception e) {
                    Dialogs.showErrorMessage(e);
                }
            });
        } catch (Exception e) {
            Dialogs.showErrorMessage(e);
        }
    }

    public void deleteRoomAct(ActionEvent actionEvent) {
            try {
                String name = ucebnaComboBox.getSelectionModel().getSelectedItem().toString();
                ucebnaComboBox.getItems().remove(name);
               // mainController.deleteRoom(ucebnaComboBox.getSelectionModel());
                setAllData();
                Dialogs.showInfoDialog("Učebna " + name + " smazána");
            } catch (Exception e) {
                Dialogs.showErrorMessage(e);
            }
    }

    public void ucebnaComboAct(ActionEvent actionEvent) {
    }
}

