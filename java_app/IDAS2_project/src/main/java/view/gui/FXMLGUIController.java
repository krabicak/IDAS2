package view.gui;

import controller.MainController;
import controller.MainControllerInterface;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import model.*;
import view.gui.libs.Action;
import view.gui.libs.Dialogs;

import java.io.File;
import java.net.URL;
import java.util.*;


public class FXMLGUIController implements Initializable {
    public static MainControllerInterface mainController;

    @FXML
    private Button changePasswordButton;
    @FXML
    private Button importButton;
    @FXML
    private Label nameOfLoggedUser;
    @FXML
    private GridPane rozvrhGrid;
    @FXML
    private GridPane ucecebnyGrid;
    @FXML
    private Button addRoomBtn;
    @FXML
    private Button editRoomBtn;
    @FXML
    private Button delRoomBtn;
    @FXML
    private TableView<Room> tableViewMistnosti;
    @FXML
    private TableColumn<Room, String> mistnost_popisClm;
    @FXML
    private TableColumn<Room, String> mistnos_zkratkaClm;
    @FXML
    private TableView<StudyPlan> tableViewPlany;
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
    private ChoiceBox<Room> ucebnaChoiceBox;
    @FXML
    private TableColumn<StudyPlan, String> studyPlan_rocnik_Clm;
    @FXML
    private TableColumn<StudyPlan, String> studyPlan_kategorie_Clm;
    @FXML
    private TableColumn<StudyPlan, Subject> studyPlan_Predmet_Clm;
    @FXML
    private Tab mujRozvrhTab;
    @FXML
    private Button delMujRozvrhBtn;
    @FXML
    private Button editMujRozvrhBtn;
    @FXML
    private Button addMujRozvrhBtn;
    @FXML
    private Tab ucebnyTab;
    @FXML
    private GridPane rozvrhGrid1;

    private List<Button> adminButtons;
    private List<Button> infoButtons;

    private Action clickedAction;

    private GridPane setRozvrhGrid(GridPane gridPane, List<Day> days, List<LearningAction> learningActions) {
        learningActions.forEach(learningAction -> {
            for (int i = 0; i < days.size(); i++) {
                Day day = days.get(i);
                if (learningAction.getPocatek() != null && learningAction.getKonec() != null)
                    if (learningAction.getDen().getShortcut().equals(day.getShortcut())) {
                        Action action = new Action(learningAction);
                        action.setOnMouseClicked(event -> {
                            clickedAction = action;
                            action.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
                            gridPane.getChildren().forEach(node -> {
                                if (node.getClass() == Action.class && node != action) {
                                    ((Action) node).setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
                                }
                            });
                        });
                        int index = Integer.valueOf(learningAction.getPocatek().split(":")[0]) - 6;
                        gridPane.add(action, index, i + 1);
                        int konec = Integer.valueOf(learningAction.getKonec().split(":")[0]) - 6;
                        index++;
                        while (index < konec) {
                            gridPane.add(new Action(new LearningAction()), index, i + 1);
                            index++;
                        }
                        return;
                    }
            }

        });
        return gridPane;
    }

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

    private void setUcebnyTab(List<Room> rooms) {
        ucebnaChoiceBox.setItems(FXCollections.observableArrayList(rooms));
        ucebnaChoiceBox.getSelectionModel().selectFirst();
    }

    private void setMujRozvrhTab(List<LearningAction> learningActions, List<Day> days) {
        rozvrhGrid.getChildren().removeIf(node -> node.getClass() == Action.class);
        setRozvrhGrid(rozvrhGrid, days, learningActions);
    }

    private void setTableViewMistnosti(List<Room> rooms) {
        mistnos_zkratkaClm.setCellValueFactory(new PropertyValueFactory<>("oznaceni"));
        mistnost_popisClm.setCellValueFactory(new PropertyValueFactory<>("popis"));

        tableViewMistnosti.setItems(FXCollections.observableArrayList(rooms));
        tableViewMistnosti.refresh();
    }

