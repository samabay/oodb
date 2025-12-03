package book;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
public class ShoppingCart {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long cartId;
	    
	    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    private List<Books> items;
	    
	    private double total;
	    
	    // Constructors
	    public ShoppingCart() {
	        this.items = new ArrayList<>();
	        this.total = 0.0;
	    }
	    
	    // Getters and Setters
	    public Long getCartId() { return cartId; }
	    public List<Books> getItems() { return items; }
	    public void setItems(List<Books> items) { this.items = items; }
	    public void setTotal(double total) { this.total = total; }
	    
	    // Methods
	    public void addItem(Books book) {
	        this.items.add(book);
	        calculateTotal();
	    }
	    
	    public void removeItem(Books book) {
	        this.items.remove(book);
	        calculateTotal();
	    }
	    
	    public void checkout() {
	        System.out.println("Checking out " + items.size() + " items. Total: $" + total);
	        this.items.clear();
	        this.total = 0.0;
	    }
	    
	    public double getTotal() {
	        calculateTotal();
	        return total;
	    }
	    
	    private void calculateTotal() {
	        this.total = 0.0;
	        for (Books book : items) {
	            this.total += book.getPrice();
	        }
	    }
	    
	    public int getItemCount() {
	        return items.size();
	    }
	    
	    @Override
	    public String toString() {
	        return "ShoppingCart [id=" + cartId + ", items=" + items.size() + ", total=$" + total + "]";
	    }
}
