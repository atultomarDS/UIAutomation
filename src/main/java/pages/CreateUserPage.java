package pages;

import helpers.ErrorMessage;
import org.junit.Assert;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateUserPage extends BasePage {

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "confirmationPassword")
    private WebElement confirmPasswordInput;

    @FindBy(id = "user.email.error")
    private WebElement emailErrorMessage;

    @FindBy(id = "user.name.error")
    private WebElement nameErrorMessage;

    @FindBy(id = "user.password.error")
    private WebElement passwordErrorMessage;

    @FindBy(id = "user.confirmationPassword.error")
    private WebElement confirmPasswordErrorMessage;

    @FindBy(css = "a[href='/users/all']")
    private WebElement allUsersButton;

    public CreateUserPage enterUserName(String username) {
        nameInput.sendKeys(username);
        return this;
    }

    public CreateUserPage enterPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public CreateUserPage setConfirmationPassword(String password) {
        confirmPasswordInput.sendKeys(password);
        return this;
    }

    public CreateUserPage enterEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public CreateUserPage submit() {
        nameInput.submit();
        return this;
    }

    public CreateUserPage clickAllUsersButton() {
        allUsersButton.click();
        return this;
    }

    public CreateUserPage checkNameHasError(ErrorMessage errorMessage) {
        verifyErrorPresentForField(nameErrorMessage, errorMessage.getMessageText());
        return this;
    }

    public CreateUserPage checkEmailHasError(ErrorMessage errorMessage) {
        verifyErrorPresentForField(emailErrorMessage, errorMessage.getMessageText());
        return this;
    }

    public CreateUserPage checkPasswordHasError(ErrorMessage errorMessage) {
        verifyErrorPresentForField(passwordErrorMessage, errorMessage.getMessageText());
        return this;
    }

    public CreateUserPage checkConfirmPasswordHasError(ErrorMessage errorMessage) {
        verifyErrorPresentForField(confirmPasswordErrorMessage, errorMessage.getMessageText());
        return this;
    }

    public CreateUserPage verifyErrorPresentForField(WebElement element, String errorMessage) {
        if (!element.isDisplayed()) {
            throw new ElementNotVisibleException("Element [" + element + " ] is not displayed");
        }
        Assert.assertTrue(" Error message is expected to be [ " + errorMessage + " ] but is [ " + element.getText() + " ] ",
                element.getText().equalsIgnoreCase(errorMessage));
        return this;
    }



}

