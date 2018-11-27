package controller.libs;

import model.Teacher;

import javax.persistence.*;
import java.util.List;

public final class DatabaseHelper {

    public static class DatabaseException extends Exception {
        public DatabaseException() {
            super();
        }

        public DatabaseException(String message) {
            super(message);
        }
    }


    @PersistenceContext
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("IDAS2PU");
    private static EntityManager em = emf.createEntityManager();

    private DatabaseHelper() {

    }

    public static Teacher login(String email, String password) throws DatabaseException {

        Query query = em.createNamedQuery("login_user");
        query.setParameter(1, email);
        query.setParameter(2, password);
        System.out.printf(query.toString());

        List<Teacher> list = query.getResultList();
        if (list.size() == 1) {
            Teacher user = list.get(0);
            return user;
        } else {
            throw new DatabaseException("Invalid credentials");
        }
    }

    public static List<Teacher> getAllTeachers() throws DatabaseException {

        try {
            Query query = em.createQuery("from Teacher ");
            return query.getResultList();
        } catch (Exception e) {
            throw new DatabaseException("Cannot get data from database: " + e.getLocalizedMessage());
        }


    }
}
