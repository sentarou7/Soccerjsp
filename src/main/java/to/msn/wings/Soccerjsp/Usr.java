package to.msn.wings.Soccerjsp;

import java.io.Serializable;

public class Usr implements Serializable{
	private String usr_name;
	private String usr_id;
	
	public Usr(String id,String name) {
		usr_id=id;
		usr_name=name;
	}

	public String getUsr_name() {
		return usr_name;
	}

	public void setUsr_name(String usr_name) {
		this.usr_name = usr_name;
	}

	public String getUsr_id() {
		return usr_id;
	}

	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}
	
}