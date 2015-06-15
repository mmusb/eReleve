package eReleveEJB;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import eReleveEJB.UserFlowRemote.State;

@ManagedBean(name="profLoginForm")
@RequestScoped
public class ProfLoginForm {
    private String username;
    private String password;
    private boolean error;
    private int PROF_PROFILE= 3; 
    

	@EJB
   	private GestionReleve g;
    
	@ManagedProperty(value="#{profSession.pf}")
	private ProfFlow pf;
		
	
	
    public ProfLoginForm(){
    	error=false;
    }
    
    
    @PostConstruct
	public void init(){
	}
    
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
	public String verifier(){
		pf.login(username, password, PROF_PROFILE);
		if(pf.getState()!= eReleveEJB.ProfFlowRemote.State.LOGGED_IN){
			error=true;
			return "LoginProf";
		}
		error=false;
		return "AccueilProf?faces-redirect=true";
	}
	
	public boolean isError() {
		return error;
	}


	public ProfFlow getPf() {
		return pf;
	}


	public void setPf(ProfFlow pf) {
		this.pf = pf;
	}

	
}

