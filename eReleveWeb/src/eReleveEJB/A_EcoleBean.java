package eReleveEJB;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import eReleveEJB.UserFlowRemote.State;


@ManagedBean(name="ecoleBean")
@RequestScoped
public class A_EcoleBean {
    
	//private Ecole this_ecole;	
	private List<Employe> employes;
	
	private String employe_nom;
	private String employe_prenom;
	private String employe_fonction;
	private String employe_mail;
	private String employe_password1;
	private String employe_password2;
	
	private Boolean error=false;
	private String err_message;
	
	private Boolean success=false;
	private String succ_message;
	
	
	@ManagedProperty(value="#{adminSession.af}")
	private AdminFlow af;
	
	
	@ManagedProperty(value="#{admin.this_ecole}")
	private Ecole this_ec;
	

	@PostConstruct
	public void init(){
		// setThis_ecole(null);
	
	}
	
	
	public String clear(){
		employe_nom= null;
		employe_prenom= null;
		employe_fonction= null;
		employe_mail=null;
		employe_password1=null;
		employe_password2=null;
		error=null;
		success=null;
		
		return "AddEmploye";
	}
	
	/********* Ajouts **********/ 
		
	
	public String addEmploye() {
		
		try {
			
			if (employe_password2.equals(employe_password1)){
			if (this_ec != null){
				
			af.addEmploye(employe_nom, employe_prenom, employe_fonction, this_ec, employe_mail, employe_password2);
			
			/*
			 * success= true;
			succ_message= "Employé enregistré avec succès !";
			*/
			
			return getEmployes_ec();
			
			}
			else{ // This_ecole == Null
				error = true;
				err_message = "L'ajout d'employés s'effectue à partir de la page de gestion d'école.";
				return "AddEmploye";
			}
			}
			else{ // Password1 != password2
			error = true;
			err_message = "Mots de passes non identiques";
			return "AddEmploye";
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			error= true;
			err_message = e.getMessage();
			return "AddEmploye";
		}
		
	}
	
	
	 /********* Consultations **********/
    
    public String getEmployes_ec(){
    	
    	setEmployes(af.getEmployes_ecole(this_ec));
    	return "ListEmployes";
    }
	
	
	
	public String logout(){
		af.logout();
		return "Administrator";
	}
	
	
	
	public AdminFlow getAf() {
		return af;
	}

	public void setAf(AdminFlow af) {
		this.af = af;
	}

	
	
	public String getEmploye_nom() {
		return employe_nom;
	}


	
	public void setEmploye_nom(String employe_nom) {
		this.employe_nom = employe_nom;
	}


	public String getEmploye_prenom() {
		return employe_prenom;
	}



	public void setEmploye_prenom(String employe_prenom) {
		this.employe_prenom = employe_prenom;
	}



	public String getEmploye_fonction() {
		return employe_fonction;
	}


	public void setEmploye_fonction(String employe_fonction) {
		this.employe_fonction = employe_fonction;
	}


	public Boolean getError() {
		return error;
	}


	public void setError(Boolean error) {
		this.error = error;
	}


	
	public Ecole getThis_ec() {
		return this_ec;
	}


	public void setThis_ec(Ecole this_ec) {
		this.this_ec = this_ec;
	}


	public String getEmploye_mail() {
		return employe_mail;
	}


	public void setEmploye_mail(String employe_mail) {
		this.employe_mail = employe_mail;
	}


	public String getEmploye_password1() {
		return employe_password1;
	}


	public void setEmploye_password1(String employe_password) {
		this.employe_password1 = employe_password;
	}


	public String getEmploye_password2() {
		return employe_password2;
	}


	public void setEmploye_password2(String employe_password2) {
		this.employe_password2 = employe_password2;
	}


	public List<Employe> getEmployes() {
		return employes;
	}


	public void setEmployes(List<Employe> employes) {
		this.employes = employes;
	}


	public String getErr_message() {
		return err_message;
	}


	public void setErr_message(String err_message) {
		this.err_message = err_message;
	}


	public Boolean getSuccess() {
		return success;
	}


	public void setSuccess(Boolean success) {
		this.success = success;
	}


	public String getSucc_message() {
		return succ_message;
	}


	public void setSucc_message(String succ_message) {
		this.succ_message = succ_message;
	}


	
}