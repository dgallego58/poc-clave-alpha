package co.com.bancolombia.cognito.ops;

import co.com.bancolombia.cognito.config.CognitoClientFactory;
import co.com.bancolombia.cognito.properties.CognitoBaseProperties;
import co.com.bancolombia.cognito.signin.gateways.SignInRepository;
import co.com.bancolombia.cognito.signin.model.SignInVO;
import co.com.bancolombia.cognito.signin.model.SignInResponseVO;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AuthFlowType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

@Service
public class InitAuthFlow implements SignInRepository {


    private final CognitoIdentityProviderClient client;
    private final String appClientId;

    public InitAuthFlow() {
        this.client = CognitoClientFactory.syncCognitoClient();
        this.appClientId = CognitoBaseProperties.getInstance().getAppClientId();
    }

    @Override
    public SignInResponseVO startSignInFlow(SignInVO signInVO) {
        UnaryOperator<String> hashed = usrName -> new HashingSecret().hashing(usrName);
        var mutable = new HashMap<>(signInVO.asParameters());
        mutable.putIfAbsent("SECRET_HASH", hashed.apply(signInVO.getUsername()));

        var resp = client.initiateAuth(authBuilder -> authBuilder.authFlow(AuthFlowType.USER_PASSWORD_AUTH)
                                                                 .authParameters(Map.copyOf(mutable))
                                                                 .clientId(appClientId));
        var result = resp.authenticationResult();
        return SignInResponseVO.builder()
                               .accessToken(result.accessToken())
                               .type(result.tokenType())
                               .expiresIn(result.expiresIn())
                               .refreshToken(result.refreshToken())
                               .idToken(result.idToken())
                               .build();
    }

}
