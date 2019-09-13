package hilos;

import interfazGrafica.IngresoSistem;

/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public class HiloInterfazGrafica extends Thread
{

	public void run()
	{
		try
		{
			IngresoSistem frame = new IngresoSistem();
			frame.setVisible(true);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
