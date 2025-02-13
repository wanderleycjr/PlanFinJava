package com.pcmoney.model;

public class Usuario {
	
	private int id;
	private int privi;
	private String nome;
	private String email;
	private String senha;
	private String ip;
	private String DataCriacao;
	private String DataAtualizacao;
	private String hora;
	private String horaAtualizacao;
	private int status;
	
	//Da tabela de período
	private String dataDeUs;
	private String dataAteUs;
	private int idusuUs;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPrivi() {
		return privi;
	}
	public void setPrivi(int privi) {
		this.privi = privi;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getDataCriacao() {
		return DataCriacao;
	}
	public void setDataCriacao(String dataCriacao) {
		DataCriacao = dataCriacao;
	}
	public String getDataAtualizacao() {
		return DataAtualizacao;
	}
	public void setDataAtualizacao(String dataAtualizacao) {
		DataAtualizacao = dataAtualizacao;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getHoraAtualizacao() {
		return horaAtualizacao;
	}
	public void setHoraAtualizacao(String horaAtualizacao) {
		this.horaAtualizacao = horaAtualizacao;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	//Da tabela de período
	public String getDataDeUs() {
		return dataDeUs;
	}
	public void setDataDeUs(String dataDeUs) {
		this.dataDeUs = dataDeUs;
	}
	public String getDataAteUs() {
		return dataAteUs;
	}
	public void setDataAteUs(String dataAteUs) {
		this.dataAteUs = dataAteUs;
	}
	public int getIdusuUs() {
		return idusuUs;
	}
	public void setIdusuUs(int idusuUs) {
		this.idusuUs = idusuUs;
	}
	
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", privi=" + privi + ", nome=" + nome + ", email=" + email + ", senha=" + senha
				+ ", ip=" + ip + ", DataCriacao=" + DataCriacao + ", DataAtualizacao=" + DataAtualizacao + ", hora="
				+ hora + ", horaAtualizacao=" + horaAtualizacao + ", status=" + status + ", dataDeUs=" + dataDeUs
				+ ", dataAteUs=" + dataAteUs + ", idusuUs=" + idusuUs + ", getId()=" + getId() + ", getPrivi()="
				+ getPrivi() + ", getNome()=" + getNome() + ", getEmail()=" + getEmail() + ", getSenha()=" + getSenha()
				+ ", getIp()=" + getIp() + ", getDataCriacao()=" + getDataCriacao() + ", getDataAtualizacao()="
				+ getDataAtualizacao() + ", getHora()=" + getHora() + ", getHoraAtualizacao()=" + getHoraAtualizacao()
				+ ", getStatus()=" + getStatus() + ", getDataDeUs()=" + getDataDeUs() + ", getDataAteUs()="
				+ getDataAteUs() + ", getIdusuUs()=" + getIdusuUs() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
