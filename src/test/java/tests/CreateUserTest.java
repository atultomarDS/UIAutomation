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
public class CreateUserTest extends BaseTest {
    CreateUserPage createUserPage = new CreateUserPage();
    UserHelper userHelper = new UserHelper();

    @Test
    public void createNewUserAndVerifyItsCreated() {
        User user = userHelper.setUserDetails();
        createUserPage.enterUserName(user.name)
                .enterEmail(user.email)
                .enterPassword(user.password)
                .setConfirmationPassword(user.password)
                .submit();
        verifyCurrentURL(Url.ALL_USERS.getValue());
        User actualUser = UserHelper.getUser(user.email);
        Assert.assertTrue("User details is not same as expected", user.equals(actualUser));
    }

    @Test
    public void checkErrorOnEmptyFields() {
        createUserPage.enterUserName("")
                .enterEmail("")
                .enterPassword("")
                .setConfirmationPassword("")
                .submit();
        verifyCurrentURL(Url.CREATE_NEW_USER.getValue());
        createUserPage.checkNameHasError(ErrorMessage.FIELD_IS_REQUIRED);
        createUserPage.checkEmailHasError(ErrorMessage.FIELD_IS_REQUIRED);
        createUserPage.checkPasswordHasError(ErrorMessage.FIELD_IS_REQUIRED);
    }

    @Test()
    public void errorOnNonUniqueFields() {
        User user = userHelper.setUserDetails();
        createUserPage.enterUserName(user.name)
                .enterEmail(user.email)
                .enterPassword(user.password)
                .setConfirmationPassword(user.password)
                .submit();
        User actualUser = UserHelper.getUser(user.email);
        Assert.assertTrue("Users are not same", user.equals(actualUser));
        goToURL(FileLoader.getBaseURL());
        createUserPage.enterUserName(user.name)
                .enterEmail(user.email)
                .submit();
        createUserPage.checkNameHasError(ErrorMessage.UNIQUE_FIELD);
        createUserPage.checkEmailHasError(ErrorMessage.UNIQUE_FIELD);
    }

    @Test
    public void errorOnNonMatchingPasswords() {
        String randomPassword = RandomStringUtils.randomAlphabetic(10);
        String randomConfirmPassword = RandomStringUtils.randomAlphabetic(10);
        createUserPage
                .enterPassword(randomPassword)
                .setConfirmationPassword(randomConfirmPassword)
                .submit();
        verifyCurrentURL(Url.CREATE_NEW_USER.getValue());
        createUserPage.checkConfirmPasswordHasError(ErrorMessage.PASSWORD_DONT_MATCH);
    }

    @Test
    public void errorOnWrongEmailSyntax() {
        String wrongEmail = RandomStringUtils.randomAlphabetic(3);
        createUserPage.enterEmail(wrongEmail)
                .submit();
        verifyCurrentURL(Url.CREATE_NEW_USER.getValue());
        createUserPage.checkEmailHasError(ErrorMessage.INVALID_EMAIL_ADDRESS);
    }

    @Test
    public void errorOnLessThan6CharPassword() {
        String lessThan6CharactersPassword = RandomStringUtils.randomAlphabetic(3);
        createUserPage.enterPassword(lessThan6CharactersPassword)
                .setConfirmationPassword(lessThan6CharactersPassword)
                .submit();
        verifyCurrentURL(Url.CREATE_NEW_USER.getValue());
        createUserPage.checkPasswordHasError(ErrorMessage.MINIMUM_PASSWORD_LENGTH);
    }

    @Test
    //THIS TEST FAILS CURRENTLY BECAUSE THIS USER IS NOT GETTING SAVED. ALERT!
    public void createUserWithXssScript() {
        String random_email = "Atul" + System.currentTimeMillis() + "@depositsolution.com";
        String xssScript = "<script>alert(document.cookie);</script>";
        createUserPage.enterUserName(xssScript)
                .enterEmail(random_email)
                .enterPassword(xssScript)
                .setConfirmationPassword(xssScript)
                .submit();
        verifyCurrentURL(Url.ALL_USERS.getValue());
        User actualUser = UserHelper.getUser(xssScript);
        Assert.assertEquals("Email is not same", actualUser.email, xssScript);
        Assert.assertEquals("Name is not same", actualUser.name, xssScript);
        Assert.assertEquals("Passsword is not same", actualUser.password, xssScript);
    }

    @Test
    public void createUserWithLongAndSpecialCharacters() {
        String long_name = "23456789876543123456789123456789012345678901234567890!@#$%^&*()_+{:???<~~" + System.currentTimeMillis();
        String long_email = "email234567898765431234567891234567890123456789012345678901234567890!#$%"+ System.currentTimeMillis() + "@depositsolution.com";
        String long_password = "12345678901234567!@#$%^&*()_+{:???<~~" ;
        userHelper.deleteAllUsers();
        createUserPage.enterUserName(long_name)
                .enterEmail(long_email)
                .enterPassword(long_password)
                .setConfirmationPassword(long_password)
                .submit();
        verifyCurrentURL(Url.ALL_USERS.getValue());
        User actualUser = UserHelper.getUser(long_email);
        Assert.assertEquals("Email is not correct", actualUser.email, long_email);
        Assert.assertEquals("Name is not correct", actualUser.name, long_name);
        Assert.assertEquals("Passsword is not correct", actualUser.password, long_password);
    }

    @Test
    public void navigateToAllUsersPage() {
        createUserPage.clickAllUsersButton();
        verifyCurrentURL(Url.ALL_USERS.getValue());
    }

}

