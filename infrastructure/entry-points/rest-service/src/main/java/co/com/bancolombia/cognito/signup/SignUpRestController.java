package co.com.bancolombia.cognito.signup;

import co.com.bancolombia.cognito.signup.contract.SignUpUseCase;
import co.com.bancolombia.cognito.signup.model.ConfirmSignUpDao;
import co.com.bancolombia.cognito.signup.model.SignUpDao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/sign-up")
public class SignUpRestController {

    private final SignUpUseCase signUpUseCase;

    public SignUpRestController(final SignUpUseCase signUpUseCase) {
        this.signUpUseCase = signUpUseCase;
    }

    @PostMapping(path = "/creds")
    public ResponseEntity<Object> startSignUpFlow(@RequestBody SignUpDao body){
        return ResponseEntity.ok(signUpUseCase.startSignUpFlow(body));
    }

    @PostMapping(path = "/confirm-att")
    public ResponseEntity<Object> confirmEmail(@RequestBody ConfirmSignUpDao body){
        return ResponseEntity.ok(signUpUseCase.confirmSignUp(body));
    }
}
