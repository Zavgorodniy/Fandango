package com.zavgorodniy.dao.impl;

import com.zavgorodniy.HibernateUtils;
import com.zavgorodniy.dao.MovieDAO;
import com.zavgorodniy.entity.Movie;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.*;

@Repository
public class MovieDAOImpl implements MovieDAO {

    @Override
    public Collection toSearch(String req) throws SQLException {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        Set<Movie> elementList = new HashSet<Movie>();

        try {
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(Movie.class);

            Criterion rest1= Restrictions.ilike("name", req);
            Criterion rest2= Restrictions.ilike("description", req);

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
    public Long addElement(Movie element) throws SQLException {

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
    public void updateElement(Movie element) throws SQLException {

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
    public Movie getElementById(Long id) throws SQLException {

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        Movie element = null;

        try {
            tx = session.beginTransaction();
            element = (Movie) session.get(Movie.class, id);
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
        List<Movie> elementList = new ArrayList<Movie>();

        try {
            tx = session.beginTransaction();
            elementList = session.createCriteria(Movie.class).list();
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
    public void deleteElement(Movie element) throws SQLException {

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
