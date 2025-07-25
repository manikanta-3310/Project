package compiler;
import java.util.ArrayList;
import java.util.Scanner;

// ======= Customer Class =======
class Customer {
    private String name;
    private String accountNumber;
    private double balance;
    private double interestRate = 0.03; // 3% monthly

    public Customer(String name, String accountNumber, double balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0 && amount <= 100000) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Deposit limit exceeded or invalid amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }

    public void addMonthlyInterest() {
        double interest = balance * interestRate;
        balance += interest;
        System.out.println("Interest added: " + interest);
    }

    public void display() {
        System.out.println("Name: " + name + ", Account No: " + accountNumber + ", Balance: " + balance);
    }
}

// ======= Employee Class =======
class Employee {
    private String name;
    private String employeeId;
    private String position;

    public Employee(String name, String employeeId, String position) {
        this.name = name;
        this.employeeId = employeeId;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getPosition() {
        return position;
    }

    public void display() {
        System.out.println("Employee Name: " + name + ", ID: " + employeeId + ", Position: " + position);
    }
}

// ======= Bank Class =======
class Bank {
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Employee> employees = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
        System.out.println("Customer added successfully!");
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        System.out.println("Employee added successfully!");
    }

    public Customer findCustomer(String accountNumber) {
        for (Customer c : customers) {
            if (c.getAccountNumber().equals(accountNumber)) {
                return c;
            }
        }
        return null;
    }

    public void displayAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers found!");
        }
        for (Customer c : customers) {
            c.display();
        }
    }

    public void displayAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found!");
        }
        for (Employee e : employees) {
            e.display();
        }
    }
}

// ======= Main Class =======
public class BankManagementSystem {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Bank Management System ===");
            System.out.println("1. Customer Portal");
            System.out.println("2. Employee Portal");
            System.out.println("3. Admin Portal");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    customerPortal(bank, sc);
                    break;
                case 2:
                    employeePortal(bank, sc);
                    break;
                case 3:
                    adminPortal(bank);
                    break;
                case 4:
                    System.out.println("Exiting... Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static void customerPortal(Bank bank, Scanner sc) {
        System.out.println("\n--- Customer Portal ---");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Account Number: ");
            String acc = sc.nextLine();
            System.out.print("Enter Initial Balance: ");
            double bal = sc.nextDouble();
            bank.addCustomer(new Customer(name, acc, bal));
        } else if (choice == 2) {
            System.out.print("Enter Account Number: ");
            String acc = sc.next();
            Customer c = bank.findCustomer(acc);

            if (c != null) {
                while (true) {
                    System.out.println("\n1. Deposit");
                    System.out.println("2. Withdraw");
                    System.out.println("3. Check Balance");
                    System.out.println("4. Add Monthly Interest");
                    System.out.println("5. Logout");
                    int ch = sc.nextInt();
                    if (ch == 1) {
                        System.out.print("Enter amount: ");
                        c.deposit(sc.nextDouble());
                    } else if (ch == 2) {
                        System.out.print("Enter amount: ");
                        c.withdraw(sc.nextDouble());
                    } else if (ch == 3) {
                        c.display();
                    } else if (ch == 4) {
                        c.addMonthlyInterest();
                    } else {
                        break;
                    }
                }
            } else {
                System.out.println("Account not found!");
            }
        }
    }

    public static void employeePortal(Bank bank, Scanner sc) {
        System.out.println("\n--- Employee Portal ---");
        System.out.print("Enter Name: ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("Enter Employee ID: ");
        String id = sc.next();
        System.out.print("Enter Position: ");
        String pos = sc.next();

        Employee e = new Employee(name, id, pos);
        bank.addEmployee(e);

        System.out.println("\nAll Customer Details:");
        bank.displayAllCustomers();
    }

    public static void adminPortal(Bank bank) {
        System.out.println("\n--- Admin Portal ---");
        System.out.println("All Customers:");
        bank.displayAllCustomers();
        System.out.println("\nAll Employees:");
        bank.displayAllEmployees();
    }
}
