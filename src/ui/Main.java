package ui;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import models.*;
import services.*;

/**
 * Main GUI for the Library Management System.
 *
 * This class builds a simple Swing interface for managing books and borrowers.
 * Users can add, update, delete, search, borrow, and return books.
 * Users can also add, update, delete, and search borrowers.
 */
public class Main extends JFrame {

    private LibraryService service = new LibraryService();

    private JTable bookTable, borrowerTable;
    private DefaultTableModel bookModel, borrowerModel;

    private JTextField bookIdField, titleField, authorField, genreField, searchBookField;
    private JTextField borrowerIdField, borrowerNameField, borrowerContactField, searchBorrowerField;
//Create Main window and leads all data into the ui
    public Main() {
        service.loadFromFiles();

        setTitle("Library Management System");
        setSize(950, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Books", createBookPanel());
        tabs.addTab("Borrowers", createBorrowerPanel());

        add(tabs);
    }
//Create the book tab, contains all book controls and the book table    
    private JPanel createBookPanel() {
        bookModel = new DefaultTableModel(new String[]{"ID", "Title", "Author", "Genre", "Status"}, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
        };

        bookTable = new JTable(bookModel);
        refreshBookTable();

        bookIdField = new JTextField(10);
        titleField = new JTextField(10);
        authorField = new JTextField(10);
        genreField = new JTextField(10);
        searchBookField = new JTextField(10);

        JPanel searchPanel = new JPanel();
        JButton searchButton = new JButton("Search ID");
        searchPanel.add(new JLabel("Search"));
        searchPanel.add(searchBookField);
        searchPanel.add(searchButton);

        JPanel formPanel = new JPanel();
        formPanel.add(new JLabel("ID"));
        formPanel.add(bookIdField);
        formPanel.add(new JLabel("Title"));
        formPanel.add(titleField);
        formPanel.add(new JLabel("Author"));
        formPanel.add(authorField);
        formPanel.add(new JLabel("Genre"));
        formPanel.add(genreField);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton borrowButton = new JButton("Borrow");
        JButton returnButton = new JButton("Return");
        JButton clearButton = new JButton("Clear");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(borrowButton);
        buttonPanel.add(returnButton);
        buttonPanel.add(clearButton);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(bookTable), BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(formPanel, BorderLayout.NORTH);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);
        panel.add(southPanel, BorderLayout.SOUTH);

        bookTable.getSelectionModel().addListSelectionListener(e -> {
            int row = bookTable.getSelectedRow();
            if (row != -1) {
                bookIdField.setText(bookModel.getValueAt(row, 0).toString());
                titleField.setText(bookModel.getValueAt(row, 1).toString());
                authorField.setText(bookModel.getValueAt(row, 2).toString());
                genreField.setText(bookModel.getValueAt(row, 3).toString());
            }
        });

        addButton.addActionListener(e -> addBook());
        updateButton.addActionListener(e -> updateBook());
        deleteButton.addActionListener(e -> deleteBook());
        borrowButton.addActionListener(e -> borrowBook());
        returnButton.addActionListener(e -> returnBook());
        clearButton.addActionListener(e -> clearBookFields());
        searchButton.addActionListener(e -> searchBook());

        return panel;
    }
//Create the borrower tab, contains all the borrower controls and borrower table
    private JPanel createBorrowerPanel() {
        borrowerModel = new DefaultTableModel(new String[]{"ID", "Name", "Contact"}, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
        };

        borrowerTable = new JTable(borrowerModel);
        refreshBorrowerTable();

        borrowerIdField = new JTextField(10);
        borrowerNameField = new JTextField(10);
        borrowerContactField = new JTextField(10);
        searchBorrowerField = new JTextField(10);

        JPanel searchPanel = new JPanel();
        JButton searchButton = new JButton("Search ID");
        searchPanel.add(new JLabel("Search"));
        searchPanel.add(searchBorrowerField);
        searchPanel.add(searchButton);

        JPanel formPanel = new JPanel();
        formPanel.add(new JLabel("ID"));
        formPanel.add(borrowerIdField);
        formPanel.add(new JLabel("Name"));
        formPanel.add(borrowerNameField);
        formPanel.add(new JLabel("Contact"));
        formPanel.add(borrowerContactField);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton clearButton = new JButton("Clear");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(borrowerTable), BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(formPanel, BorderLayout.NORTH);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);
        panel.add(southPanel, BorderLayout.SOUTH);

