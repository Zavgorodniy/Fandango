package com.zavgorodniy.dao.impl;

import com.zavgorodniy.HibernateUtils;
import com.zavgorodniy.dao.SheduleDAO;
import com.zavgorodniy.entity.Shedule;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class SheduleDAOImpl implements SheduleDAO {

    @Override
    public Collection getMoviesByCinemaId(Long id) throws SQLException {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        List<Shedule> elementList = new ArrayList<Shedule>();

        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Shedule.class).add(Restrictions.eq("cinemaId", id));
            elementList = criteria.list();
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
    public Collection getCinemasByMovieId(Long id) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        List<Shedule> elementList = new ArrayList<Shedule>();

        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Shedule.class).add(Restrictions.eq("movieId", id));
            elementList = criteria.list();
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
    public Collection getSessionsStart(Long movieId, Long cinemaId) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        List<Shedule> elementList = new ArrayList<Shedule>();

        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Shedule.class);
            criteria.add(Restrictions.eq("movieId", movieId));
            criteria.add(Restrictions.eq("cinemaId", cinemaId));
            elementList = criteria.list();
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
    public Long addElement(Shedule element) throws SQLException {

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
    public void updateElement(Shedule element) throws SQLException {

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
    public Shedule getElementById(Long id) throws SQLException {

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        Shedule element = null;

        try {
            tx = session.beginTransaction();
            element = (Shedule) session.get(Shedule.class, id);
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
        List<Shedule> elementList = new ArrayList<Shedule>();

        try {
            tx = session.beginTransaction();
            elementList = session.createCriteria(Shedule.class).list();
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
    public void deleteElement(Shedule element) throws SQLException {

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
