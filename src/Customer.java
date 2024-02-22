import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

public class Customer extends Person implements SavingAccount {

    private String username, password;
    private double balance;
    private ArrayList<String> transactions; // last 5 transactions


    public Customer(String username, String password, double balance, ArrayList<String> transactions, Date date) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.transactions = transactions;
        addTransaction(String.format("Initial deposit - " + NumberFormat.getCurrencyInstance().format(balance) +
                " as on " + "%1$tD" + " at " + "%1$tT.", date));
    }

    public Customer(String firstName, String lastName, String address, String phone,
                    String username, String password, Date date) {
        super(firstName, lastName, address, phone);
        this.username = username;
        this.password = password;
    }

    public void withdraw(double amount, Date date) {

        if (amount > (balance - 10)) {
            System.out.println("Insufficient balance.");
            return;
        }
        balance -= amount;
        addTransaction(String.format(NumberFormat.getCurrencyInstance()
                .format(amount) + " debited from your account. Balance - " +
                NumberFormat.getCurrencyInstance().format(balance) + " as on " + "%1$tD" + " at " + "%1$tT.", date));
    }

    private void addTransaction(String message) {
        transactions.add(0, message);
        if (transactions.size() > 5) {
            transactions.remove(5);
            transactions.trimToSize();
        }
    }

    public ArrayList<String> getTransactions() {
        return transactions;
    }

    public void deposit(double amount, Date date) {
        balance += amount;
        addTransaction(String.format(NumberFormat.getCurrencyInstance().format(amount) +
                " credited to your account. Balance: " + NumberFormat.getCurrencyInstance().format(balance) +
                " as on " + "%1$tD" + " at " + "%1$tT.", date));
    }

    @Override
    public String toString() {
        return "Customer { " + " with username: " + username + "\n" +
                "password" + password + "\n" +
                "balance" + balance + "\n" +
                "transactions" + transactions + " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (Double.compare(customer.getBalance(), getBalance()) != 0) return false;
        if (getUsername() != null ? !getUsername().equals(customer.getUsername()) : customer.getUsername() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(customer.getPassword()) : customer.getPassword() != null)
            return false;
        return getTransactions() != null ? getTransactions().equals(customer.getTransactions()) :
                customer.getTransactions() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getUsername() != null ? getUsername().hashCode() : 0;
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        temp = Double.doubleToLongBits(getBalance());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getTransactions() != null ? getTransactions().hashCode() : 0);
        return result;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }
}
