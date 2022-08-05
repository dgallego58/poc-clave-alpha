package co.com.bancolombia.cognito.signin.gateways;

import co.com.bancolombia.cognito.signin.model.SignInDao;

public interface SignInRepository {

    Object startSignInFlow(SignInDao signInDao);
}
