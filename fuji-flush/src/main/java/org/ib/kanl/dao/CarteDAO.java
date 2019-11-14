package org.ib.kanl.dao;

import org.ib.kanl.pojo.Carte;
import org.ib.kanl.pojo.Main;

import javax.persistence.EntityManager;

public class CarteDAO extends AbstractDAO<Carte> {

    public CarteDAO(EntityManager em) {
        super(em);
    }

    @Override
    public Carte create(Carte c) {
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        return c;
    }

    @Override
    public void delete(Carte c) {
        em.getTransaction().begin();
        em.remove(c);
        em.getTransaction().commit();
    }

    @Override
    public void update(Carte c) {
        em.getTransaction().begin();
        em.merge(c);
        em.getTransaction().commit();
    }

    @Override
    public Carte get(int id) {
        return em.find(Carte.class, id);
    }
}
