package controller;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.dBConnection.CommonMethods;
import model.UserSingleton;
import model.User;
import java.net.URL;
import java.nio.file.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    CommonMethods common = new CommonMethods();
    UserCommon userCommon = new UserCommon();
    private User user;

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
        rememberLogin();
    }

    @FXML
    public void loginButtonPressed(ActionEvent ae) {
        if (checkFields()) {
            user = common.getUser(ssnTextField.getText());
            if (user != null) {
                if (ssnTextField.getText().equals(user.getSsn())) {
                    try {
                        String password = user.getPassword();
                        if (userCommon.hashPassword(passwordField.getText()).equals(password)) {
                            if (!user.getActive()) {
                                user.setActive(true);
                                common.updateUser(user);
                            }
                            UserSingleton.getOurInstance().setUser(user);
                            progress.setVisible(true);
                            int type = user.getUserType();
                            PauseTransition pt = new PauseTransition();
                            pt.setOnFinished(event -> {
                                if (rememberMeCheckBox.isSelected()) {
                                    onRememberMeCheckBox();
                                }
                                try {
                                    String view;
                                    if (type == 1) {
                                        view = "/view/patientView.fxml";
                                    } else if (type == 2) {
                                        view = "/view/doctorView.fxml";
                                    } else {
                                        view = "/view/adminView.fxml";
                                    }
                                    userCommon.switchScene(ae, view);
                                } catch (Exception ex) {
                                    System.out.println(ex.getMessage());
                                    ex.printStackTrace();
                                }
                            });
                            pt.play();
                        } else {
                            user = null;
                            Validation.alertPopup("Incorrect SSN or Password ", "Invalid Credentials", "Invalid Credentials");
                            ssnTextField.setText("");
                            passwordField.setText("");
                        }
                    } catch (NoSuchAlgorithmException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    user = null;
                    Validation.alertPopup("Incorrect SSN or Password ", "Invalid Credentials", "Invalid Credentials");
                    ssnTextField.setText("");
                    passwordField.setText("");
                }
            } else {
                Validation.alertPopup("Incorrect SSN or Password ", "Invalid Credentials", "Invalid Credentials");
                ssnTextField.setText("");
                passwordField.setText("");
            }
        }
    }

    @FXML
    public void registerButtonPressed(ActionEvent ae) {
        progress.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setOnFinished(event -> {
            try {
                userCommon.switchScene(ae, "/view/registrationView.fxml");
            } catch (Exception ex) {
                ex.getMessage();
            }
        });
        pt.play();
    }

    @FXML
    public boolean checkFields() {
        if (ssnTextField.getText().isEmpty() && passwordField.getText().isEmpty()) {
            Validation.alertPopup("Please enter information into all the fields", "Empty Fields", "SSN & Password are empty");
            return false;
        } else if (ssnTextField.getText().isEmpty()) {
            Validation.alertPopup("Please enter your SSN into the field", "Empty SSN", "SSN is empty");
            return false;
        } else if (passwordField.getText().isEmpty()) {
            Validation.alertPopup("Please enter your Password into the field", "Empty Password", "Password is empty");
            return false;
        } else
            return true;
    }

    @FXML
    public void onForgotPasswordPressed(MouseEvent me) {
        try {
            userCommon.switchScene(me, "/view/forgottenPasswordView.fxml");
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    @FXML
    public void onRememberMeCheckBox() {
        if (rememberMeCheckBox.isSelected()) {
            try {
                Path path = Paths.get("login.txt");

                ArrayList<String> lines = new ArrayList<>();
                lines.add(ssnTextField.getText());
                lines.add(passwordField.getText());
                Files.write(path, lines, StandardOpenOption.CREATE);
            } catch (Exception ignored) {
            }
        }
    }

    public void rememberLogin() {
        Path path = Paths.get("login.txt");
        if (Files.exists(path, LinkOption.NOFOLLOW_LINKS))
            try {
                List<String> lines = Files.readAllLines(path);
                ssnTextField.setText(lines.get(0));
                passwordField.setText(lines.get(1));
            } catch (Exception ignored) {
                ignored.getSuppressed();
            }
    }

}
