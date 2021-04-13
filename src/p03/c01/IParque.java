package src.p03.c01;

public interface IParque {
	
	//declaracion en la interfaz del metodo entrar
	public abstract void entrarAlParque(String puerta) throws InterruptedException;
	//declaracion en la interfaz del metodo salir
	public abstract void salirDelParque(String puerta) throws InterruptedException;


}
