import java.util.*;

public class Library {
    private final ArrayList<Book> library = new ArrayList<>();
    private final ArrayList<RentBook> libraryRentBooks = new ArrayList<>();
    private final static int MAKE_CORRECTIONS_IN_BOOK = 1;
    private final static int DELETE_BOOK = -1;
    private final static int PRINT_BOOKS = 0;
    private final static int PRINT_RENT_BOOKS_AND_CLIENT = 2;
    private int task;
    private boolean go;

    public void loop() {
        UserInterface uI = new UserInterface();
        go = true;
        while (go) {
            try {
                task = 1;
                performTheAction(uI.printOption());
            } catch (InputMismatchException e) {
                System.err.println("Wprowadzono nieobsługiwany format znaków!");
                uI.clearScannerBuffer();
            } catch (NumberFormatException e) {
                System.err.println("Błędny format zapisu danych w pliku albo zły plik");
            }
        }
    }

    private void performTheAction(int option) {
        UserOption userOption = UserOption.fromKod(option);
        FileStorage fileStorage = new FileStorage();
        if (userOption != null) {
            switch (userOption) {
                case ADD_BOOK -> {
                    Book book = UserInterface.informationAboutBook();
                    library.add(book);
                    System.out.println("Książkę dodano do biblioteki!");
                }
                case EDIT_BOOK -> {
                    int findOption = UserInterface.optionToFindBook();
                    findBookByUserRecommendation(findOption);

                }
                case DELETE_BOOK -> {
                    task = -1;
                    int findOption = UserInterface.optionToFindBook();
                    findBookByUserRecommendation(findOption);
                }
                case PRINT_BOOKS -> {
                    printBooks();
                }
                case PRINT_RENT_BOOKS -> {
                    printRentBooks();
                }
                case READ_FROM_FILE -> {
                    readFromFile(fileStorage);
                }
                case SAVE_LIBRARY_AT_FILE -> {
                    saveLibraryAtFile(fileStorage);
                }
                case RENT_BOOK -> {
                    Book bookForClient = UserInterface.informationAboutBook();
                    Person clientData = UserInterface.clientData();
                    RentBook rentBook = UserInterface.rentBook(bookForClient, clientData);
                    addAndDeleteBookAtLibrary(rentBook, rentBook.getBook());
                }
                case RETURN_BOOK -> {
                    Book bookFromClient = UserInterface.informationAboutBook();
                    Person clientData = UserInterface.clientData();
                    String dateReturn = UserInterface.returnBook();
                    RentBook rentBook = new RentBook(bookFromClient, clientData);
                    findBookInRentList(rentBook, dateReturn);
                }
                case EXIT -> {
                    UserInterface.goodBye();
                    go = false;
                }
            }
        }
    }

//    public void findBookInRentList(RentBook rentBook, String dateReturn) {
//        for (RentBook rentBookInList : libraryRentBooks) {
//            System.out.println("Porównanie:");
//            System.out.println("Książka z listy: " + rentBookInList.getBook());
//            System.out.println("Książka klienta: " + rentBook.getBook());
//            System.out.println("Czy książki są takie same? " + rentBookInList.getBook().equals(rentBook.getBook()));
//
//            System.out.println("Klient z listy: " + rentBookInList.getPerson());
//            System.out.println("Klient klienta: " + rentBook.getPerson());
//            System.out.println("Czy klienci są tacy sami? " + rentBookInList.getPerson().equals(rentBook.getPerson()));
//
//            if (rentBookInList.getBook().equals(rentBook.getBook()) && rentBookInList.getPerson().equals(rentBook.getPerson()) && rentBookInList.getDateOfReturn() == null) {
//                rentBookInList.setDateOfReturn(dateReturn);
//                System.out.println("Książka została zwrócona!");
//                return;
//            }
//        }
//        System.out.println("Nie można zwrócić książki");
//    }

