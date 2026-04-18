package models;

public class Borrower{
    private String borrowerName;
    private String borrowerId;
    private String borrowerEmail;

    public Borrower(String id, String name, String email)
    {
        this.borrowerName = name;
        this.borrowerId = id;
        this.borrowerEmail = email;
    }
    public String getID() {return this.borrowerId;}
    public String getName() {return this.borrowerName;}
    public String getEmail() {return this.borrowerEmail;}

    public String toString()
    {
        return borrowerId + "-" + borrowerName + " contact by: " + borrowerEmail;
    }
}