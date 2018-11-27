package controller;

import controller.libs.DatabaseHelper;
import model.Teacher;

public class MainController implements MainControllerInterface {
    private Teacher loggedUser;

    public void login(String email, String password) throws LoginException {
        loggedUser = DatabaseHelper.login(email,password);
    }
}
