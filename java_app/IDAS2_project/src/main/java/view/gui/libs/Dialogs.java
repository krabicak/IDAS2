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
import model.Obligation;
import model.Role;
import model.Teacher;
import model.Workplace;

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
        // část deklarace polí
        ButtonType save = new ButtonType(
                "Uložit",
                ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType(
                "Zrušit",
                ButtonBar.ButtonData.CANCEL_CLOSE);
        TextField firstName = new TextField();
        TextField lastName = new TextField();
        TextField tittleBefore = new TextField();
        TextField tittleAfter = new TextField();
        TextField telNumber = new TextField();
        TextField mobileNumber = new TextField();
        TextField email = new TextField();
        PasswordField password = new PasswordField();
        ChoiceBox<Workplace> workplacess = new ChoiceBox<>();
        workplaces.forEach(workplacess.getItems()::add);
        workplacess.getSelectionModel().selectFirst();

        ChoiceBox<Role> roless = new ChoiceBox<>();
        roles.forEach(roless.getItems()::add);
        roless.getSelectionModel().selectFirst();

        ChoiceBox<Obligation> obligationn = new ChoiceBox<>();
        obligations.forEach(obligationn.getItems()::add);
        obligationn.getSelectionModel().selectFirst();

        //kontrola vložených dat
        Dialog dialog = new Dialog();
        InvalidationListener listener = observable -> {
            try {
                isItNull(lastName);
                isItNull(firstName);
                isItNull(email);
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
        dialog.setTitle("Nový zaměstnanec");
        dialog.getDialogPane().getButtonTypes().addAll(save, cancel);
        dialog.getDialogPane().lookupButton(save).setDisable(true);
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
                return new Teacher(
                        tittleBefore.getText(),
                        firstName.getText(),
                        lastName.getText(),
                        tittleAfter.getText(),
                        workplacess.getSelectionModel().getSelectedItem(),
                        obligationn.getSelectionModel().getSelectedItem(),
                        email.getText(),
                        telNumber.getText(),
                        mobileNumber.getText(),
                        roless.getSelectionModel().getSelectedItem(),
                        password.getText());
            }
            return null;
        };
        dialog.setResultConverter(callback);
        return dialog;
    }
}
