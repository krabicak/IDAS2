package controller;

import controller.libs.DatabaseHelper;
import model.*;

import javax.persistence.Persistence;
import java.util.List;

public class MainController implements MainControllerInterface {
    private Teacher loggedUser;
    private String password;

    public MainController() {
        DatabaseHelper.emf = Persistence.createEntityManagerFactory("IDAS2PU");
        DatabaseHelper.em = DatabaseHelper.emf.createEntityManager();
    }

    public void login(String email, String password) throws LoginException {
        try {
            loggedUser = DatabaseHelper.login(email, password);
        } catch (DatabaseHelper.DatabaseException e) {
            throw new LoginException(e);
        }
        this.password = password;
    }

    public boolean isUserLogged() {
        return loggedUser != null;
    }

    public boolean isUserAdmin(){
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
            if (!isUserLogged()) throw new LoginException("Uživatel není přihlášen");
            if (!isUserAdmin()) throw new DatabaseAccesException("Přihlášený uživatel není admin");
            DatabaseHelper.addTeacher(newTeacher,loggedUser.getEmail(),password);
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }

    public Teacher getLoggedUser(){
        return loggedUser;
    }
}
