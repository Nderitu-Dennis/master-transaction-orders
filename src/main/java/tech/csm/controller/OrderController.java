package tech.csm.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.hibernate.Session;
import org.hibernate.Transaction;

import tech.csm.entity.Customer;
import tech.csm.entity.Order;
import tech.csm.entity.Product;
import tech.csm.util.HibernateUtil;

@WebServlet("/orders")

public class OrderController extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


        // check if user clicked an action
        String action = req.getParameter("action");

        if ("insert".equals(action)) {
            insertOrder();
        } else if ("update".equals(action)) {
            updateOrder();
        } else if ("delete".equals(action)) {
            deleteOrder();
        }
		
		/*	open a session
		* A Session- lightweight connection to the DB
		You use it to save, update, delete, or query entities.
		*/
		
		Session session = HibernateUtil.getSessionFactory().openSession();		

       // String HQL= "from Order o where o.customer.customerId =1";
        String hql = "from Order";
        List<Order> orders = session.createQuery(hql, Order.class)
                                    .list();         

        session.close();
        
//        forwading request to orders jsp
        req.setAttribute("orders",orders);
        req.getRequestDispatcher("orders.jsp").forward(req, res);
    }
	
//	insert
	
    private void insertOrder() {

    	// getters/setters are required to set relationships in Hibernate.
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        String strDate = "2025-11-22";
        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
        
        /*extracting foreign keys from related entities, Customer & Product
         *  foreign keys need entity references, not raw IDs.
 */
        Customer custId = session.get(Customer.class, 2);
        Product prodId = session.get(Product.class, 3);
       
        Order o = new Order();
        
        o.setOrderId(6);  //orderId set manually cz of hibernate.hbm2ddl.auto = none & no generation strategy
        

        try {
			o.setOrderDate(sdf.parse(strDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        o.setQuantity(60);
        o.setCustomer(custId);
        o.setProduct(prodId);
        
        session.save(o);

        tx.commit();
        session.close();
    }
    
//    update
    // todo-finish w this, update()
    
    private void updateOrder() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        String hql = "update Order set quantity = ?1 where orderId = ?2";

        session.createQuery(hql)
                .setParameter(1, 20)
                .setParameter(2, 1)
                .executeUpdate();

        tx.commit();
        session.close();
    }
    
//    delete
    private void deleteOrder() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        String hql = "delete from Order where orderId = ?1";

        session.createQuery(hql)
                .setParameter(1,6)
                .executeUpdate();

        tx.commit();
        session.close();
    }
       
    
    
	
    public void destroy() {
        HibernateUtil.closeSessionFactory();
    }
    
}
		
	


