package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Patient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;


public class PatientController implements Initializable {

    Patient model;

    public PatientController(Patient model) {
        this.model = model;

    }

    @FXML
    private ComboBox<String> filter_combo;

    @FXML
    private ComboBox<String> sort_combo;

    @FXML
    private TextField search_textField;

    @FXML
    private Button go_Button;

    @FXML
    private ComboBox<String> filter2_combo;

    @FXML
    private Button cartButton;

    @FXML
    private Button save_button;

    @FXML
    private TextField ssn_text;

    @FXML
    private TextField lastName_text;

    @FXML
    private TextField address_text;

    @FXML
    private TextField phone_text;

    @FXML
    private TextField email_text;

    @FXML
    private TextField firstName_text;

    @FXML
    private TextField birth_text;

    @FXML
    private TextField pass_text;

    ObservableList<String> sortings = FXCollections.observableArrayList("Prescribed first", "A-Z", "Z-A", "Price ascending", "Price descending");
    ObservableList<String> filters1 = FXCollections.observableArrayList("All", "Only Prescribed", "Only Prescription-free");
    ObservableList<String> filters2 = FXCollections.observableArrayList("All", "Only Current", "Only Consumed");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sort_combo.setItems(sortings);
        filter_combo.setItems(filters1);
        filter2_combo.setItems(filters2);
    }

    public ObservableList<String> getSortings() {
        return sortings;
    }

    public ObservableList<String> getFilters1() {
        return filters1;
    }

    public ObservableList<String> getFilters2() {
        return filters2;
    }

    @FXML
    public void cartButtonHandle(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("view/shoppingCartView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
}
