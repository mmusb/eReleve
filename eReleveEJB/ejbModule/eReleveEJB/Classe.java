package eReleveEJB;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="Classe")
public class Classe implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private long id;
	

	private String nom;
	
	
	private String branche;
	
	
	private Ecole ecole;
	
	
	private Set<Prof> profs = new HashSet<Prof>(0);

	
	
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

	
	
	@Column(name="Branche")
	public String getBranche() {
		return branche;
	}


	public void setBranche(String branche) {
		this.branche = branche;
	}
	
	
	@ManyToOne
	@JoinColumn(name = "idEcole")
	public Ecole getEcole() {
		return ecole;
	}

	public void setEcole(Ecole ecole) {
		this.ecole = ecole;
	}
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "Enseigner", 
			joinColumns = {@JoinColumn(name = "idClasse", nullable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "idProf", nullable = false) })
	
	public Set<Prof> getProfs(){
		return this.profs;
	}
	
	public void setProfs(Set<Prof> profs){
		this.profs = profs;
	}
	
}
