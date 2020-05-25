package controller;

import javafx.scene.control.*;
import model.dBConnection.CommonMethods;
import model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ForgottenPasswordController implements Initializable {

    public Label helpForgotten;
    public TextArea helpMenuForgotten;
    @FXML
    private TextField emailText, ssnTextField, confirmationTextField;

    @FXML
    private Button sendButton, cancelButton, enterButton, confirmButton;

    @FXML
    private PasswordField passwordField1, passwordField2;

    public String confirmationCode;
    public CommonMethods common = new CommonMethods();
    public UserCommon userCommon = new UserCommon();
    public User temp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        userCommon.handleHelpMenus(helpMenuForgotten, helpForgotten, "Have you forgotten your password?\n\nDon't worry!\nFollow the steps and an email will be sent\nfor you to set up a new password");
        confirmationTextField.setVisible(false);
        enterButton.setVisible(false);
        passwordField1.setVisible(false);
        passwordField2.setVisible(false);
        confirmButton.setVisible(false);

        sendButton.setOnAction(event -> sendEmail());
        enterButton.setOnAction(event -> enterButtonHandler());
        cancelButton.setOnAction(event -> handleCancelButton(event));
        confirmButton.setOnAction(event -> confirmButtonHandler(event));
    }

    @FXML
    public void sendEmail() {

        // sender info
        String from = "edrugspwhelp@gmail.com";
        final String username = "edrugspwhelp@gmail.com";//your Gmail username
        final String password = "EDrugspwProtection";//your Gmail password

        String to = addRecipient();

        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            confirmationCode = confirmationStringGenerator();
            //Create subject & message
            message.setSubject("Password Reset");
            message.setText("Hi there, your confirmation code is: " + confirmationCode);
            Transport.send(message);
            confirmationTextField.setVisible(true);
            enterButton.setVisible(true);
        } catch (MessagingException ignored) {
            System.out.println(ignored.getMessage());
            ssnTextField.clear();
            emailText.clear();
        }
    }

    public String addRecipient() {

        String recipient = "";
        try {
            temp = common.getUser(ssnTextField.getText());
            if (temp != null) {
                if (temp.getEmail().equals(emailText.getText())) {
                    recipient = emailText.getText();
                }
            } else Validation.alertPopup("There is no user with such social security number", "Wrong SSN", "User doesn't exist");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return recipient;
    }

    public String confirmationStringGenerator() {
        StringBuilder confirmationString = new StringBuilder();
        Random random = new Random();
        confirmationString.append("C");
        for (int i = 0; i < 8; i++) {
            confirmationString.append(random.nextInt(10));
        }
        return confirmationString.toString();
    }

    @FXML
    public void handleCancelButton(ActionEvent ae) {
        try {
            userCommon.switchScene(ae, "/view/loginView.fxml");
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    public void enterButtonHandler() {
        if (confirmationTextField.getText().equals(confirmationCode)) {
            passwordField1.setVisible(true);
            passwordField2.setVisible(true);
            confirmButton.setVisible(true);
        } else {
            Validation.alertPopup("Confirmation code doesn't match the code sent to specified email", "Confirmation Failure", "Error");
        }
    }

    public void confirmButtonHandler(ActionEvent ae) {
        try {
            if (passwordField1.getText().equals(passwordField2.getText()) && passwordField1.getText().length() > 5) {
                temp.setPassword(userCommon.hashPassword(passwordField1.getText()));
                common.updatePassword(temp);
                handleCancelButton(ae);
            } else {
                throw new Exception();
            }
        } catch (Exception ignored) {
            Validation.alertPopup("Both passwords must be the same and at least 6 characters long",
                    "Password incorrect",
                    "Warning");
        }
    }
}

