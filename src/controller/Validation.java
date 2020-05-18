package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class Validation {
    public static void alertPopup(String infoMessage, String titleBar, String headerMessage) {
        alertPopup(Alert.AlertType.WARNING, infoMessage, titleBar, headerMessage);

    }

    public static void alertPopup(Alert.AlertType alertType, String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(alertType, infoMessage, ButtonType.OK);
        alert.setResizable(true);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.getDialogPane().setPrefSize(500, 200);
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
    //creditcard validation { VISA ,MASTERCARD, AMEX, DISCOVER
    public static boolean isValid(long number, Label starLabel, Label CCPaymentLabel)
    {
        if (getSize(number) >= 13 && getSize(number) <= 16 ) {
            if (prefixMatched(number, 4) ||
                    prefixMatched(number, 5) ||
                    prefixMatched(number, 37) ||
                    prefixMatched(number, 6)) {
                if (prefixMatched(number, 4)){
                    CCPaymentLabel.setVisible(true);
                    CCPaymentLabel.setText("VISA");
                } else if (prefixMatched(number, 5)) {
                    CCPaymentLabel.setVisible(true);
                    CCPaymentLabel.setText("MASTERCARD");
                } else if (prefixMatched(number, 37)) {
                    CCPaymentLabel.setVisible(true);
                    CCPaymentLabel.setText("AMEX");
                } else if (prefixMatched(number, 4)) {
                    CCPaymentLabel.setVisible(true);
                    CCPaymentLabel.setText("DISCOVER");
                }
                if ((sumOfDoubleEvenPlace(number) +
                        sumOfOddPlace(number)) % 10 == 0) {
                    return true;
                } else {
                    starLabel.setVisible(true);
                    return false;
                }
            } else {
                starLabel.setVisible(true);
                return false;
            }
        } else {
            starLabel.setVisible(true);
            return false;
        }

    }

    // Get the result from Step 2
    public static int sumOfDoubleEvenPlace(long number)
    {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number) - 2; i >= 0; i -= 2)
            sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);

        return sum;
    }

    // Return this number if it is a single digit, otherwise,
    // return the sum of the two digits
    public static int getDigit(int number)
    {
        if (number < 9)
            return number;
        return number / 10 + number % 10;
    }

    // Return sum of odd-place digits in number
    public static int sumOfOddPlace(long number)
    {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number) - 1; i >= 0; i -= 2)
            sum += Integer.parseInt(num.charAt(i) + "");
        return sum;
    }

    // Return true if the digit d is a prefix for number
    public static boolean prefixMatched(long number, int d)
    {
        return getPrefix(number, getSize(d)) == d;
    }

    // Return the number of digits in d
    public static int getSize(long d)
    {
        String num = d + "";
        return num.length();
    }

    // Return the first k number of digits from
    // number. If the number of digits in number
    // is less than k, return number.
    public static long getPrefix(long number, int k)
    {
        if (getSize(number) > k) {
            String num = number + "";
            return Long.parseLong(num.substring(0, k));
        }
        return number;
    }
    // } credit card validation

    public static boolean isCCV(String ccv, Label starLabel, Label ccPaymentLabel) {
        if (ccPaymentLabel.getText().equalsIgnoreCase("AMEX")) {
            String regex = "^[0-9]{4}+$";
            boolean aCCV = Pattern.compile(regex).matcher(ccv).find();
            if (aCCV) {
                starLabel.setVisible(false);
                return true;
            } else
                alertPopup("Invalid CCV please enter the proper numerical characters", "Invalid CCV", "Please enter 4 integers");
            starLabel.setVisible(true);
            return false;
        } else {
            String regex = "^[0-9]{3}+$";
            boolean aCCV = Pattern.compile(regex).matcher(ccv).find();
            if (aCCV) {
                starLabel.setVisible(false);
                return true;
            } else
                alertPopup("Invalid CCV please enter the proper numerical characters", "Invalid CCV", "Please enter 3 integers");
            starLabel.setVisible(true);
            return false;
        }
    }
    //MM/YY
    public static boolean isValidEXPDate(String expDate, Label starLabel) {
        String regex = "^(0[1-9]|1[0-2])\\/([0-9]{2})$";
        boolean aEXPDate = Pattern.compile(regex).matcher(expDate).find();
        if (aEXPDate) {
            String yy = "20";
            char m1 = expDate.charAt(0);
            char m2 = expDate.charAt(1);
            char y1 = expDate.charAt(3);
            char y2 = expDate.charAt(4);
            char y = yy.charAt(0);
            char y0 = yy.charAt(1);
            char[] years = new char[4];
            years[0] = y;
            years[1] = y0;
            years[2] = y1;
            years[3] = y2;
            System.out.println(years);
            char[] months= new char[2];
            months[0] = m1;
            months[1] = m2;
            System.out.println(months);
            String yearString = String.valueOf(years);
            String monthString = String.valueOf(months);
            String date = yearString + "-" + monthString + "-28";
            java.sql.Date dateEXP = java.sql.Date.valueOf(date);
            java.util.Date todaysDate = new java.util.Date();
            System.out.println(dateEXP);
            System.out.println(todaysDate);
            if (todaysDate.before(dateEXP)) {
                return true;
            } else {
                starLabel.setVisible(true);
                alertPopup("Card may be expired check to make sure the expiration is valid", "Expired", "Expired card or mistake");
                return false;
            }
            } else {
            starLabel.setVisible(true);
            alertPopup("Please use the proper format MM/YY", "Improper Format","Please use Proper format");
            return false;
        }
    }
}
