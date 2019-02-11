package dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionListener;
import java.util.Objects;

@javax.servlet.annotation.WebListener()
public class EMFactory implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    private static EntityManagerFactory emFactory;

    @Override
    public void contextInitialized(ServletContextEvent sce){
        if(emFactory==null)
            emFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce){
        if(emFactory!=null)
            emFactory.close();
    }

    public static EntityManagerFactory getEMFactory(){
        Objects.requireNonNull(emFactory, "Entity Manager Factory not initilized");
        return emFactory;
    }
}
