package cucumber.stepdefs;

import cucumber.api.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.*;
import utils.*;

import java.util.List;

public class FaqDefs extends BasePage {

    ReporterManager reporter = ReporterManager.Instance;

    @When("^I should see questions and answers relevant to request \"([^\"]*)\"$")
    public void verify_answers(String request){
        List<WebElement> questionsList = findElementsIgnoreException(By.xpath("//div[@class='question']"));
        List<WebElement> answersList = findElementsIgnoreException(By.xpath("//div[@class='answer']"));
        int questionMatch = 0, answerMatch = 0;
        for (int i = 0; i<questionsList.size(); i++) {
            if(questionsList.get(i).getText().contains(request)) questionMatch++;
            if(answersList.get(i).getText().contains(request)) answerMatch++;
        }
        Assert.assertTrue(questionMatch>0 && answerMatch>0, "No matches found");
//        System.out.println(questionMatch + " questions and " + answerMatch + " answers were found");
        reporter.info(questionMatch + " questions and " + answerMatch + " answers were found");
    }
}
