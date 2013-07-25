package com.alcytrite.penilaian;

public class Murid {

	private int id;
	private String name;
	private String address;
	
	// constructor 
	public  Murid(){};
	
	public Murid(int id,String name, String address) {
		this.id=id;
		this.name=name;
		this.address=address;
	}
	
	public Murid(String name, String address) {
		this.name=name;
		this.address=address;
	}
	
	//end constructor
	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return this.id;
	}
	public String getIdtoString(){
		return ""+this.id;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setAddress(String address){
		this.address=address;
	}
	
	public String getAddress(){
		return this.address;
	}
}
