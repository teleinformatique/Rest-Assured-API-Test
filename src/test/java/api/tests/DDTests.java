package api.tests;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DDTests {

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void postUserTest(String id,String fname, String lname, String email, String pw, String ph, String username){
        User user = new User();
        user.setUsername(username);
        user.setId(Integer.parseInt(id));
        user.setPassword(pw);
        user.setEmail(email);
        user.setPhone(ph);
        user.setLastName(lname);
        user.setFirstName(fname);

        Response response = UserEndPoints.createUser(user);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2, dataProvider="UserNames", dataProviderClass = DataProviders.class)
    public void deleteUserByNameTest(String username){
        Response response = UserEndPoints.deleteUser(username);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
