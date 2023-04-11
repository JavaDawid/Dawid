import java.io.*;
import java.util.ArrayList;

public class FileStorage {
    private String lineInFile;

    public ArrayList<Book> readFileAndCreateNewList(String pathOfFile) {
        ArrayList<Book> libraryFromFile = new ArrayList<>();
        try (
                FileReader fileReader = new FileReader(pathOfFile);
                BufferedReader bf = new BufferedReader(fileReader);
        ) {
            while ((lineInFile = bf.readLine()) != null) {
                String[] data = lineInFile.split(",");
                Book book = createBook(data);
                libraryFromFile.add(book);
            }
            System.err.println("Plik został zaimportowany!");
        } catch (IOException e) {
            System.err.println("Wprowadzona ścieżka jest niepoprawna!");
        }
        return libraryFromFile;
    }

    public ArrayList<RentBook> readFileAndCreateNewListHistoryLibrary(String pathOfFile) {
        ArrayList<RentBook> libraryHistoryFromFile = new ArrayList<>();
        try (
                FileReader fileReader = new FileReader(pathOfFile);
                BufferedReader bf = new BufferedReader(fileReader);
        ) {
            while ((bf.readLine()) != null) {
                String[] data = bf.readLine().split(",");
                Person person = createPerson(data);
                bf.readLine();
                String[] data2 = bf.readLine().split(",");
                Book book = createBook(data2);
                bf.readLine();
                String dateRent = bf.readLine();
                bf.readLine();
                String dateReturn = bf.readLine();
                RentBook rentBook = new RentBook(dateRent, dateReturn, book, person);
                libraryHistoryFromFile.add(rentBook);
            }
            System.err.println("Plik został zaimportowany!");
        } catch (IOException e) {
            System.err.println("Wprowadzona ścieżka jest niepoprawna!");
        }
        return libraryHistoryFromFile;
    }

    public void saveDataAtFile(ArrayList<Book> library, String pathToSave) {
        try (
                FileWriter fileWriter = new FileWriter(pathToSave);
                BufferedWriter bfw = new BufferedWriter(fileWriter);
        ) {
            for (Book book : library) {
                bfw.write(String.valueOf(book));
                bfw.newLine();
            }
            System.out.println("Zapisano pomyślnie");
            System.out.println();
        } catch (IOException e) {
            System.err.println("Zapis się nie powiódł");
            System.err.println("Spróbuj ponownie");
        }
    }

    public void saveHistoryDataAtFile(ArrayList<RentBook> libraryRentBooks, String pathToSave) {
        try (
                FileWriter fileWriter = new FileWriter(pathToSave);
                BufferedWriter bfw = new BufferedWriter(fileWriter);
        ) {
            for (RentBook rentBook : libraryRentBooks) {
                bfw.write(String.valueOf(rentBook));
                bfw.newLine();
            }
            System.out.println("Zapisano pomyślnie");
            System.out.println();
        } catch (IOException e) {
            System.err.println("Zapis się nie powiódł");
            System.err.println("Spróbuj ponownie");
        }
    }

    private Book createBook(String[] data) {
        String title = data[0];
        String author = data[1];
        String category = data[2];
        int publications = Integer.parseInt(data[3].trim());
        int id = Integer.parseInt(data[4].trim());
        return new Book(title, author, category, publications, id);
    }

    private Person createPerson(String[] data) {
        String firstName = data[0].trim();
        String lastName = data[1].trim();
        int numberClubCard = Integer.parseInt(data[2].trim());
        return new Person(firstName, lastName, numberClubCard);
    }

    public String getLineInFile() {
        return lineInFile;
    }
}
