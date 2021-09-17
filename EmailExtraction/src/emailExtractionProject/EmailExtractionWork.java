package emailExtractionProject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EmailExtractionWork {
    public static void main(String[] args) {
        // creating path
        Path filePath
                = Paths.get("EmailExtraction/src/sample.txt");
        try

        {
            // reading file from given path
            String content = Files.readString(filePath);
            // printing the content
            System.out.println(content);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
