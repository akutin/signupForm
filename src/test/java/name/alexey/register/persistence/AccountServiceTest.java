package name.alexey.register.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import name.alexey.register.service.JpaAccountService;
import name.alexey.register.service.NotFoundException;
import name.alexey.register.service.NotFoundException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

	private JpaAccountService accountService;

	@Mock
	private AccountRepository accountRepositoryMock;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() {
        accountService = new JpaAccountService(accountRepositoryMock);
    }

	@Test
	public void exceptionOnNotFound() {
		thrown.expect(NotFoundException.class);
		thrown.expectMessage("Account not found");

		when(accountRepositoryMock.findByUsername("user1")).thenReturn(null);
		accountService.load("user1");
	}

	@Test
	public void returnsAccount() {
		Account demoUser = new Account("demo", "demo@architech.ca", "secret");
		when(accountRepositoryMock.findByUsername("demo")).thenReturn(demoUser);

        Account load = accountService.load("demo");

		// assert
		assertThat(demoUser.getUsername()).isEqualTo(load.getUsername());
		assertThat(demoUser.getEmail()).isEqualTo(load.getEmail());
		assertThat(demoUser.getPassword()).isEqualTo(load.getPassword());
	}
}
