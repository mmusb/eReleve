package eReleveEJB;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
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



@ManagedBean(name= "profConverter")
@ViewScoped

public class ProfConverter implements Converter{

			
	   
	    @ManagedProperty(value="#{classeBean.moduleProfs}")
	    private HashMap<Module, List<Prof>> moduleProfs;
	 
	    private Collection<List<Prof>> listValues;
	    private List<Prof> listProfs;
	    
	    
	    @Override	    
	    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	    	
	    	System.out.println("ICI ProfConverter: Nmbre d'elements de la collection: " + moduleProfs.size());
	    	
	    	listValues= moduleProfs.values();
	    	
	    			
	    	boolean found= false;
	    	Prof p = null;
	    	
	    	int id = Integer.parseInt(value);
	    	
	    	Iterator<List<Prof>> iterValues = listValues.iterator();
	    	
	    	// Première boucle
	    	while (iterValues.hasNext() && !found){
	    		
	    	listProfs= iterValues.next();
	    	
	    	Iterator<Prof> it = listProfs.iterator();
	    	
	    	// Deuxième boucle
	    	while (it.hasNext() && !found){
	    	p= it.next();
	    	
	    	if (p.getId() == id){
	    		
	    		found = true;
	    	}
	    	}
	    	}
	    	
	    	System.out.println("getAsObject() finished and returned: " + p.toString() + "  dont le Nom est: " + p.getNom());
    		return p;
	        
	    }


		public Collection<List<Prof>> getListValues() {
			return listValues;
		}


		public void setListValues(Collection<List<Prof>> listValues) {
			this.listValues = listValues;
		}


		@Override
		public String getAsString(FacesContext context, UIComponent component, Object value){
	    	
	    	if (value == null){
	    		return null;
	    	}
	    	else{
	    		
	        Prof prof = new Prof();
	        prof = (Prof) value;
	        System.out.println("getAsString() received object: " + prof.getId() + " / " + prof.getNom());
	        System.out.println("and returned: " + String.valueOf(prof.getId()));
	        return String.valueOf(prof.getId());
	        		
	    	}
	    	

	    }

	    @PreDestroy
		public void cleanUp() throws Exception {
		  System.out.println("ModuleConverter is about to be destroyed !");
		}


		public HashMap<Module, List<Prof>> getModuleProfs() {
			return moduleProfs;
		}


		public void setModuleProfs(HashMap<Module, List<Prof>> moduleProfs) {
			this.moduleProfs = moduleProfs;
		}


		public List<Prof> getListProfs() {
			return listProfs;
		}


		public void setListProfs(List<Prof> listProfs) {
			this.listProfs = listProfs;
		}

	    
	 	   
}
