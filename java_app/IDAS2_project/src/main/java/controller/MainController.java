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

    public void addSubject(Subject subject) throws DatabaseAccesException, LoginException {
        try {
            checkLoging();
            DatabaseHelper.addSubject(subject, loggedUser.getEmail(), password);
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }
/*
    public void updateSubject(Subject subject) throws DatabaseAccesException, LoginException {

    }

    public void deleteSubject(Subject subject) throws DatabaseAccesException, LoginException {

    }
    */
}
