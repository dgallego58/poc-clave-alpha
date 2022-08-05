package co.com.bancolombia.cognito.signup.contract;

import co.com.bancolombia.cognito.signup.model.ConfirmSignUpVO;
import co.com.bancolombia.cognito.signup.model.ConfirmationState;
import co.com.bancolombia.cognito.signup.model.SignUpResponseVO;
import co.com.bancolombia.cognito.signup.model.SignUpVO;
import reactor.core.publisher.Mono;

public interface SignUpUseCase {

    SignUpResponseVO startSignUpFlow(SignUpVO signUpVO);

    ConfirmationState confirmSignUp(ConfirmSignUpVO confirmSignUpVO);

    Mono<SignUpResponseVO> reactiveSignUpFlow(SignUpVO signUpVO);
}
