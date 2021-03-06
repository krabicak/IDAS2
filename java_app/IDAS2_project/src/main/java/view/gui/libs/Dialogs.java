package view.gui.libs;

import controller.MainControllerInterface;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Pair;
import model.*;
import view.gui.FXMLGUIController;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.nio.file.Files;

public final class Dialogs {
    private Dialogs() {
    }

    public static void showExitDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/info_icon.png"));
        alert.setHeaderText("Přejete si ukončit tento program?");
        alert.setTitle("Konec");
        Optional<ButtonType> result = alert.showAndWait();
        result.ifPresent((t) -> {
            Platform.exit();
        });
    }

    public static void showInfoDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/info_icon.png"));
        alert.setHeaderText(message);
        alert.setTitle("Info");
        alert.show();
    }

    public static void showErrorMessage(Exception ex) {
        System.out.println(ex);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/info_icon.png"));
        alert.setTitle("Chyba");
        alert.setHeaderText(ex.getLocalizedMessage());
        alert.setContentText(ex.toString());
        alert.showAndWait();
    }

    public static void showErrorMessage(Exception e, String message) {
        System.out.println(e);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/info_icon.png"));
        alert.setTitle("Chyba");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    private static void isItNull(TextField textField)
            throws NullPointerException {
        if (textField.getText() == null || textField.getText().equals("")) {
            throw new NullPointerException("Nezadány všechny údaje");
        }
    }

    public static Dialog getLoginDialog() {
        final ButtonType SAVE = new ButtonType("Přihlásit", ButtonBar.ButtonData.OK_DONE);
        final ButtonType CANCEL = new ButtonType("Zrušit", ButtonBar.ButtonData.CANCEL_CLOSE);
        TextField email = new TextField();
        PasswordField password = new PasswordField();

        Dialog dialog = new Dialog();
        ((Stage) dialog.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/info_icon.png"));
        InvalidationListener listener = (Observable observable) -> {
            try {
                isItNull(email);
                isItNull(password);
                dialog.getDialogPane().lookupButton(SAVE).setDisable(false);
            } catch (NullPointerException ex) {
                dialog.getDialogPane().lookupButton(SAVE).setDisable(true);
            }
        };

        email.setPromptText("email");
        password.setPromptText("heslo");
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        grid.add(new Label("Zadejte email:"), 0, 0);
        grid.add(email, 1, 0);
        grid.add(new Label("Zadejte heslo:"), 0, 1);
        grid.add(password, 1, 1);

        dialog.setTitle("Přihlášení");
        dialog.getDialogPane().getButtonTypes().addAll(SAVE, CANCEL);
        dialog.getDialogPane().lookupButton(SAVE).setDisable(true);
        dialog.initModality(Modality.APPLICATION_MODAL);

        dialog.getDialogPane().setContent(grid);

        email.textProperty().addListener(listener);
        password.textProperty().addListener(listener);

        Callback<ButtonType, Pair<String, String>> callback = dialogButton -> {
            if (dialogButton == SAVE) {
                return new Pair<>(email.getText(), password.getText());
            }
            return null;
        };
        dialog.setResultConverter(callback);
        return dialog;
    }

    public static Dialog getTeacherDialog(List<Workplace> workplaces, List<Obligation> obligations, List<Role> roles) throws SQLException {
        return getTeacherDialog(new Teacher(), new Photo(), workplaces, obligations, roles, true);
    }

    public static Dialog getTeacherDialog(Teacher teacher, Photo photo, List<Workplace> workplaces, List<Obligation> obligations, List<Role> roles, boolean editable) throws SQLException {
        // část deklarace polí
        ButtonType save = new ButtonType(
                "Uložit",
                ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType(
                "Zrušit",
                ButtonBar.ButtonData.CANCEL_CLOSE);
        TextField firstName = new TextField();
        firstName.setText(teacher.getJmeno());
        firstName.setEditable(editable);
        TextField lastName = new TextField();
        lastName.setText(teacher.getPrijmeni());
        lastName.setEditable(editable);
        TextField tittleBefore = new TextField();
        tittleBefore.setText(teacher.getTitulPred());
        tittleBefore.setEditable(editable);
        TextField tittleAfter = new TextField();
        tittleAfter.setText(teacher.getTitulZa());
        tittleAfter.setEditable(editable);
        TextField telNumber = new TextField();
        telNumber.setText(teacher.getTelefon());
        telNumber.setEditable(editable);
        TextField mobileNumber = new TextField();
        mobileNumber.setText(teacher.getMobil());
        mobileNumber.setEditable(editable);
        TextField email = new TextField();
        email.setText(teacher.getEmail());
        email.setEditable(editable);
        PasswordField password = new PasswordField();
        password.setEditable(editable);
        ChoiceBox<Workplace> workplacess = new ChoiceBox<>();
        workplaces.forEach(workplacess.getItems()::add);
        workplacess.setDisable(!editable);
        if (teacher.getPracoviste() != null) workplacess.getSelectionModel().select(teacher.getPracoviste());
        else workplacess.getSelectionModel().selectFirst();

        ChoiceBox<Role> roless = new ChoiceBox<>();
        roles.forEach(roless.getItems()::add);
        roless.setDisable(!editable);
        if (teacher.getRole() != null) roless.getSelectionModel().select(teacher.getRole());
        else roless.getSelectionModel().selectFirst();

        ChoiceBox<Obligation> obligationn = new ChoiceBox<>();
        obligations.forEach(obligationn.getItems()::add);
        obligationn.setDisable(!editable);
        if (teacher.getUvazek() != null) obligationn.getSelectionModel().select(teacher.getUvazek());
        else obligationn.getSelectionModel().selectFirst();

        HBox hBox = new HBox();
        if (photo.getId() != null) {
            Button updatePhoto = new Button();
            updatePhoto.setDisable(!editable);
            updatePhoto.setText("Upravit");
            updatePhoto.setOnAction(event -> {
                Optional<Photo> result = getPhotoDialog(photo).showAndWait();
                result.ifPresent(o -> {
                    try {
                        FXMLGUIController.mainController.updatePhoto(o);
                        showInfoDialog("fotka úspěšně přidána");
                    } catch (MainControllerInterface.DatabaseAccesException e) {
                        showErrorMessage(e);
                    }
                });
            });
            Button deletePhoto = new Button();
            deletePhoto.setText("Odstranit");
            deletePhoto.setDisable(!editable);
            deletePhoto.setOnAction(event -> {
                try {
                    FXMLGUIController.mainController.deletePhoto(photo);
                    showInfoDialog("fotka úspěšně smazána");
                } catch (MainControllerInterface.DatabaseAccesException e) {
                    showErrorMessage(e);
                }
            });
            hBox.getChildren().addAll(updatePhoto, deletePhoto);
        } else {
            Button addPhoto = new Button();
            addPhoto.setDisable(!editable);
            addPhoto.setText("Přidat");
            addPhoto.setOnAction(event -> {
                photo.setId(teacher.getId());
                Optional<Photo> result = getPhotoDialog(photo).showAndWait();
                result.ifPresent(o -> {
                    try {
                        FXMLGUIController.mainController.addPhoto(o);
                        showInfoDialog("fotka úspěšně přidána");
                    } catch (MainControllerInterface.DatabaseAccesException e) {
                        showErrorMessage(e);
                    }
                });
            });
            hBox.getChildren().add(addPhoto);
        }


        //kontrola vložených dat
        Dialog dialog = new Dialog();
        ((Stage) dialog.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/info_icon.png"));
        InvalidationListener listener = observable -> {
            try {
                isItNull(lastName);
                isItNull(firstName);
                isItNull(email);
                if (teacher.getId() == null)
                    isItNull(password);
                dialog.getDialogPane().lookupButton(save).setDisable(false);
            } catch (NullPointerException | NumberFormatException e) {
                dialog.getDialogPane().lookupButton(save).setDisable(true);
            }
        };

        // popis prmpt textu
        firstName.setPromptText("jméno");
        lastName.setPromptText("příjmení");
        tittleBefore.setPromptText("titul před");
        tittleAfter.setPromptText("titul za");
        telNumber.setPromptText("telefon");
        mobileNumber.setPromptText("mobil");
        email.setPromptText("email");
        password.setPromptText("heslo");

        // grid všech polí
        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        if (photo.getId() != null) {
            ImageView imageView = new ImageView(new Image(new ByteArrayInputStream(photo.getObrazek())));
            imageView.setFitHeight(150);
            imageView.setPreserveRatio(true);
            grid.add(imageView, 1, 0);
        }
        grid.add(new Label("Obrázek:"), 0, 1);
        grid.add(hBox, 1, 1);
        grid.add(new Label("Zadejte jméno:"), 0, 2);
        grid.add(firstName, 1, 2);
        grid.add(new Label("Zadejte příjmení:"), 0, 3);
        grid.add(lastName, 1, 3);
        grid.add(new Label("Zadejte titul před:"), 0, 4);
        grid.add(tittleBefore, 1, 4);
        grid.add(new Label("Zadejte titul za:"), 0, 5);
        grid.add(tittleAfter, 1, 5);
        grid.add(new Label("Zadejte telefon:"), 0, 6);
        grid.add(telNumber, 1, 6);
        grid.add(new Label("Zadejte číslo mobilního telefonu:"), 0, 7);
        grid.add(mobileNumber, 1, 7);
        grid.add(new Label("Zadejte email:"), 0, 8);
        grid.add(email, 1, 8);
        grid.add(new Label("Zadejte heslo:"), 0, 9);
        grid.add(password, 1, 9);
        grid.add(new Label("Vyberte katedru:"), 0, 10);
        grid.add(workplacess, 1, 10);
        grid.add(new Label("Vyberte roli:"), 0, 11);
        grid.add(roless, 1, 11);
        grid.add(new Label("Vyberte úvazek:"), 0, 12);
        grid.add(obligationn, 1, 12);

        //nastavení dialogu (modal atd)
        dialog.setTitle("Vyučující");
        dialog.getDialogPane().getButtonTypes().addAll(save, cancel);
        dialog.getDialogPane().lookupButton(save).setDisable(teacher.getId() == null || !editable);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.getDialogPane().setContent(grid);

        // nastaveni listeneru pro kontrolu dat na pole
        firstName.textProperty().addListener(listener);
        lastName.textProperty().addListener(listener);
        tittleBefore.textProperty().addListener(listener);
        tittleAfter.textProperty().addListener(listener);
        telNumber.textProperty().addListener(listener);
        mobileNumber.textProperty().addListener(listener);
        email.textProperty().addListener(listener);
        password.textProperty().addListener(listener);


        //vraceni objektu
        Callback<ButtonType, Teacher> callback = (ButtonType dialogButton) -> {
            if (dialogButton == save) {
                teacher.setTitulPred(tittleBefore.getText());
                teacher.setJmeno(firstName.getText());
                teacher.setPrijmeni(lastName.getText());
                teacher.setTitulZa(tittleAfter.getText());
                teacher.setPracoviste(workplacess.getSelectionModel().getSelectedItem());
                teacher.setUvazek(obligationn.getSelectionModel().getSelectedItem());
                teacher.setEmail(email.getText());
                teacher.setTelefon(telNumber.getText());
                teacher.setMobil(mobileNumber.getText());
                teacher.setRole(roless.getSelectionModel().getSelectedItem());
                teacher.setHeslo(password.getText());
                return teacher;
            }
            return null;
        };
        dialog.setResultConverter(callback);
        return dialog;
    }

    public static Dialog getWorkplaceDialog(List<Faculty> faculties) {
        return getWorkplaceDialog(new Workplace(), faculties, true);
    }

    public static Dialog getWorkplaceDialog(Workplace workplace, List<Faculty> faculties, boolean editable) {
        // část deklarace polí
        ButtonType save = new ButtonType(
                "Uložit",
                ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType(
                "Zrušit",
                ButtonBar.ButtonData.CANCEL_CLOSE);
        TextField nazev = new TextField();
        nazev.setEditable(editable);
        nazev.setText(workplace.getNazev());
        TextField zkratka = new TextField();
        zkratka.setEditable(editable);
        zkratka.setText(workplace.getZkratka());

        ChoiceBox<Faculty> facultyChoiceBox = new ChoiceBox<>();
        facultyChoiceBox.setDisable(!editable);
        faculties.forEach(facultyChoiceBox.getItems()::add);
        if (workplace.getFakulta() != null) facultyChoiceBox.getSelectionModel().select(workplace.getFakulta());
        else facultyChoiceBox.getSelectionModel().selectFirst();

        //kontrola vložených dat
        Dialog dialog = new Dialog();
        ((Stage) dialog.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/info_icon.png"));
        InvalidationListener listener = observable -> {
            try {
                isItNull(zkratka);
                isItNull(nazev);
                dialog.getDialogPane().lookupButton(save).setDisable(false);
            } catch (NullPointerException | NumberFormatException e) {
                dialog.getDialogPane().lookupButton(save).setDisable(true);
            }
        };

        // popis prmpt textu
        nazev.setPromptText("název");
        zkratka.setPromptText("zkratka");

        // grid všech polí
        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        grid.add(new Label("Zadejte název:"), 0, 0);
        grid.add(nazev, 1, 0);
        grid.add(new Label("Zadejte zkratku:"), 0, 1);
        grid.add(zkratka, 1, 1);
        grid.add(new Label("Vyberte fakultu:"), 0, 2);
        grid.add(facultyChoiceBox, 1, 2);

        //nastavení dialogu (modal atd)
        dialog.setTitle("Pracoviště");
        dialog.getDialogPane().getButtonTypes().addAll(save, cancel);
        dialog.getDialogPane().lookupButton(save).setDisable(workplace.getId() == null || !editable);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.getDialogPane().setContent(grid);

        // nastaveni listeneru pro kontrolu dat na pole
        nazev.textProperty().addListener(listener);
        zkratka.textProperty().addListener(listener);


        //vraceni objektu
        Callback<ButtonType, Workplace> callback = (ButtonType dialogButton) -> {
            if (dialogButton == save) {
                workplace.setNazev(nazev.getText());
                workplace.setZkratka(zkratka.getText());
                workplace.setFakulta(facultyChoiceBox.getSelectionModel().getSelectedItem());
                return workplace;
            }
            return null;
        };
        dialog.setResultConverter(callback);
        return dialog;
    }

    public static Dialog getLearningActionDialog(List<MethodOfLearning> methodOfLearnings, List<Teacher> teachers,
                                                 List<Day> days, List<Subject> subjects, List<Room> rooms) {
        return getLearningActionDialog(new LearningAction(), methodOfLearnings, teachers, days, subjects, rooms, true, null);
    }

    public static Dialog getLearningActionDialog(List<MethodOfLearning> methodOfLearnings, List<Teacher> teachers,
                                                 List<Day> days, List<Subject> subjects, List<Room> rooms, Teacher owner) {
        return getLearningActionDialog(new LearningAction(), methodOfLearnings, teachers, days, subjects, rooms, true, owner);
    }

    public static Dialog getLearningActionDialog(LearningAction learningAction, List<MethodOfLearning> methodOfLearnings, List<Teacher> teachers,
                                                 List<Day> days, List<Subject> subjects, List<Room> rooms, boolean editable) {
        return getLearningActionDialog(learningAction, methodOfLearnings, teachers, days, subjects, rooms, editable, null);
    }

    public static Dialog getLearningActionDialog(LearningAction learningAction, List<MethodOfLearning> methodOfLearnings, List<Teacher> teachers,
                                                 List<Day> days, List<Subject> subjects, List<Room> rooms, boolean editable, Teacher owner) {
        // část deklarace polí
        ButtonType save = new ButtonType(
                "Uložit",
                ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType(
                "Zrušit",
                ButtonBar.ButtonData.CANCEL_CLOSE);
        TextField kapacita = new TextField();
        kapacita.setText(learningAction.getKapacita());
        kapacita.setEditable(editable);


        final int initialValueZ = 7;
        final int initialValueK = 8;

        Spinner<Integer> zacatekHodiny = new Spinner<>();
        zacatekHodiny.setDisable(!editable);
        Spinner<Integer> konecHodiny = new Spinner<>();
        konecHodiny.setDisable(!editable);
        SpinnerValueFactory<Integer> valuesZacatek = new SpinnerValueFactory.IntegerSpinnerValueFactory(7, 19, initialValueZ);
        if (learningAction.getPocatek() != null)
            valuesZacatek.setValue(Integer.valueOf(learningAction.getPocatek().split(":")[0]));
        SpinnerValueFactory<Integer> valuesKonec = new SpinnerValueFactory.IntegerSpinnerValueFactory(8, 20, initialValueK);
        if (learningAction.getKonec() != null)
            valuesKonec.setValue(Integer.valueOf(learningAction.getKonec().split(":")[0]));

        //TextField pocatek = new TextField();
        //pocatek.setText(learningAction.getPocatek());
        //pocatek.setEditable(editable);
        zacatekHodiny.setValueFactory(valuesZacatek);
        //TextField konec = new TextField();
        //konec.setText(learningAction.getKonec());
        //konec.setEditable(editable);
        konecHodiny.setValueFactory(valuesKonec);

        ChoiceBox<MethodOfLearning> methodOfLearningChoiceBox = new ChoiceBox<>();
        methodOfLearnings.forEach(methodOfLearningChoiceBox.getItems()::add);
        methodOfLearningChoiceBox.setDisable(!editable);
        if (learningAction.getZpusobVyuky() != null)
            methodOfLearningChoiceBox.getSelectionModel().select(learningAction.getZpusobVyuky());
        else methodOfLearningChoiceBox.getSelectionModel().selectFirst();

        ChoiceBox<Teacher> teacherChoiceBox = new ChoiceBox<>();
        teachers.forEach(teacherChoiceBox.getItems()::add);
        teacherChoiceBox.setDisable(!editable || owner != null);
        if (learningAction.getVyucujici() != null)
            teacherChoiceBox.getSelectionModel().select(learningAction.getVyucujici());
        else if (owner != null) teacherChoiceBox.getSelectionModel().select(owner);
        else teacherChoiceBox.getSelectionModel().selectFirst();

        ChoiceBox<Day> dayChoiceBox = new ChoiceBox<>();
        days.forEach(dayChoiceBox.getItems()::add);
        dayChoiceBox.setDisable(!editable);
        if (learningAction.getDen() != null) dayChoiceBox.getSelectionModel().select(learningAction.getDen());
        else dayChoiceBox.getSelectionModel().selectFirst();

        ChoiceBox<Subject> subjectChoiceBox = new ChoiceBox<>();
        subjects.forEach(subjectChoiceBox.getItems()::add);
        subjectChoiceBox.setDisable(!editable);
        if (learningAction.getPredmet() != null)
            subjectChoiceBox.getSelectionModel().select(learningAction.getPredmet());
        else subjectChoiceBox.getSelectionModel().selectFirst();

        ChoiceBox<Room> roomChoiceBox = new ChoiceBox<>();
        rooms.forEach(roomChoiceBox.getItems()::add);
        roomChoiceBox.setDisable(!editable);
        if (learningAction.getUcebna() != null) roomChoiceBox.getSelectionModel().select(learningAction.getUcebna());
        else roomChoiceBox.getSelectionModel().selectFirst();

        //kontrola vložených dat
        Dialog dialog = new Dialog();
        ((Stage) dialog.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/info_icon.png"));
        InvalidationListener listener = observable -> {
            try {
                if (!editable) throw new NullPointerException();
                isItNull(kapacita);
                dialog.getDialogPane().lookupButton(save).setDisable(false);
            } catch (NullPointerException | NumberFormatException e) {
                dialog.getDialogPane().lookupButton(save).setDisable(true);
            }
        };

        // popis prmpt textu
        kapacita.setPromptText("kapacita");
        //pocatek.setPromptText("počátek");
        //konec.setPromptText("konec");

        // grid všech polí
        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        grid.add(new Label("Zadejte kapacitu:"), 0, 0);
        grid.add(kapacita, 1, 0);
        grid.add(new Label("Zadejte počátek:"), 0, 1);
        // grid.add(pocatek, 1, 1);
        grid.add(zacatekHodiny, 1, 1);
        grid.add(new Label("Vyberte konec:"), 0, 2);
        // grid.add(konec, 1, 2);
        grid.add(konecHodiny, 1, 2);
        grid.add(new Label("Vyberte způsob výuky:"), 0, 3);
        grid.add(methodOfLearningChoiceBox, 1, 3);
        grid.add(new Label("Vyberte vyučujícího:"), 0, 4);
        grid.add(teacherChoiceBox, 1, 4);
        grid.add(new Label("Vyberte den:"), 0, 5);
        grid.add(dayChoiceBox, 1, 5);
        grid.add(new Label("Vyberte předmět:"), 0, 6);
        grid.add(subjectChoiceBox, 1, 6);
        grid.add(new Label("Vyberte místnost:"), 0, 7);
        grid.add(roomChoiceBox, 1, 7);

        //nastavení dialogu (modal atd)
        dialog.setTitle("Vyučovací akce");
        dialog.getDialogPane().getButtonTypes().addAll(save, cancel);
        dialog.getDialogPane().lookupButton(save).setDisable(learningAction.getId() == null || !editable);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.getDialogPane().setContent(grid);

        // nastaveni listeneru pro kontrolu dat na pole
        kapacita.textProperty().addListener(listener);
        //pocatek.textProperty().addListener(listener);
        //konec.textProperty().addListener(listener);


        //vraceni objektu
        Callback<ButtonType, LearningAction> callback = (ButtonType dialogButton) -> {
            if (dialogButton == save) {
                learningAction.setZpusobVyuky(methodOfLearningChoiceBox.getSelectionModel().getSelectedItem());
                learningAction.setVyucujici(teacherChoiceBox.getSelectionModel().getSelectedItem());
                learningAction.setUcebna(roomChoiceBox.getSelectionModel().getSelectedItem());
                learningAction.setPredmet(subjectChoiceBox.getSelectionModel().getSelectedItem());
                learningAction.setPocatek(zacatekHodiny.getValue().toString() + ":00");
                learningAction.setKonec(konecHodiny.getValue().toString() + ":00");
                learningAction.setKapacita(kapacita.getText());
                learningAction.setDen(dayChoiceBox.getSelectionModel().getSelectedItem());
                return learningAction;
            }
            return null;
        };
        dialog.setResultConverter(callback);
        return dialog;
    }

    public static Dialog getSubjectDialog(List<Semester> semestr, List<CategoryOfSubject> category, List<ConclusionOfSubject> conclusion,
                                          List<Teacher> garant, List<RecommendedYear> recommendedYears) {
        return getSubjectDialog(new Subject(), semestr, category, conclusion, garant, recommendedYears, true);
    }

    public static Dialog getSubjectDialog(Subject subject, List<Semester> semestr, List<CategoryOfSubject> category,
                                          List<ConclusionOfSubject> conclusion, List<Teacher> garant, List<RecommendedYear> recommendedYears, boolean editable) {

        ButtonType save = new ButtonType("Uložit", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Zrušit", ButtonBar.ButtonData.CANCEL_CLOSE);

        TextField nazevPredmetu = new TextField();
        nazevPredmetu.setText(subject.getNazev());
        nazevPredmetu.setEditable(editable);
        TextField zkratkaPredmetu = new TextField();
        zkratkaPredmetu.setText(subject.getZkratka());
        zkratkaPredmetu.setEditable(editable);
        TextField rozsahHodin = new TextField();
        rozsahHodin.setText(subject.getRozsahHodin());
        rozsahHodin.setEditable(editable);

        ChoiceBox<SemesterTypes> semesterChoiceBox = new ChoiceBox<>();
        semesterChoiceBox.setDisable(!editable);
        semesterChoiceBox.getItems().addAll(SemesterTypes.values());
        if (subject.getSemestr() != null) {
            if (subject.getSemestr().size() == 2)
                semesterChoiceBox.getSelectionModel().select(SemesterTypes.ZIMNI_A_LETNI);
            else if (subject.getSemestr().size() == 1 && subject.getSemestr().get(0).toString().equals(SemesterTypes.ZIMNI.toString()))
                semesterChoiceBox.getSelectionModel().select(SemesterTypes.ZIMNI);
            else if (subject.getSemestr().size() == 1 && subject.getSemestr().get(0).toString().equals(SemesterTypes.LETNI.toString()))
                semesterChoiceBox.getSelectionModel().select(SemesterTypes.LETNI);
        } else semesterChoiceBox.getSelectionModel().selectFirst();

        ChoiceBox<CategoryOfSubject> categoryOfSubjectChoiceBox = new ChoiceBox<>();
        categoryOfSubjectChoiceBox.setDisable(!editable);
        category.forEach(categoryOfSubjectChoiceBox.getItems()::add);
        if (subject.getKategorie() != null)
            categoryOfSubjectChoiceBox.getSelectionModel().select(subject.getKategorie());
        else categoryOfSubjectChoiceBox.getSelectionModel().selectFirst();

        ChoiceBox<ConclusionOfSubject> conclusionChoiceBox = new ChoiceBox<>();
        conclusionChoiceBox.setDisable(!editable);
        conclusion.forEach(conclusionChoiceBox.getItems()::add);
        if (subject.getZpusobZakonceni() != null)
            conclusionChoiceBox.getSelectionModel().select(subject.getZpusobZakonceni());
        else conclusionChoiceBox.getSelectionModel().selectFirst();

        ChoiceBox<Teacher> garantChoiceBox = new ChoiceBox<>();
        garantChoiceBox.setDisable(!editable);
        garant.forEach(garantChoiceBox.getItems()::add);
        if (subject.getGarant() != null)
            garantChoiceBox.getSelectionModel().select(subject.getGarant());
        else garantChoiceBox.getSelectionModel().selectFirst();

        ChoiceBox<RecommendedYear> recommendedYearChoiceBox = new ChoiceBox<>();
        recommendedYearChoiceBox.setDisable(!editable);
        recommendedYears.forEach(recommendedYearChoiceBox.getItems()::add);
        recommendedYearChoiceBox.getItems().add(null);
        if (subject.getDoporucenyRocnik() != null)
            recommendedYearChoiceBox.getSelectionModel().select(subject.getDoporucenyRocnik());
        else recommendedYearChoiceBox.getSelectionModel().selectFirst();

        //kontrola vložených dat
        Dialog dialog = new Dialog();
        ((Stage) dialog.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/info_icon.png"));
        InvalidationListener listener = observable -> {
            try {
                isItNull(nazevPredmetu);
                isItNull(zkratkaPredmetu);
                isItNull(rozsahHodin);
                dialog.getDialogPane().lookupButton(save).setDisable(false);
            } catch (NullPointerException | NumberFormatException e) {
                dialog.getDialogPane().lookupButton(save).setDisable(true);
            }
        };

        // popis prmpt textu
        nazevPredmetu.setPromptText("název");
        zkratkaPredmetu.setPromptText("zkratka");
        rozsahHodin.setPromptText("rozsah");


        // grid všech polí
        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        grid.add(new Label("Zadejte název:"), 0, 0);
        grid.add(nazevPredmetu, 1, 0);
        grid.add(new Label("Zadejte zkratku:"), 0, 1);
        grid.add(zkratkaPredmetu, 1, 1);
        grid.add(new Label("Zadejte rozsah hodin:"), 0, 2);
        grid.add(rozsahHodin, 1, 2);
        grid.add(new Label("Vyberte semestr:"), 0, 3);
        grid.add(semesterChoiceBox, 1, 3);
        grid.add(new Label("Vyberte kategorii předmětu:"), 0, 4);
        grid.add(categoryOfSubjectChoiceBox, 1, 4);
        grid.add(new Label("Vyberte způsob zakončení:"), 0, 5);
        grid.add(conclusionChoiceBox, 1, 5);
        grid.add(new Label("Vyberte doporučený ročník:"), 0, 6);
        grid.add(recommendedYearChoiceBox, 1, 6);
        grid.add(new Label("Vyberte garanta předmětu:"), 0, 7);
        grid.add(garantChoiceBox, 1, 7);

        //nastavení dialogu (modal atd)
        dialog.setTitle("Předmět");
        dialog.getDialogPane().getButtonTypes().addAll(save, cancel);
        dialog.getDialogPane().lookupButton(save).setDisable(subject.getId() == null || !editable);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.getDialogPane().setContent(grid);

        // nastaveni listeneru pro kontrolu dat na pole
        nazevPredmetu.textProperty().addListener(listener);
        zkratkaPredmetu.textProperty().addListener(listener);
        rozsahHodin.textProperty().addListener(listener);


        //vraceni objektu
        Callback<ButtonType, Subject> callback = (ButtonType dialogButton) -> {
            if (dialogButton == save) {
                subject.setNazev(nazevPredmetu.getText());
                subject.setZkratka(zkratkaPredmetu.getText());
                subject.setRozsahHodin(rozsahHodin.getText());
                subject.setKategorie(categoryOfSubjectChoiceBox.getSelectionModel().getSelectedItem());
                subject.setZpusobZakonceni(conclusionChoiceBox.getSelectionModel().getSelectedItem());
                subject.setGarant(garantChoiceBox.getSelectionModel().getSelectedItem());
                subject.setDoporucenyRocnik(recommendedYearChoiceBox.getSelectionModel().getSelectedItem());
                List<Semester> sem = new ArrayList<>();
                if (semesterChoiceBox.getSelectionModel().getSelectedItem() != null) {
                    switch (semesterChoiceBox.getSelectionModel().getSelectedItem()) {
                        case ZIMNI_A_LETNI: {
                            sem.add(semestr.get(0));
                            sem.add(semestr.get(1));
                            break;
                        }
                        case LETNI: {
                            sem.add(semestr.get(1));
                            break;
                        }
                        case ZIMNI: {
                            sem.add(semestr.get(0));
                            break;
                        }
                    }
                }
                subject.setSemestr(sem);
                return subject;
            }
            return null;
        };
        dialog.setResultConverter(callback);

        return dialog;
    }

    public static Dialog getFieldOfStudyDialog(List<FormOfStudy> formOfStudies, List<Workplace> workplaces) {
        return getFieldOfStudyDialog(new FieldOfStudy(), formOfStudies, workplaces, true);
    }

    public static Dialog getFieldOfStudyDialog(FieldOfStudy fieldOfStudy, List<FormOfStudy> formOfStudies, List<Workplace> workplaces, boolean editable) {
        ButtonType save = new ButtonType("Uložit", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Zrušit", ButtonBar.ButtonData.CANCEL_CLOSE);

        TextField nazev = new TextField();
        nazev.setEditable(editable);
        nazev.setText(fieldOfStudy.getNazev());
        TextField zkratka = new TextField();
        zkratka.setEditable(editable);
        zkratka.setText(fieldOfStudy.getZkratka());

        ChoiceBox<Workplace> workplaceChoiceBox = new ChoiceBox<>();
        workplaceChoiceBox.setDisable(!editable);
        workplaces.forEach(workplaceChoiceBox.getItems()::add);
        if (fieldOfStudy.getPracoviste() != null)
            workplaceChoiceBox.getSelectionModel().select(fieldOfStudy.getPracoviste());
        else workplaceChoiceBox.getSelectionModel().selectFirst();

        ChoiceBox<FormsTypes> formsTypesChoiceBox = new ChoiceBox<>();
        formsTypesChoiceBox.setDisable(!editable);
        formsTypesChoiceBox.getItems().addAll(FormsTypes.values());
        if (fieldOfStudy.getForma() != null) {
            if (fieldOfStudy.getForma().size() == 2)
                formsTypesChoiceBox.getSelectionModel().select(FormsTypes.PREZENCNI_A_KOMBINOVANA);
            else if (fieldOfStudy.getForma().size() == 1 && fieldOfStudy.getForma().get(0).toString().equals(FormsTypes.PREZENCNI.toString()))
                formsTypesChoiceBox.getSelectionModel().select(FormsTypes.PREZENCNI);
            else if (fieldOfStudy.getForma().size() == 1 && fieldOfStudy.getForma().get(0).toString().equals(FormsTypes.KOMBINOVANA.toString()))
                formsTypesChoiceBox.getSelectionModel().select(FormsTypes.KOMBINOVANA);
        } else formsTypesChoiceBox.getSelectionModel().selectFirst();

        //kontrola vložených dat
        Dialog dialog = new Dialog();
        ((Stage) dialog.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/info_icon.png"));
        InvalidationListener listener = observable -> {
            try {
                isItNull(nazev);
                isItNull(zkratka);
                dialog.getDialogPane().lookupButton(save).setDisable(false);
            } catch (NullPointerException | NumberFormatException e) {
                dialog.getDialogPane().lookupButton(save).setDisable(true);
            }
        };

        // popis prmpt textu
        nazev.setPromptText("název");
        zkratka.setPromptText("zkratka");

        // grid všech polí
        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        grid.add(new Label("Zadejte název:"), 0, 0);
        grid.add(nazev, 1, 0);
        grid.add(new Label("Zadejte zkratku:"), 0, 1);
        grid.add(zkratka, 1, 1);
        grid.add(new Label("Vyberte pracoviště:"), 0, 2);
        grid.add(workplaceChoiceBox, 1, 2);
        grid.add(new Label("Vyberte formu studia:"), 0, 3);
        grid.add(formsTypesChoiceBox, 1, 3);

        //nastavení dialogu (modal atd)
        dialog.setTitle("Studijní obor");
        dialog.getDialogPane().getButtonTypes().addAll(save, cancel);
        dialog.getDialogPane().lookupButton(save).setDisable(fieldOfStudy.getId() == null || !editable);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.getDialogPane().setContent(grid);

        // nastaveni listeneru pro kontrolu dat na pole
        nazev.textProperty().addListener(listener);
        zkratka.textProperty().addListener(listener);


        //vraceni objektu
        Callback<ButtonType, FieldOfStudy> callback = (ButtonType dialogButton) -> {
            if (dialogButton == save) {
                fieldOfStudy.setNazev(nazev.getText());
                fieldOfStudy.setZkratka(zkratka.getText());
                fieldOfStudy.setPracoviste(workplaceChoiceBox.getSelectionModel().getSelectedItem());
                List<FormOfStudy> sem = new ArrayList<>();
                if (formsTypesChoiceBox.getSelectionModel().getSelectedItem() != null) {
                    switch (formsTypesChoiceBox.getSelectionModel().getSelectedItem()) {
                        case PREZENCNI_A_KOMBINOVANA: {
                            sem.add(formOfStudies.get(0));
                            sem.add(formOfStudies.get(1));
                            break;
                        }
                        case KOMBINOVANA: {
                            sem.add(formOfStudies.get(1));
                            break;
                        }
                        case PREZENCNI: {
                            sem.add(formOfStudies.get(0));
                            break;
                        }
                    }
                }
                fieldOfStudy.setForma(sem);
                return fieldOfStudy;
            }
            return null;
        };
        dialog.setResultConverter(callback);

        return dialog;
    }


    private static Dialog getPhotoDialog(Photo photo) {
        // část deklarace polí
        ButtonType save = new ButtonType(
                "Uložit",
                ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType(
                "Zrušit",
                ButtonBar.ButtonData.CANCEL_CLOSE);
        TextField info = new TextField();
        info.setText(photo.getInfo());
        Button button = new Button();
        button.setText("Procházet");


        //kontrola vložených dat
        Dialog dialog = new Dialog();
        ((Stage) dialog.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/info_icon.png"));
        InvalidationListener listener = observable -> {
            setPhotoMethod(photo, save, info, dialog);
        };

        button.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                try {
                    byte[] fileContent = Files.readAllBytes(selectedFile.toPath());
                    photo.setObrazek(fileContent);
                    setPhotoMethod(photo, save, info, dialog);
                } catch (Exception e) {

                }
            }
        });

        // popis prmpt textu
        info.setPromptText("info");

        // grid všech polí
        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        grid.add(new Label("Zadejte název:"), 0, 0);
        grid.add(info, 1, 0);
        grid.add(new Label("Vyberte fotku:"), 0, 1);
        grid.add(button, 1, 1);

        //nastavení dialogu (modal atd)
        dialog.setTitle("Fotografie");
        dialog.getDialogPane().getButtonTypes().addAll(save, cancel);
        dialog.getDialogPane().lookupButton(save).setDisable(photo.getId() == null);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.getDialogPane().setContent(grid);

        // nastaveni listeneru pro kontrolu dat na pole
        info.textProperty().addListener(listener);


        //vraceni objektu
        Callback<ButtonType, Photo> callback = (ButtonType dialogButton) -> {
            if (dialogButton == save) {
                photo.setInfo(info.getText());
                return photo;
            }
            return null;
        };
        dialog.setResultConverter(callback);
        return dialog;
    }

    private static void setPhotoMethod(Photo photo, ButtonType save, TextField info, Dialog dialog) {
        try {
            isItNull(info);
            if (photo.getObrazek() == null) throw new NullPointerException("Nezadány všechny údaje");
            dialog.getDialogPane().lookupButton(save).setDisable(false);
        } catch (NullPointerException | NumberFormatException e) {
            dialog.getDialogPane().lookupButton(save).setDisable(true);
        }
    }

    public static Dialog getStudyPlanDialog(List<Subject> subjects) {
        // část deklarace polí
        ButtonType save = new ButtonType(
                "Uložit",
                ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType(
                "Zrušit",
                ButtonBar.ButtonData.CANCEL_CLOSE);

        ChoiceBox<Subject> subjectChoiceBox = new ChoiceBox<>();
        subjects.forEach(subjectChoiceBox.getItems()::add);
        subjectChoiceBox.getSelectionModel().selectFirst();

        //kontrola vložených dat
        Dialog dialog = new Dialog();
        ((Stage) dialog.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/info_icon.png"));

        // grid všech polí
        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        grid.add(new Label("Vyberte předmět:"), 0, 0);
        grid.add(subjectChoiceBox, 1, 0);

        //nastavení dialogu (modal atd)
        dialog.setTitle("Studijní plán");
        dialog.getDialogPane().getButtonTypes().addAll(save, cancel);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.getDialogPane().setContent(grid);


        //vraceni objektu
        Callback<ButtonType, StudyPlan> callback = (ButtonType dialogButton) -> {
            if (dialogButton == save) {
                return new StudyPlan(subjectChoiceBox.getSelectionModel().getSelectedItem());
            }
            return null;
        };
        dialog.setResultConverter(callback);
        return dialog;
    }

    public static Dialog getRoomDialog() {
        return getRoomDialog(new Room(), true);
    }

    public static Dialog getRoomDialog(Room room, boolean editable) {
        // část deklarace polí
        ButtonType save = new ButtonType(
                "Uložit",
                ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType(
                "Zrušit",
                ButtonBar.ButtonData.CANCEL_CLOSE);
        TextField popis = new TextField();
        popis.setText(room.getPopis());
        popis.setEditable(editable);
        TextField oznaceni = new TextField();
        oznaceni.setText(room.getOznaceni());
        oznaceni.setEditable(editable);

        //kontrola vložených dat
        Dialog dialog = new Dialog();
        ((Stage) dialog.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/info_icon.png"));
        InvalidationListener listener = observable -> {
            try {

                if (!editable) throw new NullPointerException();
                isItNull(popis);
                if (!editable) throw new NullPointerException();
                isItNull(oznaceni);
                dialog.getDialogPane().lookupButton(save).setDisable(false);
            } catch (NullPointerException | NumberFormatException e) {
                dialog.getDialogPane().lookupButton(save).setDisable(true);
            }
        };

        // popis prmpt textu
        popis.setPromptText("popis učebny");
        oznaceni.setPromptText("Oznaceni učebny");

        // grid všech polí
        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        grid.add(new Label("Zadejte popis:"), 0, 0);
        grid.add(popis, 1, 0);
        grid.add(new Label("Zadejte označení:"), 0, 1);
        grid.add(oznaceni, 1, 1);
        //nastavení dialogu (modal atd)
        dialog.setTitle("Učebna");
        dialog.getDialogPane().getButtonTypes().addAll(save, cancel);
        dialog.getDialogPane().lookupButton(save).setDisable(room.getId() == null || !editable);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.getDialogPane().setContent(grid);

        // nastaveni listeneru pro kontrolu dat na pole
        popis.textProperty().addListener(listener);
        oznaceni.textProperty().addListener(listener);


        //vraceni objektu
        Callback<ButtonType, Room> callback = (ButtonType dialogButton) -> {
            if (dialogButton == save) {
                room.setPopis(popis.getText());
                room.setOznaceni(oznaceni.getText());
                return room;
            }
            return null;
        };
        dialog.setResultConverter(callback);
        return dialog;
    }

    public static FileChooser getImportDialog() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Importovat");
        fileChooser.setInitialDirectory(new File("/"));
        fileChooser.setInitialFileName("import.csv");
        return fileChooser;
    }

    public static Dialog changePasswordDialog(String password) {
        // část deklarace polí
        ButtonType save = new ButtonType(
                "Uložit",
                ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType(
                "Zrušit",
                ButtonBar.ButtonData.CANCEL_CLOSE);
        PasswordField oldPassword = new PasswordField();
        PasswordField newPassword = new PasswordField();
        PasswordField newPasswordAgain = new PasswordField();
        //kontrola vložených dat
        Dialog dialog = new Dialog();
        ((Stage) dialog.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/info_icon.png"));
        InvalidationListener listener = observable -> {
            try {
                if (!oldPassword.getText().equals(password)) throw new NullPointerException();
                if (!newPassword.getText().equals(newPasswordAgain.getText())) throw new NullPointerException();
                isItNull(newPassword);
                isItNull(newPasswordAgain);
                dialog.getDialogPane().lookupButton(save).setDisable(false);
            } catch (NullPointerException | NumberFormatException e) {
                dialog.getDialogPane().lookupButton(save).setDisable(true);
            }
        };

        // popis prmpt textu
        oldPassword.setPromptText("Staré heslo");
        newPassword.setPromptText("Nové heslo");
        newPasswordAgain.setPromptText("Nové heslo znovu");

        // grid všech polí
        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        grid.add(new Label("Zadejte staré heslo:"), 0, 0);
        grid.add(oldPassword, 1, 0);
        grid.add(new Label("Zadejte nové heslo:"), 0, 1);
        grid.add(newPassword, 1, 1);
        grid.add(new Label("Zadejte nové heslo znovu:"), 0, 2);
        grid.add(newPasswordAgain, 1, 2);
        //nastavení dialogu (modal atd)
        dialog.setTitle("Učebna");
        dialog.getDialogPane().getButtonTypes().addAll(save, cancel);
        dialog.getDialogPane().lookupButton(save).setDisable(true);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.getDialogPane().setContent(grid);

        // nastaveni listeneru pro kontrolu dat na pole
        oldPassword.textProperty().addListener(listener);
        newPassword.textProperty().addListener(listener);
        newPasswordAgain.textProperty().addListener(listener);

        //vraceni objektu
        Callback<ButtonType, String> callback = (ButtonType dialogButton) -> {
            if (dialogButton == save) {
                return newPassword.getText();
            }
            return null;
        };
        dialog.setResultConverter(callback);
        return dialog;
    }

}
