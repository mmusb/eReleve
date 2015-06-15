package eReleveEJB;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;


/**
 * Session Bean implementation class UserFlow
 */

@Stateful
@LocalBean
public class ProfFlow implements ProfFlowRemote {

	
	private State state;
	private User u;
	private Prof pr;

	
	@EJB
	GestionReleve gR;
	
	
	@EJB
	GestionUser gU;
	
	
	
    /**
     * Default constructor. 
     */
	
    public ProfFlow() {
        state=State.UNKNOWN;
        u=null;
    }
	
    public State getState() {
		return state;
	}
	
    // Pour s'enregistrer
    
    // Un prof ne peut s'enregistrer lui même, il 
    
    public void register(String nom, String prenom, String email, String password){
    	// Assuming state=UNKNOWN - TODO: add a check
    	if(gU.isLoginUsed(email)){
    		// The username is already used
    		state=State.USERNAME_USED;
    	}
    	else{
    		gU.registerUser(nom, prenom, email, password, 3);
    		state=State.REGISTERED; 
    		// We could return right here
    		// Instead, we proceed with login
          //  login(email, password);
            
    	}
    }
	
    
    // Pour se connecter
    public void login(String email, String password, int profile){
    	u=gU.checkLogin(email, password, profile);
    	if(u==null){
    		state=State.LOGIN_FAILURE;
    	}else{
    		state=State.LOGGED_IN;
    		
    		pr = gR.findUserProf(u);
    	}
    }

    
    // Pour se déconnecter
    public void logout(){
    	u=null;
    	state=State.UNKNOWN;
    }
    
    
    // Pour récupérer ses enfants élèves
    public List<Eleve> getEleves(){
    	return gU.findEleve_parent(u);
    }
    
    
    
    public User getU() {
		return u;
	}

	public Prof getPr() {
		return pr;
	}

	public void setPr(Prof pr) {
		this.pr = pr;
	}
    
    
}
