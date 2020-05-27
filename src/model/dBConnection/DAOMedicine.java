package model.dBConnection;

import model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOMedicine {
    private int id;
    private String grName;
    private String path;
    private ProdGroup group = null;
    private ResultSet resultSet = null;
    private final List<Medicine> medList = new ArrayList<>();
    private final List<ProdGroup> groups = new ArrayList<>();
    private final DAOCommon common = new DAOCommon();
    private String value1;
    private String value2;
    private int value3;
    private int linesAffected = 0;
    private Medicine medicine;
    private int articleNo;
    private boolean onPrescription;
    private String name;
    private String producer;
    private String packageSize;
    private String description;
    private int quantity;
    private double price;
    private String searchTerms;
    private int groupId;
    private boolean isActive;

    protected List<Medicine> retrieveMedicineList(boolean onPrescription, boolean active) {
        medList.clear();
        if (onPrescription) {
            value1 = "1";
        } else {
            value1 = "0";
        }
        if (active) {
            value2 = "1";
        } else {
            value2 = "0";
        }
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                resultSet = common.retrieveSet("SELECT * FROM Medicine where onPrescription = ? and active = ?;", value1, value2);
                if (resultSet != null) {
                    while (resultSet.next()) {
                        medList.add(createMedicineObjects(resultSet));
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with ResultSet!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return medList;
        }
    }

    protected List<Medicine> retrieveMedicineList(boolean onPrescription) {
        medList.clear();
        if (onPrescription) {
            value1 = "1";
        } else {
            value1 = "0";
        }

        try {
            if (!DBConnection.dbConnection.isClosed()) {
                resultSet = common.retrieveSet("SELECT * FROM Medicine WHERE active = 1 AND onPrescription = ?", value1);
                if (resultSet != null) {
                    while (resultSet.next()) {
                        medList.add(createMedicineObjects(resultSet));
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with ResultSet!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return medList;
        }
    }

    protected List<Medicine> retrieveMedicineList() {
        medList.clear();
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                resultSet = common.retrieveSet("SELECT * FROM Medicine;");
                if (resultSet != null) {
                    while (resultSet.next()) {
                        medList.add(createMedicineObjects(resultSet));
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with ResultSet!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return medList;
        }
    }

    protected Medicine createMedicineObjects(ResultSet resultSet) throws Exception {
        Medicine med = null;
        if (resultSet != null) {
            if (!resultSet.getBoolean("onPrescription")) {
                med = new PrescriptionFree(resultSet.getInt("article"), resultSet.getInt("Product_group_id"), resultSet.getString("name"), resultSet.getString("producer"), resultSet.getString("package_size"), resultSet.getString("description"), resultSet.getInt("quantity_available"), resultSet.getDouble("price"), resultSet.getString("search_terms"), resultSet.getBoolean("active"));
            } else {
                med = new OnPrescription(resultSet.getInt("article"), resultSet.getInt("Product_group_id"), resultSet.getString("name"), resultSet.getString("producer"), resultSet.getString("package_size"), resultSet.getString("description"), resultSet.getInt("quantity_available"), resultSet.getDouble("price"), resultSet.getString("search_terms"), resultSet.getBoolean("active"));
            }
        }
        return med;
    }

    protected Medicine getMedicine(int article) {
        Medicine temp = null;
        String query = "SELECT * FROM Medicine where article = ?;";
        try {
            temp = retrieveMedicine(query, article);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return temp;
    }

    protected int retrieveLastArticle() {
        id = 0;
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                resultSet = common.retrieveSet("SELECT MAX(article) AS maxId FROM `Medicine`;");
            }
            if (resultSet != null) {
                while (resultSet.next()) {
                    return id = resultSet.getInt("maxId");
                }
            } else return id;

        } catch (SQLException ex) {
            System.out.println("Error while working with ResultSet!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return id;
        }
    }

    protected Medicine retrieveMedicine(String query, int article) {
        medicine = null;
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                resultSet = common.retrieveSet(query, String.valueOf(article));
                if (resultSet != null) {
                    if (resultSet.first()) {
                        medicine = createMedicineObjects(resultSet);
                    }
                } else {
                    System.out.println("Empty resultSet");
                    return medicine;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with ResultSet!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                resultSet.close();
            }catch (Exception ex) {
                ex.printStackTrace();
            }
            return medicine;
        }
    }

    protected int addMedicine(Medicine medicine) {
        int value1;
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                if (medicine != null) {
                    articleNo = medicine.getArticleNo();
                    onPrescription = medicine.isOnPrescription();
                    name = medicine.getName();
                    producer = medicine.getProducer();
                    packageSize = medicine.getPackageSize();
                    description = medicine.getDescription();
                    quantity = medicine.getQuantity();
                    price = medicine.getPrice();
                    searchTerms = medicine.getSearchTerms();
                    groupId = medicine.getGroup();
                    isActive = medicine.getActive();
                    if (onPrescription) {
                        value1 = 1;
                    } else {
                        value1 = 0;
                    }

                    if (isActive) {
                        value3 = 1;
                    } else {
                        value3 = 0;
                    }
                    String query = "INSERT INTO `Medicine` (`article`, `Product_group_id`, `onPrescription`, `name`, `producer`, `description`, `package_size`, `quantity_available`, `price`, `search_terms`, `active`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
                    linesAffected = common.insertMedicine(query, articleNo, groupId, value1, name, producer, description, packageSize, quantity, price, searchTerms, value3);
                } else {
                    throw new NullPointerException("The user object is null");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with statement!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return linesAffected;
        }
    }

    protected int updateMedicine(Medicine medicine) {
        int value1;
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                if (medicine != null) {
                    articleNo = medicine.getArticleNo();
                    onPrescription = medicine.isOnPrescription();
                    name = medicine.getName();
                    producer = medicine.getProducer();
                    packageSize = medicine.getPackageSize();
                    description = medicine.getDescription();
                    quantity = medicine.getQuantity();
                    price = medicine.getPrice();
                    searchTerms = medicine.getSearchTerms();
                    groupId = medicine.getGroup();
                    isActive = medicine.getActive();
                    if (onPrescription) {
                        value1 = 1;
                    } else {
                        value1 = 0;
                    }
                    if (isActive) {
                        value3 = 1;
                    } else {
                        value3 = 0;
                    }
                    String query = "UPDATE `Medicine` SET `Product_group_id` = ?, `onPrescription` = ?, `name` = ?, `producer` = ?, `description` = ?, `package_size` = ?, `quantity_available` = ?, `price` = ?, `search_terms` = ?, `active` = ? WHERE (`article` = ?);";
                    linesAffected = common.updateMedicine(query, articleNo, groupId, value1, name, producer, description, packageSize, quantity, price, searchTerms, value3);
                } else {
                    throw new NullPointerException("The user object is null");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with statement!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return linesAffected;
        }
    }

    protected List<ProdGroup> retrieveProductGroupList() {
        groups.clear();
        try {
            resultSet = common.retrieveSet("SELECT CONCAT(CONCAT(c.gr_name, '/', b.gr_name), '/', a.gr_name) AS path, a.id, a.gr_name FROM Product_group a \n" +
                    "INNER JOIN Product_group b ON b.id = a.Product_group_id \n" +
                    "INNER JOIN Product_group c ON c.id = b.Product_group_id");
            if (resultSet != null) {
                while (resultSet.next()) {
                    groups.add(createGroupObject(resultSet));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with ResultSet!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return groups;
        }
    }

    protected ProdGroup retrieveProductGroup(int groupId) {
        group = null;
        try {
            resultSet = common.retrieveSet("SELECT CONCAT(CONCAT(c.gr_name, '/', b.gr_name), '/', a.gr_name) AS path, a.id, a.gr_name FROM Product_group a \n" +
                    "INNER JOIN Product_group b ON b.id = a.Product_group_id \n" +
                    "INNER JOIN Product_group c ON c.id = b.Product_group_id\n" +
                    "WHERE a.id =  ?;", String.valueOf(groupId));
            if (resultSet != null) {
                while (resultSet.next()) {
                    group = createGroupObject(resultSet);
                }
            } else throw new NullPointerException("There is no group with id = " + groupId);
        } catch (SQLException ex) {
            System.out.println("Error while working with ResultSet!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return group;
        }
    }

    private ProdGroup createGroupObject(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt("id");
        grName = resultSet.getString("gr_name");
        path = resultSet.getString("path");
        group = new ProdGroup(id, grName, path);
        return group;
    }

    // here we expect full path like "Drugs/Digestion & Nausea/Constipation"
    protected List<Medicine> retrieveMedicineByProductGroupPath(String fullPath) {
        medList.clear();
        try {
            resultSet = common.retrieveSet("select * from Medicine as m \n" +
                    "join groupPath as p on m.Product_group_id = p.id\n" +
                    "WHERE active = 1 AND p.path = ?;", fullPath);
            if (resultSet != null) {
                while (resultSet.next()) {
                    medList.add(createMedicineObjects(resultSet));
                }
            } else throw new NullPointerException("There is no medicine belonging to group = " + fullPath);
        } catch (SQLException ex) {
            System.out.println("Error while working with ResultSet!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return medList;
        }
    }

    protected List<Medicine> retrieveMedicineByMaxPrice(double maxPrice) {
        medList.clear();
        try {
            resultSet = common.retrieveSet("SELECT * from Medicine WHERE active = 1 AND price <= ?", String.valueOf(maxPrice));
            if (resultSet != null) {
                while (resultSet.next()) {
                    medList.add(createMedicineObjects(resultSet));
                }
            } else throw new NullPointerException("There is no medicine with price less than " + maxPrice);
        } catch (SQLException ex) {
            System.out.println("Error while working with ResultSet!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return medList;
        }
    }
    protected int updateQuantity(Medicine medicine) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                if (medicine != null) {

                    articleNo = medicine.getArticleNo();
                    quantity = medicine.getQuantity();

                    String query = "UPDATE `Medicine` SET `quantity_available` = ? WHERE (`article` = ?);";
                    linesAffected = common.updateMedicineQuantity(query, quantity, articleNo);
                } else {
                    throw new NullPointerException("The user object is null");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with statement!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return linesAffected;
        }
    }

}
