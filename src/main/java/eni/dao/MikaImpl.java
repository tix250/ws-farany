package eni.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eni.entities.Region;
import eni.entities.Signalement;
import eni.entities.UtilisateurBO;
import eni.entities.UtilisateurMobile;
import eni.repository.MikaRepository;
import eni.repository.SignalementRepository;


@Service
public class MikaImpl implements MikaInterface{
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private MikaRepository  mr;
	
	@Override
	public UtilisateurBO connexion(String login, String mdp) {
			
			UtilisateurBO mp = new UtilisateurBO ();
			try {
				
				Query req = em.createQuery("from UtilisateurBO where login=:login and mdp=:mdp");
				req.setParameter("login", login);
				req.setParameter("mdp", mdp);
				
				mp = (UtilisateurBO) req.getSingleResult();
				
				if (mp == null) throw new RuntimeException("compte introuvable");
			
			}catch (RuntimeException e ) {
				e.printStackTrace();
			}
			
			return mp;
		}
	
	@Override
	public void affecterSignalement(Signalement s) {
		Signalement ssd = new Signalement();
		try {
			Query req = em.createQuery("FROM Signalement where id_signalement = " + s.getId_signalement() )  ;
			ssd = (Signalement) req.getSingleResult();
			
		}catch (Exception e ) {
			e.printStackTrace();		
		}
		
		ssd.setId_region(s.getId_region());
		em.merge(ssd);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Signalement> listeSignalement() {
		
		List<Signalement> resultat = new ArrayList<>();
		try {
			
			Query req = em.createQuery("select s from Signalement s where id_region = 0" )  ;
			resultat = req.getResultList();
			
			if (resultat== null) throw new RuntimeException("Tsy misy signalement");
			
		}catch (Exception e ) {
			e.printStackTrace();		
		}
		
		return resultat;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Region> listeRegion() {
		List<Region> regions = new ArrayList<>();
		try {
			
			Query req = em.createQuery("select r from Region r " )  ;			
			regions = req.getResultList();
			
			if (regions== null) throw new RuntimeException("Tsy nisy region");
			
		}catch (Exception e ) {
			e.printStackTrace();		
		}
		
		return regions;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Signalement> listeSignalementAffecter() {
		List<Signalement> resultat = new ArrayList<>();
		try {
			
			Query req = em.createQuery("select s from Signalement s where s.id_region=0 " )  ;
			resultat = req.getResultList();
			
			if (resultat== null) throw new RuntimeException("Tsy misy signalement");
			
		}catch (Exception e ) {
			e.printStackTrace();		
		}
		
		return resultat;
	}
	
	public String typeSignalement(int idTypesignalement) {
		String type = null ;
		try {
			
			Query req = em.createQuery("select t.type_signalement from TypeSignalement t where t.id_type_signalement=:idTypesignalement " )  ;
			req.setParameter("idTypesignalement",idTypesignalement);
			type = (String) req.getSingleResult();
			
			if (type== null) throw new RuntimeException("Tsy misy signalement");
			
		}catch (Exception e ) {
			e.printStackTrace();		
		}
		
		return type;
	}

	@Override
	public void insertUserBo(UtilisateurBO u) {
		mr.save(u);
	}


}
