package it.polito.tdp.exam.model;

import java.util.Objects;

public class Vertice {
	
	Integer anno;
	Integer pesoMedio;
	public Integer getAnno() {
		return anno;
	}
	public void setAnno(Integer anno) {
		this.anno = anno;
	}
	public Integer getPesoMedio() {
		return pesoMedio;
	}
	public void setPesoMedio(Integer pesoMedio) {
		this.pesoMedio = pesoMedio;
	}
	@Override
	public int hashCode() {
		return Objects.hash(anno, pesoMedio);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertice other = (Vertice) obj;
		return Objects.equals(anno, other.anno) && Objects.equals(pesoMedio, other.pesoMedio);
	}
	@Override
	public String toString() {
		return "Vertice [anno=" + anno + ", pesoMedio=" + pesoMedio + "]";
	}
	public Vertice(Integer anno, Integer pesoMedio) {
		super();
		this.anno = anno;
		this.pesoMedio = pesoMedio;
	}
	
	

}
