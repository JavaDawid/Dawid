import java.util.Objects;

public class Person {
    private String firstName;
    private String lastName;
    private int numberClubCard;

    public Person(String firstName, String lastName, int numberClubCard) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberClubCard = numberClubCard;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getNumberClubCard() {
        return numberClubCard;
    }

    public void setNumberClubCard(int numberClubCard) {
        this.numberClubCard = numberClubCard;
    }

    @Override
    public String toString() {
        return firstName + ", " + lastName + ", " + numberClubCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName)
                && Objects.equals(lastName, person.lastName)
                && Objects.equals(numberClubCard, person.numberClubCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, numberClubCard);
    }
}
