package view.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.gui.libs.Dialogs;

import java.io.IOException;

public class GUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLGUI.fxml"));
            primaryStage.setTitle("Databázový projekt");
            primaryStage.setMinHeight(600);
            primaryStage.setMinWidth(1300);
            primaryStage.setMaximized(true);
            primaryStage.getIcons().add(new Image("/img/app_icon.png"));
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            Dialogs.showErrorMessage(e, "Chyba s připojením");
            Platform.exit();
        }

    }


    public static void main(String[] args) {

        launch(args);
    }
}
