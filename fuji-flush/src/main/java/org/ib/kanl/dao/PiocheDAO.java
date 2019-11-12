package org.ib.kanl.dao;

import org.ib.kanl.pojo.Carte;
import org.ib.kanl.pojo.Pioche;

import javax.persistence.EntityManager;

public class PiocheDAO extends AbstractDAO<Pioche> {

    public PiocheDAO(EntityManager em) {
        super(em);
    }

    @Override
    public Pioche create(Pioche pioche) {
        em.getTransaction().begin();
        em.persist(pioche.piocheToString());
        em.getTransaction().commit();
        return pioche;
    }

    @Override
    public void delete(Pioche pioche) {
        em.getTransaction().begin();
        em.remove(pioche);
        em.getTransaction().commit();
    }

    @Override
    public void update(Pioche pioche) {
        em.getTransaction().begin();
        em.merge(pioche.piocheToString());
        em.getTransaction().commit();
    }

    @Override
    public Pioche get(int id) {
        return em.find(Pioche.class, id);
    }
}

