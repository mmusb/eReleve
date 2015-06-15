package eReleveEJB;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="userSession")
@SessionScoped
public class UserSession {
     
    @EJB
    UserFlow uf;
	
	
	public UserSession(){		
	}


	public UserFlow getUf() {
		return uf;
	}

	public void setUf(UserFlow uf) {
		this.uf= uf;
	}
	
}
