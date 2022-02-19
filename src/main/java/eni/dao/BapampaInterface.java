package eni.dao;
import java.util.List;

import eni.entities.Region;
import eni.entities.Signalement;
import eni.entities.UtilisateurFO;
import eni.entities.UtilisateurMobile;



public interface BapampaInterface {
	public UtilisateurFO connexionFO(String login ,String mdp ) ;
	public List<Signalement> ListSignalementRegion (int idRegion); 
	public Region findRegion (int id_region);
	public UtilisateurMobile findUtilisateurMobile (int id_user);
}
