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
                        PrescriptionFree med = new PrescriptionFree(resultSet.getInt("article"), resultSet.getInt("Product_group_id"), resultSet.getString("name"), resultSet.getString("producer"), resultSet.getString("description"), resultSet.getString("packageSize"), resultSet.getInt("quantityAvailable"), resultSet.getDouble("Price"), resultSet.getString("searchTerms"), resultSet.getBoolean("active"));
                        medList.add(med);
                    } else {
                        OnPrescription med = new OnPrescription(resultSet.getInt("article"), resultSet.getInt("Product_group_id"), resultSet.getString("name"), resultSet.getString("producer"), resultSet.getString("description"), resultSet.getString("packageSize"), resultSet.getInt("quantityAvailable"), resultSet.getDouble("Price"), resultSet.getString("searchTerms"), resultSet.getBoolean("active"));
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
            resultSet = common.retrieveSet("select concat(concat(a.gr_name, '/', b.gr_name), '/', c.gr_name) as path, c.id from Product_group b \n" +
                    "inner join Product_group a on a.id = b.Product_group_id\n" +
                    "inner join Product_group c on b.id = c.Product_group_id;");
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
