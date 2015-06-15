package eReleveEJB;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;


@Stateful
@LocalBean
public class AdminFlow implements AdminFlowRemote {


	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	EntityManager em;

	
		private State state;
		private AdminUser au;

		
		@EJB
		GestionReleve g;
		
		
	    /**
	     * Default constructor. 
	     */
		
	    public AdminFlow() {
	        state=State.UNKNOWN;
	        au=null;
	    }
		
	    
	    public State getState() {
			return state;
		}
		
	    
	    
	    // Pour s'enregistrer
	    public void register(String nom, String prenom, String email, String password){
	    	// Assuming state=UNKNOWN - TODO: add a check
	    	if(isAdminLoginUsed(email)){
	    		// The username is already used
	    		state=State.USERNAME_USED;
	    	}
	    	else{
	    		registerAdmin(nom, prenom, email, password);
	    		state=State.REGISTERED; 
	    		// We could return right here
	    		// Instead, we proceed with login
	            login(email, password);
	            
	    	}
	    }
		
	    
	    
	    // Pour se connecter
	    public void login(String email, String password){
	    	au= checkAdminLogin(email, password);
	    	if(au==null){
	    		state=State.LOGIN_FAILURE;
	    	}else{
	    		state=State.LOGGED_IN;
	    	}
	    }



	    /********* Ajouts **********/
	    
	    // Ajout d'une Ecole
	    public void addEcole(String nom, String type) throws Exception{
	    	
	    	try{
	    	g.addEcole(nom, type);
	    	}
	    	catch (Exception e){
	    		
	    		throw new Exception(e);
	    	}
	    }

	    
	 // Ajout d'un Employe
	    public void addEmploye (String nom, String prenom, String fonction, Ecole ecole, String email, String password) throws Exception{
	    	
	    	try{
	    	g.addEmploye(nom, prenom, fonction, ecole, email, password);
	    	}
	    	catch (Exception e){
	    		
	    		throw new Exception(e);
	    	}
	    }
	    
	    
	    // Ajout d'un module
	    public void addModule(String nom, String niveau) throws Exception{
	    	
	    	try{
		    	g.addModule(nom, niveau);
		    	}
		    	catch (Exception e){
		    		
		    		throw new Exception(e);
		    	}
	    	
	    	
	    }
	    
	    
	    
	    /********* Consultations **********/
	    
	    public List<Ecole> getEcoles(){
	    	
	    	return g.getAllEcoles();
	    	
	    }
	    
	    // Touuuut les employés
	    public List<Employe> getAllEmployes(){
	    	
	    	return g.getAllEmployes();
	    	
	    }
	    
	    
	    // Les employés de l'école "ec"
	    public List<Employe> getEmployes_ecole(Ecole ec){
	    	
	    	return g.getEmployes_ec(ec);
	    	
	    }
	    
	    
	    @SuppressWarnings("unchecked")
	    public List<AdminUser> getAllAdmins(){
	    
    		return em.createQuery("SELECT ad FROM AdminUser ad ORDER BY ad.nom")
    		.getResultList();
    	}	
	    	
	    
	    @SuppressWarnings("unchecked")
	    public List<Module> getAllModules(){
	    
    	return g.getAllModules();
    	}	
	    
	    
	    // Pour se déconnecter
	    public void logout(){
	    	au=null;
	    	state=State.UNKNOWN;
	    }
	    
	    
	    public AdminUser getAu() {
			return au;
		}
	    
	    
	    
	    
	    
	    /************ Méthodes pour le super utisateur ***************/

	    
	    @SuppressWarnings({ "unchecked" })
	    private AdminUser findAdmin(String email){
	    	List<AdminUser> list=em.createQuery("SELECT a FROM AdminUser a where a.email=?1")
	    	    	.setParameter(1,email)
	    	    	.getResultList();
	    	return list.isEmpty()? null : list.get(0);
	    }
	    
	 	
	 	private AdminUser registerAdmin(String nom, String prenom, String email, String password){
	 		AdminUser au=new AdminUser();
	 		au.setNom(nom);
	 		au.setPrenom(prenom);
	 		//u.setUsername(username);
	 		au.setEmail(email);
	 		newAdminPassword(au, password);
	 		em.persist(au);
	 		
	 		return au;
	 	}

	 	
	 	private void newAdminPassword(AdminUser au, String password){
	 	    	String salt=BCrypt.gensalt();
	 			au.setPassword(BCrypt.hashpw(password, salt));
	 	    }
	 	 
	 	public AdminUser checkAdminLogin(String email, String password){
	        AdminUser retval=findAdmin(email);
	        if(retval==null) return null;
	        if(BCrypt.checkpw(password, retval.getPassword()))
	        {
	     	   return retval;
	        } else return null;
	     }

	 	
	 	public boolean isAdminLoginUsed(String login){
	 	return findAdmin(login)!=null;
	 }
	    
	}
