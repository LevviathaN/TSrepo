package bps;

import annotations.TestName;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.aem.AssetsPage;
import utils.BaseTest;

public class ContentFragmentTest extends BaseTest {

    @Test
    @TestName(name = "Content Fragment Create Test")
    public void a_contentFragmentCreateTest(){
        AssetsPage ast = AssetsPage.Instance;

        ast.logIn();
        ast.navigate("/test-folder");
        ast.createContentFragment("AutoTestCF");
        Assert.assertTrue(ast.isAssetPresent("AutoTestCF"));
    }

    @Test
    @TestName(name = "Create Content Fragment Variation Test")
    public void b_contentFragmentVariationTest(){
        AssetsPage ast = AssetsPage.Instance;

        ast.logIn();
        ast.navigate("/test-folder");
        ast.clickOnAsset("AutoTestCF");
        ast.createCfVariation("AutoTestVar");
        Assert.assertTrue(ast.isElementPresentAndDisplay(ast.byText("AutoTestVar")),
                "Variation is created");
    }

    @Test
    @TestName(name = "Delete Content Fragment Test")
    public void c_contentFragmentDeleteTest(){
        AssetsPage ast = AssetsPage.Instance;

        ast.logIn();
        ast.navigate("/test-folder");
        ast.selectAsset("AutoTestCF");
        ast.clickOnAsset("Create CF Test");
        ast.deleteSelectedAssets();
        Assert.assertFalse(ast.isAssetPresent("AutoTestCF") &&
                ast.isAssetPresent("Create CF Test"), "Assets are present");
    }
}
