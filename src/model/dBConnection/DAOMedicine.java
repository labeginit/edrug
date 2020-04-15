package model.dBConnection;

import model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DAOMedicine {
    private Statement statement;
    private ResultSet resultSet;
    private List<Medicine> medList;
    DAOCommon common = new DAOCommon();


    public List<Medicine> retrieveMedicineList(String query) {
        try {
            resultSet = common.retrieveSet(query);
            if (resultSet != null) {
                while (resultSet.next()) {
                    if (!resultSet.getBoolean(2)) {
                        PrescriptionFree med = new PrescriptionFree(resultSet.getInt(1), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getDouble(8), resultSet.getString(9), resultSet.getInt(10));
                        medList.add(med);
                    } else {
                        OnPrescription med = new OnPrescription(resultSet.getInt(1), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getDouble(8), resultSet.getString(9), resultSet.getInt(10));
                        medList.add(med);
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with ResultSet!");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            return medList;
        }
    }

}
