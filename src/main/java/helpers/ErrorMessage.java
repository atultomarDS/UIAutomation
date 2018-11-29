package helpers;

public enum ErrorMessage {

    FIELD_IS_REQUIRED("Required"),
    INVALID_EMAIL_ADDRESS("Invalid email address"),
    UNIQUE_FIELD("Must be unique"),
    MINIMUM_PASSWORD_LENGTH("Minimum size is 6"),
    PASSWORD_DONT_MATCH("passwords are not the same");

    private String messageText;

    ErrorMessage(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageText() {
        return messageText;
    }

}



