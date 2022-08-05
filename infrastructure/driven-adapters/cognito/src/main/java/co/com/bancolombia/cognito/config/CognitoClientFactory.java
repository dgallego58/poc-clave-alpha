package co.com.bancolombia.cognito.config;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderAsyncClient;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;

public class CognitoClientFactory {

    private CognitoClientFactory() {
        //sonar
    }

    public static CognitoIdentityProviderClient syncCognitoClient() {
        return CognitoIdentityProviderClient.builder()
                                            .credentialsProvider(DefaultCredentialsProvider.create())
                                            .region(Region.US_EAST_1)
                                            .build();
    }

    public static CognitoIdentityProviderAsyncClient asyncCognitoClient(){
        return CognitoIdentityProviderAsyncClient.builder()
                       .credentialsProvider(DefaultCredentialsProvider.create())
                       .region(Region.US_EAST_1)
                       .build();
    }

}
