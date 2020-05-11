package model;

public class PrescriptionParent {
    private Patient patient;
    private Doctor doctor;
    private Medicine medicine;
    private int quantityPrescribed;
    private int quantityConsumed;

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

    public Doctor getDoctor() {
        return doctor;
    }

    public int getArticle(){
        return this.getMedicine().getArticleNo();
    }

    public String getName(){
        return this.getMedicine().getName();
    }

}
