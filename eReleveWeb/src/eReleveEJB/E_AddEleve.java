package eReleveEJB;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import eReleveEJB.UserFlowRemote.State;

@ManagedBean(name="addEleve")
@ViewScoped
public class E_AddEleve {
	private String nom;
	private String prenom;
	private Date dateNaissance;
	private Classe classe;
	private Boolean error;
	private String errormessage;
	private Boolean success;
	private String successmessage;
	private List<Classe> classes;

	
	@EJB
	GestionReleve gR;
	
	@ManagedProperty(value="#{employeBean.thisEcole}")
	private Ecole this_ec;
	

	@PostConstruct
	public void init(){
	
	
		setClasses(gR.getEcoleClasses(this_ec));
	}
	

	public String addEtudiant(){
		
		try {
		
			if (this_ec != null){
			
			gR.addEleve(nom, prenom, dateNaissance, classe);
			error = false;
			nom= "";
			prenom= "";
			dateNaissance= null;
			classe = null;
			
			setSuccess(true);
			setSuccessmessage("Etudiant enregistré !");
			return "AddEtudiant";
			}
			else{
				error = true;
				errormessage = "Ecole inconnue: erreur de transmission d'information.";
				return "AddEtudiant";
			}
				
								
			} catch (Exception e) {
			// TODO Auto-generated catch block
			error= true;
			errormessage = e.getMessage();
			return "AddEtudiant";
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
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
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
	public Classe getClasse() {
		return classe;
	}
	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public List<Classe> getClasses() {
		return classes;
	}

	public void setClasses(List<Classe> classes) {
		this.classes = classes;
	}

}
