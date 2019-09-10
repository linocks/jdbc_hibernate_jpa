package com.linocks.database;

import com.linocks.database.config.Setup;
import com.linocks.database.model.Role;
import com.linocks.database.model.User;
import org.hibernate.Session;

import java.util.List;

public class Hibernate {

    public static void main(String[] args) {
        System.out.println("working");
        try (Session session = Setup.getSessionFactory().openSession()) {
            // start a transaction
            session.beginTransaction();

            session.save(new Role("USER"));
            session.save(new Role("ADMIN"));
            List<Role> r = session.createQuery("from Role", Role.class).list();

            System.out.println("role "+r);

            User u = new User("enock", "boadi-ansah");
            session.save(u);

            u.setUserRole(r);

            session.getTransaction().commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
