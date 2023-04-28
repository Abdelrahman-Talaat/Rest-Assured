import static io.restassured.RestAssured.get;
import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class RestTest_XML {
    public WebDriver driver;

    @Test
    public void getStatusCode(){
        //get status code
/*
        given().get("https://www.google.com").then().assertThat().statusCode(200);
*/

        //another way to get status code
        Response response= RestAssured.get("https://www.google.com");
        Assert.assertEquals(response.statusCode(),200);
    }
     @Test (dataProvider = "data")
     public void checkData(String customer_id,String Fname,String Lname, String street ,String city , String state , String zipCode , String phoneNumber , String ssn){
        String xml=RestAssured.get("https://parabank.parasoft.com/parabank/services/bank/customers/"+customer_id+"/").asString();
         XmlPath xmlPath=new XmlPath(xml).setRootPath("customer");
         Assert.assertEquals(xmlPath.getString("id"),customer_id);
         Assert.assertEquals(xmlPath.getString("firstName"),Fname);
         Assert.assertEquals(xmlPath.getString("lastName"),Lname);
         Assert.assertEquals(xmlPath.getString("address.street"),street);
         Assert.assertEquals(xmlPath.getString("address.city"),city);
         Assert.assertEquals(xmlPath.getString("address.state"),state);
         Assert.assertEquals(xmlPath.getString("address.zipCode"),zipCode);
         Assert.assertEquals(xmlPath.getString("phoneNumber"),phoneNumber);
         Assert.assertEquals(xmlPath.getString("ssn"),ssn);
    }

    @Test
    public void validate_invalid_response(){
        String xml=RestAssured.get("https://parabank.parasoft.com/parabank/services/bank/customers/12214/").asString();
        Assert.assertEquals(xml,"Could not find customer #12214");
    }

 @DataProvider (name = "data")
    public Object[][] data()  throws IOException, InvalidFormatException {
        Data_Driven_Excel obj=new Data_Driven_Excel();
        return obj.read_from_excel();
    }

}
