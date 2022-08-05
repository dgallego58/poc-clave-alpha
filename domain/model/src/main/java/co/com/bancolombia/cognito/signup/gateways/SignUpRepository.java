package co.com.bancolombia.cognito.signup.gateways;

import co.com.bancolombia.cognito.signup.model.ConfirmSignUpDao;
import co.com.bancolombia.cognito.signup.model.SignUpDao;

public interface SignUpRepository {

    Object signUp(SignUpDao signUpDao);

    Object confirmSignUp(ConfirmSignUpDao confirmSignUpDao);

}
