package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import methods.Alerts;
import methods.OpenNewScene;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authSignUpButton;

    @FXML
    private Button buttonDocumentation;

    @FXML
    private ImageView imageForDocumentation;

    @FXML
    private TextField loginField;

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem menuItemAboutProgram;

    @FXML
    private MenuItem menuItemHelp;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void initialize() {
        OpenNewScene ons = new OpenNewScene();
        Alerts alerts = new Alerts();

        /* Натискання на кнопку авторізації
         Лямбда вираз, перевірає якщо поля порожні вибрасує помилку,
         Якщо воні заповнені відкріває нову сцено */
        authSignUpButton.setOnAction(actionEvent -> {
            if (loginField.getText().isEmpty() | passwordField.getText().isEmpty()) {
                alerts.showAlert(Alert.AlertType.ERROR, "ПОМИЛКА", "Будь ласка, введіть свій логін і пароль");
            } else {
                authSignUpButton.getScene().getWindow().hide();
                ons.openNewScene("../filesFXML/tableController.fxml");
                alerts.showAlert(Alert.AlertType.INFORMATION,
                        "Вхід виконано", "Ласкаво просимо " + loginField.getText());

            }


        });

        // Кнопка документації відкриває сцену з документацією.
        buttonDocumentation.setOnAction(event -> ons.openNewScene("../filesFXML/documentation.fxml"));

        // Меню ітем, котрий при натисканні відкриває нову сцену та показує розробника програми.
        menuItemAboutProgram.setOnAction(event -> ons.openNewScene("../filesFXML/aboutProgram.fxml"));

        // Меню ітем, котрий при натисканні відкриваєь нову сцену, та відображає документацію.
        menuItemHelp.setOnAction(event -> ons.openNewScene("../filesFXML/documentation.fxml"));

        // При наведені на поле відображає підсказку.
        authSignUpButton.setTooltip(new Tooltip("Вхід"));
        buttonDocumentation.setTooltip(new Tooltip("Відкрити документацію"));
        loginField.setTooltip(new Tooltip("Введіть логін"));
        passwordField.setTooltip(new Tooltip("Введіть пароль"));
        buttonDocumentation.setStyle("-fx-font: normal 17px 'cursive' ");
    }
}
