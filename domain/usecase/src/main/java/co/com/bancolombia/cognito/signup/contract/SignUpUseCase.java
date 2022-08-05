package co.com.bancolombia.cognito.signup.contract;

import co.com.bancolombia.cognito.signup.model.ConfirmSignUpDao;
import co.com.bancolombia.cognito.signup.model.SignUpDao;

public interface SignUpUseCase {

    Object startSignUpFlow(SignUpDao signUpDao);

    Object confirmSignUp(ConfirmSignUpDao confirmSignUpDao);
}
