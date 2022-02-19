package eni.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: UserBackoffice
 *
 */
@Entity

public class UserBackoffice implements Serializable {

	   
	@Id
	@GeneratedValue
	private int idBackoffice;
	private String nom;
	private String prenom;
	private String login;
	private String mdp;
	private static final long serialVersionUID = 1L;

	public UserBackoffice() {
		super();
	}   
	public int getIdBackoffice() {
		return this.idBackoffice;
	}

	public void setIdBackoffice(int idBackoffice) {
		this.idBackoffice = idBackoffice;
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
