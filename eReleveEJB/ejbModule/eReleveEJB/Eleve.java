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
@Table(name="Eleve")
public class Eleve implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private long id;
	

	private String nom;
	
	
	private String prenom;
	
	
	private Date date_naissance;
	
	
	private User parent;
	
	
	private Classe classe;

	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

    
	public void setId(long id) {
		this.id = id;
	}

	
	
    @Column(name="Prenom")
	public String getPrenom() {
		return prenom;
	}

	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	
	
	@Column(name="Nom")
	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}

	
	
	@Column(name="Date_naissance")
	
	public Date getDate_naissance() {
		return date_naissance;
	}


	public void setDate_naissance(Date date_naissance) {
		this.date_naissance = date_naissance;
	}
	
	
	
	@ManyToOne
	@JoinColumn(name = "idClasse")
	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	
	@ManyToOne
	@JoinColumn(name = "idParent")
	public User getParent() {
		return parent;
	}


	public void setParent(User parent) {
		this.parent = parent;
	}

}
