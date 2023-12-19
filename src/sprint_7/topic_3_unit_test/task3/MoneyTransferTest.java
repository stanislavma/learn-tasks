package sprint_7.topic_3_unit_test.task3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MoneyTransferService {

    public void transfer(int amount, Account accountOne, Account accountTwo) {
        // переводить деньги можно только на другой счёт
        if (accountOne.id.equals(accountTwo.id))
            throw new IllegalArgumentException("Нельзя перевести деньги на тот же аккаунт. Id: " + accountOne.id);

        // сумма перевода должна быть больше 0
        if (amount <= 0)
            throw new IllegalArgumentException("Сумма перевода должна быть больше 0. Текущая сумма: " + amount);

        // баланс не может стать отрицательным
        if (amount > accountOne.balance)
            throw new IllegalArgumentException("Недостаточно средств на счёте с id " + accountOne.id + ". Перевод суммы " + amount + " невозможен.");

        // перевод не должен приводить к переполнению баланса
        if (accountTwo.balance + amount < 0)
            throw new IllegalArgumentException("Перевод невозможен. Если транзакция выполнится, счёт с " + accountTwo.id + " будет переполнен.");

        accountOne.balance -= amount;
        accountTwo.balance += amount;
    }
}
class Account {
		public String id;
		public int balance;
	
	  public Account(String id, int balance) {
			  this.id = id;
			  this.balance = balance;
		}
}

public class MoneyTransferTest {
    private final MoneyTransferService moneyTransferService = new MoneyTransferService();

    @Test
    public void shouldMakeTransfer() {
        Account accountOne = new Account("1", 10);
        Account accountTwo = new Account("2", 10);

        moneyTransferService.transfer(7, accountOne, accountTwo);
        assertEquals(3, accountOne.balance);
        assertEquals(17, accountTwo.balance);
    }

    @Test
    public void shouldNotTransferMoneyOnSameAccount() {
        Account accountOne = new Account("1", 10);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() {
                moneyTransferService.transfer(5, accountOne, accountOne);
            }
        });

        assertEquals("Нельзя перевести деньги на тот же аккаунт. Id: 1", ex.getMessage());
    }

    @Test
    public void shouldNotTransferZero() {

        Account accountOne = new Account("1", 10);
        Account accountTwo = new Account("2", 10);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() {
                moneyTransferService.transfer(0, accountOne, accountTwo);
            }
        });

        assertEquals("Сумма перевода должна быть больше 0. Текущая сумма: 0", ex.getMessage());
    }

    @Test
    public void shouldNotMakeBalanceLessThanZero() {

        Account accountOne = new Account("1", 10);
        Account accountTwo = new Account("2", 10);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() {
                moneyTransferService.transfer(15, accountOne, accountTwo);
            }
        });

        assertEquals("Недостаточно средств на счёте с id 1. Перевод суммы 15 невозможен.", ex.getMessage());
    }

    @Test
    public void shouldNotMakeOverflowOnTransfer() {

        Account accountOne = new Account("1", 10);
        Account accountTwo = new Account("2", Integer.MAX_VALUE);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() {
                moneyTransferService.transfer(1, accountOne, accountTwo);
            }
        });

        assertEquals("Перевод невозможен. Если транзакция выполнится, счёт с 2 будет переполнен.", ex.getMessage());
    }
}