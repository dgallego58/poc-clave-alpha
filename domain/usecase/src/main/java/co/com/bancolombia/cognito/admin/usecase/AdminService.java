package co.com.bancolombia.cognito.admin.usecase;

import co.com.bancolombia.cognito.admin.contract.AdminUseCase;
import co.com.bancolombia.cognito.admin.gateways.AdminOPS;
import co.com.bancolombia.cognito.admin.model.OpsForUser;

public class AdminService implements AdminUseCase {

    private final AdminOPS adminOPS;

    public AdminService(final AdminOPS adminOPS) {
        this.adminOPS = adminOPS;
    }

    @Override
    public void disableUser(final OpsForUser opsForUser) {
        adminOPS.disableUser(opsForUser);
    }

    @Override
    public void deleteUser(final OpsForUser opsForUser) {
        adminOPS.deleteUser(opsForUser);
    }
}
