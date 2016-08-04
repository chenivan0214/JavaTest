package com.db.implement;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.db.dao.BasicAddressesDao;
import com.db.model.BasicAddressesModel;
import com.utility.HibernateUtility;

public class BasicAddressesImp implements BasicAddressesDao {
    public BasicAddressesImp() {
    }
    
    @SuppressWarnings("unchecked")
    public List<BasicAddressesModel> getSection(String _regionCode) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        List<BasicAddressesModel> listBasicAddressesModel = null;
        
        try {
            transaction = session.beginTransaction();
            String sql = "select addr_code, addr_name from basic_addresses where addr_code like :addrCode "
                + " and length(addr_code) = 3";
            
            Query query = session
                          .createSQLQuery(sql)
                          .addEntity(BasicAddressesModel.class)
                          .setParameter("addrCode", _regionCode + "%");
            listBasicAddressesModel = query.list();
        }  catch (HibernateException e) {
            e.printStackTrace(); 
        } finally {
            transaction.commit();
            session.close();
        }
        
        return listBasicAddressesModel;
    }

    @SuppressWarnings("unchecked")
    public List<BasicAddressesModel> getStreet(String _sectionCode) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        List<BasicAddressesModel> listBasicAddressesModel = null;
        
        try {
            transaction = session.beginTransaction();
            String sql = "select addr_code, addr_name from basic_addresses where addr_code like :addrCode "
                + " and length(addr_code) = 6";
            
            Query query = session
                          .createSQLQuery(sql)
                          .addEntity(BasicAddressesModel.class)
                          .setParameter("addrCode", _sectionCode + "%");
            listBasicAddressesModel = query.list();
        }  catch (HibernateException e) {
            e.printStackTrace(); 
        } finally {
            transaction.commit();
            session.close();
        }
        
        return listBasicAddressesModel;
    }

}
