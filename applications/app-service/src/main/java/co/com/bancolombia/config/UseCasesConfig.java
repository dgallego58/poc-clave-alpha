package co.com.bancolombia.config;

import co.com.bancolombia.cognito.signin.contract.SignInUseCase;
import co.com.bancolombia.cognito.signin.gateways.SignInRepository;
import co.com.bancolombia.cognito.signin.usecase.SignInService;
import co.com.bancolombia.cognito.signup.contract.SignUpUseCase;
import co.com.bancolombia.cognito.signup.gateways.SignUpRepository;
import co.com.bancolombia.cognito.signup.usecase.SignUpService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

    @Bean
    public SignInUseCase signInUseCase(SignInRepository signInRepository) {
        return new SignInService(signInRepository);
    }

    @Bean
    public SignUpUseCase signUpRepository(SignUpRepository signUpRepository) {
        return new SignUpService(signUpRepository);
    }
}
