package eReleveEJB;

import javax.ejb.Remote;

import eReleveEJB.EmployeFlowRemote.State;

@Remote
public interface EmployeFlowRemote {
	public enum State{UNKNOWN, REGISTERED, LOGGED_IN, LOGIN_FAILURE, USERNAME_USED};
	public State getState();
    public void login(String email, String password, int profile);
	public void logout();
	public User getU();
	public Employe getEpl();

}
