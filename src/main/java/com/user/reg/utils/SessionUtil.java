package com.user.reg.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionUtil {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session session;
		
	public SessionUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public Session getSession() {

		if(session != null && session.isOpen()) {
			Transaction transaction = session.beginTransaction();
			if(!transaction.wasCommitted()) {
				transaction.commit();
			}
			session.close();
		}
			
		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    session = sessionFactory.openSession();
		}
				
		return session;
	}

}
