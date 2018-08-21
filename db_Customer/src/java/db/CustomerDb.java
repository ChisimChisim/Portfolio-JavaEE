package db;

import util.DbService;
import entity.*;
import javax.ejb.Stateless;

@Stateless
public class CustomerDb extends DbService {

    public CustomerDb() {
        super(Customer.class);
    }
}
