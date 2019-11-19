package org.ib.kanl.dao;

import org.ib.kanl.pojo.Carte;
import org.ib.kanl.pojo.Partie;

import javax.persistence.EntityManager;

public class PartieDAO extends AbstractDAO<Partie> {

    public PartieDAO(EntityManager em) {
        super(em);
    }

    @Override
    public Partie create(Partie partie) {
        em.getTransaction().begin();
        em.persist(partie);
        em.getTransaction().commit();
        return partie;
    }

    @Override
    public void delete(Partie partie) {
        em.getTransaction().begin();
        em.remove(partie);
        em.getTransaction().commit();
    }

    @Override
    public void update(Partie partie) {
        em.getTransaction().begin();
        em.merge(partie);
        em.getTransaction().commit();
    }

    @Override
    public Partie get(int id) {
        return em.find(Partie.class, id);
    }
}
