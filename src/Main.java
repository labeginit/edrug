import controller.UserCommon;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
import model.dBConnection.DBConnection;
import java.util.List;

public class Main extends Application {
    private UserCommon userCommon = new UserCommon();
    private static List<OrderLine> cart = CartSingleton.getOurInstance().getCart();

    @Override
    public void start(Stage primaryStage) throws Exception{
        DBConnection.getInstance();
        Parent root = FXMLLoader.load(getClass().getResource("view/loginView.fxml"));
        root.getStylesheets().add(getClass().getResource("FileUtil/layout.css").toExternalForm());
        primaryStage.setTitle("eDrug");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        userCommon.clearCart(cart);
        if(DBConnection.getInstance() != null)
            DBConnection.getInstance().disconnect();
        try {
        } catch (Exception exception){
            exception.getSuppressed();
        }
        super.stop();
    }

    public static void main(String[] args) {launch(args);}
}
