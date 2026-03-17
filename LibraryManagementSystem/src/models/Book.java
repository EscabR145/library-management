package models;

public class Book{
    private String bookId;
    private String bookTitle;
    private String bookAuthor;
    private String bookGenre;
    private boolean isBookAvailable;

    public Book(String id, String title, String author, String genre)
    {
        this.bookId = id;
        this.bookTitle = title;
        this.bookAuthor = author;
        this.bookGenre = genre;
        this.isBookAvailable = true;
    }
}