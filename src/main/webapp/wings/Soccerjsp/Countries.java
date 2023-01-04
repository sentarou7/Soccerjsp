package to.msn.wings.Soccerjsp;

import java.io.Serializable;

public class Countries implements Serializable {

    private int id;
    private String name;
    private int ranking;
    private String group_name;

    public Countries() { /* コンストラクタ */ }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

   
}
