package eReleveEJB;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface GestionReleveRemote {

	
	/*** Créations ***/
	public Classe addClasse(String nom, String branche, Ecole ecole);
	public Ecole addEcole(String nom, String type) throws Exception;
	public Eleve addEleve(String nom, String prenom,  Date date_naissance, Classe classe);
	public Employe addEmploye(String nom, String prenom, String fonction, Ecole ecole, String email, String password);
	public Module addModule(String nom, String niveau);
	public Note addNote(String note, Eleve eleve, Module module);
	public Prof addProf(String nom, String prenom, Ecole ecole, Module module, String email, String password);
	
	
	/*** Recherches et consultations ***/
	public List<Eleve> findEleve_nom(String nom);
	public List<Eleve> findEleve_parent(User parent);
	public List<Eleve> getAllEleves();
	public List<Module> getModules_ec(String niveau);

	
	
	/************ Méthodes pour l'utisateur ***************/
	public User registerUser(String nom, String prenom, String email, String password, int profil);
	public User checkLogin(String email, String password);
 	public boolean isLoginUsed(String login);
 	public Employe findUserEmploye(User u);
	public Prof findUserProf(User u);

 	
}
