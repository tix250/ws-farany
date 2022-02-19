package eni.dao;

import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eni.entities.Signalement;
import eni.entities.UtilisateurMobile;
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
	
}
