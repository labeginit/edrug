package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import model.dBConnection.CommonMethods;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class CheckoutController implements Initializable {
    private User user = UserSingleton.getOurInstance().getUser();
    private CommonMethods commonMethods = new CommonMethods();
    private UserCommon userCommon = new UserCommon();
    private List<OrderLine> medList = CartSingleton.getOurInstance().cart;
    private List<OrderLine> cart = CartSingleton.getOurInstance().cart;
    private List<Pharmacy> pharmacyList = commonMethods.retrievePharmacyList();
    private ObservableList<Pharmacy> pharmacies = FXCollections.observableArrayList(pharmacyList);
    @FXML
    private Tab deliveryTab, paymentTab, pickUpTab, confirmationTab;

    @FXML
    private Label deliveryMethod1Label, totalVAT1Label, total1Label, creditCardPaymentLabel, pharmacyNameLabel, pharmacyAddressLabel, zipcode3Label
            , city2Label, firstName4Label, address4Label, city4Label, lastName4Label, paymentMethod4Label, zipcode4Label, totalVAT4Label, total4Label
            , fName1StarLabel, zipcode1StarLabel, lName1StarLabel, phoneNumber1StarLabel, address1StarLabel, city1StarLabel, cardHolderStarLabel,
            cardStarLabel, address2StarLabel, city2StarLabel, zipcode2StarLabel, ccvStarLabel, emailLabel, phoneNumber3Label, dPickerStarLabel;

    @FXML
    private TextField firstName1TextField, lastName1TextField, address1TextField, zipcode1TextField, phoneNumber1TextField, creditCardNumber1TextField,
            ccvTextField, zipcode2TextField, address2TextField, city2TextField, creditCardHolderTextField, city1TextField, expDateTextField;

    @FXML
    private Button next1Button, back1Button, back2Button, next2Button, back3Button, next3Button, back4Button, confirmOrderButton;

    @FXML
    private ComboBox<Pharmacy> pickUpComboBox;

    @FXML
    private TableView<OrderLine> orderTableView;

    @FXML
    private TableColumn<OrderLine, Integer> c1, c5;

    @FXML
    private TableColumn<OrderLine, String> c2;

    @FXML
    private TableColumn<OrderLine, Double> c4;

    @FXML private TabPane tabPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialValues();
        setVisible();
        c1.setCellValueFactory(new PropertyValueFactory<OrderLine, Integer>("articleNo"));
        c2.setCellValueFactory(new PropertyValueFactory<OrderLine, String>("name"));
        c4.setCellValueFactory(new PropertyValueFactory<OrderLine, Double>("price"));
        c5.setCellValueFactory(new PropertyValueFactory<OrderLine, Integer>("quantity"));
        orderTableView.setItems((ObservableList<OrderLine>) medList);
        calcTotals();
        pickUpComboBox.setItems(pharmacies);
        back1Button.setOnAction(event -> back1ButtonPressed(event));
        next1Button.setOnAction(event -> next1ButtonPressed());
        back2Button.setOnAction(event -> back2ButtonPressed(event));
        next2Button.setOnAction(event -> next2ButtonPressed());
        back3Button.setOnAction(event -> back3ButtonPressed(event));
        pickUpComboBox.setOnAction(event -> pickupComboBox(event));
        next3Button.setOnAction(event -> next3ButtonPressed());
        back4Button.setOnAction(event -> back4ButtonPressed());
    }
    @FXML public void initialValues() {
        if (CartSingleton.getOurInstance().getDeliveryMethod().equalsIgnoreCase("SELFPICKUP")) {
            tabPane.getTabs().remove(deliveryTab);
            if (CartSingleton.getOurInstance().getPaymentMethod().equalsIgnoreCase("CREDIT_CARD")) {
                paymentTab.setText("Step 1");
                tabPane.getTabs().remove(pickUpTab);
                tabPane.getTabs().remove(confirmationTab);
            } else {
                tabPane.getTabs().remove(paymentTab);
                pickUpTab.setText("Step 1");
                tabPane.getTabs().remove(confirmationTab);
            }
        } else {
            deliveryTab.setText("Step 1");
            tabPane.getTabs().remove(pickUpTab);
            tabPane.getTabs().remove(paymentTab);
            tabPane.getTabs().remove(confirmationTab);
            deliveryMethod1Label.setText(CartSingleton.getOurInstance().getDeliveryMethod());
            firstName1TextField.setText(user.getFirstName());
            lastName1TextField.setText(user.getLastName());
            address1TextField.setText(user.getAddress());
            phoneNumber1TextField.setText(user.getPhoneNumber());
            zipcode1TextField.setText(user.getZipCode());
        }
    }

    @FXML private void pickupComboBox(ActionEvent event) {
        Pharmacy pharmacy = pickUpComboBox.getValue();
        pharmacyNameLabel.setText(pharmacy.getStoreName());
        pharmacyAddressLabel.setText(pharmacy.getAddress());
        zipcode3Label.setText(Integer.toString(pharmacy.getZipcode()));
        city2Label.setText(pharmacy.getCity());
        emailLabel.setText(pharmacy.getEmail());
        phoneNumber3Label.setText(pharmacy.getPhoneNumber());
    }
    @FXML private void calcTotals(){
        double cost = 0;
        for (int i = 0; i < cart.size(); i++) {
            cost = cost + cart.get(i).getPrice() * cart.get(i).getQuantity();
        }
        DecimalFormat df = new DecimalFormat("####0.00");
        double totalVAT = (cost * 0.2);
        total1Label.setText(Double.toString(cost));
        totalVAT1Label.setText(Double.toString(Double.parseDouble(df.format(totalVAT))));
        total4Label.setText(Double.toString(cost));
        totalVAT4Label.setText(Double.toString(Double.parseDouble(df.format(totalVAT))));
    }
    @FXML private void back1ButtonPressed(ActionEvent event) {
        try {
            userCommon.switchScene(event,"/view/shoppingCartView.fxml");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML private void next1ButtonPressed() {
        if (checkFields1()) {
            if (Validation.isName(firstName1TextField.getText(), fName1StarLabel) && Validation.isName(lastName1TextField.getText(), lName1StarLabel) &&
            Validation.isZipcode(zipcode1TextField.getText(), zipcode1StarLabel) && Validation.isPhoneNumber(phoneNumber1TextField.getText(), phoneNumber1StarLabel)
            && Validation.isName(city1TextField.getText(), city1StarLabel)) {
                if (CartSingleton.getOurInstance().getPaymentMethod().equalsIgnoreCase("CREDIT_CARD")) {
                    tabPane.getTabs().add(paymentTab);
                    paymentTab.setText("Step 2");
                    tabPane.getTabs().remove(deliveryTab);
                } else {
                    tabPane.getTabs().add(confirmationTab);
                    confirmationTab.setText("Final Step");
                    tabPane.getTabs().remove(deliveryTab);
                    firstName4Label.setText(firstName1TextField.getText());
                    lastName4Label.setText(lastName1TextField.getText());
                    address4Label.setText(address1TextField.getText());
                    zipcode4Label.setText(zipcode1TextField.getText());
                    city4Label.setText(city1TextField.getText());
                    if (CartSingleton.getOurInstance().getPaymentMethod().equalsIgnoreCase("INVOICE")) {
                        paymentMethod4Label.setText("Invoice");
                    } else {
                        paymentMethod4Label.setText("Bank Transfer");
                    }
                }
            }
        }
    }

    @FXML private void next2ButtonPressed() {
        if (checkFields2()) {
            if (Validation.isName(creditCardHolderTextField.getText(), cardHolderStarLabel) &&
                    Validation.isValid(Long.parseLong(creditCardNumber1TextField.getText()), cardStarLabel, creditCardPaymentLabel)
            && Validation.isZipcode(zipcode2TextField.getText(), zipcode2StarLabel) && Validation.isName(city2TextField.getText(), city2StarLabel) &&
            Validation.isCCV(ccvTextField.getText(), ccvStarLabel, creditCardPaymentLabel) && Validation.isValidEXPDate(expDateTextField.getText(), dPickerStarLabel)) {
                if (CartSingleton.getOurInstance().getDeliveryMethod().equalsIgnoreCase("SELFPICKUP")) {
                    tabPane.getTabs().add(pickUpTab);
                    pickUpTab.setText("Step 2");
                    tabPane.getTabs().remove(paymentTab);
                } else {
                    tabPane.getTabs().add(confirmationTab);
                    confirmationTab.setText("Final Step");
                    tabPane.getTabs().remove(paymentTab);
                    firstName4Label.setText(user.getFirstName());
                    lastName4Label.setText(user.getLastName());
                    paymentMethod4Label.setText(creditCardPaymentLabel.getText());
                    address4Label.setText(address1TextField.getText());
                    zipcode4Label.setText(zipcode4Label.getText());
                    city4Label.setText(city1TextField.getText());
                }
            }
        }
    }
    @FXML private void back2ButtonPressed(ActionEvent event) {
        if (paymentTab.getText().equalsIgnoreCase("Step 1")) {
            try {
                userCommon.switchScene(event, "/view/shoppingCartView.fxml");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            tabPane.getTabs().remove(paymentTab);
            tabPane.getTabs().add(deliveryTab);
            deliveryTab.setText("Step 1");
        }
    }

    @FXML private void back3ButtonPressed(ActionEvent event) {
        if (pickUpTab.getText().equalsIgnoreCase("Step 1")) {
            try {
                userCommon.switchScene(event, "/view/shoppingCartView.fxml");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            tabPane.getTabs().remove(pickUpTab);
            tabPane.getTabs().add(paymentTab);
            paymentTab.setText("Step 1");
        }
    }

    @FXML private void next3ButtonPressed() {
        if (pickUpComboBox != null) {
            tabPane.getTabs().remove(pickUpTab);
            tabPane.getTabs().add(confirmationTab);
            confirmationTab.setText("Final Step");
            if (CartSingleton.getOurInstance().getPaymentMethod().equalsIgnoreCase("CREDIT_CARD")) {
                paymentMethod4Label.setText(creditCardPaymentLabel.getText());
            } else if (CartSingleton.getOurInstance().getPaymentMethod().equalsIgnoreCase("INVOICE")) {
                paymentMethod4Label.setText("Invoice");
            } else {
                paymentMethod4Label.setText("Bank Transfer");
            }
            firstName4Label.setText(user.getFirstName());
            lastName4Label.setText(user.getLastName());
            address4Label.setText(pharmacyAddressLabel.getText());
            zipcode4Label.setText(zipcode3Label.getText());
            city4Label.setText(city2Label.getText());
        } else {
            Validation.alertPopup("Please choose a Pharmacy to pick up prescription", "No Pick up Loactaion", "Select Pickup location");
        }
    }

    @FXML private void back4ButtonPressed() {
        tabPane.getTabs().remove(confirmationTab);
        if (CartSingleton.getOurInstance().getDeliveryMethod().equalsIgnoreCase("SELFPICKUP")){
            tabPane.getTabs().add(pickUpTab);
        } else if (CartSingleton.getOurInstance().getPaymentMethod().equalsIgnoreCase("CREDIT_CARD")) {
            tabPane.getTabs().add(paymentTab);
        } else {
            tabPane.getTabs().add(deliveryTab);
        }
    }

    @FXML private void confirmOrderButtonPressed(ActionEvent actionEvent) {
        Random rand = new Random();
        int id = rand.nextInt(1000);
        Date date = new Date();
        Order.DeliveryMethod orderMethod;
        Order.PaymentMethod paymentMethod;
        if (CartSingleton.getOurInstance().getDeliveryMethod().equalsIgnoreCase("SELFPICKUP")) {
            orderMethod = Order.DeliveryMethod.SELFPICKUP;
        } else if (CartSingleton.getOurInstance().getDeliveryMethod().equalsIgnoreCase("POSTEN")) {
            orderMethod = Order.DeliveryMethod.POSTEN;
        } else {
            orderMethod = Order.DeliveryMethod.SCHENKER;
        }
        if (CartSingleton.getOurInstance().getPaymentMethod().equalsIgnoreCase("CREDIT_CARD")){
            paymentMethod = Order.PaymentMethod.CREDIT_CARD;
        } else if (CartSingleton.getOurInstance().getPaymentMethod().equalsIgnoreCase("INVOICE")) {
            paymentMethod = Order.PaymentMethod.INVOICE;
        } else {
            paymentMethod = Order.PaymentMethod.BANK_TRANSFER;
        }

        Order order = new Order(id,user,(java.sql.Date) date, orderMethod, paymentMethod, medList);

    }
    @FXML private boolean checkFields1() {
        if (firstName1TextField.getText().isEmpty() || lastName1TextField.getText().isEmpty() || address1TextField.getText().isEmpty() ||
        zipcode1TextField.getText().isEmpty() || phoneNumber1TextField.getText().isEmpty() || city1TextField.getText().isEmpty()) {
            if (firstName1TextField.getText().isEmpty()) {
                fName1StarLabel.setVisible(true);
            } if (lastName1TextField.getText().isEmpty()) {
                lName1StarLabel.setVisible(true);
            } if (address1TextField.getText().isEmpty()) {
                address1StarLabel.setVisible(true);
            } if (zipcode1TextField.getText().isEmpty()){
                zipcode1StarLabel.setVisible(true);
            } if (phoneNumber1StarLabel.getText().isEmpty()) {
                phoneNumber1StarLabel.setVisible(true);
            } if (city1TextField.getText().isEmpty()) {
                city1StarLabel.setVisible(true);
            }
            Validation.alertPopup("All text fields need to be filled out to move to the next step","Empty Text Fields","Fill all TextFields");
            return false;
        } else {
            return true;
        }
    }

    @FXML private boolean checkFields2() {
        if (creditCardHolderTextField.getText().isEmpty() || creditCardNumber1TextField.getText().isEmpty() || ccvTextField.getText().isEmpty() ||
        address2TextField.getText().isEmpty() || zipcode2TextField.getText().isEmpty() || city2TextField.getText().isEmpty() || expDateTextField.getText().isEmpty()) {
            if (creditCardHolderTextField.getText().isEmpty()) {
                cardHolderStarLabel.setVisible(true);
            } if (creditCardNumber1TextField.getText().isEmpty()) {
                cardHolderStarLabel.setVisible(true);
            } if (ccvTextField.getText().isEmpty()) {
                ccvStarLabel.setVisible(true);
            } if (address2TextField.getText().isEmpty()) {
                address2StarLabel.setVisible(true);
            } if (zipcode2TextField.getText().isEmpty()) {
                zipcode2StarLabel.setVisible(true);
            } if (city2TextField.getText().isEmpty()) {
                city2StarLabel.setVisible(true);
            } if (expDateTextField.getText().isEmpty()) {
                dPickerStarLabel.setVisible(true);
            }
            Validation.alertPopup("All text fields need to be filled out to move to the next step","Empty Text Fields","Fill all TextFields");
            return false;
            } else {
            return true;
        }
    }

    @FXML private void setVisible() {
        fName1StarLabel.setVisible(false);
        lName1StarLabel.setVisible(false);
        address1StarLabel.setVisible(false);
        zipcode1StarLabel.setVisible(false);
        phoneNumber1StarLabel.setVisible(false);
        city1StarLabel.setVisible(false);
        cardHolderStarLabel.setVisible(false);
        cardStarLabel.setVisible(false);
        address2StarLabel.setVisible(false);
        city2StarLabel.setVisible(false);
        zipcode2StarLabel.setVisible(false);
        ccvStarLabel.setVisible(false);
        dPickerStarLabel.setVisible(false);
    }
}
