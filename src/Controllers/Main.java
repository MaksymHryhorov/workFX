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

        //Загружаємо файл fxml з папки filesFXML
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../filesFXML/sample.fxml")));
        // Встановлюємо заголовок
        primaryStage.setTitle("Physical Person");
        // Встановлюємо розмір сцени
        primaryStage.setScene(new Scene(root, 800, 450));
        primaryStage.show();

        // Лямбда вираз. При виході з програми визиваємо метод класу Alerts, який перевіряє натиск на кнопку
        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            Alerts alerts = new Alerts();
            alerts.showAlert(Alert.AlertType.CONFIRMATION,"ПОПЕРЕДЖЕННЯ","Будь ласка оберіть",
                    "Ви дійсно бажаєте вийти?");

        });

    }

    public static void main(String[] args) {
        launch(args);
    }

}

