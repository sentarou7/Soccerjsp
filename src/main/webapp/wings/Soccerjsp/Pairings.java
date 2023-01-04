package to.msn.wings.Soccerjsp;

import java.io.Serializable;

public class Pairings implements Serializable {

    private int id;
    private String kickoff;
    private int my_country_id;
    private int enemy_country_id;

    public Pairings() { /* コンストラクタ */ }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKickoff() {
		return kickoff;
	}

	public void setKickoff(String kickoff) {
		this.kickoff = kickoff;
	}

	public int getMy_country_id() {
		return my_country_id;
	}

	public void setMy_country_id(int my_country_id) {
		this.my_country_id = my_country_id;
	}

	public int getEnemy_country_id() {
		return enemy_country_id;
	}

	public void setEnemy_country_id(int enemy_country_id) {
		this.enemy_country_id = enemy_country_id;
	}

	
   
}