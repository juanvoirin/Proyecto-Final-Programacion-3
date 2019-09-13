package usuarios;

import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import auxiliares.Direccion;
import auxiliares.Viaje;
import colecciones.ListaUsuarios;
import colecciones.ListaViajes;

/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public class Empresa extends Cliente
{
	private String nombre;
	private HashSet<Persona> habilitados;
	private Direccion direccion;

	/**
	 * Constructor vacio
	 */
	public Empresa()
	{
		super();
		setNombre("nn");
		direccion = new Direccion();
		habilitados = new HashSet<Persona>();
	}

	/**
	 * Constructor con atributos
	 * 
	 * @param Objeto Direccion
	 */
	public Empresa(ListaUsuarios usuario, String nombre, Direccion direccion)
	{
		super(usuario, "Empresa");
		setNombre(nombre);
		this.direccion = new Direccion(direccion);
		habilitados = new HashSet<Persona>();
	}

	/**
	 * Constructor completo
	 * 
	 * @param Objeto    Direccion
	 * @param Coleccion HashSet de personas habilitadas
	 */
	public Empresa(ListaUsuarios usuario, String nombre, Direccion direccion, HashSet<Persona> habilitados)
	{
		super(usuario, "Empresa");
		setNombre(nombre);
		this.direccion = new Direccion(direccion);
		this.habilitados = new HashSet<Persona>();
		getHabilitados().addAll(habilitados);
	}

	/**
	 * Constructor que crea una empresa a partir de un JSONObject.
	 * 
	 */
	public Empresa(JSONObject empresa) throws JSONException
	{
		super(empresa.getJSONObject("cliente"), empresa.getJSONObject("usuario"));
		setNombre(empresa.getString("nombre"));
		this.direccion = new Direccion(empresa.getJSONObject("direccion"));
		this.habilitados = listaDesdeJSON(empresa.getJSONArray("habilitados"));
	}

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public HashSet<Persona> getHabilitados()
	{
		return habilitados;
	}

	public void setHabilitado(HashSet<Persona> habilitado)
	{
		this.habilitados = habilitado;
	}

	public Direccion getDireccion()
	{
		return this.direccion;
	}

	private HashSet<Persona> listaDesdeJSON(JSONArray habilitado) throws JSONException
	{
		HashSet<Persona> rta = new HashSet<Persona>();
		int contador = 0;
		while (contador < habilitado.length())
		{
			Persona p = new Persona(habilitado.getJSONObject(contador));
			rta.add(p);
			contador++;
		}
		return rta;
	}

	/**
	 * Agrega una Persona a la coleccion
	 * 
	 * @param Objeto Persona
	 * @return Devuelve true si lo agrega, false si habia otro igual
	 */
	public boolean agregarHabilitado(Persona p)
	{
		boolean aux = true;
		getHabilitados().add(p);
		for (Persona aux2 : getHabilitados())
		{
			if (p.equals(aux2))
			{
				aux = false;
			}
		}
		return aux;
	}

	/**
	 * Elimina en base al id recibido por parametro.
	 * 
	 * @param DNI en String
	 */
	public void eliminarHabilitado(String dni)
	{
		for (Persona aux : getHabilitados())
		{
			if (aux.getDni() == dni)
			{
				getHabilitados().remove(aux);
			}
		}
	}

	/**
	 * Recorre y lista todos los Usuarios.
	 * 
	 * @return Devuelve StringBuilder con todos los usuarios.
	 */
	public StringBuilder listarHabilitados()
	{

		StringBuilder sb = new StringBuilder();

		for (Persona aux : getHabilitados())
		{
			sb.append(aux.toString() + "\n");
		}
		return sb;
	}

	/**
	 * Transforma a un objeto de Empresa en formato apto para JSONObject
	 * 
	 * @return obj: Objeto listo para ser cargado al JSONArray de usuarios
	 * @throws JSONException
	 */
	public JSONObject empresaGetFormatoJson() throws JSONException
	{

		JSONObject obj = new JSONObject();
		obj.put("usuario", usuarioGetFormatoJson());
		obj.put("cliente", clienteGetFormatoJson());
		obj.put("nombre", getNombre());
		obj.put("direccion", getDireccion().direccionGetFormatoJson());
		JSONArray habilitados = new JSONArray();
		for (Persona p : getHabilitados())
		{
			habilitados.put(p.personaGetFormatoJson());
		}
		obj.put("habilitados", habilitados);
		return obj;
	}

	@Override
	public String toString()
	{
		return super.toString() + " Empresa [nombre=" + getNombre() + ", habilitados=" + listarHabilitados()
				+ ", direccion=" + getDireccion().toString() + "]";
	}

	/**
	 * Muestra el historial de viajes que realizo la empresa
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
