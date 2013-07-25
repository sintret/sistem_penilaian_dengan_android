package com.alcytrite.penilaian;

public class Nilai {

	private int id;
	private int muridId;
	private int matapelajaranId;
	private int nilai;
	
	// constructor
	public Nilai(){};
	
	public Nilai(int id,int muridId, int matapelajaranId, int nilai){
		this.id=id;
		this.muridId=muridId;
		this.matapelajaranId=matapelajaranId;
		this.nilai= nilai;
	}
	
	public Nilai(int muridId, int matapelajaranId, int nilai){
	
		this.muridId=muridId;
		this.matapelajaranId=matapelajaranId;
		this.nilai= nilai;
	}
	
	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return this.id;
	}
	public void setMuridId(int muridId){
		this.muridId=muridId;
	}
	public int getMuridId(){
		return this.muridId;
	}
	public void setMatapelajaranId(int matapelajaranId){
		this.matapelajaranId =matapelajaranId;
	}
	public int getMatapelajaranId(){
		return this.matapelajaranId;
	}
	public void setNilai(int nilai){
		this.nilai=nilai;
	}
	public int getNilai(){
		return this.nilai;
	}
	
	
}
