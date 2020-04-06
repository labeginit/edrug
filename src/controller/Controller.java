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

public class Controller implements Initializable {
    @FXML
    ComboBox<String> filter_combo;

    @FXML
    ComboBox<String> sort_combo;

    @FXML
    TextField search_textField;

    @FXML
    Button go_Button;

    ObservableList<String> sortings = FXCollections.observableArrayList("Prescribed first", "A-Z", "Z-A", "Price ascending", "Price descending");
    ObservableList<String> filters = FXCollections.observableArrayList("Only Prescribed", "Only Prescription-free");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sort_combo.setItems(sortings);
        filter_combo.setItems(filters);
    }

    public ObservableList<String> getSortings() {
        return sortings;
    }

    public ObservableList<String> getFilters() {
        return filters;
    }
}
