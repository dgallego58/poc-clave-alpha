package co.com.bancolombia.config;


import co.com.bancolombia.cognito.admin.contract.AdminUseCase;
import co.com.bancolombia.cognito.admin.gateways.AdminOPS;
import co.com.bancolombia.cognito.admin.usecase.AdminService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminOpsUseCaseConfig {

    @Bean
    public AdminUseCase adminUseCase(AdminOPS adminOPS){
        return new AdminService(adminOPS);
    }

}
