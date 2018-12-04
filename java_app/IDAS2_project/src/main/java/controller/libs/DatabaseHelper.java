package controller.libs;

import model.*;

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

        public DatabaseException(Exception e) {
            super(e);
        }
    }


    @PersistenceContext
    public static EntityManagerFactory emf;
    public static EntityManager em;

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
            Query query = em.createNamedQuery("get_all_teachers");
            return query.getResultList();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static List<Workplace> getAllWorkplaces() throws DatabaseException {

        try {
            Query query = em.createNamedQuery("get_all_workplaces");
            return query.getResultList();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static List<Subject> getAllSubjects() throws DatabaseException {
        try {
            Query query = em.createNamedQuery("get_all_subjects");
            return query.getResultList();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static List<FieldOfStudy> getAllFieldsOfStudy() throws DatabaseException {
        try {
            Query query = em.createNamedQuery("get_all_fields_of_study");
            return query.getResultList();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static List<LearningAction> getAllLearningActions() throws DatabaseException {
        try {
            Query query = em.createNamedQuery("get_all_learning_actions");
            return query.getResultList();
        }catch (Exception e){
            throw new DatabaseException(e);
        }
    }
}
