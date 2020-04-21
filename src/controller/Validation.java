package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Validation {
    public static void alertPopup(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, infoMessage, ButtonType.OK);
        alert.setResizable(true);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.showAndWait();
    }

    //an example of validation method
    public static boolean validateAmount(String value){
        String reg = "^(?!0+$)\\d+$";
        if (!value.matches(reg)){
            alertPopup("Wrong input format", "Illegal format", "Wrong input format");
        }
        return value.matches(reg);
    }
}
