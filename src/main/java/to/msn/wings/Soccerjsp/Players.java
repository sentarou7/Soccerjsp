package to.msn.wings.Soccerjsp;

import java.io.Serializable;

public class Players implements Serializable {

    private int id;
    private int country_id;
    private int uniform_num;
    private String position;
    private String name;
    private String club;
    private String birth;
    private int height;
    private int weight;

    public Players() { /* コンストラクタ */ }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCountry_id() {
		return country_id;
	}

	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}

	public int getUniform_num() {
		return uniform_num;
	}

	public void setUniform_num(int uniform_num) {
		this.uniform_num = uniform_num;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClub() {
		return club;
	}

	public void setClub(String club) {
		this.club = club;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	
   
}
