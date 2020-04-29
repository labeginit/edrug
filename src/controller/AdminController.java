package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;

import model.CommonMethods;

import model.User;
import model.UserSingleton;


import java.net.URL;

import java.util.ResourceBundle;

public class AdminController implements Initializable {


    public ComboBox filterPatient;
    public ComboBox doctorFilter;
    public TreeTableColumn addSSN;
    public TreeTableColumn addName;
    public TreeTableColumn addPhone;
    public TreeTableColumn addEmail;
    public TreeTableColumn addRole;
    public ComboBox addFilter;
    public Button confirmAddButton;
    public TextField SSNnewTextField;
    public TextField nameNewTextField;
    public TextField phoneNewTextField;
    public TextField emailNewTextField;
    public TextField roleNewTextField;
    public Button cancel_button;
    public Button save_button;
    public TextField SSNtext;
    public TextField birth_text;
    public TextField firstName_text;
    public TextField lastName_text;
    public TextField zip_text;
    public TextField address_text;
    public TextField phone_text;
    public TextField email_text;
    public TextField pass1_text;
    public TextField pass2_text;
    public TableView<User> patientTableView;
    public TableColumn<User, String> patientSSNtable;
    public TableColumn<User, String> patientPhoneTable;
    public TableColumn<User, String> patientEmailTable;
    public TableColumn<User, String> patientLastNameTable;
    public TableColumn<User, String> patientFirstNameTable;
    public TableView <User>doctorTable;
    public TableColumn <User, String> doctorSSNtable;
    public TableColumn <User, String> doctorLastNameTable;
    public TableColumn <User, String> doctorFirstNameTable;
    public TableColumn <User, String> doctorPhoneTable;
    public TableColumn <User, String> doctorEmailTable;

    CommonMethods methods = new CommonMethods();
    User currentUser = UserSingleton.getOurInstance().getUser();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillPatientTable();
        fillDoctorTable();

    }

    public void fillPatientTable() {

        patientSSNtable.setCellValueFactory(new PropertyValueFactory<>("Ssn"));
        patientFirstNameTable.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        patientLastNameTable.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        patientPhoneTable.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        patientEmailTable.setCellValueFactory(new PropertyValueFactory<>("Email"));

        ObservableList<User> listOfPatients = FXCollections.observableArrayList(methods.getPatientList());

        patientTableView.setItems(listOfPatients);

    }
    public void fillDoctorTable() {
        doctorSSNtable.setCellValueFactory(new PropertyValueFactory<>("Ssn"));
        doctorFirstNameTable.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        doctorLastNameTable.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        doctorPhoneTable.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        doctorEmailTable.setCellValueFactory(new PropertyValueFactory<>("Email"));

        ObservableList<User> listOfDoctors = FXCollections.observableArrayList(methods.getDoctorList());

        doctorTable.setItems(listOfDoctors);
    }

}
