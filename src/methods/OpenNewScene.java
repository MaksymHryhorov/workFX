package methods;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class OpenNewScene {
    public void openNewScene(String window) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));


        if (window.equals("../filesFXML/tableController.fxml")) {
            stage.setTitle("Таблиця Користувачів");
            stage.setOnCloseRequest(windowEvent -> {
                windowEvent.consume();
                Alerts alerts = new Alerts();
                alerts.showAlert(Alert.AlertType.CONFIRMATION,"ПОПЕРЕДЖЕННЯ","",
                        "Ви зберігли базу даних?");
            });
        }
        if (window.equals("../filesFXML/sample.fxml")) {
            stage.setTitle("Меню");
        }
        if (window.equals("../filesFXML/documentation.fxml")) {
            stage.setTitle("Документація");
        }

        stage.show();
    }
}
