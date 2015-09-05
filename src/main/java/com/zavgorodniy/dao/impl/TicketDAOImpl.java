package com.zavgorodniy.dao.impl;

import com.zavgorodniy.HibernateUtils;
import com.zavgorodniy.dao.TicketDAO;
import com.zavgorodniy.entity.Ticket;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class TicketDAOImpl implements TicketDAO {

    @Override
    public Long addElement(Ticket element) throws SQLException {

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        Long id = null;

        try {
            tx = session.beginTransaction();
            id = (Long) session.save(element);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return id;
    }

    @Override
    public void updateElement(Ticket element) throws SQLException {

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(element);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
//    @SuppressWarnings("unchecked")
    public Ticket getElementById(Long id) throws SQLException {

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        Ticket element = null;

        try {
            tx = session.beginTransaction();
            element = (Ticket) session.get(Ticket.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return element;
    }

    @Override
//    @SuppressWarnings("unchecked")
    public Collection getAllElements() throws SQLException {

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        List<Ticket> elementList = new ArrayList<Ticket>();

        try {
            tx = session.beginTransaction();
            elementList = session.createCriteria(Ticket.class).list();
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return elementList;
    }

    @Override
    public void deleteElement(Ticket element) throws SQLException {

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(element);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
}
