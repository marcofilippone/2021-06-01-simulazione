package it.polito.tdp.genes.model;

public class Adiacenza {
	private Genes g1;
	private Genes g2;
	private double corr;
	private Integer c1;
	private Integer c2;
	public Adiacenza(Genes g1, Genes g2, double corr, Integer c1, Integer c2) {
		super();
		this.g1 = g1;
		this.g2 = g2;
		this.corr = corr;
		this.c1 = c1;
		this.c2 = c2;
	}
	public Genes getG1() {
		return g1;
	}
	public void setG1(Genes g1) {
		this.g1 = g1;
	}
	public Genes getG2() {
		return g2;
	}
	public void setG2(Genes g2) {
		this.g2 = g2;
	}
	public double getCorr() {
		return corr;
	}
	public void setCorr(double corr) {
		this.corr = corr;
	}
	public Integer getC1() {
		return c1;
	}
	public void setC1(Integer c1) {
		this.c1 = c1;
	}
	public Integer getC2() {
		return c2;
	}
	public void setC2(Integer c2) {
		this.c2 = c2;
	}
	
	

}
