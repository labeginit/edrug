package model.dBConnection;

import model.*;
import model.dBConnection.DAOMedicine;
import model.dBConnection.DAOPrescription;
import model.dBConnection.DAOUser;

import java.util.List;

public class CommonMethods {
    public DAOUser daoUser = new DAOUser();
    public DAOMedicine daoMedicine = new DAOMedicine();
    public DAOPrescription daoPrescription = new DAOPrescription();
    public DAOPickupPharmacies daoPickupPharmacies = new DAOPickupPharmacies();
    public DAODelivery daoDelivery = new DAODelivery();
    public DAOOrder daoOrder = new DAOOrder();
    public int linesAffected;

    //**********************

    //returns a User object
    public User getUser(String sSN){
        return daoUser.getUser(sSN);
    }

    //**********************

    //returns all users
    public List<User> getUserList(){
        return daoUser.getUserList("0");
    }

    //returns all types active or passive users
    public List<User> getUserList(boolean isActive){
        return daoUser.retrieveUserList(isActive);
    }

    //returns all admins
    public List<User> getAdminList(){
        return daoUser.getUserList("3");
    }

    //returns all doctors
    public List<User> getDoctorList(){
        return daoUser.getUserList("2");
    }

    //returns all active patients
    public List<User> getPatientList(){
        return daoUser.getUserList("1");
    }

    //**********************

    public int addUser(User user){
        return linesAffected = daoUser.addUser(user);
    }

    //creates a user of Patient type
    public int addPatient(Patient user){
            return linesAffected = daoUser.addUser(user);
    }

    //creates a user of Doctor type
    public int addDoctor(Doctor user){
        return linesAffected = daoUser.addUser(user);
    }

    //creates a user of Admin type
    public int addAdmin(Admin user){
            return linesAffected = daoUser.addUser(user);
    }

    //**********************

    public int updateUser(User user){
        return daoUser.updateUser(user);
    }

    public int updatePatient(Patient user){
        return daoUser.updateUser(user);
    }
    public int updateDoctor(Doctor user){
        return daoUser.updateUser(user);
    }

    public int updateAdmin(Admin user){
        return daoUser.updateUser(user);
    }

    public int updatePassword(User user){
        return daoUser.updatePassword(user);
    }

    //**********************

    public int removeUser(User user){
        return daoUser.removeUser(user);
    }

    public int removePatient(Patient user){
        return daoUser.removeUser(user);
    }
    public int removeDoctor(Doctor user){
        return daoUser.removeUser(user);
    }
    public int removeAdmin(Admin user){
        return daoUser.removeUser(user);
    }

    //**********************

    //returns a list of product group including: id, name and full path
    public List<ProdGroup> getProductGroupList() {
        return daoMedicine.retrieveProductGroupList();
    }

    //returns a Product Group object
    public ProdGroup getProductGroup(int groupId){
        return daoMedicine.retrieveProductGroup(groupId);
    }

    //**********************

    public Medicine getMedicine(int article){
        return daoMedicine.getMedicine(article);
    }

    //all kinds of medicine, active and inactive
    public List<Medicine> getMedicineList(){
        return daoMedicine.retrieveMedicineList();
    }

    //returns either onPrescription (true) or PrescriptionFree (false) active medicine
    public List<Medicine> getMedicineList(boolean onPrescription){
        return daoMedicine.retrieveMedicineList(onPrescription);
    }

    //returns either onPrescription (true) or PrescriptionFree (false) which is active or inactive medicine
    public List<Medicine> getMedicineList(boolean onPrescription, boolean isActive){
        return daoMedicine.retrieveMedicineList(onPrescription, isActive);
    }

    //**********************

    public int addMedicine(Medicine medicine){
        return linesAffected = daoMedicine.addMedicine(medicine);
    }

    public int updateMedicine(Medicine medicine){
        return linesAffected = daoMedicine.updateMedicine(medicine);
    }

    public int updateQuantity(Medicine medicine) { return linesAffected = daoMedicine.updateQuantity(medicine);}

    //**********************

    //returns all active medicine in the specified group (by full path) - made for filter
    public List<Medicine> getMedicineByProductGroupPath(String fullPath){
        return daoMedicine.retrieveMedicineByProductGroupPath(fullPath);
    }

