package colecciones;

import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONException;

import auxiliares.Viaje;


/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public class ListaViajes
{
	private HashSet<Viaje> viajes;

	/**
	 * Constructor de la coleccion vacio
	 */
	public ListaViajes()
	{
		viajes = new HashSet<Viaje>();
	}

	/**
	 * Constructor copia
	 * 
	 * @param Recibe coleccion LinkedHashSet de viajes
	 */
	public ListaViajes(HashSet<Viaje> viaje)
	{
		viajes = new HashSet<Viaje>();
		getViajes().addAll(viaje);
	}

	/**
	 * Constructor que crea la coleccion a partir de un JSONArray
	 */
	public ListaViajes(JSONArray aux) throws JSONException
	{
		viajes = JSONToColeccion(aux);
	}

	public HashSet<Viaje> getViajes()
	{
		return viajes;
	}

	/**
	 * Agrega un viaje a la coleccion
	 * 
	 * @param Objeto Viaje
	 * @return Devuelve true si lo agrega, false si habia otro igual
	 */
	public boolean agregarViaje(Viaje aux)
	{
		return getViajes().add(aux);
	}

	/**
	 * @return Devuelve un StringBuilder con todos los viajes existentes listados.
	 */
	public StringBuilder listarViajes()
	{
		StringBuilder sb = new StringBuilder();
		for (Viaje aux : getViajes())
		{
			sb.append(aux.toString() + "\n\n");
		}
		return sb;
	}


	/**
	 * Devuelve el viaje en curso de un chofer ingresando su licencia.
	 */
	public Viaje viajeEnCurso(String licencia)
	{
		Viaje rta = new Viaje();
		for (Viaje aux : getViajes())
		{
			if (aux.getChofer().equalsIgnoreCase(licencia) && (aux.getFinalizado() == false))

				rta = aux;
		}
		return rta;
	}

	/**
	 * Construye el Array de Viajes en Formato JSON y lo ingresa al final a un
	 * JSONObject con clave "viajes" Este metodo se llama desde el main (alla se
	 * hace el try/catch)
	 * 
	 *
	 * @return definitivo: JSONObject que contiene Array de viajes en formato JSON
	 */
	public JSONArray coleccionViajesAJSON() throws JSONException
	{
		JSONArray array = new JSONArray();

		for (Viaje aux : getViajes())
		{
			array.put(aux.viajeGetFormatoJson());
		}
		return array;
	}

	/**
	 * Crea la coleccion a partir de un JSONArray
	 */
	private HashSet<Viaje> JSONToColeccion(JSONArray aux) throws JSONException
	{
		HashSet<Viaje> rta = new HashSet<Viaje>();
		int contador = 0;
		while (contador < aux.length())
		{
			rta.add(new Viaje(aux.getJSONObject(contador)));
			contador++;
		}
		return rta;
	}

}
