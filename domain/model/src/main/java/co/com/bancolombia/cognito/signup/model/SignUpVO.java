package co.com.bancolombia.cognito.signup.model;

import co.com.bancolombia.cognito.signin.model.SignInVO;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@SuperBuilder(toBuilder = true)
@Jacksonized
@Getter
public class SignUpVO extends SignInVO {

    private final String email;

    @Override
    public String toString() {
        return "SignUpRequest{" +
                       "email='" + email + '\'' +
                       "} " + super.toString();
    }
}
