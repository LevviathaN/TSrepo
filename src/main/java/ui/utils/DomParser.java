package ui.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class DomParser {

    public static void deleteFailedTestsFromHtmlReport(String testName) {
        String filePath = Reporter.getScreenshotFolder().toString().replace("screenshots","report.html");

        try {
            File inputFile = new File(filePath);
            Document doc = Jsoup.parse(inputFile,"utf-8");
            Elements testNameElement = doc.getElementsByClass("test-name test-name-list");
            if (testNameElement.text().contains(testName)) {
                testNameElement.parents().first().parents().first().remove();
            }
            inputFile.delete();
            FileWriter writer = new FileWriter(filePath, false);
            writer.write(doc.outerHtml());
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
