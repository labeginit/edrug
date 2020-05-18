package model;

import model.dBConnection.CommonMethods;

import java.sql.Date;
import java.util.ArrayList;
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
 /*       System.out.println();

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

*/


/*
        ProdGroup gr = new ProdGroup();
        ProdGroup gr1 = gr.getProdGroup(152300);
        System.out.println(gr1.getId() + " " + gr1.getName() + " " + gr1.getPath());
        System.out.println();
        List<ProdGroup> groupList = commonMethods.getProductGroupList();
        if (groupList != null) {
            for (ProdGroup element : groupList) {
                System.out.println(element);
            }
        } else System.out.println("empty group list");
        System.out.println();

        List<Medicine> mList = commonMethods.getMedicineByProductGroupPath("Drugs/Digestion & Nausea/Constipation");
        if (mList != null) {
            for (Medicine element : mList) {
                System.out.println(element);
            }
        } else System.out.println("empty med list");
        System.out.println();

        List<Medicine> mList2 = commonMethods.getMedicineByMaxPrice(200);
        if (mList2 != null) {
            for (Medicine element : mList2) {
                System.out.println(element + " price below or = 200");
            }
        } else System.out.println("empty med list");

        System.out.println();
        List<Medicine> list3 = commonMethods.getMedicineList();
        if (list3 != null) {
            for (Medicine element : list3) {
                System.out.println(element + " all active medicine");
            }
        } else System.out.println("empty list3");

        System.out.println();
        List<Medicine> list8 = commonMethods.getMedicineList(false);
        if (list8 != null) {
            for (Medicine element : list8) {
                System.out.println(element + " no prescription, active");
            }
        } else System.out.println("empty list8");
*/

    /*    Medicine medicine = new OnPrescription(10005, 152400, "medicine", "biohim", "10 pcs", "descr", 33, 12.25, "active ingridient", true);
        commonMethods.addMedicine(medicine);
        medicine.setQuantity(5);
        medicine.setActive(false);
        medicine.setDescription("new descr");
        commonMethods.updateMedicine(medicine);*/

  /*      List<PrescriptionLine> lines = new ArrayList<>();
        PrescriptionLine line1 = new PrescriptionLine(4, (Patient) commonMethods.getUser("660530-3910"), commonMethods.getMedicine(10001), 1, "1 pill a day 4 days");
        lines.add(line1);

        Prescription prescription = new Prescription(4, (Doctor) commonMethods.getUser("860305-0731"), (Patient) commonMethods.getUser("660530-3910"), java.sql.Date.valueOf("2020-04-30"), "fever", lines);
        int added = commonMethods.addPrescription(prescription);
        System.out.println(added);
*/

        System.out.println(commonMethods.getLastId(Prescription.class));
        System.out.println(commonMethods.getLastId(Order.class));
   /*     List <OrderLine> lines = new ArrayList<>();
        OrderLine line1 = new OrderLine(commonMethods.getLastId(Order.class) + 1, commonMethods.getUser("222222-1111"), commonMethods.getMedicine(10004), 40, 2);
        lines.add(line1);
        Order order = new Order(commonMethods.getLastId(Order.class) + 1, commonMethods.getUser("222222-1111"), Date.valueOf("2020-05-20"), Order.DeliveryMethod.POSTEN, Order.PaymentMethod.BANK_TRANSFER,
                lines, 40, 8);
        commonMethods.addOrder(order);*/

    }
}
