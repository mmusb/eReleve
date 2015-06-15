package eReleveEJB;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface GestionUserRemote {

	
	
	
	/************ Méthodes pour l'utisateur ***************/
	public User registerUser(String nom, String prenom, String email, String password, int profil);
	public User checkLogin(String email, String password, int profile);
 	public boolean isLoginUsed(String login);
 	
}
