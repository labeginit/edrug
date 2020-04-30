package model;

public class OnPrescription extends Medicine {
    private OnPrescription onPrescriptionObject;

    public OnPrescription(int articleNo, int groupId, String name, String producer, String packageSize, String description, int quantity, double price, String searchTerms, boolean isActive){
        super(articleNo, groupId, true, name, producer, packageSize, description, quantity, price, searchTerms, isActive);
    }

    public boolean isOnPrescription() {
        return super.isOnPrescription();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
