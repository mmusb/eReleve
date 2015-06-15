package eReleveEJB;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



@Stateless
@LocalBean
public class GestionUser implements GestionUserRemote {

	@PersistenceContext
	EntityManager em;
	
	

@SuppressWarnings("unchecked")
public List<Eleve> findEleve_parent(User parent){
	List<Eleve> list=em.createQuery("SELECT el FROM Eleve el where el.parent=?1")
	    	.setParameter(1,parent)
	    	.getResultList();
	return list.isEmpty()? null : list;
}
	
		

/************ Méthodes pour l'utisateur ***************/

	
@SuppressWarnings("unchecked")
private User findUser(String email){
	List<User> list=em.createQuery("SELECT u FROM User u where u.email=?1")
	    	.setParameter(1,email)
	    	.getResultList();
	return list.isEmpty()? null : list.get(0);
}

@SuppressWarnings({ "unchecked"})
private User findUser_profile(String email, int profile){
	List<User> list=em.createQuery("SELECT u FROM User u where u.email=?1 and u.profil=?2")
	    	.setParameter(1,email).setParameter(2,profile)
	    	.getResultList();
	return list.isEmpty()? null : list.get(0);
}


public User registerUser(String nom, String prenom, String email, String password, int profil){
	User u=new User();
	u.setNom(nom);
	u.setPrenom(prenom);
	//u.setUsername(username);
	u.setEmail(email);
	newPassword(u, password);
	u.setProfil(profil);  //Profil: parent=1, Prof= 3, Direction= 5.
	em.persist(u);
	return u;
}



 private void newPassword(User u, String password){
    	String salt=BCrypt.gensalt();
		u.setPassword(BCrypt.hashpw(password, salt));
    }
 
 
 
 public User checkLogin(String email, String password, int profile){
        User retval= findUser_profile(email, profile);
        if(retval==null) return null;
        if(BCrypt.checkpw(password, retval.getPassword()))
        {
     	   return retval;
        } else return null;
     }

 
 	public boolean isLoginUsed(String login){
 	return findUser(login)!=null;
 }

}
