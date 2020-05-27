package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.*;
import model.dBConnection.CommonMethods;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {


    @FXML
    public TextField patientSearchTextField, doctorSearchTextField, editSearchTextField;
    public Button confirmAddButton, cancelButton, saveButton, logOutMyButton;
    public TextField SSNtext, firstNameText, lastNameText, zipText, cityText, addressText, phoneText, emailText;
    public PasswordField pass1_text;
    public PasswordField pass2_text;
    public TableView<User> patientTableView;
    public TableColumn<User, String> patientSSNTable, patientPhoneTable, patientEmailTable, patientLastNameTable, patientFirstNameTable;
    public TableColumn<User, Boolean> patientActive;
    public TableView<User> doctorTableView;
    public TableColumn<User, String> doctorSSNTable, doctorLastNameTable, doctorFirstNameTable, doctorPhoneTable, doctorEmailTable;
    public TableColumn<User, Boolean> doctorActive;
    public TableColumn<User, String> changeLastNameTable, changeFirstNameTable, changePhoneTable, changeEmailTable, changeSSNTable;
    public TableColumn<User, CheckBox> changeActive;
    public TableView<User> changeTable;
    public TableColumn<User, Integer> changeRoleTable;
    public Label BDateStar, firstNameStar, lastNameStar, zipStar, cityStar, addressStar, phoneStar, emailStar, pass2star, pass1star;
    public Label passwordCheckLabel, passwordCheckAddLabel;
    public Button saveButtonAdd, cancelButtonAdd, logOutPatButton, logOutDocButton, logOutAddButton, logOutEditButton, logOutMedButton;

    public TextField SSNtextAdd, firstNameTextAdd, lastNameTextAdd, zipTextAdd, cityTextAdd, addressTextAdd, phoneTextAdd, emailTextAdd;
    public DatePicker datePicker;
    public PasswordField pass1_textAdd;
    public PasswordField pass2_textAdd;
    public Label SSNstarAdd, BDateStarAdd, firstNameStarAdd, lastNameStarAdd, zipStarAdd, cityStarAdd, addressStarAdd, phoneStarAdd, emailStarAdd;
    public Label pass2starAdd, pass1starAdd, roleStarAdd;
    public TextField roleTextAdd;
    public DatePicker datePickerAdd;
    public TableView<Medicine> storeView;
    public TableColumn<Medicine, Integer> storeArticle;
    public TableColumn<Medicine, String> storeName, storeSize, storeDescription, storeProducer;
    public TableColumn<Medicine, Double> storePrice;
    public TableColumn<Medicine, Integer> storeAvailability;

    public TableColumn<Medicine, CheckBox> isActiveMed;
    public TextField storeSearchTextField;
    public TableView<User> adminTable;
    public TableColumn<User, String> adminSSNtable, adminLastNameTable, adminFirstNameTable, adminPhoneTable, adminEmailTable;
    public TableColumn<User, CheckBox> adminActiveTable;
    public Button logOutAdminButton;
    public TextField adminSearchTextField, articleMedicine, nameMedicine, packageMedicine, producerMedicine, descriptionMedicine, priceMedicine, quantityMedicine, searchTermsMedicine;
    public ChoiceBox<ProdGroup> prodGroupMedicine;

    public ChoiceBox<String> onPrescrMedicine;
    public Button logOutMedicineButton, saveButtonMedicine, cancelButtonMedicine;

    public Label articleStar, prodGroupStar, nameMedicineStar, packageStar, producerStar, descriptionStar, priceStar, quantityStar, searchStar, typeStar;
    public Label helpEditMedicine, helpEditUser;
    public TextArea helpMenuEditMedicine, helpMenuEditUser;


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
        userCommon.handleHelpMenus( helpMenuEditMedicine, helpEditMedicine, "Double click on the \ninformation you want to edit and then \nsave by pressing enter");
        userCommon.handleHelpMenus(helpMenuEditUser, helpEditUser, "Double click on the \ninformation you want to edit and then \nsave by pressing enter");
        articleMedicine.setText(String.valueOf(methods.getLastId(Medicine.class) + 1));

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
        prodGroupMedicine.setItems(groupsObserve);
        onPrescrMedicine.setItems(typeObserve);

        cancelButton.setOnAction(actionEvent -> {
            fillMe();
            setVisible(false);
        });
        cancelButtonAdd.setOnAction(actionEvent -> {
            setVisibleAdd(false);
            clearFieldsAdd();
        });

        cancelButtonMedicine.setOnAction(actionEvent -> {
            setVisibleAddM(false);
            clearFieldsAddM();
        });

        logOutMyButton.setOnAction(event -> {
            try {
                userCommon.onLogOutButtonPressed(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        logOutAddButton.setOnAction(event -> {
            try {
                userCommon.onLogOutButtonPressed(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        logOutDocButton.setOnAction(event -> {
            try {
                userCommon.onLogOutButtonPressed(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        logOutEditButton.setOnAction(event -> {
            try {
                userCommon.onLogOutButtonPressed(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        logOutPatButton.setOnAction(event -> {
            try {
                userCommon.onLogOutButtonPressed(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        logOutMedButton.setOnAction(event -> {
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

        logOutMedicineButton.setOnAction(event -> {
            try {
                userCommon.onLogOutButtonPressed(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        saveButton.setOnAction(actionEvent -> {
            if (isItOk()) {
                if (Validation.isName(firstNameText.getText(), firstNameStar) && Validation.isName(lastNameText.getText(), lastNameStar) &&
                        Validation.isZipcode(zipText.getText(), zipStar) &&
                        Validation.isCity(cityText.getText(), cityStar) && Validation.isPhoneNumber(phoneText.getText(), phoneStar)
                        && Validation.isEmail(emailText.getText(), emailStar)) {
                    try {
                        currentUser.setSsn(SSNtext.getText());
                        currentUser.setLastName(lastNameText.getText());
                        currentUser.setBDate(Date.valueOf(datePicker.getValue().plusDays(1)));
                        currentUser.setZipCode(zipText.getText());
                        currentUser.setCity(cityText.getText());
                        currentUser.setAddress(addressText.getText());
                        currentUser.setPhoneNumber(phoneText.getText());
                        currentUser.setEmail(emailText.getText());
                        currentUser.setPassword(userCommon.hashPassword(pass1_text.getText()));
                        methods.updateAdmin((Admin) currentUser);
                        if (!pass1_text.getText().isEmpty() && !pass2_text.getText().isEmpty()) {
                            methods.updatePassword(currentUser);
                            pass1_text.clear();
                            pass2_text.clear();
                        }
                        setVisible(false);
                        Validation.alertPopup(Alert.AlertType.INFORMATION, "Updated information has been saved successfully.", "OK", "Update is Saved");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        saveButtonAdd.setOnAction(actionEvent -> {
            if (isItOkAdd()) {
                if (Validation.isSSN(SSNtextAdd.getText(), SSNstarAdd) && Validation.isName(firstNameTextAdd.getText(), firstNameStarAdd) && Validation.isName(lastNameTextAdd.getText(), lastNameStarAdd) &&
                        Validation.isZipcode(zipTextAdd.getText(), zipStarAdd) && Validation.isCity(cityTextAdd.getText(), cityStarAdd) && Validation.isPhoneNumber(phoneTextAdd.getText(), phoneStarAdd)
                        && Validation.isEmail(emailTextAdd.getText(), emailStarAdd) && Validation.isRole(roleTextAdd.getText())) {
                    try {
                        if (methods.getUser(SSNtextAdd.getText()) == null) {
                            if (Integer.parseInt(roleTextAdd.getText()) == 1) {
                                Patient patient = new Patient(SSNtextAdd.getText(), firstNameTextAdd.getText(), lastNameTextAdd.getText(),
                                        Date.valueOf(datePickerAdd.getValue().plusDays(1)), zipTextAdd.getText(), cityTextAdd.getText(), addressTextAdd.getText(),
                                        emailTextAdd.getText(), phoneTextAdd.getText(), userCommon.hashPassword(pass1_textAdd.getText()), true);
                                methods.addPatient(patient);
                            } else if (Integer.parseInt(roleTextAdd.getText()) == 2) {
                                Doctor doctor = new Doctor(SSNtextAdd.getText(), firstNameTextAdd.getText(), lastNameTextAdd.getText(),
                                        Date.valueOf(datePickerAdd.getValue().plusDays(1)), zipTextAdd.getText(), cityTextAdd.getText(), addressTextAdd.getText(),
                                        emailTextAdd.getText(), phoneTextAdd.getText(), userCommon.hashPassword(pass1_textAdd.getText()), true);
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

        saveButtonMedicine.setOnAction(actionEvent -> {
            Medicine newMed = null;
            articleMedicine.setText(String.valueOf(methods.getLastId(Medicine.class) + 1));
                    try {
                        if (!articleMedicine.getText().isEmpty() && onPrescrMedicine.getValue() != null && prodGroupMedicine.getValue() != null && !nameMedicine.getText().isEmpty() && !priceMedicine.getText().isEmpty() && !quantityMedicine.getText().isEmpty()) {
                            if (Validation.isQuantityMedicine(quantityMedicine.getText(), quantityStar) && Validation.isPriceMedicine(priceMedicine.getText(), priceStar)) {
                                if (methods.getMedicine(Integer.parseInt(articleMedicine.getText())) == null) {
                                    boolean prescription;
                                    if (onPrescrMedicine.getValue().equalsIgnoreCase(withP)) {
                                        prescription = true;
                                    } else prescription = false;
                                    if (prescription) {
                                        newMed = new OnPrescription(Integer.parseInt(articleMedicine.getText()), prodGroupMedicine.getValue().getId(), nameMedicine.getText(), producerMedicine.getText(), packageMedicine.getText(), descriptionMedicine.getText(), Integer.parseInt(quantityMedicine.getText()),
                                                Double.parseDouble(priceMedicine.getText()), searchTermsMedicine.getText(), true);
                                    } else {
                                        newMed = new PrescriptionFree(Integer.parseInt(articleMedicine.getText()), prodGroupMedicine.getValue().getId(), nameMedicine.getText(), producerMedicine.getText(), packageMedicine.getText(), descriptionMedicine.getText(), Integer.parseInt(quantityMedicine.getText()),
                                                Double.parseDouble(priceMedicine.getText()), searchTermsMedicine.getText(), true);
                                    }
                                    methods.addMedicine(newMed);
                                    clearFieldsAddM();

                                    fillStore();
                                    setVisibleAddM(false);
                                } else {
                                    setVisibleAddM(false);
                                    articleStar.setVisible(true);
                                    Validation.alertPopup("Medicine with article = " + articleMedicine.getText() + " already exists. It might be inactive.", "Item exists", "Medicine item already exists");
                                }
                            }
                        } else Validation.alertPopup("At least Article Number, Product Group, Type, Name, Price and Quantity must be filled in.","Please, fill out more fields", "Not enough information");
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
        cityStar.setVisible(on);
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
        cityStarAdd.setVisible(on);
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
        nameMedicineStar.setVisible(on);
        packageStar.setVisible(on);
        producerStar.setVisible(on);
        descriptionStar.setVisible(on);
        priceStar.setVisible(on);
        quantityStar.setVisible(on);
        searchStar.setVisible(on);
        typeStar.setVisible(on);
    }

    private void clearFieldsAdd() {
        SSNtextAdd.clear();
        firstNameTextAdd.clear();
        lastNameTextAdd.clear();
        phoneTextAdd.clear();
        zipTextAdd.clear();
        cityTextAdd.clear();
        addressTextAdd.clear();
        emailTextAdd.clear();
        pass1_textAdd.clear();
        pass2_textAdd.clear();
        roleTextAdd.clear();
        datePickerAdd.setValue(LocalDate.now());
    }

    private void clearFieldsAddM(){
        articleMedicine.clear();
        nameMedicine.clear();
        packageMedicine.clear();
        producerMedicine.clear();
        descriptionMedicine.clear();
        priceMedicine.clear();
        quantityMedicine.clear();
        searchTermsMedicine.clear();
    }

    public void fillPatientTable() {

        patientSSNTable.setCellValueFactory(new PropertyValueFactory<>("Ssn"));
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
        doctorSSNTable.setCellValueFactory(new PropertyValueFactory<>("Ssn"));
        doctorFirstNameTable.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        doctorLastNameTable.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        doctorPhoneTable.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        doctorEmailTable.setCellValueFactory(new PropertyValueFactory<>("Email"));
        doctorActive.setCellValueFactory(new PropertyValueFactory<>("active"));

        ObservableList<User> listOfDoctors = FXCollections.observableArrayList(methods.getDoctorList());

        FilteredList<User> filteredData = new FilteredList<>(listOfDoctors, p -> true);
        doctorTableView.setItems(userCommon.userFilter(filteredData, doctorSearchTextField, doctorTableView));
    }

    public void fillEditTable() {
        changeSSNTable.setCellValueFactory(new PropertyValueFactory<>("Ssn"));
        changeFirstNameTable.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        changeLastNameTable.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        changePhoneTable.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        changeEmailTable.setCellValueFactory(new PropertyValueFactory<>("Email"));
        changeRoleTable.setCellValueFactory(new PropertyValueFactory<>("userType"));
        changeActive.setCellValueFactory(new PropertyValueFactory<>("checkBox"));

        ObservableList<User> listOfAll = FXCollections.observableArrayList(methods.getDoctorList());
        listOfAll.addAll(FXCollections.observableArrayList(methods.getPatientList()));

        FilteredList<User> filteredData = new FilteredList<>(listOfAll, p -> true);
        changeTable.setItems(userCommon.userFilter(filteredData, editSearchTextField, changeTable));
        for (int i = 0; i < changeTable.getItems().size(); i++) {
            if (changeTable.getItems().get(i).getActive()) {
                changeActive.getCellData(i).setSelected(true);
            } else changeActive.getCellData(i).setSelected(false);
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
                    storeView.setItems(userCommon.medFilter(filteredData, storeSearchTextField, storeView));

                    for (int i = 0; i < filteredData.size(); i++) {
                        if (filteredData.get(i).getActive()) {
                            isActiveMed.getCellData(i).setSelected(true);
                        } else isActiveMed.getCellData(i).setSelected(false);
                    }

                }

                public void fillMe() {
                    SSNtext.setText(currentUser.getSsn());
                    datePicker.setValue(currentUser.getBDate().toLocalDate());
                    firstNameText.setText(currentUser.getFirstName());
                    lastNameText.setText(currentUser.getLastName());
                    zipText.setText(currentUser.getZipCode());
                    cityText.setText(currentUser.getCity());
                    addressText.setText(currentUser.getAddress());
                    phoneText.setText(currentUser.getPhoneNumber());
                    emailText.setText(currentUser.getEmail());
                }


                private boolean isItOk() {
                    if (firstNameText.getText().isEmpty() || lastNameText.getText().isEmpty() || datePicker.getValue() == null
                            || zipText.getText().isEmpty() || addressText.getText().isEmpty() || emailText.getText().isEmpty() || phoneText.getText().isEmpty()) {
                        if (firstNameText.getText().isEmpty()) {
                            firstNameStar.setVisible(true);
                        }
                        if (lastNameText.getText().isEmpty()) {
                            lastNameStar.setVisible(true);
                        }
                        if (datePicker.getValue() == null) {
                            BDateStar.setVisible(true);
                        }
                        if (zipText.getText().isEmpty()) {
                            zipStar.setVisible(true);
                        }
                        if (cityText.getText().isEmpty()) {
                            cityStar.setVisible(true);
                        }
                        if (addressText.getText().isEmpty()) {
                            addressStar.setVisible(true);
                        }
                        if (emailText.getText().isEmpty()) {
                            emailStar.setVisible(true);
                        }
                        if (phoneText.getText().isEmpty()) {
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
                    if (SSNtextAdd.getText().isEmpty() || firstNameTextAdd.getText().isEmpty() || lastNameTextAdd.getText().isEmpty() || datePickerAdd.getValue() == null
                            || zipTextAdd.getText().isEmpty() || addressTextAdd.getText().isEmpty() || emailTextAdd.getText().isEmpty() || phoneTextAdd.getText().isEmpty()
                            || roleTextAdd.getText().isEmpty()) {

                        if (SSNtextAdd.getText().isEmpty()) {
                            SSNstarAdd.setVisible(true);
                        }
                        if (firstNameTextAdd.getText().isEmpty()) {
                            firstNameStarAdd.setVisible(true);
                        }
                        if (lastNameTextAdd.getText().isEmpty()) {
                            lastNameStarAdd.setVisible(true);
                        }
                        if (datePickerAdd.getValue() == null) {
                            BDateStarAdd.setVisible(true);
                        }
                        if (zipTextAdd.getText().isEmpty()) {
                            zipStarAdd.setVisible(true);
                        }
                        if (cityTextAdd.getText().isEmpty()) {
                            cityStarAdd.setVisible(true);
                        }
                        if (addressTextAdd.getText().isEmpty()) {
                            addressStarAdd.setVisible(true);
                        }
                        if (emailTextAdd.getText().isEmpty()) {
                            emailStarAdd.setVisible(true);
                        }
                        if (phoneTextAdd.getText().isEmpty()) {
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
                                return "true";
                            } else {return "false";}
                        }

                        @Override
                        public CheckBox fromString(String s) {
                            CheckBox checkBox = new CheckBox();
                            if (s.equalsIgnoreCase("true")){
                              checkBox.setSelected(true);
                            } if (s.equalsIgnoreCase("false")) {
                                    checkBox.setSelected(false);}
                            else {
                                Validation.alertPopup("Must be true or false", "Active can only be true or false", "Input error");
                            }
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


                    changeActive.setCellFactory(TextFieldTableCell.<User, CheckBox>forTableColumn(new StringConverter<CheckBox>() {
                        @Override
                        public String toString(CheckBox checkBox) {
                            if (checkBox.isSelected()){
                                return "true";
                            } else {return "false";}
                        }

                        @Override
                        public CheckBox fromString(String s) {
                            CheckBox checkBox = new CheckBox();
                            if (s.equalsIgnoreCase("true")){
                                checkBox.setSelected(true);
                            } if (s.equalsIgnoreCase("false")) {
                                checkBox.setSelected(false);}
                            else {
                                Validation.alertPopup("Must be true or false", "Active can only be true or false", "Input error");
                            }
                            return checkBox;
                        }
                    }));
                    changeActive.setOnEditCommit(
                            new EventHandler<TableColumn.CellEditEvent<User, CheckBox>>() {
                                @Override
                                public void handle(TableColumn.CellEditEvent<User, CheckBox> t) {
                                    ((User) t.getTableView().getItems().get(
                                            t.getTablePosition().getRow())
                                    ).setActive(t.getNewValue().isSelected());
                                    methods.updateUser(t.getTableView().getItems().get(t.getTablePosition().getRow()));

                                   fillDoctorTable();
                                   fillPatientTable();
                                }
                            });

                }

            }

