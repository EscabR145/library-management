package services;
import java.util.ArrayList;
import java.util.HashMap;
import models.Book;



public class LibraryService{
    FileHandler fileHandler = new FileHandler();
    ArrayList<Book> bookList = new ArrayList<>();
    HashMap<String, Book> bookMap = new HashMap<>();
    public void addBook(Book bookToAdd)
    {
        bookList.add(bookToAdd);
        bookMap.put(bookToAdd.getID(), bookToAdd);
        fileHandler.saveBook(bookToAdd);
    }
    public void editBook(Book oldBook, Book newBook)
    {
        bookList.remove(oldBook);
        bookList.add(newBook);
        bookMap.remove(oldBook.getID());
        bookMap.put(newBook.getID(), newBook);
        fileHandler.editBook(oldBook, newBook);
    }
    public void borrowBook(ArrayList<Book> bookToBorrow)
    {

    }
    public void returnBook(ArrayList<Book> bookToReturn)
    {

    }     
    public Book searchBook(String bookId)
    {
        if(bookMap.containsKey(bookId))
            return bookMap.get(bookId);
        else
        {
            System.out.println("Book Not Found!");
            return null;
        }
    }
    public void loadFromFiles()
    {
        bookList = fileHandler.updateList();

        //Rebuild the Hashmap
        bookMap.clear();
        for(Book book : bookList)
        {
            bookMap.put(book.getID(), book);
        }
    }
    public ArrayList<Book> getBookList()
    {
        return bookList;
    }


}