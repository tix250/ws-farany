package eni.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: TypeSignalement
 *
 */
@Entity

public class TypeSignalement implements Serializable {

	@Id
	@GeneratedValue
	private int idTypesignalement;
	private String typesignalement;
	private static final long serialVersionUID = 1L;

	public TypeSignalement() {
		super();
	}   
	public int getIdTypesignalement() {
		return this.idTypesignalement;
	}

	public void setIdTypesignalement(int idTypesignalement) {
		this.idTypesignalement = idTypesignalement;
	}   
	public String getTypesignalement() {
		return this.typesignalement;
	}

	public void setTypesignalement(String typesignalement) {
		this.typesignalement = typesignalement;
	}
   
}
