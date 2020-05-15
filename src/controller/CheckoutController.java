package controller;

import FileUtil.RWFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.OrderLine;
import model.Pharmacy;
import model.User;
import model.UserSingleton;
import model.dBConnection.CommonMethods;

import java.net.URL;
import java.util.ResourceBundle;

import static controller.PatientController.cart;

public class CheckoutController implements Initializable {
    private User user = UserSingleton.getOurInstance().getUser();
    private CommonMethods commonMethods = new CommonMethods();
    private UserCommon userCommon = new UserCommon();
    private ObservableList<OrderLine> medList =

    @FXML
    private Tab deliveryTab, paymentTab, pickUpTab, confirmationTab;

    @FXML
    private Label deliveryMethod1Label, totalVAT1Label, total1Label, creditCardPaymentLabel, pharmacyNameLabel, pharmacyAddressLabel, zipcode3Label
            , city2Label, firstName4Label, address4Label, city4Label, lastName4Label, paymentMethod4Label, zipcode4Label, totalVAT4Label, total4Label;

    @FXML
    private TextField firstName1TextField, lastName1TextField, address1TextField, zipcode1TextField, phoneNumber1TextField, creditCardNumber1TextField,
            ccvTextField, zipcode2TextField, address2TextField, city2TextField, creditCardHolderTextField;

    @FXML
    private Button next1Button, back1Button, back2Button, next2Button, back3Button, next3Button, back4Button, confirmOrderButton;

    @FXML
    private ComboBox<Pharmacy> pickUpComboBox;

    @FXML
    private TableView<OrderLine> orderTableView;
    @FXML
    private TableColumn<OrderLine, Integer> c1;

    @FXML
    private TableColumn<OrderLine, String> c2;

    @FXML
    private TableColumn<OrderLine, Double> c4;

    @FXML
    private TableColumn<OrderLine, Integer> c5;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (RWFile.readObject(RWFile.cartPath) != null) {
            cart = RWF
            medList = FXCollections.observableList(cart);
        }
        c1.setCellValueFactory(new PropertyValueFactory<OrderLine, Integer>("articleNo"));
        c2.setCellValueFactory(new PropertyValueFactory<OrderLine, String>("name"));
        c4.setCellValueFactory(new PropertyValueFactory<OrderLine, Double>("price"));
        c5.setCellValueFactory(new PropertyValueFactory<OrderLine, Integer>("quantity"));
        orderTableView.setItems(medList);
    }
}
