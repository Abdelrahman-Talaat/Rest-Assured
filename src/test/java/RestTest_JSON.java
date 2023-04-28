import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestTest_JSON {
    @Test
    public void validateStatusCode(){
        given().get("https://reqres.in/api/users?page=2").then().assertThat().statusCode(200);
    }
    @Test
    public void validateData(){
        given().get("https://reqres.in/api/users?page=2").then().assertThat().body("data[0].'id'",equalTo(7)).
                and().assertThat().body("data[0].'email'",equalTo("michael.lawson@reqres.in")) .
                and().assertThat().body("data[0].'first_name'",equalTo("Michael")).
                and().assertThat().body("data[0].'last_name'",equalTo("Lawson")).
                and().assertThat().body("data[0].'avatar'",equalTo("https://reqres.in/img/faces/7-image.jpg"));

    }
}
