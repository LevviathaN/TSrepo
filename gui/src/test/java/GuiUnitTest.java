import CodeEditor.GherkinValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class GuiUnitTest {

    @Test
    public void mapsInitializationTest(){
        GherkinValidator validator = new GherkinValidator();
        Assert.assertTrue(validator!=null);
//        Assert.assertFalse(GherkinValidator.locatorsMap.isEmpty());
//        Assert.assertFalse(GherkinValidator.reusablesList.isEmpty());
//        Assert.assertFalse(GherkinValidator.specialLocatorsMap.isEmpty());
//        Assert.assertFalse(GherkinValidator.stepPatternsMap.isEmpty());
//        Assert.assertFalse(GherkinValidator.stepSignaturesMap.isEmpty());
    }
}
