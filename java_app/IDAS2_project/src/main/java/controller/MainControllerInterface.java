package controller;

public interface MainControllerInterface {

    /**
     * vystavuje vyjimku pri neuspesnem prihlaseni
     * @param email
     * @param password
     * @throws Exception
     */
    void login(String email, String password) throws LoginException;

    class LoginException extends Exception{
        public LoginException() {
            super();
        }

        public LoginException(String message) {
            super(message);
        }
    }
}
