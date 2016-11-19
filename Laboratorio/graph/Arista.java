package graph;

public class Arista {
	Vertice destino; // Indica a donde lleva la artista
	double costo; // Indica el peso de arista, si es un grafo ponderado

	public Arista(Vertice v, Double costo) {
		this.destino = v;
		this.costo = costo;
	}
}
