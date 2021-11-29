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
    void initialize() {
        OpenNewScene ons = new OpenNewScene();
        Alerts alerts = new Alerts();

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
        buttonDocumentation.setOnAction(event -> ons.openNewScene("../filesFXML/documentation.fxml"));
        menuItemAboutProgram.setOnAction(event -> ons.openNewScene("../filesFXML/aboutProgram.fxml"));
        menuItemHelp.setOnAction(event -> ons.openNewScene("../filesFXML/documentation.fxml"));

        authSignUpButton.setTooltip(new Tooltip("Вхід"));
        buttonDocumentation.setStyle("-fx-font: normal 17px 'cursive' ");
        buttonDocumentation.setTooltip(new Tooltip("Відкрити документацію"));
        loginField.setTooltip(new Tooltip("Введіть логін"));
        passwordField.setTooltip(new Tooltip("Введіть пароль"));

    }

}
