/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import util.SHA256Encoder;

/**
 *
 * @author Chieko Yamamoto
 */
@Named
@SessionScoped
public class Bb extends SuperBb implements Serializable{
    
@Inject
protected LoginBean loginBean;

    /******** content-1/Screen-1/Item lists*********************/
    //Commandbotton-Cart

public String dispCart(){
        if(cart.isEmpty()){
            facesMessage("Your Shopping Cart is empty. ");   //message
            return "index";
        }
        return "cart?faces-redirect=true";  //go to cart.xhtml
    }
    
    public String dispOrderHistory(){
        return "/info/history?faces-redirect=true";  // go to order history
    }
    
    public String dispUser(){
        String name = getUserId();
        if (name==null){
            return "Welcome to KC Cap Shop!" ;
        }else{
            return "Hello " + name + "!" ;
        }
    }
    
    public String dispSignin(){
        return "/info/login?faces-redirect=true";   // go to Sign in
    }
    
    public String dispSignStatus(){
        if(getUserId()==null){
        return "Sign in";
        }else{
            return "Sign out";
        }
    }
    
    public String dispSignInOut(){
     if(getUserId()==null){
         return "/info/login?faces-redirect=true";
     }else{
         return loginBean.logout();
     }
     
}
    	// get item Description
	public String detail(Product product) {
		sel = product;			 // set selected tiem to "sel"
		return "detail?faces-redirect=true";
	}
	// Get item detail from DB(for content-1.xhtml)
	public List<Product> getFromDb() {
		List<Product> ls = null;
		try {
			ls = pm.getFromDb(priceItem, kindItem, productPage);
		} catch (Exception e) {
			facesMessage("Error: Fail to get item description from DB.");
		}
		return ls;
	}
	// re-setup for number of pages and items
	public void counterClear() {
		try {
			pm.counterClear(kindItem, productPage);
		} catch (Exception e) {
			facesMessage("Error: Fail to get DB initialize.");
		}
	}
	/* ******（content-2）**************************************/
	// add a item to cart
	public String addToCart() {
		addOrder();
		return "cart?faces-redirect=true";
	}
	// If the item is already in the cart, add 1 to the quantity.
        // If not, add the item to OrderLine(cart) entity.
	public OrderLine addOrder() {
		Long id = sel.getId();
		for (OrderLine pastOrder : cart) {			// loop to get item from cart
			Product c_product = pastOrder.getProduct();	// get product from item
			if (Objects.equals(c_product.getId(), id)) {    // check if product id and sel id are same
				pastOrder.increase();		        // if same, add 1 to quantity
				return pastOrder;
			}
		}
		OrderLine newOrder = new OrderLine(sel, 1);	// if sel id is not in the cart, 
		cart.add(newOrder);		                // add new item in the cart.
		return newOrder;
	}
	/* ******（content-3）**************************************/
	// go to Order form
	public String gotoOrderForm() {
		if (cart.isEmpty()) {	
			facesMessage("Your shopping cart is empty.");
			return "cart";
		}
		return "orderForm?faces-redirect=true";	
	}
	// delete item form cart
	public String delete(OrderLine order) {
		cart.remove(order);
		return null;
	}
	/* ******（content-4）**************************************/
	// Buy items in cart
	public String buy() {
		String uid = getUserId();// get User ID
		if (uid == null) {
			uid = "guest";	// if not sign in, set user ID "guest".
		}
		makeOrder(uid);		// Register items of cart (OrderLine) to AppOrder 
		clear();		// make cart empty
		return "confirm?faces-redirect=true";
	}
	// Register items of cart (OrderLine) to AppOrder 
	public void makeOrder(String uid) {
		Customer c = (Customer) customerDb.search(uid);
		double total = 0.00;
		for (OrderLine ol : cart) {
			total += ol.getQuantity() * ol.getProduct().getPrice(); 
		}
		AppOrder appOrder = new AppOrder(c, c_name, c_mail, c_msg, new Date(), total, cart);
		appOrderDb.add(appOrder);
	}
	

	public void clear() {
		c_name = c_address = c_mail = c_msg = null;	
		cart.clear();	// make cart empty
	}
	/* ******（content-7）**************************************/
	// Get order hitsoty
	public List<AppOrder> getHistory() {
		List<AppOrder> history = new ArrayList<>();
                String id = getUserId();
                Customer c = (Customer) customerDb.search(id);
		try {
			history = orderManager.getHistory(c.getGroup().getGroupKey().getGroupId(), id);
		} catch (Exception e) {
			facesMessage("Error: Fail to order histories from DB.");
		}
		return history;
	}
        
        /* ******（content-8）**************************************/
        // Create new account
        public String createCustomer(){
            //check user exist
           Customer nc = (Customer) customerDb.search(new_id);
           // If the user is already exists, 
           if(nc!=null){ 
               facesMessage("register" ,"The User name is already exists.");
               return null;
           }else{
            GroupKey key = new GroupKey(new_group, new_id);
            AppGroup group = new AppGroup(key, null);
            // encrypt the passsword
            Customer customer = new Customer(new_id, getEncodedPw(new_passwd), new_name, new_mail, group);
            group.setCustomer(customer);
            // create new customer(account)
            customerDb.add(customer);
            // SignIn with the new account
            loginBean.setUsername(new_id);
            loginBean.setPassword(new_passwd);
            loginBean.login();
            
            return "index?faces-redirect=true";
           }
            
        }
        // encrypt the password
        private String getEncodedPw(String pw) {
            SHA256Encoder encoder = new SHA256Encoder();
            return encoder.encodePassword(pw);
        }
        
}
