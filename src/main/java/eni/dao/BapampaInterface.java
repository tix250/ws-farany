package eni.dao;
import java.util.Date;
import java.util.List;

import eni.entities.Region;
import eni.entities.Signalement;
import eni.entities.TypeSignalement;
import eni.entities.UtilisateurFO;
import eni.entities.UtilisateurMobile;



public interface BapampaInterface {
	public UtilisateurFO connexionFO(String login ,String mdp ) ;
	public void insertUserFo (UtilisateurFO u);
	public List<Signalement> ListSignalementRegion (int idRegion); 
	public Region findRegion (int id_region);
	public UtilisateurMobile findUtilisateurMobile (int id_user);
	
	public void terminerSignalement (int idSignalement) ;
	//GET
	public int nbrPageSignalement (int id_region ,int elementParPage);
	//POST
	public List<Signalement> rechercheSimple(String nomSignalement , int idRegion);
	//POST
	public List<Signalement> rechercheAvancer(int idRegion , Date d, int idTypeSignalemet, int status , String nomSignalement);
	//GET
	public List<TypeSignalement> ListAllTypeSignalement ();
	//GET
	public Signalement detailSignalement (int idSignalement);
	//GET
	public TypeSignalement getTypeSignalement (int idTypeSignalement);
	
}
