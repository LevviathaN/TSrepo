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
//        jira.exportJiraTickets();
        jira.uploadTickets();
    }
}
