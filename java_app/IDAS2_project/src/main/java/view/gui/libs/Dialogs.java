package view.gui.libs;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.util.Callback;
import javafx.util.Pair;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Dialogs {
    private Dialogs() {
    }

    public static void showExitDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Přejete si ukončit tento program?");
        alert.setTitle("Konec");
        Optional<ButtonType> result = alert.showAndWait();
        result.ifPresent((t) -> {
            Platform.exit();
        });
    }

    public static void showInfoDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(message);
        alert.setTitle("Info");
        alert.show();
    }

    public static void showErrorMessage(Exception ex) {
        System.out.println(ex);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Chyba");
        alert.setHeaderText(ex.getLocalizedMessage());
        alert.setContentText(ex.toString());
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

    public static Dialog getTeacherDialog(List<Workplace> workplaces, List<Obligation> obligations, List<Role> roles) {
        return getTeacherDialog(new Teacher(), workplaces, obligations, roles);
    }

    public static Dialog getTeacherDialog(Teacher teacher, List<Workplace> workplaces, List<Obligation> obligations, List<Role> roles) {
        // část deklarace polí
        ButtonType save = new ButtonType(
                "Uložit",
                ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType(
                "Zrušit",
                ButtonBar.ButtonData.CANCEL_CLOSE);
        TextField firstName = new TextField();
        firstName.setText(teacher.getJmeno());
        TextField lastName = new TextField();
        lastName.setText(teacher.getPrijmeni());
        TextField tittleBefore = new TextField();
        tittleBefore.setText(teacher.getTitulPred());
        TextField tittleAfter = new TextField();
        tittleAfter.setText(teacher.getTitulZa());
        TextField telNumber = new TextField();
        telNumber.setText(teacher.getTelefon());
        TextField mobileNumber = new TextField();
        mobileNumber.setText(teacher.getMobil());
        TextField email = new TextField();
        email.setText(teacher.getEmail());
        PasswordField password = new PasswordField();
        ChoiceBox<Workplace> workplacess = new ChoiceBox<>();
        workplaces.forEach(workplacess.getItems()::add);
        if (teacher.getPracoviste() != null) workplacess.getSelectionModel().select(teacher.getPracoviste());
        else workplacess.getSelectionModel().selectFirst();

        ChoiceBox<Role> roless = new ChoiceBox<>();
        roles.forEach(roless.getItems()::add);
        if (teacher.getRole() != null) roless.getSelectionModel().select(teacher.getRole());
        else roless.getSelectionModel().selectFirst();

        ChoiceBox<Obligation> obligationn = new ChoiceBox<>();
        obligations.forEach(obligationn.getItems()::add);
        if (teacher.getUvazek() != null) obligationn.getSelectionModel().select(teacher.getUvazek());
        else obligationn.getSelectionModel().selectFirst();

        //kontrola vložených dat
        Dialog dialog = new Dialog();
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
        grid.add(new Label("Zadejte jméno:"), 0, 0);
        grid.add(firstName, 1, 0);
        grid.add(new Label("Zadejte příjmení:"), 0, 1);
        grid.add(lastName, 1, 1);
        grid.add(new Label("Zadejte titul před:"), 0, 2);
        grid.add(tittleBefore, 1, 2);
        grid.add(new Label("Zadejte titul za:"), 0, 3);
        grid.add(tittleAfter, 1, 3);
        grid.add(new Label("Zadejte telefon:"), 0, 4);
        grid.add(telNumber, 1, 4);
        grid.add(new Label("Zadejte číslo mobilního telefonu:"), 0, 5);
        grid.add(mobileNumber, 1, 5);
        grid.add(new Label("Zadejte email:"), 0, 6);
        grid.add(email, 1, 6);
        grid.add(new Label("Zadejte heslo:"), 0, 7);
        grid.add(password, 1, 7);
        grid.add(new Label("Vyberte katedru:"), 0, 8);
        grid.add(workplacess, 1, 8);
        grid.add(new Label("Vyberte roli:"), 0, 9);
        grid.add(roless, 1, 9);
        grid.add(new Label("Vyberte úvazek:"), 0, 10);
        grid.add(obligationn, 1, 10);

        //nastavení dialogu (modal atd)
        dialog.setTitle("Učitel");
        dialog.getDialogPane().getButtonTypes().addAll(save, cancel);
        dialog.getDialogPane().lookupButton(save).setDisable(teacher.getId() == null);
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
        return getWorkplaceDialog(new Workplace(), faculties);
    }

    public static Dialog getWorkplaceDialog(Workplace workplace, List<Faculty> faculties) {
        // část deklarace polí
        ButtonType save = new ButtonType(
                "Uložit",
                ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType(
                "Zrušit",
                ButtonBar.ButtonData.CANCEL_CLOSE);
        TextField nazev = new TextField();
        nazev.setText(workplace.getNazev());
        TextField zkratka = new TextField();
        zkratka.setText(workplace.getZkratka());

        ChoiceBox<Faculty> facultyChoiceBox = new ChoiceBox<>();
        faculties.forEach(facultyChoiceBox.getItems()::add);
        if (workplace.getFakulta() != null) facultyChoiceBox.getSelectionModel().select(workplace.getFakulta());
        else facultyChoiceBox.getSelectionModel().selectFirst();

        //kontrola vložených dat
        Dialog dialog = new Dialog();
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
        dialog.getDialogPane().lookupButton(save).setDisable(workplace.getId() == null);
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
        return getLearningActionDialog(new LearningAction(), methodOfLearnings, teachers, days, subjects, rooms);
    }

    public static Dialog getLearningActionDialog(LearningAction learningAction, List<MethodOfLearning> methodOfLearnings, List<Teacher> teachers,
                                                 List<Day> days, List<Subject> subjects, List<Room> rooms) {
        // část deklarace polí
        ButtonType save = new ButtonType(
                "Uložit",
                ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType(
                "Zrušit",
                ButtonBar.ButtonData.CANCEL_CLOSE);
        TextField kapacita = new TextField();
        kapacita.setText(learningAction.getKapacita());
        TextField pocatek = new TextField();
        pocatek.setText(learningAction.getPocatek());
        TextField konec = new TextField();
        konec.setText(learningAction.getKonec());

        ChoiceBox<MethodOfLearning> methodOfLearningChoiceBox = new ChoiceBox<>();
        methodOfLearnings.forEach(methodOfLearningChoiceBox.getItems()::add);
        if (learningAction.getZpusobVyuky() != null)
            methodOfLearningChoiceBox.getSelectionModel().select(learningAction.getZpusobVyuky());
        else methodOfLearningChoiceBox.getSelectionModel().selectFirst();

        ChoiceBox<Teacher> teacherChoiceBox = new ChoiceBox<>();
        teachers.forEach(teacherChoiceBox.getItems()::add);
        if (learningAction.getVyucujici() != null)
            teacherChoiceBox.getSelectionModel().select(learningAction.getVyucujici());
        else teacherChoiceBox.getSelectionModel().selectFirst();

        ChoiceBox<Day> dayChoiceBox = new ChoiceBox<>();
        days.forEach(dayChoiceBox.getItems()::add);
        if (learningAction.getDen() != null) dayChoiceBox.getSelectionModel().select(learningAction.getDen());
        else dayChoiceBox.getSelectionModel().selectFirst();

        ChoiceBox<Subject> subjectChoiceBox = new ChoiceBox<>();
        subjects.forEach(subjectChoiceBox.getItems()::add);
        if (learningAction.getPredmet() != null)
            subjectChoiceBox.getSelectionModel().select(learningAction.getPredmet());
        else subjectChoiceBox.getSelectionModel().selectFirst();

        ChoiceBox<Room> roomChoiceBox = new ChoiceBox<>();
        rooms.forEach(roomChoiceBox.getItems()::add);
        if (learningAction.getUcebna() != null) roomChoiceBox.getSelectionModel().select(learningAction.getUcebna());
        else roomChoiceBox.getSelectionModel().selectFirst();

        //kontrola vložených dat
        Dialog dialog = new Dialog();
        InvalidationListener listener = observable -> {
            try {
                isItNull(kapacita);
                dialog.getDialogPane().lookupButton(save).setDisable(false);
            } catch (NullPointerException | NumberFormatException e) {
                dialog.getDialogPane().lookupButton(save).setDisable(true);
            }
        };

        // popis prmpt textu
        kapacita.setPromptText("kapacita");
        pocatek.setPromptText("počátek");
        konec.setPromptText("konec");

        // grid všech polí
        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        grid.add(new Label("Zadejte kapacitu:"), 0, 0);
        grid.add(kapacita, 1, 0);
        grid.add(new Label("Zadejte počátek:"), 0, 1);
        grid.add(pocatek, 1, 1);
        grid.add(new Label("Vyberte konec:"), 0, 2);
        grid.add(konec, 1, 2);
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
        dialog.setTitle("Pracoviště");
        dialog.getDialogPane().getButtonTypes().addAll(save, cancel);
        dialog.getDialogPane().lookupButton(save).setDisable(learningAction.getId() == null);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.getDialogPane().setContent(grid);

        // nastaveni listeneru pro kontrolu dat na pole
        kapacita.textProperty().addListener(listener);
        pocatek.textProperty().addListener(listener);
        konec.textProperty().addListener(listener);


        //vraceni objektu
        Callback<ButtonType, LearningAction> callback = (ButtonType dialogButton) -> {
            if (dialogButton == save) {
                learningAction.setZpusobVyuky(methodOfLearningChoiceBox.getSelectionModel().getSelectedItem());
                learningAction.setVyucujici(teacherChoiceBox.getSelectionModel().getSelectedItem());
                learningAction.setUcebna(roomChoiceBox.getSelectionModel().getSelectedItem());
                learningAction.setPredmet(subjectChoiceBox.getSelectionModel().getSelectedItem());
                learningAction.setPocatek(pocatek.getText());
                learningAction.setKonec(konec.getText());
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
        return getSubjectDialog(new Subject(), semestr, category, conclusion, garant, recommendedYears);
    }

    public static Dialog getSubjectDialog(Subject subject, List<Semester> semestr, List<CategoryOfSubject> category,
                                          List<ConclusionOfSubject> conclusion, List<Teacher> garant, List<RecommendedYear> recommendedYears) {

        ButtonType save = new ButtonType("Uložit", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Zrušit", ButtonBar.ButtonData.CANCEL_CLOSE);

        TextField nazevPredmetu = new TextField();
        nazevPredmetu.setText(subject.getNazev());
        TextField zkratkaPredmetu = new TextField();
        zkratkaPredmetu.setText(subject.getZkratka());
        TextField rozsahHodin = new TextField();
        rozsahHodin.setText(subject.getRozsahHodin());

        ChoiceBox<SemesterTypes> semesterChoiceBox = new ChoiceBox<>();
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
        category.forEach(categoryOfSubjectChoiceBox.getItems()::add);
        if (subject.getKategorie() != null)
            categoryOfSubjectChoiceBox.getSelectionModel().select(subject.getKategorie());
        else categoryOfSubjectChoiceBox.getSelectionModel().selectFirst();

        ChoiceBox<ConclusionOfSubject> conclusionChoiceBox = new ChoiceBox<>();
        conclusion.forEach(conclusionChoiceBox.getItems()::add);
        if (subject.getZpusobZakonceni() != null)
            conclusionChoiceBox.getSelectionModel().select(subject.getZpusobZakonceni());
        else conclusionChoiceBox.getSelectionModel().selectFirst();

        ChoiceBox<Teacher> garantChoiceBox = new ChoiceBox<>();
        garant.forEach(garantChoiceBox.getItems()::add);
        if (subject.getGarant() != null)
            garantChoiceBox.getSelectionModel().select(subject.getGarant());
        else garantChoiceBox.getSelectionModel().selectFirst();

        ChoiceBox<RecommendedYear> recommendedYearChoiceBox = new ChoiceBox<>();
        recommendedYears.forEach(recommendedYearChoiceBox.getItems()::add);
        recommendedYearChoiceBox.getItems().add(null);
        if (subject.getDoporucenyRocnik() != null)
            recommendedYearChoiceBox.getSelectionModel().select(subject.getDoporucenyRocnik());
        else recommendedYearChoiceBox.getSelectionModel().selectFirst();

        //kontrola vložených dat
        Dialog dialog = new Dialog();
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
        dialog.getDialogPane().lookupButton(save).setDisable(subject.getId() == null);
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

}
