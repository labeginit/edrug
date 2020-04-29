package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;

import model.Admin;
import model.CommonMethods;

import model.User;
import model.UserSingleton;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class AdminController implements Initializable {


    public ComboBox filterPatient;
    public ComboBox doctorFilter;
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
    public TableView<User> doctorTable;
    public TableColumn<User, String> doctorSSNtable;
    public TableColumn<User, String> doctorLastNameTable;
    public TableColumn<User, String> doctorFirstNameTable;
    public TableColumn<User, String> doctorPhoneTable;
    public TableColumn<User, String> doctorEmailTable;
    public TableColumn<User, String> changeLastNameTable;
    public TableColumn<User, String> changeFirstNameTable;
    public TableColumn<User, String> changePhoneTable;
    public TableColumn<User, String> changeEmailTable;
    public TableColumn<User, String> changeSSNTable;
    public TableView<User> changeTable;
    public TableColumn<User, Integer> changeRoleTable;
    public Label SSNstar;
    public Label BDateStar;
    public Label firstNameStar;
    public Label lastNameStar;
    public Label zipStar;
    public Label addressStar;
    public Label phoneStar;
    public Label emailStar;
    public Label pass2star;
    public Label pass1star;

    CommonMethods methods = new CommonMethods();
    User currentUser = UserSingleton.getOurInstance().getUser();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SSNstar.setVisible(false);
        BDateStar.setVisible(false);
        firstNameStar.setVisible(false);
        lastNameStar.setVisible(false);
        zipStar.setVisible(false);
        addressStar.setVisible(false);
        phoneStar.setVisible(false);
        emailStar.setVisible(false);
        pass1star.setVisible(false);
        pass2star.setVisible(false);

        fillPatientTable();
        fillDoctorTable();
        fillEditTable();
        fillMe();

        cancel_button.setOnAction(actionEvent -> {
            fillMe();
        });
        save_button.setOnAction(actionEvent -> {
            if (isItOk()) {
            if (Validation.isName(firstName_text.getText(), firstNameStar) && Validation.isName(lastName_text.getText(), lastNameStar) &&
                    Validation.isZipcode(zip_text.getText(), zipStar) && Validation.isPhoneNumber(phone_text.getText(), phoneStar)
                    && Validation.isEmail(email_text.getText(), emailStar)) {
                try {
                    currentUser.setSsn(SSNtext.getText());
                    currentUser.setLastName(lastName_text.getText());
                    currentUser.setBDate(Date.valueOf(birth_text.getText()));
                    currentUser.setZipCode(zip_text.getText());
                    currentUser.setAddress(address_text.getText());
                    currentUser.setPhoneNumber(phone_text.getText());
                    currentUser.setEmail(email_text.getText());
                    currentUser.setPassword(pass1_text.getText());
                    methods.updateAdmin((Admin) currentUser);

                    pass1_text.setText("******");
                    pass2_text.setText("******");

                } catch (Exception e ) {
                    e.printStackTrace();
                }
            }
            }});

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
        doctorPhoneTable.setEditable(true);

    }

    public void fillEditTable() {
        changeSSNTable.setCellValueFactory(new PropertyValueFactory<>("Ssn"));
        changeFirstNameTable.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        changeLastNameTable.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        changePhoneTable.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        changeEmailTable.setCellValueFactory(new PropertyValueFactory<>("Email"));
        changeRoleTable.setCellValueFactory(new PropertyValueFactory<>("userType"));


        ObservableList<User> listOfAll = FXCollections.observableArrayList(methods.getDoctorList());
        listOfAll.addAll(FXCollections.observableArrayList(methods.getPatientList()));

        changeTable.setItems(listOfAll);

    }

    public void fillMe() {
        SSNtext.setText(currentUser.getSsn());
        birth_text.setText(currentUser.getBDate().toString());
        firstName_text.setText(currentUser.getFirstName());
        lastName_text.setText(currentUser.getLastName());
        zip_text.setText(currentUser.getZipCode());
        address_text.setText(currentUser.getAddress());
        phone_text.setText(currentUser.getPhoneNumber());
        email_text.setText(currentUser.getEmail());
        pass1_text.setText("******");
        pass2_text.setText("******");

    }

    private boolean isItOk() {
        if (firstName_text.getText().isEmpty() || lastName_text.getText().isEmpty() || birth_text.getText().isEmpty()
                || zip_text.getText().isEmpty() || address_text.getText().isEmpty() || email_text.getText().isEmpty() || phone_text.getText().isEmpty()) {
            if (firstName_text.getText().isEmpty()) {
                firstNameStar.setVisible(true);
            }
            if (lastName_text.getText().isEmpty()) {
                lastNameStar.setVisible(true);
            }
            if (birth_text.getText().isEmpty()) {
                BDateStar.setVisible(true);
            }
            if (zip_text.getText().isEmpty()) {
                zipStar.setVisible(true);
            }
            if (address_text.getText().isEmpty()) {
                addressStar.setVisible(true);
            }
            if (email_text.getText().isEmpty()) {
                emailStar.setVisible(true);
            }
            if (phone_text.getText().isEmpty()) {
                phoneStar.setVisible(true);
            }
            if (pass1_text.getText().isEmpty()) {
                pass1star.setVisible(true);
            }
            if (pass2_text.getText().isEmpty()) {
                pass2star.setVisible(true);
            }
            Validation.alertPopup("Please enter your information into all fields", "Empty Fields", "Contains empty fields");
        return false;
        } else if (!pass1_text.getText().equals(pass2_text.getText())) {
            pass1star.setVisible(true);
            pass2star.setVisible(true);
            Validation.alertPopup("Password does not match", "Password Mismatch", "Password doesn't match");
return false;
        }
        else if (!Validation.isPassword(pass1_text.getText(), pass1star)) {
        return false;
        }
        else {
            return true;
        }
    }
}
