package eReleveEJB;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import eReleveEJB.UserFlowRemote.State;


@ManagedBean(name="profBean")
@RequestScoped
public class ProfBean {
    
	private String nom;
	private String prenom;
	
	
	@EJB
   	private GestionReleve g;
    

	@ManagedProperty(value="#{profSession.pf}")
	private ProfFlow pf;
		

	@PostConstruct
	public void init(){
		nom= pf.getU().getNom();
		prenom= pf.getU().getPrenom();
	}
	
	
	public String logout(){
		pf.logout();
    	FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return "/index?faces-redirect=true";
	}
	
	
	public ProfFlow getPf() {
		return pf;
	}

	public void setPf(ProfFlow pf) {
		this.pf = pf;
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
 	
}

