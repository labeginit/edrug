import FileUtil.RWFile;
import controller.UserCommon;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
import model.dBConnection.CommonMethods;
import model.dBConnection.DBConnection;

import java.io.IOException;

import static controller.PatientController.cart;

public class Main extends Application {
    private CommonMethods commonMethods = new CommonMethods();
    private UserCommon userCommon = new UserCommon();

    @Override
    public void start(Stage primaryStage) throws Exception{
        DBConnection.getInstance();
        Parent root = FXMLLoader.load(getClass().getResource("view/loginView.fxml"));
        primaryStage.setTitle("e-DRUGS");
        primaryStage.setScene(new Scene(root));
        
        primaryStage.show();

        TestCode tst = new TestCode();
        tst.code();

    }

    @Override
    public void stop() throws Exception {
        userCommon.clearCart(cart);
        if(DBConnection.getInstance() != null)
            DBConnection.getInstance().disconnect();
        try {
            RWFile.delete();
        } catch (Exception exception){
            exception.getSuppressed();
        }
        super.stop();
    }



    public static void main(String[] args) {launch(args);}
}
