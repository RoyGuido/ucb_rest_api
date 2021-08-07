package basicTest;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonSchemaFactoryBuilder;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

public class RestAssuredSchemaValidation {
    @Test
    public void restAssuredSchema() {
        JSONObject body = new JSONObject();
        body.put("Content", "RoyCheck");
        body.put("Icon", 1);

        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder()
            .setValidationConfiguration(ValidationConfiguration.newBuilder()
                .setDefaultVersion(SchemaVersion.DRAFTV4).freeze()).freeze();

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
        response.then()
            .body(matchesJsonSchemaInClasspath("validationSchema.json").using(jsonSchemaFactory));

        int id = response.then().extract().path("Id");
        System.out.println("****** ID ***** : " + id);
    }
}
