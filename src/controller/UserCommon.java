package controller;

import FileUtil.RWFile;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Medicine;
import model.OrderLine;
import model.User;
import model.UserSingleton;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import model.dBConnection.CommonMethods;


import java.io.IOException;
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
        Parent tableViewParent = FXMLLoader.load(getClass().getResource(path));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    public SortedList<Medicine> search(FilteredList<Medicine> filteredData, TextField field, TableView<Medicine> tableView){
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
                RWFile.writeObject(RWFile.cartPath, cart);
                RWFile.delete();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
