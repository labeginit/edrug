package controller;

import javafx.fxml.Initializable;
import model.Patient;

import java.net.URL;
import java.util.ResourceBundle;

public class PatientViewController implements Initializable {
    Patient model;

    public PatientViewController(Patient model) {
        this.model = model;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
