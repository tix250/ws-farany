package eni.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Signalement
 *
 */
@Entity

public class Signalement implements Serializable {

	   
	@Id
	@GeneratedValue
	private int id_signalement;
	private int id_user;
	private int id_region;
	private Date date;
	private String description;
	private int statut;
	private int id_type_signalement;
	private String photo;
	private String coordonnee;
	private static final long serialVersionUID = 1L;

	public Signalement() {
		super();
	}   
	public int getId_signalement() {
		return this.id_signalement;
	}

	public void setId_signalement(int id_signalement) {
		this.id_signalement = id_signalement;
	}   
	public int getId_user() {
		return this.id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}   
	public int getId_region() {
		return this.id_region;
	}

	public void setId_region(int id_region) {
		this.id_region = id_region;
	}   
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}   
	public int getStatut() {
		return this.statut;
	}

	public void setStatut(int statut) {
		this.statut = statut;
	}   
	public int getId_type_signalement() {
		return this.id_type_signalement;
	}

	public void setId_type_signalement(int id_type_signalement) {
		this.id_type_signalement = id_type_signalement;
	}   
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}   
	public String getCoordonnee() {
		return this.coordonnee;
	}

	public void setCoordonnee(String coordonnee) {
		this.coordonnee = coordonnee;
	}
   
}
