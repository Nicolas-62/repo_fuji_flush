package org.ib.kanl.dao;

import javax.persistence.EntityManager;

public abstract class AbstractDAO<T> {
    protected EntityManager em;

    public AbstractDAO(EntityManager em) {
        this.em = em;
    }

    /**
     * Méthode de création
     *
     * @param obj
     * @return boolean
     */
    public abstract T create(T obj);

    /**
     * Méthode pour effacer
     *
     * @param obj
     * @return boolean
     */
    public abstract void delete(T obj);

    /**
     * Méthode de mise à jour
     *
     * @param obj
     * @return boolean
     */
    public abstract void update(T obj);

    /**
     * Méthode de recherche des informations
     *
     * @param id
     * @return T
     */
    public abstract T get(int id);
}
