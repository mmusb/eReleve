package eReleveEJB;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import eReleveEJB.UserFlowRemote.State;

@ManagedBean(name="addClasse")
@RequestScoped
public class E_AddClasse {
	private String nom;
	private String branche;
	private Boolean error;
	private String errormessage;
	private Boolean success;
	private String successmessage;
	
	
	@ManagedProperty(value="#{employeBean.thisEcole}")
	private Ecole this_ec;

	
	
	@EJB
	GestionReleve gR;
	
	
	public String addclasse(){
		
		try {
			if (this_ec != null){
			gR.addClasse(nom, branche, this_ec);
			error = false;
			nom= "";
			branche= "";
			//message = "Ecole Ajoutée !";
			success= true;
			successmessage= "Classe enregistrée !";
			return "AddClasse";
			}
			else{
				error= true;
				errormessage = "Ecole inconnue: erreur de transmission d'information.";
				return "AddClasse";

			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			error= true;
			errormessage = e.getMessage();
			return "AddClasse";
		}
		
	}
	
	
	
	public Ecole getThis_ec() {
		return this_ec;
	}

	public void setThis_ec(Ecole this_ec) {
		this.this_ec = this_ec;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getBranche() {
		return branche;
	}



	public void setBranche(String branche) {
		this.branche = branche;
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
	
}
