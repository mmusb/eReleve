package eReleveEJB;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="adminSession")
@SessionScoped
public class AdminSession {
     
    @EJB
    AdminFlow af;
	
	
	public AdminSession(){	
	}


	public AdminFlow getAf() {
		return af;
	}

	public void setUf(AdminFlow af) {
		this.af= af;
	}
	
}
