package basicTest;

import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.equalTo;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

public class RestAssuredExtract {
    @Test
    public void restAssuredVerification(){
        JSONObject body = new JSONObject();
        body.put("Content","UCB_Roy_Check2");
        body.put("Icon","1");
        given()
            .auth()
            .preemptive()
            .basic("rgsh@ucb.com","123456")
            //.header("Authorization","Basic cmdzaEB1Y2IuY29tOjEyMzQ1Ng==")
            .body(body.toString())
            .log()
            .all()
       .when()
            .post("http://todo.ly/api/projects.json")
       .then()
            .statusCode(200)
            .body("Content",equalTo("UCB_Roy_Check2"))
            .body("Icon",equalTo(1))
            .log()
            .all();

    }
    @Test
    public void restAssuredExtract(){
        JSONObject body = new JSONObject();
        body.put("Content","UCB_Roy_Check2");
        body.put("Icon","1");
        Response response = (Response) given()
            .auth()
            .preemptive()
            .basic("rgsh@ucb.com","123456")
            //.header("Authorization","Basic cmdzaEB1Y2IuY29tOjEyMzQ1Ng==")
            .body(body.toString())
            .log()
            .all()
        .when()
            .post("http://todo.ly/api/projects.json");
        response.then()
            .statusCode(200)
            .body("Content",equalTo("UCB_Roy_Check2"))
            .body("Icon",equalTo(1))
            .log()
            .all();

    }
    @Test
    public void restAssuredExtract2() {
        JSONObject body = new JSONObject();
        body.put("Content", "RoyCheck");
        body.put("Icon", 1);

        Response response = given()
            .auth()
            .preemptive()
            .basic("rgsh@ucb.com", "123456")
            .body(body.toString())
            .log()
            .all()
        .when()
            .post("http://todo.ly/api/projects.json");

        response.then()
            .statusCode(200)
            .body("Content", equalTo("RoyCheck"))
            .body("Icon", equalTo(1))
            .log()
            .all();

        int id = response.then().extract().path("Id");
        System.out.println("****** ID ***** : " + id);
    }
}
