package utils;

import org.openqa.selenium.By;
import pages.BasePage;
import java.io.*;


/**
 * created by rlevytskyi
 *
 * Used to export tickets database from Jira to spreadsheet
 * */
public class TicketsDatabaseUpload extends BasePage {

    String pythonScriptPath = "/Users/ruslanlevytskyi/PycharmProjects/GoogleSheetsApi/quickstart.py";
    String pathToPyExecutor = "/Users/ruslanlevytskyi/PycharmProjects/GoogleSheetsApi/venv/python/bin/python";
    String csvPath = "/Users/ruslanlevytskyi/Downloads/Jira.csv"; //todo: remove hardcode

    By username = By.xpath(".//input[@id='username']");
    By continueBtn = By.xpath(".//button[@id='login-submit']");
    By password = By.xpath(".//input[@id='password']");

    By exportIcon = By.xpath(".//a[@original-title='Export this filter in another format']");
    By exportCurrent = By.xpath(".//a[text()='Export Excel CSV (current fields)']");

    //todo: Make it prettier
    public void exportJiraTickets(String jiraTicketsFilterLink, String jiraEmail, String jiraPass) {
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
        executeCommand(pathToPyExecutor,pythonScriptPath);
    }

    public void executeCommand(String pathToExecutor, String pathToScript){
        ProcessBuilder builder = new ProcessBuilder();
        builder.command(pathToExecutor,pathToScript);
        builder.directory(new File(System.getProperty("user.home")));
        try{
            Process process = builder.start();
            new BufferedReader(new InputStreamReader(process.getInputStream())).lines().forEach(System.out::println);
        }
        catch(Exception e){e.printStackTrace();}

    }
}
