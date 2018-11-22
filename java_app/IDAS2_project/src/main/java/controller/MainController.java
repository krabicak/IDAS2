package controller;

import controller.libs.DatabaseHelper;

public class MainController implements MainControllerInterface {

    public void login(String email, String password) throws LoginException {
        DatabaseHelper.login(email,password);
    }
}
