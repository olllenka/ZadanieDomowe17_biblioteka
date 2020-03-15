import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {

    public static final int INITIAL_STOCK_VALUE = 2;
    private List<Book> books;

    private Map<Book, Integer> bookStock;
    private Map<User, List<Book>> rentRegistry;

    public Library() {
        bookStock = new HashMap<>();
        books = BookReader.loadFromFile();
        for(Book book : books){
            bookStock.put(book, INITIAL_STOCK_VALUE);
        }
        rentRegistry = new HashMap<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public boolean rent(User user, Book book){
        if(isBookAvailable(book)){
            bookStock.put(book, bookStock.get(book)-1);
            addBookToUser(user, book);
            return true;
        }
        else
            return false;
    }

    private void addBookToUser(User user, Book book) {
        List<Book> userBooks;
        if(rentRegistry.containsKey(user)){
            userBooks = rentRegistry.get(user);
        }
        else {
            userBooks = new ArrayList<>();

        }
        userBooks.add(book);
        rentRegistry.put(user, userBooks);
    }

    private boolean isBookAvailable(Book book) {
        if(bookStock.containsKey(book)){
            if(bookStock.get(book)>0)
                return true;
            else
                return false;
        }
        else
            return false;
    }

    public boolean giveBack(User user, Book book){
        if(userHasBook(user, book)){
            removeBookFromUser(user, book);
            bookStock.put(book, bookStock.get(book)+1);
            return true;
        }
        else
            return false;
    }

    private void removeBookFromUser(User user, Book book) {
        List<Book> userBooks = rentRegistry.get(user);
        userBooks.remove(book);
        if(!userBooks.isEmpty()){
            rentRegistry.put(user, userBooks);
        }
        else{
            rentRegistry.remove(user);
        }
    }

    private boolean userHasBook(User user, Book book) {
        if(rentRegistry.containsKey(user)){
            if(rentRegistry.get(user).contains(book))
                return true;
            else
                return false;
        }
        else
            return false;
    }

    public List<Book> getBooksRentedBy(User user){
        if(rentRegistry.containsKey(user)){
            return rentRegistry.get(user);
        }
        return new ArrayList<>();
    }

    public int leftInStock(Book book){
        if(bookStock.containsKey(book)){
            return bookStock.get(book);
        }
        return 0;
    }
}
