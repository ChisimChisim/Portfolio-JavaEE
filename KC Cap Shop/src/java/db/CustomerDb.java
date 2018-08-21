/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entity.*;
import javax.ejb.Stateless;
import util.TryCatchDb;

/**
 *
 * @author chieko Yamamoto
 */
@Stateless
public class CustomerDb extends TryCatchDb{
	
	public CustomerDb() {
		super(Customer.class);
                
        }
}
