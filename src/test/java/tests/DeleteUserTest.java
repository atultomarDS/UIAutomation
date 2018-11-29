package tests;

import helpers.BaseTest;
import helpers.ErrorMessage;
import helpers.FileLoader;
import helpers.UserHelper;
import objects.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pages.CreateUserPage;
import urls.Url;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DeleteUserTest extends BaseTest {

    UserHelper userHelper = new UserHelper();

    @Test
    public void verifyDeleteAllUsers() {
        userHelper.deleteAllUsers();
        userHelper.checkNumberOfUsers(0);
    }

}

