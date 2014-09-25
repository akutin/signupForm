package name.alexey.register.service;

import name.alexey.register.persistence.Account;
import name.alexey.register.persistence.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JpaAccountService implements AccountService {
	
	private AccountRepository accountRepository;

    @Autowired
    public JpaAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

	@Override
	public Account load(String username) {
		final Account account = accountRepository.findByUsername(username);
		if(account == null) {
			throw new NotFoundException("Account not found");
		}
		return account;
	}

    @Override
	public Account create(Account account) {
		return accountRepository.save( account);
	}

}
