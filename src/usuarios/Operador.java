package usuarios;

import org.json.JSONException;
import org.json.JSONObject;

import auxiliares.Direccion;
import auxiliares.Viaje;
import colecciones.ListaUsuarios;
import colecciones.ListaViajes;
import interfaces.IHerramientasViaje;
import interfaces.IInformacion;


/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public class Operador extends Persona implements IInformacion, IHerramientasViaje
{

	/**
	 * Constructor vacio
	 */
	public Operador()
	{
		super();
	}

	/**
	 * Constructor completo
	 */
	public Operador(String nombreApellido, String dni, int dia, int mes, int anio, String calle, int altura,
			String ciudad, float utmX, float utmY, ListaUsuarios usuario)
	{
		super(nombreApellido, dni, dia, mes, anio, calle, altura, ciudad, utmX, utmY, usuario, "Operador");
	}

	/**
	 * Constructor copia
	 * 
	 * @Param Lista Usuarios
	 */
	public Operador(Persona p, ListaUsuarios usuario)
	{
		super(p, usuario, "Operador");
	}

	/**
	 * Constructor que crea un operador a traves de un JSONObject
	 * 
	 * @param Persona
	 * @Param Lista Usuarios
	 */
	public Operador(JSONObject operador) throws JSONException
	{
		super(operador.getJSONObject("persona"), operador.getJSONObject("usuario"));
	}

	/**
	 * Devuelve un Viaje incompleto al chofer y cliente (salvo que pasajero sea 0),
	 * para que el chofer complete el viaje.
	 */
	public Viaje asignarViaje(Direccion direccion, double pasajero, String patente, String chofer, boolean cancelado)
	{
		Viaje viajeAux = new Viaje(direccion, pasajero, patente, 0, 0, chofer, cancelado, false);
		return viajeAux;
	}

	/**
	 * Transforma a un objeto de Operador en formato apto para JSONObject
	 * 
	 * @return obj: Objeto listo para ser cargado al JSONArray de usuarios
	 */
	public JSONObject operadorGetFormatoJson() throws JSONException
	{

		JSONObject obj = new JSONObject();
		obj.put("usuario", usuarioGetFormatoJson());
		obj.put("persona", personaGetFormatoJson());

		return obj;
	}

	@Override
	public Viaje cancelarViaje(Viaje viaje)
	{
		viaje.setCancelado(true);
		return null;
	}

	/**
	 * Muestra el historial de los viajes del usuario que se haya ingresado
	 */
	@Override
	public StringBuilder historial(ListaViajes viajes, Usuario user)
	{
		StringBuilder sb = new StringBuilder();
		if (user instanceof Chofer)
		{
			for (Viaje aux : viajes.getViajes())
			{
				if (((Chofer) user).getLicencia().equalsIgnoreCase(aux.getChofer()) == true)
				{

					sb.append(aux.toString() + "\n");
				}
			}

		}
		if (user instanceof Cliente)
		{
			for (Viaje aux : viajes.getViajes())
			{
				if (user.getId() == aux.getPasajero())
				{
					sb.append(aux.toString() + "\n");
				}
			}
		}
		return sb;
	}

	/**
	 * Muestra informacion respecto del usuario ingresado ( pudiendo ser el chofer o
	 * el cliente)
	 */
	@Override
	public String pedirInformacion(Usuario user)
	{
		String msj = "Error al pedir información";

		if (user instanceof Empresa)
		{
			msj = ((Empresa) user).toString();
		}

		if (user instanceof Particular)
		{
			msj = ((Particular) user).toString();
		}
		if (user instanceof Chofer)
		{
			msj = ((Chofer) user).toString();
		}
		return msj;
	}

	@Override
	public String toString()
	{
		return "Operador [" + super.toString() + "]";
	}

}
