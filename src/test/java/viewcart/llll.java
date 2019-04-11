package viewcart;

import annotations.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BasePage;
import utils.BaseTest;
import utils.DriverProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static pages.BasePage.findElement;
import static pages.BasePage.isElementDisplayedRightNow;
import static pages.BasePage.openUrl;

public class llll extends BaseTest {

    String csv = "src/main/resources/Book1.csv";

    @DataProvider(name = "test", parallel = true)
    public Iterator<Object[]> provider() throws IOException {

        List<Object[]> url = new ArrayList<>();
        String[] info = null;
        BufferedReader reader = new BufferedReader(new FileReader(csv));
        String line;

        while ((line = reader.readLine()) != null){
            info = line.split(",");
            url.add(info);
        }

        return url.iterator();
    }

    @Test (dataProvider = "test")
    @TestName (name = "lol")
    public void lol(String url) throws MalformedURLException {

        openUrl(url);
        BasePage.closeWelcome();
        Assert.assertFalse( isElementDisplayedRightNow(By.xpath(".//contains(text(), 'lorem ipsum') | .//contains(text(), 'Lorem ipsum') | .//contains(text(), 'Lorem Ipsum')")));
        String source = BasePage.getSource();
        Assert.assertFalse(source.toLowerCase().contains("lorem ipsum"));

    }
}
