package controller;

import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.CommonMethods;
import model.Patient;
import model.User;

import java.net.URL;
import java.util.ResourceBundle;
import java.sql.Date;

public class RegistrationController implements Initializable {
    CommonMethods common = new CommonMethods();
    private User user;
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
    private TextField birthDate;

    @FXML
    private TextField address;

    @FXML
    private TextField zipcode;

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
    private Label phoneNumberStar;

    @FXML
    private Label emailStar;

    @FXML
    private Label passwordStar;

    @FXML
    private Label confirmPasswordStar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setVisibleFalse();
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
            Validation.isSSN(ssn.getText(), ssnStar) && Validation.isDOB(birthDate.getText(), birthDateStar) &&
            Validation.isZipcode(zipcode.getText(), zipcodeStar) && Validation.isPhoneNumber(phoneNumber.getText(), phoneNumberStar)
            && Validation.isEmail(email.getText(), emailStar)) {
                try {
                    Date dob = Date.valueOf(birthDate.getText());
                    Patient patient = new Patient(ssn.getText(), firstName.getText(), lastName.getText(), dob,
                            zipcode.getText(), address.getText(), email.getText(),
                            phoneNumber.getText(), password.getText());
                    common.addUser(patient);
                } catch (IllegalArgumentException illegalArgumentException) {
                    birthDate.setText("");
                }
                progress.setVisible(true);
                PauseTransition pt = new PauseTransition();
                pt.setDuration(Duration.seconds(2));
                pt.setOnFinished(event -> {
                    System.out.println("Login successful");
                    try {
                        Node node = (Node) ae.getSource();
                        Scene scene = node.getScene();
                        Stage stage = (Stage) scene.getWindow();

                        Parent root = FXMLLoader.load(getClass().getResource("/view/loginView.fxml"));
                        Scene newScene = new Scene(root);

                        stage.setTitle("e-Drugs Login");
                        stage.setScene(newScene);


                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                });
                pt.play();
            }
        }
    }
    @FXML public void onCancelButtonPressed(ActionEvent ae) {
        progress.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(2));
        pt.setOnFinished(event -> {
            try {
                Node node = (Node) ae.getSource();
                Scene scene = node.getScene();
                Stage stage = (Stage) scene.getWindow();

                Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
                Scene newScene = new Scene(root);

                stage.setTitle("e-Drugs Login");
                stage.setScene(newScene);


            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });
        pt.play();
    }
    @FXML public boolean checkFields() {
        if (firstName.getText().isEmpty() || lastName.getText().isEmpty() || ssn.getText().isEmpty() || birthDate.getText().isEmpty()
        || zipcode.getText().isEmpty() || address.getText().isEmpty() || email.getText().isEmpty() || password.getText().isEmpty()
        || confirmPassword.getText().isEmpty() || phoneNumber.getText().isEmpty()) {
            if(firstName.getText().isEmpty()){
                firstNameStar.setVisible(true);
            } if (lastName.getText().isEmpty()) {
                lastNameStar.setVisible(true);
            } if (ssn.getText().isEmpty()) {
                ssnStar.setVisible(true);
            } if (birthDate.getText().isEmpty()) {
                birthDateStar.setVisible(true);
            } if (zipcode.getText().isEmpty()) {
                zipcodeStar.setVisible(true);
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
            Validation.alertPopup("Password does not match", "Password Mismatch", "Password doesnt Match");
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
        zipcodeStar.setVisible(false);
        phoneNumberStar.setVisible(false);
        emailStar.setVisible(false);
        passwordStar.setVisible(false);
        confirmPasswordStar.setVisible(false);
        passwordCheckLabel.setVisible(false);
    }
}
