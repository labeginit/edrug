package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Doctor;

import javax.swing.table.TableColumn;
import java.net.URL;
import java.util.ResourceBundle;

public class DoctorController implements Initializable {

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
    private TextField sSN_update;

    @FXML
    private TextField birth_text;

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
    private ComboBox sort_Combo;

    @FXML
    private ComboBox filter_Combo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
