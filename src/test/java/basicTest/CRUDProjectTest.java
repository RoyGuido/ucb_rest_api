package basicTest;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
public class CRUDProjectTest {
    @Test
    public void crudProjectRestApi(){
        // Creacion
        JSONObject body= new JSONObject();
        body.put("Content","EynaCRUD");
        body.put("Icon",1);

        Response response=given()
            .auth()
            .preemptive()
            .basic("ucb@ucb2021.com","12345")
            .body(body.toString())
            .log()
            .all()
            .when()
            .post("http://todo.ly/api/projects.json");

        response.then()
            .statusCode(200)
            .body("Content", equalTo("EynaCRUD"))
            .body("Icon",equalTo(1))
            .log()
            .all();

        int id = response.then().extract().path("Id");

        // Actualizacion
        body.put("Content","EynarCRUDUpdate");
        body.put("Icon",4);


        response=given()
            .auth()
            .preemptive()
            .basic("ucb@ucb2021.com","12345")
            .body(body.toString())
            .log()
            .all()
            .when()
            .put("http://todo.ly/api/projects/"+id+".json");

        response.then()
            .statusCode(200)
            .body("Content", equalTo("EynarCRUDUpdate"))
            .body("Icon",equalTo(4))
            .log()
            .all();
// Get
        response=given()
            .auth()
            .preemptive()
            .basic("ucb@ucb2021.com","12345")
            .log()
            .all()
            .when()
            .get("http://todo.ly/api/projects/"+id+".json");

        response.then()
            .statusCode(200)
            .body("Content", equalTo("EynarCRUDUpdate"))
            .body("Icon",equalTo(4))
            .log()
            .all();
        // Delete
        response=given()
            .auth()
            .preemptive()
            .basic("ucb@ucb2021.com","12345")
            .log()
            .all()
            .when()
            .delete("http://todo.ly/api/projects/"+id+".json");

        response.then()
            .statusCode(200)
            .body("Content", equalTo("EynarCRUDUpdate"))
            .body("Icon",equalTo(4))
            .body("Deleted",equalTo(true))
            .log()
            .all();




    }


}
