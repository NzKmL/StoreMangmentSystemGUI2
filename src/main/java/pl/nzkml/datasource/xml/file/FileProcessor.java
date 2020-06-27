package pl.nzkml.datasource.xml.file;

import javafx.application.Platform;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileProcessor {

    public String readFile(String fileName) throws FileNotFoundException{
            File file = new File(fileName);
            Scanner scaner = new Scanner(file);
            StringBuilder sb = new StringBuilder();

            while (scaner.hasNextLine()) {
                sb.append(scaner.nextLine());
            }

            scaner.close();
            return sb.toString();
    }
    public void writeToFile(String fileName, String text) throws IOException {
        Thread thread = new Thread(()->{
            FileWriter myWriter = null;
            try {
                myWriter = new FileWriter(fileName);
                myWriter.write(text);
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
                Platform.exit();
            }

        });
        thread.start();

    }

}
