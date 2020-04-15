package controller;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

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
    }
    @FXML public void loginButtonPressed(ActionEvent ae) {
        progress.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(3));
        pt.setOnFinished(event -> {
            System.out.println("Login successful");

        });
        pt.play();
    }
    @FXML public void registerButtonPressed(ActionEvent ae) {

    }
}
