package ui.utils;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Proxy;
import ui.utils.bpp.PreProcessFiles;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class to store custom methods
 *
 * @author rlevytskyi
 */

public class Tools {


    public static String getCurDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return sdf.format(new Date(System.currentTimeMillis()));
    }

    public static String getReportName() {

        String name;
        LocalDateTime dateTime = LocalDateTime.now();
        name = dateTime.toLocalDate() + "_" + dateTime.toLocalTime().getHour() + "_"
                + dateTime.toLocalTime().getMinute() + ".html";
        return name;
    }

    public static String getStackTrace(Throwable problem) {
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        problem.printStackTrace(printWriter);
        return result.toString();
    }

    /**
     * @param locatorsFile - the name of Json file which will be used to retrieve Json file as an Object
     */
    @SuppressFBWarnings("DM_DEFAULT_ENCODING")
    public JSONObject getJsonObjectForLocators(String locatorsFile) {

        JSONParser parser = new JSONParser();

        StringBuilder path = new StringBuilder();
        path.append(System.getProperty("user.dir"));
        path.append(locatorsFile);

        try (FileReader reader = new FileReader(path.toString())) {

            Object obj = parser.parse(reader);

            return (JSONObject) obj;
        } catch (ParseException | IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static List<String> getQuotet(final String input, final char quote) {
        final ArrayList<String> result = new ArrayList<>();
        int n = -1;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == quote) {
                if (n == -1) { //not currently inside quote -> start new quote
                    n = i + 1;
                } else { //close current quote
                    result.add(input.substring(n, i));
                    n = -1;
                }
            }
        }
        return result;
    }

    public static void writeHar(BrowserMobProxy proxy) {
        proxy.newHar();
        Har har = proxy.getHar();
        //System.out.println(har.getLog().getBrowser().getName());
        //BPPLogManager.getLogger().info(har.getLog().getBrowser().getName());
        //BPPLogManager.getLogger().info(har.getLog().getBrowser().getVersion());

        File harFile = new File(Reporter.getReportPath().toString() + "/automation.har");
        try {
            har.writeTo(harFile);
        } catch (IOException e) {
            e.printStackTrace();
            BPPLogManager.getLogger().error("Cannot write har file for proxy");
        }
    }
}
