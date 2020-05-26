package controller;

import FileUtil.RWFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import model.dBConnection.CommonMethods;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.*;

public class CheckoutController implements Initializable {
    private User user = UserSingleton.getOurInstance().getUser();
    private CommonMethods commonMethods = new CommonMethods();
    private UserCommon userCommon = new UserCommon();
    private List<OrderLine> medList = CartSingleton.getOurInstance().cart;
    private List<OrderLine> cart = CartSingleton.getOurInstance().cart;
    private Order.DeliveryMethod deliveryMethod = CartSingleton.getOurInstance().getDeliveryMethod();
    private Order.PaymentMethod payment = CartSingleton.getOurInstance().getPaymentMethod();
    private List<Pharmacy> pharmacyList = commonMethods.retrievePharmacyList();
    private ObservableList<Pharmacy> pharmacies = FXCollections.observableArrayList(pharmacyList);
    @FXML
    private Tab deliveryTab, paymentTab, pickUpTab, confirmationTab;

    @FXML
    private Label deliveryMethod1Label, totalVAT1Label, total1Label,
            creditCardPaymentLabel, pharmacyNameLabel, pharmacyAddressLabel, zipcode3Label
            , city2Label, firstName4Label, address4Label, city4Label, lastName4Label,
            paymentMethod4Label, zipcode4Label, totalVAT4Label, total4Label
            , fName1StarLabel, zipcode1StarLabel, lName1StarLabel, phoneNumber1StarLabel,
            address1StarLabel, city1StarLabel, cardHolderStarLabel,
            cardStarLabel, address2StarLabel, city2StarLabel, zipcode2StarLabel,
            ccvStarLabel, emailLabel, phoneNumber3Label, dPickerStarLabel;

    @FXML
    private TextField firstName1TextField, lastName1TextField, address1TextField,
            zipcode1TextField, phoneNumber1TextField, creditCardNumber1TextField,
            ccvTextField, zipcode2TextField, address2TextField, city2TextField,
            creditCardHolderTextField, city1TextField, expDateTextField;

