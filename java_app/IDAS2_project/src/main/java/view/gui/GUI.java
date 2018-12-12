package view.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.gui.libs.Dialogs;

import java.io.IOException;

public class GUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Task<Void> loadingTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    //FXMLGUIController.mainController = new MainController();
                } catch (Exception e) {
                    Dialogs.showErrorMessage(e);
                    Platform.exit();
                }
                return null;
            }
        };
        new Thread(loadingTask).start();
        loadingTask.setOnSucceeded(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLGUI.fxml"));
                primaryStage.setTitle("Databázový projekt");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            } catch (IOException e) {
                Dialogs.showErrorMessage(e);
                Platform.exit();
            }
        });
    }


    public static void main(String[] args) {

        launch(args);
    }
}
