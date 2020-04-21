import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
import model.dBConnection.DBConnection;
import model.dBConnection.DAOMedicine;
import model.dBConnection.DAOUser;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/loginView.fxml"));
        primaryStage.setTitle("e-DRUGS");
        primaryStage.setScene(new Scene(root));
        
        primaryStage.show();

       // if(DBConnection.getInstance() != null)
        //    DBConnection.getInstance().disconnect();
        TestCode tst = new TestCode();
        tst.code();

    }


    public static void main(String[] args) {launch(args);}
}
