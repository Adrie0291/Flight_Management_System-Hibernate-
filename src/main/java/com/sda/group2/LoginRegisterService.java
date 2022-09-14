package com.sda.group2;

import com.sda.group2.hibernate.HibernateUtil;

import com.sda.group2.hibernate.hql.users.Account;
import com.sda.group2.hibernate.hql.users.Admin;
import com.sda.group2.hibernate.hql.users.User;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.List;

public class LoginRegisterService {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final EntityManager entityManager = sessionFactory.createEntityManager();

    public Account login(String email, String password) {
        Query query = entityManager.createQuery("FROM Account a WHERE a.email = :email AND a.password = :password")
                .setParameter("email", email)
                .setParameter("password", password);
        List<Account> accounts = query.getResultList();
        if (accounts.isEmpty())
            // Account with given email and password do not exist.
            return null;
        return accounts.get(0);
    }

    public void registerNewUser(String firstName, String lastName, String email, String password) {
        User user = new User(email, password, firstName, lastName);

        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    public void createNewAdminAccount(Admin admin) {
        entityManager.getTransaction().begin();
        entityManager.persist(admin);
        entityManager.getTransaction().commit();
    }
}