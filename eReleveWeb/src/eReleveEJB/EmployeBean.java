package eReleveEJB;

import java.lang.ProcessBuilder.Redirect;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import eReleveEJB.UserFlowRemote.State;


@ManagedBean(name="employeBean")
@SessionScoped
public class EmployeBean {
    
	private String nom;
	private String prenom;
	private String fullName;
	private Ecole thisEcole;
	private List<Employe> employes; 
	private List<Prof> profs;
	private List<Classe> classes;
	private List<Eleve> eleves;
	private Classe thisClasse;
	private List<Module> modules;
	private HashMap<Module, List<Prof>> moduleProfs;
	
	
	@PersistenceContext
	 private transient EntityManager em;
	
	@EJB
   	private GestionReleve g;
    

	@ManagedProperty(value="#{employeSession.ef}")
	private EmployeFlow ef;
		

	@PostConstruct
	public void init(){
		nom= ef.getU().getNom();
		prenom= ef.getU().getPrenom();
		fullName=prenom;
		fullName= fullName.concat(" ").concat(nom);
			
		setThisEcole(ef.getEpl().getEcole());
	}

	
	
	/***** Ajouts ******/
	
	
	
	/***** Consultations ******/
	
	 public String ListEmployes(){
	    	
		 employes= ef.getEcoleEmployes(ef.getEpl().getEcole());
		 return "ListEmployes?faces-redirect=true";
		 
	    }

	 
	 public String ListProfs(){
	    	
		 profs = ef.getEcoleProfs(ef.getEpl().getEcole());
		 return "ListProfs?faces-redirect=true";
		 
	    }
	 
	 
	 public String ListClasses(){
	    	
		 classes = ef.getEcoleClasses(ef.getEpl().getEcole());
		 return "ListClasses?faces-redirect=true";
		 
	    }
	 
	 
	 public String ListEleves(){
	    	
		 eleves = ef.getEcoleEleves(ef.getEpl().getEcole());
		 return "ListEtudiants?faces-redirect=true";
		 
	    }
	 
	  
	 public String gererClasse(Classe classe){
	    
		 setThisClasse(classe);
		 return "DetailsClasse?faces-redirect=true";
		 
	    }
	  
	 
	 
	 
	public String logout(){
		ef.logout();
    	FacesContext.getCurrentInstance().getExternalContext().invalidateSession(); /*** Pour effacer le bean g�r� Sessionscoped couant � la d�connex***/
    	
		return "/index?faces-redirect=true"; 	// Pour rediriger la page ET l'URL � la bonne adresse 
	}
	
	
	public EmployeFlow getEf() {
		return ef;
	}

	public void setEf(EmployeFlow ef) {
		this.ef = ef;
	}

	
		public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public List<Employe> getEmployes() {
		return employes;
	}


	public void setEmployes(List<Employe> employes) {
		this.employes = employes;
	}


	public Ecole getThisEcole() {
		return thisEcole;
	}


	public void setThisEcole(Ecole thisEcole) {
		this.thisEcole = thisEcole;
	}


	public List<Prof> getProfs() {
		return profs;
	}


	public void setProfs(List<Prof> profs) {
		this.profs = profs;
	}


	public List<Classe> getClasses() {
		return classes;
	}


	public void setClasses(List<Classe> classes) {
		this.classes = classes;
	}


	public List<Eleve> getEleves() {
		return eleves;
	}


	public void setEleves(List<Eleve> eleves) {
		this.eleves = eleves;
	}



	public Classe getThisClasse() {
		return thisClasse;
	}



	public void setThisClasse(Classe thisClasse) {
		this.thisClasse = thisClasse;
	}



	public HashMap<Module, List<Prof>> getModuleProfs() {
		return moduleProfs;
	}



	public void setModuleProfs(HashMap<Module, List<Prof>> moduleProfs) {
		this.moduleProfs = moduleProfs;
	}



	public List<Module> getModules() {
		return modules;
	}



	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
 	
}

