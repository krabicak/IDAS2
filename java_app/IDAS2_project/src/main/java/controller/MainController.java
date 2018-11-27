package controller;

import controller.libs.DatabaseHelper;
import model.Teacher;

import java.util.List;

public class MainController implements MainControllerInterface {
    private Teacher loggedUser;
    private String email;
    private String password;

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
}
