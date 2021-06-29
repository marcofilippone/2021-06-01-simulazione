package it.polito.tdp.genes.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	private Graph<Genes, DefaultWeightedEdge> grafo;
	private GenesDao dao;
	private Map<String, Genes> idMap;
	
	public Model() {
		dao = new GenesDao();
	}
	
	public void creaGrafo() {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		idMap = new HashMap<>();
		dao.vertici(idMap);
		Graphs.addAllVertices(this.grafo, idMap.values());
		double corr = 0;
		for(Adiacenza ad : dao.archi(idMap)) {
			if(grafo.getEdge(ad.getG1(), ad.getG2()) == null) {
					if(ad.getC1() != ad.getC2()) {
						corr = Math.abs(ad.getCorr());
					}
					else {
						corr = 2*Math.abs(ad.getCorr());
					}
					Graphs.addEdgeWithVertices(this.grafo, ad.getG1(), ad.getG2(), corr);
			}
		}
	}
	
	public Set<Genes> getVertici(){
		return grafo.vertexSet();
	}
	
	public Set<DefaultWeightedEdge> getArchi(){
		return grafo.edgeSet();
	}
	
	public List<GeneNumb> getAdiacenti(Genes g){
		List<Genes> listaAd = Graphs.neighborListOf(grafo, g);
		List<GeneNumb> adiacenti = new LinkedList<>();
		for(Genes genes : listaAd) {
			GeneNumb gn = new GeneNumb(genes, grafo.getEdgeWeight(grafo.getEdge(g, genes)));
			adiacenti.add(gn);
		}
		Collections.sort(adiacenti);
		return adiacenti;
	}
}
