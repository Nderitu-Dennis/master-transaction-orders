package tech.csm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import tech.csm.entity.Order;
import tech.csm.util.HibernateUtil;

@WebServlet("/orders")

public class OrderController extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		open a session
		Session session = HibernateUtil.getSessionFactory().openSession();
		

        // HQL: all orders for customer id=1
        String hql = "from Order o where o.customer.customerId =1";
        List<Order> orders = session.createQuery(hql, Order.class)
                                    .list();
         

        session.close();
        
//        forwading request to orders jsp
        req.setAttribute("orders",orders);
        req.getRequestDispatcher("orders.jsp").forward(req, res);
    }

    public void destroy() {
        HibernateUtil.closeSessionFactory();
    }
    
//    todo - insert/update/delete-use transactions in HQL
}
		
	


