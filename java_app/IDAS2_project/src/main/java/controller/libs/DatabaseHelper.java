package controller.libs;

import model.*;
import org.hibernate.Session;


import javax.persistence.*;
import java.sql.CallableStatement;
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
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static void addTeacher(Teacher newTeacher, String email, String password) throws DatabaseException {
        try {
            em.getTransaction().begin();
            Session session = (Session) em.getDelegate();
            session.doWork(connection -> {
                CallableStatement stmt = connection.prepareCall("{ call add_teacher(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13) }");
                stmt.setString(1,email);
                stmt.setString(2,password);
                stmt.setString(3,newTeacher.getJmeno());
                stmt.setString(4,newTeacher.getPrijmeni());
                stmt.execute();
                stmt.close();
            });
/*newTeacher.getTitulPred());
            newTeacher.getTitulZa());
            newTeacher.getTelefon());
            newTeacher.getMobil());
            newTeacher.getEmail());
            newTeacher.getPracoviste().getId());
            newTeacher.getHeslo());
            newTeacher.getRole().getZkratka());
            newTeacher.getUvazek().getTyp());*/
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DatabaseException(e);
        }
    }
}
