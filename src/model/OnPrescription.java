package model;

public class OnPrescription extends Medicine {
    private OnPrescription onPrescriptionObject;

    public OnPrescription(){}

    public OnPrescription(int articleNo, String name, String producer, String packageSize, String description, int quantity, double price, String searchTerms, int groupId){
        super(articleNo, true, name, producer, packageSize, description, quantity , price , searchTerms, groupId);
    }

    public boolean isOnPrescription() {
        return super.isOnPrescription();
    }

 /*   public OnPrescription getOnPrescriptionObject(int articleNo){
        //request to the DB
        if (onPrescriptionObject != null) {
            return onPrescriptionObject;
        }
        return null;
    }*/

    @Override
    public String toString() {
        return super.toString() + "onPrescription=" + isOnPrescription() +
                '}';
    }
}
