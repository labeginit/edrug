package controller;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.User;
import model.dBConnection.DAOUser;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Label forgotPasswordLabel;

    @FXML
    private Button loginButton;

    @FXML
    private TextField ssnTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    private CheckBox rememberMeCheckBox;

    @FXML
    private ImageView progress;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        progress.setVisible(false);
        loginButton.setOnAction(event -> loginButtonPressed(event));
        registerButton.setOnAction(event -> registerButtonPressed(event));
        forgotPasswordLabel.setOnMouseClicked(event -> onForgotPasswordPressed(event));
    }
    @FXML public void loginButtonPressed(ActionEvent ae) {
        if (checkFields() == true) {
            DAOUser DBUser = new DAOUser();
            User user = DBUser.getUser(ssnTextField.getText());
            if (user != null) {
                String password = user.getPassword();
                if (passwordField.getText().equals(password)) {
                    progress.setVisible(true);
                    PauseTransition pt = new PauseTransition();
                    pt.setDuration(Duration.seconds(2));
                    pt.setOnFinished(event -> {
                        System.out.println("Login successful");
                        try {
                            Node node = (Node) ae.getSource();
                            Scene scene = node.getScene();
                            Stage stage = (Stage) scene.getWindow();

                            Parent root = FXMLLoader.load(getClass().getResource("/view/patient.fxml"));
                            Scene newScene = new Scene(root);

                            stage.setTitle("e-Drugs");
                            stage.setScene(newScene);

                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    });
                    pt.play();
                } else
                    ValidationController.alertPopup("Incorrect SSN or Password ", "Invalid Login", "Invalid Login");
            }
        }
    }
    @FXML public void registerButtonPressed(ActionEvent ae) {
        progress.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(2));
        pt.setOnFinished(event -> {
            try {
                Node node = (Node) ae.getSource();
                Scene scene = node.getScene();
                Stage stage = (Stage) scene.getWindow();

                Parent root = FXMLLoader.load(getClass().getResource("/view/registration.fxml"));
                Scene newScene = new Scene(root);

                stage.setTitle("e-Drugs Registration");
                stage.setScene(newScene);

            } catch (Exception ex) {
                ex.getMessage();
            }
        });
        pt.play();
    }
    @FXML public boolean checkFields(){
        if (ssnTextField.getText().isEmpty() && passwordField.getText().isEmpty()) {
            ValidationController.alertPopup("Please enter information into all the fields", "Empty Fields", "SSN & Password are empty");
            return false;
        } else if (ssnTextField.getText().isEmpty()) {
            ValidationController.alertPopup("Please enter your SSN into the field", "Empty SSN", "SSN is empty");
            return false;
        } else if (passwordField.getText().isEmpty()) {
            ValidationController.alertPopup("Please enter your Password into the field", "Empty Password", "Password is empty");
            return false;
        } else
            return true;
    }
    @FXML public void onForgotPasswordPressed(MouseEvent me) {
        if (!ssnTextField.getText().isEmpty()) {
            DAOUser DBUser = new DAOUser();
            User user = DBUser.getUser(ssnTextField.getText());
            if (user != null) {
                ValidationController.alertPopup("A temporary password has been sent to your email", "Forgot Password", "Forgot Password");
            }
        } else
            ValidationController.alertPopup("Please enter your SSN to get a new password sent to your email address", "SSN Empty", "Need to supply SSN");
    }
}
