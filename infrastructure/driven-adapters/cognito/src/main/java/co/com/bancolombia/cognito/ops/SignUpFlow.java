package co.com.bancolombia.cognito.ops;

import co.com.bancolombia.cognito.config.CognitoClientFactory;
import co.com.bancolombia.cognito.properties.CognitoBaseProperties;
import co.com.bancolombia.cognito.signup.gateways.SignUpRepository;
import co.com.bancolombia.cognito.signup.model.ConfirmSignUpVO;
import co.com.bancolombia.cognito.signup.model.ConfirmationState;
import co.com.bancolombia.cognito.signup.model.SignUpResponseVO;
import co.com.bancolombia.cognito.signup.model.SignUpVO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;

import java.util.List;

@Service
public class SignUpFlow implements SignUpRepository {

    private final CognitoIdentityProviderClient client;
    private final String appClientId;

    public SignUpFlow() {
        this.client = CognitoClientFactory.syncCognitoClient();
        this.appClientId = CognitoBaseProperties.getInstance().getAppClientId();
    }

    @Override
    public SignUpResponseVO signUp(SignUpVO request) {
        var email = AttributeType.builder().name("email").value(request.getEmail()).build();
        String secretHash = new HashingSecret().hashing(request.getUsername());
        var signUpResponse = client.signUp(signUpBuilder -> signUpBuilder.clientId(appClientId)
                                                                         .userAttributes(List.of(email))
                                                                         .username(request.getUsername())
                                                                         .password(request.getPassword())
                                                                         .secretHash(secretHash)
        );
        return SignUpResponseVO.builder()
                               .deliveryMedium(signUpResponse.codeDeliveryDetails().deliveryMediumAsString())
                               .destination(signUpResponse.codeDeliveryDetails().destination())
                               .attributeName(signUpResponse.codeDeliveryDetails().attributeName())
                               .build();
    }

    @Override
    public ConfirmationState confirmSignUp(final ConfirmSignUpVO confirmSignUpVO) {
        var secretHash = new HashingSecret().hashing(confirmSignUpVO.getUsername());
        var resp = client.confirmSignUp(builder -> builder.clientId(appClientId)
                                                          .username(confirmSignUpVO.getUsername())
                                                          .secretHash(secretHash)
                                                          .confirmationCode(confirmSignUpVO.getConfirmationCode())
        );
        return ConfirmationState.builder().success(resp.sdkHttpResponse().isSuccessful()).build();
    }

    @Override
    public Mono<SignUpResponseVO> asyncSignUp(SignUpVO request) {
        var email = AttributeType.builder().name("email").value(request.getEmail()).build();
        String secretHash = new HashingSecret().hashing(request.getUsername());
        var future = CognitoClientFactory.asyncCognitoClient()
                                         .signUp(signUpBuilder -> signUpBuilder.clientId(appClientId)
                                                                               .userAttributes(List.of(email))
                                                                               .username(request.getUsername())
                                                                               .password(request.getPassword())
                                                                               .secretHash(secretHash));
        return Mono.fromFuture(future)
                   .map(signUpResponse -> SignUpResponseVO.builder()
                                                          .deliveryMedium(signUpResponse.codeDeliveryDetails()
                                                                                        .deliveryMediumAsString())
                                                          .destination(
                                                                  signUpResponse.codeDeliveryDetails().destination())
                                                          .attributeName(
                                                                  signUpResponse.codeDeliveryDetails().attributeName())
                                                          .build()
                   );

    }


}
