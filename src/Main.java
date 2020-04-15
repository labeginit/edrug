import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/login.fxml"));
        primaryStage.setTitle("e-DRUGS");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

//  THIS IS JUST A TEST CODE
   /*     String[] ingredients = {"Naturligt havsvatten", "hyperton",  "koncentration av mineralsalter (22 g/l)"};
        PrescriptionFree a1 = new PrescriptionFree(10001, "Nezeril", "Astra Zeneka", "50 ml.", "Nezefri Menthol är en nässpray med eteriska oljor. Kan användas av vuxna och barn över 6 år.", 54, 68.50, ingredients);
        OnPrescription b1 = new OnPrescription(10002, "Cocaine", "neighbour", "1 match box", "You are going to get high", 20, 75, new String[]{"chemical stuff"});

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