    private void findBookByUserRecommendation(int findOption) {
        UserOption userOption = UserOption.fromKod(findOption);
        if (userOption != null) {
            switch (userOption) {
                case TITLE -> {
                    String title = UserInterface.takeTitleFromUser();
                    Book book = searchBook(UserOption.TITLE, title);
                    if (task == MAKE_CORRECTIONS_IN_BOOK)
                        makeCorrections(book);
                    if (task == DELETE_BOOK)
                        library.remove(book);
                    if (task == PRINT_BOOKS) {
                        String dataToPrint = UserInterface.takeTitleFromUser();
                        printUsersBookByDetail(dataToPrint);
                    }
                    if (task == PRINT_RENT_BOOKS_AND_CLIENT) {
                        String dataToPrint = UserInterface.takeTitleFromUser();
                        printDataAboutRentBookByDetail(dataToPrint);
                    }
                }
                case AUTHOR -> {
                    String author = UserInterface.takeAuthorFromUser();
                    Book book = searchBook(UserOption.AUTHOR, author);
                    if (task == MAKE_CORRECTIONS_IN_BOOK)
                        makeCorrections(book);
                    if (task == DELETE_BOOK)
                        library.remove(book);
                    if (task == PRINT_BOOKS) {
                        String dataToPrint = UserInterface.takeAuthorFromUser();
                        printUsersBookByDetail(dataToPrint);
                    }
                    if (task == PRINT_RENT_BOOKS_AND_CLIENT) {
                        String dataToPrint = UserInterface.takeAuthorFromUser();
                        printDataAboutRentBookByDetail(dataToPrint);
                    }
                }
                case CATEGORY -> {
                    String category = UserInterface.takeCategoryFromUser();
                    Book book = searchBook(UserOption.CATEGORY, category);
                    if (task == MAKE_CORRECTIONS_IN_BOOK)
                        makeCorrections(book);
                    if (task == DELETE_BOOK)
                        library.remove(book);
                    if (task == PRINT_BOOKS) {
                        String dataToPrint = UserInterface.takeCategoryFromUser();
                        printUsersBookByDetail(dataToPrint);
                    }
                    if (task == PRINT_RENT_BOOKS_AND_CLIENT) {
                        String dataToPrint = UserInterface.takeCategoryFromUser();
                        printDataAboutRentBookByDetail(dataToPrint);
                    }
                }
                default -> System.out.println("Nie ma takiej opcji");
            }
        }
    }


    private Book searchBook(UserOption searchType, String searchValue) {
        String searchValueLowerCase = searchValue.toLowerCase();// po zmianie tekstu na małe litery nie ma znaczenia
        // jakiej wielkości liter użyje użytkownik.
        for (Book book : library) {
            switch (searchType) {
                case TITLE -> {
                    if (book.getTitle().toLowerCase().equals(searchValueLowerCase)) {
                        System.out.println(book);
                        return book;
                    }
                }
                case AUTHOR -> {
                    if (book.getAuthor().toLowerCase().equals(searchValueLowerCase)) {
                        System.out.println(book);
                        return book;
                    } else return null;
                }
                case CATEGORY -> {
                    if (book.getCategory().toLowerCase().equals(searchValueLowerCase)) {
                        System.out.println(book);
                        return book;
                    } else return null;
                }
                default -> System.out.println("Nieprawidłowy typ wyszukiwania");
            }
        }
        System.out.println("Nie znaleziono książki o podanym parametrze");
        return null;
    }

    private void makeCorrections(Book book) {
        System.out.println(">>Nanoszenie poprawek<<");
        Book newBook = UserInterface.informationAboutBook();
        library.remove(book);
        library.add(newBook);
    }

    private void printUsersBookByDetail(String title) {
        String titleLC = title.toLowerCase();
        for (Book book : library) {
            if (book.getTitle().equals(titleLC))
                System.out.println(book);
            else System.out.println("Nie znaleziono książki o podanym parametrze");
        }
    }

    private void printDataAboutRentBookByDetail(String detail) {
        String detailLC = detail.toLowerCase();
        for (RentBook libraryRentBook : libraryRentBooks) {
            if (libraryRentBook.getBook().getTitle().equalsIgnoreCase(detailLC)) {
                System.out.println(libraryRentBook);
            } else System.out.println("Nie znaleziono książki o podanym parametrze");
        }
    }

    private void printAllBooks() {
        for (Book book : library) {
            if (book != null)
                System.out.println(book);
            else System.out.println("Brak książek w bibliotece");
        }
    }

