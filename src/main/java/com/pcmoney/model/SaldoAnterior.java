package com.pcmoney.model;

public class SaldoAnterior {
	
	private Double valorTotalAnterior;

	public Double getValorTotalAnterior() {
		return valorTotalAnterior;
	}

	public void setValorTotalAnterior(Double valorTotalAnterior) {
		this.valorTotalAnterior = valorTotalAnterior;
	}

	@Override
	public String toString() {
		return "SaldoAnterior [valorTotalAnterior=" + valorTotalAnterior + ", getValorTotalAnterior()="
				+ getValorTotalAnterior() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}


