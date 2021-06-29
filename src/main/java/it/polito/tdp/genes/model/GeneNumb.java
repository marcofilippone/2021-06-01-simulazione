package it.polito.tdp.genes.model;

public class GeneNumb implements Comparable<GeneNumb>{
	private Genes g;
	private Double peso;
	
	public Genes getG() {
		return g;
	}

	public void setG(Genes g) {
		this.g = g;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public GeneNumb(Genes g, Double peso) {
		super();
		this.g = g;
		this.peso = peso;
	}

	@Override
	public int compareTo(GeneNumb other) {
		return -this.getPeso().compareTo(other.getPeso());
	}
	

}
