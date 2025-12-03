package book;
import javax.persistence.*;

@Entity
public class Books {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long bookId;
	    
	    private String title;
	    private String author;
	    private double price;
	    
	    
	    // Constructors
	    public Books() {}
	    
	    public Books(String title, String author, double price, String imageUrl) {
	        this.title = title;
	        this.author = author;
	        this.price = price;
	        
	    }
	    
	    // Getters and Setters
	    public Long getBookId() { return bookId; }
	    public String getTitle() { return title; }
	    public void setTitle(String title) { this.title = title; }
	    public String getAuthor() { return author; }
	    public void setAuthor(String author) { this.author = author; }
	    public double getPrice() { return price; }
	    public void setPrice(double price) { this.price = price; }
	   
	    
	    // Methods
	    public String getInfo() {
	        return String.format("Title: %s, Author: %s, Price: $%.2f", title, author, price);
	    }
	    
	    @Override
	    public String toString() {
	        return "Book [id=" + bookId + ", title=" + title + ", author=" + author + ", price=$" + price + "]";
	    }
}
