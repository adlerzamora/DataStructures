package Laboratorio;

public class WTree {
	int[] players, tree;
	int lowExt, offset;

	//
	public WTree() {

	}

	// Regresa el jugador que quedo victorioso (La posicion de players que queda
	// registrada en tree[1]
	public int getWinner() {
		if (tree == null) {
			return 0;
		} else {
			return players[tree[1]];
		}
	}

	// Pasa a la siguiende ronda al jugador ganador (El de menor valor)
	private void play(int p, int l, int r) {
		tree[p] = (players[l] < players[r]) ? l : r;
		while (p % 2 == 1 && p > 1) {
			tree[p / 2] = (players[tree[p - 1]] < players[tree[p]]) ? tree[p - 1] : tree[p];
			p /= 2;
		}

	}

	// Inicializa el arreglo al arbol conforme a los 3 casos
	// Caso 1: los jugadores generan un arbol completo-Jugadores como potencia
	// de 2
	// Caso 2: los jugadores no son potencia de dos por lo cual queda un jugador
	// individual para jugar en el nivel superior
	// Caso 3: los jugadores no son potencia de dos pero quedan pares de
	// jugadores para jugar en el nivel superior
	public void initialize(int a[]) {
		int n = a.length - 1;
		if (n < 2) {
			throw new IllegalArgumentException();
		}
		players = a;
		tree = new int[n];
		int i, s;
		for (s = 1; s * 2 <= n - 1; s += s)
			;

		lowExt = 2 * (n - s);
		offset = 2 * s - 1;

		for (i = 2; i <= lowExt; i += 2) {
			play((i + offset) / 2, i - 1, i);
		}

		// Complete tree
		if (n % 2 == 1) {
			play(n / 2, tree[n - 1], lowExt + 1);
			i = lowExt + 3;
		} else { // Full tree
			i = lowExt + 2;
		}

		for (; i <= n; i += 2) {
			play((i - lowExt + n - 1) / 2, i - 1, i);
		}
	}

	public void display() {
		System.out.println("offset: " + offset + ", lowExt: " + lowExt);
		for (int i = 1; i < tree.length; i++) {
			System.out.println("[" + players[tree[i]] + "]");
		}
	}

	// Se utiliza en el arraySort para volver a jugar (ignorando los previos
	// ganadores cuyo valor se asigno a infinito para ignorar al momento de
	// jugar
	public void replay(int posPlayer) {

		int n = players.length - 1;

		if (posPlayer < 1 || posPlayer > n) {
			throw new IllegalArgumentException();
		}

		int matchN, left, right;

		if (posPlayer <= lowExt) {

			matchN = (posPlayer + offset) / 2;
			left = 2 * matchN - offset;
			right = left + 1;

		} else {

			matchN = (posPlayer - lowExt + n - 1) / 2;

			if (2 * matchN == n - 1) {

				left = tree[n - 1];
				right = posPlayer;

			} else {

				left = 2 * matchN - n + 1 + lowExt;
				right = left + 1;
			}
		}

		tree[matchN] = (players[left] < players[right]) ? left : right;

		if (matchN == n - 1 && n % 2 == 1) {
			matchN /= 2;
			tree[matchN] = players[tree[n - 1]] < players[lowExt + 1] ? tree[n - 1] : lowExt + 1;
		}

		matchN /= 2;

		for (; matchN >= 1; matchN /= 2) {
			tree[matchN] = (players[tree[matchN * 2]] < players[tree[matchN * 2 + 1]]) ? tree[matchN * 2]
					: tree[matchN * 2 + 1];

		}

	}

	// Intercambia el valor del jugador que recibe por el nuevo valor asignado
	// Se usa en el arraySort para eliminar los jugadores que van ganando
	public void change(int thePlayer, int value) {
		players[thePlayer] = value;
		replay(thePlayer);
	}

	// Regresa arreglo original pero sorteado
	public int[] arraySort() {
		int[] ordenado = new int[players.length - 1];
		for (int j = 0; j < ordenado.length; j++) {
			ordenado[j] = this.getWinner();
			change(tree[1], 0x7FFFFFFF);
		}
		return ordenado;
	}

	public static void main(String[] args) {
		WTree wTree = new WTree();
		int[] a = { 0, 12, 11, 23, 22, 33, 44, 55, 66, 77, 88, 99 };
		wTree.initialize(a);
		int[] b = wTree.arraySort();
		String c = "[";
		for (int i = 0; i < b.length; i++) {
			c += b[i] + ",";
		}
		c += "]";
		System.out.println(c);
	}
}