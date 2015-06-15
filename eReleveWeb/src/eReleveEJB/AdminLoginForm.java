package eReleveEJB;


	import javax.annotation.PostConstruct;
	import javax.ejb.EJB;
	import javax.faces.bean.ManagedBean;
	import javax.faces.bean.ManagedProperty;
	import javax.faces.bean.RequestScoped;

	import eReleveEJB.AdminFlowRemote.State;

	
	@ManagedBean(name="adminloginForm")
	@RequestScoped
	public class AdminLoginForm {

	    private String username;
	    private String password;
	    private boolean error;
	    
	    

		@EJB
	   	private GestionReleve g;
	    
		@ManagedProperty(value="#{adminSession.af}")
		private AdminFlow af;
				
		
	    public AdminLoginForm(){
	    	error=false;
	    }
	    
	    
	    @PostConstruct
		public void init(){
		}
	    
	    
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	    
		public String verifier(){
			af.login(username, password);
			if(af.getState()!=State.LOGGED_IN){
				error=true;
				return "Administrator";
			}
			error=false;
			return "admin/Admin_accueil?faces-redirect=true";
		}
		
		public boolean isError() {
			return error;
		}


		public AdminFlow getAf() {
			return af;
		}


		public void setAf(AdminFlow af) {
			this.af = af;
		}

		
	}

