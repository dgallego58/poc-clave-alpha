package co.com.bancolombia.cognito.signup.gateways;

import co.com.bancolombia.cognito.signup.model.ConfirmSignUpVO;
import co.com.bancolombia.cognito.signup.model.ConfirmationState;
import co.com.bancolombia.cognito.signup.model.SignUpResponseVO;
import co.com.bancolombia.cognito.signup.model.SignUpVO;
import reactor.core.publisher.Mono;

public interface SignUpRepository {

    SignUpResponseVO signUp(SignUpVO signUpVO);

    ConfirmationState confirmSignUp(ConfirmSignUpVO confirmSignUpVO);

    Mono<SignUpResponseVO> asyncSignUp(SignUpVO request);
}
