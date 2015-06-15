package eReleveEJB;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;





import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
@LocalBean
public class GestionReleve implements GestionReleveRemote {

	

	@PersistenceContext
	EntityManager em;
	
	
	
	/*** Créations ***/
	
	public Classe addClasse(String nom, String branche, Ecole ecole){
    	
		Classe c= new Classe();
		c.setNom(nom);
		c.setBranche(branche);
		c.setEcole(ecole);
		em.persist(c);
		return c;
    }
	
	
	public Ecole addEcole(String nom, String type) throws Exception{
    	
		Ecole ec= new Ecole();
		ec.setNom(nom);
		ec.setType(type);
		
		try{
		em.persist(ec);
		}
		catch(Exception e){
			throw new Exception(e);
		}
		return ec;
    }
		
	
	public Eleve addEleve(String nom, String prenom,  Date date_naissance, Classe classe){
    	
		Eleve el= new Eleve();
		el.setNom(nom);
		el.setPrenom(prenom);
		el.setDate_naissance(date_naissance);
		el.setClasse(classe);
		em.persist(el);
		return el;
    }
	
	
	public Employe addEmploye(String nom, String prenom, String fonction, Ecole ecole, String email, String password){
		
		
		Employe epl= new Employe();
		epl.setNom(nom);
		epl.setPrenom(prenom);
		epl.setFonction(fonction);
		epl.setEcole(ecole);
		
		User u = registerUser(nom, prenom, email, password, 5);
		
		epl.setUser(u);
		em.persist(epl);
		
		return epl;
	}
	
	
	public Module addModule(String nom, String niveau){
    	
		Module m= new Module();
		m.setNom(nom);
		m.setNiveau(niveau);
		em.persist(m);
		return m;
    }
	

	public Note addNote(String note, Eleve eleve, Module module){
    	
		Note n= new Note();
		n.setNote(note);
		n.setEleve(eleve);
		n.setModule(module);
		em.persist(n);
		return n;
    }

	
	public Prof addProf(String nom, String prenom, Ecole ecole, Module module, String email, String password){
		
		
		Prof pf= new Prof();
		pf.setNom(nom);
		pf.setPrenom(prenom);
		pf.setEcole(ecole);
		pf.setModule(module);
		
		User u = registerUser(nom, prenom, email, password, 3);
		
		pf.setUser(u);
		em.persist(pf);
		
		return pf;
	}
	
	

	/*** Recherches et consultations ***/

	
@SuppressWarnings("unchecked")
public List<Classe> getAllClasses(){
	return em.createQuery("SELECT cl FROM Classe cl ORDER BY cl.nom")
	.getResultList();
}	


@SuppressWarnings("unchecked")
public List<Ecole> getAllEcoles(){
	return em.createQuery("SELECT ec FROM Ecole ec ORDER BY ec.nom")
	.getResultList();
}	


@SuppressWarnings("unchecked")
public List<Eleve> getAllEleves(){
	return em.createQuery("SELECT el FROM Eleve el ORDER BY el.nom")
	.getResultList();
}	


@SuppressWarnings("unchecked")
public List<Employe> getAllEmployes(){
	return em.createQuery("SELECT epl FROM Employe epl ORDER BY epl.nom")
	.getResultList();
}	



@SuppressWarnings("unchecked")
public List<Module> getAllModules(){
	return em.createQuery("SELECT m FROM Module m ORDER BY m.niveau")
	.getResultList();
}	


@SuppressWarnings("unchecked")
public List<Note> getAllNotes(){
	return em.createQuery("SELECT n FROM Note n")
	.getResultList();
}	


	
@SuppressWarnings("unchecked")
public List<Eleve> findEleve_nom(String nom){
	List<Eleve> list=em.createQuery("SELECT el FROM Eleve el where el.nom=?1")
	    	.setParameter(1,nom)
	    	.getResultList();
	return list.isEmpty()? null : list;
}


@SuppressWarnings("unchecked")
public List<Eleve> findEleve_parent(User parent){
	List<Eleve> list=em.createQuery("SELECT el FROM Eleve el where el.parent=?1")
	    	.setParameter(1,parent)
	    	.getResultList();
	return list.isEmpty()? null : list;
}


@SuppressWarnings("unchecked")
private User findUser(String email){
	List<User> list=em.createQuery("SELECT u FROM User u where u.email=?1")
	    	.setParameter(1,email)
	    	.getResultList();
	return list.isEmpty()? null : list.get(0);
}





