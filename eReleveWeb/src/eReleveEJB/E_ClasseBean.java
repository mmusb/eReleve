package eReleveEJB;

import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import eReleveEJB.UserFlowRemote.State;


@ManagedBean(name="classeBean")
@ViewScoped
public class E_ClasseBean {
    
	private List<Prof> classeProfs;
	private List<Prof> mod_pr;
	private List<Eleve> eleves;
	private Prof theProf;
	private Module ch_module;
	private List<Module> selectedModules;
	private Boolean okModule= false;
	private Boolean profsAdded= false;
	
	private List<Prof> ecoleProfs;

	
	@ManagedProperty(value="#{employeBean.thisClasse}")
	private Classe thisClasse;
	
	
	@PersistenceContext
	 private transient EntityManager em;
	
	@EJB
   	private GestionReleve g;
    
	
	@ManagedProperty(value="#{employeBean.moduleProfs}")
	private HashMap<Module, List<Prof>> moduleProfs;
	
	
	@ManagedProperty(value="#{employeSession.ef}")
	private EmployeFlow ef;
		
	
	@ManagedProperty(value="#{employeBean.modules}")
	private List<Module> modules;
	

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init(){
		
		theProf = null;
		mod_pr= new ArrayList<Prof>();
		modules= (g.getModules_ec(thisClasse.getEcole().getType()));
		setEleves(ef.getClasseEleves(thisClasse));
		ecoleProfs = g.getEcoleProfs(thisClasse.getEcole());
		moduleProfs= new HashMap<Module, List<Prof>>();
		
		
		if (thisClasse.getProfs().isEmpty()){
			classeProfs = new ArrayList<Prof>();
		}else{
			classeProfs = new ArrayList<Prof>(thisClasse.getProfs());
			//okModule = true;
			//profsAdded = true;
		}
		
	}

	 
	public String checkModules(){
		
		
		Iterator<Module> iter= modules.iterator();
		
		while (iter.hasNext()){
			
			Module m= iter.next();
			List <Prof> pp= g.getModuleProfs(thisClasse.getEcole(), m);
			
			moduleProfs.put(m, pp);
		
		}
	
		return "AddProf_Classe.xhtml";
	}
	
	
	public String showProfs(Module sm){
		
		//classeProfs.add(theProf);
		//ch_module = sm;
		System.out.println("SM: " + sm.getNom());
    	System.out.println("moduleProfs est vide : " + moduleProfs.isEmpty());

		
		mod_pr = moduleProfs.get(sm);
		okModule = true;

		//System.out.println("Nbre d'elements dans mod_pr: " + mod_pr.size());

		//selectedModules.remove(sm);
		//theProf= null;
		
		return "";
	}
	
	
	public String finalizeProfs(){
		
		
		thisClasse.setProfs(new HashSet<Prof>(classeProfs));
		profsAdded = true;
		okModule = true;
		em.persist(thisClasse);
		//classeProfs = new ArrayList<Prof>(thisClasse.getProfs());
		
		return "";
	}
	
	 public EmployeFlow getEf() {
		return ef;
	}

	public void setEf(EmployeFlow ef) {
		this.ef = ef;
	}


	public List<Prof> getClasseProfs() {
		return classeProfs;
	}


	public void setClasseProfs(List<Prof> profs) {
		this.classeProfs = profs;
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


	public Prof getTheProf() {
		return theProf;
	}


	public void setTheProf(Prof theProf) {
		this.theProf = theProf;
	}


	public List<Module> getModules() {
		return modules;
	}


	public void setModules(List<Module> modules) {
		this.modules = modules;
	}


	public List<Module> getSelectedModules() {
		return selectedModules;
	}


	public void setSelectedModules(List<Module> selectedModules) {
		this.selectedModules = selectedModules;
	}


	public Boolean getOkModule() {
		return okModule;
	}


	public void setOkModule(Boolean okModule) {
		this.okModule = okModule;
	}
 	
	
	public HashMap<Module, List<Prof>> getModuleProfs() {
		return moduleProfs;
	}


	public void setModuleProfs(HashMap<Module, List<Prof>> moduleProfs) {
		this.moduleProfs = moduleProfs;
	}


	public Boolean getProfsAdded() {
		return profsAdded;
	}


	public void setProfsAdded(Boolean profsAdded) {
		this.profsAdded = profsAdded;
	}


	public List<Prof> getEcoleProfs() {
		return ecoleProfs;
	}


	public void setEcoleProfs(List<Prof> ecoleProfs) {
		this.ecoleProfs = ecoleProfs;
	}


	public Module getCh_module() {
		return ch_module;
	}


	public void setCh_module(Module ch_module) {
		this.ch_module = ch_module;
	}


	public List<Prof> getMod_pr() {
		return mod_pr;
	}


	public void setMod_pr(List<Prof> mod_pr) {
		this.mod_pr = mod_pr;
	}

}

