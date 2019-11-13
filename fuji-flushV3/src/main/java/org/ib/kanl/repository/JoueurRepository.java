package org.ib.kanl.repository;

import org.ib.kanl.model.Joueur;
import org.springframework.data.repository.CrudRepository;



public interface JoueurRepository extends CrudRepository<Joueur, Integer>{
	Joueur findByPseudo(String pseudo);
}
