package graph;

import java.util.HashMap;
import java.util.Map;

public class Grafo {
	Map<String, Vertice> mapaVertices;
	public static final double INFINITO = Double.MAX_VALUE;

	public Grafo() {
		mapaVertices = new HashMap<String, Vertice>();
	}

	// Verifica si existe en el diccionarioel vertice a traves de su nombre, en
	// caso de que no, se crea el vertice y se agrega al diccionario, devuelve
	// el vertice creado o encontrado en el mapa
	private Vertice getVertice(String nombre) {
		if (mapaVertices.containsKey(nombre)) {
			return mapaVertices.get(nombre);
		}
		Vertice v = new Vertice(nombre);
		mapaVertices.put(nombre, v);
		return v;
	}

	// A traves de getVertice y con los Strings origen y destino, inicializa dos
	// variables de tipo Vertice (v y w). Una vez identificadoslos vertices,
	// agregamosa la lista de adyacencia del vertice v, una arista con destino w
	// y el costo; y viceversa.
	public void agregaArista(String origen, String destino, double costo) {
		Vertice v = getVertice(origen);
		Vertice w = getVertice(destino);
		Arista aristaV = new Arista(v, costo);
		Arista aristaW = new Arista(w, costo);
		v.adyacentes.add(aristaW);
		w.adyacentes.add(aristaV);
	}

	// Este metodo reinicia todos los vertices del grafo. Este metodo sirvepara
	// reiniciar todas las condiciones iniciales, todos los vertices del grafo
	// antes de los recorridos.
	public void reiniciaTodos() {
		for (Vertice v : mapaVertices.values()) {
			v.reinicia();
		}
	}

	// Debe devolver el String que contiene el recorrido en anchura del grafo,
	// empezando por el vertice origen.
	public String recorridoEnAnchura(String origen) {
		return null;
	}
}
