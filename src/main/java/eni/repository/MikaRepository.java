package eni.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eni.entities.Signalement;
import eni.entities.UtilisateurBO;

public interface MikaRepository extends JpaRepository<UtilisateurBO, Integer>{
	
}
