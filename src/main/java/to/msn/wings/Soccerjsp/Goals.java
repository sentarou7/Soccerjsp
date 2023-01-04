package to.msn.wings.Soccerjsp;

import java.io.Serializable;

public class Goals implements Serializable {

    private int id;
    private int pairing_id;
    private int player_id;
    private String goal_time;

    public Goals() { /* コンストラクタ */ }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPairing_id() {
		return pairing_id;
	}

	public void setPairing_id(int pairing_id) {
		this.pairing_id = pairing_id;
	}

	public int getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}

	public String getGoal_time() {
		return goal_time;
	}

	public void setGoal_time(String goal_time) {
		this.goal_time = goal_time;
	}

	
   
}