package model.dBConnection;

import model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOMedicine {
    private Statement statement;
    private ResultSet resultSet;
    private List<Medicine> medList  = new ArrayList<>();;
    private List<String> strings  = new ArrayList<>();;
    DAOCommon common = new DAOCommon();


    public List<Medicine> retrieveMedicineList(String query) {
        try {
            resultSet = common.retrieveSet(query);
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
        } catch (SQLException ex) {
            System.out.println("Error while working with ResultSet!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return medList;
        }
    }

    public List<String> retrieveProductGroupList(){
        String path = "";
        try{
            resultSet = common.retrieveSet("SELECT CONCAT(CONCAT(c.gr_name, '/', b.gr_name), '/', a.gr_name) AS path, a.id FROM Product_group a \n" +
                    "INNER JOIN Product_group b ON b.id = a.Product_group_id \n" +
                    "INNER JOIN Product_group c ON c.id = b.Product_group_id;");
            if (resultSet != null) {
                while (resultSet.next()) {
                    path = resultSet.getString("path");
                    strings.add(path);
                }
            }

        } catch (SQLException ex) {
        System.out.println("Error while working with ResultSet!");
        ex.printStackTrace();
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {
        return strings;
    }
    }

}
