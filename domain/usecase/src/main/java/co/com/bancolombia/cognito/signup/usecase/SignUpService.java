package co.com.bancolombia.cognito.signup.usecase;

import co.com.bancolombia.cognito.signup.contract.SignUpUseCase;
import co.com.bancolombia.cognito.signup.gateways.SignUpRepository;
import co.com.bancolombia.cognito.signup.model.ConfirmSignUpVO;
import co.com.bancolombia.cognito.signup.model.ConfirmationState;
import co.com.bancolombia.cognito.signup.model.SignUpResponseVO;
import co.com.bancolombia.cognito.signup.model.SignUpVO;
import reactor.core.publisher.Mono;

public class SignUpService implements SignUpUseCase {

    private final SignUpRepository signUpRepository;

    public SignUpService(final SignUpRepository signUpRepository) {
        this.signUpRepository = signUpRepository;
    }

    @Override
    public SignUpResponseVO startSignUpFlow(SignUpVO signUp) {
        return signUpRepository.signUp(signUp);
    }

    @Override
    public ConfirmationState confirmSignUp(final ConfirmSignUpVO confirmSignUpVO) {
        return signUpRepository.confirmSignUp(confirmSignUpVO);
    }

    @Override
    public Mono<SignUpResponseVO> reactiveSignUpFlow(final SignUpVO signUpVO) {
        return signUpRepository.asyncSignUp(signUpVO);
    }
}
