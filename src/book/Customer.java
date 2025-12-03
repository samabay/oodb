package book;
import javax.persistence.*;

@Entity
public class Customer {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long customerId;
	    
	    private String name;
	    private String email;
	    private String password;
	    
	    @OneToOne(cascade = CascadeType.ALL)
	    private ShoppingCart shoppingCart;
	    
	    // Constructors
	    public Customer() {
	        this.shoppingCart = new ShoppingCart();
	    }
	    
	    public Customer(String name, String email, String password) {
	        this.name = name;
	        this.email = email;
	        this.password = password;
	        this.shoppingCart = new ShoppingCart();
	    }
	    
	    // Getters and Setters
	    public Long getCustomerId() { return customerId; }
	    public String getName() { return name; }
	    public void setName(String name) { this.name = name; }
	    public String getEmail() { return email; }
	    public void setEmail(String email) { this.email = email; }
	    public String getPassword() { return password; }
	    public void setPassword(String password) { this.password = password; }
	    public ShoppingCart getShoppingCart() { return shoppingCart; }
	    public void setShoppingCart(ShoppingCart shoppingCart) { this.shoppingCart = shoppingCart; }
	    
	    // Methods
	    public boolean login(String email, String password) {
	        return this.email.equals(email) && this.password.equals(password);
	    }
	    
	    public void register() {
	        System.out.println("Customer registered: " + this.name);
	    }
	    
	    @Override
	    public String toString() {
	        return "Customer [id=" + customerId + ", name=" + name + ", email=" + email + "]";
	    }
	}

