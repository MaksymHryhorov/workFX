package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import methods.Alerts;
import methods.OpenNewScene;
import sample.UserAccount;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class TableController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<UserAccount, String> emailCol;

    @FXML
    private TextField emailInput;

    @FXML
    private TableColumn<UserAccount, String> firstNameCol;

    @FXML
    private TextField firstNameInput;

    @FXML
    private TableColumn<UserAccount, String> fullNameCol;

    @FXML
    private TableColumn<UserAccount, String> lastNameCol;

    @FXML
    private TextField lastNameInput;

    @FXML
    private TableColumn<UserAccount, Integer> pit;

    @FXML
    private TextField pitInput;

    @FXML
    private TableView<UserAccount> table;

    @FXML
    private TableColumn<UserAccount, String> userNameCol;

    @FXML
    private TextField userNameInput;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button regScene;

    @FXML
    private Button changeColumnButton;

    @FXML
    private TextField changeEmail;

    @FXML
    private TextField changeFirstName;

    @FXML
    private TextField changeLastName;

    @FXML
    private TextField changePit;

    @FXML
    private TextField changeUserName;

    @FXML
    private Button findButton;

    @FXML
    private void edit(ActionEvent event) {
        try {
            ObservableList<UserAccount> currentTableData = table.getItems();

            int currentPIT = Integer.parseInt(changePit.getText());

            for (UserAccount user : currentTableData) {
                if (user.getPit() == currentPIT) {
                    user.setUserName(changeUserName.getText());
                    user.setEmail(changeEmail.getText());
                    user.setFirstName(changeFirstName.getText());
                    user.setLastName(changeLastName.getText());
                    user.setPit(Integer.parseInt(changePit.getText()));

                    System.out.println("IN");

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("ПОПЕРЕДЖЕННЯ");
                    alert.setHeaderText(null);
                    alert.setContentText("Ви дійсно бажаєте редагувати користувача?");

                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        table.setItems(currentTableData);
                        table.refresh();
                        break;
                    }
                }
                System.out.println("no");
            }
        } catch (Exception e) {
            alerts.showAlert(Alert.AlertType.ERROR, "ПОМИЛКА", "Будь ласка, виберіть користувача в таблиці");
        }
    }

    @FXML
    private void rowClicked(javafx.scene.input.MouseEvent event) {
        try {
            UserAccount user = table.getSelectionModel().getSelectedItem();
            changeUserName.setText(String.valueOf(user.getUserName()));
            changeEmail.setText(String.valueOf(user.getEmail()));
            changeFirstName.setText(String.valueOf(user.getFirstName()));
            changeLastName.setText(String.valueOf(user.getLastName()));
            changePit.setText(String.valueOf(user.getPit()));
        } catch (Exception e) {
            alerts.showAlert(Alert.AlertType.ERROR, "ПОМИЛКА", "Виберіть користувача в таблиці");
        }

    }


    Alerts alerts = new Alerts();
    OpenNewScene ons = new OpenNewScene();

    private final FileInputStream fis = new FileInputStream("src/sample/map.txt");
    private final ObjectInputStream ois = new ObjectInputStream(fis);
    private final ArrayList<UserAccount> l = (ArrayList<UserAccount>) ois.readObject();
    ObservableList<UserAccount> list = FXCollections.observableArrayList(l);

    public TableController() throws IOException, ClassNotFoundException {
        fis.close();
        ois.close();
    }

    @FXML
    private void initialize() {

        table.setEditable(true);

        userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        userNameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        userNameCol.setOnEditCommit(userAccountStringCellEditEvent -> {
            UserAccount userAccount = userAccountStringCellEditEvent.getRowValue();
            userAccount.setUserName(userAccountStringCellEditEvent.getNewValue());
        });


        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());

        emailCol.setOnEditCommit(userAccountStringCellEditEvent -> {
            UserAccount userAccount = userAccountStringCellEditEvent.getRowValue();
            userAccount.setEmail(userAccountStringCellEditEvent.getNewValue());
        });


        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        firstNameCol.setOnEditCommit(userAccountStringCellEditEvent -> {
            UserAccount userAccount = userAccountStringCellEditEvent.getRowValue();
            userAccount.setFirstName(userAccountStringCellEditEvent.getNewValue());
        });


        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        lastNameCol.setOnEditCommit(userAccountStringCellEditEvent -> {
            UserAccount userAccount = userAccountStringCellEditEvent.getRowValue();
            userAccount.setLastName(userAccountStringCellEditEvent.getNewValue());
        });


        pit.setCellValueFactory(new PropertyValueFactory<>("pit"));
        pit.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        pit.setOnEditCommit(userAccountStringCellEditEvent -> {
            UserAccount userAccount = userAccountStringCellEditEvent.getRowValue();
            userAccount.setPit(userAccountStringCellEditEvent.getNewValue());
        });


        addButton.setOnAction(event -> {
            try {
                if (userNameInput.getText().isEmpty() | emailInput.getText().isEmpty() |
                        firstNameInput.getText().isEmpty() | lastNameInput.getText().isEmpty() |
                        pitInput.getText().isEmpty()) {
                    alerts.showAlert(Alert.AlertType.ERROR, "ПОМИЛКА", "Будь ласка, заповніть усі поля");
                } else {
                    UserAccount userAccount = new UserAccount(userNameInput.getText(), emailInput.getText(),
                            firstNameInput.getText(), lastNameInput.getText(), Integer.parseInt(pitInput.getText()));
                    add(userAccount);
                }

            } catch (NumberFormatException e) {
                alerts.showAlert(Alert.AlertType.ERROR, "ПОМИЛКА", "Введіть лише числа в \"іпн\"");
            }

        });

        deleteButton.setOnAction(event -> deleteButtonClicked());

        saveButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ПОПЕРЕДЖЕННЯ");
            alert.setHeaderText(null);
            alert.setContentText("Ви дійсно бажаєте зберегти базу даних?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                saveButtonClicked();
            } else
                System.out.println("nope");

        });

        userNameInput.setTooltip(new Tooltip("Введіть Ім'я користувача"));
        emailInput.setTooltip(new Tooltip("Введіть Пошту"));
        firstNameInput.setTooltip(new Tooltip("Введіть Ім'я"));
        lastNameInput.setTooltip(new Tooltip("Введіть Фамілію"));
        pitInput.setTooltip(new Tooltip("Введіть ІПН"));
        addButton.setTooltip(new Tooltip("Додати до таблиці"));
        saveButton.setTooltip(new Tooltip("Зберігти Базу Даних"));
        deleteButton.setTooltip(new Tooltip("Видалити користувача"));
        changeColumnButton.setTooltip(new Tooltip("Зберегти зміни"));
        regScene.setTooltip(new Tooltip("Вийти з аккаунта"));


        regScene.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ПОПЕРЕДЖЕННЯ");
            alert.setHeaderText(null);
            alert.setContentText("Ви дійсно бажаєте вийти?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                regScene.getScene().getWindow().hide();
                ons.openNewScene("../filesFXML/sample.fxml");
            } else
                System.out.println("nope");

        });

        /*ObservableList<UserAccount> list = getUserList();*/

        try {
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        } catch (Exception e) {
            alerts.showAlert(Alert.AlertType.ERROR, "ПОМИЛКА", "Виберіть користувача в таблиці");
        }

        table.setStyle("-fx-font: normal 16px 'cursive' ");

        findButton.setOnAction(event -> ons.openNewScene("../filesFXML/tableSearch.fxml"));
        table.setItems(list);

    }

    private void add(UserAccount userAccount) {
        table.getItems().add(userAccount);
        clearFields();
    }

    private void clearFields() {
        userNameInput.clear();
        emailInput.clear();
        firstNameInput.clear();
        lastNameInput.clear();
        pitInput.clear();
    }

    private void clearChangeFields() {
        changeUserName.clear();
        changeEmail.clear();
        changeFirstName.clear();
        changeLastName.clear();
        changePit.clear();
    }

    private static void write(ObservableList<UserAccount> users) {
        try {
            FileOutputStream fos = new FileOutputStream("src/sample/map.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (UserAccount ignored : users) {
                oos.writeObject(new ArrayList<>(users));
            }

            fos.close();
            oos.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void deleteButtonClicked() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ПОПЕРЕДЖЕННЯ");
        alert.setHeaderText(null);
        alert.setContentText("Ви дійсно бажаєте видалити користувача?");

        ObservableList<UserAccount> userSelected, allUsers;

        allUsers = table.getItems();
        userSelected = table.getSelectionModel().getSelectedItems();

        Optional<ButtonType> result = alert.showAndWait();


        if (result.isPresent() && result.get() == ButtonType.OK) {
            userSelected.forEach(allUsers::remove);
            clearChangeFields();
        } else
            System.out.println("nope");

    }

    private void saveButtonClicked() {
        ObservableList<UserAccount> allUsers;
        allUsers = table.getItems();

        write(allUsers);
    }

}

