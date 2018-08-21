/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.CustomerDb;
import javax.ejb.*;
import entity.*;
import javax.ejb.Stateless;
import util.SHA256Encoder;

/**
 *
 * @author Chieko Yamamoto
 */
@Stateless
public class InitCustomers {

    @EJB
    CustomerDb db;

    String[] id = {"admin", "yamamoto", "guest"};
    String[] passwd = {"adminP", "yamamotoP", "guestP"};
    String[] name = {"administrator", "Chieko Yamamoto", ""};
    String[] mail = {"admin@com", "yamamoto@com", ""};
    String[] group = {"admin", "user", ""};

    public void init() {
        for (int i = 0; i < id.length; i++) {
            GroupKey key = new GroupKey(group[i], id[i]);
            AppGroup group = new AppGroup(key, null);
            // encrypt the passsword
            Customer customer = new Customer(id[i], getEncodedPw(passwd[i]), name[i], mail[i], group);
            group.setCustomer(customer);
            db.add(customer);
        }
    }

    // encrypt the password
    private String getEncodedPw(String pw) {
        SHA256Encoder encoder = new SHA256Encoder();
        return encoder.encodePassword(pw);
    }
}
