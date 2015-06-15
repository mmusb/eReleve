package eReleveEJB;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import eReleveEJB.UserFlowRemote.State;

@ManagedBean(name="registerForm")
@RequestScoped
public class RegisterForm {
	private String nom;
	private String prenom;
	private String username;
	private String password1;
	private String password2;
	private Boolean error;
	private String errormessage;
	
	
	@ManagedProperty(value="#{userSession}")
	private UserSession uss;
	
	
	private UserFlow uf;
	
	
	public String register(){
		if(password1.equals(password2)){
			
		if (uss != null){
			uf= uss.getUf();
			}
			
			uf.register(nom, prenom, username, password1);
			if(uf.getState()==State.USERNAME_USED){
				errormessage="Le pseudo est deja pris";
				error=true;
				return "Register";
			}else{
				error=false;
				return "Perso";
			}
			
		}else{
			errormessage="Les mots de passe sont differents";
			error=true;
			return "Register";
		}
	}
	
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public Boolean getError() {
		return error;
	}
	
	
	public UserSession getUss() {
		return uss;
	}

	public void setUss(UserSession uss) {
		this.uss = uss;
	}
	
	
	public UserFlow getUf() {
		return uf;
	}

	public void setUf(UserFlow uf) {
		this.uf = uf;
	}

	public void setError(Boolean error) {
		this.error = error;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}
	
	

}
