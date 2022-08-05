package co.com.bancolombia.cognito.properties;

import lombok.Getter;

@Getter
public class CognitoBaseProperties {
    private final String appClientId;
    private final String appClientSecret;
    private final String userPoolId;

    private CognitoBaseProperties() {
        this.appClientId = System.getenv("app-client-id");
        this.appClientSecret = System.getenv("app-client-secret");
        this.userPoolId = System.getenv("user-pool-id");
    }

    public static CognitoBaseProperties getInstance() {
        return Helper.INSTANCE;
    }

    private static class Helper {
        private static final CognitoBaseProperties INSTANCE = new CognitoBaseProperties();
    }

}
