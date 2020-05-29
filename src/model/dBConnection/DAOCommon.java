package model.dBConnection;

import model.Order;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOCommon {
    private ResultSet resultSet = null;
    private int linesAffected = 0;

    protected ResultSet retrieveSet(String queryString, String... params) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                PreparedStatement prepStmt = DBConnection.getConnection().prepareStatement(queryString);
                for (int i = 0; i < params.length; i++) {
                    prepStmt.setString(i+1, params[i]);
                }
                return resultSet = prepStmt.executeQuery();
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error when executing statement!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultSet;
    }

    protected ResultSet retrieveSet(String queryString) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                PreparedStatement prepStmt = DBConnection.getConnection().prepareStatement(queryString);
                return resultSet = prepStmt.executeQuery();
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error when executing statement!");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return resultSet;
    }

    protected int insertUser(String queryString, String ssn, int type, String firstName, String lastName, Date birthDate, String zipCode, String city, String address, String email, String phoneNumber, String password, int isActive) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                PreparedStatement prepStmt = DBConnection.getConnection().prepareStatement(queryString);
                prepStmt.setString(1, ssn);
                prepStmt.setInt(2, type);
                prepStmt.setString(3, firstName);
                prepStmt.setString(4, lastName);
                prepStmt.setDate(5, birthDate);
                prepStmt.setString(6, zipCode);
                prepStmt.setString(7, city);
                prepStmt.setString(8, address);
                prepStmt.setString(9, email);
                prepStmt.setString(10, phoneNumber);
                prepStmt.setString(11, password);
                prepStmt.setInt(12, isActive);

                linesAffected = prepStmt.executeUpdate();
                prepStmt.close();
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error when executing statement!");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return linesAffected;
    }

    protected int updateUser(String queryString, String ssn, String firstName, String lastName, Date birthDate, String zipCode, String city, String address, String email, String phoneNumber, int isActive) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                PreparedStatement prepStmt = DBConnection.getConnection().prepareStatement(queryString);
                prepStmt.setString(1, firstName);
                prepStmt.setString(2, lastName);
                prepStmt.setDate(3, birthDate);
                prepStmt.setString(4, zipCode);
                prepStmt.setString(5, city);
                prepStmt.setString(6, address);
                prepStmt.setString(7, email);
                prepStmt.setString(8, phoneNumber);
                prepStmt.setInt(9, isActive);
                prepStmt.setString(10, ssn);
                linesAffected = prepStmt.executeUpdate();
                prepStmt.close();
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error when executing statement!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return linesAffected;
    }

    protected int updateRecordStr(String queryString, String... params) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                PreparedStatement prepStmt = DBConnection.getConnection().prepareStatement(queryString);
                for (int i = 0; i < params.length; i++) {
                    prepStmt.setString(i+1, params[i]);
                }
                linesAffected = prepStmt.executeUpdate();
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error when executing statement!");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return linesAffected;
    }

    protected int updateRecordBool(String queryString, Boolean... params) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                PreparedStatement prepStmt = DBConnection.getConnection().prepareStatement(queryString);
                for (int i = 0; i < params.length; i++) {
                    prepStmt.setBoolean(i+1, params[i]);
                }
                linesAffected = prepStmt.executeUpdate();
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error when executing statement!");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return linesAffected;
    }

    protected int insertMedicine(String queryString, int article, int prodGroupId, int onPrescription, String name, String producer, String description, String packageSize, int quantityAvailable, double price, String searchTerms, int isActive) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                PreparedStatement prepStmt = DBConnection.getConnection().prepareStatement(queryString);
                prepStmt.setInt(1, article);
                prepStmt.setInt(2, prodGroupId);
                prepStmt.setInt(3, onPrescription);
                prepStmt.setString(4, name);
                prepStmt.setString(5, producer);
                prepStmt.setString(6, description);
                prepStmt.setString(7, packageSize);
                prepStmt.setInt(8, quantityAvailable);
                prepStmt.setDouble(9, price);
                prepStmt.setString(10, searchTerms);
                prepStmt.setInt(11, isActive);
                linesAffected = prepStmt.executeUpdate();
                prepStmt.close();
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error when executing statement!");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return linesAffected;
    }

    protected int updateMedicine(String queryString, int article, int prodGroupId, int onPrescription, String name, String producer, String description, String packageSize, int quantityAvailable, double price, String searchTerms, int isActive) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                PreparedStatement prepStmt = DBConnection.getConnection().prepareStatement(queryString);

                prepStmt.setInt(1, prodGroupId);
                prepStmt.setInt(2, onPrescription);
                prepStmt.setString(3, name);
                prepStmt.setString(4, producer);
                prepStmt.setString(5, description);
                prepStmt.setString(6, packageSize);
                prepStmt.setInt(7, quantityAvailable);
                prepStmt.setDouble(8, price);
                prepStmt.setString(9, searchTerms);
                prepStmt.setInt(10, isActive);
                prepStmt.setInt(11, article);
                linesAffected = prepStmt.executeUpdate();
                prepStmt.close();
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error when executing statement!");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return linesAffected;
    }

    protected int insertPrescriptionHeader(String queryString, int id, String patientSSN, String doctorSSN, Date startDate, String diagnosis, Date endDate) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                PreparedStatement prepStmt = DBConnection.getConnection().prepareStatement(queryString);
                prepStmt.setInt(1, id);
                prepStmt.setString(2, patientSSN);
                prepStmt.setString(3, doctorSSN);
                prepStmt.setDate(4, startDate);
                prepStmt.setString(5, diagnosis);
                prepStmt.setDate(6, endDate);

                linesAffected = prepStmt.executeUpdate();
                prepStmt.close();
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error when executing statement!");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return linesAffected;
    }

    protected int insertPrescriptionLine(String queryString, int prescId, String patientSSN, int article, int quantityPrescribed, String instructions) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                PreparedStatement prepStmt = DBConnection.getConnection().prepareStatement(queryString);
                prepStmt.setInt(1, prescId);
                prepStmt.setString(2, patientSSN);
                prepStmt.setInt(3, article);
                prepStmt.setInt(4, quantityPrescribed);
                prepStmt.setString(5, instructions);

                linesAffected = prepStmt.executeUpdate();
                prepStmt.close();
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error when executing statement!");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return linesAffected;
    }
    protected void deletePrescriptionHasMedicine(String queryString, int id, String patientSsn, int article) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                PreparedStatement prepStmt = DBConnection.getConnection().prepareStatement(queryString);
                prepStmt.setInt(1, id);
                prepStmt.setString(2, patientSsn);
                if (article != 0) {
                    prepStmt.setInt(3, article);
                }
                prepStmt.executeUpdate();
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error when executing statement!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected void deletePrescriptionHasMedicine(String queryString, int id, String patientSsn) {
        deletePrescriptionHasMedicine(queryString, id, patientSsn, 0);
    }

    protected void deletePrescription(String queryString, int id, String patientSsn, String currentUserSsn) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                PreparedStatement prepStmt = DBConnection.getConnection().prepareStatement(queryString);
                prepStmt.setInt(1, id);
                prepStmt.setString(2, patientSsn);
                if (!currentUserSsn.isEmpty()) {
                    prepStmt.setString(3, currentUserSsn);
                }
                prepStmt.executeUpdate();
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error when executing statement!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected void deletePrescription(String queryString, int id, String patientSsn) {
        deletePrescription(queryString, id, patientSsn, "");
    }

    protected int updatePrescriptionLine(String queryString, int quantityConsumed, int prescId, String patientSsn, int article) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                PreparedStatement prepStmt = DBConnection.getConnection().prepareStatement(queryString);

                prepStmt.setInt(1, quantityConsumed);
                prepStmt.setInt(2, prescId);
                prepStmt.setString(3, patientSsn);
                prepStmt.setInt(4, article);
                linesAffected = prepStmt.executeUpdate();
                prepStmt.close();
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error when executing statement!");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return linesAffected;
    }

    protected int updateMedicineQuantity(String queryString, int quantityAvailable, int article) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                PreparedStatement prepStmt = DBConnection.getConnection().prepareStatement(queryString);

                prepStmt.setInt(1, quantityAvailable);
                prepStmt.setInt(2, article);

                linesAffected = prepStmt.executeUpdate();
                prepStmt.close();
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error when executing statement!");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return linesAffected;
    }
    protected int insertPharmacy(String queryString, int storeId, String storeName, String address, String zipCode, String city, String phoneNumber, String email) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                PreparedStatement prepStmt = DBConnection.getConnection().prepareStatement(queryString);
                prepStmt.setInt(1, storeId);
                prepStmt.setString(2, storeName);
                prepStmt.setString(3, address);
                prepStmt.setString(4, zipCode);
                prepStmt.setString(5, city);
                prepStmt.setString(6, phoneNumber);
                prepStmt.setString(7, email);

                linesAffected = prepStmt.executeUpdate();
                prepStmt.close();
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error when executing statement!");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return linesAffected;
    }
    protected int updatePharmacy(String queryString, int storeId, String storeName, String address, String zipCode, String city, String phoneNumber, String email) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                PreparedStatement prepStmt = DBConnection.getConnection().prepareStatement(queryString);
                prepStmt.setInt(1, storeId);
                prepStmt.setString(2, storeName);
                prepStmt.setString(3, address);
                prepStmt.setString(4, zipCode);
                prepStmt.setString(5, city);
                prepStmt.setString(6, phoneNumber);
                prepStmt.setString(7, email);

                linesAffected = prepStmt.executeUpdate();
                prepStmt.close();
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error when executing statement!");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return linesAffected;
    }
    protected int removePharmacy(String queryString, int storeId) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                PreparedStatement prepStmt = DBConnection.getConnection().prepareStatement(queryString);
                prepStmt.setInt(1, storeId);

                linesAffected = prepStmt.executeUpdate();
                prepStmt.close();
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error when executing statement!");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return linesAffected;
    }
    protected int insertOrderHasMedicine(String queryString, int id, String ssn, int articleNo, double price, int quantity) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                PreparedStatement prepStmt = DBConnection.getConnection().prepareStatement(queryString);
                prepStmt.setInt(1, id);
                prepStmt.setString(2, ssn);
                prepStmt.setInt(3, articleNo);
                prepStmt.setDouble(4, price);
                prepStmt.setInt(5, quantity);

                linesAffected = prepStmt.executeUpdate();
                prepStmt.close();
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error when executing statement!");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return linesAffected;
    }


    protected int insertOrderPharmacy(String queryString, int id, String userSSN, Date date, Order.DeliveryMethod deliveryMethod, Order.PaymentMethod paymentMethod, double totalSum, double totalVAT, Integer pharmacyId ) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                PreparedStatement prepStmt = DBConnection.getConnection().prepareStatement(queryString);
                prepStmt.setInt(1, id);
                prepStmt.setString(2, userSSN);
                prepStmt.setDate(3, date);
                prepStmt.setString(4,deliveryMethod.toString());
                prepStmt.setString(5,paymentMethod.toString());
                prepStmt.setDouble(6, totalSum);
                prepStmt.setDouble(7,totalVAT);
                prepStmt.setInt(8, pharmacyId);


                linesAffected = prepStmt.executeUpdate();
                prepStmt.close();
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error when executing statement!");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return linesAffected;
    }
    protected int insertDelivery(String queryString, int id, String firstName, String lastName, String address, String city, String zipCode, String phoneNumber, Date shipDate) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                PreparedStatement prepStmt = DBConnection.getConnection().prepareStatement(queryString);
                prepStmt.setInt(1, id);
                prepStmt.setString(2, firstName);
                prepStmt.setString(3, lastName);
                prepStmt.setString(4, address);
                prepStmt.setString(5, city);
                prepStmt.setString(6,zipCode);
                prepStmt.setString(7, phoneNumber);
                prepStmt.setDate(8,shipDate);

                linesAffected = prepStmt.executeUpdate();
                prepStmt.close();
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error when executing statement!");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return linesAffected;
    }
}
