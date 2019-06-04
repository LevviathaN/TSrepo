package utils;

import org.openqa.selenium.By;
import pages.BasePage;

public class TicketsDatabaseUpload extends BasePage {

    String jiraTicketsFilterLink = "https://basspro.atlassian.net/browse/CCM-79?filter=-4&jql=project%20%3D%20CCM%20order%20by%20created%20ASC";
    String jiraEmail = "ruslan.levytskyi@avionos.com";
    String jiraPass = "BillyJoe619";
    String pythonScriptPath = "/Users/ruslanlevytskyi/Projects/LevviathaN/TestFramework/TestFramework/data/python/quickstart.py";
    String pathToPyExecutor = "/Users/ruslanlevytskyi/Projects/LevviathaN/TestFramework/TestFramework/data/python/venv/bin/python";
    String uploadCommad = "/Users/ruslanlevytskyi/Projects/LevviathaN/TestFramework/TestFramework/data/python/venv/bin/python /Users/ruslanlevytskyi/Projects/LevviathaN/TestFramework/TestFramework/data/python/quickstart.py";
    String csvPath = "/Users/ruslanlevytskyi/Downloads/Tomorrow_Sleep.csv";

    By username = By.xpath(".//input[@id='username']");
    By continueBtn = By.xpath(".//button[@id='login-submit']");
    By password = By.xpath(".//input[@id='password']");

    By exportIcon = By.xpath(".//a[@original-title='Export this filter in another format']");
    By exportCurrent = By.xpath(".//a[text()='Export Excel CSV (current fields)']");

    //todo: Make it prettier
    public void exportJiraTickets() {
        driver().get(jiraTicketsFilterLink);
        findElement(username).sendKeys(jiraEmail);
        findElement(continueBtn).click();
        findElement(password).sendKeys(jiraPass);
        findElement(continueBtn).click();
        waitForPageToLoad();
        driver().get(jiraTicketsFilterLink);
        waitForPageToLoad();
        driver().get(jiraTicketsFilterLink);
        findElement(exportIcon).click();
        findElement(exportCurrent).click();
        sleepFor(5000);
    }

    public void uploadTickets(){
        Runtime rt = Runtime.getRuntime();
        try {
//            Process proc = rt.exec(uploadCommad);
//            reporter.info(proc.getOutputStream().toString());
//            reporter.info(proc.getErrorStream().toString());
            String[] args = new String[] {pythonScriptPath};
            Process proc = new ProcessBuilder(args).start();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            reporter.info("Oops!");
        }
    }
}
