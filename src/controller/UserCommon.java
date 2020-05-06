package controller;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Medicine;
import model.User;
import model.UserSingleton;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;



import java.io.IOException;

public class UserCommon {
    private User currentUser;
    @FXML
    public void onLogOutButtonPressed(ActionEvent event) throws IOException {
        try {
            // Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/loginView.fxml"));
            switchScene(event, "/view/loginView.fxml");
            currentUser = null;
            UserSingleton.getOurInstance().setUser(currentUser);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void switchScene(ActionEvent event, String path) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource(path));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    public void search(FilteredList<Medicine> filteredData, TextField field, TableView<Medicine> tableView){
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(medicine -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (medicine.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (medicine.getSearchTerms().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(medicine.getArticleNo()).contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Medicine> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
    }
}
