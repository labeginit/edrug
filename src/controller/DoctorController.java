package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Doctor;
import model.User;
import model.UserSingleton;

import javax.swing.table.TableColumn;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class DoctorController implements Initializable {
    private User currentUser;
    private UserCommon userCommon = new UserCommon();

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
    private TableColumn d2SSN;

    @FXML
    private TableColumn d2Name;

    @FXML
    private TableColumn d2Phone;

    @FXML
    private TableColumn d2Email;

    @FXML
    private TableColumn d2Prescriptions;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentUser = UserSingleton.getOurInstance().getUser();
        setProfileData(currentUser);

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
    public void setProfileData(User currentUser) {
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
    public void handleSaveButton() {

    }
}
