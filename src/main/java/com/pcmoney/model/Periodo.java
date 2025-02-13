package com.pcmoney.model;

public class Periodo {
	
	private String dataDe;
	private String dataAte;
	private int idusu;
	
	
	public String getDataDe() {
		return dataDe;
	}
	public void setDataDe(String dataDe) {
		this.dataDe = dataDe;
	}
	public String getDataAte() {
		return dataAte;
	}
	public void setDataAte(String dataAte) {
		this.dataAte = dataAte;
	}
	public int getIdusu() {
		return idusu;
	}
	public void setIdusu(int idusu) {
		this.idusu = idusu;
	}
	
	
	@Override
	public String toString() {
		return "Periodo [dataDe=" + dataDe + ", dataAte=" + dataAte + ", idusu=" + idusu + ", getDataDe()="
				+ getDataDe() + ", getDataAte()=" + getDataAte() + ", getIdusu()=" + getIdusu() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	

}
