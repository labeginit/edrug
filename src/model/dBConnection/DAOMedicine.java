package model.dBConnection;

import model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOMedicine {
    private int id;
    private String name;
    private String path;
    private ProdGroup group = null;
    private ResultSet resultSet;
    private static List<Medicine> medList = new ArrayList<>();
    private static List<ProdGroup> groups = new ArrayList<>();
    private DAOCommon common = new DAOCommon();
    private String value1;
    private String value2;

    public List<Medicine> retrieveMedicineList(boolean onPrescription, boolean active) {
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
                        if (!resultSet.getBoolean("onPrescription")) {
                            PrescriptionFree med = new PrescriptionFree(resultSet.getInt("article"), resultSet.getInt("Product_group_id"), resultSet.getString("name"), resultSet.getString("producer"), resultSet.getString("package_size"), resultSet.getString("description"), resultSet.getInt("quantity_available"), resultSet.getDouble("price"), resultSet.getString("search_terms"), resultSet.getBoolean("active"));
                            medList.add(med);
                        } else {
                            OnPrescription med = new OnPrescription(resultSet.getInt("article"), resultSet.getInt("Product_group_id"), resultSet.getString("name"), resultSet.getString("producer"), resultSet.getString("package_size"), resultSet.getString("description"), resultSet.getInt("quantity_available"), resultSet.getDouble("price"), resultSet.getString("search_terms"), resultSet.getBoolean("active"));
                            medList.add(med);
                        }
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

    public List<Medicine> retrieveMedicineList(boolean onPrescription) {
        if (onPrescription) {
            value1 = "1";
        } else {
            value1 = "0";
        }

        try {
            if (!DBConnection.dbConnection.isClosed()) {
                resultSet = common.retrieveSet("SELECT * FROM Medicine where onPrescription = ?", value1);
                if (resultSet != null) {
                    createObjects(resultSet);
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

    public List<Medicine> retrieveMedicineList() {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                resultSet = common.retrieveSet("SELECT * FROM Medicine;");
                if (resultSet != null) {
                    createObjects(resultSet);
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

    private void createObjects(ResultSet resultSet) throws Exception {
        if (resultSet != null) {
            while (resultSet.next()) {
                if (!resultSet.getBoolean("onPrescription")) {
                    PrescriptionFree med = new PrescriptionFree(resultSet.getInt("article"), resultSet.getInt("Product_group_id"), resultSet.getString("name"), resultSet.getString("producer"), resultSet.getString("package_size"), resultSet.getString("description"), resultSet.getInt("quantity_available"), resultSet.getDouble("price"), resultSet.getString("search_terms"), resultSet.getBoolean("active"));
                    medList.add(med);
                } else {
                    OnPrescription med = new OnPrescription(resultSet.getInt("article"), resultSet.getInt("Product_group_id"), resultSet.getString("name"), resultSet.getString("producer"), resultSet.getString("package_size"), resultSet.getString("description"), resultSet.getInt("quantity_available"), resultSet.getDouble("price"), resultSet.getString("search_terms"), resultSet.getBoolean("active"));
                    medList.add(med);
                }
            }
        }
    }

    public List<ProdGroup> retrieveProductGroupList() {
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

    public ProdGroup retrieveProductGroup(int groupId) {
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
        name = resultSet.getString("gr_name");
        path = resultSet.getString("path");
        group = new ProdGroup(id, name, path);
        return group;
    }

}
