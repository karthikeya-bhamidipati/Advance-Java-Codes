import java.util.ArrayList;

class bankExample {
	static class BankAccount {
		int accountNumber;
		double balance;
		String accountHolderName;

		void deposit(double amount) {
			balance = balance + amount;
		}

		boolean withdraw(double amount) {
			if (balance - amount > 0) {
				balance = balance - amount;
				return true;
			}
			return false;
		}

		void displayAccountInfo() {
			System.out.println("Name: " + accountHolderName);
			System.out.println("Account number: " + accountNumber);
			System.out.println("Balance: " + balance);
		}
	}

	static class SavingsAccount extends BankAccount {
		double intrestRate;

		void addIntrest() {
			double intrestAmount = 0;
			intrestAmount = (balance * intrestRate * 30) / (100 * 365);
			balance = balance + intrestAmount;
		}
	}

	static class CurrentAccount extends BankAccount {
		double overdraftLimit;

		boolean withdraw(double amount) {
			if (balance - amount < 0)
				if (overdraftLimit - amount > 0) {
					overdraftLimit = overdraftLimit - amount;
					return true;
				} else {
					balance = balance - amount;
					return true;
				}
			return false;
		}
	}

	static class Bank {
		ArrayList<BankAccount> accounts = new ArrayList<>();
	}

	public static void main(String[] args) {

	}
}
