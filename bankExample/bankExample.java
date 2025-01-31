import java.util.Scanner;
import java.util.ArrayList;

class bankExample{
	static class BankAccount{
		int accountNumber;
		double balance;
		String accountHolderName;
		void deposit(double amount){
			balance = balance + amount;
		}
		boolean withdraw(double amount){
			if(balance - amount > 0){
				balance = balance - amount;
				return true;
			}
			return false;
		}
		void displayAccountInfo(){
			System.out.println("Name: " + accountHolderName);
			System.out.println("Account number: "+ accountNumber);
			System.out.println("Balance: " + balance);
		}
	}
	public static void main(String[] args){
	
	}
}
