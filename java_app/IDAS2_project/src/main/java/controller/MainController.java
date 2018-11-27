package controller;

import controller.libs.DatabaseHelper;
import model.Teacher;
import model.Workplace;

import javax.persistence.Persistence;
import java.util.List;

public class MainController implements MainControllerInterface {
    private Teacher loggedUser;
    private String email;
    private String password;

    public MainController() {
        DatabaseHelper.emf = Persistence.createEntityManagerFactory("IDAS2PU");
        DatabaseHelper.em = DatabaseHelper.emf.createEntityManager();
    }

    public void login(String email, String password) throws LoginException {
        try {
            loggedUser = DatabaseHelper.login(email,password);
        } catch (DatabaseHelper.DatabaseException e) {
            throw new LoginException(e);
        }
        this.email = email;
        this.password = password;
    }

    public List<Teacher> getAllTeachers() throws DatabaseAccesException{
        try {
            return DatabaseHelper.getAllTeachers();
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }

    public List<Workplace> getAllWorkplaces() throws DatabaseAccesException{
        try {
            return DatabaseHelper.getAllWorkplaces();
        } catch (DatabaseHelper.DatabaseException e) {
            throw new DatabaseAccesException(e);
        }
    }
}
