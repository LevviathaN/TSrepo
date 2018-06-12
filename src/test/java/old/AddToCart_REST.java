package old;

import annotations.TestName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.FileIO;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static pages.BasePage.BASE_URL;


public class AddToCart_REST extends BaseTest{

    String csv = "src/main/resources/products_info.csv";

    @DataProvider(name = "test", parallel = true)
    public Iterator <Object[]> provider() throws IOException {

        List <Object[]> product = new ArrayList<>();
        String[] info = null;
        BufferedReader reader = new BufferedReader(new FileReader(csv));
        String line;

        while ((line = reader.readLine()) != null){
            info = line.split(",");
            product.add(info);
        }

        return product.iterator();
    }

    @Test (dataProvider = "test")
    @TestName(name = "REST add to cart")

    public void add_to_cart_REST (String product, String url, String sizeAttribute, String feelAttribute, String colorAttribute, String id){

        RestAssured.baseURI = BASE_URL + "/checkout/cart/add/uenc/";

        Response response = given().log().all()
                .param("product", id)
                .param("qty", 1)
                .param("super_attribute[178]", feelAttribute)
                .param("super_attribute[177]", sizeAttribute)
                .param("super_attribute[93]", colorAttribute)

            .when()
                .post(url)

            .then()
                .statusCode(302)
                .log().cookies()
                .extract().response();

        Assert.assertEquals("https://www.tomorrowsleep.com/checkout/cart/", response.getHeader("Location"), "Failed to add " + product + " to cart");
    }
}
