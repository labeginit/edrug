package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.util.regex.Pattern;

public class Validation {
    static void alertPopup(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, infoMessage, ButtonType.OK);
        alert.setResizable(true);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.showAndWait();
    }
    @FXML public static boolean isName(String name, Label starLabel) {
        String regex = "^[\\p{L}\\s.’\\-,]+$";
        boolean aName = Pattern.compile(regex).matcher(name).find();
        if (aName) {
            starLabel.setVisible(false);
            return true;
        } else
            alertPopup("Name must contain only alphabetical characters", "Invalid name", "Please enter proper name");
            starLabel.setVisible(true);
            return false;
    }
    @FXML public static boolean isSSN(String SSN, Label starLabel) {
        String regex = "[0-9]{10,12}";
        boolean aSSN = Pattern.compile(regex).matcher(SSN).find();
        if (aSSN) {
            starLabel.setVisible(false);
            return true;
        } else
            alertPopup("Please use a valid SSN", "Invalid SSN", "Use valid SSN");
            starLabel.setVisible(true);
            return false;
    }
    @FXML public static boolean isDOB(String dob, Label starLabel) {
        String regex = "^([0-9]{4})-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$";
        boolean aDOB = Pattern.compile(regex).matcher(dob).matches();
        if (aDOB) {
            starLabel.setVisible(false);
            return true;
        } else
            alertPopup("Date must follow the correct format YYYY-MM-DD and be a valid date", "Improper Date", "Improper date format ");
            starLabel.setVisible(true);
            return false;
    }
    @FXML public static boolean isZipcode(String zipcode, Label starLabel) {
        String regex = "[0-9]{5}";
        boolean aZipcode = Pattern.compile(regex).matcher(zipcode).find();
        if (aZipcode) {
            starLabel.setVisible(false);
            return true;
        } else
            alertPopup("Invalid zipcode please enter 5 numerical characters", "Invalid Zipcode", "Zipcode enter valid zipcode");
            starLabel.setVisible(true);
            return false;
    }
    @FXML public static boolean isPhoneNumber(String phoneNumber, Label starLabel) {
        String regex = "^\\s?((\\+[1-9]{1,4}[ \\-]*)|(\\([0-9]{2,3}\\)[ \\-]*)|([0-9]{2,4})[ \\-]*)*?[0-9]{3,4}?[ \\-]*[0-9]{3,4}?\\s?";
        boolean aPhoneNumber = Pattern.compile(regex).matcher(phoneNumber).matches();
        if (aPhoneNumber) {
            starLabel.setVisible(false);
            return true;
        } else
            alertPopup("Please enter a valid phone number ", "Invalid phone number", "Enter a valid phone number");
            starLabel.setVisible(true);
            return false;
    }
}
