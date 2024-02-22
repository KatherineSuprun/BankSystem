import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainBank {

    private static double amount;
    Map<String, Customer> customerMap;

    MainBank() {
        customerMap = new HashMap<>();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Customer customer;
        MainBank bank = new MainBank();
        int choice;

        while (true) {
            System.out.println("\n-------------------");
            System.out.println("BANK    OF     JAVA");
            System.out.println("-------------------\n");
            System.out.println("1. Registrar cont.");
            System.out.println("2. Login.");
            System.out.println("3. Exit.");
            System.out.print("\nEnter your choice : ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: // if login incorrect
                    System.out.println("Enter first name:");
                    String firstName = sc.nextLine();
                    System.out.println("Enter last name:");
                    String lastName = sc.nextLine();
                    System.out.println("Enter address name:");
                    String address = sc.nextLine();
                    System.out.println("Enter contact number:");
                    String phone = sc.nextLine();
                    System.out.println("Please, select a username:");
                    String username = sc.next();

                    while (bank.customerMap.containsKey(username)) {
                        System.out.println("Username already exists. Choose other: ");
                        username = sc.next();
                    }
                    System.out.println("Set a password:");
                    String password = sc.next();

                    customer = new Customer(firstName, lastName, address, phone, username, password, new Date());
                    bank.customerMap.put(username, customer);
                    break;

                case 2:  // if login is correct
                    System.out.println("Enter username:");
                    username = sc.nextLine();
                    System.out.println("Enter password:");
                    password = sc.next();

                    if (bank.customerMap.containsKey(username)) {
                        customer = bank.customerMap.get(username);
                        if (customer.getPassword().equals(password)) {
                            while (true) {
                                System.out.println("\n-------------------");
                                System.out.println("W  E  L  C  O  M  E");
                                System.out.println("-------------------\n");
                                System.out.println("1. Deposit.");
                                System.out.println("2. Transfer.");
                                System.out.println("3. Last 5 transactions.");
                                System.out.println("4. User information.");
                                System.out.println("5. Exit.");
                                System.out.print("\nEnter your choice : ");
                                choice = sc.nextInt();
                                sc.nextLine();

                                switch (choice) {
                                    case 1:
                                        System.out.print("Enter amount : ");
                                        while (!sc.hasNextDouble()) {
                                            System.out.println("Invalid amount. Enter again :");
                                            sc.nextLine();
                                        }
                                        amount = sc.nextDouble();
                                        sc.nextLine();
                                        customer.deposit(amount, new Date());
                                        break;

                                    case 2:
                                        System.out.print("Enter beneficiary username : ");
                                        username = sc.next();
                                        sc.nextLine();
                                        System.out.println("Enter amount : ");
                                        while (!sc.hasNextDouble()) {
                                            System.out.println("Invalid amount. Enter again :");
                                            sc.nextLine();
                                        }
                                        amount = sc.nextDouble();
                                        sc.nextLine();
                                        if (amount > 300) {
                                            System.out.println("Transfer limit exceeded. Contact bank manager.");
                                            break;
                                        }
                                        if (bank.customerMap.containsKey(username)) {
                                            Customer payee = bank.customerMap.get(username);
                                            payee.deposit(amount, new Date());
                                            customer.withdraw(amount, new Date());
                                        } else {
                                            System.out.println("Username doesn't exist.");
                                        }
                                        break;

                                    case 3:
                                        for (String transactions : customer.getTransactions()) {
                                            System.out.println(transactions);
                                        }
                                        break;
                                    case 4:
                                        System.out.println("Account owner by name: " + customer.getFirstName());
                                        System.out.println("Account owner with name: " + customer.getLastName());
                                        System.out.println("Account owner with username: " + customer.getUsername());
                                        System.out.println("Account owner with address: " + customer.getAddress());
                                        System.out.println("Account owner with phone number: " + customer.getPhone());
                                        break;
                                    case 5:
                                        System.exit(0);
                                    default:
                                        System.out.println("It seems you're not playing by the rules? :)" +
                                                "Try next time");
                                        System.exit(0);
                                }
                            }
                        } else {
                            System.out.println("Wrong username/password");
                        }
                        break;
                    }
            }
        }
    }
}