    @FXML
    private Button next1Button, back1Button, back2Button, next2Button,
            back3Button, next3Button, back4Button, confirmOrderButton;

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
        confirmOrderButton.setOnAction(event -> confirmOrderButtonPressed(event));
    }
    @FXML public void initialValues() {
        if (CartSingleton.getOurInstance().getDeliveryMethod() == Order.DeliveryMethod.SELFPICKUP) {
            tabPane.getTabs().remove(deliveryTab);
            if (CartSingleton.getOurInstance().getPaymentMethod() == Order.PaymentMethod.CREDIT_CARD) {
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
            deliveryMethod1Label.setText(CartSingleton.getOurInstance().getDeliveryMethod().toString());
            firstName1TextField.setText(user.getFirstName());
            lastName1TextField.setText(user.getLastName());
            address1TextField.setText(user.getAddress());
            phoneNumber1TextField.setText(user.getPhoneNumber());
            city1TextField.setText(user.getCity());
            zipcode1TextField.setText(user.getZipCode());
        }
    }

    @FXML private void pickupComboBox(ActionEvent event) {
        Pharmacy pharmacy = pickUpComboBox.getValue();
        pharmacyNameLabel.setText(pharmacy.getStoreName());
        pharmacyAddressLabel.setText(pharmacy.getAddress());
        zipcode3Label.setText(pharmacy.getZipCode());
        city2Label.setText(pharmacy.getCity());
        emailLabel.setText(pharmacy.getEmail());
        phoneNumber3Label.setText(pharmacy.getPhoneNumber());
    }
    @FXML private void calcTotals(){
        double cost = 0;
        for (int i = 0; i < cart.size(); i++) {
            cost = cost + cart.get(i).getPrice() * cart.get(i).getQuantity();
        }
        cost = userCommon.round(cost, 2);
        double totalVAT = userCommon.round(cost * 0.2, 2);
        String vat = String.valueOf(totalVAT);
        if(vat.contains(",")){
            vat = vat.replaceAll(",",".").trim();
        }
        total1Label.setText(Double.toString(cost));
        totalVAT1Label.setText(vat);
        total4Label.setText(Double.toString(cost));
        totalVAT4Label.setText(vat);
    }
    @FXML private void back1ButtonPressed(ActionEvent event) {
        try {
            userCommon.switchScene(event,"../view/shoppingCartView.fxml");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML private void next1ButtonPressed() {
        if (checkFields1()) {
            if (Validation.isName(firstName1TextField.getText(), fName1StarLabel) && Validation.isName(lastName1TextField.getText(), lName1StarLabel) &&
            Validation.isZipcode(zipcode1TextField.getText(), zipcode1StarLabel) && Validation.isPhoneNumber(phoneNumber1TextField.getText(), phoneNumber1StarLabel)
            && Validation.isName(city1TextField.getText(), city1StarLabel)) {
                if (CartSingleton.getOurInstance().getPaymentMethod() == Order.PaymentMethod.CREDIT_CARD) {
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
                    if (CartSingleton.getOurInstance().getPaymentMethod() == Order.PaymentMethod.INVOICE) {
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
                if (CartSingleton.getOurInstance().getDeliveryMethod() == Order.DeliveryMethod.SELFPICKUP) {
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
                    zipcode4Label.setText(zipcode1TextField.getText());
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
            if (CartSingleton.getOurInstance().getPaymentMethod() == Order.PaymentMethod.CREDIT_CARD) {
                paymentMethod4Label.setText(creditCardPaymentLabel.getText());
            } else if (CartSingleton.getOurInstance().getPaymentMethod() == Order.PaymentMethod.INVOICE) {
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
            Validation.alertPopup("Please choose a Pharmacy to pick up prescription", "No Pick up Location", "Select Pickup location");
        }
    }

    @FXML private void back4ButtonPressed() {
        tabPane.getTabs().remove(confirmationTab);
        if (CartSingleton.getOurInstance().getDeliveryMethod() == Order.DeliveryMethod.SELFPICKUP){
            tabPane.getTabs().add(pickUpTab);
        } else if (CartSingleton.getOurInstance().getPaymentMethod() == Order.PaymentMethod.CREDIT_CARD) {
            tabPane.getTabs().add(paymentTab);
        } else {
            tabPane.getTabs().add(deliveryTab);
        }
    }

    @FXML private void confirmOrderButtonPressed(ActionEvent actionEvent) {
        Random rand = new Random();
        int OCR = rand.nextInt(100000000);
        int id = 1 + commonMethods.getLastId(Order.class);
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        Delivery delivery = new Delivery(id,firstName4Label.getText(),lastName4Label.getText(),address4Label.getText(),city4Label.getText(), zipcode4Label.getText(), phoneNumber1TextField.getText(), date);
        Pharmacy pharmacy = pickUpComboBox.getValue();
        Time time = new Time(Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND);
        String paymentMessage;
        String orderMessage;
        ArrayList<String> fileArrayList= new ArrayList<>();

        try {
            if (CartSingleton.getOurInstance().getDeliveryMethod() == Order.DeliveryMethod.SELFPICKUP) {
                Order order = new Order(id,user,date,deliveryMethod,payment,medList,Double.parseDouble(total4Label.getText()),Double.parseDouble(totalVAT4Label.getText()),delivery,pharmacy.getStoreId());
                commonMethods.addOrder(order);
            } else {
                commonMethods.addDelivery(delivery);
                Order order = new Order(id, user, date, deliveryMethod, payment, medList, Double.parseDouble(total4Label.getText()), Double.parseDouble(totalVAT4Label.getText()), delivery, 0);
                commonMethods.addOrder(order);
            }

        } finally {
            Validation.alertPopup(Alert.AlertType.INFORMATION, "Your Order #: " + id + " is registered. You will receive an email shortly.", "Thank you for choosing us", "Your order is being processed");
            try {
                userCommon.switchScene(actionEvent, "/view/patientView.fxml");
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }


        fileArrayList.add( "\n"
                + "\te-Drugs AB\n"
                + "\tElmetorpsVagen 15, 291 39, Kristianstad\n"
                + "\tLand: +460712254630 Mob: +460712205220 \n " +
                "\tFax: 812254639\n\n"
                + "\tCUSTOMER INVOICE\n"
                + " \n");
         fileArrayList.add("\tINFO" + "\t\t\tCUSTOMER");
         fileArrayList.add("DATE: " + date + ",\t\t" +user.getFirstName() + " " + user.getLastName());
         fileArrayList.add("TIME: " + time + ",\t\tMOB: " + user.getPhoneNumber());
         fileArrayList.add("ORDER NO: " + id + ",\t\tADDRESS: " + user.getAddress() + " \n\t\t\t\t\t\t" + user.getCity() + " " + user.getZipCode());
         fileArrayList.add("\t\tORDER DETAILS");
         fileArrayList.add("| ARTICLE NO |" + "\t\tNAME\t\t|" + "QUANTITY |" + " PRICE |");

        for (int i = 0; i < medList.size(); i++) {
            String string = "| " + medList.get(i).getArticleNo() +" | "+ medList.get(i).getName() + " | " + medList.get(i).getQuantity() + " | " + medList.get(i).getPrice() +" |\n";
            fileArrayList.add(string);
        }

        fileArrayList.add("\tTotal VAT: " + totalVAT4Label.getText() + "SEK\n\tTotal Cost: " + total4Label.getText() + "SEK\n");
        RWFile.saveToFile(RWFile.invoice, fileArrayList);
        if (CartSingleton.getOurInstance().getDeliveryMethod() == Order.DeliveryMethod.SELFPICKUP) {
            orderMessage = "\tYou can pick-up your order "+ date +" at the " + pharmacyNameLabel.getText() + " located at \n" +
                    address4Label.getText() + " in " + city4Label.getText() + " " + zipcode4Label.getText() + ".\nYou can contact them" +
                    " by phone " + phoneNumber3Label.getText() + " or email: " + emailLabel.getText() + "\n";
            userCommon.clearCart(cart);
        } else if (CartSingleton.getOurInstance().getDeliveryMethod() == Order.DeliveryMethod.POSTEN) {
            orderMessage = "\tYour order has been sent " + date + " to " + firstName1TextField.getText() + " " + lastName1TextField.getText() + " \n" +
                    "Allow 3-5 business days for shipping to " + address4Label.getText() + " in " + city4Label.getText() + " " + zipcode4Label.getText() + "\n";
        } else {
            orderMessage = "\tYour order has been sent " + date +" to " + firstName1TextField.getText() + " " + lastName1TextField.getText() + " \n" +
                    "Allow 2-3 business days for shipping to " + address4Label.getText() + " in " + city4Label.getText() + " " + zipcode4Label.getText() + "\n";
        }
        if (CartSingleton.getOurInstance().getPaymentMethod() == Order.PaymentMethod.CREDIT_CARD){
            paymentMessage = " You have completed payment of your order with a " + paymentMethod4Label.getText() + " card \n " +
                    "with a total cost of " + total4Label.getText() + "SEK and total VAT of " + totalVAT4Label.getText() + "\n";
            fileArrayList.add("\tPAID");
        } else if (CartSingleton.getOurInstance().getPaymentMethod() == Order.PaymentMethod.INVOICE) {
            paymentMessage = "Your invoice has been included in a text file ";
            fileArrayList.add("\tDUE");
        } else {
            paymentMessage = "Your invoice is included in a text file OCR Number: " + OCR + " Bank Giro: 00000-00000";
            fileArrayList.add("\tOCR Number: " + OCR + "\t Bank Giro: 00000-00000");
        }

        try {
            RWFile.saveToFile(RWFile.invoice, fileArrayList);
            sendEmail(orderMessage,paymentMessage);
            RWFile.delete();
            CartSingleton.getOurInstance().cart.clear();
            medList.clear();
            cart.clear();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void sendEmail(String orderMessage, String paymentMessage) {

        // sender info
        String from = "edrugspwhelp@gmail.com";
        final String username = "edrugspwhelp@gmail.com";//your Gmail username
        final String password = "EDrugspwProtection";//your Gmail password

        String to = user.getEmail();

        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));


            //Create subject, message & file
            message.setSubject("Order is being processed");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Thank you for your order. You will find details below\n\n" + orderMessage + paymentMessage);
            String fileName = "invoice.txt";
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(fileName);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);

        } catch (Exception ex) {
            System.out.println("message failed to be sent");
            ex.printStackTrace();
        }
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
