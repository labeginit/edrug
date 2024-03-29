package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import javafx.event.ActionEvent;
import model.dBConnection.CommonMethods;

public class PatientController implements Initializable {

    private CommonMethods commonMethods = new CommonMethods();
    private UserCommon userCommon = new UserCommon();
    private User currentUser;
    public LocalDate localDate;
    private static List<OrderLine> cart = CartSingleton.getOurInstance().getCart();


    @FXML
    private ComboBox<ProdGroup> groupFilter_combo;

    @FXML
    private Button cartButton, save_button, cancel_button, logOut1_button, logOut2_button, logOut3_button;

    @FXML
    private TextField search_textField, lastName_text, address_text, city_text, email_text, firstName_text, maxPrice_text, zipCode_text, phoneNumber_text;

    @FXML
    public TextArea helpMenuMyPrescriptions;

    @FXML
    private Label ssn_text, firstNameStar, lastNameStar, birthDateStar, zipCodeStar, cityStar, phoneStar, emailStar, addressStar, passwordCheckLabel, helpMyPrescriptions;

    @FXML
    private DatePicker dPicker;

    @FXML
    private PasswordField password, confirmPassword;

    @FXML
    private TableView<Medicine> tableView;

    @FXML
    private TableColumn<Medicine, Integer> c1, c5;

    @FXML
    private TableColumn<Medicine, String> c2, c3, c6, c7;

    @FXML
    private TableColumn<Medicine, Double> c4;

    @FXML
    private TableColumn<Medicine, CheckBox> c8;

    @FXML
    private TableView<Prescription> prescriptionTableView;

    @FXML
    private TableColumn<Prescription, Date> c9, c101;

    @FXML
    private TableColumn<Prescription, String> c10;

    @FXML
    private TableView<PrescriptionLine> prescriptionLineTableView;

    @FXML
    private TableColumn<PrescriptionLine, Medicine> c11;

    @FXML
    private TableColumn<PrescriptionLine, String> c12, c15;

    @FXML
    private TableColumn<PrescriptionLine, Integer> c13, c14;


    private final List<ProdGroup> groups = commonMethods.getProductGroupList();
    private ObservableList<Medicine> medList = FXCollections.observableArrayList();

    private List<ProdGroup> fillList(List<ProdGroup> groups) {
        groups.add(0, new ProdGroup(0, "", ""));
        return groups;
    }

    private final ObservableList<ProdGroup> filters1 = FXCollections.observableArrayList(fillList(groups));
    ObservableList<Prescription> prescrList = FXCollections.observableArrayList();
    ObservableList<PrescriptionLine> prescrLines = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentUser = UserSingleton.getOurInstance().getUser();
        List<PrescriptionLine> temp = new ArrayList<>();
        temp.addAll(commonMethods.getPrescriptionLineList(0, currentUser));

        //prepare data for the Shop
        ArrayList<Medicine> templist = new ArrayList<>(commonMethods.getMedicineList((Patient) currentUser)); // all medicine items from prescriptions (not expired and not consumed)
        if (!templist.isEmpty() || templist != null) {
            medList = FXCollections.observableArrayList(templist);
        }
        templist.clear();
        templist.addAll(commonMethods.getMedicineList(false)); // all prescription free medicine items
        if (!templist.isEmpty() || templist != null) {
            medList.addAll(templist);
        }
        // end of prepare data

        userCommon.handleHelpMenus(helpMenuMyPrescriptions, helpMyPrescriptions, "This is the quantity you\nhave, respectively the quantity you\nhave consumed");

        FilteredList<Medicine> filteredData = new FilteredList<>(medList, p -> true);

        setVisible(false);
        cancel_button.setCancelButton(true);

        groupFilter_combo.setItems(filters1);
        setInitialValues(currentUser);
        dPicker.setOnAction(e -> {
            localDate = dPicker.getValue();
        });
        save_button.setOnAction(this::onSaveButtonPressed);
        cancel_button.setOnAction(this::onCancelButtonPressed);

