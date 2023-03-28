package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (SessionFactory factory = Util.getSessionFactory();
             Session session = factory.openSession()){

            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS user (id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(30), lastname VARCHAR (30), age TINYINT UNSIGNED)").executeUpdate();
            session.getTransaction().commit();

        }
    }

    @Override
    public void dropUsersTable() {
        try (SessionFactory factory = Util.getSessionFactory();
             Session session = factory.openSession()){

            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS user").executeUpdate();
            session.getTransaction().commit();

        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (SessionFactory factory = Util.getSessionFactory();
             Session session = factory.openSession()){

            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();

        }
    }

    @Override
    public void removeUserById(long id) {
        try (SessionFactory factory = Util.getSessionFactory();
             Session session = factory.openSession()){

            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();

        }
    }

    @Override
    public List<User> getAllUsers() {
        try (SessionFactory factory = Util.getSessionFactory();
             Session session = factory.openSession()){

            session.beginTransaction();
            List<User> users = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
            return users;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (SessionFactory factory = Util.getSessionFactory();
             Session session = factory.openSession()){

            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();

        }
    }
}
