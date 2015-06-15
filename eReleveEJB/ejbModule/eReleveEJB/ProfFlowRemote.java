package eReleveEJB;

import javax.ejb.Remote;

import eReleveEJB.ProfFlowRemote.State;

@Remote
public interface ProfFlowRemote {
	public enum State{UNKNOWN, REGISTERED, LOGGED_IN, LOGIN_FAILURE, USERNAME_USED};
	public State getState();
    public void register(String nom, String prenom, String email, String password);
    public void login(String email, String password, int profile);
	public void logout();
	public User getU();
}
