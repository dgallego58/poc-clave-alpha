package co.com.bancolombia.cognito.signin.usecase;

import co.com.bancolombia.cognito.signin.contract.SignInUseCase;
import co.com.bancolombia.cognito.signin.gateways.SignInRepository;
import co.com.bancolombia.cognito.signin.model.SignInResponseVO;
import co.com.bancolombia.cognito.signin.model.SignInVO;

public class SignInService implements SignInUseCase {

    private final SignInRepository signInRepository;

    public SignInService(final SignInRepository signInRepository) {
        this.signInRepository = signInRepository;
    }

    @Override
    public SignInResponseVO startSignInFlow(SignInVO signInVO) {
        return signInRepository.startSignInFlow(signInVO);
    }

}
