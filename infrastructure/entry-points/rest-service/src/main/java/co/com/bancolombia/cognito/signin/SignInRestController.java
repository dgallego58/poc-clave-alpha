package co.com.bancolombia.cognito.signin;

import co.com.bancolombia.cognito.signin.contract.SignInUseCase;
import co.com.bancolombia.cognito.signin.model.SignInDao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/sign-in")
public class SignInRestController {

    private final SignInUseCase signInUseCase;

    public SignInRestController(final SignInUseCase signInUseCase) {
        this.signInUseCase = signInUseCase;
    }

    @PostMapping(path = "/access")
    public ResponseEntity<?> startLoginFlow(@RequestBody SignInDao body) {
        return ResponseEntity.ok(signInUseCase.startSignInFlow(body));
    }
}
