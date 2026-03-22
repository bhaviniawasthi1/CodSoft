//TASK 3- ATM INTERFACE

import java.util.Scanner;

// Class representing a user's bank account
class BankAccount {

    private double balance;

    // Constructor to initialize account with some balance
    public BankAccount(double initialBalance) {
        if (initialBalance < 0) {
            balance = 0;
        } else {
            balance = initialBalance;
        }
    }

    // Method to deposit money
    public void deposit(double amount) {
        balance += amount;
    }

    // Method to withdraw money
    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false; // insufficient balance
        }
        balance -= amount;
        return true;
    }

    // Method to check balance
    public double getBalance() {
        return balance;
    }
}


// Class representing the ATM machine
public class codsoft_task3 {

    private BankAccount account;
    private Scanner scanner;

    // Constructor to connect ATM with a bank account
    public codsoft_task3(BankAccount account) {
        this.account = account;
        scanner = new Scanner(System.in);
    }

    // Display ATM menu
    private void showMenu() {
        System.out.println("\n----- ATM MENU -----");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    // Method to handle deposit
    private void handleDeposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
            return;
        }

        account.deposit(amount);
        System.out.println("Deposit successful.");
    }

    // Method to handle withdrawal
    private void handleWithdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
            return;
        }

        boolean success = account.withdraw(amount);

        if (success) {
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Transaction failed. Insufficient balance.");
        }
    }

    // Method to display balance
    private void handleCheckBalance() {
        System.out.printf("Current Balance: %.2f\n", account.getBalance());
    }

    // Main ATM loop
    public void start() {

        boolean running = true;

        while (running) {
            showMenu();

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    handleCheckBalance();
                    break;

                case 2:
                    handleDeposit();
                    break;

                case 3:
                    handleWithdraw();
                    break;

                case 4:
                    System.out.println("Thank you for using the ATM.");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    // Entry point
    public static void main(String[] args) {

        // Creating a bank account with initial balance
        BankAccount userAccount = new BankAccount(1000.0);

        // Connecting ATM with the account
        codsoft_task3 atm = new codsoft_task3(userAccount);

        // Start ATM interface
        atm.start();
    }
}