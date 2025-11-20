package tech.csm.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import tech.csm.entity.Customer;
import tech.csm.entity.Order;
import tech.csm.entity.Product;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory=null;
	
	static {
//		register the classes
		Configuration cnf=new Configuration();
		cnf.addAnnotatedClass(Customer.class)
			.addAnnotatedClass(Order.class)
			.addAnnotatedClass(Product.class);
		sessionFactory=cnf.buildSessionFactory();
		
	}
	
	
	public static SessionFactory getSessionFactory(){
		
		return sessionFactory;
		
	}
	
	public static void closeSessionFactory() {
		if(sessionFactory!=null) {
			sessionFactory.close();
		}
	}
	

}
