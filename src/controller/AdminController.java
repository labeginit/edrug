package controller;

import javafx.fxml.Initializable;
import model.Admin;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    Admin model;

    public AdminController(Admin model) {
        this.model = model;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
