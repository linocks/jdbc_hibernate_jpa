package com.linock.database;

import com.linock.database.model.Role;
import com.linock.database.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Map;

public class Jpa {
    public static void main(String[] args) {
        System.out.println("hello jpa");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em.persist(new Role("USER"));
        em.persist(new Role("ADMIN"));
        List<Role> r = em.createQuery("select r from Role r ").getResultList();

        System.out.println("role "+r);

        User u = new User("enock", "boadi-ansah");
        em.persist(u);

        u.setUserRole(r);
        em.getTransaction().commit();
    }
}
