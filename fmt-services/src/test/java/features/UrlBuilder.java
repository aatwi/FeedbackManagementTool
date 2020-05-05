package features;

public final class UrlBuilder {

    protected static final String HOME_PAGE = "http://localhost:8082/";
    protected static final String USER_MANAGEMENT_URL = HOME_PAGE + "api/users";
    protected static final String USER_CREATION_URL = USER_MANAGEMENT_URL + "/create/";

    public UrlBuilder() {
    }
}
