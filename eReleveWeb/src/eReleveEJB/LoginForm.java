package eReleveEJB;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import eReleveEJB.UserFlowRemote.State;

@ManagedBean(name="loginForm")
@RequestScoped
public class LoginForm {
    private String username;
    private String password;
    private boolean error;
    private int PARENT_PROFILE= 1; 

    
	@EJB
   	private GestionReleve g;
    
	
	@ManagedProperty(value="#{userSession.uf}")
	private UserFlow uf;
		
	
	
    public LoginForm(){
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
		uf.login(username, password, PARENT_PROFILE);
		if(uf.getState()!=State.LOGGED_IN){
			error=true;
			return "LoginUser";
		}
		error=false;
		return "AccueilUser?faces-redirect=true";
	}
	
	public boolean isError() {
		return error;
	}


	public UserFlow getUf() {
		return uf;
	}


	public void setUf(UserFlow uf) {
		this.uf = uf;
	}

	
}

