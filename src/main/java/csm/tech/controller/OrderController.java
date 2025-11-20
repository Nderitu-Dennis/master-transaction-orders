package csm.tech.controller;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import tech.csm.entity.Order;
import tech.csm.util.HibernateUtil;

@WebServlet("/orders")

public class OrderController extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
//		open a session
		Session session = HibernateUtil.getSessionFactory().openSession();
		

        // HQL: all orders for customer id=1
        String hql = "from Order o where o.customer.customerId =1";
        List<Order> orders = session.createQuery(hql, Order.class)
                                    .list();

        for(Order o : orders) {
//        	 print only the fields you explicitly access
            System.out.println(o.getOrderId() + " | " +
                               o.getOrderDate() +  " | " +
                               o.getQuantity());
                               
        }        

        session.close();
    }

    public void destroy() {
        HibernateUtil.closeSessionFactory();
    }
    
//    todo - insert/update/delete-use transactions in HQL
}
		
	


