package main;

import hilos.HiloConsola;
import hilos.HiloInterfazGrafica;

/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public class Main
{


	public static void main(String[] args)
	{

		HiloInterfazGrafica interfazChofer = new HiloInterfazGrafica();
		HiloConsola consola = new HiloConsola();

		interfazChofer.start();
		consola.start();


	}
}
