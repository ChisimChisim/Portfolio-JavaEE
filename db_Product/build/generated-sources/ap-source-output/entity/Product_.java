package entity;

import entity.AppKind;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-12T16:27:55")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, byte[]> pic_S;
    public static volatile SingularAttribute<Product, Integer> price;
    public static volatile SingularAttribute<Product, AppKind> kind;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile SingularAttribute<Product, Long> id;
    public static volatile SingularAttribute<Product, String> text;
    public static volatile SingularAttribute<Product, byte[]> pic_L;

}