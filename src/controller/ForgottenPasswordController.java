package controller;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.CommonMethods;
import model.User;
import model.dBConnection.DAOUser;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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

    @FXML
    private TextField emailText;

    @FXML
    private TextField ssnTextField;

    @FXML
    private Button sendButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField confirmationTextField;

    @FXML
    private Button enterButton;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField passwordTextField2;

    @FXML
    private Button confirmButton;

    String confirmationCode;
    CommonMethods common = new CommonMethods();
    User temp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        confirmationTextField.setVisible(false);
        enterButton.setVisible(false);
        passwordTextField.setVisible(false);
        passwordTextField2.setVisible(false);
        confirmButton.setVisible(false);

        sendButton.setOnAction(event -> sendEmail());
        enterButton.setOnAction(event -> enterButtonHandler());
        cancelButton.setOnAction(event -> handleCancelButton(event));
        confirmButton.setOnAction(event -> confirmButtonHandler(event));
    }

    @FXML
    public void sendEmail() {

        // Add sender
        String from = "edrugspwhelp@gmail.com";
        final String username = "edrugspwhelp@gmail.com";//your Gmail username
        final String password = "EDrugspwProtection";//your Gmail password

        // Add recipient
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
            // Create a default MimeMessage object
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            confirmationCode = confirmationStringGenerator();

            // Set Subject
            message.setSubject("Password Reset");

            // Put the content of your message
            message.setText("Hi there,your confirmation code is: " + confirmationCode);

            // Send message
            Transport.send(message);

            System.out.println("Succesfully sent");

        } catch (MessagingException ignored) {
            System.out.println("Email failed");
        }
        confirmationTextField.setVisible(true);
        enterButton.setVisible(true);
    }

    public String addRecipient() {

        String recipient = "";
        try {
            temp = common.getUser(ssnTextField.getText());
            
            if (temp.getEmail().equals(emailText.getText())) {
                recipient = emailText.getText();
            }
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
            Node node = (Node) ae.getSource();
            Scene scene = node.getScene();
            Stage stage = (Stage) scene.getWindow();

            Parent root = FXMLLoader.load(getClass().getResource("/view/loginView.fxml"));
            Scene newScene = new Scene(root);

            stage.setTitle("e-DRUGS");
            stage.setScene(newScene);

        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    public void enterButtonHandler() {
        if (confirmationTextField.getText().equals(confirmationCode)) {
            passwordTextField.setVisible(true);
            passwordTextField2.setVisible(true);
            confirmButton.setVisible(true);
        } else {
            Validation.alertPopup("Confirmation code doesn't match the code sent to specified email", "Confirmation Failure", "Error");
        }
    }

    public void confirmButtonHandler(ActionEvent ae) {
        try {
        if (passwordTextField.getText().equals(passwordTextField2.getText()) && passwordTextField.getText().length() > 6) {
            temp.setPassword(passwordTextField.getText());
            common.updateUser(temp);
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

