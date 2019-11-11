package org.ib.kanl.dao;

import org.ib.kanl.pojo.Carte;

import javax.persistence.EntityManager;

public class CarteDAO extends AbstractDAO<Carte> {

        public CarteDAO(EntityManager em) {
            super(em);
        }

        @Override
        public Carte create(Carte carte) {
            em.getTransaction().begin();
            em.persist(carte);
            em.getTransaction().commit();
            return carte;
        }

        @Override
        public void delete(Carte carte) {
            em.getTransaction().begin();
            em.remove(carte);
            em.getTransaction().commit();
        }

        @Override
        public void update(Carte carte) {
            em.getTransaction().begin();
            em.merge(carte);
            em.getTransaction().commit();
        }

        @Override
        public Carte get(int id) {
            return em.find(Carte.class, id);
        }
}
