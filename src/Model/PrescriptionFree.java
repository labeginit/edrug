package Model;

public class PrescriptionFree extends Medicine {
    private PrescriptionFree prescriptionFreeObject;
    private boolean onPrescription;

    public PrescriptionFree(int articleNo, String name, String producer, String packageSize, String description,int quantity, double price, String... searchTerms) {
        super(articleNo, name, producer, packageSize, description, quantity, price, searchTerms);
        onPrescription = false;
    }

    public boolean isOnPrescription() {
        return onPrescription;
    }

    public PrescriptionFree getPrescriptionFreeObject(int articleNo){
        //request to the DB
        if (prescriptionFreeObject != null) {
            return prescriptionFreeObject;
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString() +
                "onPrescription=" + isOnPrescription() +
                '}';
    }
}

