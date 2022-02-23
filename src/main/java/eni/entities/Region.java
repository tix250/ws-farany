package eni.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Region
 *
 */

@Entity

public class Region implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_region;
	private String nom_region;
	private static final long serialVersionUID = 1L;

	public Region() {
		super();
	}   
	public int getId_region() {
		return this.id_region;
	}

	public void setId_region(int id_region) {
		this.id_region = id_region;
	}   
	public String getNom_region() {
		return this.nom_region;
	}

	public void setNom_region(String nom_region) {
		this.nom_region = nom_region;
	}
   
}
