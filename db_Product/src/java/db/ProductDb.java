package db;

import util.DbService;
import entity.*;
import javax.ejb.Stateless;

@Stateless
public class ProductDb extends DbService {

    public ProductDb() {
        super(Product.class);
    }

}
