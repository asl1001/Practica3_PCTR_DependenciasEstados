package src.p03.c01;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

public class Parque implements IParque{


	private int contadorPersonasTotales;
	//contador a 40 porque puede haber 20 personas en los parques
	private int max = 40;
	private int min = 0;
	private Hashtable<String, Integer> contadoresPersonasPuerta;
	public static final int totalPersonasPuertas = 20;
	
	
	public Parque() {	// TODO
		contadorPersonasTotales = 0;
		contadoresPersonasPuerta = new Hashtable<String, Integer>();
		// TODO
	}


	@Override
	public void entrarAlParque(String puerta) throws InterruptedException{		// TODO
		comprobarAntesDeEntrar();
		// Si no hay entradas por esa puerta, inicializamos
		if (contadoresPersonasPuerta.get(puerta) == null){
			contadoresPersonasPuerta.put(puerta, 0);
		}
		// TODO
				
		
		// Aumentamos el contador total y el individual
		contadorPersonasTotales++;		
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta)+1);
		
		// Imprimimos el estado del parque
		imprimirInfo(puerta, "Entrada");
		checkInvariante();
		// TODO
		
		
		// TODO
		
	}
	
	// Declaracion del metodo salir dwl parque
	public void salirDelParque(String puerta) {}
	
	
	private void imprimirInfo (String puerta, String movimiento){
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
				int contador=0;
				Set <String> keys=contadoresPersonasPuerta.keySet();
				for(String key: keys) {
					contador+=contadoresPersonasPuerta.get(key);
				}
		assert sumarContadoresPuerta() == contadorPersonasTotales : "INV: La suma de contadores de las puertas debe ser igual al valor del contador del parte";
		// TODO
	}

	protected void comprobarAntesDeEntrar() throws InterruptedException{	
		//si el contador el mayor hay que esperar a que haya gente
		while( contadorPersonasTotales == max ) {
			wait();
		}
	}


	public void comprobarAlSalir() throws InterruptedException {
        while( contadorPersonasTotales == min ) {
            wait();
        }
    }

	public void saleAlguienDelParque() {
        contadorPersonasTotales--;
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
