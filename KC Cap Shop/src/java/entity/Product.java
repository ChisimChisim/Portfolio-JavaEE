/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;


/**
 *
 * @author chieko Yamamoto
 */
@NamedQueries ({
	@NamedQuery(name=Product.Qall,			query="select c from Product c ORDER BY c.id"),
	@NamedQuery(name=Product.QallASC,		query="select c from Product c ORDER BY c.price"),
	@NamedQuery(name=Product.QallDESC,		query="select c from Product c ORDER BY c.price DESC"),
	@NamedQuery(name=Product.Count_Qall,	        query="select COUNT(c) from Product c"),
	
	@NamedQuery(name=Product.QKind,			query="select c from Product c where c.kind=:valueOfKind ORDER BY c.id"),
	@NamedQuery(name=Product.QkindASC,		query="select c from Product c where c.kind=:valueOfKind ORDER BY c.price"),
	@NamedQuery(name=Product.QkindDESC,		query="select c from Product c where c.kind=:valueOfKind ORDER BY c.price DESC"),
	@NamedQuery(name=Product.Count_QKind,	        query="select COUNT(c) from Product c where c.kind=:valueOfKind")

})
@Entity
@Table(name="KCCS_PRODUCT")
public class Product implements Serializable {

	public static final String Qall			= "Qall";
	public static final String QallASC		= "QallASC";
	public static final String QallDESC		= "QallDESC";
	public static final String QKind		= "QKind";
	public static final String QkindASC		= "QkindASC";
	public static final String QkindDESC	= "QkindDESC";
	
	public static final String Count_Qall	= "Count_Qall";
	public static final String Count_QKind	= "Count_QKind";
	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private	String	name;
	private	Double	price;
	@Enumerated(EnumType.STRING)
	private	AppKind	kind;
	@Lob
	private	String	text;
	@Lob
	@Basic(fetch=FetchType.LAZY)	
	private	byte[]	pic_S;
	@Lob
	@Basic(fetch=FetchType.LAZY)
	private	byte[]	pic_L;
	
	public	Product(){}
	public	Product(String name, Double price, AppKind kind, String text,byte[] pic_S, byte[] pic_L){
		this.name	=	name;
		this.price	=	price;
		this.kind	=	kind;
		this.text	=	text;
		this.pic_S	=	pic_S;
		this.pic_L	=	pic_L;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public AppKind getKind() {
		return kind;
	}

	public void setKind(AppKind kind) {
		this.kind = kind;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public byte[] getPic_S() {
		return pic_S;
	}

	public void setPic_S(byte[] pic_S) {
		this.pic_S = pic_S;
	}

	public byte[] getPic_L() {
		return pic_L;
	}

	public void setPic_L(byte[] pic_L) {
		this.pic_L = pic_L;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 97 * hash + Objects.hashCode(this.id);
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
		final Product other = (Product) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Product{" + "id=" + id + ", name=" + name + ", price=" + price + ", kind=" + kind + '}';
	}


	
}
