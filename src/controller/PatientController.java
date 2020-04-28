package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    User currentUser;
    LocalDate localDate;

    @FXML
    private ComboBox<String> groupFilter_combo;

    @FXML
    private TextField search_textField;

    @FXML
    private Button go_Button;

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
    private Button buy_button;

    @FXML
    private TableColumn<?, ?> c1;

    @FXML
    private TableColumn<?, ?> c2;

    @FXML
    private TableColumn<?, ?> c3;

    @FXML
    private TableColumn<?, ?> c4;

    @FXML
    private TableColumn<?, ?> c5;

    @FXML
    private TableColumn<?, ?> c6;

    @FXML
    private TableColumn<?, ?> c7;

    @FXML
    private TreeTableView<?> treeTableView;

    @FXML
    private TreeTableColumn<?, ?> c8;

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
    private Button cancel_button;

    @FXML
    private DatePicker dPicker;

    @FXML
    private  Button logOut1_button;

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

    List<ProdGroup> groups = commonMethods.getProductGroupList();
    List<String> groupPaths = new ArrayList<>();

    private List<String> fillList(List<ProdGroup> groups){
        groupPaths.add("All");
        for (int i = 1; i < groups.size(); i++) {
            groupPaths.add(groups.get(i).getPath());
        }
        return groupPaths;
    }

    ObservableList<String> filters1 = FXCollections.observableArrayList(fillList(groups));
    ObservableList<String> filters2 = FXCollections.observableArrayList("All", "Only Current", "Only Consumed");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        setVisible(false);
        cancel_button.setCancelButton(true);
        currentUser = Singleton.getInstance().getUser();
        groupFilter_combo.setItems(filters1);
        prescFilter_combo.setItems(filters2);
        setInitialValues(currentUser);
        dPicker.setOnAction(e -> {localDate = dPicker.getValue();});
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

    }

    public ObservableList<String> getFilters1() {
        return filters1;
    }

    public ObservableList<String> getFilters2() {
        return filters2;
    }

    @FXML
    public void cartButtonHandle(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/shoppingCartView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML public void onSaveButtonPressed(ActionEvent ae) {
        if (checkFields()) {
            if(Validation.isName(firstName_text.getText(), firstNameStar) && Validation.isName(lastName_text.getText(), lastNameStar) &&
                    Validation.isZipcode(zipCode_text.getText(), zipCodeStar) && Validation.isPhoneNumber(phoneNumber_text.getText(), phoneStar)
                    && Validation.isEmail(email_text.getText(), emailStar)) {
                try {
                    Date dob = Date.valueOf(dPicker.getValue());
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
                    Singleton.getInstance().setUser(currentUser);
                    setVisible(false);
                    password.clear();
                    confirmPassword.clear();

                } catch (IllegalArgumentException illegalArgumentException) {
                    illegalArgumentException.getSuppressed();
                }
            }
        }
    }

    @FXML public void onCancelButtonPressed(ActionEvent ae) {
      setInitialValues(currentUser);
      setVisible(false);
    }

    @FXML
    public void onLogOutButtonPressed(ActionEvent event) throws IOException{
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/loginView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
        currentUser = null;
        Singleton.getInstance().setUser(currentUser);
    }

    @FXML public boolean checkFields() {
        if (firstName_text.getText().isEmpty() || lastName_text.getText().isEmpty() || dPicker.getValue() == null
                || zipCode_text.getText().isEmpty() || address_text.getText().isEmpty() || email_text.getText().isEmpty() || phoneNumber_text.getText().isEmpty()) {
            if(firstName_text.getText().isEmpty()){
                firstNameStar.setVisible(true);
            } if (lastName_text.getText().isEmpty()) {
                lastNameStar.setVisible(true);
            } if (dPicker.getValue() == null) {
                birthDateStar.setVisible(true);
            } if (zipCode_text.getText().isEmpty()) {
                zipCodeStar.setVisible(true);
            } if (address_text.getText().isEmpty()) {
                addressStar.setVisible(true);
            } if (email_text.getText().isEmpty()) {
                emailStar.setVisible(true);
            } if (phoneNumber_text.getText().isEmpty()) {
                phoneStar.setVisible(true);
            }
            Validation.alertPopup("Please enter your information into all fields", "Empty Fields", "Contains empty fields");
            return false;
        } else if (!password.getText().equals(confirmPassword.getText())){
            passwordCheckLabel.setVisible(true);
            Validation.alertPopup("Password does not match", "Password Mismatch", "Password doesn't match");
            return false;
        } else
            return true;
    }
    @FXML public void setVisible(boolean on) {
        firstNameStar.setVisible(on);
        lastNameStar.setVisible(on);
        birthDateStar.setVisible(on);
        addressStar.setVisible(on);
        zipCodeStar.setVisible(on);
        phoneStar.setVisible(on);
        emailStar.setVisible(on);
        passwordCheckLabel.setVisible(on);
    }

    public void setInitialValues(User currentUser){
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
