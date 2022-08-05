package co.com.bancolombia.cognito.ops;

import co.com.bancolombia.cognito.config.CognitoClientFactory;
import co.com.bancolombia.cognito.properties.CognitoBaseProperties;
import co.com.bancolombia.cognito.signup.gateways.SignUpRepository;
import co.com.bancolombia.cognito.signup.model.ConfirmSignUpDao;
import co.com.bancolombia.cognito.signup.model.SignUpDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;

import java.util.List;

@Service
public class SignUpFlow implements SignUpRepository {

    private static final Logger log = LoggerFactory.getLogger(SignUpFlow.class);
    private final CognitoIdentityProviderClient client;
    private final String appClientId;

    public SignUpFlow() {
        this.client = CognitoClientFactory.syncCognitoClient();
        this.appClientId = CognitoBaseProperties.getInstance().getAppClientId();
    }

    @Override
    public Object signUp(SignUpDao request) {
        var email = AttributeType.builder().name("email").value(request.getEmail()).build();
        String secretHash = new HashingSecret().hashing(request.getUsername());
        var signUpResponse = client.signUp(signUpBuilder -> signUpBuilder.clientId(appClientId)
                                                                         .userAttributes(List.of(email))
                                                                         .username(request.getUsername())
                                                                         .password(request.getPassword())
                                                                         .secretHash(secretHash)
        );
        var respAsString = signUpResponse.toString();
        log.info("Response for SignUP {}", respAsString);
        return signUpResponse.codeDeliveryDetails();
    }

    @Override
    public Object confirmSignUp(final ConfirmSignUpDao confirmSignUpDao) {
        var secretHash = new HashingSecret().hashing(confirmSignUpDao.getUsername());
        var resp = client.confirmSignUp(
                builder -> builder.clientId(appClientId)
                                  .username(confirmSignUpDao.getUsername())
                                  .secretHash(secretHash)
                                  .confirmationCode(confirmSignUpDao.getConfirmationCode())
        );
        log.info("Confirmation {}", resp.sdkHttpResponse().statusText());
        return resp.sdkHttpResponse();
    }

    public Mono<?> asyncSignUp(SignUpDao request) {
        var email = AttributeType.builder().name("email").value(request.getEmail()).build();
        String secretHash = new HashingSecret().hashing(request.getUsername());
        var future = CognitoClientFactory.asyncCognitoClient()
                            .signUp(signUpBuilder -> signUpBuilder.clientId(appClientId)
                                                                  .userAttributes(List.of(email))
                                                                  .username(request.getUsername())
                                                                  .password(request.getPassword())
                                                                  .secretHash(secretHash));
        return Mono.fromFuture(future);
    }


}
