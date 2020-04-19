package controller;

import javafx.fxml.Initializable;
import model.Admin;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminViewController implements Initializable {
    Admin model;

    public AdminViewController(Admin model) {
        this.model = model;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
