package ThirtyDaysOfCodeChallenges;

public class Day10BinaryNumbers {
	public static int maxBinary(int a) {
		String prueba = Integer.toBinaryString(a);
		System.out.println(prueba);
		int acumulador = 0, maxAcumulado = 0;
		for (int i = 0; i < prueba.length(); i++) {
			if (prueba.charAt(i) == '1') {
				acumulador += 1;
				System.out.println("Entro a este if con acumulador: " + acumulador);
				if (maxAcumulado < acumulador) {
					maxAcumulado = acumulador;
				}
			} else {
				acumulador = 0;
			}
		}
		return maxAcumulado;
	}

	public static void main(String[] args) {
		// Scanner scan = new Scanner(System.in);
		// System.out.println(maxBinary(scan.nextInt()));
		System.out.println(maxBinary(5));
	}
}
