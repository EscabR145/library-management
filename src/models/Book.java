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

    public String getID() {return this.bookId;}
    public String getTitle() {return this.bookTitle;}
    public String getAuthor() {return this.bookAuthor;}
    public String getGenre() {return this.bookGenre;}
    public boolean getAvail() {return this.isBookAvailable;}

    public String toString()
    {
        return bookId + "-" + bookTitle + " by " + bookAuthor + " [ " + (isBookAvailable ? "Available" : "Borrowed") + " ] ";
    }
}