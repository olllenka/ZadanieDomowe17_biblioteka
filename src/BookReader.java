import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookReader {
    public static List<Book> loadFromFile() {
        List<Book> books = new ArrayList<>();
        try (var scanner = new Scanner(new File("books.csv"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splittedLine = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                String title = stripQuotes(splittedLine[0]);
                String author = stripQuotes(splittedLine[1]);
                books.add(new Book(title, author));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot load books");
        }
        return books;
    }
    private static String stripQuotes(String text) {
        return text.replaceAll("^\"|\"$", "");
    }
}
