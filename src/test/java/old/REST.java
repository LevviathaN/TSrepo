package old;

import annotations.TestName;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.BaseTest;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matcher.*;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;


public class REST extends BaseTest{

    String csv = "";
    @DataProvider(name = "test")
    public Iterator <Object[]> provider() throws IOException {
        List <Object> product = new ArrayList<>();
        String[] data = null;
        BufferedReader br = new BufferedReader(new FileReader(csv));
        String line;
        while ((line = br.readLine()) != null){
            data = line.split(",");
            product.add(data);
        }
        return (Iterator<Object[]>) product.iterator();
    }

//    @DataProvider(name = "products")
//    public Object[][] provider(){
//        return new Object[][]{
//                {13, 21},
//                {14, 21},
//                {15, 21},
//                {16, 21},
//                {17, 21},
//                {18, 21},
//                {13, 22},
//                {14, 22},
//                {15, 22},
//                {16, 22},
//                {17, 22},
//                {18, 22},
//        };
//    }



    @Test (dataProvider = "test")
    @TestName(name = "REST add to cart")

    public void HybridMattress_REST (String product, String url, int idAttribute, int sizeAttribute, int feelAttribute, int colorAttribute){

        RestAssured.baseURI = "https://www.tomorrowsleep.com";

        given().log().all()
                .cookie("PHPSESSID", "9sqf8c3gig7k8n9438lqli5gc2")
                .when().get("/mattresses")
                .then().log().headers()
                .log().cookies()
                .statusCode(200)
                .extract().response();

        Response response = given().log().all()
                .cookie("PHPSESSID", "9sqf8c3gig7k8n9438lqli5gc2")
                .cookie("form_key","oDM6xq5g739vnuCH")
                .param("form_key", "oDM6xq5g739vnuCH")
                .param("product", 5)
                .param("qty", 1)
                .param("super_attribute[178]", feelAttribute)
                .param("super_attribute[177]", sizeAttribute)
        .when().post("/checkout/cart/add/uenc/aHR0cHM6Ly93d3cudG9tb3Jyb3dzbGVlcC5jb20vbWF0dHJlc3Nlcw%2C%2C/product/5/")
                .then()
                .statusCode(302)
                //.log().cookies()
                .extract().response();

        //String stcode = response.getHeader("Location");
        Assert.assertTrue(response.getHeader("Location").equals("https://www.tomorrowsleep.com/checkout/cart/"));



//        given().log().all()
//                .cookie("PHPSESSID", "9sqf8c3gig7k8n9438lqli5gc2")
//                .cookie("form_key","oDM6xq5g739vnuCH");
//                when().get(stcode)
//                        .then().log().headers()
//                        .log().cookies()
//                        .statusCode(200);


    }
}
