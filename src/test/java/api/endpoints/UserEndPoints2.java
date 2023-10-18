package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndPoints2 {

    public static ResourceBundle getURL(){
        ResourceBundle routes = ResourceBundle.getBundle("routes");  //load routes.properties file
        return routes;
    }
    public static Response createUser(User payload){
        String postUrl = getURL().getString("post_url");
        Response response =
                given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(payload)
                .when()
                        .post(postUrl);

        return response;
    }

    public static Response readUser(String username){
        String getUrl = getURL().getString("get_url");

        Response response =
                given()
                    .pathParam("username", username)
                .when()
                    .get(Routes.get_url);

        return response;
    }

    public static Response updateUser(String username, User payload){
        String updateUrl = getURL().getString("update_url");

        Response response =
                given()
                    .pathParam("username", username)
                    .body(payload)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                .when()
                    .put(Routes.update_url);

        return response;
    }

    public static Response deleteUser(String username) {
        String deleteUrl = getURL().getString("delete_url");

        Response response =
                given()
                        .pathParam("username", username)
                        .when()
                        .delete(Routes.delete_url);

        return response;
    }
}
