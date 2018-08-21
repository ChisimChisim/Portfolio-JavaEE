package beans;

import entity.AppKind;
import entity.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import util.Pagenation;

@Stateless
public class ProductManager {
	@PersistenceContext
	EntityManager	em;
        
	public  List<Product> getFromDb(int priceItem, AppKind kindItem, Pagenation productPage){
		TypedQuery<Product> query = null;
		if(kindItem==AppKind.NONE ){
			if(priceItem==1){
				query = em.createNamedQuery(Product.Qall, Product.class);
			}else if(priceItem==2){
				query = em.createNamedQuery(Product.QallASC, Product.class);
			}else{
				query = em.createNamedQuery(Product.QallDESC, Product.class);
			}
		}else{
			if(priceItem==1){
				query = em.createNamedQuery(Product.QKind, Product.class);
			}else if(priceItem==2){
				query = em.createNamedQuery(Product.QkindASC, Product.class);
			}else{
				query = em.createNamedQuery(Product.QkindDESC, Product.class);
			}
			query.setParameter("valueOfKind", kindItem);
		}
		query.setFirstResult(productPage.firstResult());
		query.setMaxResults(productPage.maxResult());
		return	query.getResultList();
	}
	
	public void counterClear(AppKind kindItem, Pagenation productPage){
		TypedQuery<Long> count_query = null;
		if(kindItem==AppKind.NONE ){
			count_query	=	em.createNamedQuery(Product.Count_Qall, Long.class);
		}else{
			count_query	=	em.createNamedQuery(Product.Count_QKind, Long.class);
			count_query.setParameter("valueOfKind", kindItem);
		}
		long countResult = count_query.getSingleResult();
		productPage.setup((int)countResult, 5);
	}


}
