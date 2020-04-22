package controller;

import javafx.fxml.Initializable;
import model.ShoppingCart;

import java.net.URL;
import java.util.ResourceBundle;

public class ShoppingCartController implements Initializable {

    ShoppingCart model;

    public ShoppingCartController(ShoppingCart model) {
        this.model = model;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
