package model;

public class PrescriptionFree extends Medicine {
    private PrescriptionFree prescriptionFreeObject;

    public PrescriptionFree(int articleNo, String name, String producer, String packageSize, String description,int quantity, double price, String searchTerms, int groupId) {
        super(articleNo, false, name, producer, packageSize, description, quantity, price, searchTerms, groupId);
    }

    public boolean isOnPrescription() {
        return super.isOnPrescription();
    }
/*
    public PrescriptionFree getPrescriptionFreeObject(int articleNo){
        //request to the DB
        if (prescriptionFreeObject != null) {
            return prescriptionFreeObject;
        }
        return null;
    }
*/
    @Override
    public String toString() {
        return super.toString() +
                "onPrescription=" + isOnPrescription() +
                '}';
    }
}

