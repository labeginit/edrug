package model.dBConnection;

import model.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class DAOPrescription {
    private int id;
    private Patient patient;
    private Doctor doctor;
    private Date date;
    private String diagnosis;
    private List<PrescriptionLines> specification;
    private Medicine medicine;
    private int quantity;
    private String instructions;
    private int linesAffected = 0;
    DAOCommon common = new DAOCommon();

    public int addPrescription(Prescription prescription) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                if (prescription != null) {
                    id = prescription.getId();
                    String patientSSN = prescription.getPatient().getSsn();
                    String doctorSSN = prescription.getDoctor().getSsn();
                    date = prescription.getDate();
                    diagnosis = prescription.getDiagnosis();

                    String query = "INSERT INTO `edrugs_test`.`Prescription` (`id`, `patient_ssn`, `user_ssn`, `date`, `diagnosis`) VALUES (?, ?, ?, ?, ?);";
                    linesAffected = common.insertPrescriptionHeader(query, id, patientSSN, doctorSSN, date, diagnosis);
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
