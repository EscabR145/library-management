package services;
import java.util.ArrayList;
import java.util.HashMap;
import models.Book;
import models.Borrower;



public class LibraryService{
    FileHandler fileHandler = new FileHandler();
    ArrayList<Book> bookList = new ArrayList<>();
    ArrayList<Borrower> borrowerList = new ArrayList<>();
    HashMap<String, Book> bookMap = new HashMap<>();
    HashMap<String, Borrower> borrowerMap = new HashMap<>();
    public void registerBorrower(Borrower borrowerToRegister)
    {
        borrowerList.add(borrowerToRegister);
        borrowerMap.put(borrowerToRegister.getID(),borrowerToRegister);
        fileHandler.addBorrower(borrowerToRegister);
    }
    public void changeBorrower(Borrower borrowerToChange, Borrower newBorrower)
    {
        borrowerList.remove(borrowerToChange);
        borrowerList.add(newBorrower);
        borrowerMap.remove(borrowerToChange.getID());
        borrowerMap.put(newBorrower.getID(),newBorrower);
        fileHandler.saveToBorrowerFile(borrowerList);
    }
    public void deleteBorrower(String id)
    {
        Borrower borrowerToDelete = borrowerMap.get(id);
        borrowerList.remove(borrowerToDelete);
        borrowerMap.remove(id);
        fileHandler.saveToBorrowerFile(borrowerList);
    }
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
        fileHandler.saveToBookFile(bookList);
    }
    public void deleteBook(String id)
    {
        Book bookToDelete = bookMap.get(id);
        bookList.remove(bookToDelete);
        bookMap.remove(id);
        fileHandler.saveToBookFile(bookList);

    }
    public void borrowBook(String id)
    {
        Book borrowedBook = bookMap.get(id);
        bookList.remove(borrowedBook);
        bookMap.remove(borrowedBook.getID());
        borrowedBook.borrowBook();
        bookList.add(borrowedBook);
        bookMap.put(borrowedBook.getID(),borrowedBook);
        
    }
    public void returnBook(String id)
    {
        Book borrowedBook = bookMap.get(id);
        bookList.remove(borrowedBook);
        bookMap.remove(borrowedBook.getID());
        borrowedBook.returnBook();
        bookList.add(borrowedBook);
        bookMap.put(borrowedBook.getID(),borrowedBook);
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
        bookList = fileHandler.updateBookList();
        borrowerList = fileHandler.updateBorrowerList();

        //Rebuild the Hashmap
        bookMap.clear();
        borrowerList.clear();
        for(Book book : bookList)
        {
            bookMap.put(book.getID(), book);
        }
        for(Borrower borrower : borrowerList)
        {
            borrowerMap.put(borrower.getID(), borrower);
        }
    }
    public ArrayList<Book> getBookList()
    {
        return bookList;
    }


}