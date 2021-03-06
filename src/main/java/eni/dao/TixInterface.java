package eni.dao;

import java.util.ArrayList;

import eni.entities.Region;
import eni.entities.Signalement;
import eni.entities.StatSignialement;
import eni.entities.UtilisateurMobile;


public interface TixInterface {
	public boolean InscriptionMobile(UtilisateurMobile user);
	public UtilisateurMobile loginMobile(String email , String mdp);
	public boolean insererSignalement(Signalement s);
	public ArrayList<UtilisateurMobile> getAllUserMobile();
	public ArrayList<Signalement> recupererToutSignialement();
	public ArrayList<StatSignialement> donnerStatRegionSignialement();
	public ArrayList<Region> findAllRegion();
	public boolean  insertRegion(Region r);
}
