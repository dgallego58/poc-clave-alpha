package co.com.bancolombia.cognito.signin.contract;

import co.com.bancolombia.cognito.signin.model.SignInDao;

public interface SignInUseCase {

    Object startSignInFlow(SignInDao signInDao);
}
