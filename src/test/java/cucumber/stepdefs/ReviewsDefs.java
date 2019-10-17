package cucumber.stepdefs;

import cucumber.api.java.en.*;
import entities.ItemEntity;
import entities.UserEntity;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.*;
import utils.*;

public class ReviewsDefs extends BasePage {

    @When("^I rate product with ([^\"]*) stars$")
    public void rate_product(String rating){
        findElement(By.xpath("//*[@id='pr-rating']/div/div[" + rating + "]")).click();
    }

    @When("^I leave comment: \"([^\"]*)\"$")
    public void leave_comment(String comment){
        findElement(By.xpath("//textarea[@id='pr-comments-input']")).sendKeys(comment);
    }
}
