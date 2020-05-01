package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
