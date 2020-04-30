package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Medicine;
import model.ShoppingCart;
import model.Order;
import model.OrderLine;

import java.net.URL;
import java.util.ResourceBundle;

public class ShoppingCartController implements Initializable {
    @FXML
    private Button logOut_button;

    @FXML
    private Button delete_button;

    @FXML
    private Button checkOut_button;

    @FXML
    private Button back_button;

    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> c1;

    @FXML
    private TableColumn<?, ?> c2;

    @FXML
    private TableColumn<?, ?> c4;

    @FXML
    private TableColumn<?, ?> c5;

    @FXML
    private TableColumn<?, ?> c8;

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
    private TextField totalVAT_text;

    @FXML
    private TextField lastName_text;

    private Order.DeliveryMethod deliveryMethod;
    private ObservableList<ShoppingCart> medList = FXCollections.observableArrayList(PatientController.cart);
    private ObservableList<Enum> deliveryMethodsCombo = FXCollections.observableArrayList(Order.DeliveryMethod.SELFPICKUP, Order.DeliveryMethod.SCHENKER, Order.DeliveryMethod.POSTEN);
    private ObservableList<Enum> paymentMethodsCombo = FXCollections.observableArrayList(Order.PaymentMethod.CREDIT_CARD, Order.PaymentMethod.INVOICE, Order.PaymentMethod.CREDIT_CARD);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        delivery_combo.setItems(deliveryMethodsCombo);
        payment_combo.setItems(paymentMethodsCombo);
    }
}
