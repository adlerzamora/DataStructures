package graph;

import java.util.LinkedList;

public class Vertice {
	// Nombre del vertice
	String nombre;
	// Lista de los vertices que son adyacentes a este
	LinkedList<Arista> adyacentes;
	Double distancia;
	int marcado;
	Vertice anterior;

	public Vertice(String nombre) {
		adyacentes = new LinkedList<Arista>();
		this.nombre = nombre;
	}

	public void reinicia() {
		this.marcado = 0;
		this.anterior = null;
		this.distancia = Grafo.INFINITO;
	}
}