        borrowerTable.getSelectionModel().addListSelectionListener(e -> {
            int row = borrowerTable.getSelectedRow();
            if (row != -1) {
                borrowerIdField.setText(borrowerModel.getValueAt(row, 0).toString());
                borrowerNameField.setText(borrowerModel.getValueAt(row, 1).toString());
                borrowerContactField.setText(borrowerModel.getValueAt(row, 2).toString());
            }
        });

        addButton.addActionListener(e -> addBorrower());
        updateButton.addActionListener(e -> updateBorrower());
        deleteButton.addActionListener(e -> deleteBorrower());
        clearButton.addActionListener(e -> clearBorrowerFields());
        searchButton.addActionListener(e -> searchBorrower());

        return panel;
    }
//Reload the book table so it matches the data in the bookList
    private void refreshBookTable() {
        bookModel.setRowCount(0);
        for (Book b : service.getBookList()) {
            String status = b.getAvail();
            bookModel.addRow(new Object[]{b.getID(), b.getTitle(), b.getAuthor(), b.getGenre(), status});
        }
    }
//Reload the borrower table so it matches the data in borrowerList
    private void refreshBorrowerTable() {
        borrowerModel.setRowCount(0);
        for (Borrower b : service.getBorrowerList()) {
            borrowerModel.addRow(new Object[]{b.getID(), b.getName(), b.getEmail()});
        }
    }
//Check if any book fields are empty, return true if at least one is empty
    private boolean emptyBookFields() {
        return bookIdField.getText().trim().isEmpty() ||
               titleField.getText().trim().isEmpty() ||
               authorField.getText().trim().isEmpty() ||
               genreField.getText().trim().isEmpty();
    }
//Check if any borrower fields are empty, return true if at least one is empty
    private boolean emptyBorrowerFields() {
        return borrowerIdField.getText().trim().isEmpty() ||
               borrowerNameField.getText().trim().isEmpty() ||
               borrowerContactField.getText().trim().isEmpty();
    }
//Take data from fields and create book object, add this to the list
    private void addBook() {
        if (emptyBookFields()) {
            JOptionPane.showMessageDialog(this, "Fill in all book fields.");
            return;
        }
        if (service.searchBook(bookIdField.getText().trim()) != null) {
            JOptionPane.showMessageDialog(this, "Book ID already exists.");
            return;
        }

        Book b = new Book(bookIdField.getText().trim(), titleField.getText().trim(), authorField.getText().trim(), genreField.getText().trim());
        service.addBook(b);
        refreshBookTable();
        clearBookFields();
    }
//Take data from fields and calls editBook function in library service to update book
    private void updateBook() {
        int row = bookTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a book first.");
            return;
        }
        if (emptyBookFields()) {
            JOptionPane.showMessageDialog(this, "Fill in all book fields.");
            return;
        }

        Book oldBook = service.searchBook(bookModel.getValueAt(row, 0).toString());
        Book newBook = new Book(bookIdField.getText().trim(), titleField.getText().trim(), authorField.getText().trim(), genreField.getText().trim());
        Book existing = service.searchBook(bookIdField.getText().trim());

        if (existing != null && !oldBook.getID().equals(bookIdField.getText().trim())) {
            JOptionPane.showMessageDialog(this, "New ID already exists.");
            return;
        }

        service.editBook(oldBook, newBook);
        refreshBookTable();
        clearBookFields();
    }
//Delete the book from list and map
    private void deleteBook() {
        int row = bookTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a book first.");
            return;
        }

        service.deleteBook(bookModel.getValueAt(row, 0).toString());
        refreshBookTable();
        clearBookFields();
    }
