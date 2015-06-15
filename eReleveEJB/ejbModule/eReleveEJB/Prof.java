package eReleveEJB;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="Prof")
public class Prof implements Serializable {

	
	private static final long serialVersionUID = -5960371069728822202L;

	private long id;
	

	private String nom;
	
	
	private String prenom;

	
	private Ecole ecole;
	
	
	private User user;


	private Module module;

	
	private Set<Classe> classes = new HashSet<Classe>(0);
	
	
	
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

	
	
	@Column(name="Prenom")
	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	

	@ManyToOne
	@JoinColumn(name = "idEcole")
	public Ecole getEcole() {
		return ecole;
	}


	public void setEcole(Ecole ecole) {
		this.ecole = ecole;
	}
	
	
	
	@ManyToOne
	@JoinColumn(name = "idUser")
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
	@ManyToOne
	@JoinColumn(name = "idModule")
	public Module getModule(){
		return module;
	}

	public void setModule(Module module){
		this.module= module;
	}


	@ManyToMany
	public Set<Classe> getClasses() {
		return classes;
	}


	public void setClasses(Set<Classe> classes) {
		this.classes = classes;
	}
}
