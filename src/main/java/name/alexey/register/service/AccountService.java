package name.alexey.register.service;

import name.alexey.register.persistence.Account;

/**
 * Serves user accounts
 */
public interface AccountService {
    public Account load(String username);
    public Account create(Account account);
}
