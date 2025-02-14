package library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class LibraryManagementSystem {
    private static final String URL = "jdbc:mysql://localhost:3306/library_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Pooja@7259165149";
    private static Connection conn;

    private JFrame frame;
    private JTextField titleField, authorField, idField;
    private JTextArea displayArea;

    public LibraryManagementSystem() {
        initializeGUI();
        connectDatabase();
    }

    private void initializeGUI() {
        frame = new JFrame("Library Management System");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel titleLabel = new JLabel("Title:");
        titleField = new JTextField(20);
        JLabel authorLabel = new JLabel("Author:");
        authorField = new JTextField(20);
        JButton addButton = new JButton("Add Book");
        addButton.addActionListener(e -> addBook());

        JLabel idLabel = new JLabel("Book ID:");
        idField = new JTextField(5);
        JButton deleteButton = new JButton("Delete Book");
        deleteButton.addActionListener(e -> deleteBook());
        
        inputPanel.add(titleLabel);
        inputPanel.add(titleField);
        inputPanel.add(authorLabel);
        inputPanel.add(authorField);
        inputPanel.add(addButton);

        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.add(idLabel);
        controlPanel.add(idField);
        controlPanel.add(deleteButton);
        JButton viewButton = new JButton("View Books");
        viewButton.addActionListener(e -> viewBooks());
        controlPanel.add(viewButton);

        displayArea = new JTextArea(15, 40);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(controlPanel, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);
        
        frame.setVisible(true);
    }

    private void connectDatabase() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addBook() {
        try {
            String title = titleField.getText();
            String author = authorField.getText();
            if (title.isEmpty() || author.isEmpty()) {
                displayArea.setText("Please enter title and author.");
                return;
            }

            String sql = "INSERT INTO books (title, author) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, title);
                stmt.setString(2, author);
                stmt.executeUpdate();
                displayArea.setText("Book added successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void viewBooks() {
        try {
            String sql = "SELECT * FROM books";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
                StringBuilder booksList = new StringBuilder("Books in Library:\n");
                while (rs.next()) {
                    booksList.append("ID: ").append(rs.getInt("id"))
                             .append(" | Title: ").append(rs.getString("title"))
                             .append(" | Author: ").append(rs.getString("author"))
                             .append("\n");
                }
                displayArea.setText(booksList.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteBook() {
        try {
            int id = Integer.parseInt(idField.getText());
            String sql = "DELETE FROM books WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                int rows = stmt.executeUpdate();
                displayArea.setText(rows > 0 ? "Book deleted successfully!" : "Book not found.");
            }
        } catch (SQLException | NumberFormatException e) {
            displayArea.setText("Please enter a valid book ID.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LibraryManagementSystem::new);
    }
}
