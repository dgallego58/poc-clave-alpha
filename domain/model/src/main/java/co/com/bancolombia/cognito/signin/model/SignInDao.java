package co.com.bancolombia.cognito.signin.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

@SuperBuilder(toBuilder = true)
@Jacksonized
@Getter
public class SignInDao {

    private final String username;
    private final String password;

    @Override
    public String toString() {
        return "SignInRequest{" +
                       "username='" + username + '\'' +
                       ", password='" + password + '\'' +
                       '}';
    }

    public Map<String, String> asParameters() {
        return Map.of("USERNAME", username,
                "PASSWORD", password);
    }
}
