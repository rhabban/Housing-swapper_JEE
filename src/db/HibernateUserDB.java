package com.devoir;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.IOException;
import java.io.Serializable;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.util.Set;

public class HibernateUserDB extends HttpServlet {
    // Gestion des sessions Hibernate =========================================================

    private SessionFactory sessionFactory;

    public HibernateUserDB (SessionFactory sessionFactory) throws ServletException {
        this.sessionFactory = sessionFactory;
    }

    public void destroy () {
        if (this.sessionFactory!=null) {
            this.sessionFactory.close();
        }
    }

    // Actions ================================================================================

    protected void create(UserModel user) throws ServletException {
        Session hibernateSession=sessionFactory.openSession();
        Transaction transaction=null;
        try {
            transaction=hibernateSession.beginTransaction();
            // Makes the instance persistent
            hibernateSession.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction!=null) {
                transaction.rollback();
            }
            throw new ServletException(e);
        } finally {
            hibernateSession.close();
        }
    }

    public void associateHousing(int housing_id, UserModel user){
        Session hibernateSession=this.sessionFactory.openSession();
        UserModel persistent_user = (UserModel)hibernateSession.merge(user);

        Set<Integer> associatedHousings = persistent_user.getHousings();
        associatedHousings.add(housing_id);

        hibernateSession.flush();
        hibernateSession.close();
    }

    public UserModel login (String mail, String password) {
        Session hibernateSession=this.sessionFactory.openSession();
        Transaction transaction=null;
        transaction=hibernateSession.beginTransaction();
        Query query=hibernateSession.createQuery("from UserModel where mail = :mail and password = :password");

        query.setParameter("mail", mail);
        query.setParameter("password", password);

        UserModel user = (UserModel) query.uniqueResult();
        hibernateSession.close();
        return user;
    }

    public ArrayList<UserModel> retrieveAll () {
        Session hibernateSession=this.sessionFactory.openSession();
        Transaction transaction=null;
        transaction=hibernateSession.beginTransaction();
        // Retrieves all products and stores them in list allProducts
        Query query=hibernateSession.createQuery("from UserModel");
        ArrayList<UserModel> allUsers= new ArrayList<>();
        allUsers = (ArrayList<UserModel>)query.list();
        for (Object user: allUsers) {
            Hibernate.initialize(((UserModel)user).getHousings());
        }
        hibernateSession.close();
        return allUsers;
    }

    public UserModel retrieve(String mail){
        Session hibernateSession=this.sessionFactory.openSession();
        Transaction transaction=null;
        transaction=hibernateSession.beginTransaction();
        Query query=hibernateSession.createQuery("from UserModel where mail = :mail");

        query.setParameter("mail", mail);

        UserModel user = (UserModel) query.uniqueResult();
        hibernateSession.close();
        return user;
    }

    /*
    // Non utilisee, pour l'exemple seulement (a utiliser en initialisation, ne modifie pas la session HTTP)
    public void addProducts () throws ServletException {
    Session session = sessionFactory.openSession();
    Transaction transaction=null;
    try {
    transaction=session.beginTransaction();
    session.save(new AssociableProduct("Carottes",1.5f,2f));
    AssociableProduct steak=new AssociableProduct("Steak",15f,0.5f);
    AssociableProduct sel=new AssociableProduct("Sel",1f,1f);
    steak.associateProduct(sel);
    session.save(sel);
    session.save(steak);
    AssociableProduct moutarde=new AssociableProduct("Moutarde",5f,0.2f);
    session.save(moutarde);
    steak.associateProduct(moutarde);
    transaction.commit();
    } catch (Exception e) {
    if (transaction!=null) {
    transaction.rollback();
    }
    throw new ServletException(e);
    } finally {
    session.close();
    }
    }
    */

}
