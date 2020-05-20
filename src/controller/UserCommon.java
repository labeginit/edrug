package controller;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Medicine;
import model.OrderLine;
import model.User;
import model.UserSingleton;
import model.dBConnection.CommonMethods;


import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class UserCommon {
    private User currentUser;
    private CommonMethods commonMethods = new CommonMethods();

    @FXML
    public void onLogOutButtonPressed(ActionEvent event) throws IOException {
        try {
            // Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/loginView.fxml"));
            switchScene(event, "/view/loginView.fxml");
            currentUser = null;
            UserSingleton.getOurInstance().setUser(currentUser);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void switchScene(ActionEvent event, String path) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(path));
        Scene newScene = new Scene(root);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(newScene);
        newScene.getStylesheets().add(getClass().getResource("../FileUtil/layout.css").toExternalForm());
        window.show();
    }

    public SortedList<Medicine> medFilter(FilteredList<Medicine> filteredData, TextField field, TableView<Medicine> tableView){
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(medicine -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (medicine.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (medicine.getSearchTerms().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(medicine.getArticleNo()).contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Medicine> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
        return sortedData;
    }

    public SortedList<User> userFilter(FilteredList<User> filteredData, TextField field, TableView<User> tableView){
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (user.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (user.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (user.getSsn().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<User> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
        return sortedData;
    }

    public void clearCart(List<OrderLine> cart){
        try {
            if (cart != null) {
                for (int i = 0; i < cart.size(); i++) {
                    int article = cart.get(i).getArticleNo();
                    int quantity = cart.get(i).getQuantity();
                    Medicine medicine = commonMethods.getMedicine(article);
                    int newQuantity = medicine.getQuantity() + quantity;
                    medicine.setQuantity(newQuantity);
                    commonMethods.updateQuantity(medicine);
                }
                cart.removeAll(cart);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void handleHelpMenus(TextArea helpMenu, Label helpCircle, String text) {
        helpMenu.setVisible(false);

        helpCircle.setOnMouseEntered(mouseEvent -> {
            helpMenu.setVisible(true);
            helpMenu.setText(text);

        });
        helpCircle.setOnMouseExited(mouseEvent -> helpMenu.setVisible(false));
    }

    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public String hashPassword(String passwordString) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(passwordString.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        return String.format("%064x", new BigInteger(1, digest));
    }
}
