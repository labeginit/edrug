package model;

public class PrescriptionLines {
    private int prescId;
    private Medicine medicine;
    private Patient patient;
    private int quantity;
    private String instructions;

    public PrescriptionLines(int prescId, Patient patient, Medicine medicine, int quantity, String instructions){
        setPrescId(prescId);
        setPatient(patient);
        setMedicine(medicine);
        setQuantity(quantity);
        setInstructions(instructions);
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
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

    public int getQuantity() {
        return quantity;
    }

    public String getInstructions() {
        return instructions;
    }

    @Override
    public String toString() {
        return "PrescriptionLines{" +
                "prescId=" + prescId +
                ", medicine=" + medicine +
                ", patient=" + patient +
                ", quantity=" + quantity +
                ", instructions='" + instructions + '\'' +
                '}';
    }
}
