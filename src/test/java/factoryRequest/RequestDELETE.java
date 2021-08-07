package factoryRequest;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import util.ConfigENV;

public class RequestDELETE implements IRequest{
    @Override
    public Response send(RequestInformation information) {
        Response response=given()
                            .auth()
                            .preemptive()
                            .basic(ConfigENV.user,ConfigENV.password)
                            .log()
                            .all()
                        .when()
                            .delete(information.getUrl());
        response.then()
            .log()
            .all();
        return response;
    }
}

