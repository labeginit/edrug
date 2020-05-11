package model;

import java.sql.Date;
import java.util.List;

public class PrescriptionParent {
    private int id;
    private Patient patient;
    private Doctor doctor;
    private Date date;
    private String diagnosis;
    private Medicine medicine;
    private int quantityPrescribed;
    private int quantityConsumed;
    private String instructions;
    private List<PrescriptionLine> specification;
    private String doctorName;

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantityPrescribed() {
        return quantityPrescribed;
    }

    public int getQuantityConsumed() {
        return quantityConsumed;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setQuantityPrescribed(int quantityPrescribed) {
        this.quantityPrescribed = quantityPrescribed;
    }

    public void setQuantityConsumed(int quantityConsumed) {
        this.quantityConsumed = quantityConsumed;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getInstructions() {
        return instructions;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public List<PrescriptionLine> getSpecification() {
        return specification;
    }

    public int getArticle(){
        return this.getMedicine().getArticleNo();
    }

    public String getName(){
        return this.getMedicine().getName();
    }

    public String getDoctorName() {
        return this.doctor.getFirstName() + " " + this.doctor.getLastName();
    }

    @Override
    public String toString() {
        return "PrescriptionParent{" +
                "id=" + getId() +
                ", date=" + getDate() +
                ", diagnosis='" + getDiagnosis() +
                '}';
    }
}
