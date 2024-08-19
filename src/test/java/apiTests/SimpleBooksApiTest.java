package apiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import io.qameta.allure.restassured.AllureRestAssured;


public class SimpleBooksApiTest {
    String accessToken ;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmssSSS");
    String tenDigitString = dateFormat.format(new Date());
    @BeforeClass
    public void beforeClass() {
        baseURI = "https://simple-books-api.glitch.me/";
    }

    @Test
    public void registerWithNewEmail() {


        String clientName = "azanoon"+tenDigitString;
        String clientEmail = clientName+"@example.com";
        String requestBody = "{\n" +
                "  \"clientName\": \"" + clientName + "\",\n" +
                "  \"clientEmail\": \"" + clientEmail + "\"\n" +
                "}";
        Response response =
                given()
                        .filter(new AllureRestAssured())
                        .log().all()
                        .contentType(ContentType.JSON)
                        .body(requestBody)
                        .when()
                        .post("/api-clients/")
                        .then().log().all()
                        .statusCode(201) //
                        .extract()
                        .response();
        this.accessToken = response.jsonPath().getString("accessToken");
        System.out.println("Access Token: " + accessToken);
    }


    @Test
    public void registerWithExistUser() {
        String clientName = "azanoon"; // You can change this to avoid conflicts
        String clientEmail = "azanoon@example.com"; // You can change this to avoid conflicts
        String requestBody = "{\n" +
                "  \"clientName\": \"" + clientName + "\",\n" +
                "  \"clientEmail\": \"" + clientEmail + "\"\n" +
                "}";
                given()
                        .filter(new AllureRestAssured())
                        .contentType(ContentType.JSON)
                        .body(requestBody)
                        .when()
                        .post("/api-clients/")
                        .then().log().all()
                        .statusCode(409) //
                        .extract()
                        .response();
    }
    @Test
    public void getAllBooks() {
        given()
                .filter(new AllureRestAssured())
                .log().all().
                when().
                get("/books").
                then().log().all()
                .statusCode(200)
                .body("size()", equalTo(6))
                .extract()
                .response();

    }

    @Test
    public void submitOrder() {
        String requestBody = "{\n" +
                "  \"bookId\": 1,\n" +
                "  \"customerName\": \"azanoon\"\n" +
                "}";
        given()
                .filter(new AllureRestAssured())
                .header("Authorization", "Bearer " +"d3e84157f446a07d88098d136a628d1c8d849fbb5a7e87ea6c6f7c730302d0eb")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/orders")
                .then()
                .log().all()
                .statusCode(201)
                .body("orderId", notNullValue()) // Check if the orderId is returned
                .extract()
                .response();
    }

    @Test
    public void getOrders() {
        given()
                .header("Authorization", "Bearer " + "d3e84157f446a07d88098d136a628d1c8d849fbb5a7e87ea6c6f7c730302d0eb")
                .log().all()
                .when()
                .get("/orders")
                .then().log().all()
                .statusCode(200)
                .extract()
                .response();

    }

    @Test
    public void getOrder (){
        given()
                .header("Authorization", "Bearer " + "d3e84157f446a07d88098d136a628d1c8d849fbb5a7e87ea6c6f7c730302d0eb")
                .log().all()
                .when()
                .get("/orders/r-fT27Al_erOr6anbYHY5")
                .then().log().all()
                .statusCode(200)
                .extract()
                .response();

    }

@Test
    public void updateOrder() {
        String customerName = "azanoon"+tenDigitString; // You can change this to avoid conflicts
        String requestBody = "{\n" +
                "  \"customerName\": \"" + customerName + "\",\n" +
                "}";
        given()
                .filter(new AllureRestAssured())
                .header("Authorization", "Bearer " + "d3e84157f446a07d88098d136a628d1c8d849fbb5a7e87ea6c6f7c730302d0eb")
                .body(requestBody)
                .log().all()
                .when()
                .patch("/orders/r-fT27Al_erOr6anbYHY5")
                .then().log().all()
                .statusCode(204)
                .extract()
                .response();

    }


    @Test
    public void deleteOrder() {
        String customerName = "azanoon"+tenDigitString; // You can change this to avoid conflicts
        String requestBody = "{\n" +
                "  \"customerName\": \"" + customerName + "\",\n" +
                "}";
        given()
                .filter(new AllureRestAssured())
                .header("Authorization", "Bearer " + "d3e84157f446a07d88098d136a628d1c8d849fbb5a7e87ea6c6f7c730302d0eb")
                .body(requestBody)
                .log().all()
                .when()
                .delete("/orders/UfwxMNx1r_0DnCjGIfypV")
                .then().log().all()
                .statusCode(204)
                .extract()
                .response();

    }


}