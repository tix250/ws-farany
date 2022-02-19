package eni.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: UtilisateurFO
 *
 */
@Entity

public class UtilisateurFO implements Serializable {

	@Id
	@GeneratedValue
	private int idFO;
	private String nom;
	private String prenom;
	private int id_region;
	private String login;
	private String mdp;
	private static final long serialVersionUID = 1L;

	public UtilisateurFO() {
		super();
	}   
	public int getIdFO() {
		return this.idFO;
	}

	public void setIdFO(int idFO) {
		this.idFO = idFO;
	}   
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}   
	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}   
	public int getId_region() {
		return this.id_region;
	}

	public void setId_region(int id_region) {
		this.id_region = id_region;
	}   
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}   
	public String getMdp() {
		return this.mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
   
}
