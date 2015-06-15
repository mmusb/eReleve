package eReleveEJB;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.faces.context.FacesContext;


/**
 * Session Bean implementation class UserFlow
 */

@Stateful
@LocalBean
public class EmployeFlow implements EmployeFlowRemote {

	
	private State state;
	private User u;
	private Employe epl;
	
	@EJB
	GestionReleve gR;
	
	
	@EJB
	GestionUser gU;
	
	
	
    /**
     * Default constructor. 
     */
	
    public EmployeFlow() {
        state=State.UNKNOWN;
        u=null;
    	epl=null;

    }
	
    public State getState() {
		return state;
	}
	      	
    
    // Pour se connecter
    public void login(String email, String password, int profile){
    	u=gU.checkLogin(email, password, profile);
    	    	
    	if(u==null){
    		state=State.LOGIN_FAILURE;
    	}else{
    		state=State.LOGGED_IN;
        	setEpl(gR.findUserEmploye(u));
    	}
    }

    
    // Pour se déconnecter
    public void logout(){
    	u=null;
    	epl=null;
    	state=State.UNKNOWN;
    }
    
    
    
    /***** Consultations ******/
    
    public List<Employe> getEcoleEmployes (Ecole e){
    	
    	return gR.getEmployes_ec(e);
    }
    
    
    public List<Prof> getEcoleProfs (Ecole e){
    	
    	return gR.getEcoleProfs(e);
    }
    
       
    public List<Classe> getEcoleClasses (Ecole e){
    	
    	return gR.getEcoleClasses(e);
    }
    
    
    public List<Eleve> getEcoleEleves (Ecole e){
    	
    	return gR.getEcoleEleves(e);
    }
    
    
    public List<Eleve> getClasseEleves (Classe c){
    	
    	return gR.getClasseEleves(c);
    }
    
    
    public User getU() {
		return u;
	}

	public Employe getEpl() {
		return epl;
	}

	public void setEpl(Employe epl) {
		this.epl = epl;
	}
    
    
}
