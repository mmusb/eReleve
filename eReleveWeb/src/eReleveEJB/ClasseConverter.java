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



@ManagedBean(name= "classeConverter")
@ViewScoped

public class ClasseConverter implements Converter{

			
	   
	    @ManagedProperty(value="#{addEleve.classes}")
		private List<Classe> listClasses;
	 
	    
	    @Override	    
	    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	    	boolean found= false;
	    	Classe m = null;
	    	int id = Integer.parseInt(value);
	    	
	    	Iterator<Classe> iter = listClasses.iterator();
	    	
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
	    		
	        Classe classe = new Classe();
	        classe = (Classe) value;
	        System.out.println("getAsString() received object: " + classe.getId() + " / " + classe.getNom());
	        System.out.println("and returned: " + String.valueOf(classe.getId()));
	        return String.valueOf(classe.getId());
	        		
	    	}
	    	

	    }

	    @PreDestroy
		public void cleanUp() throws Exception {
		  System.out.println("ModuleConverter is about to be destroyed !");
		}

	    
	    public List<Classe> getListClasses() {
			return listClasses;
		}



		public void setListClasses(List<Classe> listClasses) {
			this.listClasses = listClasses;
		}

	   
}
