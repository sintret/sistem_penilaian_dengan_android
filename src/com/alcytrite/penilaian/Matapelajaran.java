package com.alcytrite.penilaian;

public class Matapelajaran {

	private int id;
	private String subject;
	
	public Matapelajaran(){};
	
	public Matapelajaran(int id, String s){
		this.id=id;
		this.subject=s;
	}
	
	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return this.id;
	}
	public String getIdtoString(){
		return ""+this.id;
	}
	public void setSubject(String subject){
		this.subject=subject;
	}
	public String getSubject(){
		return this.subject;
	}
	
}
