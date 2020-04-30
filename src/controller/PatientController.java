package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

public class PatientController implements Initializable {
    CommonMethods commonMethods = new CommonMethods();
    private User currentUser;
    public LocalDate localDate;
    public static ShoppingCart cart = new ShoppingCart();

    @FXML
    private ComboBox<String> groupFilter_combo;

    @FXML
    private TextField search_textField;

    @FXML
    private ComboBox<String> prescFilter_combo;

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
    private TreeTableView<?> treeTableView;

    @FXML
    private TreeTableColumn<?, ?> c9;

    @FXML
    private TreeTableColumn<?, ?> c10;

    @FXML
    private TreeTableColumn<?, ?> c11;

    @FXML
    private TreeTableColumn<?, ?> c12;

    @FXML
    private TreeTableColumn<?, ?> c13;

    @FXML
    private TreeTableColumn<?, ?> c14;

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
    private ObservableList<String> filters2 = FXCollections.observableArrayList("", "Only Current", "Only Consumed");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setVisible(false);
        cancel_button.setCancelButton(true);
        currentUser = UserSingleton.getOurInstance().getUser();
        groupFilter_combo.setItems(filters1);
        prescFilter_combo.setItems(filters2);
        setInitialValues(currentUser);
        dPicker.setOnAction(e -> {
            localDate = dPicker.getValue();
        });
        save_button.setOnAction(this::onSaveButtonPressed);
        cancel_button.setOnAction(this::onCancelButtonPressed);

        logOut1_button.setOnAction(event -> {
            try {
                onLogOutButtonPressed(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        logOut2_button.setOnAction(event -> {
            try {
                onLogOutButtonPressed(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        logOut3_button.setOnAction(event -> {
            try {
                onLogOutButtonPressed(event);
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

        search_textField.textProperty().addListener((observable, oldValue, newValue) -> {
            maxPrice_text.setText("");
            filteredData.setPredicate(medicine -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (medicine.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (medicine.getSearchTerms().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(medicine.getArticleNo()).contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

           groupFilter_combo.setOnAction((event) -> {
               String val = groupFilter_combo.getValue();
               ObservableList<Medicine> newList = FXCollections.observableArrayList(commonMethods.getMedicineByProductGroupPath(val));
               for (int i = 0; i < newList.size(); i++) {
                   if (newList.get(i).isOnPrescription()){  //add more here - if these ids are from the prescription - do not delete
                       newList.remove(i);
                    }
                }
               SortedList<Medicine> sortedData2 = new SortedList<>(newList);
               sortedData2.comparatorProperty().bind(tableView.comparatorProperty());
               tableView.setItems(sortedData2);

            });

  /*      groupFilter_combo.editorProperty().addListener((observable, oldValue, newValue) -> {
            String val = groupFilter_combo.getValue();
            ObservableList<Medicine> newList = FXCollections.observableArrayList(commonMethods.getMedicineByProductGroupPath(val));
            filteredData.setPredicate(medicine -> {
                if (newValue == null) {
                    return true;
                }
                if (newList != null) {
                    for (int i = 0; i < newList.size(); i++) {
                        if (newList.get(i).isOnPrescription()) {  //add more here - if these ids are from the prescription - do not delete
                            newList.remove(i);
                        }
                        return true;
                    }
                }
                return false;
                //     SortedList<Medicine> sortedData2 = new SortedList<>(newList);
                //     sortedData2.comparatorProperty().bind(tableView.comparatorProperty());
                //     tableView.setItems(sortedData2);
            });
        });
*/
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

    }

    private ObservableList<String> getFilters1() {
        return filters1;
    }

    private ObservableList<String> getFilters2() {
        return filters2;
    }

    @FXML
    private void cartButtonHandle(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/shoppingCartView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML

    private void addToCartButtonHandle(ActionEvent event) {
        int available;
        int qtyReserved;
        for(Medicine element : filteredData)
        {
            if(element.getCheckBox().isSelected())
            {
                available = element.getQuantity();
                qtyReserved = element.getQuantityReserved();

                if (available > 0) {
                    if (cart.size() == 0) {
                        cart.addMedicine(element);
                    } else {
                        if (!cartElementPresenceCheck(element)) {
                            cart.addMedicine(element);
                        }
                    }
                    element.setQuantityReserved(qtyReserved + 1);
                    available = available - 1;
                    element.setQuantity(available);

                    tableView.refresh();
                }
                element.getCheckBox().setSelected(false);
            }
        }
//deleteme
        for (int i = 0; i < cart.size(); i++) {
            System.out.println(cart.getMedicine(i));
        }
        System.out.println();
    }

    private boolean cartElementPresenceCheck(Medicine selectedElement){
        for (int i = 0; i < cart.size(); i++) {
            if (cart.getMedicine(i).getArticleNo() == selectedElement.getArticleNo()) {
                cart.getMedicine(i).setQuantity(cart.getMedicine(i).getQuantity() + 1);
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
    private void onLogOutButtonPressed(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/loginView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
        currentUser = null;
        UserSingleton.getOurInstance().setUser(currentUser);
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