    private void setAllData() {
        try {
            setTableViewUcitel(mainController.getAllTeachers());
            setTableViewPredmety(mainController.getAllSubjects());
            setTableViewPracoviste(mainController.getAllWorkplaces());
            setTableViewObory(mainController.getAllFieldsOfStudy());
            setTableViewRozvrh(mainController.getAllLearningActions());
            setTableViewPlany(mainController.getAllFieldsOfStudy());
            setTableViewMistnosti(mainController.getAllRooms());

            setUcebnyTab(mainController.getAllRooms());

            if (mainController.isUserLogged()) {
                nameOfLoggedUser.setText((mainController.isUserAdmin() ? "admin " : "") + mainController.getLoggedUser().toString());
                setMujRozvrhTab(mainController.getLearningActionsByTeacher(mainController.getLoggedUser()), mainController.getAllDays());
            } else nameOfLoggedUser.setText("Nepřihlášený uživatel");
            mujRozvrhTab.setDisable(!mainController.isUserLogged());
            adminButtons.forEach(button -> button.setDisable(!mainController.isUserAdmin()));
            infoButtons.forEach(button -> {
                if (!mainController.isUserAdmin()) {
                    button.setText("Info");
                } else button.setText("Upravit");
            });
            changePasswordButton.setDisable(!mainController.isUserLogged());
        } catch (Exception e) {
            Dialogs.showErrorMessage(e, "Neplatná operace");
        }

    }

    public void initialize(URL url, ResourceBundle rb) {
        mainController = new MainController();
        adminButtons = Arrays.asList(
                addOborBtn, addPracovisteBtn, addPredmetBtn, addRozvrhBtn, addStudijniPlan, addUcitelbtn,
                delOborBtn, delPracovisteBtn, delPredmetBtn, delRozvrhBtn, delPlanBtn, delUcitelbtn, addRoomBtn, delRoomBtn, importButton);
        infoButtons = Arrays.asList(
                editOborBtn, editPracovisteBtn, editPredmetBtn, editRozvrhBtn, editUcitelbtn, editRoomBtn
        );
        logButton.setOnAction(this::onLoginClick);
        setAllData();
    }

