package co.com.bancolombia.cognito.signin.gateways;

import co.com.bancolombia.cognito.signin.model.SignInResponseVO;
import co.com.bancolombia.cognito.signin.model.SignInVO;

public interface SignInRepository {

    SignInResponseVO startSignInFlow(SignInVO signInVO);
}
