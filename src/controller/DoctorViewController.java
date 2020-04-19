package controller;

import javafx.fxml.Initializable;
import model.Doctor;

import java.net.URL;
import java.util.ResourceBundle;

public class DoctorViewController implements Initializable {
    Doctor model;

    public DoctorViewController(Doctor model) {
        this.model = model;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
