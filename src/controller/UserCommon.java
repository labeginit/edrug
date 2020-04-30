package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;
import model.UserSingleton;

import java.io.IOException;

public class UserCommon {
    private User currentUser;
    @FXML
    public void onLogOutButtonPressed(ActionEvent event) throws IOException {
       // Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/loginView.fxml"));
        switchScene(event, "/view/loginView.fxml");
        currentUser = null;
        UserSingleton.getOurInstance().setUser(currentUser);
    }

    public void switchScene(ActionEvent event, String path) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource(path));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

}
