package view.gui.libs;

import javafx.beans.InvalidationListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.util.Callback;
import model.Teacher;
import model.Workplace;

import java.util.List;

public final class Dialogs {
    private Dialogs() {
    }

    private static void isItNull(TextField textField)
            throws NullPointerException {
        if (textField.getText() == null || textField.getText().equals("")) {
            throw new NullPointerException("Nezadány všechny údaje");
        }
    }

    public static Dialog getNewEmployeeDialog(List<Workplace> departments) {
        // část deklarace polí
        final ButtonType SAVE = new ButtonType(
                "Uložit",
                ButtonBar.ButtonData.OK_DONE);
        final ButtonType CANCEL = new ButtonType(
                "Zrušit",
                ButtonBar.ButtonData.CANCEL_CLOSE);
        final TextField FIRST_NAME = new TextField();
        final TextField LAST_NAME = new TextField();
        final TextField TITTLE_BEFORE = new TextField();
        final TextField TITTLE_AFTER = new TextField();
        final TextField TEL_NUMBER = new TextField();
        final TextField MOBILE_NUMBER = new TextField();
        final TextField EMAIL = new TextField();
        final ChoiceBox<String> DEPARTMENT = new ChoiceBox<>();
        departments.forEach((t) -> {
            DEPARTMENT.getItems().add(t.getZkratka());
        });
        DEPARTMENT.getSelectionModel().selectFirst();

        //kontrola vložených dat
        Dialog dialog = new Dialog();
        InvalidationListener listener = observable -> {
            try {
                isItNull(LAST_NAME);
                isItNull(FIRST_NAME);
                dialog.getDialogPane().lookupButton(SAVE).setDisable(false);
            } catch (NullPointerException | NumberFormatException e) {
                dialog.getDialogPane().lookupButton(SAVE).setDisable(true);
            }
        };

        // popis prmpt textu
        FIRST_NAME.setPromptText(
                "jméno");
        LAST_NAME.setPromptText(
                "příjmení");
        TITTLE_BEFORE.setPromptText("titul před");
        TITTLE_AFTER.setPromptText("titul za");
        TEL_NUMBER.setPromptText("telefon");
        MOBILE_NUMBER.setPromptText("mobil");
        EMAIL.setPromptText(
                "email");

        // grid všech polí
        GridPane grid = new GridPane();

        grid.setHgap(
                10);
        grid.setVgap(
                10);
        grid.setPadding(
                new Insets(20, 150, 10, 10));
        grid.add(
                new Label("Zadejte jméno:"), 0, 0);
        grid.add(FIRST_NAME,
                1, 0);
        grid.add(
                new Label("Zadejte příjmení:"), 0, 1);
        grid.add(LAST_NAME,
                1, 1);
        grid.add(
                new Label("Zadejte titul před:"), 0, 2);
        grid.add(TITTLE_BEFORE,
                1, 2);
        grid.add(
                new Label("Zadejte titul za:"), 0, 3);
        grid.add(TITTLE_AFTER,
                1, 3);
        grid.add(
                new Label("Zadejte telefon:"), 0, 4);
        grid.add(TEL_NUMBER,
                1, 4);
        grid.add(
                new Label("Zadejte číslo mobilního telefonu:"), 0, 5);
        grid.add(MOBILE_NUMBER,
                1, 5);
        grid.add(
                new Label("Zadejte email:"), 0, 6);
        grid.add(EMAIL,
                1, 6);
        grid.add(
                new Label("Vyberte katedru:"), 0, 7);
        grid.add(DEPARTMENT,
                1, 7);

        //nastavení dialogu (modal atd)
        dialog.setTitle(
                "Nový zaměstnanec");
        dialog.getDialogPane()
                .getButtonTypes().addAll(SAVE, CANCEL);
        dialog.getDialogPane()
                .lookupButton(SAVE).setDisable(true);
        dialog.initModality(Modality.APPLICATION_MODAL);

        dialog.getDialogPane()
                .setContent(grid);

        // nastaveni listeneru pro kontrolu dat na pole
        FIRST_NAME.textProperty()
                .addListener(listener);
        LAST_NAME.textProperty()
                .addListener(listener);

        TITTLE_BEFORE.textProperty()
                .addListener(listener);

        TITTLE_AFTER.textProperty()
                .addListener(listener);

        TEL_NUMBER.textProperty()
                .addListener(listener);

        MOBILE_NUMBER.textProperty()
                .addListener(listener);
        EMAIL.textProperty()
                .addListener(listener);

        //vraceni objektu
        Callback<ButtonType, Teacher> callback = (ButtonType dialogButton)
                -> {
            if (dialogButton == SAVE) {
                /*return new Workplace(
                        0,
                        FIRST_NAME.getText(),
                        LAST_NAME.getText(),
                        TITTLE_BEFORE.getText(),
                        TITTLE_AFTER.getText(),
                        TEL_NUMBER.getText(),
                        MOBILE_NUMBER.getText(),
                        EMAIL.getText()
                );*/
            }
            return null;
        };
        dialog.setResultConverter(callback);
        return dialog;
    }
}
