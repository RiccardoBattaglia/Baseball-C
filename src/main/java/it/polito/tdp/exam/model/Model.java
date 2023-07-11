package it.polito.tdp.exam.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.exam.db.BaseballDAO;


public class Model {
	
	private BaseballDAO dao;
//	private Graph<Vertice, DefaultWeightedEdge> grafo;
    private SimpleWeightedGraph<Integer, DefaultWeightedEdge> grafo;

//	private Map<String, Team> userMap;
	private List<Team> teamList;
	private List<Vertice> verticiList;
	private Map<Integer, List<People>> peoplePerAnno;
	
	public Model() {
		this.dao = new BaseballDAO();
		
		 this.peoplePerAnno = new HashMap<Integer, List<People>>();
		
//		this.userMap=new HashMap<>();
		this.teamList = this.dao.readAllTeams();
//		for(User i : userList) {
//			this.userMap.put(i.getUserId(), i);
//		}
		
	}
	
	public List<String> getNomiTeams(){
		
		List<String> result = new LinkedList<>();
		
		for(Team i : teamList) {
			if(!result.contains(i.getName())) {
				result.add(i.getName());
			}
			
		}
		
		Collections.sort(result);
		
		return result;
	}
	

	public void creaGrafo(String team) {
		// TODO Auto-generated method stub
		
//	this.grafo = new SimpleWeightedGraph<Vertice, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
	// Aggiunta VERTICI 
//	List<Vertice> vertici=new LinkedList<>();
//
//	List<Integer> anni=new LinkedList<>();
//	
//	anni.addAll(this.dao.getAllYearsPerTeam(team));
//	
//	for(Integer i : anni) {
//			vertici.add(new Vertice(i, this.dao.getPesoMedio(team, i)));
//	}
//	
//	Graphs.addAllVertices(this.grafo, vertici);

	
	// Aggiunta ARCHI
	
//	double peso;
//	for (Vertice v1 : vertici) {
//		for (Vertice v2 : vertici) {
//			if((v1.getAnno()!=v2.getAnno()))
//					 {
//		      this.grafo.addEdge(v1,v2);
//		      this.grafo.setEdgeWeight(this.grafo.getEdge(v1, v2), v1.getPesoMedio()-v2.getPesoMedio());
//		      System.out.println(v1.getAnno()+"-"+v2.getAnno()+"-"+this.grafo.getEdgeWeight(this.grafo.getEdge(v1, v2)));
//			}}
//			}

	  // inizializza grafo
    this.grafo = new SimpleWeightedGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
    
	 List<Integer> vertici = this.dao.getAllYearsPerTeam(team);
     Graphs.addAllVertices(this.grafo, vertici);
	
	//Leggere i giocatori per ogni anno
    this.peoplePerAnno.clear();
    for (int anno : vertici) {
        this.peoplePerAnno.put(anno, this.dao.gPdY(team, anno) );
    }


    //verificare ogni coppia di anni, e creare un arco con il peso corrispondente
    for (int v1 : vertici) {
        for (int v2 : vertici) {
if(v1>v2) {
            List<People> giocatori1 = this.peoplePerAnno.get(v1);
            List<People> giocatori2 = this.peoplePerAnno.get(v2);
     

            double peso = Math.abs(getAvgWeight(giocatori1) - getAvgWeight(giocatori2));
            
            this.grafo.addEdge(v1,v2);
		    this.grafo.setEdgeWeight(this.grafo.getEdge(v1, v2), peso);
		   
        }}
    }

}

private double getAvgWeight(List<People> giocatori) {
    double somma = 0;

    for (People p : giocatori){
        somma += p.getWeight();
    }

    return somma/giocatori.size();
}


public int nVertici() {
	return this.grafo.vertexSet().size();
}

public int nArchi() {
	return this.grafo.edgeSet().size();
}

public Set<Integer> getVertici(){
	
	Set<Integer> vertici=this.grafo.vertexSet();
	
	return vertici;
}

public List<Dettaglio> getDettagli(int y) {
	
	List<Dettaglio> dettagli = new LinkedList<>();
	
	for(DefaultWeightedEdge i :  this.grafo.edgesOf(y)) {
		dettagli.add(new Dettaglio(this.grafo.getEdgeTarget(i), this.grafo.getEdgeWeight(i)));
	}
	
	Collections.sort(dettagli);
	
	return dettagli;
}

//public List<Set<Vertice>> getComponente() {
//	ConnectivityInspector<Vertice, DefaultWeightedEdge> ci = new ConnectivityInspector<>(this.grafo) ;
//	return ci.connectedSets() ;
//}
	
}
