package model;

import model.dBConnection.DAOMedicine;
import model.dBConnection.DAOUser;
import model.dBConnection.DBConnection;

import java.util.List;

public class TestCode {
    public void code(){
        //examples of use of methods getUser() and getUserList()
        DBConnection.getInstance();
        DAOUser dbUser = new DAOUser();
// Important!
 /*       Patient patient = new Patient("2", "2", "3", java.sql.Date.valueOf("1995-12-10"), "5", "6", "7", "8", "9");
        System.out.println(patient.addPatient() + " add");
        patient.setFirstName("Liliia");
        patient.setLastName("Allansson");
        patient.setBDate(java.sql.Date.valueOf("1986-12-10"));  //with this way of entering date we loose one day for some reason (in the table it is 1986-12-09).
        patient.setZipCode("12222");
        patient.setAddress("new address");
        patient.setEmail("new@gmail.com");
        patient.setPhoneNumber("+467777777");
        patient.setActive(true);
        System.out.println(patient.updatePatient() + " update");  // SSN, Type and Password are not being changed by this. For Password - another method (work in progress) (i think its more secure to do so, plus there we need to hash and validate it twice).
        System.out.println(patient.getUser("2"));
        System.out.println(patient.removePatient() + " remove");

        System.out.println();

*/
 /*       Doctor doctor = new Doctor();
        List<User> list1 = doctor.getDoctorList();
        if (list1 != null) {
            for (User element : list1) {
                System.out.println(" list1 doctors " + element);
            }
        }
        System.out.println();

        Patient patient1 = new Patient();
        List<User> list2 = patient1.getPatientList();
        if (list2 != null) {
            for (User element : list2) {
                System.out.println(" list2 patients" + element);
            }
        }

        System.out.println();
        User user = dbUser.getUser("2009103168");
        if (user instanceof Patient) {
            System.out.println(user + " patient test");
            //System.out.println(user.getClass().toString() + " patient test");
        }
        System.out.println();

        List<User> list3  = dbUser.getUserList("0");
        if (list3 != null) {
            for (User element : list3) {
                System.out.println(" list3 all users" + element);
            }
        }

*/
        DAOMedicine dbMedicine = new DAOMedicine();
   /*     ProdGroup gr = new ProdGroup();
        ProdGroup gr1 = gr.getProdGroup(152300);
        System.out.println(gr1.getId() + " " + gr1.getName() + " " + gr1.getPath());
        System.out.println();
        List<ProdGroup> groupList = dbMedicine.retrieveProductGroupList();
        if (groupList != null) {
            for (ProdGroup element : groupList) {
                System.out.println(element);
            }
        } else System.out.println("empty group list");
        System.out.println();

        List<Medicine> mList = dbMedicine.retrieveMedicineByProductGroupPath("Drugs/Digestion & Nausea/Constipation");
        if (mList != null) {
            for (Medicine element : mList) {
                System.out.println(element);
            }
        } else System.out.println("empty med list");
        System.out.println();

        List<Medicine> mList2 = dbMedicine.retrieveMedicineByMaxPrice(200);
        if (mList2 != null) {
            for (Medicine element : mList2) {
                System.out.println(element);
            }
        } else System.out.println("empty med list");
*/

   /*     List<Medicine> list3 = dbMedicine.retrieveMedicineList();
        if (list3 != null) {
            for (Medicine element : list3) {
                System.out.println(element);
            }
        } else System.out.println("empty list3");
*/

   /*     String ingredients = {"Naturligt havsvatten", "hyperton",  "koncentration av mineralsalter (22 g/l)"};
        PrescriptionFree a1 = new PrescriptionFree(10001, "Nezeril", "Astra Zeneka", "50 ml.", "Nezefri Menthol är en nässpray med eteriska oljor. Kan användas av vuxna och barn över 6 år.", 54, 68.50, ingredients);
        OnPrescription b1 = new OnPrescription(10002, "Cocaine", "neighbour", "1 match box", "You are going to get high", 20, 75, "chemical stuff");

        OnPrescription d1 = new OnPrescription();
        OnPrescription c1 = d1.getOnPrescriptionObject(10002);
        if (c1 != null) {
            System.out.println(c1.toString());
        }
        List<Medicine> listAll = new ArrayList<>();
        listAll.add(a1);
        listAll.add(b1);

        for (Medicine item: listAll) {
           System.out.println(item.toString());
        }*/
    }
}