package colecciones;

import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONException;

import auxiliares.Direccion;


/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public class ListaDirecciones
{

	private HashSet<Direccion> direcciones;

	/**
	 * Constructor de la coleccion vacio
	 */
	public ListaDirecciones()
	{
		direcciones = new HashSet<Direccion>();
	}

	/**
	 * Constructor copia
	 * 
	 * @param Recibe coleccion HashSet de direcciones
	 */
	public ListaDirecciones(HashSet<Direccion> direccion)
	{
		direcciones = new HashSet<Direccion>();
		getDirecciones().addAll(direccion);
	}

	/**
	 * Constructor que crea la coleccion a partir de un JSONArray
	 * 
	 */
	public ListaDirecciones(JSONArray aux) throws JSONException
	{
		direcciones = JSONToColeccion(aux);
	}

	private HashSet<Direccion> getDirecciones()
	{
		return direcciones;
	}

	/**
	 * Agrega una direccion a la coleccion
	 * 
	 * @param Objeto Direccion
	 * @return Devuelve true si lo agrega, false si habia otro igual.
	 */
	public boolean agregarDireccion(Direccion direccion)
	{

		return getDirecciones().add(direccion);

	}

	/**
	 * Recorre y lista todas las direcciones.
	 * 
	 * @return Devuelve StringBuilder con todas las direcciones.
	 */
	public StringBuilder listarDirecciones()
	{

		StringBuilder sb = new StringBuilder();

		for (Direccion aux : getDirecciones())
		{
			sb.append(aux.toString() + "\n\n");
		}
		return sb;
	}

	/**
	 * Elimina en base a la calle, altura y ciudad ingresados.
	 * 
	 * @return Retorna la accion realizada
	 */
	public String eliminarDireccion(String calle, int altura, String ciudad)
	{
		String rta = "Esta direccion no esta cargada.";
		for (Direccion aux : getDirecciones())
		{
			if (aux.compruebaDireccion(calle, altura, ciudad))
			{
				getDirecciones().remove(aux);
				rta = "Direccion borrada con exito.";
			}
		}
		return rta;
	}

	/**
	 * Busca si hay una direccion con misma calle, altura y ciudad
	 * 
	 * @return True si la hay, false si no.
	 */
	public boolean buscaDireccion(String calle, int altura, String ciudad)
	{
		boolean rta = false;
		for (Direccion aux : getDirecciones())
		{
			if (aux.compruebaDireccion(calle, altura, ciudad))
				rta = true;
		}
		return rta;
	}

	/**
	 * Crea la direccion agregando las coordenadas utm
	 * 
	 * @return devuelve la direccion completa
	 */
	public Direccion completaDireccion(String calle, int altura, String ciudad)
	{
		Direccion rta;
		rta = new Direccion();
		for (Direccion aux : getDirecciones())
		{
			if (aux.compruebaDireccion(calle, altura, ciudad))
			{
				rta.setCalle(aux.getCalle());
				rta.setAltura(aux.getAltura());
				rta.setCiudad(aux.getCiudad());
				rta.setUtmX(aux.getUtmX());
				rta.setUtmY(aux.getUtmY());
			}
		}
		return rta;
	}

	/**
	 * Si existe la direccion no hace nada, si no existe la agrega a la coleccion.
	 * 
	 */
	public void existeDireccion(Direccion direccion, ListaDirecciones direcciones)
	{
		if (!buscaDireccion(direccion.getCalle(), direccion.getAltura(), direccion.getCiudad()))
			direcciones.agregarDireccion(direccion);
	}

	/**
	 * Construye el Array de Direcciones en Formato JSON y lo ingresa al final a un
	 * JSONObject con clave "direcciones" Este metodo se llama desde el main (alla
	 * se hace el try/catch)
	 * 
	 *
	 * @return definitivo: JSONObject que contiene Array de direcciones en formato
	 *         JSON
	 */
	public JSONArray coleccionDireccionesAJSON() throws JSONException
	{
		JSONArray array = new JSONArray();

		for (Direccion aux : getDirecciones())
		{
			array.put(aux.direccionGetFormatoJson());
		}
		return array;
	}

	/**
	 * Crea la coleccion a partir de un JSONArray
	 * 
	 * @return Coleccion a partir de un JSONArray
	 */
	private HashSet<Direccion> JSONToColeccion(JSONArray aux) throws JSONException
	{
		HashSet<Direccion> rta = new HashSet<Direccion>();
		int contador = 0;
		while (contador < aux.length())
		{
			rta.add(new Direccion(aux.getJSONObject(contador)));
			contador++;
		}
		return rta;
	}

}
