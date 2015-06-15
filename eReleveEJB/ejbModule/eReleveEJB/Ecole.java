package eReleveEJB;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Ecole")
public class Ecole implements Serializable {

	
	private static final long serialVersionUID = -5960371069728822202L;

	private long id;
	

	private String nom;
	
	
	private String type;
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

    
	public void setId(long id) {
		this.id = id;
	}
	
	
	@Column(name="Nom")
	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}

	
	
	@Column(name="Type")
	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

}
