package beans;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class Bb implements Serializable {

    @EJB
    InitCustomers customers;

    public String add() {
        customers.init();
        return "output";
    }

}
