package co.com.bancolombia.cognito.signup.usecase;

import co.com.bancolombia.cognito.signup.contract.SignUpUseCase;
import co.com.bancolombia.cognito.signup.gateways.SignUpRepository;
import co.com.bancolombia.cognito.signup.model.ConfirmSignUpDao;
import co.com.bancolombia.cognito.signup.model.SignUpDao;

public class SignUpService implements SignUpUseCase {

    private final SignUpRepository signUpRepository;

    public SignUpService(final SignUpRepository signUpRepository) {
        this.signUpRepository = signUpRepository;
    }

    @Override
    public Object startSignUpFlow(SignUpDao signUp) {
        return signUpRepository.signUp(signUp);
    }

    @Override
    public Object confirmSignUp(final ConfirmSignUpDao confirmSignUpDao) {
        return signUpRepository.confirmSignUp(confirmSignUpDao);
    }
}
