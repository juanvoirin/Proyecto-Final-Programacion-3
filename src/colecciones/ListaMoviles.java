package colecciones;

import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONException;

import auxiliares.Movil;

/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public class ListaMoviles
{

	private HashSet<Movil> moviles;

	/**
	 * Constructor de la coleccion vacio
	 */
	public ListaMoviles()
	{
		moviles = new HashSet<Movil>();
	}

	/**
	 * Constructor copia
	 * 
	 * @param Recibe coleccion HashSet de moviles
	 */
	public ListaMoviles(HashSet<Movil> movil)
	{
		moviles = new HashSet<Movil>();
		getMoviles().addAll(movil);
	}

	/**
	 * Constructor que crea la coleccion a partir de un JSONArray
	 */
	public ListaMoviles(JSONArray aux) throws JSONException
	{
		moviles = JSONToColeccion(aux);
	}

	private HashSet<Movil> getMoviles()
	{
		return moviles;
	}

	/**
	 * Agrega un movil a la coleccion
	 * 
	 * @param Objeto Movil
	 * @return Devuelve true si lo agrega, false si habia otro igual.
	 */
	public boolean agregarMovil(Movil movil)
	{
		return getMoviles().add(movil);
	}

	/**
	 * Recorre y lista todos los moviles.
	 * 
	 * @return Devuelve StringBuilder con todos los moviles.
	 */
	public StringBuilder listarMoviles()
	{

		StringBuilder sb = new StringBuilder();

		for (Movil aux : getMoviles())
		{
			sb.append(aux.toString() + "\n\n");
		}
		return sb;
	}

	/**
	 * Elimina en base a la patente recibida.
	 * 
	 * @return Retorna la accion realizada.
	 */
	public String eliminarMovil(String patente)
	{
		String rta = "Esta patente no esta asignada a ningun auto.";
		for (Movil aux : getMoviles())
		{
			if (aux.getPatente().equalsIgnoreCase(patente))
			{
				getMoviles().remove(aux);
				rta = "Movil borrada con exito.";
			}
		}
		return rta;
	}

	/**
	 * Verifica disponibilidad de moviles con chofer asignado
	 * 
	 * @return true si hay disponibles, false en caso contrario.
	 */
	public boolean movilesDisponibles()
	{
		int contador = 0;
		for (Movil aux : getMoviles())
		{
			if (!aux.getLicencia().equalsIgnoreCase("0"))
				if (!aux.getLicencia().equalsIgnoreCase("-1"))
					contador++;
		}
		if (contador == 0)
			return false;
		else
			return true;
	}

	/**
	 * Selecciona un movil con chofer.
	 * 
	 * @return Devuelve el movil.
	 */
	public Movil seleccionMovil()
	{
		Movil rta = null;

		for (Movil aux : getMoviles())
		{
			if (!(aux.getLicencia().equalsIgnoreCase("-1") || aux.getLicencia().equalsIgnoreCase("0")))
			{
				rta = aux;
			}

		}
		return rta;
	}

	/**
	 * Selecciona un movil sin chofer.
	 * 
	 * @return Devuelve el movil.
	 */
	public Movil movilesSinChofer()
	{
		Movil rta = null;
		for (Movil aux : getMoviles())
		{
			if (aux.getLicencia().equalsIgnoreCase("0"))
				rta = aux;
		}
		return rta;
	}

	/**
	 * Construye el Array de Moviles en Formato JSON y lo ingresa al final a un
	 * JSONObject con clave "moviles" Este metodo se llama desde el main (alla se
	 * hace el try/catch)
	 * 
	 * @return definitivo: JSONObject que contiene Array de moviles en formato JSON
	 */
	public JSONArray coleccionMovilesAJSON() throws JSONException
	{
		JSONArray array = new JSONArray();

		for (Movil aux : getMoviles())
		{
			array.put(aux.movilGetFormatoJson());
		}
		return array;
	}

	/**
	 * Crea la coleccion a partir de un JSONArray
	 */
	private HashSet<Movil> JSONToColeccion(JSONArray aux) throws JSONException
	{
		HashSet<Movil> rta = new HashSet<Movil>();
		int contador = 0;
		while (contador < aux.length())
		{
			rta.add(new Movil(aux.getJSONObject(contador)));
			contador++;
		}
		return rta;
	}

}
