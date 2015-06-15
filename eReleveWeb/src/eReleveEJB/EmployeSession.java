package eReleveEJB;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="employeSession")
@SessionScoped
public class EmployeSession {
     
    @EJB
    EmployeFlow ef;
	
	
	public EmployeSession(){		
	}


	public EmployeFlow getEf() {
		return ef;
	}

	public void setEf(EmployeFlow ef) {
		this.ef= ef;
	}
	
}

