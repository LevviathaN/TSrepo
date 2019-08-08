package debug;

import annotations.TestName;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.aem.AssetsPage;
import pages.aem.MetadataFormPage;
import utils.BaseTest;
import utils.CsvReader;
import utils.XmlReader;

public class DebugTest extends BaseTest {

    @Test
    public void debugTest(){
        XmlReader reader = new XmlReader("src/main/resources/data/bps/Skyword_Create.xml");
        reader.go();
    }

    @Test
    public void csvReaderTest(){
        CsvReader reader = new CsvReader("src/main/resources/data/bps/DMAT_short.csv");
        System.out.println(reader.getValues("ATDSKU","184337","SACCOD"));
    }

    @Test
    public void isBrowserInFocusTest(){
        AssetsPage ast = AssetsPage.Instance;
        ast.logIn();

    }

    @Test
    public void fieldsValueTest(){
        AssetsPage ast = AssetsPage.Instance;
        MetadataFormPage meta = MetadataFormPage.Instance;
        ast.logIn();
        ast.navigate("/test-folder/");
        ast.openPropertiesOfAsset("2648783_100207535_is.jpeg");
        for(String value : meta.getMultifieldValues("CAB SKU")){
            System.out.println(value);
        }
        System.out.println(meta.findMultifieldByTitle("CAB SKU").getText());
    }

    @Test
    public void verifyMetadata(){
        MetadataFormPage meta = MetadataFormPage.Instance;
        AssetsPage ast = AssetsPage.Instance;

        ast.logIn();
        ast.navigate("/test-folder");
        ast.openPropertiesOfAsset("2648783_100207535_is.jpeg");
        Assert.assertTrue(meta.verifyMetadata("CAB SKU/104897","Master SKU/1091175","SKU Status/A"));
    }
}
