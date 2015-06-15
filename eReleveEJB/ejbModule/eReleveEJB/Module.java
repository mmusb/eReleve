package eReleveEJB;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="Module")
public class Module implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5528552575942140658L;


	private int id;
	

	private String nom;
	
	
	private String niveau;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

    
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
    	System.out.println("Module's equals method. Object received:  " + obj.toString()+ " and returned:  "+ super.equals(obj)); 

		return super.equals(obj);
	}


	public void setId(int id) {
		this.id = id;
	}
	
	
	@Column(name="Nom")
	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}
	

	@Column(name="Niveau")
	public String getNiveau() {
		return niveau;
	}


	public void setNiveau(String niveau) {
		this.niveau= niveau; 
	}
	
	
}
