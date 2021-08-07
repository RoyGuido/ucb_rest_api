package basicTest;

import static io.restassured.RestAssured.given;

import java.io.File;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

public class RestAssuredBody {


    @Test
    public void createProjectString(){
        given()
            .auth()
            .preemptive()
            .basic("rgsh@ucb.com","123456")
            //.header("Authorization","Basic cmdzaEB1Y2IuY29tOjEyMzQ1Ng==")
            .body("{\n" +
                "  \"Content\":\"UCB_EynarREST\",\n" +
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
    public void createProjectExternalFile(){

        given()
            .auth()
            .preemptive()
            .basic("rgsh@ucb.com","123456")
            //.header("Authorization","Basic cmdzaEB1Y2IuY29tOjEyMzQ1Ng==")
            .body(new File("D:\\Cursos\\Diplomado\\Modulo 3\\ucb_rest_api\\src\\test\\resources\\projectBody.json"))
            .log()
            .all()
            .when()
            .post("http://todo.ly/api/projects.json")
            .then()
            .log()
            .all();
    }
    @Test
    public void createProjectJSONObject(){
        JSONObject body = new JSONObject();
        body.put("Content","UCB_Roy4");
        body.put("Icon","5");
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
            .log()
            .all();
    }

}
