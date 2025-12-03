package book;
import java.util.List;

public class Main {
	 public static void main(String[] args) {
		 Bookstore.main(args);
	        System.out.println("🚀 Starting Online Bookstore System...\n");
	        
	        // Create sample books
	        Books book1 = new Books("Java Programming", "John Doe", 29.99, "java.jpg");
	        Books book2 = new Books("Spring Framework", "Jane Smith", 39.99, "spring.jpg");
	        Books book3 = new Books("Database Systems", "Mike Johnson", 34.99, "database.jpg");
	        
	        // Add books to database
	        Database.addBook(book1);
	        Database.addBook(book2);
	        Database.addBook(book3);
	        System.out.println("✅ Books added to database");
	        
	        // Create customers
	        Customer customer1 = new Customer("Alice Brown", "alice@email.com", "pass123");
	        Customer customer2 = new Customer("Bob Wilson", "bob@email.com", "pass456");
	        
	        // Add customers to database
	        Database.addCustomer(customer1);
	        Database.addCustomer(customer2);
	        System.out.println("✅ Customers added to database");
	        
	        // Add books to customer1's cart
	        Customer foundCustomer1 = Database.getCustomerById(customer1.getCustomerId());
	        foundCustomer1.getShoppingCart().addItem(book1);
	        foundCustomer1.getShoppingCart().addItem(book2);
	        Database.updateCustomer(foundCustomer1.getCustomerId(), foundCustomer1.getName(), foundCustomer1.getEmail());
	        
	        // Add books to customer2's cart
	        Customer foundCustomer2 = Database.getCustomerById(customer2.getCustomerId());
	        foundCustomer2.getShoppingCart().addItem(book3);
	        Database.updateCustomer(foundCustomer2.getCustomerId(), foundCustomer2.getName(), foundCustomer2.getEmail());
	        
	        // Display results
	        System.out.println("\n📊 Current Data:");
	        System.out.println("Customer 1: " + foundCustomer1.getName());
	        System.out.println("Cart: " + foundCustomer1.getShoppingCart().getItemCount() + " items");
	        System.out.println("Total: $" + foundCustomer1.getShoppingCart().getTotal());
	        
	        System.out.println("\nCustomer 2: " + foundCustomer2.getName());
	        System.out.println("Cart: " + foundCustomer2.getShoppingCart().getItemCount() + " items");
	        System.out.println("Total: $" + foundCustomer2.getShoppingCart().getTotal());
	        
	        // Test update functionality
	        System.out.println("\n🔄 Testing Update...");
	        Database.updateCustomer(customer1.getCustomerId(), "Alice Green", null);
	        Database.updateBook(book1.getBookId(), "Java Programming - Advanced", null, null);
	        
	        // Verify updates
	        Customer updatedCustomer = Database.getCustomerById(customer1.getCustomerId());
	        Books updatedBook = Database.getBookById(book1.getBookId());
	        System.out.println("Updated Customer: " + updatedCustomer.getName());
	        System.out.println("Updated Book: " + updatedBook.getTitle());
	        
	        System.out.println("\n✅ Online Bookstore System Demo Completed!");
	        System.out.println("\n--- ALL CUSTOMERS ---");
	        List<Customer> allCustomers = Database.getAllCustomers();
	        for (Customer c : allCustomers) {
	            System.out.println("ID: " + c.getCustomerId() + 
	                              ", Name: " + c.getName() + 
	                              ", Email: " + c.getEmail() +
	                              ", Cart Items: " + c.getShoppingCart().getItemCount());
	        }

	        // View all books
	        System.out.println("\n--- ALL BOOKS ---");
	        List<Books> allBooks = Database.getAllBooks();
	        for (Books b : allBooks) {
	            System.out.println("ID: " + b.getBookId() + 
	                              ", Title: " + b.getTitle() + 
	                              ", Author: " + b.getAuthor() +
	                              ", Price: $" + b.getPrice());
	        }

	        // View all shopping carts
	        System.out.println("\n--- ALL SHOPPING CARTS ---");
	        List<ShoppingCart> allCarts = Database.getAllShoppingCarts();
	        for (ShoppingCart sc : allCarts) {
	            System.out.println("Cart ID: " + sc.getCartId() + 
	                              ", Items: " + sc.getItemCount() + 
	                              ", Total: $" + sc.getTotal());
	        }
	    }
	 
}