    private void addAndDeleteBookAtLibrary(RentBook rentBook, Book bookForClient) {
        libraryRentBooks.add(rentBook);
        deleteBookTemporaryFromLibrary(bookForClient);
    }

    private void deleteBookTemporaryFromLibrary(Book bookForClient) {
        Iterator<Book> bookIterator = library.iterator();
        while (bookIterator.hasNext()) {
            Book book = bookIterator.next();
            String bookId = String.valueOf(book.getId());
            String bookIdTwo = String.valueOf(bookForClient.getId());
            if (bookId.equals(bookIdTwo)) {
                bookIterator.remove();
                System.out.println("Wypożyczono pomyślnie!");
                break;
            } else System.out.println("Książki nie udało się wypożyczyć");
        }
    }

    private void printRentBooksWithClients() {
        for (RentBook libraryRentBook : libraryRentBooks) {
            if (libraryRentBook != null)
                System.out.println(libraryRentBook);
            else System.out.println("Brak wypożyczonych książek");
        }
    }

    private void printBooks() {
        Collections.sort(library);// sortowanie alfabetycznie po tytule przed wyświetleniem
        int printOptions = UserInterface.howPrintBooks();
        if (UserOption.DETAILS_BOOK.getKod() == printOptions) {
            int findOption = UserInterface.optionToFindBook();
            task = 0;
            findBookByUserRecommendation(findOption);
        } else if (UserOption.ALL_BOOKS.getKod() == printOptions) {
            printAllBooks();
        } else System.err.println("Nie ma takiego wyboru");
    }

    private void printRentBooks() {
        Collections.sort(libraryRentBooks);
        int printOptions = UserInterface.howPrintBooks();
        if (UserOption.DETAILS_BOOK.getKod() == printOptions) {
            int findOption = UserInterface.optionToFindBook();
            task = 2;
            findBookByUserRecommendation(findOption);
            System.out.println();
        } else if (UserOption.ALL_BOOKS.getKod() == printOptions) {
            printRentBooksWithClients();
            System.out.println();
        } else System.err.println("Nie ma takiego wyboru");
    }

    private void readFromFile(FileStorage fileStorage) {
        int importOption = UserInterface.readHistoryOfRentOrAvailable();
        if (importOption == UserOption.AVAILABLE_BOOKS.getKod()) {
            String pathOfFile = UserInterface.nameOfFile();
            ArrayList<Book> newBooksList = fileStorage.readFileAndCreateNewList(pathOfFile);
            library.addAll(newBooksList);
        } else if (importOption == UserOption.HISTORY_BOOKS.getKod()) {
            String pathOfFile = UserInterface.nameOfFile();
            ArrayList<RentBook> rentBooks = fileStorage.readFileAndCreateNewListHistoryLibrary(pathOfFile);
            libraryRentBooks.addAll(rentBooks);
        } else System.err.println("Nie ma takiego wyboru");
    }

    private void saveLibraryAtFile(FileStorage fileStorage) {
        int saveOption = UserInterface.saveHistoryLibraryOrAvailable();
        if (saveOption == UserOption.AVAILABLE_BOOKS.getKod()) {
            String pathToSave = UserInterface.pathToSave();
            fileStorage.saveDataAtFile(library, pathToSave);
        } else if (saveOption == UserOption.HISTORY_BOOKS.getKod()) {
            String pathToSave = UserInterface.pathToSave();
            fileStorage.saveHistoryDataAtFile(libraryRentBooks, pathToSave);
        } else System.err.println("Nie ma takiego wyboru");
    }

    private void findBookInRentList(RentBook rentBook, String dateReturn) {
        boolean bookReturned = false;
        for (RentBook bookRents : libraryRentBooks) {
            if (bookRents.getBook().equals(rentBook.getBook()) && bookRents.getPerson().equals(rentBook.getPerson())) {
                RentBook rentBookNew = new RentBook(bookRents.getRentDate(), dateReturn,
                        rentBook.getBook(), rentBook.getPerson());
                libraryRentBooks.remove(bookRents);
                libraryRentBooks.add(rentBookNew);
                System.out.println(rentBookNew);
                library.add(bookRents.getBook());
                bookReturned = true;
            }
        }
        if (bookReturned) {
            System.out.println("Zwrócono książkę!");
        } else System.out.println("Nie udało się zwrócić książki");
    }

}
