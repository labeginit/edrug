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
        currentUser = UserSingleton.getOurInstance().getUser();
        delivery_combo.setItems(deliveryMethodsCombo);
        payment_combo.setItems(paymentMethodsCombo);
        setInitialValues(currentUser);
        if (cart == null){
            totalCost_text.setText("0.0");
            totalVAT_text.setText("0.0");
        } else {
            double cost = 0;
            for (int i = 0; i < cart.size(); i++) {
                cost = cost + cart.get(i).getPrice() * cart.get(i).getQuantity();
            }
            totalCost_text.setText(Double.toString(cost));
            totalVAT_text.setText(Double.toString(cost * 0.2));
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        c1.setCellValueFactory(new PropertyValueFactory<OrderLine, Integer>("articleNo"));
        c2.setCellValueFactory(new PropertyValueFactory<OrderLine, String>("name"));
        c4.setCellValueFactory(new PropertyValueFactory<OrderLine, Double>("price"));
        makeEditable();
        c5.setCellValueFactory(new PropertyValueFactory<OrderLine, Integer>("quantity"));
        c8.setCellValueFactory(new PropertyValueFactory<OrderLine, CheckBox>("checkBox"));
        tableView.setItems(medList);


    }

    private void setInitialValues(User currentUser) {
        firstName_text.setText(currentUser.getFirstName());
        lastName_text.setText(currentUser.getLastName());
        zipCode_text.setText(currentUser.getZipCode());
        address_text.setText(currentUser.getAddress());
    }

    @FXML
    private void backButtonHandle(ActionEvent event) throws IOException { //WHEN SWITCHING BACK TO SHOP the quantities become all wrong
        userCommon.switchScene(event,"/view/patientView.fxml");
    }

    public void makeEditable() {
        c5.setCellFactory(TextFieldTableCell.<OrderLine, Integer>forTableColumn(new IntegerStringConverter()));
        c5.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<OrderLine, Integer>>() {

                    @Override
                    public void handle(TableColumn.CellEditEvent<OrderLine, Integer> t) {
                        if(true) {

                            ((OrderLine) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                            ).setQuantity(t.getNewValue());

                        }}
                });
    }

}
