package eni.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: UtilisateurBO
 *
 */
@Entity

public class UtilisateurBO implements Serializable {

	   
	@Id
	@GeneratedValue
	private int idBO;
	private String login;
	private String nom;
	private String prenom;
	private String mdp;
	private static final long serialVersionUID = 1L;

	public UtilisateurBO() {
		super();
	}   
	public int getIdBO() {
		return this.idBO;
	}

	public void setIdBO(int idBO) {
		this.idBO = idBO;
	}   
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
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
	public String getMdp() {
		return this.mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
   
}
