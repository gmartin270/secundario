package org.web3.secundario.persist;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.web3.secundario.model.UsuarioDTO;
import org.web3.secundario.util.HibernateUtil;

public abstract class GenericDAO {

	protected Session session;
    protected Transaction tx;

    public GenericDAO() {}
    
    protected void saveOrUpdate(Object obj) {
        try {
            startOperation();
            session.saveOrUpdate(obj);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
        	HibernateUtil.close(session);
        }
    }

    protected void delete(Object obj) {
        try {
            startOperation();
            session.delete(obj);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
        	HibernateUtil.close(session);
        }
    }

    protected Object find(Class<?> clazz, String id) {
        Object obj = null;
        try {
            startOperation();
            obj = session.load(clazz, id);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
        	HibernateUtil.close(session);
        }
        return obj;
    }

    @SuppressWarnings("unchecked")
	protected List<Object> getAll(String namedQuery) {
        List<Object> objects = null;
        
        try {
            startOperation();
            Query query = session.getNamedQuery(namedQuery);
            objects = query.list();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
        	HibernateUtil.close(session);
        }
        return objects;
    }
    
    @SuppressWarnings("unchecked")
	protected List<Object> getByCriteria(List<Criterion> criteriones, Class clazz) {
    	List<Object> objects = null;
        
        try {
            startOperation();
            
            Criteria criteria = session.createCriteria(clazz);
    		for (Criterion criterion : criteriones) {
    			criteria.add(criterion);
    		}
    		
            objects = criteria.list();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
        	HibernateUtil.close(session);
        }
        return objects;
	}

    protected void handleException(HibernateException e)  {
    	HibernateUtil.rollback(tx);
        e.printStackTrace();
    }

    protected void startOperation() throws HibernateException {
        session = HibernateUtil.openSession();
        tx = session.beginTransaction();
    }
}
