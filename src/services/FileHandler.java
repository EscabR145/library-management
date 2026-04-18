package services;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringJoiner;
import models.Book;
import models.Borrower;

public class FileHandler {

    // Method to read books from the file and return a list of Book objects
    public ArrayList<Book> updateBookList()
    {
        ArrayList<Book> bookList = new ArrayList<>();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("books.txt"));
            String line;
            while((line = br.readLine()) != null)
            {
                String[] bookData = line.split(",");
                if(bookData.length == 5)
                {
                    Book book = new Book(bookData[0], bookData[1], bookData[2], bookData[3]);
                    bookList.add(book);
                }
            }
            br.close();
        }
        catch(IOException e)
        {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return bookList;
    }

    //Method to read borrowers from the borrower file a return a list of Borrower objects
    public ArrayList<Borrower> updateBorrowerList()
    {
        ArrayList<Borrower> borrowerList = new ArrayList<>();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("borrowers.txt"));
            String line;
            while((line = br.readLine()) != null)
            {
                String[] borrowerData = line.split(",");
                if(borrowerData.length == 5)
                {
                    Borrower borrower = new Borrower(borrowerData[0], borrowerData[1], borrowerData[2]);
                    borrowerList.add(borrower);
                }
            }
            br.close();
        }
        catch(IOException e)
        {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return borrowerList;
    }

    //Method to update file from list 
    public void saveToBookFile(ArrayList<Book> listToSave)
    {
        try (FileWriter writer = new FileWriter("books.txt",false))
        {
            for(Book bookToAdd:listToSave)
            {
                StringJoiner joiner = new StringJoiner(",");
                joiner.add(bookToAdd.getID())
                .add(bookToAdd.getTitle())
                .add(bookToAdd.getAuthor())
                .add(bookToAdd.getGenre())
                .add(bookToAdd.getAvail());                
                writer.write(joiner.toString() + "\n");
                           
            }
            writer.close(); 
            
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    //Method to update borrower file from list
    public void saveToBorrowerFile(ArrayList<Borrower> listToSave)
    {
        try (FileWriter writer = new FileWriter("borrowers.txt",false))
        {
            for(Borrower borrower:listToSave)
            {
                StringJoiner joiner = new StringJoiner(",");
                joiner.add(borrower.getID())
                .add(borrower.getName())
                .add(borrower.getEmail());
                writer.write(joiner.toString() + "\n");
                           
            }
            writer.close(); 
            
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    //Method to add single borrower to borrower file
    public void addBorrower(Borrower borrower)
    {
        try (FileWriter writer = new FileWriter("borrowers.txt",true))
        {

            StringJoiner joiner = new StringJoiner(",");
            joiner.add(borrower.getID())
                  .add(borrower.getName())
                  .add(borrower.getEmail());
            writer.write(joiner.toString() + "\n");              
            writer.close(); 
            
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Method to add a new book to the file
    public void saveBook(Book bookToAdd)
    {
        try (FileWriter writer = new FileWriter("books.txt",true)) 
        {
            StringJoiner joiner = new StringJoiner(",");
            joiner.add(bookToAdd.getID())
                  .add(bookToAdd.getTitle())
                  .add(bookToAdd.getAuthor())
                  .add(bookToAdd.getGenre())
                  .add("true");
            writer.write(joiner.toString() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /*DEPRECATED:Method to edit a book in the file
    public void editBook(Book oldBook, Book newBook)
    {
        String lineToRemove = oldBook.getID() + "," + oldBook.getTitle() + "," + oldBook.getAuthor() + "," + oldBook.getGenre() + oldBook.getAvail();
        String lineToAdd = newBook.getID() + "," + newBook.getTitle() + "," + newBook.getAuthor() + "," + newBook.getGenre() + newBook.getAvail();
        String currentLine;
        String fileContents = "";
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("books.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("books.txt"));
            while((currentLine = reader.readLine()) != null)
            {
                String trimmedLine = currentLine.trim();
                fileContents+= trimmedLine + "\n";
            }

            fileContents = fileContents.replace(lineToRemove,lineToAdd);
            
            writer.write(fileContents);

            reader.close();
            writer.close();
        }   
        catch(IOException e)
        {
            System.out.println("Error editing file: " + e.getMessage());
        }
        
    }*/
}
