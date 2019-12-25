package ui.utils;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ReadFileInText {

    @SuppressFBWarnings("DM_DEFAULT_ENCODING")
    public static String readFile(String fileName){

        InputStream in = ReadFileInText.class.getResourceAsStream("/" + fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String currentLine = "";
        String allText = "";
        try {
            while ((currentLine = reader.readLine()) != null) {
                allText += currentLine;
            }

            return allText;
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
