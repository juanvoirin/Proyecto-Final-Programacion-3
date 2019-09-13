package hilos;

import Menu.MenuGeneral;

/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public class HiloConsola extends Thread
{
	/**
	 * Si no hay ningun usuario creado pedir crear un operador Inicio sesion
	 * interfaz grafica Corroborar usuario y clave
	 */
	public void run()
	{
		MenuGeneral.menuInicio();
	}

}
