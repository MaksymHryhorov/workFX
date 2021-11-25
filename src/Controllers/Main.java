package Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import methods.Alerts;

import java.util.Objects;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../filesFXML/sample.fxml")));
        primaryStage.setTitle("Menu");
        primaryStage.setScene(new Scene(root, 800, 450));
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            Alerts alerts = new Alerts();
            alerts.showAlert(Alert.AlertType.CONFIRMATION,"WARNING","Please consider",
                    "Do your really want to exit?");

        });

    }

    public static void main(String[] args) {
        launch(args);
    }

}

