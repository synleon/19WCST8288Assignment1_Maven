package standalone;

import entity.Player;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Date;

public class TestMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager manager = entityManagerFactory.createEntityManager();
        manager.getTransaction().begin();

        Player player = new Player();

        player.setId(100000013);
        player.setEmail("jd13@ac.ca");
        player.setFirstName("John13");
        player.setLastName("Doe13");
        player.setJoined(Date.valueOf("1913-01-01"));

        manager.persist(player);

        manager.getTransaction().commit();
        manager.close();
        entityManagerFactory.close();
    }
}