//Mark the book as borrowed
    private void borrowBook() {
        int row = bookTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a book first.");
            return;
        }

        String id = bookModel.getValueAt(row, 0).toString();
        Book b = service.searchBook(id);
        if (b.getAvail().equals("Borrowed")) {
            JOptionPane.showMessageDialog(this, "Book already borrowed.");
            return;
        }

        service.borrowBook(id);
        refreshBookTable();
        clearBookFields();
    }
//Mark the book as returned
    private void returnBook() {
        int row = bookTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a book first.");
            return;
        }

        String id = bookModel.getValueAt(row, 0).toString();
        Book b = service.searchBook(id);
        if (b.getAvail().equals("Available")) {
            JOptionPane.showMessageDialog(this, "Book already returned.");
            return;
        }

        service.returnBook(id);
        refreshBookTable();
        clearBookFields();
    }
//Search book by the id and make it selected
    private void searchBook() {
        String id = searchBookField.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter a book ID.");
            return;
        }

        for (int i = 0; i < bookModel.getRowCount(); i++) {
            if (bookModel.getValueAt(i, 0).toString().equals(id)) {
                bookTable.setRowSelectionInterval(i, i);
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Book not found.");
    }
//Add borrower to borrower list from data in fields
    private void addBorrower() {
        if (emptyBorrowerFields()) {
            JOptionPane.showMessageDialog(this, "Fill in all borrower fields.");
            return;
        }
        if (findBorrowerById(borrowerIdField.getText().trim()) != null) {
            JOptionPane.showMessageDialog(this, "Borrower ID already exists.");
            return;
        }

        Borrower b = new Borrower(borrowerIdField.getText().trim(), borrowerNameField.getText().trim(), borrowerContactField.getText().trim());
        service.registerBorrower(b);
        refreshBorrowerTable();
        clearBorrowerFields();
    }
//Take data from fields and create borrower object then add borrower to list
    private void updateBorrower() {
        int row = borrowerTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a borrower first.");
            return;
        }
        if (emptyBorrowerFields()) {
            JOptionPane.showMessageDialog(this, "Fill in all borrower fields.");
            return;
        }

        Borrower oldBorrower = findBorrowerById(borrowerModel.getValueAt(row, 0).toString());
        Borrower newBorrower = new Borrower(borrowerIdField.getText().trim(), borrowerNameField.getText().trim(), borrowerContactField.getText().trim());
        Borrower existing = findBorrowerById(borrowerIdField.getText().trim());

        if (existing != null && !oldBorrower.getID().equals(borrowerIdField.getText().trim())) {
            JOptionPane.showMessageDialog(this, "New ID already exists.");
            return;
        }

        service.changeBorrower(oldBorrower, newBorrower);
        refreshBorrowerTable();
        clearBorrowerFields();
    }
//Delete the borrower from list and map
    private void deleteBorrower() {
        int row = borrowerTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a borrower first.");
            return;
        }

        service.deleteBorrower(borrowerModel.getValueAt(row, 0).toString());
        refreshBorrowerTable();
        clearBorrowerFields();
    }
//Search borrower by id 
    private void searchBorrower() {
        String id = searchBorrowerField.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter a borrower ID.");
            return;
        }

        for (int i = 0; i < borrowerModel.getRowCount(); i++) {
            if (borrowerModel.getValueAt(i, 0).toString().equals(id)) {
                borrowerTable.setRowSelectionInterval(i, i);
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Borrower not found.");
    }
//Set all the book fields to empty
    private void clearBookFields() {
        bookIdField.setText("");
        titleField.setText("");
        authorField.setText("");
        genreField.setText("");
        searchBookField.setText("");
        bookTable.clearSelection();
    }
//Return borrower object from id
    private Borrower findBorrowerById(String id) {
        for (Borrower b : service.getBorrowerList()) {
            if (b.getID().equals(id)) {
                return b;
            }
        }
        return null;
    }
//Set all the borrower fields to empty
    private void clearBorrowerFields() {
        borrowerIdField.setText("");
        borrowerNameField.setText("");
        borrowerContactField.setText("");
        searchBorrowerField.setText("");
        borrowerTable.clearSelection();
    }
//Main to show the table to user
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }

}    