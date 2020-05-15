import FileUtil.RWFile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
import model.dBConnection.DBConnection;


public class Main extends Application {

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
