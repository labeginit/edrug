package model;

import java.sql.Date;
import java.util.List;

public class Prescription {
    private int id;
    private Patient patient;
    private Doctor doctor;
    private Date startDate;
    private Date endDate;
    private String diagnosis;
    private List<PrescriptionLine> specification;

    public Prescription(int id, Doctor doctor, Patient patient, Date startDate, Date endDate,  String diagnosis, List<PrescriptionLine> specification){
        setId(id);
        setCurrentUser(doctor);
        setPatient(patient);
        setStartDate(startDate);
        setEndDate(endDate);
        setDiagnosis(diagnosis);
        setSpecification(specification);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCurrentUser(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setStartDate(Date date) {this.startDate = date;}

    public void setEndDate(Date date) {
        this.endDate = date;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setSpecification(List<PrescriptionLine> specification) {
        this.specification = specification;
    }

    public int getId() {
        return id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public Date getStartDate() {return startDate;}

    public Date getEndDate() {return endDate;}



    public String getDiagnosis() {
        return diagnosis;
    }

    public List<PrescriptionLine> getSpecification() {
        return specification;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "id=" + id +
                ", patient=" + patient +
                ", doctor=" + doctor +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", diagnosis='" + diagnosis + '\'' +
                ", specification=" + specification +
                '}';
    }
}
