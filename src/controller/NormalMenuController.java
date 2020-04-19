package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class NormalMenuController implements Initializable {
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
}
