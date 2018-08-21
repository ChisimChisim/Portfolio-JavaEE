/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author chieko Yamamoto
 */
@Entity
@Table(name="KCCS_ORDERLINE")
public class OrderLine implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private	Product	product;
    private	Integer	quantity=0;	

    public OrderLine(){}    
    public OrderLine(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }    
    
    public int increase(){
        if(quantity<10){       //set max quantity = 10 
        ++quantity;
        }
        return	quantity;
    }
    public int decrease(){
        if(quantity>0) --quantity;
        return	quantity;
    }
      
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
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
        final OrderLine other = (OrderLine) obj;
        return Objects.equals(this.id, other.id);
    }
    
    @Override
    public String toString() {
        return "OrderLine{" + "id=" + id + ", product=" + product + ", quantity=" + quantity + '}';
    }

   
}
