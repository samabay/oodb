package book;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;


import book.Bookstore;
public class Bookstore extends JFrame {
	private static final long serialVersionUID = 1L;
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;
    
    // Login Panel Components
    private JTextField loginEmailField;
    private JPasswordField loginPasswordField;
    private JButton loginButton;
    private JButton registerButton;
    
    // Book Panel Components
    private JList<String> bookList;
    private JTextArea bookDetailsArea;
    private JButton addToCartButton;
    private JButton viewCartButton;
    
    // Cart Panel Components
    private JList<String> cartList;
    private JLabel totalLabel;
    private JButton checkoutButton;
    private JButton removeButton;
    
    /**
     * Create the frame.
     */
    public Bookstore() {
        setTitle("Online Bookstore System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center window
        
        createUI();
        setVisible(true);
    }
    
    private void createUI() {
        mainPanel = new JPanel(new BorderLayout());
        
        // Create tabbed pane for different sections
        tabbedPane = new JTabbedPane();
        
        // Tab 1: Login/Register
        tabbedPane.addTab("Login", createLoginPanel());
        
        // Tab 2: Browse Books
        tabbedPane.addTab("Books", createBooksPanel());
        
        // Tab 3: Shopping Cart
        tabbedPane.addTab("Cart", createCartPanel());
        
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        setContentPane(mainPanel);
    }
    
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Title
        JLabel titleLabel = new JLabel("Online Bookstore Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 30, 0);
        panel.add(titleLabel, gbc);
        
        // Email
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridy = 1; gbc.gridx = 0;
        panel.add(new JLabel("Email:"), gbc);
        
        gbc.gridx = 1;
        loginEmailField = new JTextField(20);
        panel.add(loginEmailField, gbc);
        
        // Password
        gbc.gridy = 2; gbc.gridx = 0;
        panel.add(new JLabel("Password:"), gbc);
        
        gbc.gridx = 1;
        loginPasswordField = new JPasswordField(20);
        panel.add(loginPasswordField, gbc);
        
        // Login Button
        gbc.gridy = 3; gbc.gridx = 0;
        loginButton = new JButton("Login");
        loginButton.addActionListener(e -> login());
        panel.add(loginButton, gbc);
        
        // Register Button
        gbc.gridx = 1;
        registerButton = new JButton("Register");
        registerButton.addActionListener(e -> register());
        panel.add(registerButton, gbc);
        
        return panel;
    }
    
    private JPanel createBooksPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Left: Book List
        String[] sampleBooks = {
            "Java Programming - $29.99",
            "Spring Framework - $39.99", 
            "Database Systems - $34.99",
            "Web Development - $44.99",
            "Algorithms - $49.99"
        };
        
        bookList = new JList<>(sampleBooks);
        bookList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        bookList.addListSelectionListener(e -> showBookDetails());
        
        JScrollPane listScrollPane = new JScrollPane(bookList);
        listScrollPane.setPreferredSize(new Dimension(300, 400));
        
        // Center: Book Details
        bookDetailsArea = new JTextArea();
        bookDetailsArea.setEditable(false);
        bookDetailsArea.setText("Select a book to see details...");
        bookDetailsArea.setBorder(BorderFactory.createTitledBorder("Book Details"));
        
        // Right: Buttons Panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(e -> addToCart());
        
        viewCartButton = new JButton("View Cart");
        viewCartButton.addActionListener(e -> tabbedPane.setSelectedIndex(2)); // Switch to cart tab
        
        buttonPanel.add(addToCartButton);
        buttonPanel.add(viewCartButton);
        
        // Add components
        panel.add(listScrollPane, BorderLayout.WEST);
        panel.add(bookDetailsArea, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.EAST);
        
        return panel;
    }
    
    private JPanel createCartPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Cart items list
        String[] cartItems = {
            "Java Programming - $29.99",
            "Spring Framework - $39.99"
        };
        
        cartList = new JList<>(cartItems);
        JScrollPane cartScrollPane = new JScrollPane(cartList);
        
        // Total panel
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.add(new JLabel("Total: "));
        totalLabel = new JLabel("$69.98");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalPanel.add(totalLabel);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        removeButton = new JButton("Remove Selected");
        removeButton.addActionListener(e -> removeFromCart());
        
        checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(e -> checkout());
        
        buttonPanel.add(removeButton);
        buttonPanel.add(checkoutButton);
        
        // Add components
        panel.add(new JLabel("Your Shopping Cart:", JLabel.CENTER), BorderLayout.NORTH);
        panel.add(cartScrollPane, BorderLayout.CENTER);
        panel.add(totalPanel, BorderLayout.SOUTH);
        panel.add(buttonPanel, BorderLayout.EAST);
        
        return panel;
    }
    
    // Action Methods (Connect to your Database)
    private void login() {
        String email = loginEmailField.getText();
        String password = new String(loginPasswordField.getPassword());
        
        // TODO: Connect to Database
        JOptionPane.showMessageDialog(this, 
            "Login attempted for: " + email + "\nPassword: " + password,
            "Login Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void register() {
        // TODO: Connect to Database
        JOptionPane.showMessageDialog(this, 
            "Registration form would open here",
            "Register", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showBookDetails() {
        String selected = bookList.getSelectedValue();
        if (selected != null) {
            bookDetailsArea.setText("Book: " + selected + 
                "\n\nDescription: This is a sample book description.\n" +
                "Author: John Doe\nISBN: 1234567890\n" +
                "Category: Programming\nStock: 10 available");
        }
    }
    
    private void addToCart() {
        String selected = bookList.getSelectedValue();
        if (selected != null) {
            JOptionPane.showMessageDialog(this, 
                "Added to cart: " + selected,
                "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, 
                "Please select a book first!",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void removeFromCart() {
        String selected = cartList.getSelectedValue();
        if (selected != null) {
            JOptionPane.showMessageDialog(this, 
                "Removed from cart: " + selected,
                "Removed", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void checkout() {
        int confirm = JOptionPane.showConfirmDialog(this,
            "Confirm checkout? Total: " + totalLabel.getText(),
            "Checkout", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, 
                "Checkout successful!\nThank you for your purchase.",
                "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    // Main method to run the GUI
    public static void main(String[] args) {
        // Run GUI on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new Bookstore();
        });
    }
}
	