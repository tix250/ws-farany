package eni.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eni.entities.Region;
import eni.entities.Signalement;
import eni.entities.TypeSignalement;
import eni.entities.UtilisateurFO;
import eni.entities.UtilisateurMobile;
import eni.repository.BapampaRepository;
import eni.repository.MikaRepository;



@Service
public class BapampaMetier implements BapampaInterface{
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private BapampaRepository  br;
	
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

	@Override
	public void insertUserFo(UtilisateurFO u) {
		br.save(u);
	}

	@Override
	public void terminerSignalement(int idSignalement) {
		Query req = em.createQuery("update Signalement s set s.statut =:statut where s.id_signalement =:id_signalement");
		req.setParameter("statut", 3 );
		req.setParameter("id_signalement", idSignalement);
		req.executeUpdate();
	}

	@Override
	public int nbrPageSignalement(int id_region, int elementParPage) {
		int nbrPage = 0;
		try {	
			List<Signalement> list;
			Query  req = em.createQuery("select e from Signalement e where e.id_region=:id_region and e.statut=2");
			req.setParameter("id_region", id_region);
			list =  (List<Signalement>)req.getResultList();
			nbrPage = list.size();
			
			
		}catch (Exception e ) {
			
		}
		
		if((nbrPage % elementParPage) != 0) {
			nbrPage = (nbrPage /elementParPage) + 1;
		}else {
			nbrPage = nbrPage /elementParPage;
		}
		
		return  nbrPage;
	}

	@Override
	public List<Signalement> rechercheSimple(String nomSignalement, int idRegion) {
		List<Signalement> allSignalement = new ArrayList<Signalement>();
		try {
			
			Query  req = em.createQuery("select s from Signalement s where s.id_region=:idRegion and s.statut=2 and s.nom_signalement like :nom_signalement");
			req.setParameter("idRegion", idRegion);
			req.setParameter("nom_signalement", "%" + nomSignalement + "%");
			allSignalement = (List<Signalement>) req.getResultList();
			
			
		}catch (Exception e ) {
			
		}
		return allSignalement;
	}

	

	@Override
	public List<TypeSignalement> ListAllTypeSignalement() {
		List<TypeSignalement> listSg = new ArrayList<TypeSignalement>();
		Query req = em.createQuery("select cr from TypeSignalement cr");
		listSg = req.getResultList();
		return listSg;
	}

	@Override
	public Signalement detailSignalement(int idSignalement) {
		Signalement s = new Signalement();
		try {
			
			Query  req = em.createQuery("from Signalement where id_signalement=:id_signalement ");
			req.setParameter("id_signalement", idSignalement);
			
			s = (Signalement) req.getSingleResult();
			
			
		}catch (Exception e ) {
			
		}
		return s;
	}

	@Override
	public TypeSignalement getTypeSignalement(int idTypeSignalement) {
		TypeSignalement s = new TypeSignalement();
		try {
			Query  req = em.createQuery("from TypeSignalement where id_type_signalement=:id_type_signalement ");
			req.setParameter("id_type_signalement", idTypeSignalement);
			s = (TypeSignalement) req.getSingleResult();
			
			
		}catch (Exception e ) {
			
		}
		return s;
	}

	@Override
	public List<Signalement> rechercheAvancer(int idRegion, Date d, int idTypeSignalemet, int status,
			String nomSignalement) {
		
		List<Signalement> allSignalement = new ArrayList<Signalement>();
		String requette = "select s from Signalement s where s.id_region=:idRegion";
		if (status != 0) {
			requette = requette + " and s.statut=:status ";
		}
		
		if (nomSignalement != null) {
			requette = requette + " and s.nom_signalement like :nom_signalement ";
		}
		if (d != null) {
			requette = requette + " and s.date=:date ";
		}
		if (idTypeSignalemet != 0) {
			requette = requette + "and id_type_signalement =:id_type_signalement";
		}
		
		try {
			
			Query  req = em.createQuery(requette);
			req.setParameter("idRegion", idRegion);
			if (status != 0 ) {
				req.setParameter("status", status);
			}
			if (nomSignalement != null) {
				req.setParameter("nom_signalement", "%" + nomSignalement + "%");
			}
			if (d != null) {
				req.setParameter("date", d);
			}
			if (idTypeSignalemet != 0) {
				requette = requette + "and id_type_signalement =:id_type_signalement";
			}
			
			allSignalement = (List<Signalement>) req.getResultList();

		}catch (Exception e ) {
			
		}
		return allSignalement;
	}
	
	
}
