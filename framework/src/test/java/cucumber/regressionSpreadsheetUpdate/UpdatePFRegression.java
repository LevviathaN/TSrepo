package cucumber.regressionSpreadsheetUpdate;

import org.testng.annotations.Test;
import ui.utils.BaseUITest;
import ui.utils.SpreadsheetsHelper;

public class UpdatePFRegression extends BaseUITest {

    @Test(groups = {"SpreadsheetsUpdate"})
    public void udateSpreadsheet() {
        SpreadsheetsHelper.manageSpreadsheet("ProductFactory");
    }
}