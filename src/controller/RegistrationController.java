package controller;

import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import model.dBConnection.CommonMethods;
import model.Patient;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.sql.Date;

public class RegistrationController implements Initializable {

    private CommonMethods commonMethods = new CommonMethods();
    private UserCommon userCommon = new UserCommon();
    private LocalDate localDate;

    @FXML
    private Button registerButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ImageView progress;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField ssn;

    @FXML
    public DatePicker dPicker;

    @FXML
    private TextField address;

    @FXML
    private TextField zipcode;

    @FXML
    private TextField city;

    @FXML
    private TextField phoneNumber;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private PasswordField password;

    @FXML
    private TextField email;

    @FXML
    private Label passwordCheckLabel;

    @FXML
    private Label firstNameStar;

    @FXML
    private Label lastNameStar;

    @FXML
    private Label birthDateStar;

    @FXML
    private Label ssnStar;

    @FXML
    private Label addressStar;

    @FXML
    private Label zipcodeStar;

    @FXML
    private Label cityStar;

    @FXML
    private Label phoneNumberStar;

    @FXML
    private Label emailStar;

    @FXML
    private Label passwordStar;

    @FXML
    private Label confirmPasswordStar;
    public Label helpNewUser;
    public TextArea helpMenuNewUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        userCommon.handleHelpMenus(helpMenuNewUser, helpNewUser, "New to e-Drugs?\n\nVery welcome!\nPlease enter all your information\nas correctly as possible and\nlook forward to using e-Drugs");


        setVisibleFalse();
        dPicker.setOnAction(e -> {localDate = dPicker.getValue();});
        registerButton.setOnAction(this::onRegisterButtonPressed);
        cancelButton.setOnAction(this::onCancelButtonPressed);
        confirmPassword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                passwordCheckLabel.setVisible(!t1.equals(password.getText()));
            }
        });
    }
    @FXML public void onRegisterButtonPressed(ActionEvent ae) {
        if (checkFields()) {
            if(Validation.isName(firstName.getText(), firstNameStar) && Validation.isName(lastName.getText(), lastNameStar) &&
            Validation.isSSN(ssn.getText(), ssnStar) &&
            Validation.isZipcode(zipcode.getText(), zipcodeStar) && Validation.isCity(city.getText(), cityStar) && Validation.isPhoneNumber(phoneNumber.getText(), phoneNumberStar)
            && Validation.isEmail(email.getText(), emailStar) && Validation.isPassword(password.getText(), passwordStar)) {
                try {
                    Date dob = Date.valueOf(dPicker.getValue().plusDays(1));
                    Patient patient = new Patient(ssn.getText(), firstName.getText(), lastName.getText(), dob,
                            zipcode.getText(), city.getText(), address.getText(), email.getText(),
                            phoneNumber.getText(), userCommon.hashPassword(password.getText()));
                    commonMethods.addUser(patient);
                } catch (IllegalArgumentException | NoSuchAlgorithmException ex) {
                   ex.getSuppressed();
                }
                progress.setVisible(true);
                PauseTransition pt = new PauseTransition();
                pt.setOnFinished(event -> {
                    System.out.println("Login successful");
                    try {
                        userCommon.switchScene(ae, "/view/loginView.fxml");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
                pt.play();
            }
        }
    }
    @FXML public void onCancelButtonPressed(ActionEvent ae) {
        progress.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setOnFinished(event -> {
            try {
                userCommon.switchScene(ae, "/view/loginView.fxml");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });
        pt.play();
    }
    @FXML public boolean checkFields() {
        if (firstName.getText().isEmpty() || lastName.getText().isEmpty() || ssn.getText().isEmpty() || dPicker.getValue() == null
        || zipcode.getText().isEmpty() || city.getText().isEmpty() || address.getText().isEmpty() || email.getText().isEmpty() || password.getText().isEmpty()
        || confirmPassword.getText().isEmpty() || phoneNumber.getText().isEmpty()) {
            if(firstName.getText().isEmpty()){
                firstNameStar.setVisible(true);
            } if (lastName.getText().isEmpty()) {
                lastNameStar.setVisible(true);
            } if (ssn.getText().isEmpty()) {
                ssnStar.setVisible(true);
            } if (dPicker.getValue() == null) {
                birthDateStar.setVisible(true);
            } if (zipcode.getText().isEmpty()) {
                zipcodeStar.setVisible(true);
            } if (city.getText().isEmpty()) {
                cityStar.setVisible(true);
            } if (address.getText().isEmpty()) {
                addressStar.setVisible(true);
            } if (email.getText().isEmpty()) {
                emailStar.setVisible(true);
            } if (password.getText().isEmpty()) {
                passwordStar.setVisible(true);
            } if (confirmPassword.getText().isEmpty()) {
                confirmPasswordStar.setVisible(true);
            } if (phoneNumber.getText().isEmpty()) {
                phoneNumberStar.setVisible(true);
            }
            Validation.alertPopup("Please enter your information into all fields", "Empty Fields", "Contains empty fields");
            return false;
        } else if (!password.getText().equals(confirmPassword.getText())){
            passwordStar.setVisible(true);
            confirmPasswordStar.setVisible(true);
            Validation.alertPopup("Password does not match", "Password Mismatch", "Password doesn't Match");
            return false;
        } else
            return true;
    }
    @FXML public void setVisibleFalse() {
        progress.setVisible(false);
        firstNameStar.setVisible(false);
        lastNameStar.setVisible(false);
        ssnStar.setVisible(false);
        birthDateStar.setVisible(false);
        addressStar.setVisible(false);
        cityStar.setVisible(false);
        zipcodeStar.setVisible(false);
        phoneNumberStar.setVisible(false);
        emailStar.setVisible(false);
        passwordStar.setVisible(false);
        confirmPasswordStar.setVisible(false);
        passwordCheckLabel.setVisible(false);
    }
}
