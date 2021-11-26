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
import methods.NumberTextField;
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

    private int numbers;
    Alerts alerts = new Alerts();

    @FXML
    void rowClicked(javafx.scene.input.MouseEvent event) {
        try {
            UserAccount user = table.getSelectionModel().getSelectedItem();
            changeUserName.setText(String.valueOf(user.getUserName()));
            changeEmail.setText(String.valueOf(user.getEmail()));
            changeFirstName.setText(String.valueOf(user.getFirstName()));
            changeLastName.setText(String.valueOf(user.getLastName()));
            changePit.setText(String.valueOf(user.getPit()));
        } catch (Exception e) {
            alerts.showAlert(Alert.AlertType.ERROR, "ОШИБКА", "Виберіть користувача в таблиці");
        }

    }


    @FXML
    void edit(ActionEvent event) {
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
                    table.setItems(currentTableData);
                    table.refresh();
                    break;
                }
                System.out.println("no");
            }
        } catch (Exception e) {
            alerts.showAlert(Alert.AlertType.ERROR, "ОШИБКА", "Будь ласка, виберіть користувача в таблиці");
        }
    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
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


        NumberTextField numberTextField = new NumberTextField();

        pit.setCellValueFactory(new PropertyValueFactory<>("pit"));
        pit.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));


        pit.setOnEditCommit(userAccountStringCellEditEvent -> {
            UserAccount userAccount = userAccountStringCellEditEvent.getRowValue();
            userAccount.setPit(userAccountStringCellEditEvent.getNewValue());
        });


        addButton.setOnAction(event -> {
            try {
                numbers = Integer.parseInt(pitInput.getText());

                if (userNameInput.getText().isEmpty() | emailInput.getText().isEmpty() |
                        firstNameInput.getText().isEmpty() | lastNameInput.getText().isEmpty() |
                        pitInput.getText().isEmpty()) {
                    alerts.showAlert(Alert.AlertType.ERROR, "ОШИБКА", "Будь ласка, заповніть усі поля");
                } else {
                    UserAccount userAccount = new UserAccount(userNameInput.getText(), emailInput.getText(),
                            firstNameInput.getText(), lastNameInput.getText(), Integer.parseInt(pitInput.getText()));
                    add(userAccount);
                }

            } catch (NumberFormatException e) {
                alerts.showAlert(Alert.AlertType.ERROR, "ОШИБКА", "Введіть лише числа в \"pit\"");
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


        userNameInput.setTooltip(new Tooltip("Enter user name"));
        emailInput.setTooltip(new Tooltip("Enter email"));
        firstNameInput.setTooltip(new Tooltip("Enter first name"));
        lastNameInput.setTooltip(new Tooltip("Enter last name"));
        pitInput.setTooltip(new Tooltip("Enter PIT"));

        OpenNewScene ons = new OpenNewScene();

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

        FileInputStream fis = new FileInputStream("src/sample/map.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        ArrayList<UserAccount> l = (ArrayList<UserAccount>) ois.readObject();

        fis.close();
        ois.close();

        ObservableList<UserAccount> list = FXCollections.observableArrayList(l);

        /*ObservableList<UserAccount> list = getUserList();*/

        try {
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        } catch (Exception e) {
            alerts.showAlert(Alert.AlertType.ERROR, "ОШИБКА", "Виберіть користувача в таблиці");
        }

        table.setStyle("-fx-font: normal 17px 'cursive' ");
        table.setItems(list);

    }

/*    private ObservableList<UserAccount> getUserList() {
        ObservableList<UserAccount> userAccounts = FXCollections.observableArrayList();
        userAccounts.add(new UserAccount("adam", "adam@gmail.com",
                "Adam", "Clued", 123456));
        userAccounts.add(new UserAccount("login", "bane@gmail.com",
                "Bane", "Brown", 134526));
        userAccounts.add(new UserAccount("cube", "circle@gmail.com",
                "Circle", "Adamson", 153424));

        write(userAccounts);
        return userAccounts;
    }*/

    private void add(UserAccount userAccount) {
        table.getItems().add(userAccount);
        clearFields();

    }

    // Clear column fields
    private void clearFields() {
        userNameInput.clear();
        emailInput.clear();
        firstNameInput.clear();
        lastNameInput.clear();
        pitInput.clear();
    }

    private static void write(ObservableList<UserAccount> users) {
        try {
            FileOutputStream fos = new FileOutputStream("src/sample/map.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            String newLine = "\n";

            for (UserAccount ignored : users) {
                newLine += users + "\n";
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
        } else
            System.out.println("nope");

    }

    private void saveButtonClicked() {
        ObservableList<UserAccount> allUsers;
        allUsers = table.getItems();

        write(allUsers);
    }


}

