package eReleveEJB;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import eReleveEJB.UserFlowRemote.State;


@ManagedBean(name="userBean")
@RequestScoped
public class UserBean {
    
	private String nom;
	private String prenom;
	
	
	@EJB
   	private GestionReleve g;
    

	@ManagedProperty(value="#{userSession.uf}")
	private UserFlow uf;
		

	@PostConstruct
	public void init(){
		nom= uf.getU().getNom();
		prenom= uf.getU().getPrenom();
	}
	
	
	public String logout(){
		uf.logout();
    	FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return "/index?faces-redirect=true";
	}
	
	
	public UserFlow getUf() {
		return uf;
	}

	public void setUf(UserFlow uf) {
		this.uf = uf;
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

