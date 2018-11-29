package tests;

import helpers.BaseTest;
import helpers.UserHelper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DeleteUserTest extends BaseTest {

    UserHelper userHelper = new UserHelper();

    @Test
    public void verifyDeleteAllUsers() {
        userHelper.deleteAllUsers();
        userHelper.checkNumberOfUsers(0);
    }

}

