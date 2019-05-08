package bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class BankTests {

	@Test
	@DisplayName("Bank Init Test")
	void initBankTest() {
		Account [] accountlist = new Account[5];
		accountlist[0] = new Account("Choi", "1234567890", "0001");
		accountlist[1] = new Account("Han", "1234567891", "0002");
		accountlist[2] = new Account("Lee", "1234567892", "0003");
		accountlist[3] = new Account("Park", "1234567893", "0004");
		accountlist[4] = new Account("Aeom", "1234567894", "0005");
		Bank bank = new Bank("KoreaBank", 0, accountlist);
		assertNotNull(bank);
	}

	@Test
	@DisplayName("Functional Test : Check")
	void CheckTest(){
		Account [] accountlist = new Account[5];
		accountlist[0] = new Account("Choi", "1234567890", "0001");
		accountlist[1] = new Account("Han", "1234567891", "0002");
		accountlist[2] = new Account("Lee", "1234567892", "0003");
		accountlist[3] = new Account("Park", "1234567893", "0004");
		accountlist[4] = new Account("Aeom", "1234567894", "0005");
		Bank bank = new Bank("KoreaBank", 0, accountlist);
		assertEquals(0, bank.Check("Choi"));
		assertEquals(0, bank.Check("Han"));
		assertEquals(0, bank.Check("Lee"));
		assertEquals(0, bank.Check("Park"));
		assertEquals(0, bank.Check("Aeom"));
	}

	@Test
	@DisplayName("Functional Test : Deposit")
	void DepositTest(){
		Account [] accountlist = new Account[5];
		accountlist[0] = new Account("Choi", "1234567890", "0001");
		accountlist[1] = new Account("Han", "1234567891", "0002");
		accountlist[2] = new Account("Lee", "1234567892", "0003");
		accountlist[3] = new Account("Park", "1234567893", "0004");
		accountlist[4] = new Account("Aeom", "1234567894", "0005");

		Bank bank = new Bank("KoreaBank", 0, accountlist);
		assertEquals(0, bank.Check("0001"));
		assertEquals(0, bank.Check("0002"));
		assertEquals(0, bank.Check("0003"));
		assertEquals(0, bank.Check("0004"));
		assertEquals(0, bank.Check("0005"));
		bank.Deposit("0001", 1000);
		bank.Deposit("0002", 2000);
		bank.Deposit("0003", 3000);
		bank.Deposit("0004", 4000);
		bank.Deposit("0005", 5000);
		assertEquals(1000, bank.Check("0001"));
		assertEquals(2000, bank.Check("0002"));
		assertEquals(3000, bank.Check("0003"));
		assertEquals(4000, bank.Check("0004"));
		assertEquals(5000, bank.Check("0005"));
	}
}
