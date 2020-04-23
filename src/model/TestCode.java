package model;

import model.dBConnection.DAOMedicine;
import model.dBConnection.DAOUser;
import model.dBConnection.DBConnection;

import javax.print.MultiDocPrintService;
import java.util.List;

public class TestCode {
    public void code(){
        //examples of use of methods getUser() and getUserList()


        CommonMethods commonMethods = new CommonMethods();
// Important!
 /*       Patient patient = new Patient("222222-111", "2", "3", java.sql.Date.valueOf("1995-12-10"), "5", "6", "7", "8", "9");
        System.out.println(commonMethods.addPatient(patient) + " add");
        patient.setFirstName("Liliia");
        patient.setLastName("Allansson");
        patient.setBDate(java.sql.Date.valueOf("1986-12-10"));  //with this way of entering date we loose one day for some reason (in the table it is 1986-12-09).
        patient.setZipCode("12222");
        patient.setAddress("new address");
        patient.setEmail("new@gmail.com");
        patient.setPhoneNumber("+467777777");
        patient.setActive(true);
        patient.setPassword("333");
        System.out.println(commonMethods.updateUser(patient) + " update");  // SSN, Type and Password are not being changed by this. For Password - another method (work in progress) (i think its more secure to do so, plus there we need to hash and validate it twice).
        commonMethods.updatePassword(patient);
        System.out.println(commonMethods.getUser("222222-111"));
        //System.out.println(commonMethods.removePatient(patient) + " remove");
*/
        System.out.println();

        List<User> list3  = commonMethods.getUserList();
        if (list3 != null) {
            for (User element : list3) {
                System.out.println(" list3 all users" + element);
            }
        }
        System.out.println();

        List<User> list1 = commonMethods.getDoctorList();
        if (list1 != null) {
            for (User element : list1) {
                System.out.println(" list1 doctors " + element);
            }
        }
        System.out.println();


        List<User> list2 = commonMethods.getPatientList();
        if (list2 != null) {
            for (User element : list2) {
                System.out.println(" list2 patients" + element);
            }
        }
        System.out.println();

        List<User> list6 = commonMethods.getAdminList();
        if (list6 != null) {
            for (User element : list6) {
                System.out.println(" list6 admins" + element);
            }
        }
        System.out.println();

        List<User> list7 = commonMethods.getUserList(false);
        if (list7 != null) {
            for (User element : list7) {
                System.out.println(" list7 passive " + element);
            }
        }

        System.out.println();

        User user1 = commonMethods.getUser("860305-0731");

            System.out.println(user1 + " getUser test");

        System.out.println();





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
