package eReleveEJB;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import eReleveEJB.AdminFlowRemote.State;

@Remote
public interface AdminFlowRemote {
	public enum State{UNKNOWN, REGISTERED, LOGGED_IN, LOGIN_FAILURE, USERNAME_USED};
	public State getState();
    public void register(String nom, String prenom, String email, String password);
    public void login(String email, String password);
    public void addEcole(String nom, String type) throws Exception;
    public List<Ecole> getEcoles();
    public void logout();
    public AdminUser getAu();
  
}
