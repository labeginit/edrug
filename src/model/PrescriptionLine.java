package model;

public class PrescriptionLine{
    private int prescId;
    private Medicine medicine;
    private Patient patient;
    private int quantityPrescribed;
    private int quantityConsumed;
    private String instructions;
    private String name;
    private int article;

    public PrescriptionLine(int prescId, Patient patient, Medicine medicine, int quantityPrescribed, int quantityConsumed, String instructions){
        setPrescId(prescId);
        setPatient(patient);
        setMedicine(medicine);
        setQuantityPrescribed(quantityPrescribed);
        setQuantityConsumed(quantityConsumed);
        setInstructions(instructions);
        setArticle(medicine);
        setName(medicine);
    }

    public void setPrescId(int prescId) {
        this.prescId = prescId;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public void setQuantityPrescribed(int quantityPrescribed) {
        this.quantityPrescribed = quantityPrescribed;
    }

    public void setQuantityConsumed(int quantityConsumed) {
        this.quantityConsumed = quantityConsumed;
    }

    public void setInstructions(String instructions) {
        if (instructions.length() > 60) {
            this.instructions = instructions.substring(0, 59);
        } else this.instructions = instructions;
    }

    public int getPrescId() {
        return prescId;
    }

    public Patient getPatient() {
        return patient;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public int getQuantityPrescribed() {
        return quantityPrescribed;
    }

    public int getQuantityConsumed() {
        return quantityConsumed;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setName(Medicine medicine) {
        this.name = medicine.getName();
    }

    public String getName() {
        return name;
    }

    public void setArticle(Medicine medicine) {
        this.article = medicine.getArticleNo();
    }

    public int getArticle() {
        return article;
    }



    @Override
    public String toString() {
        return "PrescriptionLine{" +
                "prescId=" + getPrescId() +
                ", medicine=" + getMedicine() +
                ", patient=" + getPatient() +
                ", quantityPrescribed=" + getQuantityPrescribed() +
                ", quantityConsumed=" + getQuantityConsumed() +
                ", instructions='" + getInstructions() + '\'' +
                '}';
    }
}
