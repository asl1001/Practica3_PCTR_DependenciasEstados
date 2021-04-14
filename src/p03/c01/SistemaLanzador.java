package src.p03.c01;

public class SistemaLanzador {
	public static void main(String[] args) {

		IParque parque = new Parque(); // TODO
		char letra = 'A';

		System.out.println("¡Parque abierto!");

		for (int i = 0; i < Integer.parseInt(args[0]); i++) {

			String puerta = "" + ((char) (letra++));

			// Creación de hilos de entrada
			ActividadEntradaPuerta entrada = new ActividadEntradaPuerta(puerta, parque);
			ActividadSalidaPuerta salida = new ActividadSalidaPuerta(puerta, parque);

			Thread pa = new Thread(new ActividadEntradaPuerta("A", parque));

			Thread pA = new Thread(new ActividadSalidaPuerta("A", parque));
			new Thread(entrada).start();
			new Thread(salida).start();
			pa.start();

			pA.start();

		}
	}
}