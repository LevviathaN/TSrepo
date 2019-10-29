package debug;

import org.testng.Assert;
import org.testng.annotations.Test;
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

}
