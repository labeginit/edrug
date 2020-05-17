package FileUtil;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;


public class RWFile {

    public static Path invoice = Paths.get("invoice.txt");


    public static void saveToFile(Path path, ArrayList<String> strings) {

        try {
            StandardOpenOption[] options = { StandardOpenOption.CREATE };
            Files.write(path, strings, options);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean delete() {
        File file = invoice.toFile();
        if (file.delete()) {
            System.out.println("File Deleted");
            return true;
        } else {
            System.out.println("File not Deleted");
            return false;
        }
    }
}
