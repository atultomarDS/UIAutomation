package enpoints;

public enum UserEndpoint {

    GET_USERS("/user/all/json"),
    DELETE_USER("/user/all");
    private String url;

    UserEndpoint(String endpointUrl) {
        this.url = endpointUrl;
    }

    public String getUrl() {
        return url;
    }

}



