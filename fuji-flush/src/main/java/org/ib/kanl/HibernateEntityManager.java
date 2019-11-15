package org.ib.kanl;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateEntityManager {
    private static EntityManagerFactory factory;
    private static EntityManager em;

    private HibernateEntityManager(){
        factory = Persistence.createEntityManagerFactory("FujiFlushDB");
        em = factory.createEntityManager();
    }

    public static EntityManager getInstance(){
        if(em == null){
            new HibernateEntityManager();
        }
        return em;
    }

    public  static void closeEntityManager() {
        em.close();
        factory.close();
    }
}
