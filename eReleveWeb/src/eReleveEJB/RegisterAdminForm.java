package eReleveEJB;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import eReleveEJB.AdminFlowRemote.State;

@ManagedBean(name="registerAdminForm")
@RequestScoped
public class RegisterAdminForm {
	private String nomA;
	private String prenomA;
	private String usernameA;
	private String password1A;
	private String password2A;
	private Boolean error;
	private String errormessage;
	
	
	@ManagedProperty(value="#{adminSession}")
	private AdminSession ass;
	
	
	private AdminFlow af;
	
	
	public String register(){
		if(password1A.equals(password2A)){
	
		if (ass != null){
			af= ass.getAf();
			}
		
			af.register(nomA, prenomA, usernameA, password1A);
			if(af.getState()==State.USERNAME_USED){
				errormessage="Ce pseudo est deja utilisé";
				error=true;
				return "RegisterAdmin";
			}else{
				error=false;
				return "Admin_accueil?faces-redirect=true";
			}
			
		}else{
			errormessage="Les mots de passe sont differents";
			error=true;
			return "RegisterAdmin";
		}
	}
	
	
	
	public String getNomA() {
		return nomA;
	}
	public void setNomA(String nom) {
		this.nomA = nom;
	}
	public String getPrenomA() {
		return prenomA;
	}
	public void setPrenomA(String prenom) {
		this.prenomA = prenom;
	}
	public String getUsernameA() {
		return usernameA;
	}
	public void setUsernameA(String username) {
		this.usernameA = username;
	}
	public String getPassword1A() {
		return password1A;
	}
	public void setPassword1A(String password1) {
		this.password1A = password1;
	}
	public String getPassword2A() {
		return password2A;
	}
	public void setPassword2A(String password2) {
		this.password2A = password2;
	}
	public Boolean getError() {
		return error;
	}
	
	
	public AdminSession getAss() {
		return ass;
	}

	public void setAss(AdminSession ass) {
		this.ass = ass;
	}
	
	
	public AdminFlow getAf() {
		return af;
	}

	public void setAf(AdminFlow af) {
		this.af = af;
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
