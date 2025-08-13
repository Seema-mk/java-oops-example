// Bank Management System

interface AccountOperations { // Abstraction
    void deposit(double amount);
    void withdraw(double amount);
    void displayBalance();
}

abstract class BankAccount implements AccountOperations { // Inheritance + Encapsulation
    private String accountHolder;
    private double balance;

    public BankAccount(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    protected double getBalance() { // Protected so subclasses can access
        return balance;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(amount + " deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    @Override
    public void displayBalance() {
        System.out.println("Account Holder: " + accountHolder +
                           " | Balance: " + balance);
    }
}

class SavingsAccount extends BankAccount { // Polymorphism (Overriding)
    public SavingsAccount(String accountHolder, double balance) {
        super(accountHolder, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= getBalance()) {
            setBalance(getBalance() - amount);
            System.out.println(amount + " withdrawn successfully from Savings Account.");
        } else {
            System.out.println("Insufficient balance in Savings Account.");
        }
    }
}

class CurrentAccount extends BankAccount { // Different logic for withdraw
    private double overdraftLimit = 5000;

    public CurrentAccount(String accountHolder, double balance) {
        super(accountHolder, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= getBalance() + overdraftLimit) {
            setBalance(getBalance() - amount);
            System.out.println(amount + " withdrawn successfully from Current Account.");
        } else {
            System.out.println("Withdrawal exceeds overdraft limit.");
        }
    }
}

public class Main1 {
    public static void main(String[] args) {
        BankAccount acc1 = new SavingsAccount("Alice", 1000);
        BankAccount acc2 = new CurrentAccount("Bob", 2000);

        acc1.deposit(500);
        acc1.withdraw(300);
        acc1.displayBalance();

        System.out.println("----");

        acc2.deposit(1000);
        acc2.withdraw(2500);
        acc2.displayBalance();
    }
}