 /***** Recherches / écoles *****/

@SuppressWarnings("unchecked")
public List<Classe> getEcoleClasses(Ecole ecole){
	return em.createQuery("SELECT cl FROM Classe cl WHERE cl.ecole=?1 ORDER BY cl.nom")
			.setParameter(1,ecole)
			.getResultList();
}	


@SuppressWarnings("unchecked")
public List<Eleve> getEcoleEleves(Ecole ecole){
	
	return em.createQuery("SELECT el FROM Eleve el, IN (el.classe) cl WHERE cl.ecole = ?1 ORDER BY el.nom")
			.setParameter(1,ecole)
			.getResultList();
}	


@SuppressWarnings("unchecked")
public List<Prof> getEcoleProfs(Ecole ecole){
	
	return em.createQuery("SELECT pr FROM Prof pr WHERE pr.ecole=?1 ORDER BY pr.module ASC, pr.nom ASC")
			.setParameter(1,ecole)
			.getResultList();
}	


//Les employés de "ecole"
@SuppressWarnings("unchecked")
public List<Employe> getEmployes_ec(Ecole ecole){
	return em.createQuery("SELECT epl FROM Employe epl WHERE epl.ecole=?1 ORDER BY epl.nom")
			.setParameter(1,ecole)
			.getResultList();
}	

//Les modules du niveau "niveau"
@SuppressWarnings("unchecked")
public List<Module> getModules_ec(String niveau){
	return em.createQuery("SELECT m FROM Module m WHERE m.niveau=?1 ORDER BY m.nom")
			.setParameter(1,niveau)
			.getResultList();
}	



/***** Recherches / Classe *****/


@SuppressWarnings("unchecked")
public List<Eleve> getClasseEleves(Classe classe){
	
	return em.createQuery("SELECT el FROM Eleve el WHERE el.classe = ?1 ORDER BY el.nom")
			.setParameter(1,classe)
			.getResultList();
}	


@SuppressWarnings("unchecked")
public List<Prof> getModuleProfs(Ecole ecole, Module module){
	
	return em.createQuery("SELECT pr FROM Prof pr WHERE pr.ecole=?1 AND pr.module=?2 ORDER BY pr.module, pr.nom")
			.setParameter(1,ecole)
			.setParameter(2, module)
			.getResultList();
}	


/************ Méthodes pour l'utisateur ***************/


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
 
 
 
 public User checkLogin(String email, String password){
        User retval=findUser(email);
        if(retval==null) return null;
        if(BCrypt.checkpw(password, retval.getPassword()))
        {
     	   return retval;
        } else return null;
     }

 	public boolean isLoginUsed(String login){
 	return findUser(login)!=null;
 }
 
 	
 	//Les employés de "ecole"
 	@SuppressWarnings("unchecked")
 	public Employe findUserEmploye(User u){
 		
 		List<Employe> list=em.createQuery("SELECT epl FROM Employe epl WHERE epl.user=?1")
 				.setParameter(1,u)
 				.getResultList(); 
 		
 		return list.isEmpty()? null : list.get(0);
 	}	
 	
 	
 	//Les employés de "ecole"
 	 	@SuppressWarnings("unchecked")
 	 	public Prof findUserProf(User u){
 	 		
 	 		List<Prof> list=em.createQuery("SELECT pr FROM Prof pr WHERE pr.user=?1")
 	 				.setParameter(1,u)
 	 				.getResultList(); 
 	 		
 	 		return list.isEmpty()? null : list.get(0);
 	 	}	
 	
 	
 	
}
