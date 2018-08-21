/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.*;
import entity.*;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import util.Pagenation;

/**
 *
 * @author chieko Yamamoto
 * filed item and method
 * Initial setup
 * Manage pages
 * 
 */
public class SuperBb implements Serializable {
	/* ***** filed items ********************************************/
	protected Map<String, Integer> priceItems;	// Sort list 
	protected Map<String, AppKind> kindItems;	// Radio button
        protected Map<String, Integer> qtys;             // list of quantity
	protected AppKind kindItem = AppKind.NONE;	// Initial selection of Radio button
	protected Integer priceItem = 1;	        // Initial selection of Sort
        

	protected List<OrderLine> cart;		        // Cart
	protected Product sel;			        // product item descripution					

	protected String c_name;			// customer name (order form)
	protected String c_address;			// customer address (order form)
	protected String c_mail;			// customer email (order form)
	protected String c_msg;		                // customer message (order form)
        
        protected String new_id;                        // create new account id
        protected String new_passwd;                    // create new account password
        protected String new_name;                      // create new account name
        protected String new_mail;                      // create new account mail
        protected String new_group = "user";                     // create new account groupid
       
	/* ***** DB manager   *******************************/
	@EJB
	ProductDb productDb;		// Product DB
	@EJB
	CustomerDb customerDb;		// Customer Db
	@EJB
	AppOrderDb appOrderDb;		// Order history DB
	@EJB
	OrderLineDb orderLineDb;	// Order itmes DB
	/* ******* Utility Inject ******************/
	@EJB
	ProductManager pm;	        // Product manager
	@Inject
	protected transient Logger log;		// Logger
	@Inject
	protected Pagenation productPage;	// Page manager
	@EJB
	OrderManager orderManager;		// order history manager
	/* ***** Initialize ******************************************/
	@PostConstruct
	public void init() {
		cart = new ArrayList<>();				// cart

		priceItems = new LinkedHashMap<>();			
		priceItems.put("Relevance", 1);
		priceItems.put("Price: Low to High", 2);
		priceItems.put("Price: High to Low", 3);

		kindItems = new LinkedHashMap<>();			
		kindItems.put("All", AppKind.NONE);
		kindItems.put("Newest Arraival", AppKind.KIND1);
		kindItems.put("Recomend", AppKind.KIND2);
		kindItems.put("Anual Sale!", AppKind.KIND3);
                
                qtys = new LinkedHashMap<>();			        // quantity list
		qtys.put("1", 1);
		qtys.put("2", 2);
		qtys.put("3", 3);
                qtys.put("4", 4);
                qtys.put("5", 5);
                qtys.put("6", 6);
                qtys.put("7", 7);
                qtys.put("8", 8);
                qtys.put("9", 9);
                qtys.put("10", 10); 

		productPage.setup(productDb.dataCount(), 5);// Page Manager
	}
	/* *****<get small picture from DB>************************/
	public StreamedContent getPicS() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {
			ExternalContext sv = context.getExternalContext();
			Map<String, String> map = sv.getRequestParameterMap();
			String key = map.get("productId");
			Product e = (Product) (productDb.find(Long.valueOf(key)));
			ByteArrayInputStream in = new ByteArrayInputStream(e.getPic_S());
			DefaultStreamedContent ds = new DefaultStreamedContent(in);
			return ds;
		}
	}
	/* *****<get large picture from DB>************************/
	public StreamedContent getPicL() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {
			ExternalContext sv = context.getExternalContext();
			Map<String, String> map = sv.getRequestParameterMap();
			String key = map.get("productId");
			Product e = (Product) (productDb.find(Long.valueOf(key)));
			ByteArrayInputStream in = new ByteArrayInputStream(e.getPic_L());
			DefaultStreamedContent ds = new DefaultStreamedContent(in);
			return ds;
		}
	}
	/* ***** <Set FacesMassage>**************/
	public void facesMessage(String s) {
		FacesMessage msg = new FacesMessage(s);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	/* *****<Set FacesMassage with SEVERITY_ERROR >**************/
	public void facesErrorMsg(String s) {
		FacesMessage msg = new FacesMessage(s);
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	/* ***** Message SEVERITY ***********************************
		FacesMessage.SEVERITY_FATAL		
		FacesMessage.SEVERITY_ERROR	
		FacesMessage.SEVERITY_WARN		
		FacesMessage.SEVERITY_WARN		
	*************************************************************/
	public void facesMessage(FacesMessage.Severity severity, String s) {
		FacesMessage msg = new FacesMessage(s);
		msg.setSeverity(severity);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
        /* *****<Set FacesMassage with output messageId>**************/
        public void facesMessage(String id ,String s) {
		FacesMessage msg = new FacesMessage(s);
		FacesContext.getCurrentInstance().addMessage(id, msg);
	}
        
	/* *****<get Servlet>**************/
	public ExternalContext getServlet() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}
	/* *****<get request object>**************/
	public HttpServletRequest getRequest() {
		return (HttpServletRequest) getServlet().getRequest();
	}
	/* *****<get login user ID> ************************/
	public String getUserId() {
		return getRequest().getRemoteUser();
	}
	/* ***** getter and setter ***************************/
	public List<OrderLine> getCart() {
		return cart;
	}
	public void setCart(List<OrderLine> cart) {
		this.cart = cart;
	}
	public Product getSel() {
		return sel;
	}
	public void setSel(Product sel) {
		this.sel = sel;
	}
	public Integer getPriceItem() {
		return priceItem;
	}
	public void setPriceItem(Integer priceItem) {
		this.priceItem = priceItem;
	}
	public Map<String, Integer> getPriceItems() {
		return priceItems;
	}
	public AppKind getKindItem() {
		return kindItem;
	}
	public void setKindItem(AppKind kindItem) {
		this.kindItem = kindItem;
	}
	public Map<String, AppKind> getKindItems() {
		return kindItems;
	}

       public Map<String, Integer> getQtys() {
        return qtys;
       }

       public void setQtys(Map<String, Integer> qtys) {
        this.qtys = qtys;
       }
        
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getC_address() {
		return c_address;
	}
	public void setC_address(String c_address) {
		this.c_address = c_address;
	}
	public String getC_mail() {
		return c_mail;
	}
	public void setC_mail(String c_mail) {
		this.c_mail = c_mail;
	}
	public String getC_msg() {
		return c_msg;
	}
	public void setC_msg(String c_msg) {
		this.c_msg = c_msg;
	}

            public String getNew_id() {
                return new_id;
        }

        public void setNew_id(String new_id) {
            this.new_id = new_id;
        }

        public String getNew_passwd() {
            return new_passwd;
        }

        public void setNew_passwd(String new_passwd) {
            this.new_passwd = new_passwd;
        }

        public String getNew_name() {
            return new_name;
        }

        public void setNew_name(String new_name) {
            this.new_name = new_name;
        }

        public String getNew_mail() {
            return new_mail;
        }

        public void setNew_mail(String new_mail) {
            this.new_mail = new_mail;
        }

        public String getNew_group() {
            return new_group;
        }

        public void setNew_group(String new_group) {
            this.new_group = new_group;
        }

        public Pagenation getProductPage() {
                    return productPage;
            }

        public void setProductPage(Pagenation productPage) {
                    this.productPage = productPage;
            }


}

