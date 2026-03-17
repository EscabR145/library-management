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
    public void borrowBook() {this.isBookAvailable = false;}
    public void returnBook() {this.isBookAvailable = true;}

    public String toString()
    {
        return bookId + "-" + bookTitle + " by " + bookAuthor + " [ " + (isBookAvailable ? "Available" : "Borrowed") + " ] ";
    }
}