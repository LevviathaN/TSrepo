package utils;

import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class BrowserstackApi {

    public static ReporterManager reporter = ReporterManager.Instance;

    public static void updateBrowserStackJob(String jobStatus, String sessionId) {
        try {
            String payload = new String();
            if (jobStatus.contains("pass")) {
                payload = String.format("{\"status\":\"passed\"}");
            } else {
                payload = String.format("{\"status\":\"failed\"}");
            }

            String userName = System.getProperty("browserStackUsername");
            String password = System.getProperty("browserStackPassword");

            URL url = new URL(String.format("https://api.browserstack.com/automate/builds/" + System.getProperty("browserStackBuild") + "/sessions/" + sessionId + ".json"));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization",
                    "Basic " + getBasicAuthenticationEncoding(userName, password));

            OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
            osw.write(payload);
            osw.flush();
            osw.close();

            if (connection.getResponseCode() == 200) {
                reporter.info("BrowserStack job has been updated successfully");
            } else {
                reporter.info("BrowserStack job has NOT been updated");
            }

            System.err.println(connection.getResponseCode());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String getBasicAuthenticationEncoding(String username, String password) {

        String userPassword = username + ":" + password;
        return new String(Base64.encodeBase64(userPassword.getBytes()));
    }
}
