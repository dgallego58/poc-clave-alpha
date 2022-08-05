package co.com.bancolombia.cognito.admin;


import co.com.bancolombia.cognito.admin.contract.AdminUseCase;
import co.com.bancolombia.cognito.admin.model.OpsForUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/admin")
public class AdminRestController {

    private final AdminUseCase adminUseCase;

    public AdminRestController(final AdminUseCase adminUseCase) {
        this.adminUseCase = adminUseCase;
    }

    @DeleteMapping(path = "/_disable/{username}")
    public ResponseEntity<Void> disableUser(@PathVariable String username) {
        adminUseCase.disableUser(OpsForUser.builder().username(username).build());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/_remove/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        adminUseCase.deleteUser(OpsForUser.builder().username(username).build());
        return ResponseEntity.noContent().build();
    }
}
