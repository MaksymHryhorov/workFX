package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.UserAccount;

public class TableSearch {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField find;

    @FXML
    private TableColumn<UserAccount, String> emailCol;

    @FXML
    private TableColumn<UserAccount, String> firstNameCol;

    @FXML
    private TableColumn<UserAccount, String> lastNameCol;

    @FXML
    private TableColumn<UserAccount, Integer> pit;

    @FXML
    private TableView<UserAccount> table;

    @FXML
    private TableColumn<UserAccount, String> userNameCol;

    @FXML
    private void initialize() throws IOException, ClassNotFoundException {
        TableController tableController = new TableController();

        userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        pit.setCellValueFactory(new PropertyValueFactory<>("pit"));


        FilteredList<UserAccount> filteredData = new FilteredList<>(tableController.list, p -> true);
        find.setOnAction(e -> find.textProperty().addListener((observableValue, oldValue, newValue)
                -> filteredData.setPredicate(user -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (user.getUserName().contains(newValue)) {
                return true;
            } else if (user.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (user.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (user.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (String.valueOf(user.getPit()).toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }
            return false;
        })));

        SortedList<UserAccount> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        table.setStyle("-fx-font: normal 14px 'cursive' ");
        table.setItems(sortedData);

    }

}
