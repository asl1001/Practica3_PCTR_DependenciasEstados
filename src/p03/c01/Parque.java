package src.p03.c01;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

public class Parque implements IParque{


	private int contadorPersonasTotales;
	private int max = 50;
	private int min = 0;
	private Hashtable<String, Integer> contadoresPersonasPuerta;
	
	public Parque() {	// TODO
		contadorPersonasTotales = 0;
		contadoresPersonasPuerta = new Hashtable<String, Integer>();
		// TODO
	}


	@Override
	public synchronized void entrarAlParque(String puerta) throws InterruptedException{		// TODO
		comprobarAntesDeEntrar();
		añadirPuerta(puerta);
		// Si no hay entradas por esa puerta, inicializamos
		if (contadoresPersonasPuerta.get(puerta) == null){
			contadoresPersonasPuerta.put(puerta, 0);
		}
		// Aumentamos el contador total y el individual
		//contadorPersonasTotales++;		
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta)+1);
		comprobarAntesDeEntrar();
		entraAlguienAlParque();
		
		// Imprimimos el estado del parque
		imprimirInfo(puerta, "Entrada");
		checkInvariante();
		
	}
	
	@Override
	// Declaracion del metodo salir del parque
	public synchronized void salirDelParque(String puerta) throws InterruptedException {
		
		comprobarPuerta(puerta);
		//contadorPersonasTotales--;
		Integer cont = contadoresPersonasPuerta.get(puerta);
		contadoresPersonasPuerta.put(puerta, cont - 1);
		comprobarAlSalir();
        saleAlguienDelParque();
        imprimirInfoSalida(puerta, "Salida");
        checkInvariante();	
	}
	
	private void imprimirInfo (String puerta, String movimiento){
		System.out.println(movimiento + " por puerta " + puerta);
		System.out.println("--> Personas en el parque " + contadorPersonasTotales); //+ " tiempo medio de estancia: "  + tmedio);
		
		// Iteramos por todas las puertas e imprimimos sus entradas
		for(String p: contadoresPersonasPuerta.keySet()){
			System.out.println("----> Por puerta " + p + " " + contadoresPersonasPuerta.get(p));
		}
		System.out.println(" ");
	}
	private void imprimirInfoSalida (String puerta, String movimiento){
		System.out.println(movimiento + " por puerta " + puerta);
		System.out.println("--> Personas en el parque " + contadorPersonasTotales); //+ " tiempo medio de estancia: "  + tmedio);
		
		// Iteramos por todas las puertas e imprimimos sus entradas
		for(String p: contadoresPersonasPuerta.keySet()){
			System.out.println("----> Por puerta " + p + " " + contadoresPersonasPuerta.get(p));
		}
		System.out.println(" ");
	}
	
	private int sumarContadoresPuerta() {
		int sumaContadoresPuerta = 0;
			Enumeration<Integer> iterPuertas = contadoresPersonasPuerta.elements();
			while (iterPuertas.hasMoreElements()) {
				sumaContadoresPuerta += iterPuertas.nextElement();
			}
		return sumaContadoresPuerta;
	}
	
	protected void checkInvariante() {
		// TODO Auto-generated method stub
		assert contadorPersonasTotales<=20;
		assert sumarContadoresPuerta() == contadorPersonasTotales : "INV: La suma de contadores de las puertas debe ser igual al valor del contador del parte";
	
	}

	protected void comprobarAntesDeEntrar() throws InterruptedException{	
		//si el contador el mayor hay que esperar a que haya gente
		while( contadorPersonasTotales == max ) {
			try {
				wait();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		
		}
	}


	public void comprobarAlSalir() throws InterruptedException {
        while( contadorPersonasTotales == min ) {
        	try {
        		 wait();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
           
        }
    }

	public void saleAlguienDelParque() {
        contadorPersonasTotales--;
        notify();
    }
	public void entraAlguienAlParque() {
		contadorPersonasTotales++;
		notify();
	}

	public void añadirPuerta(String puerta) {
        if (!contadoresPersonasPuerta.containsKey(puerta)) {
        	contadoresPersonasPuerta.put(puerta, 0);
            notify();
        }
    }
	
	public void comprobarPuerta(String puerta) throws InterruptedException {
		if (!contadoresPersonasPuerta.containsKey(puerta)) {
			wait();
		}
	}
}
