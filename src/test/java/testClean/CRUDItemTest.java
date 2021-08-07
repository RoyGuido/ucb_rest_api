package testClean;

import static org.hamcrest.Matchers.equalTo;

import factoryRequest.FactoryRequest;
import factoryRequest.RequestInformation;
import io.restassured.response.Response;
import java.io.IOException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.ConfigAPI;
import util.GetProperties;


public class CRUDItemTest {
    @BeforeEach
    public void before() throws IOException {
        new GetProperties().leerPropiedades();
    }

    @Test
        public void verifyCRUDforItem(){
        JSONObject body = new JSONObject();
        body.put("Content","ItemPrueba");

        RequestInformation request = new RequestInformation(ConfigAPI.CREATE_ITEM,body.toString());
        Response response = FactoryRequest.make(FactoryRequest.POST).send(request);
        response.then()
            .statusCode(200)
            .body("Content", equalTo("ItemPrueba"));
        String id = response.then().extract().path("Id")+"";

        body.put("Content","ItemPruebaUpdate");
        request = new RequestInformation(ConfigAPI.UPDATE_ITEM.replace("ID",id),body.toString());
        response = FactoryRequest.make(FactoryRequest.PUT).send(request);

        response.then()
            .statusCode(200)
            .body("Content", equalTo("ItemPruebaUpdate"));

        request = new RequestInformation(ConfigAPI.READ_ITEM.replace("ID",id),"");
        response = FactoryRequest.make(FactoryRequest.GET).send(request);

        response.then()
            .statusCode(200)
            .body("Content", equalTo("ItemPruebaUpdate"));

        request = new RequestInformation(ConfigAPI.DELETE_ITEM.replace("ID",id),"");
        response = FactoryRequest.make(FactoryRequest.DELETE).send(request);

        response.then()
            .statusCode(200)
            .body("Deleted", equalTo(true));

    }

}
