package src.p03.c01;

public class SistemaLanzador {
	public static void main(String[] args) {

		IParque parque = new Parque(); // TODO
		// IParque parque= adaptadorParqueSincronizado.getInstancia();
		char letra_a = 'A';
		char letra_b = 'B';
		char letra_c = 'C';
		char letra_d = 'D';
		char letra_e = 'E';
		// Iparque parque = new Parque();

		// Thread puertaA = new Thread(new ActividadEntradaPuerta("A", parque));
		// Thread puertaB = new Thread(new ActividadEntradaPuerta("B", parque));

		// puertaA.start();
		// puertaB.start();

		System.out.println("¡Parque abierto!");

		// for (int i = 0; i < Integer.parseInt(args[0]); i++) {

		String puertaA = "" + ((char) (letra_a++));
		String puertaB = "" + ((char) (letra_b++));
		String puertaC = "" + ((char) (letra_c++));
		String puertaD = "" + ((char) (letra_d++));
		String puertaE = "" + ((char) (letra_e++));

		// Creación de hilos de entrada
		ActividadEntradaPuerta entradaA = new ActividadEntradaPuerta(puertaA, parque);
		ActividadEntradaPuerta entradaB = new ActividadEntradaPuerta(puertaB, parque);
		ActividadEntradaPuerta entradaC = new ActividadEntradaPuerta(puertaC,parque);
		// ActividadEntradaPuerta entradaD = new ActividadEntradaPuerta(puertaD,
		// parque);
		// ActividadEntradaPuerta entradaE = new ActividadEntradaPuerta(puertaE,
		// parque);
		ActividadSalidaPuerta salidaA = new ActividadSalidaPuerta(puertaA, parque);
		ActividadSalidaPuerta salidaB = new ActividadSalidaPuerta(puertaB, parque);
		ActividadSalidaPuerta salidaC = new ActividadSalidaPuerta(puertaC, parque);
		new Thread(entradaA).start();
		new Thread(salidaA).start();
	    new Thread (entradaB).start();
		new Thread(salidaB).start();
		new Thread (entradaC).start();
		new Thread(salidaC).start();
		// new Thread (entradaD).start();
		// new Thread (entradaE).start();

		Thread pa = new Thread(new ActividadEntradaPuerta("A", parque));
		Thread pb = new Thread(new ActividadEntradaPuerta("B", parque));
		Thread pc = new Thread(new ActividadEntradaPuerta("C", parque));
		Thread pA = new Thread(new ActividadSalidaPuerta("A", parque));
		Thread pB = new Thread(new ActividadSalidaPuerta("B", parque));
		Thread pC = new Thread(new ActividadSalidaPuerta("C", parque));
		// Thread pc= new Thread( new ActividadEntradaPuerta("C", parque));
		// Thread pd= new Thread( new ActividadEntradaPuerta("D", parque));
		// Thread pe= new Thread( new ActividadEntradaPuerta("E", parque));

		pa.start();
		pb.start();
		pA.start();
		pB.start();
		// pc.start();
		// pd.start();
		// pe.start();

	}
}
//}