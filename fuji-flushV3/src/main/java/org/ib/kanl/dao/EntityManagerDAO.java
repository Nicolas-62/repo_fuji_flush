package org.ib.kanl.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerDAO {
	    private static EntityManagerFactory factory;
	    private static EntityManager em;

	    private EntityManagerDAO(){
	        factory = Persistence.createEntityManagerFactory("FujiFlushDB");
	        em = factory.createEntityManager();
	    }

	    public static EntityManager getInstance(){
	        if(em == null){
	            new EntityManagerDAO();
	        }
	        return em;
	    }

	    public static void closeEntityManager() {
	        em.close();
	        factory.close();
	    }
	}
