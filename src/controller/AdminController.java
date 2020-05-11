package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.*;
import model.dBConnection.CommonMethods;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;

import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {


    @FXML
    public TextField patientSearchTextField;
    public TextField doctorSearchTextField;
    public TextField editSearchTextField;
    public Button confirmAddButton;
    public Button cancel_button;
    public Button save_button;
    public Button logOutMy_button;
    public TextField SSNtext;
    public TextField firstName_text;
    public TextField lastName_text;
    public TextField zip_text;
    public TextField address_text;
    public TextField phone_text;
    public TextField email_text;
    public PasswordField pass1_text;
    public PasswordField pass2_text;
    public TableView<User> patientTableView;
    public TableColumn<User, String> patientSSNtable;
    public TableColumn<User, String> patientPhoneTable;
    public TableColumn<User, String> patientEmailTable;
    public TableColumn<User, String> patientLastNameTable;
    public TableColumn<User, String> patientFirstNameTable;
    public TableColumn<User, Boolean> patientActive;

    public TableView<User> doctorTable;
    public TableColumn<User, String> doctorSSNtable;
    public TableColumn<User, String> doctorLastNameTable;
    public TableColumn<User, String> doctorFirstNameTable;
    public TableColumn<User, String> doctorPhoneTable;
    public TableColumn<User, String> doctorEmailTable;
    public TableColumn<User, Boolean> doctorActive;
    public TableColumn<User, String> changeLastNameTable;
    public TableColumn<User, String> changeFirstNameTable;
    public TableColumn<User, String> changePhoneTable;
    public TableColumn<User, String> changeEmailTable;
    public TableColumn<User, String> changeSSNTable;
    public TableColumn<User, CheckBox> isActiveUser;
    public TableView<User> changeTable;
    public TableColumn<User, Integer> changeRoleTable;
    public Label BDateStar;
    public Label firstNameStar;
    public Label lastNameStar;
    public Label zipStar;
    public Label addressStar;
    public Label phoneStar;
    public Label emailStar;
    public Label pass2star;
    public Label pass1star;
    public Label passwordCheckLabel;
    public Label passwordCheckAddLabel;
    public Button save_buttonAdd;
    public Button cancel_buttonAdd;
    public Button logOutPat_button;
    public Button logOutDoc_button;
    public Button logOutAdd_button;
    public Button logOutEdit_button;
    public Button logOutMed_button;
    public TextField SSNtextAdd;
    public TextField firstName_textAdd;
    public DatePicker datePicker;
    public TextField lastName_textAdd;
    public TextField zip_textAdd;
    public TextField address_textAdd;
    public TextField phone_textAdd;
    public TextField email_textAdd;
    public PasswordField pass1_textAdd;
    public PasswordField pass2_textAdd;
    public Label SSNstarAdd;
    public Label BDateStarAdd;
    public Label firstNameStarAdd;
    public Label lastNameStarAdd;
    public Label zipStarAdd;
    public Label addressStarAdd;
    public Label phoneStarAdd;
    public Label emailStarAdd;
    public Label pass2starAdd;
    public Label pass1starAdd;
    public TextField roleTextAdd;
    public DatePicker datePickerAdd;
    public Label roleStarAdd;
    public TableView<Medicine> storeView;
    public TableColumn<Medicine, Integer> storeArticle;
    public TableColumn<Medicine, String> storeName;
    public TableColumn<Medicine, String> storeSize;
    public TableColumn<Medicine, Double> storePrice;
    public TableColumn<Medicine, Integer> storeAvailability;
    public TableColumn<Medicine, String> storeDescription;
    public TableColumn<Medicine, String> storeProducer;
    public TableColumn<Medicine, CheckBox> isActiveMed;
    public TextField storeSearchTextField;
    public ComboBox storeFilterCombo;
    public TableView<User> adminTable;
    public TableColumn<User, String> adminSSNtable;
    public TableColumn<User, String> adminLastNameTable;
    public TableColumn<User, String> adminFirstNameTable;
    public TableColumn<User, String> adminPhoneTable;
    public TableColumn<User, String> adminEmailTable;
    public TableColumn<User, CheckBox> adminActiveTable;
    public Button logOutAdminButton;
    public TextField adminSearchTextField;
    public TextField articleM;
    public ChoiceBox<ProdGroup> prodGroupM;
    public TextField nameM;
    public TextField packageM;
    public TextField producerM;
    public TextField descriptM;
    public TextField priceM;
    public TextField qtyM;
    public TextField searchTermsM;
    public ChoiceBox<String> onPrescrM;
    public Button logOutM_button;
    public Button save_buttonM;
    public Button cancel_buttonM;
    public Label articleStar;
    public Label prodGroupStar;
    public Label nameMStar;
    public Label packageStar;
    public Label producerStar;
    public Label descriptStar;
    public Label priceStar;
    public Label quantityStar;
    public Label searchStar;
    public Label typeStar;


    private final String withP = "Needs a Prescription";
    private final String withoutP = "No Prescription required";

    public CommonMethods methods = new CommonMethods();
    private UserCommon userCommon = new UserCommon();
    public User currentUser = UserSingleton.getOurInstance().getUser();
    private List<ProdGroup> groups = methods.getProductGroupList();
    private ObservableList<ProdGroup> groupsObserve = FXCollections.observableArrayList(groups);
    private ObservableList<String> typeObserve = FXCollections.observableArrayList(withP, withoutP);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setVisible(false);
        setVisibleAdd(false);
        setVisibleAddM(false);
        fillStore();
        fillPatientTable();
        fillDoctorTable();
        fillAdminTable();
        fillEditTable();
        fillMe();
        makeEditable();
        prodGroupM.setItems(groupsObserve);
        onPrescrM.setItems(typeObserve);

        cancel_button.setOnAction(actionEvent -> {
            fillMe();
            setVisible(false);
        });
        cancel_buttonAdd.setOnAction(actionEvent -> {
            setVisibleAdd(false);
            clearFieldsAdd();
        });

        cancel_buttonM.setOnAction(actionEvent -> {
            setVisibleAddM(false);
            clearFieldsAddM();
        });

        logOutMy_button.setOnAction(event -> {
            try {
                userCommon.onLogOutButtonPressed(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        logOutAdd_button.setOnAction(event -> {
            try {
                userCommon.onLogOutButtonPressed(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        logOutDoc_button.setOnAction(event -> {
            try {
                userCommon.onLogOutButtonPressed(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        logOutEdit_button.setOnAction(event -> {
            try {
                userCommon.onLogOutButtonPressed(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        logOutPat_button.setOnAction(event -> {
            try {
                userCommon.onLogOutButtonPressed(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        logOutMed_button.setOnAction(event -> {
            try {
                userCommon.onLogOutButtonPressed(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        logOutAdminButton.setOnAction(event -> {
            try {
                userCommon.onLogOutButtonPressed(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        logOutM_button.setOnAction(event -> {
            try {
                userCommon.onLogOutButtonPressed(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        save_button.setOnAction(actionEvent -> {
            if (isItOk()) {
                if (Validation.isName(firstName_text.getText(), firstNameStar) && Validation.isName(lastName_text.getText(), lastNameStar) &&
                        Validation.isZipcode(zip_text.getText(), zipStar) && Validation.isPhoneNumber(phone_text.getText(), phoneStar)
                        && Validation.isEmail(email_text.getText(), emailStar)) {
                    try {
                        currentUser.setSsn(SSNtext.getText());
                        currentUser.setLastName(lastName_text.getText());
                        currentUser.setBDate(Date.valueOf(datePicker.getValue().plusDays(1)));
                        currentUser.setZipCode(zip_text.getText());
                        currentUser.setAddress(address_text.getText());
                        currentUser.setPhoneNumber(phone_text.getText());
                        currentUser.setEmail(email_text.getText());
                        currentUser.setPassword(pass1_text.getText());
                        methods.updateAdmin((Admin) currentUser);
                        if (!pass1_text.getText().isEmpty() && !pass2_text.getText().isEmpty()) {
                            methods.updatePassword(currentUser);
                            pass1_text.clear();
                            pass2_text.clear();
                        }
                        setVisible(false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        save_buttonAdd.setOnAction(actionEvent -> {
            if (isItOkAdd()) {
                if (Validation.isSSN(SSNtextAdd.getText(), SSNstarAdd) && Validation.isName(firstName_textAdd.getText(), firstNameStarAdd) && Validation.isName(lastName_textAdd.getText(), lastNameStarAdd) &&
                        Validation.isZipcode(zip_textAdd.getText(), zipStarAdd) && Validation.isPhoneNumber(phone_textAdd.getText(), phoneStarAdd)
                        && Validation.isEmail(email_textAdd.getText(), emailStarAdd) && Validation.isRole(roleTextAdd.getText())) {
                    try {
                        if (methods.getUser(SSNtextAdd.getText()) == null) {
                            if (Integer.parseInt(roleTextAdd.getText()) == 1) {
                                Patient patient = new Patient(SSNtextAdd.getText(), firstName_textAdd.getText(), lastName_textAdd.getText(),
                                        Date.valueOf(datePickerAdd.getValue().plusDays(1)), zip_textAdd.getText(), address_textAdd.getText(),
                                        email_textAdd.getText(), phone_textAdd.getText(), pass1_textAdd.getText(), true);
                                methods.addPatient(patient);
                            } else if (Integer.parseInt(roleTextAdd.getText()) == 2) {
                                Doctor doctor = new Doctor(SSNtextAdd.getText(), firstName_textAdd.getText(), lastName_textAdd.getText(),
                                        Date.valueOf(datePickerAdd.getValue().plusDays(1)), zip_textAdd.getText(), address_textAdd.getText(),
                                        email_textAdd.getText(), phone_textAdd.getText(), pass1_textAdd.getText(), true);
                                methods.addDoctor(doctor);

                            }
                            clearFieldsAdd();
                            fillPatientTable();
                            fillDoctorTable();
                            fillEditTable();
                            setVisibleAdd(false);
                        } else {
                            setVisibleAdd(false);
                            SSNstarAdd.setVisible(true);
                            Validation.alertPopup("User with SSN = " + SSNtextAdd.getText() + " already exists. It might be inactive.", "User exists", "User already exists");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        save_buttonM.setOnAction(actionEvent -> {
            Medicine newMed = null;
                    try {
                        if (methods.getMedicine(Integer.parseInt(articleM.getText())) == null) {
                            boolean prescription;
                            if (onPrescrM.getValue().equalsIgnoreCase(withP)){
                                prescription = true;
                            } else prescription = false;
                            if (prescription) {
                                newMed = new OnPrescription(Integer.parseInt(articleM.getText()), prodGroupM.getValue().getId(), nameM.getText(), producerM.getText(), packageM.getText(), descriptM.getText(), Integer.parseInt(qtyM.getText()),
                                        Double.parseDouble(priceM.getText()), searchTermsM.getText(), true);
                            } else {
                                newMed = new PrescriptionFree(Integer.parseInt(articleM.getText()), prodGroupM.getValue().getId(), nameM.getText(), producerM.getText(), packageM.getText(), descriptM.getText(), Integer.parseInt(qtyM.getText()),
                                        Double.parseDouble(priceM.getText()), searchTermsM.getText(), true);
                            }
                            System.out.println(newMed);
                            methods.addMedicine(newMed);
                            clearFieldsAddM();
                            fillStore();
                            setVisibleAddM(false);
                        } else {
                            setVisibleAddM(false);
                            articleStar.setVisible(true);
                            Validation.alertPopup("Medicine with article = " + articleM.getText() + " already exists. It might be inactive.", "Item exists", "Medicine item already exists");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
        });
    }

    private void setVisible(boolean on) {
        BDateStar.setVisible(on);
        firstNameStar.setVisible(on);
        lastNameStar.setVisible(on);
        zipStar.setVisible(on);
        addressStar.setVisible(on);
        phoneStar.setVisible(on);
        emailStar.setVisible(on);
        pass1star.setVisible(on);
        pass2star.setVisible(on);
        passwordCheckLabel.setVisible(on);
    }

    private void setVisibleAdd(boolean on) {
        SSNstarAdd.setVisible(on);
        BDateStarAdd.setVisible(on);
        firstNameStarAdd.setVisible(on);
        lastNameStarAdd.setVisible(on);
        zipStarAdd.setVisible(on);
        addressStarAdd.setVisible(on);
        phoneStarAdd.setVisible(on);
        emailStarAdd.setVisible(on);
        pass1starAdd.setVisible(on);
        pass2starAdd.setVisible(on);
        roleStarAdd.setVisible(on);
        passwordCheckAddLabel.setVisible(on);
    }

    private void setVisibleAddM(boolean on){
        articleStar.setVisible(on);
        prodGroupStar.setVisible(on);
        nameMStar.setVisible(on);
        packageStar.setVisible(on);
        producerStar.setVisible(on);
        descriptStar.setVisible(on);
        priceStar.setVisible(on);
        quantityStar.setVisible(on);
        searchStar.setVisible(on);
        typeStar.setVisible(on);
    }

    private void clearFieldsAdd() {
        SSNtextAdd.clear();
        firstName_textAdd.clear();
        lastName_textAdd.clear();
        phone_textAdd.clear();
        zip_textAdd.clear();
        address_textAdd.clear();
        email_textAdd.clear();
        pass1_textAdd.clear();
        pass2_textAdd.clear();
        roleTextAdd.clear();
        datePickerAdd.setValue(LocalDate.now());
    }

    private void clearFieldsAddM(){
        articleM.clear();
        //prodGroupM;
        nameM.clear();
        packageM.clear();
        producerM.clear();
        descriptM.clear();
        priceM.clear();
        qtyM.clear();
        searchTermsM.clear();
        //onPrescrM;
    }

    public void fillPatientTable() {

        patientSSNtable.setCellValueFactory(new PropertyValueFactory<>("Ssn"));
        patientFirstNameTable.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        patientLastNameTable.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        patientPhoneTable.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        patientEmailTable.setCellValueFactory(new PropertyValueFactory<>("Email"));
        patientActive.setCellValueFactory(new PropertyValueFactory<>("active"));

        ObservableList<User> listOfPatients = FXCollections.observableArrayList(methods.getPatientList());

        FilteredList<User> filteredData = new FilteredList<>(listOfPatients, p -> true);
        patientTableView.setItems(userCommon.userFilter(filteredData, patientSearchTextField, patientTableView));

    }

    public void fillDoctorTable() {
        doctorSSNtable.setCellValueFactory(new PropertyValueFactory<>("Ssn"));
        doctorFirstNameTable.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        doctorLastNameTable.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        doctorPhoneTable.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        doctorEmailTable.setCellValueFactory(new PropertyValueFactory<>("Email"));
        doctorActive.setCellValueFactory(new PropertyValueFactory<>("active"));

        ObservableList<User> listOfDoctors = FXCollections.observableArrayList(methods.getDoctorList());

        FilteredList<User> filteredData = new FilteredList<>(listOfDoctors, p -> true);
        doctorTable.setItems(userCommon.userFilter(filteredData, doctorSearchTextField, doctorTable));
    }

    public void fillEditTable() {
        changeSSNTable.setCellValueFactory(new PropertyValueFactory<>("Ssn"));
        changeFirstNameTable.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        changeLastNameTable.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        changePhoneTable.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        changeEmailTable.setCellValueFactory(new PropertyValueFactory<>("Email"));
        changeRoleTable.setCellValueFactory(new PropertyValueFactory<>("userType"));
        isActiveUser.setCellValueFactory(new PropertyValueFactory<>("checkBox"));

        ObservableList<User> listOfAll = FXCollections.observableArrayList(methods.getDoctorList());
        listOfAll.addAll(FXCollections.observableArrayList(methods.getPatientList()));

        FilteredList<User> filteredData = new FilteredList<>(listOfAll, p -> true);
        changeTable.setItems(userCommon.userFilter(filteredData, editSearchTextField, changeTable));
        for (int i = 0; i < changeTable.getItems().size(); i++) {
            if (changeTable.getItems().get(i).getActive()) {
                isActiveUser.getCellData(i).setSelected(true);
            } else isActiveUser.getCellData(i).setSelected(false);
        }
    }
    public void fillAdminTable() {
        adminSSNtable.setCellValueFactory(new PropertyValueFactory<>("Ssn"));
        adminFirstNameTable.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        adminLastNameTable.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        adminPhoneTable.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        adminEmailTable.setCellValueFactory(new PropertyValueFactory<>("Email"));
        adminActiveTable.setCellValueFactory(new PropertyValueFactory<>("active"));

        ObservableList<User> listOfAdmins = FXCollections.observableArrayList(methods.getAdminList());

        FilteredList<User> filteredData = new FilteredList<>(listOfAdmins, p -> true);
        adminTable.setItems(userCommon.userFilter(filteredData, adminSearchTextField, adminTable));
    }


                public void fillStore() {
                    storeArticle.setCellValueFactory(new PropertyValueFactory<>("articleNo"));
                    storeName.setCellValueFactory(new PropertyValueFactory<>("name"));
                    storeAvailability.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                    storeDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
                    storePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
                    storeProducer.setCellValueFactory(new PropertyValueFactory<>("producer"));
                    storeSize.setCellValueFactory(new PropertyValueFactory<>("packageSize"));
                    isActiveMed.setCellValueFactory(new PropertyValueFactory<>("checkBox"));

                    ObservableList<Medicine> listOfAllMed = FXCollections.observableArrayList(methods.getMedicineList());
                    FilteredList<Medicine> filteredData = new FilteredList<>(listOfAllMed, p -> true);
                    storeView.setItems(userCommon.medFilter(filteredData, editSearchTextField, storeView));

                    for (int i = 0; i < filteredData.size(); i++) {
                        if (filteredData.get(i).getActive()) {
                            isActiveMed.getCellData(i).setSelected(true);
                        } else isActiveMed.getCellData(i).setSelected(false);
                    }

                }

                public void fillMe() {
                    SSNtext.setText(currentUser.getSsn());
                    datePicker.setValue(currentUser.getBDate().toLocalDate());
                    firstName_text.setText(currentUser.getFirstName());
                    lastName_text.setText(currentUser.getLastName());
                    zip_text.setText(currentUser.getZipCode());
                    address_text.setText(currentUser.getAddress());
                    phone_text.setText(currentUser.getPhoneNumber());
                    email_text.setText(currentUser.getEmail());
                }


                private boolean isItOk() {
                    if (firstName_text.getText().isEmpty() || lastName_text.getText().isEmpty() || datePicker.getValue() == null
                            || zip_text.getText().isEmpty() || address_text.getText().isEmpty() || email_text.getText().isEmpty() || phone_text.getText().isEmpty()) {
                        if (firstName_text.getText().isEmpty()) {
                            firstNameStar.setVisible(true);
                        }
                        if (lastName_text.getText().isEmpty()) {
                            lastNameStar.setVisible(true);
                        }
                        if (datePicker.getValue() == null) {
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
                        Validation.alertPopup("Please enter your information into all fields", "Empty Fields", "Contains empty fields");
                        return false;
                    } else if (!pass1_text.getText().isEmpty() || !pass2_text.getText().isEmpty()) {
                        if (!pass1_text.getText().equals(pass2_text.getText())) {
                            pass1star.setVisible(true);
                            pass2star.setVisible(true);
                            passwordCheckLabel.setVisible(true);
                            Validation.alertPopup("Password does not match", "Password Mismatch", "Password doesn't match");
                            return false;
                        } else return Validation.isPassword(pass1_text.getText(), pass1star);
                    } else {
                        return true;
                    }
                }

                private boolean isItOkAdd() {
                    if (SSNtextAdd.getText().isEmpty() || firstName_textAdd.getText().isEmpty() || lastName_textAdd.getText().isEmpty() || datePickerAdd.getValue() == null
                            || zip_textAdd.getText().isEmpty() || address_textAdd.getText().isEmpty() || email_textAdd.getText().isEmpty() || phone_textAdd.getText().isEmpty()
                            || roleTextAdd.getText().isEmpty()) {

                        if (SSNtextAdd.getText().isEmpty()) {
                            SSNstarAdd.setVisible(true);
                        }
                        if (firstName_textAdd.getText().isEmpty()) {
                            firstNameStarAdd.setVisible(true);
                        }
                        if (lastName_textAdd.getText().isEmpty()) {
                            lastNameStarAdd.setVisible(true);
                        }
                        if (datePickerAdd.getValue() == null) {
                            BDateStarAdd.setVisible(true);
                        }
                        if (zip_textAdd.getText().isEmpty()) {
                            zipStarAdd.setVisible(true);
                        }
                        if (address_textAdd.getText().isEmpty()) {
                            addressStarAdd.setVisible(true);
                        }
                        if (email_textAdd.getText().isEmpty()) {
                            emailStarAdd.setVisible(true);
                        }
                        if (phone_textAdd.getText().isEmpty()) {
                            phoneStarAdd.setVisible(true);
                        }
                        if (pass1_textAdd.getText().isEmpty()) {
                            pass1starAdd.setVisible(true);
                        }
                        if (pass2_textAdd.getText().isEmpty()) {
                            pass2starAdd.setVisible(true);
                        }
                        if (roleTextAdd.getText().isEmpty()) {
                            roleStarAdd.setVisible(true);
                        }
                        Validation.alertPopup("Please enter your information into all fields", "Empty Fields", "Contains empty fields");
                        return false;
                    } else if (!pass1_textAdd.getText().equals(pass2_textAdd.getText())) {
                        pass1starAdd.setVisible(true);
                        pass2starAdd.setVisible(true);
                        Validation.alertPopup("Password does not match", "Password Mismatch", "Password doesn't match");
                        return false;
                    } else if (!Validation.isPassword(pass1_textAdd.getText(), pass1starAdd)) {
                        return false;
                    } else {
                        return true;
                    }
                }

                public void makeEditable() {

                    changeFirstNameTable.setCellFactory(TextFieldTableCell.forTableColumn());
                    changeFirstNameTable.setOnEditCommit(
                            new EventHandler<TableColumn.CellEditEvent<User, String>>() {

                                @Override
                                public void handle(TableColumn.CellEditEvent<User, String> t) {
                                    if (Validation.isName(t.getNewValue(), firstNameStar)) {
                                        firstNameStar.setVisible(false);
                                        ((User) t.getTableView().getItems().get(
                                                t.getTablePosition().getRow())
                                        ).setFirstName(t.getNewValue());
                                        methods.updateUser(t.getTableView().getItems().get(t.getTablePosition().getRow()));
                                    }
                                    fillDoctorTable();
                                    fillPatientTable();
                                }
                            }
                    );
                    changeLastNameTable.setCellFactory(TextFieldTableCell.forTableColumn());
                    changeLastNameTable.setOnEditCommit(
                            new EventHandler<TableColumn.CellEditEvent<User, String>>() {

                                @Override
                                public void handle(TableColumn.CellEditEvent<User, String> t) {
                                    if (Validation.isName(t.getNewValue(), lastNameStar)) {
                                        lastNameStar.setVisible(false);
                                        ((User) t.getTableView().getItems().get(
                                                t.getTablePosition().getRow())
                                        ).setLastName(t.getNewValue());
                                        methods.updateUser(t.getTableView().getItems().get(t.getTablePosition().getRow()));
                                    }
                                    fillDoctorTable();
                                    fillPatientTable();
                                }
                            }
                    );
                    changeEmailTable.setCellFactory(TextFieldTableCell.forTableColumn());
                    changeEmailTable.setOnEditCommit(
                            new EventHandler<TableColumn.CellEditEvent<User, String>>() {

                                @Override
                                public void handle(TableColumn.CellEditEvent<User, String> t) {
                                    if (Validation.isEmail(t.getNewValue(), emailStar)) {
                                        emailStar.setVisible(false);
                                        ((User) t.getTableView().getItems().get(
                                                t.getTablePosition().getRow())
                                        ).setEmail(t.getNewValue());
                                        methods.updateUser(t.getTableView().getItems().get(t.getTablePosition().getRow()));
                                    }
                                    fillDoctorTable();
                                    fillPatientTable();
                                }
                            }
                    );
                    changePhoneTable.setCellFactory(TextFieldTableCell.forTableColumn());
                    changePhoneTable.setOnEditCommit(
                            new EventHandler<TableColumn.CellEditEvent<User, String>>() {

                                @Override
                                public void handle(TableColumn.CellEditEvent<User, String> t) {
                                    if (Validation.isPhoneNumber(t.getNewValue(), phoneStar)) {
                                        phoneStar.setVisible(false);
                                        ((User) t.getTableView().getItems().get(
                                                t.getTablePosition().getRow())
                                        ).setPhoneNumber(t.getNewValue());
                                        methods.updateUser(t.getTableView().getItems().get(t.getTablePosition().getRow()));

                                    }
                                    fillDoctorTable();
                                    fillPatientTable();
                                }
                            });
                    storeSize.setCellFactory(TextFieldTableCell.forTableColumn());
                    storeSize.setOnEditCommit(
                            new EventHandler<TableColumn.CellEditEvent<Medicine, String>>() {

                                @Override
                                public void handle(TableColumn.CellEditEvent<Medicine, String> t) {
                                    ((Medicine) t.getTableView().getItems().get(
                                            t.getTablePosition().getRow())
                                    ).setPackageSize(t.getNewValue());
                                    methods.updateMedicine(t.getTableView().getItems().get(t.getTablePosition().getRow()));

                                    fillStore();
                                }
                            });
                    storeProducer.setCellFactory(TextFieldTableCell.forTableColumn());
                    storeProducer.setOnEditCommit(
                            new EventHandler<TableColumn.CellEditEvent<Medicine, String>>() {

                                @Override
                                public void handle(TableColumn.CellEditEvent<Medicine, String> t) {

                                    ((Medicine) t.getTableView().getItems().get(
                                            t.getTablePosition().getRow())
                                    ).setProducer(t.getNewValue());
                                    methods.updateMedicine(t.getTableView().getItems().get(t.getTablePosition().getRow()));

                                    fillStore();
                                }
                            });
                    storePrice.setCellFactory(TextFieldTableCell.<Medicine, Double>forTableColumn(new DoubleStringConverter()));
                    storePrice.setOnEditCommit(
                            new EventHandler<TableColumn.CellEditEvent<Medicine, Double>>() {

                                @Override
                                public void handle(TableColumn.CellEditEvent<Medicine, Double> t) {
                                    if (Validation.isPriceMedicine(t.getNewValue().toString())) {

                                        ((Medicine) t.getTableView().getItems().get(
                                                t.getTablePosition().getRow())
                                        ).setPrice(t.getNewValue());
                                        methods.updateMedicine(t.getTableView().getItems().get(t.getTablePosition().getRow()));

                                        fillStore();
                                    }
                                }
                            });
                    storeDescription.setCellFactory(TextFieldTableCell.forTableColumn());
                    storeDescription.setOnEditCommit(

                            new EventHandler<TableColumn.CellEditEvent<Medicine, String>>() {

                                @Override
                                public void handle(TableColumn.CellEditEvent<Medicine, String> t) {

                                    ((Medicine) t.getTableView().getItems().get(
                                            t.getTablePosition().getRow())
                                    ).setDescription(t.getNewValue());
                                    methods.updateMedicine(t.getTableView().getItems().get(t.getTablePosition().getRow()));

                                    fillStore();
                                }
                            });
                    storeName.setCellFactory(TextFieldTableCell.forTableColumn());
                    storeName.setOnEditCommit(
                            new EventHandler<TableColumn.CellEditEvent<Medicine, String>>() {

                                @Override
                                public void handle(TableColumn.CellEditEvent<Medicine, String> t) {


                                    ((Medicine) t.getTableView().getItems().get(
                                            t.getTablePosition().getRow())
                                    ).setName(t.getNewValue());
                                    methods.updateMedicine(t.getTableView().getItems().get(t.getTablePosition().getRow()));

                                    fillStore();
                                }
                            });

                    storeAvailability.setCellFactory(TextFieldTableCell.<Medicine, Integer>forTableColumn(new IntegerStringConverter()));
                    storeAvailability.setOnEditCommit(
                            new EventHandler<TableColumn.CellEditEvent<Medicine, Integer>>() {

                                @Override
                                public void handle(TableColumn.CellEditEvent<Medicine, Integer> t) {
                                    if (Validation.isQuantityMedicine(t.getNewValue().toString())) {

                                        ((Medicine) t.getTableView().getItems().get(
                                                t.getTablePosition().getRow())
                                        ).setQuantity(t.getNewValue());
                                        methods.updateMedicine(t.getTableView().getItems().get(t.getTablePosition().getRow()));

                                        fillStore();
                                    }
                                }
                            });
                    isActiveMed.setCellFactory(TextFieldTableCell.<Medicine, CheckBox>forTableColumn(new StringConverter<CheckBox>() {
                        @Override
                        public String toString(CheckBox checkBox) {
                            if (checkBox.isSelected()){
                                return "1";
                            } else {return "0";}
                        }

                        @Override
                        public CheckBox fromString(String s) {
                            CheckBox checkBox = new CheckBox();
                            if (s.equalsIgnoreCase("1")){
                              checkBox.setSelected(true);
                            } else checkBox.setSelected(false);
                            return checkBox;
                        }
                    }));
                    isActiveMed.setOnEditCommit(
                            new EventHandler<TableColumn.CellEditEvent<Medicine, CheckBox>>() {
                                @Override
                                public void handle(TableColumn.CellEditEvent<Medicine, CheckBox> t) {
                                        ((Medicine) t.getTableView().getItems().get(
                                                t.getTablePosition().getRow())
                                        ).setActive(t.getNewValue().isSelected());
                                        methods.updateMedicine(t.getTableView().getItems().get(t.getTablePosition().getRow()));

                                        fillStore();
                                }
                            });

                }

            }

