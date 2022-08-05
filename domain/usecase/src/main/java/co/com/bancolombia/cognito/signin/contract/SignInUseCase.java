package co.com.bancolombia.cognito.signin.contract;

import co.com.bancolombia.cognito.signin.model.SignInResponseVO;
import co.com.bancolombia.cognito.signin.model.SignInVO;

public interface SignInUseCase {

    SignInResponseVO startSignInFlow(SignInVO signInVO);
}
