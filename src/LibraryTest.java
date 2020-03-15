public class LibraryTest {
    public static void main(String[] args) {
        User user1 = new User("ola");
        User user2 = new User("ala");
        User user3 = new User("ela");

        Book book1 = new Book("Fundamentals of Wavelets", "Goswami, Jaideva");
        Book book2 = new Book("Data Smart", "Foreman, John");
        Book book3 = new Book("God Created the Integers", "Hawking, Stephen");
        Book book4 = new Book("Superfreakonomics", "Dubner, Stephen");
        Book notExistingBook = new Book("Kubuś Puchatek", "A.A. Milne");

        Library library = new Library();

        System.out.println("Ilość dostępnych egzemplarzy " + book1 + " wynosi " + library.leftInStock(book1));
        library.rent(user1, book1);
        System.out.println("Książki wypożyczone przez " + user1 + " to " + library.getBooksRentedBy(user1));
        System.out.println("Ilość dostępnych egzemplarzy " + book1 + " wynosi " + library.leftInStock(book1));
        library.giveBack(user1, book1);
        System.out.println("Książki wypożyczone przez " + user1 + " to " + library.getBooksRentedBy(user1));
        System.out.println("Ilość dostępnych egzemplarzy " + book1 + " wynosi " + library.leftInStock(book1));
        System.out.println();

        System.out.println("POPRAWNE WYPOŻYCZENIA");
        rent(library, user1, book1);
        rent(library, user1, book1);
        rent(library, user2, book2);
        rent(library, user2, book3);
        System.out.println();

        System.out.println("NIEPOPRAWNE WYPOŻYCZENIA");
        rent(library, user3, book1);
        rent(library, user3, notExistingBook);
        System.out.println();

        System.out.println("ZWROTY");
        giveBack(library, user1, book1);
        giveBack(library, user1, book2);
    }

    private static void giveBack(Library library, User user, Book book) {
        if(library.giveBack(user, book))
            System.out.println("Książka " + book + " została poprawnie zwrócona przez użytkownika " + user);
        else
            System.out.println("Nie udało się zwrócić książki (książka nie istnieje w bazie lub użytkownik nie posiadał tej książki)");
    }

    private static void rent(Library library, User user, Book book) {
        if(library.rent(user,book))
            System.out.println("Książka " + book + " została wypożyczona użytkownikowi " + user);
        else
            System.out.println("Książka nie jest dostępna (nie istnieje w bazie lub wszystkie egzemplarze są aktualnie wypożyczone)");
    }
}
