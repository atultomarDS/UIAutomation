package helpers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import enpoints.UserEndpoint;
import io.restassured.response.Response;
import dataObjects.User;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.io.*;
import java.util.List;

public class UserHelper {

    public static final Logger logger = Logger.getLogger(UserHelper.class);
    private static Gson gson = new Gson();
    private static String random_email = "Atul" + System.currentTimeMillis() + "@depositsolution.com";
    private static String random_name = "Atul" + System.currentTimeMillis();

    public static Response getAllUsers() {
        Response allUsers = ApiHelper.get(UserEndpoint.GET_USERS.getUrl());
        return allUsers;
    }

    public static User getUser(String email) {
        User actualUser = null;
        Response allUsers = getAllUsers();
        List<User> allUser = allUsers.jsonPath().getList("", User.class);
        for (User user : allUser) {
            if (user.email.equals(email)) {
                actualUser = user;
                System.out.println(actualUser.toString());
            }
        }
        return actualUser;
    }

    public static void checkNumberOfUsers(int numberOfUsers) {
        Response allUsers = getAllUsers();
        List<User> allUser = allUsers.jsonPath().getList("");

        Assert.assertEquals(allUser.size(), numberOfUsers);
    }

    public User setUserDetails() {
        User user = null;
        try (Reader reader = JsonHelper.getJsonRequest("user.json")) {
            // Convert JSON to Java Object
            user = gson.fromJson(reader, User.class);
            user.setEmail(random_email);
            user.setName(random_name);
            logger.info("New user created as " + user.toString());
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException je) {
            System.out.println("Json couldn't be parsed correctly as " + je);
        }
        return user;
    }

    public void deleteAllUsers() {
        ApiHelper.delete(UserEndpoint.DELETE_USER.getUrl());
    }

}
