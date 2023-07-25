package com.banque;

public class Subscribe {

	private String user_name;
	private String user_password;
	private String last_name;
	private String first_name;
	private Byte sex;
	
	private String type;
	private Double montant;
	
	
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public Byte getSex() {
		return sex;
	}
	public void setSex(Byte sex) {
		this.sex = sex;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getMontant() {
		return montant;
	}
	public void setMontant(Double montant) {
		this.montant = montant;
	}
	@Override
	public String toString() {
		return "Subscribe [user_name=" + user_name + ", user_password=" + user_password + ", last_name=" + last_name
				+ ", first_name=" + first_name + ", sex=" + sex + ", type=" + type + ", montant=" + montant + "]";
	}
	
	
	
	
	
}
