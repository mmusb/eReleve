package eReleveEJB;

import java.util.Iterator;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



@ManagedBean(name= "converterModule")
@ViewScoped

public class ConverterModule implements Converter{

			
	    @PersistenceContext
	    private transient EntityManager em;

	    
	    @ManagedProperty(value="#{classeBean.modules}")
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
