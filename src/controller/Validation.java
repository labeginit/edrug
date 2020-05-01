package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.util.regex.Pattern;

public class Validation {
    public static void alertPopup(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, infoMessage, ButtonType.OK);
        alert.setResizable(true);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.showAndWait();
    }

    @FXML
    public static boolean isName(String name, Label starLabel) {
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

    @FXML
    public static boolean isSSN(String SSN, Label starLabel) {
        String regex = "^([0-9]{6})-([0-9]{4})+$";
        boolean aSSN = Pattern.compile(regex).matcher(SSN).find();
        if (aSSN) {
            starLabel.setVisible(false);
            return true;
        } else
            alertPopup("Please use a valid SSN", "Invalid SSN", "Use valid SSN");
        starLabel.setVisible(true);
        return false;
    }

    @FXML
    public static boolean isZipcode(String zipcode, Label starLabel) {
        String regex = "^[0-9]{5}+$";
        boolean aZipcode = Pattern.compile(regex).matcher(zipcode).find();
        if (aZipcode) {
            starLabel.setVisible(false);
            return true;
        } else
            alertPopup("Invalid zipcode please enter 5 numerical characters", "Invalid Zipcode", "Zipcode enter valid zipcode");
        starLabel.setVisible(true);
        return false;
    }

    @FXML
    public static boolean isPhoneNumber(String phoneNumber, Label starLabel) {
        String regex = "^(?!\\b(0)\\1+\\b)(\\+?\\d{1,3}[. -]?)?\\(?\\d{3}\\)?([. -]?)\\d{3}\\3\\d{4}$";
        boolean aPhoneNumber = Pattern.compile(regex).matcher(phoneNumber).matches();
        if (aPhoneNumber) {
            starLabel.setVisible(false);
            return true;
        } else
            alertPopup("Please enter a valid phone number ", "Invalid phone number", "Enter a valid phone number");
        starLabel.setVisible(true);
        return false;
    }

    @FXML
    public static boolean isEmail(String email, Label starLabel) {
        String regex = "^(.+)@(.+)$";
        boolean aEmail = Pattern.compile(regex).matcher(email).matches();
        if (aEmail) {
            starLabel.setVisible(false);
            return true;
        } else
            alertPopup("Please enter a valid email", "Invalid email", "Need a valid email");
        starLabel.setVisible(true);
        return false;
    }

    @FXML
    public static boolean isPassword(String password, Label starLabel) {
        String regex = ".{6,16}";
        boolean aPassword = Pattern.compile(regex).matcher(password).find();
        if (aPassword) {
            starLabel.setVisible(false);
            return true;
        } else
            alertPopup("Password needs to be at least 6 characters long", "Short Password", "Password is too short");
        starLabel.setVisible(true);
        return false;
    }

    public static boolean isRole(String role) {
        int roleint = 0;
        try {
            roleint = Integer.parseInt(role);
        } catch (Exception e) {
            alertPopup("Roles are 1 for patient and 2 for doctor", "Incorrect input", "Please enter a true role");
        }
        if (roleint == 1 || roleint == 2) {
            return true;
        } else {
            alertPopup("Roles are 1 for patient and 2 for doctor", "Incorrect input", "Please enter a true role");
            return false;
        }
    }

    public static boolean isPriceMedicine(String price) {
        double priceDouble = 0.0;
        try {
            priceDouble = Double.parseDouble(price);
            return true;
        } catch (Exception e) {
            alertPopup("Not a valid price", "Incorrect input", "Please enter a real price");
            return false;
        }
    }

    public static boolean isQuantityMedicine(String quantity) {
        double quantityDouble = 0.0;
        try {
            quantityDouble = Integer.parseInt(quantity);
            return true;
        } catch (Exception e) {
            alertPopup("Not a valid amount", "Incorrect input", "Please enter a true quantity");
            return false;
        }
    }
    public static boolean isAricleNo(String articleNo) {
        String regex = "^[0-9]{5}+$";
        boolean anArticle = Pattern.compile(regex).matcher(articleNo).find();
        if (anArticle) {
            return true;
        } else
            alertPopup("Invalid article number, please enter 5 numerical characters", "Incorrect value", "Please enter valid article number");
        return false;
    }
}
