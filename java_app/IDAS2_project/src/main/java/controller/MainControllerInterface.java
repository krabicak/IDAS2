package controller;

import model.FieldOfStudy;
import model.Subject;
import model.Teacher;
import model.Workplace;

import java.util.List;

public interface MainControllerInterface {

    /**
     * vystavuje vyjimku pri neuspesnem prihlaseni
     *
     * @param email
     * @param password
     * @throws LoginException - při neúspěšném přihlášení
     */
    void login(String email, String password) throws LoginException;

    /**
     * @return vraci seznam všech vyučujících
     * @throws DatabaseAccesException - při neúspěšném připojení
     */
    List<Teacher> getAllTeachers() throws DatabaseAccesException;

    /**
     * @return vraci seznam všech pracovišť
     * @throws DatabaseAccesException - při neúspěšném připojení
     */
    List<Workplace> getAllWorkplaces() throws DatabaseAccesException;

    /**
     * @return vrací seznam všech předmětů
     * @throws DatabaseAccesException
     */
    List<Subject> getAllSubjects() throws DatabaseAccesException;

    /**
     * @return vrací seznam všech studijních oborů
     * @throws DatabaseAccesException
     */
    List<FieldOfStudy> getAllFieldsOfStudy() throws DatabaseAccesException;

    class LoginException extends Exception {

        public LoginException(Exception e) {
            super(e);
        }
    }

    class DatabaseAccesException extends Exception {

        public DatabaseAccesException(Exception e) {
            super(e);
        }
    }
}
