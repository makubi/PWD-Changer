package at.makubi.wicket;

import java.io.Serializable;

public class PasswordObject implements Serializable {
	
	private String username;
	private String oldpw;
	private String newpw1;
	private String newpw2;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getOldpw() {
		return oldpw;
	}
	public void setOldpw(String oldpw) {
		this.oldpw = oldpw;
	}
	public String getNewpw1() {
		return newpw1;
	}
	public void setNewpw1(String newpw1) {
		this.newpw1 = newpw1;
	}
	public String getNewpw2() {
		return newpw2;
	}
	public void setNewpw2(String newpw2) {
		this.newpw2 = newpw2;
	}
}
