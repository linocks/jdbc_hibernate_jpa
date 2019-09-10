package com.linocks.database;

import com.linocks.database.model.Role;
import com.linocks.database.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class HibernateJavaConfig {

    public static void main(String[] args) {
        System.out.println("Hibernate example with java based configuration");

        Configuration config = new Configuration()
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Role.class)
                .setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver")
                .setProperty(Environment.URL, "jdbc:mysql://localhost:3306/skyte?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC")
                .setProperty(Environment.USER, "root")
                .setProperty(Environment.HBM2DDL_AUTO, "create-drop")
                .setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();


        SessionFactory sf = config.buildSessionFactory(serviceRegistry);
        try (Session session = sf.openSession()) {
            session.beginTransaction();

            session.save(new Role("USER"));
            session.save(new Role("ADMIN"));
            List<Role> r = session.createQuery("from Role", Role.class).list();

            System.out.println("role " + r);

            User u = new User("enock", "boadi-ansah");
            session.save(u);

            u.setUserRole(r);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {

        }


    }
}
