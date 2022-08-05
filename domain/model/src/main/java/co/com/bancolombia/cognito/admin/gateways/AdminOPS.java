package co.com.bancolombia.cognito.admin.gateways;

import co.com.bancolombia.cognito.admin.model.OpsForUser;

public interface AdminOPS {

    void disableUser(OpsForUser opsForUser);

    void deleteUser(OpsForUser opsForUser);
}
