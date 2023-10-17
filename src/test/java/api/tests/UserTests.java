package api.tests;

import api.endpoints.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class UserTests {
    private Faker faker;
    private User user;

    private Logger logger;

    @BeforeClass
    public void setUp(){
        faker = new Faker();
        user = new User();

        user.setId(faker.idNumber().hashCode());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(faker.internet().safeEmailAddress());
        user.setPhone(faker.phoneNumber().cellPhone());
        user.setPassword(faker.internet().password(5, 10));
        user.setUsername(faker.name().username());

        //logger
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void posTest(){
        logger.info("Crested User beggin");
        Response response = UserEndPoints.createUser(user);
        response.then().log().all();
        logger.info(response.then().log().all());
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("Crested User ended");

    }

    @Test(priority = 2)
    public void getUserByNameTest(){
        Response response = UserEndPoints.readUser(user.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3)
    public void updateUserByNameTest(){
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndPoints.updateUser(user.getUsername(), user);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 4)
    public void deleteUserByNameTest(){

        Response response = UserEndPoints.deleteUser(user.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
