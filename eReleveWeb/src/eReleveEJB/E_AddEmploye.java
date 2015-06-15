package eReleveEJB;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import eReleveEJB.UserFlowRemote.State;

@ManagedBean(name="addEmploye")
@RequestScoped
public class E_AddEmploye {
	private String nom;
	private String prenom;
	private String fonction;
	private String email;
	private String password1;
	private String password2;
	private Boolean error;
	private String errormessage;
	private Boolean success;
	private String successmessage;
	

	
	@EJB
	GestionReleve gR;
	
	@ManagedProperty(value="#{employeBean.thisEcole}")
	private Ecole this_ec;
	
		
	
		public String addEmploye(){
			
			try {
			
				if (password2.equals(password1)){
					if (this_ec != null){
					
				gR.addEmploye(nom, prenom, fonction, this_ec, email, password2);
				error = false;
				nom= "";
				prenom= "";
				email= "";
				fonction = "";
				password1= null;
				password2= null;
				//message = "Ecole Ajoutée !";
				setSuccess(true);
				setSuccessmessage("Employe enregistré !");
				return "AddEmploye";
				}
				else{
					error = true;
					errormessage = "Ecole inconnue: erreur de transmission d'information.";
					return "AddEmploye";
				}
					
				}
				else{ // Password1 != password2
					error = true;
					errormessage = "Mots de passes non identiques";
					return "AddEmploye";
					}
				
					} catch (Exception e) {
				// TODO Auto-generated catch block
				error= true;
				errormessage = e.getMessage();
				return "AddEmploye";
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
	public String getFonction() {
		return fonction;
	}


	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
	
	public String getPassword1() {
		return password1;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getSuccessmessage() {
		return successmessage;
	}

	public void setSuccessmessage(String successmessage) {
		this.successmessage = successmessage;
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
	
	public void setError(Boolean error) {
		this.error = error;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}
	public Ecole getThis_ec() {
		return this_ec;
	}
	public void setThis_ec(Ecole this_ec) {
		this.this_ec = this_ec;
	}

}
