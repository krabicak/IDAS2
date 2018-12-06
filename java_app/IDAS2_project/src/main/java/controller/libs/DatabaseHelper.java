package controller.libs;

import model.*;
import org.hibernate.Session;


import javax.persistence.*;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
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

        try {
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
        } catch (Exception e) {
            throw new DatabaseException(e);
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
            Session session = (Session) em.getDelegate();
            session.doWork(connection -> {
                CallableStatement stmt = connection.prepareCall("{ call add_teacher(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13) }");
                stmt.setString(1, email);
                stmt.setString(2, password);
                stmt.setString(3, newTeacher.getJmeno());
                stmt.setString(4, newTeacher.getPrijmeni());
                if (newTeacher.getTitulPred() == null) {
                    stmt.setNull(5, Types.VARCHAR);
                } else stmt.setString(5, newTeacher.getTitulPred());
                if (newTeacher.getTitulZa() == null) {
                    stmt.setNull(6, Types.VARCHAR);
                } else stmt.setString(6, newTeacher.getTitulZa());
                if (newTeacher.getTelefon() == null) {
                    stmt.setNull(7, Types.VARCHAR);
                } else stmt.setString(7, newTeacher.getTelefon());
                if (newTeacher.getMobil() == null) {
                    stmt.setNull(8, Types.VARCHAR);
                } else stmt.setString(8, newTeacher.getMobil());
                stmt.setString(9, newTeacher.getEmail());
                if (newTeacher.getPracoviste() == null) {
                    stmt.setNull(10, Types.NUMERIC);
                } else stmt.setString(10, newTeacher.getPracoviste().getId());
                stmt.setString(11, newTeacher.getHeslo());
                stmt.setString(12, newTeacher.getRole().getZkratka());
                stmt.setString(13, newTeacher.getUvazek().getTyp());
                stmt.execute();
                stmt.close();
            });
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static void deleteTeacher(Teacher teacher, String email, String password) throws DatabaseException {
        try {
            Query query = em.createNamedQuery("delete_teacher");
            query.setParameter(1, email);
            query.setParameter(2, password);
            query.setParameter(3, teacher.getId());
            query.executeUpdate();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static List<Obligation> getAllObligations() throws DatabaseException {
        try {
            Query query = em.createNamedQuery("delete_teacher");
            return query.getResultList();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static List<Role> getAllRoles() throws DatabaseException {
        try {
            Query query = em.createNamedQuery("get_all_role");
            return query.getResultList();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }
}
