package eReleveEJB;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="profSession")
@SessionScoped
public class ProfSession {
     
    @EJB
    ProfFlow pf;
	
	
	public ProfSession(){		
	}


	public ProfFlow getPf() {
		return pf;
	}

	public void setPf(ProfFlow pf) {
		this.pf= pf;
	}
	
}

