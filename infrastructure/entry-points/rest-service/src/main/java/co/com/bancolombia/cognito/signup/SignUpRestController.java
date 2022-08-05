package co.com.bancolombia.cognito.signup;

import co.com.bancolombia.cognito.signup.contract.SignUpUseCase;
import co.com.bancolombia.cognito.signup.model.ConfirmSignUpVO;
import co.com.bancolombia.cognito.signup.model.ConfirmationState;
import co.com.bancolombia.cognito.signup.model.SignUpResponseVO;
import co.com.bancolombia.cognito.signup.model.SignUpVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/sign-up")
public class SignUpRestController {

    private final SignUpUseCase signUpUseCase;

    public SignUpRestController(final SignUpUseCase signUpUseCase) {
        this.signUpUseCase = signUpUseCase;
    }

    /**
     * TODO Chain Of Resp to filter access
     * @param body
     * @return
     */
    @PostMapping(path = "/creds")
    public ResponseEntity<SignUpResponseVO> startSignUpFlow(@RequestBody SignUpVO body) {
        return ResponseEntity.ok(signUpUseCase.startSignUpFlow(body));
    }

    @PostMapping(path = "/confirm-att")
    public ResponseEntity<ConfirmationState> confirmEmail(@RequestBody ConfirmSignUpVO body) {
        return ResponseEntity.ok(signUpUseCase.confirmSignUp(body));
    }

    /**
     * Reactive Chain
     * @param body
     * @return
     */
    @PostMapping(path = "/creds-reactive")
    public Mono<ResponseEntity<SignUpResponseVO>> asyncSignUpFlow(@RequestBody SignUpVO body) {
        return signUpUseCase.reactiveSignUpFlow(body).map(ResponseEntity::ok);
    }

}
