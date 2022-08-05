package co.com.bancolombia.cognito.signin.usecase;

import co.com.bancolombia.cognito.signin.contract.SignInUseCase;
import co.com.bancolombia.cognito.signin.gateways.SignInRepository;
import co.com.bancolombia.cognito.signin.model.SignInDao;

public class SignInService implements SignInUseCase {

    private final SignInRepository signInRepository;

    public SignInService(final SignInRepository signInRepository) {
        this.signInRepository = signInRepository;
    }

    @Override
    public Object startSignInFlow(SignInDao signInDao) {
        return signInRepository.startSignInFlow(signInDao);
    }

}
