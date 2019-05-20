package bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class AccountTests {
	@Test
	@DisplayName("Account Init Test")
	void initAccountTest() {
		Account account = new Account("Hong gil-dong", "1234567890", "0001");
		assertNotNull(account);
		assertEquals(0, account.getBalance());
	}

	
	@Test
	@DisplayName("Account Set Balance Test")
	void setBalanceTest(){
		Account account = new Account("Hong gil-dong", "1234567890", "0001");
		account.setBalance(100);
		assertEquals(100, account.getBalance());
	}

}
