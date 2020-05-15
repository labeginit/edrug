package model.dBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOOrder {
    private ResultSet resultSet = null;
    private int id;
    private DAOCommon common = new DAOCommon();

    protected int retrieveLastOrderId() {
        id = 0;
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                resultSet = common.retrieveSet("SELECT MAX(id) AS maxId FROM `edrugs_test`.`Order`;");
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
}
