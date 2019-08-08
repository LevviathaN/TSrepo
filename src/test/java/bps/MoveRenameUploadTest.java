package bps;

import annotations.TestName;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.aem.AssetsPage;
import pages.aem.MovRnmUpldPage;
import utils.BaseTest;

public class MoveRenameUploadTest extends BaseTest {

    @Test
    @TestName(name = "Rename Upload Test")
    public void a_RenameUploadTest(){

        MovRnmUpldPage mov = MovRnmUpldPage.Instance;
        AssetsPage ast = AssetsPage.Instance;

        ast.logIn();
        ast.navigate("/test-folder");
        Assert.assertTrue(ast.isAssetPresent("2016nstPMS0547.jpg"));
        mov.navigate("");
        mov.uploadCsv("TEST FOLDER", "src/main/resources/data/bps/rename.csv");
//        Assert.assertFalse(as4.isAS400Errors(), "Some errors");
        ast.navigate("/test-folder");
        Assert.assertTrue(ast.isAssetPresent("RenameTest.jpg"));
    }

    @Test
    @TestName(name = "Move Upload Test")
    public void b_MoveUploadTest(){

        MovRnmUpldPage mov = MovRnmUpldPage.Instance;
        AssetsPage ast = AssetsPage.Instance;

        ast.logIn();
        ast.navigate("/test-folder");
        Assert.assertTrue(ast.isAssetPresent("Rename" +
                "Test.jpg"));
        mov.navigate("");
        mov.uploadCsv("TEST FOLDER", "src/main/resources/data/bps/move.csv");
//        Assert.assertFalse(as4.isAS400Errors(), "Some errors");
        ast.navigate("/web-production");
        Assert.assertTrue(ast.isAssetPresent("RenameTest.jpg"));
    }

    @Test
    @TestName(name = "Move/Rename Upload Test")
    public void c_MoveRenameUploadTest(){

        MovRnmUpldPage mov = MovRnmUpldPage.Instance;
        AssetsPage ast = AssetsPage.Instance;

        ast.logIn();
        ast.navigate("/web-production");
        Assert.assertTrue(ast.isAssetPresent("RenameTest.jpg"));
        mov.navigate("");
        mov.uploadCsv("TEST FOLDER", "src/main/resources/data/bps/moveNrename.csv");
//        Assert.assertFalse(as4.isAS400Errors(), "Some errors");
        ast.navigate("/test-folder");
        Assert.assertTrue(ast.isAssetPresent("2016nstPMS0547.jpg"));
    }
}
