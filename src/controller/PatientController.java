package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.security.Signature;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;


public class PatientController implements Initializable {
    CommonMethods commonMethods = new CommonMethods();
    User currentUser;

    @FXML
    private ComboBox<String> groupFilter_combo;

 //   @FXML
 //   private ComboBox<String> sort_combo;

    @FXML
    private TextField search_textField;

    @FXML
    private Button go_Button;

    @FXML
    private ComboBox<String> prescFilter_combo;

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

    @FXML
    private Button buy_button;

    @FXML
    private TableColumn<?, ?> c1;

    @FXML
    private TableColumn<?, ?> c2;

    @FXML
    private TableColumn<?, ?> c3;

    @FXML
    private TableColumn<?, ?> c4;

    @FXML
    private TableColumn<?, ?> c5;

    @FXML
    private TableColumn<?, ?> c6;

    @FXML
    private TableColumn<?, ?> c7;

    @FXML
    private TreeTableColumn<?, ?> c8;

    @FXML
    private TreeTableColumn<?, ?> c9;

    @FXML
    private TreeTableColumn<?, ?> c10;

    @FXML
    private TreeTableColumn<?, ?> c11;

    @FXML
    private TreeTableColumn<?, ?> c12;

    @FXML
    private TreeTableColumn<?, ?> c13;

    @FXML
    private Button cancel_button;

    @FXML
    private DatePicker dPicker;


    @FXML
    private TextField zipcode_text;

    @FXML
    private TextField phoneNumber_text;

    @FXML
    private TextField password_text;

    @FXML
    private TextField confirmPassword_text;

    @FXML
    private Label ssnStar;

    @FXML
    private Label birthDateStar;

    @FXML
    private Label firstNameStar;

    @FXML
    private Label lastNameStar;

    @FXML
    private Label zipCodeStar;

    @FXML
    private Label phoneStar;

    @FXML
    private Label emailStar;

    @FXML
    private Label newPassStar;

    @FXML
    private Label confirmPassStar;

    @FXML
    private Label addressStar;

    @FXML
    private Label passwordCheckLabel;

    List<ProdGroup> groups = commonMethods.getProductGroupList();
    List<String> groupPaths = new ArrayList<>();

    private List<String> fillList(List<ProdGroup> groups){
        groupPaths.add("All");
        for (int i = 1; i < groups.size(); i++) {
            groupPaths.add(groups.get(i).getPath());
        }
        return groupPaths;
    }

   // ObservableList<String> sortings = FXCollections.observableArrayList("Prescribed first", "A-Z", "Z-A", "Price ascending", "Price descending");
    ObservableList<String> filters1 = FXCollections.observableArrayList(fillList(groups));
    ObservableList<String> filters2 = FXCollections.observableArrayList("All", "Only Current", "Only Consumed");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentUser = Singleton.getInstance().getUser();
        groupFilter_combo.setItems(filters1);
        prescFilter_combo.setItems(filters2);
        firstName_text.setText(currentUser.getFirstName());
        lastName_text.setText(currentUser.getLastName());
        ssn_text.setText(currentUser.getSsn());
       // birth_text.setText(currentUser.getBDate().toString());
        zipcode_text.setText(currentUser.getZipCode());
        address_text.setText(currentUser.getAddress());
      //  phone_text.setText(currentUser.getPhoneNumber());
        email_text.setText(currentUser.getEmail());

    }

 //   public ObservableList<String> getSortings() {
 //       return sortings;
 //   }

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
