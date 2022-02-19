package eni.dao;

import java.util.ArrayList;

import eni.entities.Signalement;
import eni.entities.UtilisateurMobile;


public interface TixInterface {
	public boolean InscriptionMobile(UtilisateurMobile user);
	public UtilisateurMobile loginMobile(String email , String mdp);
	public boolean insererSignalement(Signalement s);
	public ArrayList<UtilisateurMobile> getAllUserMobile();
	public ArrayList<Signalement> recupererToutSignialement();
}
