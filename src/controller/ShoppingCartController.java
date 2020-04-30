package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static controller.PatientController.cart;

public class ShoppingCartController implements Initializable {
    private User currentUser;
    private UserCommon userCommon = new UserCommon();

    @FXML
    private Button logOut_button;

    @FXML
    private Button delete_button;

    @FXML
    private Button checkOut_button;

    @FXML
    private Button back_button;

    @FXML
    private TableView<Medicine> tableView;

    @FXML
    private TableColumn<Medicine, Integer> c1;

    @FXML
    private TableColumn<Medicine, String> c2;

    @FXML
    private TableColumn<Medicine, Double> c4;

    @FXML
    private TableColumn<Medicine, Integer> c5;

    @FXML
    private TableColumn<Medicine, CheckBox> c8;

    @FXML
    private TextField firstName_text;

    @FXML
    private ComboBox<Enum> delivery_combo;

    @FXML
    private TextField totalCost_text;

    @FXML
    private ComboBox<Enum> payment_combo;

    @FXML
    private TextField address_text;

    @FXML
    private TextField zipCode_text;

    @FXML
    private TextField totalVAT_text;

    @FXML
    private TextField lastName_text;

   // private ObservableList<Medicine> medList = FXCollections.observableArrayList(cart.getCartList()); //UNCOMMENTING THIS MAKES Quantity CALCULATIONS IN THE Shop SCREWED UP
    private ObservableList<Enum> deliveryMethodsCombo = FXCollections.observableArrayList(Order.DeliveryMethod.SELFPICKUP, Order.DeliveryMethod.SCHENKER, Order.DeliveryMethod.POSTEN);
    private ObservableList<Enum> paymentMethodsCombo = FXCollections.observableArrayList(Order.PaymentMethod.CREDIT_CARD, Order.PaymentMethod.INVOICE, Order.PaymentMethod.CREDIT_CARD);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentUser = UserSingleton.getOurInstance().getUser();
        delivery_combo.setItems(deliveryMethodsCombo);
        payment_combo.setItems(paymentMethodsCombo);
        setInitialValues(currentUser);
        back_button.setOnAction(event -> {
            try {
                backButtonHandle(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        logOut_button.setOnAction(event -> {
            try {
                userCommon.onLogOutButtonPressed(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        c1.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("articleNo"));
        c2.setCellValueFactory(new PropertyValueFactory<Medicine, String>("name"));
        c4.setCellValueFactory(new PropertyValueFactory<Medicine, Double>("price"));
        c5.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("quantityReserved"));
        c8.setCellValueFactory(new PropertyValueFactory<Medicine, CheckBox>("checkBox"));
     //   tableView.setItems(medList);

    }

    private void setInitialValues(User currentUser) {
        firstName_text.setText(currentUser.getFirstName());
        lastName_text.setText(currentUser.getLastName());
        zipCode_text.setText(currentUser.getZipCode());
        address_text.setText(currentUser.getAddress());
    }

    @FXML
    private void backButtonHandle(ActionEvent event) throws IOException {
        userCommon.switchScene(event,"/view/patientView.fxml");
    }

}
