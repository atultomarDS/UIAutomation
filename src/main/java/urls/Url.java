package urls;

public enum Url {

    CREATE_NEW_USER("/user/new"),
    ALL_USERS("/users/all");
    private String value;

    Url(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}



