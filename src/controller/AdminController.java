package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.util.Callback;
import model.CommonMethods;
import model.Patient;
import model.User;
import model.UserSingleton;


import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {


    public ComboBox filterPatient;
    public TreeTableColumn doctorSSNtree;
    public TreeTableColumn doctorNameTree;
    public TreeTableColumn doctorPhoneTree;
    public TreeTableColumn doctorEmailTree;
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
    public TableColumn <User, String> patientSSNtable;
    public TableColumn<User, String> patientPhoneTable;
    public TableColumn<User, String> patientEmailTable;
    public TableColumn<User, String> patientLastNameTable;
    public TableColumn<User, String> patientFirstNameTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User currentUser = UserSingleton.getOurInstance().getUser();
        CommonMethods methods = new CommonMethods();

        patientSSNtable.setCellValueFactory(new PropertyValueFactory<>("Ssn"));
        patientFirstNameTable.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        patientLastNameTable.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        patientPhoneTable.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        patientEmailTable.setCellValueFactory(new PropertyValueFactory<>("Email"));

        ObservableList<User> listOfPatients = FXCollections.observableArrayList(methods.getPatientList());


            patientTableView.setItems(listOfPatients);
        }

        }
