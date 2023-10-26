package steps;

import io.qameta.allure.Step;
import models.UserResponse;
import models.requestModels.JobRequest;
import models.requestModels.LoginRequest;
import models.requestModels.RegRequest;
import models.responseModels.DataResponse;
import models.responseModels.LoginResponse;
import models.responseModels.RegResponse;
import models.responseModels.SupportResponse;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class ReqresApiSteps {
    @Step
    public void getUserById(Integer id) {
        UserResponse userResponse = given()
                .when()
                .get("api/users/" + id)
                .then()
                .statusCode(200)
                .extract().response().body().as(UserResponse.class);
        DataResponse dataResponse = new DataResponse(2, "janet.weaver@reqres.in", "Janet", "Weaver", "https://reqres.in/img/faces/2-image.jpg");
        SupportResponse supportResponse = new SupportResponse("https://reqres.in/#support-heading", "To keep ReqRes free, contributions towards server costs are appreciated!");
        UserResponse expectedResponse = new UserResponse(dataResponse, supportResponse);
        Assert.assertEquals(userResponse, expectedResponse);
    }

    @Step
    public void createUser(String name, String job) {
        given()
                .body(new JobRequest(name, job))
                .when().spec(requestSpecification)
                .post("api/users")
                .then()
                .statusCode(201).log().all();
    }

    @Step
    public void updateUser(String name, String job) {
        given().when().spec(requestSpecification)
                .body(new JobRequest(name, job))
                .put("api/users/2")
                .then().log().all().statusCode(200);
    }

    @Step
    public void deleteUser() {
        given()
                .when().spec(requestSpecification)
                .delete("/api/users/2")
                .then().statusCode(204).log().all();
    }

    @Step
    public void checkSuccessReg(String email, String password) {
        RegResponse actualResponse = given()
                .when().spec(requestSpecification)
                .body(new RegRequest(email, password))
                .post("api/register")
                .then().log().all().statusCode(200)
                .extract().response().body().as(RegResponse.class);
        Assert.assertEquals(actualResponse.id.toString(), "4");
        Assert.assertEquals(actualResponse.token, "QpwL5tke4Pnpja7X4");
    }

    @Step
    public void checkRegUnSuccess(String email) {
        RegResponse actualResponse = given()
                .when().spec(requestSpecification)
                .body(new RegRequest(email))
                .post("api/register")
                .then().log().all().statusCode(400)
                .extract().response().body().as(RegResponse.class);
        Assert.assertEquals(actualResponse.error, "Missing password");
    }

    @Step
    public void checkLogSuccess(String email, String password) {
        LoginResponse actualResponse = given()
                .when().spec(requestSpecification)
                .body(new LoginRequest(email, password))
                .post("api/login")
                .then().log().all().statusCode(200)
                .extract().response().body().as(LoginResponse.class);
        Assert.assertEquals(actualResponse.token, "QpwL5tke4Pnpja7X4");
    }

    @Step
    public void checkLogUnSuccess(String email) {
        LoginResponse actualResponse = given()
                .when().spec(requestSpecification)
                .body(new LoginRequest(email))
                .post("api/login")
                .then().log().all().statusCode(400)
                .extract().response().body().as(LoginResponse.class);
        Assert.assertEquals(actualResponse.error, "Missing password");
    }

    @Step
    public void getSingleResource() {
        given().when().spec(requestSpecification)
                .get("api/unknown/2")
                .then().statusCode(200).log().all();
    }

    @Step
    public void getNotExistResource() {
        given().when().spec(requestSpecification)
                .get("api/unknown/23")
                .then().statusCode(404).log().all();
    }

    @Step
    public void getNoExUser(Integer id) {
        given()
                .when().spec(requestSpecification)
                .get("api/users/" + id)
                .then().statusCode(404).log().all();
    }

    @Step
    public void getDelayUserList() {
        given().when().spec(requestSpecification)
                .get("api/users?delay=3")
                .then().statusCode(200).log().all();
    }

    @Step("Получить список ресурсов")
    public void getListResource() {
        given().when().spec(requestSpecification)
                .get("api/unknown")
                .then().statusCode(200).log().all();
    }

    @Step
    public void getUsersList() {
        given().when().spec(requestSpecification)
                .get("api/users?page=2")
                .then().statusCode(200).log().all();
    }

}
