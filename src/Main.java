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

import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //examples of use of methods getUser() and getUserList()
        DBConnection.getInstance();
        DAOUser dbUser = new DAOUser();
        DAOMedicine dbMedicine = new DAOMedicine();

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

        User user = dbUser.getUser("8603050731");
        if (user instanceof Doctor) {
            System.out.println(user);
            System.out.println(user.getClass().toString());
        }

        List<User> list3  = dbUser.getUserList("0");
        if (list3 != null) {
            for (User element : list3) {
                System.out.println(element + " list3 patients");
            }
        }


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

       // if(DBConnection.getInstance() != null)
        //    DBConnection.getInstance().disconnect();


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
