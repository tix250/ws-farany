package eni.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import eni.entities.Region;
import eni.entities.Signalement;
import eni.entities.UtilisateurFO;
import eni.entities.UtilisateurMobile;



@Service
public class BapampaMetier implements BapampaInterface{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public UtilisateurFO connexionFO (String login ,String mdp ) {
		UtilisateurFO cp = new UtilisateurFO ();
		try {
			
			Query  req = em.createQuery("from UtilisateurFO where login=:login and mdp=:mdp");
			req.setParameter("login", login);
			req.setParameter("mdp", mdp);
			
			cp = (UtilisateurFO) req.getSingleResult();
			
			
		}catch (Exception e ) {
			
		}
		if (cp == null) throw new RuntimeException("compte introuvable");
		return cp;

	}

	@Override
	public List<Signalement> ListSignalementRegion( int idRegion) {
		List<Signalement> allSignalement = new ArrayList<Signalement>();
		try {
			
			Query  req = em.createQuery("from Signalement where id_region=:idRegion and statut = 2");
			req.setParameter("idRegion", idRegion);
			allSignalement = (List<Signalement>) req.getResultList();
			
			
		}catch (Exception e ) {
			
		}
		return allSignalement;
	}

	@Override
	public Region findRegion(int id_region) {
		Region reg = em.find(Region.class , id_region);
		if (reg == null) throw new RuntimeException("region introuvable");
		return reg;
	}

	@Override
	public UtilisateurMobile findUtilisateurMobile(int id_user) {
		UtilisateurMobile user = em.find(UtilisateurMobile.class , id_user);
		if (user == null) throw new RuntimeException("compte introuvable");
		return user;
	}
	
	
}
