package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "KCCS_CUSTOMER")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String passwd;
    private String name;
    private String mail;
    @OneToOne(cascade = {CascadeType.ALL})
    private AppGroup group;

    public Customer() {
    }

    public Customer(String id, String passwd, String name, String mail, AppGroup group) {
        this.id = id;
        this.passwd = passwd;
        this.name = name;
        this.mail = mail;
        this.group = group;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public AppGroup getGroup() {
        return group;
    }

    public void setGroup(AppGroup group) {
        this.group = group;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", passwd=" + passwd + ", name=" + name + ", mail=" + mail + ", group=" + group + '}';
    }

}
