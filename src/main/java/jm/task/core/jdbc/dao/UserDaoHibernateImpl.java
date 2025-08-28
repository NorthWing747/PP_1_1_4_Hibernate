package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Transaction tx = null;
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50), " +
                "lastName VARCHAR(50), " +
                "age TINYINT)";
        try (Session session = Util.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

        @Override
        public void dropUsersTable () {
            Transaction tx = null;
            try (Session session = Util.getSessionFactory().openSession()) {
                tx = session.beginTransaction();
                session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
                tx.commit();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                e.printStackTrace();
            }
        }

        @Override
        public void saveUser (String name, String lastName,byte age){
            Transaction tx = null;
            try (Session session = Util.getSessionFactory().openSession()) {
                tx = session.beginTransaction();
                session.save(new User(name, lastName, age));
                tx.commit();
                System.out.printf("Пользователь %s %s добавлен%n", name, lastName);
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                e.printStackTrace();
            }
        }

        @Override
        public void removeUserById ( long id){
            Transaction tx = null;
            try (Session session = Util.getSessionFactory().openSession()) {
                tx = session.beginTransaction();
                User user = session.get(User.class, id);
                if (user != null) {
                    session.delete(user);
                }
                tx.commit();
            }catch (Exception e) {
                if (tx != null) tx.rollback();
                e.printStackTrace();
            }
        }

        @Override
        public List<User> getAllUsers () {
            List<User> users = new ArrayList<>();
            try (Session session = Util.getSessionFactory().openSession()) {
                users = session.createQuery("from User", User.class).list();
            }catch (Exception e){
                e.printStackTrace();
            }
            return users;
        }

        @Override
        public void cleanUsersTable () {
            Transaction tx = null;
            try (Session session = Util.getSessionFactory().openSession()) {
                tx = session.beginTransaction();
                Query query = session.createQuery("delete from User");
                query.executeUpdate();
                tx.commit();
            }catch (Exception e) {
                if (tx != null) tx.rollback();
                e.printStackTrace();
            }
        }
    }
