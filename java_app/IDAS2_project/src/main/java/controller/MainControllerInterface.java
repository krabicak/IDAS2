package controller;

import model.*;

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

    /**
     * @return vrací všechny rozvrhové akce
     * @throws DatabaseAccesException
     */
    List<LearningAction> getAllLearningActions() throws DatabaseAccesException;

    /**
     * @return pokud je uzivatel prihlasen true jinak false
     */
    boolean isUserLogged();

    /**
     * @param newTeacher
     * @throws DatabaseAccesException
     */
    void addTeacher(Teacher newTeacher) throws DatabaseAccesException, LoginException;

    /**
     * @return
     */
    Teacher getLoggedUser();

    /**
     * @param teacher
     * @throws DatabaseAccesException
     * @throws LoginException
     */
    void deleteTeacher(Teacher teacher) throws DatabaseAccesException, LoginException;

    /**
     * @return
     * @throws DatabaseAccesException
     */
    List<Obligation> getAllObligations() throws DatabaseAccesException;

    /**
     * @return
     * @throws DatabaseAccesException
     */
    List<Role> getAllRoles() throws DatabaseAccesException;

    /**
     *
     */
    void logOut();

    /**
     * @param teacher
     * @throws DatabaseAccesException
     * @throws LoginException
     */
    void updateTeacher(Teacher teacher) throws DatabaseAccesException, LoginException;

    class LoginException extends Exception {

        public LoginException(Exception e) {
            super(e);
        }

        public LoginException(String s) {
            super(s);
        }
    }

    class DatabaseAccesException extends Exception {

        public DatabaseAccesException(Exception e) {
            super(e);
        }

        public DatabaseAccesException(String s) {
            super(s);
        }
    }
}
