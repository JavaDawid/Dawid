import java.util.Objects;

public class Book implements Comparable<Book> {
    private String title;
    private String author;
    private String category;
    private int yearPublication;
    private int id;

    public Book(String title, String author, String category, int yearPublication, int id) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.yearPublication = yearPublication;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;

    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getYearPublication() {
        return yearPublication;
    }

    public void setYearPublication(int yearPublication) {
        this.yearPublication = yearPublication;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return title + ", " + author + ", " + category + ", " + yearPublication + ", " + id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return yearPublication == book.yearPublication &&
                id == book.id &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(category, book.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, category, yearPublication, id);
    }

    @Override
    public int compareTo(Book t) {
        return this.title.compareToIgnoreCase(t.title);
    }
}
