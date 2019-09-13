package mapaDeCalor;

import org.json.JSONException;

import archivos.JsonUtiles;
import auxiliares.Viaje;
import colecciones.ListaViajes;


/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public class Coordenadas
{

	/**
	 * Obtiene la cantidad de metros entre un punto y otro
	 */
	public static float distanciaMetros(float utmX1, float utmY1, float utmX2, float utmY2)
	{
		float rta;
		rta = (float) Math.sqrt(((Math.pow((utmX1 - utmX2), 2)) + (Math.pow((utmY1 - utmY2), 2))));
		return rta;
	}

	/**
	 * Calcula utmX promedio.
	 * 
	 * @param Se recibe la lista de viajes realizados.
	 */
	private static float puntoCalorX(ListaViajes viaje)
	{
		float utmX = 0;
		for (Viaje aux : viaje.getViajes())
		{
			utmX = utmX + aux.getDireccionInicial().getUtmX();
		}
		utmX = utmX / (viaje.getViajes().size());
		return utmX;
	}

	/**
	 * Calcula utmY promedio.
	 * 
	 * @param Se recibe la lista de viajes realizados
	 */
	private static float puntoCalorY(ListaViajes viaje)
	{
		float utmY = 0;
		for (Viaje aux : viaje.getViajes())
		{
			utmY = utmY + aux.getDireccionInicial().getUtmY();
		}

		utmY = utmY / (viaje.getViajes().size());
		return utmY;
	}

	/**
	 * Calcula una coordenada aproximada mediante la media aritmetica o promedio.
	 * 
	 * @param Se recibe la lista de viajes realizados
	 */
	public static String puntoCalor() throws JSONException
	{
		ListaViajes viaje = JsonUtiles.abrirArchivoViajes();
		float utmX = puntoCalorX(viaje);
		float utmY = puntoCalorY(viaje);
		return "utmX: " + utmX + "\nutmY: " + utmY;
	}
}
