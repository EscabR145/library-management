package ui;
import services.LibraryService;

public class Main {
    public static void main(String[] args) {

        //Load Services and Update List From Files
        LibraryService libraryService = new LibraryService();
        libraryService.loadFromFiles();
        System.out.println(libraryService.getBookList().toString());

        /*Test Adding Books
        Book bookToAdd = new Book("5534","Hello Testingg", "Marcus Cox", "GoodQuestion");
        libraryService.addBook(bookToAdd);
        System.out.println(libraryService.getBookList().toString()); */

        /*Test Changing Books
        Book bookToChange = libraryService.searchBook("0010");
        Book editedBook = new Book("0100", "Brave New World", "Aldous Huxley", "Science Fiction/Dystopian");
        libraryService.editBook(bookToChange, editedBook); */

        //Test Registering Borrower
        
        
        

        

    }
}
