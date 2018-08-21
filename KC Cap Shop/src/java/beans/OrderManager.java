package beans;

import entity.AppOrder;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class OrderManager {
	@PersistenceContext
	EntityManager	em;
	
	public  List<AppOrder> getHistory(String groupId, String id){
            if(groupId.equals("admin")){
                TypedQuery<AppOrder> query = em.createNamedQuery(AppOrder.AppOrder_All, AppOrder.class);
                return	query.getResultList();   
            }else{
		TypedQuery<AppOrder> query = em.createNamedQuery(AppOrder.AppOrder_History, AppOrder.class);
		query.setParameter("customerId", id);
		return	query.getResultList();   
            }
        }
}
