package eReleveEJB;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="addProf")
@ViewScoped

public class E_AddProf {
	private String nom;
	private String prenom;
	private String email;
	private String password1;
	private String password2;
	private Module module;
	private Boolean error;
	private String errormessage;
	private Boolean success;
	private String successmessage;
	private List<Module> listModules;
	
	
	@EJB
	GestionReleve gR;
	
	
	@ManagedProperty(value="#{employeBean.thisEcole}")
	private Ecole this_ec;
	
	
	@PostConstruct
	public void init(){
	
		String typeEcole = this_ec.getType();
		
		listModules= gR.getModules_ec(typeEcole);
	}
		
	
	public String addProf(){
		
		try {
			
			if (password2.equals(password1)){
				if (this_ec != null){
					
					
			gR.addProf(nom, prenom, this_ec, module, email, password1);	
			error = false;
			nom= "";
			prenom= "";
			email= "";
			password1= null;
			password2= null;
			//message = "Ecole Ajoutée !";

			System.out.println("Prof ajouté avec succès !");

			setSuccess(true);
			setSuccessmessage("Enseignant enregistré !");
			System.out.println("... Boolean et message remplis !");
			return null;
			}
			else{
				error = true;
				errormessage = "Ecole inconnue: erreur de transmission d'information.";
				return "AddProf";
			}
				
			}
			else{ // Password1 != password2
				error = true;
				errormessage = "Mots de passes non identiques";
				return null;
				}
			
				} catch (Exception e) {
			// TODO Auto-generated catch block
			error= true;
			errormessage = e.getMessage();
			return null;
		}
		
	}
	
	
	 @PreDestroy
		public void cleanUp() throws Exception {
		  System.out.println("addProf bean is about to be destroyed !");
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
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}


	public List<Module> getlistModules() {
		return listModules;
	}


	public void setlistModules(List<Module> modules) {
		this.listModules = modules;
	}
	
}
