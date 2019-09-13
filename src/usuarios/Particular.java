package usuarios;

import org.json.JSONException;
import org.json.JSONObject;

import auxiliares.Viaje;
import colecciones.ListaUsuarios;
import colecciones.ListaViajes;


/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public class Particular extends Cliente
{
	private Persona persona;

	/**
	 * Constructor vacio
	 */
	public Particular()
	{
		super();
		persona = new Persona();
	}

	/**
	 * Crear cliente particular con saldo 0
	 */
	public Particular(String nombreApellido, String dni, int dia, int mes, int anio, String calle, int altura,
			String ciudad, float utmX, float utmY, ListaUsuarios usuario)
	{
		super(usuario, "Particular");
		setPersona(nombreApellido, dni, dia, mes, anio, calle, altura, ciudad, utmX, utmY);
	}

	/**
	 * Crear cliente particular especificando saldo
	 */
	public Particular(String nombreApellido, String dni, int dia, int mes, int anio, String calle, int altura,
			String ciudad, float utmX, float utmY, ListaUsuarios usuario, float saldo)
	{
		super(usuario, "Particular", saldo);
		setPersona(nombreApellido, dni, dia, mes, anio, calle, altura, ciudad, utmX, utmY);
	}

	/**
	 * Constructor con persona creada
	 *
	 */
	public Particular(Persona p, ListaUsuarios usuario)
	{
		super(usuario, "Particular");
		this.persona = new Persona(p);
	}

	/**
	 * Constructor que crea un cliente particular a partir de un JSONObject
	 * 
	 * @param particular
	 */
	public Particular(JSONObject particular) throws JSONException
	{
		super(particular.getJSONObject("cliente"), particular.getJSONObject("usuario"));
		this.persona = new Persona(particular.getJSONObject("persona"));
	}

	public Persona getP()
	{
		return persona;
	}

	/**
	 * Instancia a la persona( metodo llamado dentro del constructor)
	 *
	 */
	public void setPersona(String nombreApellido, String dni, int dia, int mes, int anio, String calle, int altura,
			String ciudad, float utmX, float utmY)
	{
		this.persona = new Persona(nombreApellido, dni, dia, mes, anio, calle, altura, ciudad, utmX, utmY);
	}

	/**
	 * Transforma a un objeto de Particular en formato apto para JSONObject
	 * 
	 * 
	 * @return obj: Objeto listo para ser cargado al JSONArray de usuarios
	 * @throws JSONException
	 */
	public JSONObject particularGetFormatoJson() throws JSONException
	{

		JSONObject obj = new JSONObject();
		obj.put("usuario", usuarioGetFormatoJson());
		obj.put("cliente", clienteGetFormatoJson());
		obj.put("persona", getP().personaGetFormatoJson());

		return obj;
	}

	/**
	 * Devuelve true si son iguales(Usando el equals de Persona como referencia)
	 */
	public boolean equals(Object obj)
	{
		if (obj != null && obj instanceof Cliente)
		{
			return persona.equals(obj);
		} else
			return false;
	}

	@Override
	public String toString()
	{
		return super.toString() + "Particular [=" + getP().toString() + "]";
	}

	/**
	 * Muestra el historial de viajes que realizo el particular
	 */
	@Override
	public StringBuilder historial(ListaViajes viajes, Usuario user)
	{
		StringBuilder sb = new StringBuilder();
		for (Viaje aux : viajes.getViajes())
		{
			if (user.getId() == aux.getPasajero())
			{
				sb.append(aux.toString() + "\n");
			}
		}
		return sb;
	}

	/**
	 * Muestra la informacion del chofer del viaje actual
	 */
	@Override
	public String pedirInformacion(Usuario user)
	{
		String msj = "Error al pedir información";
		if (user instanceof Chofer)
		{
			msj = ((Chofer) user).toStringParaCliente();
		}
		return msj;
	}
}
