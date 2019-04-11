package cucumber.stepdefs;

import cucumber.api.java.en.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.BasePage;
import pages.MagazinePage;
import pages.PageHeader;
import utils.*;

public class MagazineDefs extends BasePage {
    MagazinePage magazine = MagazinePage.Instance;

    @Given("^I should see only \"([^\"]*)\" category articles$")
    public void i_should_see_articles(String category) {
        Assert.assertTrue(magazine.categoryVerification(category),
                "There are some other tags in " + category + " category");
    }
}
