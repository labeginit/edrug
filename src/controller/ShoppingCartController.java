package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import model.*;
import model.dBConnection.CommonMethods;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ShoppingCartController implements Initializable {
    private User currentUser;
    private final CommonMethods commonMethods = new CommonMethods();
    private final UserCommon userCommon = new UserCommon();
    private static List<OrderLine> cart = CartSingleton.getOurInstance().getCart();

    @FXML
    private Button logOut_button, delete_button, checkOut_button, back_button;

    @FXML
    private TableView<OrderLine> tableView;

    @FXML
    private TableColumn<OrderLine, Integer> c1, c5;

    @FXML
    private TableColumn<OrderLine, String> c2;

    @FXML
    private TableColumn<OrderLine, Double> c4;

    @FXML
    private TableColumn<OrderLine, CheckBox> c8;

    @FXML
    private Label firstNameLabel, addressLabel, zipcodeLabel, lastNameLabel, phoneNumberLabel;

    @FXML
    private ComboBox<Enum> delivery_combo, payment_combo;

    @FXML
    private TextField totalCost_text, totalVAT_text;

    private ObservableList<OrderLine> medList = FXCollections.observableArrayList(cart);
    private final ObservableList<Enum> deliveryMethodsCombo = FXCollections.observableArrayList(Order.DeliveryMethod.SELFPICKUP, Order.DeliveryMethod.SCHENKER, Order.DeliveryMethod.POSTEN);
    private final ObservableList<Enum> paymentMethodsCombo = FXCollections.observableArrayList(Order.PaymentMethod.CREDIT_CARD, Order.PaymentMethod.INVOICE, Order.PaymentMethod.BANK_TRANSFER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        medList = FXCollections.observableList(cart);
        currentUser = UserSingleton.getOurInstance().getUser();
        delivery_combo.setItems(deliveryMethodsCombo);
        payment_combo.setItems(paymentMethodsCombo);
        setInitialValues(currentUser);
        if (cart == null){
            totalCost_text.setText("0.0");
            totalVAT_text.setText("0.0");
        } else {
            calcTotals();
        }

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
                userCommon.clearCart(cart);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        for (OrderLine element: medList) {
            element.setCheckBox(new CheckBox());
        }
        c1.setCellValueFactory(new PropertyValueFactory<OrderLine, Integer>("articleNo"));
        c2.setCellValueFactory(new PropertyValueFactory<OrderLine, String>("name"));
        c4.setCellValueFactory(new PropertyValueFactory<OrderLine, Double>("price"));
        makeEditable();
        c5.setCellValueFactory(new PropertyValueFactory<OrderLine, Integer>("quantity"));
        c8.setCellValueFactory(new PropertyValueFactory<OrderLine, CheckBox>("checkBox"));
        tableView.setItems(medList);

        delete_button.setOnAction(this::onDeleteButtonPressed);
        checkOut_button.setOnAction(this::onConfirmButtonPressed);
    }

    private void setInitialValues(User currentUser) {
        firstNameLabel.setText(currentUser.getFirstName());
        lastNameLabel.setText(currentUser.getLastName());
        zipcodeLabel.setText(currentUser.getZipCode());
        addressLabel.setText(currentUser.getAddress());
        phoneNumberLabel.setText(currentUser.getPhoneNumber());
    }

    @FXML
    private void backButtonHandle(ActionEvent event) throws IOException {
        userCommon.switchScene(event,"/view/patientView.fxml");
    }

    public void makeEditable() {
        c5.setCellFactory(TextFieldTableCell.<OrderLine, Integer>forTableColumn(new IntegerStringConverter()));
        c5.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<OrderLine, Integer>>() {

                    @Override
                    public void handle(TableColumn.CellEditEvent<OrderLine, Integer> t) {
                        int q = commonMethods.getMedicine(t.getRowValue().getArticleNo()).getQuantity() + t.getOldValue();
                        Medicine medicine = commonMethods.getMedicine(t.getRowValue().getArticleNo());
                        medicine.setQuantity(q);
                        int newQuantity;
                        if(q >= t.getNewValue()) {
                            ((OrderLine) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                            ).setQuantity(t.getNewValue());
                            ((OrderLine) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                            ).getMedicine().setQuantityReserved(t.getNewValue());
                            newQuantity = q - t.getNewValue();
                            medicine.setQuantity(newQuantity);
                            calcTotals();
                            commonMethods.updateQuantity(medicine);
                            t.getRowValue().setQuantity(t.getNewValue());
                            cart.get(medList.indexOf(t.getRowValue())).setQuantity(t.getNewValue());
                            medList.get(cart.indexOf(t.getRowValue())).setQuantity(t.getNewValue());
                        } else { // When the user tries to buy more than there is in stock
                            t.getRowValue().setQuantity(q);
                            medicine.setQuantityReserved(t.getRowValue().getQuantity());
                            medicine.setQuantity(medicine.getQuantity()-medicine.getQuantityReserved());
                            commonMethods.updateQuantity(medicine);
                            calcTotals();
                            tableView.refresh();
                        }}

                });

    }
    @FXML public void onDeleteButtonPressed(ActionEvent ae) {
        try {
            Medicine medicine;
            int quantity;
            int articleNo;
            ObservableList<OrderLine> remove = FXCollections.observableArrayList();
            for (OrderLine element: medList){
                if (element.getCheckBox().isSelected()) {
                    articleNo = element.getArticleNo();
                    medicine = commonMethods.getMedicine(articleNo);
                    quantity = element.getQuantity();
                    medicine.setQuantity(quantity + medicine.getQuantity());
                    commonMethods.updateQuantity(medicine);
                    remove.add(element);
                }
            } medList.removeAll(remove);
            cart.removeAll(remove);
            calcTotals();
            tableView.setItems(medList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void calcTotals(){
        double cost = 0;
        double vat = 0;
        for (int i = 0; i < cart.size(); i++) {
            cost = cost + cart.get(i).getPrice() * cart.get(i).getQuantity();
        }
        cost = userCommon.round(cost, 2);
        vat = userCommon.round(cost * 0.2, 2);
        totalCost_text.setText(Double.toString(cost));
        totalVAT_text.setText(Double.toString(vat));
    }

    @FXML public void onConfirmButtonPressed (ActionEvent actionEvent) {
        if (medList.size() == 0) {
            Validation.alertPopup("Your shopping cart is empty","Empty Cart", "Cart must contain items");
        } else {
            if(delivery_combo.getValue() != null && payment_combo.getValue() != null) {
                CartSingleton.getOurInstance().setCart(medList);
                CartSingleton.getOurInstance().setDeliveryMethod((Order.DeliveryMethod) delivery_combo.getValue());
                CartSingleton.getOurInstance().setPaymentMethod((Order.PaymentMethod) payment_combo.getValue());
                try {
                    userCommon.switchScene(actionEvent, "/view/checkout.fxml");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                Validation.alertPopup("Please choose delivery method and payment method", "Fill out fields", "Complete the form");
            }
        }
    }
}
