package model;

public class OnPrescription extends Medicine {
    private OnPrescription onPrescriptionObject;
    private boolean onPrescription;

    public OnPrescription(){}

    public OnPrescription(int articleNo, String name, String producer, String packageSize, String description, int quantity, double price, String searchTerms){
        super(articleNo, name, producer, packageSize, description, quantity , price , searchTerms);
        onPrescription = true;
    }

    public boolean isOnPrescription() {
        return onPrescription;
    }

    public OnPrescription getOnPrescriptionObject(int articleNo){
        //request to the DB
        if (onPrescriptionObject != null) {
            return onPrescriptionObject;
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + "onPrescription=" + isOnPrescription() +
                '}';
    }
}
