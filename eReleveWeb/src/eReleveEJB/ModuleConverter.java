package eReleveEJB;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.wsdl.Output;

import com.sun.java.swing.plaf.windows.resources.windows;
import com.sun.java.swing.plaf.windows.resources.windows_fr;

import eReleveEJB.UserFlowRemote.State;



@ManagedBean(name= "moduleConverter")
@ViewScoped

public class ModuleConverter implements Converter{

			
	    @PersistenceContext
	    private transient EntityManager em;

	    
	    @ManagedProperty(value="#{addProf.listModules}")
		private List<Module> listModules;
	 
	    
	    @Override	    
	    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	    	boolean found= false;
	    	Module m = null;
	    	int id = Integer.parseInt(value);
	    	
	    	Iterator<Module> iter = listModules.iterator();
	    	
	    	while (iter.hasNext() && !found){
	    	m= iter.next();
	    	
	    	if (m.getId() == id){
	    		
	    		found = true;
	    	}
	    	}
	    	
	    	System.out.println("getAsObject() finished and returned: " + m.toString());
    		return m;
	        
	    }


		@Override
		public String getAsString(FacesContext context, UIComponent component, Object value){
	    	
	    	if (value == null){
	    		return null;
	    	}
	    	else{
	    		
	        Module module = new Module();
	        module = (Module) value;
	        System.out.println("getAsString() received object: " + module.getId() + " / " + module.getNom());
	        System.out.println("and returned: " + String.valueOf(module.getId()));
	        return String.valueOf(module.getId());
	        		
	    	}
	    	

	    }

	    @PreDestroy
		public void cleanUp() throws Exception {
		  System.out.println("ModuleConverter is about to be destroyed !");
		}

	    
	    public List<Module> getListModules() {
			return listModules;
		}



		public void setListModules(List<Module> listModules) {
			this.listModules = listModules;
		}

	   
}
