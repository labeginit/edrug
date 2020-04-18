package controller;

import javafx.animation.PauseTransition;
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

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        progress.setVisible(false);
        passwordCheckLabel.setVisible(false);
        registerButton.setOnAction(event -> onRegisterButtonPressed(event));
        cancelButton.setOnAction(event -> onCancelButtonPressed(event));
    }
    @FXML public void onRegisterButtonPressed(ActionEvent ae) {
        progress.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(2));
        pt.setOnFinished(event -> {
            System.out.println("Login successful");
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
}
