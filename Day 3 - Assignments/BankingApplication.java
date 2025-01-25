import java.util.Scanner;

abstract class Account {
    protected double balance;
    protected int accountNumber;

    public Account(int accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0;
    }

    public double getBalance() {
        return balance;
    }

    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);
    public abstract void transferFunds(Account targetAccount, double amount);
}

class SavingsAccount extends Account {

    public SavingsAccount(int accountNumber) {
        super(accountNumber);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited " + amount + " to Savings Account. New balance: " + balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew " + amount + " from Savings Account. New balance: " + balance);
        } else {
            System.out.println("Insufficient funds for withdrawal.");
        }
    }

    @Override
    public void transferFunds(Account targetAccount, double amount) {
        if (amount <= balance) {
            this.withdraw(amount);
            targetAccount.deposit(amount);
            System.out.println("Transferred " + amount + " to Account number: " + targetAccount.accountNumber);
        } else {
            System.out.println("Insufficient funds for transfer.");
        }
    }
}

class CurrentAccount extends Account {

    public CurrentAccount(int accountNumber) {
        super(accountNumber);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited " + amount + " to Current Account. New balance: " + balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew " + amount + " from Current Account. New balance: " + balance);
        } else {
            System.out.println("Insufficient funds for withdrawal.");
        }
    }

    @Override
    public void transferFunds(Account targetAccount, double amount) {
        if (amount <= balance) {
            this.withdraw(amount);
            targetAccount.deposit(amount);
            System.out.println("Transferred " + amount + " to Account number: " + targetAccount.accountNumber);
        } else {
            System.out.println("Insufficient funds for transfer.");
        }
    }
}

class Customer {
    private String name;
    private Account account;

    public Customer(String name) {
        this.name = name;
    }

    public void addAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public void displayCustomerInfo() {
        System.out.println("Customer Name: " + name);  // Displaying customer's name
        if (account != null) {
            System.out.println("Account Number: " + account.accountNumber);
            System.out.println("Account Balance: " + account.getBalance());
        }
    }
}

class BankingTransaction {

    public static void deposit(Account account, double amount) {
        account.deposit(amount);
    }

    public static void withdraw(Account account, double amount) {
        account.withdraw(amount);
    }

    public static void transfer(Account fromAccount, Account toAccount, double amount) {
        fromAccount.transferFunds(toAccount, amount);
    }
}

public class BankingApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter customer name: ");
        String customerName = scanner.nextLine();
        Customer customer1 = new Customer(customerName);

        System.out.println("Enter account type (1 for Savings, 2 for Current): ");
        int accountType = scanner.nextInt();
        scanner.nextLine(); 

        Account account = null;
        if (accountType == 1) {
            System.out.println("Enter Savings Account number: ");
            int accountNumber = scanner.nextInt();
            account = new SavingsAccount(accountNumber);
        } else if (accountType == 2) {
            System.out.println("Enter Current Account number: ");
            int accountNumber = scanner.nextInt();
            account = new CurrentAccount(accountNumber);
        }
        customer1.addAccount(account);

        // Display customer info
        customer1.displayCustomerInfo();

        while (true) {
            System.out.println("\nChoose operation: ");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("Enter amount to deposit: ");
                double amount = scanner.nextDouble();
                BankingTransaction.deposit(account, amount);
            } else if (choice == 2) {
                System.out.println("Enter amount to withdraw: ");
                double amount = scanner.nextDouble();
                BankingTransaction.withdraw(account, amount);
            } else if (choice == 3) {
                System.out.println("Enter transfer account type (1 for Savings, 2 for Current): ");
                int targetAccountType = scanner.nextInt();
                scanner.nextLine();
                Account targetAccount = null;
                if (targetAccountType == 1) {
                    System.out.println("Enter target Savings Account number: ");
                    int targetAccountNumber = scanner.nextInt();
                    targetAccount = new SavingsAccount(targetAccountNumber);
                } else if (targetAccountType == 2) {
                    System.out.println("Enter target Current Account number: ");
                    int targetAccountNumber = scanner.nextInt();
                    targetAccount = new CurrentAccount(targetAccountNumber);
                }
                System.out.println("Enter amount to transfer: ");
                double amount = scanner.nextDouble();
                BankingTransaction.transfer(account, targetAccount, amount);
            } else if (choice == 4) {
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