        logOut1_button.setOnAction(event -> {
            try {
                userCommon.onLogOutButtonPressed(event);
                userCommon.clearCart(cart);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        logOut2_button.setOnAction(event -> {
            try {
                userCommon.onLogOutButtonPressed(event);
                userCommon.clearCart(cart);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        logOut3_button.setOnAction(event -> {
            try {
                userCommon.onLogOutButtonPressed(event);
                userCommon.clearCart(cart);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        cartButton.setOnAction(event -> {
            try {
                cartButtonHandle(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        confirmPassword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                passwordCheckLabel.setVisible(!t1.equals(password.getText()));
            }
        });

        //TableView begin
        c1.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("articleNo"));
        c2.setCellValueFactory(new PropertyValueFactory<Medicine, String>("name"));
        c3.setCellValueFactory(new PropertyValueFactory<Medicine, String>("packageSize"));
        c4.setCellValueFactory(new PropertyValueFactory<Medicine, Double>("price"));
        c5.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("quantity"));
        c6.setCellValueFactory(new PropertyValueFactory<Medicine, String>("description"));
        c7.setCellValueFactory(new PropertyValueFactory<Medicine, String>("producer"));
        c8.setCellValueFactory(new PropertyValueFactory<Medicine, CheckBox>("checkBox"));

        userCommon.medFilter(filteredData, search_textField, tableView);

        groupFilter_combo.setOnAction((event) -> {
            ProdGroup val = groupFilter_combo.getValue();
            search_textField.setText("");
            maxPrice_text.setText("");
            if (val.getId() > 0) {
                    filteredData.setPredicate(medicine -> {
                        if (medicine.getGroup() == val.getId()) {
                            return true;
                        }
                        return false;
                    });
                SortedList<Medicine> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableView.comparatorProperty());
                tableView.setItems(sortedData);
            } else {
                userCommon.medFilter(filteredData, search_textField, tableView);
            }
        });

        maxPrice_text.textProperty().addListener((observable, oldValue, newValue) -> {
            search_textField.setText("");
            filteredData.setPredicate(medicine -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                double priceFilter = Double.parseDouble(newValue);

                if (medicine.getPrice() < priceFilter) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Medicine> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);

        //TableView end

        //TreeTableView begin
        prescrList = FXCollections.observableArrayList(commonMethods.getPrescriptionList(currentUser));
        prescrLines = FXCollections.observableArrayList(commonMethods.getPrescriptionLineList(0, currentUser));
        drawPrescriptionTables(prescrList);

        //TreeTableView end
    }

    @FXML
    private void cartButtonHandle(ActionEvent event) throws IOException {
        userCommon.switchScene(event, "/view/shoppingCartView.fxml");
    }

    private void drawPrescriptionTables(ObservableList<Prescription> prescriptions) {
        c9.setCellValueFactory(new PropertyValueFactory<Prescription, Date>("startDate"));
        c10.setCellValueFactory(new PropertyValueFactory<Prescription, String>("doctorName"));
        c101.setCellValueFactory(new PropertyValueFactory<Prescription, Date>("endDate"));
        c11.setCellValueFactory(new PropertyValueFactory<PrescriptionLine, Medicine>("article"));
        c12.setCellValueFactory(new PropertyValueFactory<PrescriptionLine, String>("name"));
        c13.setCellValueFactory(new PropertyValueFactory<PrescriptionLine, Integer>("quantityPrescribed"));
        c14.setCellValueFactory(new PropertyValueFactory<PrescriptionLine, Integer>("quantityConsumed"));
        c15.setCellValueFactory(new PropertyValueFactory<PrescriptionLine, String>("instructions"));

        prescriptionTableView.setItems(prescriptions);
        prescriptionTableView.setOnMouseClicked(e ->{
            loadRowData();
        });
    }

    private void loadRowData(){
        for (Prescription selectedRow: prescriptionTableView.getSelectionModel().getSelectedItems()) {
            prescrLines.setAll(commonMethods.getPrescriptionLineList(selectedRow.getId(), currentUser));
        }
        prescriptionLineTableView.setItems(prescrLines);
    }

    @FXML
    private void addToCartButtonHandle(ActionEvent event) {
        int available = 0;
        int qtyReserved;
        for (Medicine element : tableView.getItems()) {
            if (element.getCheckBox().isSelected()) {
                if (!element.isOnPrescription()){
                    available = element.getQuantity();
                } else {
                    for (int i = 0; i < prescrLines.size(); i++) {
                        if (prescrLines.get(i).getMedicine().getArticleNo() == element.getArticleNo()) {
                            if ((prescrLines.get(i).getQuantityPrescribed() - prescrLines.get(i).getQuantityConsumed()) > 0) {
                                available = commonMethods.getPrescriptionLineUpdate(prescrLines.get(i)).getQuantityPrescribed() - commonMethods.getPrescriptionLineUpdate(prescrLines.get(i)).getQuantityConsumed();
                            }
                        }
                    }

                }
                qtyReserved = element.getQuantityReserved();
                OrderLine line = new OrderLine(0, currentUser, element, element.getPrice(), qtyReserved);
                line.setArticleNo(element.getArticleNo());
                line.setName(element.getName());

                if (available > 0) {
                    if (cart.size() == 0) {
                        cart.add(line);
                    } else if (!cartElementPresenceCheck(element)) {
                        cart.add(line);

                    }
                    qtyReserved = ++qtyReserved;
                    element.setQuantityReserved(qtyReserved);
                    line.setQuantity(qtyReserved);
                    available--;

                    if(!element.isOnPrescription()) {
                        element.setQuantity(available);
                        commonMethods.updateQuantity(element);
                    } else {
                        if (isQuantityPossible(element, prescrLines, qtyReserved)){
                            element.setQuantity(element.getQuantity() - line.getMedicine().getQuantityReserved());
                            commonMethods.updateMedicine(element);
                            prescriptionLineTableView.refresh();
                        } else Validation.alertPopup("The prescribed quantity has been consumed", "Operation not permitted", "You cannot buy more of this item");
                    }
                    tableView.refresh();
                }
                element.getCheckBox().setSelected(false);
            }
        }
    }

    private boolean isQuantityPossible (Medicine item, List<PrescriptionLine> prescriptionLines, int qtyReserved){
        boolean isPossible = false;
        for (int i = 0; i < prescriptionLines.size(); i++) {
            if ((item.getArticleNo() == prescriptionLines.get(i).getArticle() && prescriptionLines.get(i).getQuantityConsumed() < prescriptionLines.get(i).getQuantityPrescribed())) {
                commonMethods.updatePrescriptionLine(prescriptionLines.get(i), qtyReserved + prescriptionLines.get(i).getQuantityConsumed());
                return true;
            }
        }
        return isPossible;
    }

    private boolean cartElementPresenceCheck(Medicine selectedElement) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getMedicine().getArticleNo() == selectedElement.getArticleNo()) {
                cart.get(i).getMedicine().setQuantity(cart.get(i).getMedicine().getQuantity() + 1);
                cart.get(i).setQuantity(cart.get(i).getMedicine().getQuantityReserved() + 1);
                return true;
            }
        }
        return false;
    }

    @FXML
    private void onSaveButtonPressed(ActionEvent ae) {
        if (checkFields()) {
            if (Validation.isName(firstName_text.getText(), firstNameStar) && Validation.isName(lastName_text.getText(), lastNameStar) &&
                    Validation.isZipcode(zipCode_text.getText(), zipCodeStar) && Validation.isCity(city_text.getText(), cityStar) &&
                    Validation.isPhoneNumber(phoneNumber_text.getText(), phoneStar)
                    && Validation.isEmail(email_text.getText(), emailStar)) {
                try {
                    Date dob = Date.valueOf(dPicker.getValue().plusDays(1));
                    currentUser.setFirstName(firstName_text.getText());
                    currentUser.setLastName(lastName_text.getText());
                    currentUser.setBDate(dob);
                    currentUser.setZipCode(zipCode_text.getText());
                    currentUser.setCity(city_text.getText());
                    currentUser.setAddress(address_text.getText());
                    currentUser.setPhoneNumber(phoneNumber_text.getText());
                    currentUser.setEmail(email_text.getText());
                    commonMethods.updateUser(currentUser);
                    String pass = password.getText();
                    if (!pass.equalsIgnoreCase("")) {
                        if (pass.equalsIgnoreCase(confirmPassword.getText())) {
                            currentUser.setPassword(userCommon.hashPassword(pass));
                            commonMethods.updatePassword(currentUser);
                        }
                    }
                    UserSingleton.getOurInstance().setUser(currentUser);
                    setVisible(false);
                    password.clear();
                    confirmPassword.clear();
                    Validation.alertPopup(Alert.AlertType.INFORMATION, "Updated information has been saved successfully.", "OK", "Update is Saved");
                } catch (IllegalArgumentException | NoSuchAlgorithmException ex) {
                    ex.getSuppressed();
                }
            }
        }
    }

    @FXML
    private void onCancelButtonPressed(ActionEvent ae) {
        setInitialValues(currentUser);
        setVisible(false);
    }

    @FXML
    private boolean checkFields() {
        if (firstName_text.getText().isEmpty() || lastName_text.getText().isEmpty() || dPicker.getValue() == null
                || zipCode_text.getText().isEmpty() || city_text.getText().isEmpty() || address_text.getText().isEmpty() || email_text.getText().isEmpty() || phoneNumber_text.getText().isEmpty()) {
            if (firstName_text.getText().isEmpty()) {
                firstNameStar.setVisible(true);
            }
            if (lastName_text.getText().isEmpty()) {
                lastNameStar.setVisible(true);
            }
            if (dPicker.getValue() == null) {
                birthDateStar.setVisible(true);
            }
            if (zipCode_text.getText().isEmpty()) {
                zipCodeStar.setVisible(true);
            }
            if (city_text.getText().isEmpty()) {
                cityStar.setVisible(true);
            }
            if (address_text.getText().isEmpty()) {
                addressStar.setVisible(true);
            }
            if (email_text.getText().isEmpty()) {
                emailStar.setVisible(true);
            }
            if (phoneNumber_text.getText().isEmpty()) {
                phoneStar.setVisible(true);
            }
            Validation.alertPopup("Please enter your information into all fields", "Empty Fields", "Contains empty fields");
            return false;
        } else if (!password.getText().equals(confirmPassword.getText())) {
            passwordCheckLabel.setVisible(true);
            Validation.alertPopup("Password does not match", "Password Mismatch", "Password doesn't match");
            return false;
        } else
            return true;
    }

    @FXML
    private void setVisible(boolean on) {
        firstNameStar.setVisible(on);
        lastNameStar.setVisible(on);
        birthDateStar.setVisible(on);
        addressStar.setVisible(on);
        zipCodeStar.setVisible(on);
        cityStar.setVisible(on);
        phoneStar.setVisible(on);
        emailStar.setVisible(on);
        passwordCheckLabel.setVisible(on);
    }

    private void setInitialValues(User currentUser) {
        firstName_text.setText(currentUser.getFirstName());
        lastName_text.setText(currentUser.getLastName());
        ssn_text.setText(currentUser.getSsn());
        dPicker.setValue(currentUser.getBDate().toLocalDate());
        zipCode_text.setText(currentUser.getZipCode());
        city_text.setText(currentUser.getCity());
        address_text.setText(currentUser.getAddress());
        phoneNumber_text.setText(currentUser.getPhoneNumber());
        email_text.setText(currentUser.getEmail());
    }
}