package com.pcmoney.model;

public class Lancamento {

	private int id;
	private String tplanc;
	private String tipo;
	private String nome;
	private String descr;
	private Double valor;
	private String venc;
	private String dtpgto;
	private Double juros;
	private Double taxa;
	private int parc;
	private int qtdparc;
	private String maq;
	private String user;
	private String data;
	private String dataAtualizacao;
	private String hora;
	private String horaAtualizacão;
	private int idusu;
	private String obstext;
	private String pago;
		
	//Da tabela de período
	private String dataDedt;
	private String dataAtedt;
	private int idusudt;
	
	//Soma dos valores
	private Double valorTotalFinal;

	
	public Double getValorTotalFinal() {
		return valorTotalFinal;
	}
	public void setValorTotalFinal(Double valorTotalFinal) {
		this.valorTotalFinal = valorTotalFinal;
	}
	public int getId() {
		return id;
	}
	public String getData() {
		return data;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTplanc() {
		return tplanc;
	}
	public void setTplanc(String tplanc) {
		this.tplanc = tplanc;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getVenc() {
		return venc;
	}
	public void setVenc(String venc) {
		this.venc = venc;
	}
	public String getDtpgto() {
		return dtpgto;
	}
	public void setDtpgto(String dtpgto) {
		this.dtpgto = dtpgto;
	}
	public Double getJuros() {
		return juros;
	}
	public void setJuros(Double juros) {
		this.juros = juros;
	}
	public Double getTaxa() {
		return taxa;
	}
	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}
	public int getParc() {
		return parc;
	}
	public void setParc(int parc) {
		this.parc = parc;
	}
	public int getQtdparc() {
		return qtdparc;
	}
	public void setQtdparc(int qtdparc) {
		this.qtdparc = qtdparc;
	}
	public String getMaq() {
		return maq;
	}
	public void setMaq(String maq) {
		this.maq = maq;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(String dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getHoraAtualizacão() {
		return horaAtualizacão;
	}
	public void setHoraAtualizacão(String horaAtualizacão) {
		this.horaAtualizacão = horaAtualizacão;
	}
	public int getIdusu() {
		return idusu;
	}
	public void setIdusu(int idusu) {
		this.idusu = idusu;
	}
	public String getObstext() {
		return obstext;
	}
	public void setObstext(String obstext) {
		this.obstext = obstext;
	}
	public String getDataDedt() {
		return dataDedt;
	}
	public void setDataDedt(String dataDedt) {
		this.dataDedt = dataDedt;
	}
	public String getDataAtedt() {
		return dataAtedt;
	}
	public void setDataAtedt(String dataAtedt) {
		this.dataAtedt = dataAtedt;
	}
	public int getIdusudt() {
		return idusudt;
	}
	public void setIdusudt(int idusudt) {
		this.idusudt = idusudt;
	}
	public String getPago() {
		return pago;
	}
	public void setPago(String pago) {
		this.pago = pago;
	}
	
	
	@Override
	public String toString() {
		return "Lancamento [id=" + id + ", tplanc=" + tplanc + ", tipo=" + tipo + ", nome=" + nome + ", descr=" + descr
				+ ", valor=" + valor + ", venc=" + venc + ", dtpgto=" + dtpgto + ", juros=" + juros + ", taxa=" + taxa
				+ ", parc=" + parc + ", qtdparc=" + qtdparc + ", maq=" + maq + ", user=" + user + ", data=" + data
				+ ", dataAtualizacao=" + dataAtualizacao + ", hora=" + hora + ", horaAtualizacão=" + horaAtualizacão
				+ ", idusu=" + idusu + ", obstext=" + obstext + ", pago=" + pago + ", dataDedt=" + dataDedt
				+ ", dataAtedt=" + dataAtedt + ", idusudt=" + idusudt + ", valorTotalFinal=" + valorTotalFinal + "]";
	}
	
	
	
	
	
	


	

}
