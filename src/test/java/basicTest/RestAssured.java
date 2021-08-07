package basicTest;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

public class RestAssured {

    /*
    given()
    when()
    then()
    */

    @Test
    public void createProject(){
        given()
            .header("Authorization","Basic cmdzaEB1Y2IuY29tOjEyMzQ1Ng==")
            .body("{\n" +
                "  \"Content\":\"UCB_Roy\",\n" +
                "  \"Icon\":\"5\"\n" +
                "}")
            .log()
            .all()
        .when()
            .post("http://todo.ly/api/projects.json")
        .then()
            .log()
            .all();

    }
    @Test
    public void createProject2(){
        given()
            .auth()
            .preemptive()
            .basic("rgsh@ucb.com","123456")
            //.header("Authorization","Basic cmdzaEB1Y2IuY29tOjEyMzQ1Ng==")
            .body("{\n" +
                "  \"Content\":\"UCB_Roy2\",\n" +
                "  \"Icon\":\"5\"\n" +
                "}")
            .log()
            .all()
            .when()
            .post("http://todo.ly/api/projects.json")
            .then()
            .log()
            .all();

    }

}
