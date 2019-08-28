package debug;

import annotations.TestName;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.TicketsDatabaseUpload;

public class UpdateTickets extends BaseTest {

    @Test()
    @TestName(name = "Update Ticket Database")
    public void ticket_update () {

        TicketsDatabaseUpload jira = new TicketsDatabaseUpload();
//        jira.exportJiraTickets(
//        "https://basspro.atlassian.net/browse/CCM-79?filter=-4&jql=project%20%3D%20CCM%20order%20by%20created%20ASC",
//        "ruslan.levytskyi@avionos.com", "BillyJoe619");
        jira.uploadTickets();
    }
}
