package FileUtil;

import model.OrderLine;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class RWFile {

    public static Path cartPath = Paths.get("src/ShoppingCart.ser");


    public static void writeObject(Path path, List<OrderLine> cart) {
        File file = path.toFile();
        try (ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOut.writeObject(cart);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List <OrderLine>  readObject(Path path) {
        try (ObjectInputStream oIn = new ObjectInputStream(new FileInputStream(path.toFile()))) {
            return (List <OrderLine> ) oIn.readObject();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static boolean delete() {
        File file = cartPath.toFile();
        if (file.delete()) {
            System.out.println("File Deleted");
            return true;
        } else {
            System.out.println("File not Deleted");
            return false;
        }
    }
}
