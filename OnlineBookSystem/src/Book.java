public class Book {

    String id;
    String name;
    String summary;
    String authorName;
    String publisherName;
    BookCategory category;
    Book(String id, String name, String summary, String authorName, String publisherName, BookCategory category) {
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.authorName = authorName;
        this.publisherName = publisherName;
        this.category = category;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getSummary() {
        return summary;
    }
    public String getAuthorName() {
        return authorName;
    }
    public String getPublisherName() {
        return publisherName;
    }
    public BookCategory getCategory() {
        return category;
    }
}
