package eni.dao;

import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eni.entities.Region;
import eni.entities.Signalement;
import eni.entities.StatSignialement;
import eni.entities.UtilisateurMobile;
import eni.repository.RegionRepository;
import eni.repository.SignalementRepository;
import eni.repository.UserRepository;




@Service
public class TixImpl implements TixInterface {
	
	final static String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

	final static Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
			
	public static boolean isValid(final String password) 
	{
		java.util.regex.Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private SignalementRepository  sr;
	
	@Autowired
	private UserRepository ur;
	
	@Autowired
	private RegionRepository rr;
	
	@Override
	public boolean InscriptionMobile(UtilisateurMobile user) {
		
		if(isValid(user.getMdp()))
		{
			ur.save(user);
			return true;
		}
		System.out.println("mot de passe Incorrect");
		return false;
	}
	
	@Override
	public UtilisateurMobile loginMobile(String email , String mdp ) {
		UtilisateurMobile um = new UtilisateurMobile();
		try 
		{
			Query  req = em.createQuery("from UtilisateurMobile where email=:email and mdp =:mdp");
			req.setParameter("email", email);
			req.setParameter("mdp", mdp);
			um = (UtilisateurMobile) req.getSingleResult();
		} catch (Exception e) 
		{
			if(um == null )
			{
				throw new RuntimeException("compte introuvable");
			}
		}
		if(um.getId_user()!=0)
		{
			return um;
		}
		return null;
	}

	@Override
	public boolean insererSignalement(Signalement s) {
		sr.save(s);
		return true;
	}

	@Override
	public ArrayList<UtilisateurMobile> getAllUserMobile() {

		return (ArrayList) ur.findAll();
	}
	
	@Override
	public ArrayList<Signalement> recupererToutSignialement() {

		return (ArrayList) sr.findAll();
	}

	@Override
	public ArrayList<StatSignialement> donnerStatRegionSignialement() {
		ArrayList<StatSignialement> retour = new ArrayList<>();
		StatSignialement st = new StatSignialement();
		try 
		{
			Query  req = em.createQuery("SELECT id_region AS idRegion , COUNT(id_signalement ) AS nbrSignalement FROM signalement GROUP BY id_region ORDER BY isany ASC");
			st = (StatSignialement) req.getSingleResult();
		} catch (Exception e) 
		{
			if(st == null )
			{
				throw new RuntimeException("Signialment introuvable");
			}
		}
		
		return retour;
	}

	@Override
	public ArrayList<Region> findAllRegion() {UtilisateurMobile um = new UtilisateurMobile();
	ArrayList<Region>  regionLsit = new ArrayList<>();
	try 
	{
		Query  req = em.createQuery("SELECT a FROM Region a");
		regionLsit = (ArrayList<Region>) req.getResultList();
	} catch (Exception e) 
	{
		if(regionLsit == null )
		{
			throw new RuntimeException("region introuvable");
		}
	}
		return regionLsit;
	}

	@Override
	public boolean insertRegion(Region r) {
		rr.save(r);
		return true;
	}
	
}
