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

/*  WIP
    protected List<Prescription> getPrescriptionList(Doctor doctor) {
        List<Prescription> prescList = null;
        String query = "select id, patient_ssn, user_ssn, date, diagnosis, article, quantity, instructions from Prescription \n" +
                "join Prescription_has_Medicine on id = prescription_id and prescription_patient_ssn = patient_ssn\n" +
                "where user_ssn = ? order by id, prescription_patient_ssn;";
        prescription = null;
        specification = null;
        try {
            if (!DBConnection.dbConnection.isClosed()) {
                resultSet = common.retrieveSet(query, doctor.getSsn());
                if (resultSet != null) {
                    while (resultSet.next()) {
                        id = resultSet.getInt("id");
                      //  if (id == )
                        this.doctorSSN = resultSet.getString("user_ssn");
                        patientSSN = resultSet.getString("patient_ssn");
                        date = resultSet.getDate("date");
                        diagnosis = resultSet.getString("diagnosis");
                        prescription = new Prescription(id, (Doctor) user.getUser(doctorSSN), (Patient) user.getUser(patientSSN), date, diagnosis, specification);
                        prescList.add(prescription);

                        int prescId = resultSet.getInt("prescription_id");
                        String prescPatientSSN = resultSet.getString("prescription_patient_ssn");
                        article = resultSet.getInt("article");
                        quantity = resultSet.getInt("quantity");
                        instructions = resultSet.getString("instructions");

                        line = new PrescriptionLine(id, (Patient) user.getUser(prescPatientSSN), (Medicine) med.getMedicine(article), quantity, instructions);
                        specification.add(line);

                    }
                } else {
                    System.out.println("Empty resultSet");
                    return prescList;
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
            return user;
        }
    }

    private PrescriptionLine createLines(ResultSet resultSet) throws Exception {
        id = resultSet.getInt("id");
        patient =
        article = element.getMedicine().getArticleNo();
        quantity = element.getQuantity();
        instructions = element.getInstructions();
        String queryLine = "INSERT INTO `edrugs_test`.`Prescription_has_Medicine` (`prescription_id`, `prescription_patient_ssn`, `article`, `quantity_prescribed`, `instructions`) VALUES (?, ?, ?, ?, ?);";
        linesAffected = linesAffected + common.insertPrescriptionLine(queryLine, id, patientSSN, article, quantity, instructions);
        return
    }*/

}
