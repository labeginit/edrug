package model;

public class PrescriptionFree extends Medicine {
    private PrescriptionFree prescriptionFreeObject;

    public PrescriptionFree(int articleNo, int groupId, String name, String producer, String packageSize, String description,int quantity, double price, String searchTerms, Boolean isActive) {
        super(articleNo, groupId, false, name, producer, packageSize, description, quantity , price , searchTerms, isActive);
    }

    public boolean isOnPrescription() {
        return super.isOnPrescription();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

