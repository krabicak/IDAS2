package controller.libs;

import model.Day;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DaysOfWeek implements Runnable {

    public void run() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IDAS2PU");
        EntityManager em = emf.createEntityManager();

        Query query = em.createNamedQuery("get_days_of_week");

        List<Day> days = query.getResultList();
        System.out.println(days);
        System.out.println(days.get(0).getFullname());

    }
}
