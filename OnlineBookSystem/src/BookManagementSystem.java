import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookManagementSystem {

    public static void main(String[] args) {
        System.out.println("Welcome to the Book Management System!");
        // Initialize the system and start the user interface
        BookManagementSystem system = new BookManagementSystem();
        system.start();
    }

    private void start() {
        BookFactory bookFactory = new BookFactory();
        Book book1 = new Book("1", "The Great Gatsby", "Classic novel", "F. Scott Fitzgerald", "Penguin", BookCategory.FICTION);
        Book book2 = new Book("2", "To Kill a Mockingbird", "Classic novel", "Harper Lee", "J.B. Lippincott & Co.", BookCategory.FICTION);
        bookFactory.addBook(book1);
        bookFactory.addBook(book2);

        User user1 = new User("1", "Vidhi", "vidhi@gmail.com", "1234");

        Map<User, List<Book>> userBooksMap = new HashMap<>();

        //This can be used to check which user  has which book issued
        UserFactory userFactory = new UserFactory(userBooksMap);

        // Simulate issuing
        if (bookFactory.issueBook("1")) {
            userBooksMap.put(user1, new ArrayList<>(List.of(book1)));
            System.out.println(user1.getName() + " issued book: " + book1.getName());
        }


        // Try issuing already issued book
        if (!bookFactory.issueBook("1")) {
            System.out.println("Book already issued!");
        }
    }

}
