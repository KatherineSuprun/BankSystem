import java.util.Objects;

public class Person {

    private String firstName, lastName, address, phone;

    public Person() {
    }

    public Person(String firstName, String lastName, String address, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person { " + " with name: " + firstName + lastName + "\n" +
                "address" + address + "\n" +
                "phone" + phone + " }";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Person person = (Person) obj;

        if (getFirstName() != null ? !getFirstName().equals(person.getFirstName()) : person.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(person.getLastName()) : person.getLastName() != null)
            return false;
        if (getAddress() != null ? !getAddress().equals(person.getAddress()) : person.getAddress() != null)
            return false;
        return getPhone() != null ? getPhone().equals(person.getPhone()) : person.getPhone() == null;

    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, address, phone);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
