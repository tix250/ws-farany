package eni.dao;

import java.util.List;

import eni.entities.Region;
import eni.entities.Signalement;
import eni.entities.UtilisateurBO;



public interface MikaInterface {
	public UtilisateurBO connexion(String login , String mdp );
	public List<Region> listeRegion();
	public List<Signalement> listeSignalement();
	public void affecterSignalement(Signalement s);
	public List<Signalement> listeSignalementAffecter();
	public String typeSignalement(int idTypesignalement);

}
