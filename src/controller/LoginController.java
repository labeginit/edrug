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
import javafx.stage.Stage;
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
        pt.setDuration(Duration.seconds(2));
        pt.setOnFinished(event -> {
            System.out.println("Login successful");
            try {
                Node node = (Node) ae.getSource();
                Scene scene = node.getScene();
                Stage stage = (Stage) scene.getWindow();

                Parent root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
                Scene newScene = new Scene(root);

                stage.setTitle("e-Drugs");
                stage.setScene(newScene);


            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });
        pt.play();
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
}
