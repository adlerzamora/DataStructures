package Laboratorio;

public class DisjointSet {
	int[] p; // Para arreglo de padres
	int[] rank; // Para llevar control de los rangos de cada nodo
	int[] size; // Para llevar control del tamano de cada nodo
	int numSets; // Para llevar el control del numero de conjuntos que tenemos

	// Metodo que inicializa a n conjuntos. Este metodo debe inicializar p, rank
	// y size a arreglos de tamaño n . El arreglo p se inicializa con los
	// valores 0,1, ..., n-1. El arreglo Rank inicializa todos sus elementos a
	// tamaño 0 y el arreglo size a 1. Tambien numSets a n
	public void makeSet(int n) {
		p = new int[n];
		rank = new int[n];
		size = new int[n];
		numSets = n;
		for (int i = 0; i < n; i++) {
			p[i] = i;
			rank[i] = 0;
			size[i] = 1;
		}
	}

	// Funcion recursiva que aplica heuristica "path compression". Devuelve en
	// que conjunto está, a traves de quien es el padre.
	public int find(int x) {
		if (p[x] != x) {
			p[x] = find(p[x]);
		}
		return p[x];
	}

	// Este metodo determina si i y j estan en el mismo conjunto (tienen el
	// mismo padre)
	public boolean isSameSet(int i, int j) {
		return find(i) == find(j);
	}

	// Este metodo realizara la union entre el conjunto i y el de j
	public void union(int i, int j) {
		int xRoot = find(i), yRoot = find(j);
		if (xRoot == yRoot) {
			return;
		}
		size[p[xRoot]] += SizeOfSet(yRoot);
		size[p[yRoot]] = SizeOfSet(xRoot);
		if (xRoot > yRoot) {
			p[yRoot] = xRoot;
			System.out.println("-----------------------if");
		} else {
			p[xRoot] = yRoot;
			System.out.println("-----------------------if");
		}
		if (rank[xRoot] == rank[yRoot]) {
			System.out.println("-----------------------else");
			rank[yRoot] = rank[yRoot] + 1;
		}
		numSets -= 1;
	}

	// Este metodo devuelve el numero de disjoint set que tiene actualmente la
	// estructura.
	public int numDisjointSets() {
		return numSets;
	}

	// Este metodo devuelve el numero de elementos que tiene el conjunto donde
	// se encuentra i
	public int SizeOfSet(int i) {
		return size[find(i)];
	}

	public static void main(String[] args) {
		DisjointSet lucio = new DisjointSet();
		lucio.makeSet(10);
		for (int i = 0; i < lucio.rank.length; i++) {
			System.out.println("Padre de elemento " + i + ":  " + lucio.find(i));
		}
		lucio.union(0, 4);
		for (int i = 0; i < lucio.rank.length; i++) {
			System.out.println("Padre de elemento " + i + ":  " + lucio.find(i));
		}
		lucio.union(1, 3);
		for (int i = 0; i < lucio.rank.length; i++) {
			System.out.println("Padre de elemento " + i + ":  " + lucio.find(i));
		}
		lucio.union(0, 2);
		for (int i = 0; i < lucio.rank.length; i++) {
			System.out.println("Padre de elemento " + i + ":  " + lucio.find(i));
		}
		lucio.union(2, 3);
		for (int i = 0; i < lucio.rank.length; i++) {
			System.out.println("Padre de elemento " + i + ":  " + lucio.find(i));
		}
	}

}
