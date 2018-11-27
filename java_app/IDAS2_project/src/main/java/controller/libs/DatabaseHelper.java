package controller.libs;

import controller.MainControllerInterface;
import model.Teacher;

import javax.persistence.*;
import java.util.List;

public final class DatabaseHelper {

    @PersistenceContext
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("IDAS2PU");
    private static EntityManager em = emf.createEntityManager();

    private DatabaseHelper() {

    }

    public static Teacher login(String email, String password) throws  MainControllerInterface.LoginException{

        Query query = em.createNamedQuery("login_user");
        query.setParameter(1, email);
        query.setParameter(2, password);
        System.out.printf(query.toString());

        List<Teacher> list = query.getResultList();
        if (list.size() == 1) {
            Teacher user = list.get(0);
            return user;
        }else{
            throw new MainControllerInterface.LoginException("Invalid credentials");
        }
    }
}