    private void onLoginClick(ActionEvent actionEvent) {
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
                    Dialogs.showErrorMessage(e, "Špatně zadané informace");
                }
            });
        } catch (Exception e) {
            Dialogs.showErrorMessage(e, "Špatně zadané informace");
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
                    Dialogs.showErrorMessage(e, "Neplatná operace");
                }
            });
        } catch (Exception e) {
            Dialogs.showErrorMessage(e, "Špatně zadané informace");
        }
    }

    public void deleteTeacher(ActionEvent actionEvent) {
        try {
            String name = tableViewUcitel.getSelectionModel().getSelectedItem().toString();
            mainController.deleteTeacher(tableViewUcitel.getSelectionModel().getSelectedItem());
            setAllData();
            Dialogs.showInfoDialog("Uživatel " + name + "smazán");
        } catch (Exception e) {
            Dialogs.showErrorMessage(e, "Neplatná operace");
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
                    Dialogs.showErrorMessage(e, "Neplatná operace");
                }
            });
        } catch (Exception e) {
            Dialogs.showErrorMessage(e, "Neplatná operace");
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
                    Dialogs.showErrorMessage(e, "Neplatná operace");
                }
            });
        } catch (Exception e) {
            Dialogs.showErrorMessage(e, "Neplatná operace");
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
                    Dialogs.showErrorMessage(e, "Neplatná operace");
                }
            });
        } catch (Exception e) {
            Dialogs.showErrorMessage(e, "Neplatná operace");
        }
    }

    public void deleteWorkplace(ActionEvent actionEvent) {
        try {
            String name = tableViewPracoviste.getSelectionModel().getSelectedItem().toString();
            mainController.deleteWorkplace(tableViewPracoviste.getSelectionModel().getSelectedItem());
            setAllData();
            Dialogs.showInfoDialog("Katedra " + name + " smazána");
        } catch (Exception e) {
            Dialogs.showErrorMessage(e, "Neplatná operace");
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
            Dialogs.showErrorMessage(e, "Neplatná operace");
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
            Dialogs.showErrorMessage(e, "Neplatná operace");
        }
    }

    public void deleteLearningAction(ActionEvent actionEvent) {
        try {
            String name = tableViewRozvrh.getSelectionModel().getSelectedItem().toString();
            mainController.deleteLearningAction(tableViewRozvrh.getSelectionModel().getSelectedItem());
            setAllData();
            Dialogs.showInfoDialog("Výuková akce " + name + " smazána");
        } catch (Exception e) {
            Dialogs.showErrorMessage(e, "Neplatná operace");
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
                    Dialogs.showErrorMessage(e, "Neplatná operace");
                }
            });
        } catch (Exception ex) {
            Dialogs.showErrorMessage(ex, "Neplatná operace");
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
                    Dialogs.showErrorMessage(e, "Neplatná operace");
                }
            });
        } catch (Exception ex) {
            Dialogs.showErrorMessage(ex, "Neplatná operace");
        }
    }

    public void deleteSubject(ActionEvent actionEvent) {
        try {
            String name = tableViewPredmety.getSelectionModel().getSelectedItem().toString();
            mainController.deleteSubject(tableViewPredmety.getSelectionModel().getSelectedItem());
            setAllData();
            Dialogs.showInfoDialog("Předmět " + name + " smazán");
        } catch (Exception e) {
            Dialogs.showErrorMessage(e, "Neplatná operace");
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
                    Dialogs.showErrorMessage(e, "Neplatná operace");
                }
            });
        } catch (Exception ex) {
            Dialogs.showErrorMessage(ex, "Neplatná operace");
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
                    Dialogs.showErrorMessage(e, "Neplatná operace");
                }
            });
        } catch (Exception ex) {
            Dialogs.showErrorMessage(ex, "Neplatná operace");
        }
    }

    public void deleteStudyField(ActionEvent actionEvent) {
        try {
            String name = tableViewObory.getSelectionModel().getSelectedItem().toString();
            mainController.deleteFieldOfStudy(tableViewObory.getSelectionModel().getSelectedItem());
            setAllData();
            Dialogs.showInfoDialog("Obor " + name + " smazán");
        } catch (Exception e) {
            Dialogs.showErrorMessage(e, "Neplatná operace");
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
                    Dialogs.showErrorMessage(e, "Neplatná operace");
                }
            });
        } catch (Exception ex) {
            Dialogs.showErrorMessage(ex, "Neplatná operace");
        }
    }

    public void deleteStudyPlan(ActionEvent actionEvent) {
        try {
            String name = tableViewPlany.getSelectionModel().getSelectedItem().getPredmet().toString();
            mainController.deleteStudyPlan(tableViewPlany.getSelectionModel().getSelectedItem());
            setPlany();
            Dialogs.showInfoDialog("Předmět " + name + " odebrán z plánu");
        } catch (Exception ex) {
            Dialogs.showErrorMessage(ex, "Neplatná operace");
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
            Dialogs.showErrorMessage(e, "Neplatná operace");
        }
    }

    private void addStudyActionResolver(Optional<LearningAction> result) {
        result.ifPresent(learningAction -> {
            try {
                mainController.addLearningAction(learningAction);
                setAllData();
                Dialogs.showInfoDialog("Výuková akce " + learningAction + " přidána");
            } catch (Exception e) {
                Dialogs.showErrorMessage(e, "Neplatná operace");
            }
        });
    }

    public void updateMujRozvrhAct(ActionEvent actionEvent) {
        try {
            Optional<LearningAction> result = Dialogs.getLearningActionDialog(
                    clickedAction.getLearningAction(),
                    mainController.getAllMethodsOfLearning(),
                    mainController.getAllTeachers(),
                    mainController.getAllDays(),
                    mainController.getAllSubjects(),
                    mainController.getAllRooms(),
                    true,
                    mainController.getLoggedUser()).showAndWait();
            updateStudyActionResolver(result);
        } catch (Exception ex) {
            Dialogs.showErrorMessage(ex, "Neplatná operace");
        }
    }

    private void updateStudyActionResolver(Optional<LearningAction> result) {
        result.ifPresent(learningAction -> {
            try {
                mainController.updateLearningAction(learningAction);
                setAllData();
                Dialogs.showInfoDialog("Výuková akce " + learningAction + " upravena");
            } catch (Exception e) {
                Dialogs.showErrorMessage(e, "Neplatná operace");
            }
        });
    }

    public void deleteMujRozvrhAct(ActionEvent actionEvent) {
        try {
            String name = clickedAction.getLearningAction().toString();
            mainController.deleteLearningAction(clickedAction.getLearningAction());
            setAllData();
            Dialogs.showInfoDialog("Výuková akce " + name + " smazána");
        } catch (Exception e) {
            Dialogs.showErrorMessage(e, "Neplatná operace");
        }
    }

    public void onSubjectCommit(ActionEvent actionEvent) {
        try {
            setPlany();
        } catch (Exception e) {
            Dialogs.showErrorMessage(e, "Neplatná operace");
        }

    }

    public void addRoom(ActionEvent actionEvent) {
        try {
            Optional<Room> result = Dialogs.getRoomDialog().showAndWait();
            result.ifPresent(room -> {
                try {
                    mainController.addRoom(room);
                    setAllData();
                    Dialogs.showInfoDialog("Učebna " + room + " přidána");
                } catch (Exception e) {
                    Dialogs.showErrorMessage(e, "Neplatná operace");
                }
            });
        } catch (Exception ex) {
            Dialogs.showErrorMessage(ex, "Neplatná operace");
        }
    }

    public void updateRoom(ActionEvent actionEvent) {
        try {
            Optional<Room> result = Dialogs.getRoomDialog(
                    tableViewMistnosti.getSelectionModel().getSelectedItem(),
                    mainController.isUserAdmin()).showAndWait();
            result.ifPresent(room -> {
                try {
                    mainController.updateRoom(room);
                    setAllData();
                    Dialogs.showInfoDialog("Učebna " + room + " upravena");
                } catch (Exception e) {
                    Dialogs.showErrorMessage(e, "Neplatná operace");
                }
            });
        } catch (Exception e) {
            Dialogs.showErrorMessage(e, "Neplatná operace");
        }
    }

    public void deleteRoom(ActionEvent actionEvent) {
        try {
            String name = tableViewMistnosti.getSelectionModel().getSelectedItem().toString();
            mainController.deleteRoom(tableViewMistnosti.getSelectionModel().getSelectedItem());
            setAllData();
            Dialogs.showInfoDialog("Učebna " + name + " smazána");
        } catch (Exception e) {
            Dialogs.showErrorMessage(e, "Neplatná operace");
        }
    }

    public void onSelectRoomClick(ActionEvent actionEvent) {
        try {
            if (ucebnaChoiceBox.getSelectionModel().getSelectedItem() != null) {
                ucecebnyGrid.getChildren().removeIf(node -> node.getClass() == Action.class);
                setRozvrhGrid(ucecebnyGrid, mainController.getAllDays(), mainController.getAllLearningActionsByRoom(
                        ucebnaChoiceBox.getSelectionModel().getSelectedItem()));
            }
        } catch (Exception e) {
            Dialogs.showErrorMessage(e, "Neplatná operace");
        }
    }

    public void onImportButtonCLick(ActionEvent actionEvent) {
        try {
            File file = Dialogs.getImportDialog().showOpenDialog(null);
            if (file != null) {
                mainController.importRooms(file);
                setAllData();
                Dialogs.showInfoDialog("Import úspěšný");
            }
        } catch (Exception e) {
            Dialogs.showErrorMessage(e, "Neplatná operace");
        }
    }

    public void onChangePasswordClick(ActionEvent actionEvent) {
        try {
            Optional<String> result = Dialogs.changePasswordDialog(mainController.getPassword()).showAndWait();
            result.ifPresent(s -> {
                try {
                    mainController.changePassword(s);
                    Dialogs.showInfoDialog("Změna hesla úspěšná");
                } catch (MainControllerInterface.DatabaseAccesException e) {
                    Dialogs.showErrorMessage(e, "Neplatná operace");
                }
            });
        } catch (Exception e) {
            Dialogs.showErrorMessage(e, "Neplatná operace");
        }
    }
}

