package org.ib.kanl.dao;

import org.ib.kanl.pojo.Carte;
import org.ib.kanl.pojo.Joueur;

import javax.persistence.EntityManager;

public class JoueurDAO extends AbstractDAO<Joueur> {

    public JoueurDAO(EntityManager em) {
        super(em);
    }

    @Override
    public Joueur create(Joueur joueur) {
        em.getTransaction().begin();
        em.persist(joueur);
        em.getTransaction().commit();
        return joueur;
    }

    @Override
    public void delete(Joueur joueur) {
        em.getTransaction().begin();
        em.remove(joueur);
        em.getTransaction().commit();
    }

    @Override
    public void update(Joueur joueur) {
        em.getTransaction().begin();
        em.merge(joueur);
        em.getTransaction().commit();
    }

    @Override
    public Joueur get(int id) {
        return em.find(Joueur.class, id);
    }
}

