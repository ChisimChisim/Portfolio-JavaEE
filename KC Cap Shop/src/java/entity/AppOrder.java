/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author chieko Yamamoto
 */

@NamedQueries ({
	@NamedQuery(name=AppOrder.AppOrder_History,	query="select c from AppOrder c where c.customer.id=:customerId ORDER BY c.orderDate DESC"),
        @NamedQuery(name=AppOrder.AppOrder_All,       query="select c from AppOrder c ORDER BY c.orderDate DESC")
})

@Entity
@Table(name="KCCS_ORDER")
public class AppOrder implements Serializable {
    
    public static final String AppOrder_History = "AppOrder_History";
    public static final String AppOrder_All = "AppOrder_All";
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Customer customer;
    private String form_name;
    private String form_mail;
    private String form_msg;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date orderDate;
    private Double totalPrice;
    @OneToMany(cascade={CascadeType.ALL})
    private List<OrderLine> orderLine;

    public AppOrder() {
    }

    public AppOrder(Customer customer, String form_name, String form_mail,String form_msg, Date orderDate,Double totalPrice, List<OrderLine> orderLine){
		this.customer	=	customer;
		this.form_name  =	form_name;
		this.form_mail	=	form_mail;
		this.form_msg	=	form_msg;
		this.orderDate	=	orderDate;
		this.totalPrice	=	totalPrice;
		this.orderLine	=	orderLine;
	}
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getForm_name() {
        return form_name;
    }

    public void setForm_name(String form_name) {
        this.form_name = form_name;
    }

    public String getForm_mail() {
        return form_mail;
    }

    public void setForm_mail(String form_mail) {
        this.form_mail = form_mail;
    }

    public String getForm_msg() {
        return form_msg;
    }

    public void setForm_msg(String form_msg) {
        this.form_msg = form_msg;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderLine> getOrderLine() {
        return orderLine;
    }

    public void setOrderLine(List<OrderLine> orderLine) {
        this.orderLine = orderLine;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AppOrder other = (AppOrder) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AppOrder{" + "id=" + id + ", customer=" + customer + ", form_name=" + form_name + ", form_mail=" + form_mail + ", form_msg=" + form_msg + ", orderDate=" + orderDate + ", totalPrice=" + totalPrice + ", orderLine=" + orderLine + '}';
    }

  
}
