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

    public String toString()
    {
        return borrowerId + "-" + borrowerName + " contact by: " + borrowerEmail;
    }
}