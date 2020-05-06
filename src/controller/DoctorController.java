package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Doctor;
import model.User;
import model.UserSingleton;
import model.dBConnection.CommonMethods;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class DoctorController implements Initializable {
    private User currentUser;
    private UserCommon userCommon = new UserCommon();
    CommonMethods commonMethods = new CommonMethods();

    @FXML
    private TableColumn d1Article;

    @FXML
    private TableColumn d1Name;

    @FXML
    private TableColumn d1Size;

    @FXML
    private TableColumn d1Price;

    @FXML
    private TableColumn d1Stock;

    @FXML
    private TableColumn d1Description;

    @FXML
    private TableColumn d1Producer;

    @FXML
    private TreeTableColumn d2SSN;

    @FXML
    private TreeTableColumn d2Name;

    @FXML
    private TreeTableColumn d2Phone;

    @FXML
    private TreeTableColumn d2Email;

    @FXML
    private TreeTableColumn d2Prescriptions;

    @FXML
    private TextField search_textField;

    @FXML
    private TextField sSN_textField;

    @FXML
    private Label ssn_Label;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField firstName_text;

    @FXML
    private TextField lastName_text;

    @FXML
    private TextField zip_text;

    @FXML
    private TextField address_text;

    @FXML
    private TextField phone_text;

    @FXML
    private TextField email_text;

    @FXML
    private PasswordField password_Text;

    @FXML
    private PasswordField password_Text2;

    @FXML
    private Button logOut_button1;

    @FXML
    private Button logOut_button2;

    @FXML
    private Button logOut_button3;

    @FXML
    private Button cancel_Button;

    @FXML
    private Button save_Button;

    @FXML
    private Button go_Button;

    @FXML
    private Button SSN_Go_Button;

    @FXML
    private ComboBox<String> sort_Combo;

    @FXML
    private ComboBox<String> filter_Combo;

    @FXML
    private Label birthDateStar;

    @FXML
    private Label firstNameStar;

    @FXML
    private Label lastNameStar;

    @FXML
    private Label zipCodeStar;

    @FXML
    private Label addressStar;

    @FXML
    private Label phoneStar;

    @FXML
    private Label emailStar;

    @FXML
    private Label passwordCheckLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hideAllWarningLabels(false);
        currentUser = UserSingleton.getOurInstance().getUser();
        setProfileData();
        cancel_Button.setOnAction(event -> handleCancelButton());
        save_Button.setOnAction(event -> handleSaveButton());

        logOut_button1.setOnAction(event -> {
            try {
                userCommon.onLogOutButtonPressed(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        logOut_button2.setOnAction(event -> {
            try {
                userCommon.onLogOutButtonPressed(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        logOut_button3.setOnAction(event -> {
            try {
                userCommon.onLogOutButtonPressed(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setProfileData() {
        ssn_Label.setText(currentUser.getSsn());
        datePicker.setValue(datePicker.getValue());
        firstName_text.setText(currentUser.getFirstName());
        lastName_text.setText(currentUser.getLastName());
        zip_text.setText(currentUser.getZipCode());
        address_text.setText(currentUser.getAddress());
        phone_text.setText(currentUser.getPhoneNumber());
        email_text.setText(currentUser.getEmail());
        password_Text.setText(currentUser.getPassword());
        password_Text2.setText(currentUser.getPassword());
    }

    @FXML
    private void handleSaveButton() {
        if (checkFields()) {
            if (Validation.isName(firstName_text.getText(), firstNameStar) && Validation.isName(lastName_text.getText(), lastNameStar) &&
                    Validation.isZipcode(zip_text.getText(), zipCodeStar) && Validation.isPhoneNumber(phone_text.getText(), phoneStar)
                    && Validation.isEmail(email_text.getText(), emailStar)) {
                try {
                    Date dob = Date.valueOf(datePicker.getValue().plusDays(1));
                    currentUser.setFirstName(firstName_text.getText());
                    currentUser.setLastName(lastName_text.getText());
                    currentUser.setBDate(dob);
                    currentUser.setZipCode(zip_text.getText());
                    currentUser.setAddress(address_text.getText());
                    currentUser.setPhoneNumber(phone_text.getText());
                    currentUser.setEmail(email_text.getText());
                    commonMethods.updateUser(currentUser);
                    String pass = password_Text.getText();
                    if (!pass.equalsIgnoreCase("")) {
                        if (pass.equalsIgnoreCase(password_Text2.getText())) {
                            currentUser.setPassword(pass);
                            commonMethods.updatePassword(currentUser);
                        }
                    }
                    UserSingleton.getOurInstance().setUser(currentUser);
                    hideAllWarningLabels(false);
                    password_Text.clear();
                    password_Text2.clear();

                } catch (IllegalArgumentException illegalArgumentException) {
                    illegalArgumentException.getSuppressed();
                }
            }
        }
    }

    @FXML
    private void handleCancelButton() {
        setProfileData();
        hideAllWarningLabels(false);
    }

    @FXML
    public boolean checkFields() {
        if (datePicker == null || firstName_text.getText().isEmpty()
                || lastName_text.getText().isEmpty() || zip_text.getText().isEmpty()
                || address_text.getText().isEmpty() || phone_text.getText().isEmpty()
                || email_text.getText().isEmpty() || password_Text.getText().isEmpty()
                || password_Text2.getText().isEmpty()) {
            if (datePicker == null) {
                birthDateStar.setVisible(true);
            }
            if (firstName_text.getText().isEmpty()) {
                firstNameStar.setVisible(true);
            }
            if (lastName_text.getText().isEmpty()) {
                lastNameStar.setVisible(true);
            }
            if (zip_text.getText().isEmpty()) {
                zipCodeStar.setVisible(true);
            }
            if (address_text.getText().isEmpty()) {
                addressStar.setVisible(true);
            }
            if (phone_text.getText().isEmpty()) {
                phoneStar.setVisible(true);
            }
            if (email_text.getText().isEmpty()) {
                emailStar.setVisible(true);
            }
            Validation.alertPopup("Please enter your information into all fields", "Empty Fields", "Contains empty fields");
            return false;
        } else if (!(password_Text.getText().equals(password_Text2.getText()))) {
            passwordCheckLabel.setVisible(true);
            Validation.alertPopup("Password does not match", "Password Mismatch", "Password doesn't match");
            return false;
        } else {
            return true;
        }
    }

    public void hideAllWarningLabels(boolean state) {
        birthDateStar.setVisible(state);
        firstNameStar.setVisible(state);
        lastNameStar.setVisible(state);
        zipCodeStar.setVisible(state);
        addressStar.setVisible(state);
        phoneStar.setVisible(state);
        emailStar.setVisible(state);
        passwordCheckLabel.setVisible(state);
    }
}
