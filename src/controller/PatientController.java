package controller;

import FileUtil.RWFile;
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
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import model.dBConnection.CommonMethods;

public class PatientController implements Initializable {
    private CommonMethods commonMethods = new CommonMethods();
    private UserCommon userCommon = new UserCommon();
    private User currentUser;
    public LocalDate localDate;
    public static List<OrderLine> cart = new ArrayList<>();


    @FXML
    private ComboBox<String> groupFilter_combo;

    @FXML
    private TextField search_textField;

    @FXML
    private Button cartButton;

    @FXML
    private Button save_button;

    @FXML
    private Label ssn_text;

    @FXML
    private TextField lastName_text;

    @FXML
    private TextField address_text;

    @FXML
    private TextField email_text;

    @FXML
    private TextField firstName_text;

    @FXML
    private TextField maxPrice_text;

    @FXML
    private Button buy_button;

    @FXML
    private TableView<Medicine> tableView;

    @FXML
    private TableColumn<Medicine, Integer> c1;

    @FXML
    private TableColumn<Medicine, String> c2;

    @FXML
    private TableColumn<Medicine, String> c3;

    @FXML
    private TableColumn<Medicine, Double> c4;

    @FXML
    private TableColumn<Medicine, Integer> c5;

    @FXML
    private TableColumn<Medicine, String> c6;

    @FXML
    private TableColumn<Medicine, String> c7;

    @FXML
    private TableColumn<Medicine, CheckBox> c8;

    @FXML
    private TableView<Prescription> prescriptionTableView;

    @FXML
    private TableColumn<Prescription, Date> c9;

    @FXML
    private TableColumn<Prescription, String> c10;

    @FXML
    private TableColumn<Prescription, Date> c101;

    @FXML
    private TableView<PrescriptionLine> prescriptionLineTableView;

    @FXML
    private TableColumn<PrescriptionLine, Medicine> c11;

    @FXML
    private TableColumn<PrescriptionLine, String> c12;

    @FXML
    private TableColumn<PrescriptionLine, Integer> c13;

    @FXML
    private TableColumn<PrescriptionLine, Integer> c14;

    @FXML
    private Button cancel_button;

    @FXML
    private DatePicker dPicker;

    @FXML
    private Button logOut1_button;

    @FXML
    private Button logOut2_button;

    @FXML
    private Button logOut3_button;

    @FXML
    private TextField zipCode_text;

    @FXML
    private TextField phoneNumber_text;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private Label birthDateStar;

    @FXML
    private Label firstNameStar;

    @FXML
    private Label lastNameStar;

    @FXML
    private Label zipCodeStar;

    @FXML
    private Label phoneStar;

    @FXML
    private Label emailStar;

    @FXML
    private Label addressStar;

    @FXML
    private Label passwordCheckLabel;

    @FXML
    private TreeItem<PrescriptionParent> rootNode;

    private List<ProdGroup> groups = commonMethods.getProductGroupList();
    private List<String> groupPaths = new ArrayList<>();
    private ObservableList<Medicine> medList = FXCollections.observableArrayList(commonMethods.getMedicineList(false)); // here will probably need to add those from Prescriptions
    private FilteredList<Medicine> filteredData = new FilteredList<>(medList, p -> true);


    private List<String> fillList(List<ProdGroup> groups) {
        groupPaths.add("");
        for (int i = 1; i < groups.size(); i++) {
            groupPaths.add(groups.get(i).getPath());
        }
        return groupPaths;
    }

    private ObservableList<String> filters1 = FXCollections.observableArrayList(fillList(groups));
    ObservableList<Prescription> prescrList = FXCollections.observableArrayList();
    ObservableList<PrescriptionLine> prescrLines = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setVisible(false);
        cancel_button.setCancelButton(true);
        currentUser = UserSingleton.getOurInstance().getUser();
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

        // currently the combination of different filters is not working after value in the comboBox has been changed
        userCommon.medFilter(filteredData, search_textField, tableView);

        groupFilter_combo.setOnAction((event) -> {
            String val = groupFilter_combo.getValue();
            ObservableList<Medicine> newList = FXCollections.observableArrayList(commonMethods.getMedicineByProductGroupPath(val));
            for (int i = 0; i < newList.size(); i++) {
                if (newList.get(i).isOnPrescription()) {  //add more here - if these ids are from the prescription - do not delete
                    newList.remove(i);
                }
            }
            SortedList<Medicine> sortedData2 = new SortedList<>(newList);
            sortedData2.comparatorProperty().bind(tableView.comparatorProperty());
            tableView.setItems(sortedData2);

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
        prescrLines = FXCollections.observableArrayList();
        drawPrescriptionTables(prescrList);

        //TreeTableView end
    }

    private ObservableList<String> getFilters1() {
        return filters1;
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
        int available;
        int qtyReserved;
        for (Medicine element : tableView.getItems()) {
            if (element.getCheckBox().isSelected()) {
                available = element.getQuantity();
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
                    element.setQuantity(available);
                    commonMethods.updateQuantity(element);

                    RWFile.writeObject(RWFile.cartPath, cart);
                    tableView.refresh();
                }
                element.getCheckBox().setSelected(false);
            }
        }
    }


    private boolean cartElementPresenceCheck(Medicine selectedElement) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getMedicine().getArticleNo() == selectedElement.getArticleNo()) {
                cart.get(i).getMedicine().setQuantity(cart.get(i).getMedicine().getQuantity() + 1);
                cart.get(i).setQuantity(selectedElement.getQuantityReserved() + 1);
                return true;
            }
        }
        return false;
    }

    @FXML
    private void onSaveButtonPressed(ActionEvent ae) {
        if (checkFields()) {
            if (Validation.isName(firstName_text.getText(), firstNameStar) && Validation.isName(lastName_text.getText(), lastNameStar) &&
                    Validation.isZipcode(zipCode_text.getText(), zipCodeStar) && Validation.isPhoneNumber(phoneNumber_text.getText(), phoneStar)
                    && Validation.isEmail(email_text.getText(), emailStar)) {
                try {
                    Date dob = Date.valueOf(dPicker.getValue().plusDays(1));
                    currentUser.setFirstName(firstName_text.getText());
                    currentUser.setLastName(lastName_text.getText());
                    currentUser.setBDate(dob);
                    currentUser.setZipCode(zipCode_text.getText());
                    currentUser.setAddress(address_text.getText());
                    currentUser.setPhoneNumber(phoneNumber_text.getText());
                    currentUser.setEmail(email_text.getText());
                    commonMethods.updateUser(currentUser);
                    String pass = password.getText();
                    if (!pass.equalsIgnoreCase("")) {
                        if (pass.equalsIgnoreCase(confirmPassword.getText())) {
                            currentUser.setPassword(pass);
                            commonMethods.updatePassword(currentUser);
                        }
                    }
                    UserSingleton.getOurInstance().setUser(currentUser);
                    setVisible(false);
                    password.clear();
                    confirmPassword.clear();

                } catch (IllegalArgumentException illegalArgumentException) {
                    illegalArgumentException.getSuppressed();
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
                || zipCode_text.getText().isEmpty() || address_text.getText().isEmpty() || email_text.getText().isEmpty() || phoneNumber_text.getText().isEmpty()) {
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
        address_text.setText(currentUser.getAddress());
        phoneNumber_text.setText(currentUser.getPhoneNumber());
        email_text.setText(currentUser.getEmail());
    }
}
