import java.util.HashMap;
import java.util.Map;

public class BookFactory {
    private Map<String, Book> bookMap = new HashMap<>();
    private Map<String, BookStatus> bookStatusMap = new HashMap<>();

    public void addBook(Book book) {
        bookMap.put(book.getId(), book);
        bookStatusMap.put(book.getId(), BookStatus.FREE);
    }

    public Book getBook(String bookId) {
        return bookMap.get(bookId);
    }

    public BookStatus getBookStatus(String bookId) {
        return bookStatusMap.get(bookId);
    }

    public boolean issueBook(String bookId) {
        if (bookStatusMap.get(bookId) == BookStatus.FREE) {
            bookStatusMap.put(bookId, BookStatus.ISSUED);
            return true;
        }
        return false;
    }

    public void returnBook(String bookId) {
        bookStatusMap.put(bookId, BookStatus.FREE);
    }
}
