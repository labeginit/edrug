import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Doctor;
import model.Patient;
import model.User;
import model.dBConnection.DBConnection;
import model.dBConnection.DAOMedicine;
import model.dBConnection.DAOUser;

import java.sql.Date;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //examples of use of methods getUser() and getUserList()
        DBConnection.getInstance();
        DAOUser dbUser = new DAOUser();
// Important!
/*        Patient patient = new Patient("2", "2", "3", java.sql.Date.valueOf("1995-12-10"), "5", "6", "7", "8", "9");
        patient.addPatient();
        patient.setFirstName("Liliia");
        patient.setLastName("Allansson");
        patient.setBDate(java.sql.Date.valueOf("1986-12-10"));  //with this way of entering date we loose one day for some reason (in the table it is 1986-12-09).
        patient.setZipCode("11111");
        patient.setAddress("new address");
        patient.setEmail("new@gmail.com");
        patient.setPhoneNumber("+467777777");
        patient.updatePatient();  // SSN, Type and Password are not being changed by this. For Password - another method (i think its more secure to do so, plus there we need to hash and validate it twice).
        System.out.println(patient.getUser("2"));
*/

        /*DAOMedicine dbMedicine = new DAOMedicine();

        Doctor doctor = new Doctor();
        List<User> list1 = doctor.getDoctorList();
        if (list1 != null) {
            for (User element : list1) {
                System.out.println(element + " list1 doctors");
            }
        }
        Patient patient = new Patient();
        List<User> list2 = patient.getPatientList();
        if (list2 != null) {
            for (User element : list2) {
                System.out.println(element + " list2 patients");
            }
        }

        User user = dbUser.getUser("2009103168");
        if (user instanceof Patient) {
            System.out.println(user + " patient test");
            System.out.println(user.getClass().toString() + " patient test");
        }

        List<User> list3  = dbUser.getUserList("0");
        if (list3 != null) {
            for (User element : list3) {
                System.out.println(element + " list3 patients");
            }
        }
*/

        //   List<Medicine> list3 = dbMedicine.retrieveMedicineList("SELECT * FROM Medicine;");
  /*      if (list3 != null) {
            for (Medicine element : list3) {
                System.out.println(element);
            }
        } else System.out.println("empty list3");
*/

        Parent root = FXMLLoader.load(getClass().getResource("view/login.fxml"));
        primaryStage.setTitle("e-DRUGS");
        primaryStage.setScene(new Scene(root));
        
        primaryStage.show();

    //    if(DBConnection.getInstance() != null)  this code to be used when we are exiting program
    //        DBConnection.getInstance().disconnect();


//  THIS IS JUST A TEST CODE
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


    public static void main(String[] args) {launch(args);}
}
