package eReleveEJB;
import java.math.BigDecimal;
import java.util.List;


import javax.ejb.Remote;

@Remote
public interface UserFlowRemote {
	public enum State{UNKNOWN, REGISTERED, LOGGED_IN, LOGIN_FAILURE, USERNAME_USED};
	public State getState();
    public void register(String nom, String prenom, String email, String password);
    public void login(String email, String password, int profile);
	public void logout();
	public User getU();
}
