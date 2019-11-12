package org.ib.kanl.dao;

import org.ib.kanl.pojo.Carte;
import org.ib.kanl.pojo.Main;

import javax.persistence.EntityManager;

    public class MainDAO extends AbstractDAO<Main> {

        public MainDAO(EntityManager em) {
            super(em);
        }

        @Override
        public Main create(Main main) {
            em.getTransaction().begin();
            em.persist(main.mainToString());
            em.getTransaction().commit();
            return main;
        }

        @Override
        public void delete(Main main) {
            em.getTransaction().begin();
            em.remove(main);
            em.getTransaction().commit();
        }

        @Override
        public void update(Main main) {
            em.getTransaction().begin();
            em.merge(main.mainToString());
            em.getTransaction().commit();
        }

        @Override
        public Main get(int id) {
            return em.find(Main.class, id);
        }
    }
