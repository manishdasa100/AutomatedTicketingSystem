package automatedTicketingSystem.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Helper {

    /**
     * This is a utility class having non core helper functions
     */
    
    private Helper() {/** Singleton class */}

    /**
     * This function takes in a file path as string and tries to convert the file content into string and return it.
     * If the file path is invalid then it returns an error message.
     * @param filePath
     * @return
     */
    public static String resolveFile(String filePath) {

        String fileContent = "";
        try{

            InputStream inputStream = new FileInputStream(new File(filePath));
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);

            fileContent += new String(buffer, StandardCharsets.UTF_8);

        } catch(IOException exception) {
            
            String message = "File provided in arguments not found!";
            return message;
        }

        return fileContent;
    }

}
