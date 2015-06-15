package eReleveEJB;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import eReleveEJB.UserFlowRemote.State;


@ManagedBean(name="admin")
@SessionScoped
public class Admin {
    
	private Ecole this_ecole;	

	private String nom;
	private String prenom;
	
	private List<Ecole> ecoles;
	private String ecole_nom;
	private String ecole_type;
	
	private String module_nom;
	private String module_niveau;
		
	private List<AdminUser> admins;
	private List<Module> modules;
	
	private Boolean error= false;
	private String message;
	
	
	
	@EJB
   	private GestionReleve g;
    

	@ManagedProperty(value="#{adminSession.af}")
	private AdminFlow af;
		
	

	@PostConstruct
	public void init(){
		nom= af.getAu().getNom();
		prenom= af.getAu().getPrenom();
	}
	
	
	/********* Ajouts **********/ 
	
	public String addEcole() {
		
		try {
			af.addEcole(ecole_nom, ecole_type);
			error = false;
			ecole_nom= "";
			ecole_type= "";
			//message = "Ecole Ajoutée !";
			return ListEcoles();
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			error= true;
			message = e.getMessage();
			return "admin/AddEcole";
		}
		
	}
	
	public String addModule(){
		
		try {
			af.addModule(module_nom, module_niveau);
			error = false;
			module_nom= "";
			module_niveau= "";
			//message = "Ecole Ajoutée !";
			return getListModules();
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			error= true;
			message = e.getMessage();
			return "admin/AddModule";
		}
		
	}
	
	
	
	public String gererEcole(Ecole this_ec) {
		
		setThis_ecole(this_ec);
		
		return "DetailsEcole?faces-redirect=true";
	}
	
	
	
	/********* Consultations *********/
	
	public String ListEcoles(){
		
		 ecoles= af.getEcoles();
		 
		 return "ListEcoles?faces-redirect=true";
	}
	
	
	public String getListAdmins(){
		
		 setAdmins(af.getAllAdmins());
		 
		 return "ListAdmins?faces-redirect=true";
	}
	
	
	public String getListModules(){
		
		 setModules(af.getAllModules());
		 
		 return "ListModules?faces-redirect=true";
	}
	
	
	
	public String logout(){
		af.logout();
    	FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		return "/Administrator?faces-redirect=true";
	}
	
	
	
	public AdminFlow getAf() {
		return af;
	}

	public void setAf(AdminFlow af) {
		this.af = af;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getEcole_nom() {
		return ecole_nom;
	}


	public void setEcole_nom(String ecole_nom) {
		this.ecole_nom = ecole_nom;
	}

	

	public String getEcole_type() {
		return ecole_type;
	}



	public void setEcole_type(String ecole_type) {
		this.ecole_type = ecole_type;
	}


	public Boolean getError() {
		return error;
	}


	public void setError(Boolean error) {
		this.error = error;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public List<Ecole> getEcoles() {
		return ecoles;
	}
	
	public void setEcoles(List<Ecole> ecoles) {
		this.ecoles = ecoles;
	}


	public Ecole getThis_ecole() {
		return this_ecole;
	}


	public void setThis_ecole(Ecole this_ecole) {
		this.this_ecole = this_ecole;
	}


	public List<AdminUser> getAdmins() {
		return admins;
	}


	public void setAdmins(List<AdminUser> admins) {
		this.admins = admins;
	}


	public List<Module> getModules() {
		return modules;
	}


	public void setModules(List<Module> modules) {
		this.modules = modules;
	}


	public String getModule_nom() {
		return module_nom;
	}


	public void setModule_nom(String module_nom) {
		this.module_nom = module_nom;
	}


	public String getModule_niveau() {
		return module_niveau;
	}


	public void setModule_niveau(String module_niveau) {
		this.module_niveau = module_niveau;
	}
 	
}