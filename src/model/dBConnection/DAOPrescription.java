package model.dBConnection;

import model.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOPrescription {
    private Prescription prescription;
    private PrescriptionLine line;
    private ResultSet resultSet = null;
    private int id;
    private Patient patient;
    private String patientSSN;
    private Doctor doctor;
    private String doctorSSN;
    private Date startdate;
    private Date endDate;
    private String diagnosis;
    private List<PrescriptionLine> specification = new ArrayList<>();
    private Medicine medicine;
    private int article;
    private int quantityPrescribed;
    private int quantityConsumed;
    private String instructions;
    private int linesAffected = 0;
    private DAOCommon common = new DAOCommon();
    private DAOUser user = new DAOUser();
    private DAOMedicine med = new DAOMedicine();

    protected int addPrescription(Prescription prescription) {
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                if (prescription != null) {
                    id = prescription.getId();
                    patientSSN = prescription.getPatient().getSsn();
                    doctorSSN = prescription.getDoctor().getSsn();
                    startdate = prescription.getStartDate();
                    endDate = prescription.getEndDate();
                    diagnosis = prescription.getDiagnosis();

                    String queryHeader = "INSERT INTO `edrugs_test`.`Prescription` (`id`, `patient_ssn`, `user_ssn`, `date`, `end_date`, `diagnosis`) VALUES (?, ?, ?, ?, ?, ?);";
                    linesAffected = common.insertPrescriptionHeader(queryHeader, id, patientSSN, doctorSSN, startdate, diagnosis);

                    specification = prescription.getSpecification();
                    for (PrescriptionLine element: specification) {
                        article = element.getMedicine().getArticleNo();
                        quantityPrescribed = element.getQuantityPrescribed();
                        instructions = element.getInstructions();
                        String queryLine = "INSERT INTO `edrugs_test`.`Prescription_has_Medicine` (`prescription_id`, `prescription_patient_ssn`, `article`, `quantity_prescribed`, `quantity_consumed`, `instructions`) VALUES (?, ?, ?, ?, 0, ?);";
                        linesAffected = linesAffected + common.insertPrescriptionLine(queryLine, id, patientSSN, article, quantityPrescribed, instructions);
                    }
                } else {
                    throw new NullPointerException("The prescription object is null");
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

protected List<PrescriptionLine> retrievePrescriptionLines(int prescriptionId, User currentUser) {
    specification.clear();
    try {
        if (!DBConnection.dbConnection.isClosed()) {
            if (currentUser instanceof Patient) {
                resultSet = common.retrieveSet("SELECT * FROM Prescription_has_Medicine WHERE prescription_id = ? and prescription_patient_ssn = ?;", String.valueOf(prescriptionId), currentUser.getSsn());
            } else if (currentUser instanceof Doctor){
                resultSet = common.retrieveSet("SELECT prescription_id, prescription_patient_ssn, article, quantity_prescribed, quantity_consumed, instructions FROM Prescription_has_Medicine " +
                        "JOIN Prescription ON id = prescription_id AND prescription_patient_ssn = patient_ssn " +
                        "WHERE id = ? AND user_ssn = ? " +
                        "ORDER BY id, prescription_patient_ssn;", String.valueOf(prescriptionId), currentUser.getSsn());
            } else {
                resultSet = common.retrieveSet("SELECT * FROM Prescription_has_Medicine WHERE prescription_id = ?;", String.valueOf(prescriptionId));
            }

            if (resultSet != null) {
                while (resultSet.next()) {
                    specification.add(createPrescriptionLineObject(resultSet));
                }
            }
        }
    } catch (SQLException ex) {
        System.out.println("Error while working with ResultSet!");
        ex.printStackTrace();
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {
        return specification;
    }
}

    private PrescriptionLine createPrescriptionLineObject(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt("prescription_id");
        patient = (Patient) user.getUser(resultSet.getString("prescription_patient_ssn"));
        medicine = med.getMedicine(resultSet.getInt("article"));
        quantityPrescribed = resultSet.getInt("quantity_prescribed");
        quantityConsumed = resultSet.getInt("quantity_consumed");
        instructions = resultSet.getString("instructions");
        return new PrescriptionLine(id, patient, medicine, quantityPrescribed, quantityConsumed, instructions);
    }

    protected List<Prescription> getPrescriptionList(User currentUser) {
        String query = "";
        List<Prescription> prescList = new ArrayList<>();
        if (currentUser instanceof Patient) {
            query = "SELECT * from Prescription WHERE patient_ssn = ?;";
        } else if (currentUser instanceof Doctor){
            query = "SELECT * from Prescription WHERE user_ssn = ?;";
        } else {
            query = "SELECT * from Prescription;";  //possible bug here since we are not using the parameter
        }
        prescription = null;
        specification.clear();
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                resultSet = common.retrieveSet(query, currentUser.getSsn());
                if (resultSet != null) {
                    while (resultSet.next()) {
                        prescList.add(createPrescriptionObject(currentUser, resultSet));
                    }
                } else {
                    System.out.println("Empty resultSet");
                    return prescList;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with ResultSet!");
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.getSuppressed();
        } finally {
            try {
                resultSet.close();
            }catch (Exception ex) {
                ex.getSuppressed();
            }
            return prescList;
        }
    }

    private Prescription createPrescriptionObject(User currentUser, ResultSet resultSet) throws SQLException{
    Prescription prescription;
        id = resultSet.getInt("id");
        startdate = resultSet.getDate("date");
        endDate = resultSet.getDate("end_date");
        diagnosis = resultSet.getString("diagnosis");
        if (currentUser instanceof Patient) {
            doctor = (Doctor) user.getUser(resultSet.getString("user_ssn"));
            prescription = new Prescription(id, doctor, (Patient) currentUser, startdate, endDate, diagnosis, specification);
        } else if (currentUser instanceof Doctor){
            patient = (Patient) user.getUser(resultSet.getString("patient_ssn"));
            prescription = new Prescription(id, (Doctor) currentUser, patient, startdate, endDate, diagnosis, specification);
        } else {
            doctor = (Doctor) user.getUser(resultSet.getString("user_ssn"));
            patient = (Patient) user.getUser(resultSet.getString("patient_ssn"));
            prescription = new Prescription(id, doctor, patient, startdate, endDate, diagnosis, specification);
        }
        return prescription;
    }
}
