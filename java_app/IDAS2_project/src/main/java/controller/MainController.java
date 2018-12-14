package controller;

import controller.libs.DatabaseHelper;
import model.*;

import javax.persistence.Persistence;
import java.util.List;

public class MainController implements MainControllerInterface {
    private Teacher loggedUser;
    private String password;

    private void checkLoging() throws DatabaseAccesException, LoginException {
        if (!isUserLogged()) throw new LoginException("Uživatel není přihlášen");
        if (!isUserAdmin()) throw new DatabaseAccesException("Přihlášený uživatel není admin");
    }

    public MainController() {
        DatabaseHelper.emf = Persistence.createEntityManagerFactory("IDAS2PU");
        DatabaseHelper.em = DatabaseHelper.emf.createEntityManager();
    }

    public void login(String email, String password) throws LoginException {
        try {
            loggedUser = DatabaseHelper.login(email, password);
        } catch (DatabaseHelper.DatabaseException e) {
            throw new LoginException(e.getMessage());
        }
        this.password = password;
    }

    public boolean isUserLogged() {
        return loggedUser != null;
    }

    public boolean isUserAdmin() {
        if (!isUserLogged()) return false;
        return loggedUser.getRole().getZkratka().equals("admin");
    }

    public List<Teacher> getAllTeachers() throws DatabaseAccesException {
        try {
            return DatabaseHelper.getAllTeachers();
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }

    public List<Workplace> getAllWorkplaces() throws DatabaseAccesException {
        try {
            return DatabaseHelper.getAllWorkplaces();
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }

    public List<Subject> getAllSubjects() throws DatabaseAccesException {
        try {
            return DatabaseHelper.getAllSubjects();
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }

    public List<FieldOfStudy> getAllFieldsOfStudy() throws DatabaseAccesException {
        try {
            return DatabaseHelper.getAllFieldsOfStudy();
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }

    public List<LearningAction> getAllLearningActions() throws DatabaseAccesException {
        try {
            return DatabaseHelper.getAllLearningActions();
        } catch (Exception e) {
            throw new DatabaseAccesException(e);
        }
    }

    public void addTeacher(Teacher newTeacher) throws DatabaseAccesException, LoginException {
        try {
            checkLoging();
            DatabaseHelper.addTeacher(newTeacher, loggedUser.getEmail(), password);
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }

    public Teacher getLoggedUser() {
        return loggedUser;
    }

    public void deleteTeacher(Teacher teacher) throws DatabaseAccesException, LoginException {
        try {
            checkLoging();
            DatabaseHelper.deleteTeacher(teacher, loggedUser.getEmail(), password);
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }

    public List<Obligation> getAllObligations() throws DatabaseAccesException {
        try {
            return DatabaseHelper.getAllObligations();
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }

    public List<Role> getAllRoles() throws DatabaseAccesException {
        try {
            return DatabaseHelper.getAllRoles();
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }

    public void logOut() {
        loggedUser = null;
        password = null;
    }

    public void updateTeacher(Teacher teacher) throws DatabaseAccesException, LoginException {
        try {
            checkLoging();
            DatabaseHelper.updateTeacher(teacher, loggedUser.getEmail(), password);
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }

    public void addWorkplace(Workplace workplace) throws LoginException, DatabaseAccesException {
        try {
            checkLoging();
            DatabaseHelper.addWorkplace(workplace, loggedUser.getEmail(), password);
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }

    public void updateWorkplace(Workplace workplace) throws LoginException, DatabaseAccesException {
        try {
            checkLoging();
            DatabaseHelper.updateWorkplace(workplace, loggedUser.getEmail(), password);
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }

    public void deleteWorkplace(Workplace workplace) throws LoginException, DatabaseAccesException {
        try {
            checkLoging();
            DatabaseHelper.deleteWorkplace(workplace, loggedUser.getEmail(), password);
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }

    public List<Faculty> getAllFaculties() throws DatabaseAccesException {
        try {
            return DatabaseHelper.getAllFaculties();
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }

    public List<Room> getAllRooms() throws DatabaseAccesException {
        try {
            return DatabaseHelper.getAllRooms();
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }

    public List<Day> getAllDays() throws DatabaseAccesException {
        try {
            return DatabaseHelper.getAllDays();
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }

    public List<MethodOfLearning> getAllMethodsOfLearning() throws DatabaseAccesException {
        try {
            return DatabaseHelper.getAllMethodsOfLearning();
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }

    public void addLearningAction(LearningAction learningAction) throws DatabaseAccesException, LoginException {
        try {
            if (!learningAction.getVyucujici().getId().equals(loggedUser.getId()))
                checkLoging();
            DatabaseHelper.addLearningAction(learningAction, loggedUser.getEmail(), password);
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }

    public void updateLearningAction(LearningAction learningAction) throws DatabaseAccesException, LoginException {
        try {
            if (!learningAction.getVyucujici().getId().equals(loggedUser.getId()))
                checkLoging();
            DatabaseHelper.updateLearningAction(learningAction, loggedUser.getEmail(), password);
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }

    public void deleteLearningAction(LearningAction learningAction) throws DatabaseAccesException, LoginException {
        try {
            if (!learningAction.getVyucujici().getId().equals(loggedUser.getId()))
                checkLoging();
            DatabaseHelper.deleteLearningAction(learningAction, loggedUser.getEmail(), password);
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }

    public void addSubject(Subject subject) throws DatabaseAccesException, LoginException {
        try {
            checkLoging();
            DatabaseHelper.addSubject(subject, loggedUser.getEmail(), password);
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }

    public void updateSubject(Subject subject) throws DatabaseAccesException, LoginException {
        try {
            checkLoging();
            DatabaseHelper.updateSubject(subject, loggedUser.getEmail(), password);
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }

    public void deleteSubject(Subject subject) throws DatabaseAccesException, LoginException {
        try {
            checkLoging();
            DatabaseHelper.deleteSubject(subject, loggedUser.getEmail(), password);
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }

    public List<Semester> getAllSemesters() throws DatabaseAccesException {
        try {
            return DatabaseHelper.getAllSemesters();
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }

    public List<CategoryOfSubject> getAllCategoriesofSubjects() throws DatabaseAccesException {
        try {
            return DatabaseHelper.getAllCategoriesofSubjects();
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }

    public List<ConclusionOfSubject> getAllConclusionsOfSubjects() throws DatabaseAccesException {
        try {
            return DatabaseHelper.getAllConclusionsOfSubjects();
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }

    public List<RecommendedYear> getAllRecommendedYears() throws DatabaseAccesException {
        try {
            return DatabaseHelper.getAllRecommendedYears();
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }

    public List<FormOfStudy> getAllFormsOfStudy() throws DatabaseAccesException {
        try {
            return DatabaseHelper.getAllFormsOfStudy();
        } catch (Exception e) {
            throw new DatabaseAccesException(e);
        }
    }

    public void addFieldOfStudy(FieldOfStudy fieldOfStudy) throws DatabaseAccesException {
        try {
            checkLoging();
            DatabaseHelper.addFieldOfStudy(fieldOfStudy, loggedUser.getEmail(), password);
        } catch (Exception e) {
            throw new DatabaseAccesException(e);
        }
    }

    public void updateFieldOfStudy(FieldOfStudy fieldOfStudy) throws DatabaseAccesException {
        try {
            checkLoging();
            DatabaseHelper.updateFieldOfStudy(fieldOfStudy, loggedUser.getEmail(), password);
        } catch (Exception e) {
            throw new DatabaseAccesException(e);
        }
    }

    public void deleteFieldOfStudy(FieldOfStudy fieldOfStudy) throws DatabaseAccesException {
        try {
            checkLoging();
            DatabaseHelper.deleteFieldOfStudy(fieldOfStudy, loggedUser.getEmail(), password);
        } catch (Exception e) {
            throw new DatabaseAccesException(e);
        }
    }

    public Photo getPhotoByTeacher(Teacher teacher) throws DatabaseAccesException {
        try {
            return DatabaseHelper.getPhotoByTeacher(teacher);
        } catch (Exception e) {
            throw new DatabaseAccesException(e);
        }
    }

    public void addPhoto(Photo photo) throws DatabaseAccesException {
        try {
            checkLoging();
            DatabaseHelper.addPhoto(photo, loggedUser.getEmail(), password);
        } catch (Exception e) {
            throw new DatabaseAccesException(e);
        }
    }

    public void updatePhoto(Photo photo) throws DatabaseAccesException {
        try {
            checkLoging();
            DatabaseHelper.updatePhoto(photo, loggedUser.getEmail(), password);
        } catch (Exception e) {
            throw new DatabaseAccesException(e);
        }
    }

    public void deletePhoto(Photo photo) throws DatabaseAccesException {
        try {
            checkLoging();
            DatabaseHelper.deletePhoto(photo, loggedUser.getEmail(), password);
        } catch (Exception e) {
            throw new DatabaseAccesException(e);
        }
    }

    public List<LearningAction> getLearningActionsByTeacher(Teacher teacher) throws DatabaseAccesException {
        try {
            return DatabaseHelper.getLearningActionsByTeacher(teacher);
        } catch (Exception e) {
            throw new DatabaseAccesException(e);
        }
    }

    public List<StudyPlan> getAllStudyPlans() throws DatabaseAccesException {
        try {
            return DatabaseHelper.getAllStudyPlans();
        } catch (Exception e) {
            throw new DatabaseAccesException(e);
        }
    }

    public List<StudyPlan> getStudyPlansByFieldOfStudy(FieldOfStudy fieldOfStudy) throws DatabaseAccesException {
        try {
            return DatabaseHelper.getStudyPlansByFieldOfStudy(fieldOfStudy);
        } catch (Exception e) {
            throw new DatabaseAccesException(e);
        }
    }

    public void addStudyPlan(StudyPlan studyPlan) throws DatabaseAccesException {
        try {
            checkLoging();
            DatabaseHelper.addStudyPlan(studyPlan, loggedUser.getEmail(), password);
        } catch (Exception e) {
            throw new DatabaseAccesException(e);
        }
    }

    public void deleteStudyPlan(StudyPlan studyPlan) throws DatabaseAccesException {
        try {
            checkLoging();
            DatabaseHelper.deleteStudyPlan(studyPlan, loggedUser.getEmail(), password);
        } catch (Exception e) {
            throw new DatabaseAccesException(e);
        }
    }

    public void addRoom(Room room) throws DatabaseAccesException, LoginException {
        try {
            checkLoging();
            DatabaseHelper.addRoom(room, loggedUser.getEmail(), password);
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }

    public void updateRoom(Room room) throws DatabaseAccesException, LoginException {
        try {
            checkLoging();
            DatabaseHelper.updateRoom(room, loggedUser.getEmail(), password);
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }

    public void deleteRoom(Room room) throws DatabaseAccesException, LoginException {
        try {
            checkLoging();
            DatabaseHelper.deleteRoom(room, loggedUser.getEmail(), password);
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }

    public List<LearningAction> getAllLearningActionsByRoom(Room room) throws DatabaseAccesException {
        try {
            return DatabaseHelper.getAllLearningActionsByRoom(room);
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }
}
