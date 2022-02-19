package eni.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eni.entities.Signalement;

public interface MikaRepository extends JpaRepository<Signalement, Integer>{

}
