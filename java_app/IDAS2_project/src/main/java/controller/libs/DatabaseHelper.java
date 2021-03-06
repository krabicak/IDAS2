package controller.libs;

import model.*;
import org.hibernate.Session;


import javax.persistence.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
            throw new DatabaseException(e.getMessage());
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
            //Query query = em.createQuery("select u from Subject u left join fetch u.semestr");
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

    public static List<Obligation> getAllObligations() throws DatabaseException {
        try {
            Query query = em.createNamedQuery("get_all_obligations");
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

    public static List<Faculty> getAllFaculties() throws DatabaseException {
        try {
            Query query = em.createNamedQuery("get_all_faculties");
            return query.getResultList();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static List<Room> getAllRooms() throws DatabaseException {
        try {
            Query query = em.createNamedQuery("get_all_rooms");
            return query.getResultList();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static List<MethodOfLearning> getAllMethodsOfLearning() throws DatabaseException {
        try {
            Query query = em.createNamedQuery("get_all_methods_of_learning");
            return query.getResultList();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static List<Day> getAllDays() throws DatabaseException {
        try {
            Query query = em.createNamedQuery("get_all_days");
            return query.getResultList();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    private static CallableStatement setTeacher(CallableStatement stmt, Teacher newTeacher, String email, String password) throws SQLException {
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
        if (newTeacher.getHeslo() == null) {
            stmt.setNull(11, Types.VARCHAR);
        } else stmt.setString(11, newTeacher.getHeslo());
        stmt.setString(12, newTeacher.getRole().getZkratka());
        stmt.setString(13, newTeacher.getUvazek().getTyp());
        return stmt;
    }

    public static void addTeacher(Teacher newTeacher, String email, String password) throws DatabaseException {
        try {
            Session session = (Session) em.getDelegate();
            session.doWork(connection -> {
                CallableStatement stmt = connection.prepareCall("{ call add_teacher(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13) }");
                stmt = setTeacher(stmt, newTeacher, email, password);
                stmt.execute();
                stmt.close();
            });
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static void updateTeacher(Teacher teacher, String email, String password) throws DatabaseException {
        try {
            Session session = (Session) em.getDelegate();
            session.doWork(connection -> {
                CallableStatement stmt = connection.prepareCall("{ call update_teacher(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14) }");
                stmt = setTeacher(stmt, teacher, email, password);
                stmt.setString(14, teacher.getId());
                stmt.execute();
                stmt.close();
            });
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static void deleteTeacher(Teacher teacher, String email, String password) throws DatabaseException {
        try {
            Session session = (Session) em.getDelegate();
            session.doWork(connection -> {
                CallableStatement stm = connection.prepareCall("{ call delete_teacher ( :1 , :2 , :3 ) }");
                stm.setString(1, email);
                stm.setString(2, password);
                stm.setString(3, teacher.getId());
                stm.execute();
                stm.close();
            });
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    private static CallableStatement setWorkplace(CallableStatement stmt, Workplace workplace, String email, String password) throws SQLException {
        stmt.setString(1, email);
        stmt.setString(2, password);
        stmt.setString(3, workplace.getNazev());
        stmt.setString(4, workplace.getZkratka());
        stmt.setString(5, workplace.getFakulta().getId());
        return stmt;
    }

    public static void addWorkplace(Workplace workplace, String email, String password) throws DatabaseException {
        try {
            Session session = (Session) em.getDelegate();
            session.doWork(connection -> {
                CallableStatement stmt = connection.prepareCall("{ call add_workplace(:1,:2,:3,:4,:5) }");
                stmt = setWorkplace(stmt, workplace, email, password);
                stmt.execute();
                stmt.close();
            });
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static void updateWorkplace(Workplace workplace, String email, String password) throws DatabaseException {
        try {
            Session session = (Session) em.getDelegate();
            session.doWork(connection -> {
                CallableStatement stmt = connection.prepareCall("{ call update_workplace(:1,:2,:3,:4,:5,:6) }");
                stmt = setWorkplace(stmt, workplace, email, password);
                stmt.setString(6, workplace.getId());
                stmt.execute();
                stmt.close();
            });
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static void deleteWorkplace(Workplace workplace, String email, String password) throws DatabaseException {
        try {
            Session session = (Session) em.getDelegate();
            session.doWork(connection -> {
                CallableStatement stm = connection.prepareCall("{ call delete_workplace ( :1 , :2 , :3 ) }");
                stm.setString(1, email);
                stm.setString(2, password);
                stm.setString(3, workplace.getId());
                stm.execute();
                stm.close();
            });
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    private static CallableStatement setLearningAction(CallableStatement stmt, LearningAction learningAction, String email, String password) throws SQLException {
        stmt.setString(1, email);
        stmt.setString(2, password);
        stmt.setString(3, learningAction.getKapacita());
        stmt.setString(4, learningAction.getZpusobVyuky().getZkrata());
        stmt.setString(5, learningAction.getVyucujici().getId());
        if (learningAction.getPocatek() == null) stmt.setNull(6, Types.VARCHAR);
        else stmt.setString(6, learningAction.getPocatek());
        if (learningAction.getKonec() == null) stmt.setNull(7, Types.VARCHAR);
        else stmt.setString(7, learningAction.getKonec());
        if (learningAction.getDen() == null) stmt.setNull(8, Types.VARCHAR);
        else stmt.setString(8, learningAction.getDen().getShortcut());
        stmt.setString(9, learningAction.getPredmet().getId());
        stmt.setString(10, learningAction.getUcebna().getId());
        return stmt;
    }

    public static void addLearningAction(LearningAction learningAction, String email, String password) throws DatabaseException {
        try {
            Session session = (Session) em.getDelegate();
            session.doWork(connection -> {
                CallableStatement stmt = connection.prepareCall("{ call add_learning_action(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10) }");
                stmt = setLearningAction(stmt, learningAction, email, password);
                stmt.execute();
                stmt.close();
            });
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static void updateLearningAction(LearningAction learningAction, String email, String password) throws DatabaseException {
        try {
            Session session = (Session) em.getDelegate();
            session.doWork(connection -> {
                CallableStatement stmt = connection.prepareCall("{ call update_learning_action(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11) }");
                stmt = setLearningAction(stmt, learningAction, email, password);
                stmt.setString(11, learningAction.getId());
                stmt.execute();
                stmt.close();
            });
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static void deleteLearningAction(LearningAction learningAction, String email, String password) throws DatabaseException {
        try {
            Session session = (Session) em.getDelegate();
            session.doWork(connection -> {
                CallableStatement stm = connection.prepareCall("{ call delete_learning_action ( :1 , :2 , :3 ) }");
                stm.setString(1, email);
                stm.setString(2, password);
                stm.setString(3, learningAction.getId());
                stm.execute();
                stm.close();
            });
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    private static CallableStatement setSubject(CallableStatement stmt, Subject subject, String email, String password) throws SQLException {
        stmt.setString(1, email);
        stmt.setString(2, password);
        stmt.setString(3, subject.getNazev());
        stmt.setString(4, subject.getZkratka());
        stmt.setString(5, subject.getRozsahHodin());
        stmt.setString(6, subject.getZpusobZakonceni().getZkratka());
        if (subject.getKategorie() == null) stmt.setNull(7, Types.VARCHAR);
        else stmt.setString(7, subject.getKategorie().getZkratka());
        if (subject.getDoporucenyRocnik() == null) stmt.setNull(8, Types.NUMERIC);
        else stmt.setString(8, subject.getDoporucenyRocnik().getCisloRocniku());
        stmt.setString(9, subject.getGarant().getId());
        if (subject.getSemestr().size() == 0) {
            stmt.setNull(10, Types.NUMERIC);
            stmt.setNull(11, Types.NUMERIC);
        } else if (subject.getSemestr().size() == 1) {
            if (subject.getSemestr().get(0).getId().equals("1")) {
                stmt.setString(10, "1");
                stmt.setNull(11, Types.NUMERIC);
            } else if (subject.getSemestr().get(0).getId().equals("2")) {
                stmt.setString(11, "1");
                stmt.setNull(10, Types.NUMERIC);
            }
        } else if (subject.getSemestr().size() == 2) {
            stmt.setString(10, "1");
            stmt.setString(11, "1");
        }
        return stmt;
    }

    public static void addSubject(Subject subject, String email, String password) throws DatabaseException {
        try {
            Session session = (Session) em.getDelegate();
            session.doWork(connection -> {
                CallableStatement stmt = connection.prepareCall("{ call add_subject(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11) }");
                stmt = setSubject(stmt, subject, email, password);
                stmt.execute();
                stmt.close();
            });
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static void updateSubject(Subject subject, String email, String password) throws DatabaseException {
        try {
            Session session = (Session) em.getDelegate();
            session.doWork(connection -> {
                CallableStatement stmt = connection.prepareCall("{ call update_subject(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12) }");
                stmt = setSubject(stmt, subject, email, password);
                stmt.setString(12, subject.getId());
                stmt.execute();
                stmt.close();
            });
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static void deleteSubject(Subject subject, String email, String password) throws DatabaseException {
        try {
            Session session = (Session) em.getDelegate();
            session.doWork(connection -> {
                CallableStatement stm = connection.prepareCall("{ call delete_subject ( :1 , :2 , :3 ) }");
                stm.setString(1, email);
                stm.setString(2, password);
                stm.setString(3, subject.getId());
                stm.execute();
                stm.close();
            });
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static List<Semester> getAllSemesters() throws DatabaseException {
        try {
            Query query = em.createNamedQuery("get_all_semesters");
            return query.getResultList();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static List<CategoryOfSubject> getAllCategoriesofSubjects() throws DatabaseException {
        try {
            Query query = em.createNamedQuery("get_all_categories_of_subject");
            return query.getResultList();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static List<ConclusionOfSubject> getAllConclusionsOfSubjects() throws DatabaseException {
        try {
            Query query = em.createNamedQuery("get_all_conclusions_of_subject");
            return query.getResultList();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static List<RecommendedYear> getAllRecommendedYears() throws DatabaseException {
        try {
            Query query = em.createNamedQuery("get_all_recommended_years");
            return query.getResultList();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static List<FormOfStudy> getAllFormsOfStudy() throws DatabaseException {
        try {
            Query query = em.createNamedQuery("get_all_forms_of_study");
            return query.getResultList();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    private static CallableStatement setFieldOfStudy(CallableStatement stmt, FieldOfStudy fieldOfStudy, String email, String password) throws SQLException {
        stmt.setString(1, email);
        stmt.setString(2, password);
        stmt.setString(3, fieldOfStudy.getPracoviste().getId());
        stmt.setString(4, fieldOfStudy.getNazev());
        stmt.setString(5, fieldOfStudy.getZkratka());
        if (fieldOfStudy.getForma().size() == 0) {
            stmt.setNull(6, Types.NUMERIC);
            stmt.setNull(7, Types.NUMERIC);
        } else if (fieldOfStudy.getForma().size() == 1) {
            if (fieldOfStudy.getForma().get(0).getId().equals("1")) {
                stmt.setString(6, "1");
                stmt.setNull(7, Types.NUMERIC);
            } else if (fieldOfStudy.getForma().get(0).getId().equals("2")) {
                stmt.setString(11, "1");
                stmt.setNull(10, Types.NUMERIC);
            }
        } else if (fieldOfStudy.getForma().size() == 2) {
            stmt.setString(6, "1");
            stmt.setString(7, "1");
        }
        return stmt;
    }

    public static void addFieldOfStudy(FieldOfStudy fieldOfStudy, String email, String password) throws DatabaseException {
        try {
            Session session = (Session) em.getDelegate();
            session.doWork(connection -> {
                CallableStatement stmt = connection.prepareCall("{ call add_field_of_study(:1,:2,:3,:4,:5,:6,:7) }");
                stmt = setFieldOfStudy(stmt, fieldOfStudy, email, password);
                stmt.execute();
                stmt.close();
            });
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static void updateFieldOfStudy(FieldOfStudy fieldOfStudy, String email, String password) throws DatabaseException {
        try {
            Session session = (Session) em.getDelegate();
            session.doWork(connection -> {
                CallableStatement stmt = connection.prepareCall("{ call update_field_of_study(:1,:2,:3,:4,:5,:6,:7,:8) }");
                stmt = setFieldOfStudy(stmt, fieldOfStudy, email, password);
                stmt.setString(8, fieldOfStudy.getId());
                stmt.execute();
                stmt.close();
            });
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static void deleteFieldOfStudy(FieldOfStudy fieldOfStudy, String email, String password) throws DatabaseException {
        try {
            Session session = (Session) em.getDelegate();
            session.doWork(connection -> {
                CallableStatement stm = connection.prepareCall("{ call delete_field_of_study ( :1 , :2 , :3 ) }");
                stm.setString(1, email);
                stm.setString(2, password);
                stm.setString(3, fieldOfStudy.getId());
                stm.execute();
                stm.close();
            });
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static Photo getPhotoByTeacher(Teacher teacher) throws DatabaseException {
        try {
            Query query = em.createNamedQuery("get_photo_by_id");
            query.setParameter(1, teacher.getId());
            List<Photo> list = query.getResultList();
            if (list.size() == 1) {
                return list.get(0);
            } else {
                throw new DatabaseException("Obrazek nenalezen");
            }
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    private static CallableStatement setPhoto(CallableStatement stmt, Photo photo, String email, String password) throws SQLException {
        stmt.setString(1, email);
        stmt.setString(2, password);
        stmt.setString(3, photo.getId());
        stmt.setString(4, photo.getInfo());
        InputStream in = new ByteArrayInputStream(photo.getObrazek());
        stmt.setBinaryStream(5, in, photo.getObrazek().length);
        return stmt;
    }

    public static void addPhoto(Photo photo, String email, String password) throws DatabaseException {
        try {
            Session session = (Session) em.getDelegate();
            session.doWork(connection -> {
                CallableStatement stmt = connection.prepareCall("{ call add_photo(:1,:2,:3,:4,:5) }");
                stmt = setPhoto(stmt, photo, email, password);
                stmt.execute();
                stmt.close();
            });
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static void updatePhoto(Photo photo, String email, String password) throws DatabaseException {
        try {
            Session session = (Session) em.getDelegate();
            session.doWork(connection -> {
                CallableStatement stmt = connection.prepareCall("{ call update_photo(:1,:2,:3,:4,:5) }");
                stmt = setPhoto(stmt, photo, email, password);
                stmt.execute();
                stmt.close();
            });
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static void deletePhoto(Photo photo, String email, String password) throws DatabaseException {
        try {
            Session session = (Session) em.getDelegate();
            session.doWork(connection -> {
                CallableStatement stm = connection.prepareCall("{ call delete_photo ( :1 , :2 , :3 ) }");
                stm.setString(1, email);
                stm.setString(2, password);
                stm.setString(3, photo.getId());
                stm.execute();
                stm.close();
            });
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static List<LearningAction> getLearningActionsByTeacher(Teacher teacher) throws DatabaseException {
        try {
            Query query = em.createNamedQuery("get_learning_actions_by_teacher");
            query.setParameter(1, teacher.getId());
            return query.getResultList();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static List<StudyPlan> getAllStudyPlans() throws DatabaseException {
        try {
            Query query = em.createNamedQuery("get_all_study_plans");
            return query.getResultList();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static List<StudyPlan> getStudyPlansByFieldOfStudy(FieldOfStudy fieldOfStudy) throws DatabaseException {
        try {
            Query query = em.createNamedQuery("get_all_study_plan_by_field_of_study");
            query.setParameter(1, fieldOfStudy.getId());
            return query.getResultList();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static void addStudyPlan(StudyPlan studyPlan, String email, String password) throws DatabaseException {
        try {
            Session session = (Session) em.getDelegate();
            session.doWork(connection -> {
                CallableStatement stm = connection.prepareCall("{ call add_study_plan ( :1 , :2 , :3, :4 ) }");
                stm.setString(1, email);
                stm.setString(2, password);
                stm.setString(3, studyPlan.getStudijniObor().getId());
                stm.setString(4, studyPlan.getPredmet().getId());
                stm.execute();
                stm.close();
            });
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static void deleteStudyPlan(StudyPlan studyPlan, String email, String password) throws DatabaseException {
        try {
            Session session = (Session) em.getDelegate();
            session.doWork(connection -> {
                CallableStatement stm = connection.prepareCall("{ call delete_study_plan ( :1 , :2 , :3, :4 ) }");
                stm.setString(1, email);
                stm.setString(2, password);
                stm.setString(3, studyPlan.getStudijniObor().getId());
                stm.setString(4, studyPlan.getPredmet().getId());
                stm.execute();
                stm.close();
            });
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }


    public static void addRoom(Room room, String email, String password) throws DatabaseException {
        try {
            Session session = (Session) em.getDelegate();
            session.doWork(connection -> {
                CallableStatement stmt = connection.prepareCall("{ call add_room(:1,:2,:3,:4) }");
                stmt = setRoom(stmt, room, email, password);
                stmt.execute();
                stmt.close();
            });
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static void updateRoom(Room room, String email, String password) throws DatabaseException {
        try {
            Session session = (Session) em.getDelegate();
            session.doWork(connection -> {
                CallableStatement stmt = connection.prepareCall("{ call UPDATE_ROOM(:1,:2,:3,:4,:5) }");
                stmt = setRoom(stmt, room, email, password);
                stmt.setString(5, room.getId());
                stmt.execute();
                stmt.close();
            });
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static void deleteRoom(Room room, String email, String password) throws DatabaseException {
        try {
            Session session = (Session) em.getDelegate();
            session.doWork(connection -> {
                CallableStatement stm = connection.prepareCall("{ call delete_room ( :1 , :2 , :3 ) }");
                stm.setString(1, email);
                stm.setString(2, password);
                stm.setString(3, room.getId());
                stm.execute();
                stm.close();
            });
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    private static CallableStatement setRoom(CallableStatement stmt, Room room, String email, String password) throws SQLException {
        stmt.setString(1, email);
        stmt.setString(2, password);
        stmt.setString(3, room.getPopis());
        stmt.setString(4, room.getOznaceni());
        return stmt;
    }

    public static List<LearningAction> getAllLearningActionsByRoom(Room room) throws DatabaseException {
        try {
            Query query = em.createNamedQuery("get_all_learning_actions_by_room");
            query.setParameter(1, room.getId());
            return query.getResultList();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public static void changePassword(Teacher teacher, String oldPassword, String newPassword) throws DatabaseException {
        try {
            Session session = (Session) em.getDelegate();
            session.doWork(connection -> {
                CallableStatement stm = connection.prepareCall("{ call change_password ( :1 , :2 , :3 ) }");
                stm.setString(1, teacher.getEmail());
                stm.setString(2, oldPassword);
                stm.setString(3, newPassword);
                stm.execute();
                stm.close();
            });
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }


}
