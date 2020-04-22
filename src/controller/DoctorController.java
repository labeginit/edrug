package controller;

import javafx.fxml.Initializable;
import model.Doctor;

import java.net.URL;
import java.util.ResourceBundle;

public class DoctorController implements Initializable {
    Doctor model;

    public DoctorController(Doctor model) {
        this.model = model;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
