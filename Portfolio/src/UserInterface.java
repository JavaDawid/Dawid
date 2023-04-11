import java.util.Scanner;

public class UserInterface {
    private static final Scanner SCANNER = new Scanner(System.in);

    public int printOption() {
        System.out.println("Wybierz odpowiednie działanie:");
        System.out.println(UserOption.ADD_BOOK);
        System.out.println(UserOption.EDIT_BOOK);
        System.out.println(UserOption.DELETE_BOOK);
        System.out.println(UserOption.PRINT_BOOKS);
        System.out.println(UserOption.PRINT_RENT_BOOKS);
        System.out.println(UserOption.READ_FROM_FILE);
        System.out.println(UserOption.SAVE_LIBRARY_AT_FILE);
        System.out.println(UserOption.RENT_BOOK);
        System.out.println(UserOption.RETURN_BOOK);
        System.out.println(UserOption.EXIT);
        int task = SCANNER.nextInt();
        SCANNER.nextLine();
        return task;
    }


    public static Book informationAboutBook() {
        System.out.println("Podaj tytuł:");
        String title = SCANNER.nextLine();
        System.out.println("Podaj autora:");
        String author = SCANNER.nextLine();
        System.out.println("Podaj kategorię:");
        String category = SCANNER.nextLine();
        System.out.println("Podaj rok publikacji:");
        int publication = SCANNER.nextInt();
        System.out.println("Podaj numer indentyfikacyjny:");
        int id = SCANNER.nextInt();
        SCANNER.nextLine();
        return new Book(title, author, category, publication, id);
    }

    public static int optionToFindBook() {
        System.out.println("Jak chcesz wyszukać książkę?");
        System.out.println(UserOption.TITLE);
        System.out.println(UserOption.AUTHOR);
        System.out.println(UserOption.CATEGORY);
        int findOption = SCANNER.nextInt();
        SCANNER.nextLine();
        return findOption;
    }

    public static int howPrintBooks() {
        System.out.println(UserOption.DETAILS_BOOK);
        System.out.println(UserOption.ALL_BOOKS);
        int option = SCANNER.nextInt();
        SCANNER.nextLine();
        return option;
    }

    public static int saveHistoryLibraryOrAvailable() {
        System.out.println(UserOption.AVAILABLE_BOOKS);
        System.out.println(UserOption.HISTORY_BOOKS);
        int option = SCANNER.nextInt();
        SCANNER.nextLine();
        return option;
    }

    public static int readHistoryOfRentOrAvailable() {
        System.out.println(UserOption.AVAILABLE_BOOKS);
        System.out.println(UserOption.HISTORY_BOOKS);
        int option = SCANNER.nextInt();
        SCANNER.nextLine();
        return option;
    }

    public static String takeTitleFromUser() {
        System.out.println("Podaj tytuł książki:");
        return SCANNER.nextLine();
    }

    public static String takeAuthorFromUser() {
        System.out.println("Podaj autora książki");
        return SCANNER.nextLine();
    }

    public static String takeCategoryFromUser() {
        System.out.println("Podaj kategorię książki");
        return SCANNER.nextLine();
    }

    public static String nameOfFile() {
        System.out.println("\\C:\\Users\\user\\Desktop\\test.txt\\");
        System.out.println("Podaj ścieżkę dostępu do pliku w formacie jak powyżej " +
                "(pamiętaj, że nazwy katalogów i plików mogą się różnić)");
        return SCANNER.nextLine();
    }

    public static String pathToSave() {
        System.out.println("\\C:\\Users\\user\\Desktop\\test.txt\\");
        System.out.println("Podaj ścieżkę zapisu pliku wraz z jego nazwą w formacie jak powyżej " +
                "(pamiętaj, że nazwy katalogów i plików mogą się różnić)");
        return SCANNER.nextLine();
    }

    public static Person clientData() {
        System.out.println(">>DANE KLIENTA<<");
        System.out.println("Imię");
        String firstName = SCANNER.nextLine();
        System.out.println("Nazwisko");
        String lastName = SCANNER.nextLine();
        System.out.println("Numer karty członkowskiej");
        int cartNumber = SCANNER.nextInt();
        SCANNER.nextLine();
        return new Person(firstName, lastName, cartNumber);
    }

    public static RentBook rentBook(Book bookForClient, Person clientData) {
        System.out.println(">>Data wypożyczenia<<");
        String rentDate = takeDate();
        return new RentBook(rentDate,bookForClient, clientData);
    }

    public static String returnBook() {
        System.out.println(">>Data zwrotu<<");
        return takeDate();
    }

    private static String takeDate() {//z racji, że dat i czasu w Java jeszcze nie zdązyłem przerobić to jest to wykonane tak
        System.out.println("Dzień");
        int day = SCANNER.nextInt();
        System.out.println("Miesiąc");
        int month = SCANNER.nextInt();
        System.out.println("Rok");
        int year = SCANNER.nextInt();
        SCANNER.nextLine();
        return String.format("%s-%s-%s", day, month, year);
    }

    public void clearScannerBuffer() {
        SCANNER.nextLine();
    }

    public static void goodBye() {
        System.out.println("Do zobaczenia!");
        SCANNER.close();
    }
}
