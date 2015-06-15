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
@Table(name="Employe")
public class Employe implements Serializable {

		
		private static final long serialVersionUID = -5960371069728822202L;

		private long id;
		

		private String nom;
		
		
		private String prenom;
		
		
		private String fonction;
		

		private Ecole ecole;

		
		private User user;
		
		
		
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
		
		
		
		@Column(name="Fonction")
		public String getFonction() {
			return fonction;
		}


		public void setFonction(String fonction) {
			this.fonction = fonction;
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
		

	}
