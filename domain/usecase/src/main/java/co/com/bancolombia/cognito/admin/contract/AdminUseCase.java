package co.com.bancolombia.cognito.admin.contract;

import co.com.bancolombia.cognito.admin.model.OpsForUser;

public interface AdminUseCase {

    void disableUser(OpsForUser opsForUser);

    void deleteUser(OpsForUser opsForUser);
}
