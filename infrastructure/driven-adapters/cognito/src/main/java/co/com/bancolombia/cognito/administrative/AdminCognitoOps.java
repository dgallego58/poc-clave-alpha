package co.com.bancolombia.cognito.administrative;

import co.com.bancolombia.cognito.admin.gateways.AdminOPS;
import co.com.bancolombia.cognito.admin.model.OpsForUser;
import co.com.bancolombia.cognito.config.CognitoClientFactory;
import co.com.bancolombia.cognito.properties.CognitoBaseProperties;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;

@Service
public class AdminCognitoOps implements AdminOPS {
    private final CognitoIdentityProviderClient client;
    private final String userPoolId;

    public AdminCognitoOps() {
        this.client = CognitoClientFactory.syncCognitoClient();
        this.userPoolId = CognitoBaseProperties.getInstance().getUserPoolId();

    }

    @Override
    public void disableUser(final OpsForUser opsForUser) {
        client.adminDisableUser(builder -> builder.userPoolId(userPoolId).username(opsForUser.getUsername()));

    }

    @Override
    public void deleteUser(final OpsForUser opsForUser) {
        client.adminDeleteUser(builder -> builder.userPoolId(userPoolId).username(opsForUser.getUsername()));

    }


}
