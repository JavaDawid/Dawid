public enum UserOption {
    ADD_BOOK(1, "Dodanie książki"),
    EDIT_BOOK(2, "Edycja książki"),
    DELETE_BOOK(3, "Usunięcie książki"),
    PRINT_BOOKS(4, "Wyświetlenie książek"),
    PRINT_RENT_BOOKS(5, "Wyświetlenie wypożyczonych książek"),
    READ_FROM_FILE(6, "Odczytaj książki z pliku"),
    SAVE_LIBRARY_AT_FILE(7, "Zapisz dane do pliku"),
    RENT_BOOK(8, "Wypożycz książkę"),
    RETURN_BOOK(9, "Formularz zwrotny książki"),
    EXIT(10, "Wyjście"),
    TITLE(11, "Tytuł"),
    AUTHOR(12, "Autor"),
    CATEGORY(13, "Kategoria"),
    DETAILS_BOOK(14, "Wyszukaj i wyświetl książki po tytuł/autor/kategoria"),
    ALL_BOOKS(15, "Wszystkie książki"),
    AVAILABLE_BOOKS(16, "Książki przechowywane w bibliotece"),
    HISTORY_BOOKS(17, "Historia wszystkich wypożyczonych książek");
    private final int kod;
    private final String description;

    public int getKod() {
        return kod;
    }

    public String getDescription() {
        return description;
    }

    UserOption(int kod, String description) {
        this.kod = kod;
        this.description = description;
    }

    public static UserOption fromKod(int kod) {
        for (UserOption value : UserOption.values()) {
            if (value.kod == kod) {
                return value;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return kod + "-" + description;
    }
}
