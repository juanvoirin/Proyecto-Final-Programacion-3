package hilos;
//ALAN MEDINA


/**
 * Esta clase se crea con el fin de devpolver la fecha y hora para la interfaz grafica.
 * 
 */
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import interfazGrafica.UsuarioChofer;


/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public class HiloReloj extends Thread
{

	public void run()
	{

		do
		{

			int hora, minuto, segundo;

			Calendar calendar = GregorianCalendar.getInstance();
			java.util.Date date = Calendar.getInstance().getTime();

			SimpleDateFormat sdf = new SimpleDateFormat("E dd/MM/yyyy");

			hora = calendar.get(Calendar.HOUR_OF_DAY);
			minuto = calendar.get(Calendar.MINUTE);
			segundo = calendar.get(Calendar.SECOND);


			UsuarioChofer.lblHora.setText(hora + ":" + minuto + ":" + segundo);
			UsuarioChofer.lblFecha.setText(sdf.format(date));


		} while (true);


	}

}
