package controller;

import FileUtil.RWFile;
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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ShoppingCartController implements Initializable {
    private User currentUser;
    private CommonMethods commonMethods = new CommonMethods();
    private UserCommon userCommon = new UserCommon();
    private static List<OrderLine> cart = CartSingleton.getOurInstance().getCart();

    @FXML
    private Button logOut_button;

    @FXML
    private Button delete_button;

    @FXML
    private Button checkOut_button;

    @FXML
    private Button back_button;

    @FXML
    private TableView<OrderLine> tableView;

    @FXML
    private TableColumn<OrderLine, Integer> c1;

    @FXML
    private TableColumn<OrderLine, String> c2;

    @FXML
    private TableColumn<OrderLine, Double> c4;

    @FXML
    private TableColumn<OrderLine, Integer> c5;

    @FXML
    private TableColumn<OrderLine, CheckBox> c8;

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

    private ObservableList<OrderLine> medList = FXCollections.observableArrayList(cart);
    private ObservableList<Enum> deliveryMethodsCombo = FXCollections.observableArrayList(Order.DeliveryMethod.SELFPICKUP, Order.DeliveryMethod.SCHENKER, Order.DeliveryMethod.POSTEN);
    private ObservableList<Enum> paymentMethodsCombo = FXCollections.observableArrayList(Order.PaymentMethod.CREDIT_CARD, Order.PaymentMethod.INVOICE, Order.PaymentMethod.BANK_TRANSFER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //if (RWFile.readObject(RWFile.cartPath) != null) {
        //    cart = RWFile.readObject(RWFile.cartPath);
            medList = FXCollections.observableList(cart);
        //}
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

        delete_button.setOnAction(event -> onDeleteButtonPressed(event));
    }

    private void setInitialValues(User currentUser) {
        firstName_text.setText(currentUser.getFirstName());
        lastName_text.setText(currentUser.getLastName());
        zipCode_text.setText(currentUser.getZipCode());
        address_text.setText(currentUser.getAddress());
    }

    @FXML
    private void backButtonHandle(ActionEvent event) throws IOException {
       // RWFile.writeObject(RWFile.cartPath, cart);
        userCommon.switchScene(event,"/view/patientView.fxml");
    }

    public void makeEditable() {
        c5.setCellFactory(TextFieldTableCell.<OrderLine, Integer>forTableColumn(new IntegerStringConverter()));
        c5.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<OrderLine, Integer>>() {

                    @Override
                    public void handle(TableColumn.CellEditEvent<OrderLine, Integer> t) {
                        int q = commonMethods.getMedicine(t.getRowValue().getArticleNo()).getQuantity() + t.getOldValue();
                        System.out.println(q + " commonMethods.getMedicine(t.getRowValue().getArticleNo()).getQuantity() + t.getOldValue()");
                        Medicine medicine = commonMethods.getMedicine(t.getRowValue().getArticleNo());
                        medicine.setQuantity(q);
                        System.out.println(medicine.getQuantity() + " med.qty "  +  medicine.getQuantityReserved()+ " med.reserve");
                        int newQuantity;
                        if(q >= t.getNewValue()) {
                            ((OrderLine) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                            ).setQuantity(t.getNewValue());
                            ((OrderLine) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                            ).getMedicine().setQuantityReserved(t.getNewValue());
                            newQuantity = q - t.getNewValue();
                            System.out.println(newQuantity + " new qty");
                            medicine.setQuantity(newQuantity);
                          //  System.out.println(medicine.getQuantity() + " med.qty2 "  +  medicine.getQuantityReserved()+ " med.reserve2");
                            calcTotals();
                            commonMethods.updateQuantity(medicine);
                            t.getRowValue().setQuantity(t.getNewValue());
                            System.out.println(t.getRowValue().getQuantity());
                            cart.get(medList.indexOf(t.getRowValue())).setQuantity(t.getNewValue());
                            medList.get(cart.indexOf(t.getRowValue())).setQuantity(t.getNewValue());
                        //    RWFile.writeObject(RWFile.cartPath, cart);

                        } else { // When the user tries to buy more than there is in stock
                            t.getRowValue().setQuantity(q);
                            System.out.println(t.getRowValue().getQuantity() + " else statement");
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
            RWFile.delete();
            Medicine medicine;
            int quantity;
            int articleNo;
            ObservableList<OrderLine> remove = FXCollections.observableArrayList();
            for (OrderLine element: medList){
                if (element.getCheckBox().isSelected()) {
                    articleNo = element.getArticleNo();
                    medicine = commonMethods.getMedicine(articleNo);
                    quantity = element.getQuantity();
                    System.out.println(quantity + " qty3");
                    medicine.setQuantity(quantity + medicine.getQuantity());
                    System.out.println(medicine.getQuantity() + " med qty3");
                    commonMethods.updateQuantity(medicine);
                    remove.add(element);
                    System.out.println(medList.toString() + "removed");
                } else {
                    System.out.println(medList.toString() + "kept");
                }
            } medList.removeAll(remove);
            cart.removeAll(remove);
         //   RWFile.writeObject(RWFile.cartPath, cart);
            calcTotals();
            tableView.setItems(medList);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void calcTotals(){
        double cost = 0;
        for (int i = 0; i < cart.size(); i++) {
            cost = cost + cart.get(i).getPrice() * cart.get(i).getQuantity();
        }
        totalCost_text.setText(Double.toString(cost));
        totalVAT_text.setText(Double.toString(cost * 0.2));
    }
}