    //returns all active medicine with price less than or equal to parameter - made for filter
    public List<Medicine> getMedicineByMaxPrice(double maxPrice) {
        return daoMedicine.retrieveMedicineByMaxPrice(maxPrice);
    }

    //**********************

    public int addPrescription(Prescription prescription){
        return daoPrescription.addPrescription(prescription);
    }

    public void deletePrescriptionLine(PrescriptionLine prescriptionLine) {
        daoPrescription.deletePrescriptionLine(prescriptionLine);
    }

    public void deletePrescription(PrescriptionLine prescriptionLine, User currentUser) {
        daoPrescription.deletePrescription(prescriptionLine, currentUser);
    }

    //returns all lines of a specific Prescription header (id and user)
    // or all prescriptions (enter 0 as argument #1). For Admin it will return lines without filtering for user.
    public List<PrescriptionLine> getPrescriptionLineList(int prescriptionId, User currentUser){
        return daoPrescription.retrievePrescriptionLines(String.valueOf(prescriptionId), currentUser);
    }

    public List<Prescription> getPrescriptionList(User currentUser){
        return daoPrescription.getPrescriptionList(currentUser);
    }

    //**********************


    public int addPharmacy(Pharmacy pharmacy) { return daoPickupPharmacies.addPharmacy(pharmacy); }

    public List<Pharmacy> retrievePharmacyList() { return  daoPickupPharmacies.retrievePharmacyList(); }

    public int updatePharmacy(Pharmacy pharmacy) { return daoPickupPharmacies.updatePharmacy(pharmacy); }

    public int removePharmacy(Pharmacy pharmacy) { return daoPickupPharmacies.removePharmacy(pharmacy); }

    //gives the last used id number for Prescriptions, Orders, Pharmacy, and Medicine
    public int getLastId(Class myClass){
        int maxId = 0;
        if (myClass.getName().equalsIgnoreCase("model.Prescription")){
            maxId = daoPrescription.retrieveLastPrescriptionId();
        } else if (myClass.getName().equalsIgnoreCase("model.Order")) {
            maxId = daoOrder.retrieveLastOrderId();
        } else if (myClass.getName().equalsIgnoreCase("model.Pharmacy")) {
            maxId = daoPickupPharmacies.retrieveLastPharmacyId();
        } else if (myClass.getName().equalsIgnoreCase("model.Medicine")){
            maxId = daoMedicine.retrieveLastArticle();
        }
        return maxId;
    }


    //**********************


    public int addOrder(Order order) { return daoOrder.addOrder(order); }

    public List<Order> retrieveOrderList() {
         List <Order> orders = daoOrder.retrieveOrderList();
        for (int i = 0; i < orders.size(); i++) {
            orders.get(i).setUser(getUser(orders.get(i).getUser().getSsn()));
            for (int j = 0; j < orders.get(i).getSpecification().size(); j++) {
                orders.get(i).getSpecification().get(j).setMedicine(getMedicine(orders.get(i).getSpecification().get(j).getArticleNo()));
                orders.get(i).getSpecification().get(j).setUser(getUser(orders.get(i).getUser().getSsn()));
                orders.get(i).getSpecification().get(j).setName(orders.get(i).getSpecification().get(j).getMedicine().getName());
            }
        }
         return orders;
    }

    public Order getOrder(int id) {
        Order order = daoOrder.getOrder(id);
        order.setUser(getUser(order.getUser().getSsn()));
        for (int j = 0; j < order.getSpecification().size(); j++) {
            order.getSpecification().get(j).setMedicine(getMedicine(order.getSpecification().get(j).getArticleNo()));
            order.getSpecification().get(j).setUser(getUser(order.getUser().getSsn()));
            order.getSpecification().get(j).setName(order.getSpecification().get(j).getMedicine().getName());
        }
        return order;
    }

    //*****************************

    public int addDelivery(Delivery delivery) { return daoDelivery.addDelivery(delivery); }

    public List<Delivery> retrieveDeliveryList() { return  daoDelivery.retrieveDeliveryList(); }


}

