package eReleveEJB;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import eReleveEJB.UserFlowRemote.State;


@ManagedBean(name="employeLoginForm")
@RequestScoped
public class EmployeLoginForm {
    private String username;
    private String password;
    private boolean error;
    private int EMPLOYE_PROFILE= 5; 
    

	@EJB
   	private GestionReleve g;
    
	@ManagedProperty(value="#{employeSession.ef}")
	private EmployeFlow ef;
		
	
	
    public EmployeLoginForm(){
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
		ef.login(username, password, EMPLOYE_PROFILE);
		if(ef.getState()!= eReleveEJB.EmployeFlowRemote.State.LOGGED_IN){
			error=true;
			return "LoginEmploye";
		}
		error=false;
		return "AccueilEmploye?faces-redirect=true";
	}
	
	public boolean isError() {
		return error;
	}


	public EmployeFlow getEf() {
		return ef;
	}


	public void setEf(EmployeFlow ef) {
		this.ef = ef;
	}

	
}

