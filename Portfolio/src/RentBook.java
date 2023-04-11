import java.util.Objects;

public class RentBook implements Comparable<RentBook> {
    private String rentDate;
    private String dateOfReturn;
    private Book book;
    private Person person;

    public RentBook(String rentDate, String dateOfReturn, Book book, Person person) {
        this.rentDate = rentDate;
        this.dateOfReturn = dateOfReturn;
        this.book = book;
        this.person = person;
    }

    public RentBook(String rentDate, Book book, Person person) {
        this.rentDate = rentDate;
        this.book = book;
        this.person = person;
    }

    public RentBook(Book book, Person person) {
        this.book = book;
        this.person = person;
    }

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public String getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(String dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return " Wypożyczający\n" +
                person + "\n" +
                " Książka\n" +
                book + "\n" +
                " Data wypożyczenia\n" +
                rentDate + "\n" +
                " Data zwrócenia\n" +
                dateOfReturn+"\n";
    }

    @Override
    public int compareTo(RentBook d) {
        if (this.dateOfReturn == null && d.dateOfReturn == null) {
            return 0;
        }
        if (this.dateOfReturn == null) {
            return 1;
        }
        if (d.dateOfReturn == null) {
            return -1;
        }
        return this.dateOfReturn.compareTo(d.dateOfReturn);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentBook rentBook = (RentBook) o;
        return book.equals(rentBook.getBook()) &&
                person.equals(rentBook.getPerson());
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, person);
    }
}
