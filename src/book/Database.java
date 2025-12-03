package book;
import java.util.List;

import javax.persistence.*;

public class Database {
	public static List<Customer> getAllCustomers() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/odbtest.odb");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
        List<Customer> customers = query.getResultList();
        
        em.close(); emf.close();
        return customers;
    }
	public static List<Books> getAllBooks() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/odbtest.odb");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Books> query = em.createQuery("SELECT b FROM Book b", Books.class);
        List<Books> books = query.getResultList();
        
        em.close(); emf.close();
        return books;
    }
	 public static List<ShoppingCart> getAllShoppingCarts() {
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/odbtest.odb");
	        EntityManager em = emf.createEntityManager();
	        
	        TypedQuery<ShoppingCart> query = em.createQuery("SELECT sc FROM ShoppingCart sc", ShoppingCart.class);
	        List<ShoppingCart> carts = query.getResultList();
	        
	        em.close(); emf.close();
	        return carts;
	    }
	
	  public static void addCustomer(Customer c) {
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/odbtest.odb");
	        EntityManager em = emf.createEntityManager();
	        
	        em.getTransaction().begin();
	        em.persist(c);
	        em.getTransaction().commit();
	        
	        em.close();
	        emf.close();
	    }
	    
	    public static void addBook(Books b) {
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/odbtest.odb");
	        EntityManager em = emf.createEntityManager();
	        
	        em.getTransaction().begin();
	        em.persist(b);
	        em.getTransaction().commit();
	        
	        em.close();
	        emf.close();
	    }
	    
	    public static void addShoppingCart(ShoppingCart sc) {
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/odbtest.odb");
	        EntityManager em = emf.createEntityManager();
	        
	        em.getTransaction().begin();
	        em.persist(sc);
	        em.getTransaction().commit();
	        
	        em.close();
	        emf.close();
	    }
	    
	 
	    public static Customer getCustomerById(Long id) {
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/odbtest.odb");
	        EntityManager em = emf.createEntityManager();
	        Customer c = em.find(Customer.class, id);
	        em.close(); emf.close();
	        return c;
	    }
	    
	    public static Books getBookById(Long id) {
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/odbtest.odb");
	        EntityManager em = emf.createEntityManager();
	        Books b = em.find(Books.class, id);
	        em.close(); emf.close();
	        return b;
	    }
	    
	    public static ShoppingCart getShoppingCartById(Long id) {
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/odbtest.odb");
	        EntityManager em = emf.createEntityManager();
	        ShoppingCart sc = em.find(ShoppingCart.class, id);
	        em.close(); emf.close();
	        return sc;
	    }
	    
	    // UPDATE methods
	    public static void updateCustomer(Long id, String newName, String newEmail) {
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/odbtest.odb");
	        EntityManager em = emf.createEntityManager();
	        Customer c = em.find(Customer.class, id);
	        try {
	            if (c != null) {
	                if (newName != null) {
	                    em.getTransaction().begin();
	                    c.setName(newName);
	                    em.getTransaction().commit();
	                }
	                if (newEmail != null) {
	                    em.getTransaction().begin();
	                    c.setEmail(newEmail);
	                    em.getTransaction().commit();
	                }
	            }
	        } catch (Exception e) {
	            if (em.getTransaction().isActive()) {
	                em.getTransaction().rollback();
	            }
	            e.printStackTrace();
	        } finally {
	            em.close(); emf.close();
	        }
	    }
	    
	    public static void updateBook(Long id, String newTitle, String newAuthor, Double newPrice) {
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/odbtest.odb");
	        EntityManager em = emf.createEntityManager();
	        Books b = em.find(Books.class, id);
	        try {
	            if (b != null) {
	                if (newTitle != null) {
	                    em.getTransaction().begin();
	                    b.setTitle(newTitle);
	                    em.getTransaction().commit();
	                }
	                if (newAuthor != null) {
	                    em.getTransaction().begin();
	                    b.setAuthor(newAuthor);
	                    em.getTransaction().commit();
	                }
	                if (newPrice != null) {
	                    em.getTransaction().begin();
	                    b.setPrice(newPrice);
	                    em.getTransaction().commit();
	                }
	            }
	        } catch (Exception e) {
	            if (em.getTransaction().isActive()) {
	                em.getTransaction().rollback();
	            }
	            e.printStackTrace();
	        } finally {
	            em.close(); emf.close();
	        }
	    }
	    
	    public static void updateShoppingCart(Long id, Double newTotal) {
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/odbtest.odb");
	        EntityManager em = emf.createEntityManager();
	        ShoppingCart sc = em.find(ShoppingCart.class, id);
	        try {
	            if (sc != null) {
	                if (newTotal != null) {
	                    em.getTransaction().begin();
	                    sc.setTotal(newTotal);
	                    em.getTransaction().commit();
	                }
	            }
	        } catch (Exception e) {
	            if (em.getTransaction().isActive()) {
	                em.getTransaction().rollback();
	            }
	            e.printStackTrace();
	        } finally {
	            em.close(); emf.close();
	        }
	    }
	    
	   
	    public static void deleteCustomer(Long id) {
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/odbtest.odb");
	        EntityManager em = emf.createEntityManager();
	        Customer c = em.find(Customer.class, id);
	        if (c != null) {
	            em.getTransaction().begin();
	            em.remove(c);
	            System.out.printf("SUCCESS: Customer with id: %d deleted successfully.", id);
	            em.getTransaction().commit();
	        } else {
	            System.out.printf("ERROR: There's no customer with id: %d in the database.", id);
	        }
	        em.close(); emf.close();
	    }
	    
	    public static void deleteBook(Long id) {
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/odbtest.odb");
	        EntityManager em = emf.createEntityManager();
	        Books b = em.find(Books.class, id);
	        if (b != null) {
	            em.getTransaction().begin();
	            em.remove(b);
	            System.out.printf("SUCCESS: Book with id: %d deleted successfully.", id);
	            em.getTransaction().commit();
	        } else {
	            System.out.printf("ERROR: There's no book with id: %d in the database.", id);
	        }
	        em.close(); emf.close();
	    }
	    
	    public static void deleteShoppingCart(Long id) {
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb/db/odbtest.odb");
	        EntityManager em = emf.createEntityManager();
	        ShoppingCart sc = em.find(ShoppingCart.class, id);
	        if (sc != null) {
	            em.getTransaction().begin();
	            em.remove(sc);
	            System.out.printf("SUCCESS: ShoppingCart with id: %d deleted successfully.", id);
	            em.getTransaction().commit();
	        } else {
	            System.out.printf("ERROR: There's no shopping cart with id: %d in the database.", id);
	        }
	        em.close(); emf.close();
	    }
	   
	
	  
}
