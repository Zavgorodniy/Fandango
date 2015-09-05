package com.zavgorodniy.dao.impl;

import com.zavgorodniy.HibernateUtils;
import com.zavgorodniy.dao.CinemaDAO;
import com.zavgorodniy.entity.Cinema;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.*;

@Repository
public class CinemaDAOImpl implements CinemaDAO {

//    @Resource(name="sessionFactory")
//    private SessionFactory sessionFactory;

    public Collection toSearch(String req) throws SQLException {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        Set<Cinema> elementList = new HashSet<Cinema>();

        try {
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(Cinema.class);

            Criterion rest1= Restrictions.ilike("name", req);
            Criterion rest2= Restrictions.ilike("location", req);

            criteria.add(Restrictions.or(rest1, rest2));

            elementList.addAll(criteria.list());

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
    public Long addElement(Cinema element) throws SQLException {

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
    public void updateElement(Cinema element) throws SQLException {

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
    public Cinema getElementById(Long id) throws SQLException {

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        Cinema element = null;

        try {
            tx = session.beginTransaction();
            element = (Cinema) session.get(Cinema.class, id);
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
        List<Cinema> elementList = new ArrayList<Cinema>();

        try {
            tx = session.beginTransaction();
            elementList = session.createCriteria(Cinema.class).list();
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
    public void deleteElement(Cinema element) throws SQLException {

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
