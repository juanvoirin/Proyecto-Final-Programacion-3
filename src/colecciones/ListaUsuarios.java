package colecciones;

import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import usuarios.Chofer;
import usuarios.Cliente;
import usuarios.Empresa;
import usuarios.Operador;
import usuarios.Particular;
import usuarios.Usuario;

/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public class ListaUsuarios
{
	private HashSet<Usuario> usuarios;

	/**
	 * Constructor de la coleccion vacio
	 */
	public ListaUsuarios()
	{
		usuarios = new HashSet<Usuario>();
	}

	/**
	 * Constructor copia
	 * 
	 * @param Recibe coleccion HashSet de usuarios
	 */
	public ListaUsuarios(HashSet<Usuario> usuario)
	{
		usuarios = new HashSet<Usuario>();
		getUsuarios().addAll(usuario);
	}

	/**
	 * Constructor que crea la coleccion a partir de un JSONArray
	 */
	public ListaUsuarios(JSONArray aux) throws JSONException
	{
		usuarios = JSONToColeccion(aux);
	}

	private HashSet<Usuario> getUsuarios()
	{
		return usuarios;
	}

	/**
	 * Agrega un usuario a la coleccion
	 * 
	 * @param Objeto Usuario
	 * @return Devuelve true si lo agrega, false si habia otro igual.
	 */
	public boolean agregarUsuario(Usuario user)
	{

		return getUsuarios().add(user);
	}

	/**
	 * Recorre y lista todos los Usuarios.
	 * 
	 * @return Devuelve StringBuilder con todos los usuarios.
	 */
	public StringBuilder listarUsuarios()
	{

		StringBuilder sb = new StringBuilder();

		for (Usuario aux : getUsuarios())
		{
			sb.append(aux.toString() + "\n\n");
		}
		return sb;
	}

	/**
	 * 
	 * @return Devuelve un StringBuilder con todos los Clientes
	 */

	public StringBuilder listarClientes()
	{

		StringBuilder sb = new StringBuilder();

		for (Usuario aux : getUsuarios())
		{
			if (aux instanceof Cliente)
			{
				sb.append(aux.toString() + "\n\n");
			}
		}
		return sb;
	}

	/**
	 * Elimina en base al id recibido por parametro La comprobacion se hace en las
	 * otras formulas que van en el main
	 */
	public String eliminarUsuario(int id)
	{
		String rta = "Este id de usuario no existe.";
		for (Usuario aux : getUsuarios())
		{
			if (aux.getId() == id)
			{
				getUsuarios().remove(aux);
				rta = "Usuario borrado con exito.";
			}
		}
		return rta;
	}

	/**
	 * Devuelve el usuario con mismo id de parametro.
	 * 
	 * @return Retorna un usuario o null en caso que no exista.
	 */
	public Usuario buscaUsuario(int id)
	{
		Usuario rta = null;
		for (Usuario aux : getUsuarios())
		{
			if (aux.getId() == id)
			{
				rta = aux;
			}
		}
		return rta;
	}

	/**
	 * Busca chofer a traves de su licencia.
	 * 
	 * @return Retorna un Chofer o null en caso que no exista
	 */
	public Usuario buscaChofer(String licencia)
	{
		Usuario rta = null;
		for (Usuario aux : getUsuarios())
		{
			if (aux instanceof Chofer)
			{
				if (((Chofer) aux).getLicencia().equalsIgnoreCase(licencia))
				{
					rta = aux;
				}

			}

		}
		return rta;
	}

	/**
	 * Busca chofer sin movil.
	 * 
	 * @return Retorna un chofer no asignado o null.
	 */
	public Usuario choferLibre()
	{
		Usuario rta = null;
		for (Usuario aux : getUsuarios())
		{
			if (aux instanceof Chofer)
			{
				if (!((Chofer) aux).getAsignado())
					rta = aux;
			}
		}
		return rta;
	}

	/**
	 * Busca un operador, si existe devuelve true, sino false.
	 * 
	 * @return true/false
	 */
	public boolean hayOperador()
	{
		boolean rta = false;
		for (Usuario aux : getUsuarios())
		{
			if (aux.getTipoUsuario().equalsIgnoreCase("Operador"))
				rta = true;
		}
		return rta;
	}

	/**
	 * @return Devuelve cantidad de usuarios en la coleccion.
	 */
	public int cantidadUsuario()
	{
		return getUsuarios().size();
	}

	/**
	 * Construye el Array de Usuarios en Formato JSON y lo ingresa al final a un
	 * JSONObject con clave "usuarios" Este metodo se llama desde el main (alla se
	 * hace el try/catch)
	 * 
	 * 
	 * @return definitivo: JSONObject que contiene Array de usuarios en formato JSON
	 */
	public JSONArray coleccionUsuariosAJSON() throws JSONException
	{
		JSONArray array = new JSONArray();
		for (Usuario aux : getUsuarios())
		{
			array.put(seleccionaTipoUsuario(aux));
		}
		return array;
	}

	/**
	 * Metodo auxiliar de coleccionUsuariosAJSON (para modularizar tareas)
	 */
	private JSONObject seleccionaTipoUsuario(Usuario aux) throws JSONException
	{
		JSONObject obj = new JSONObject();
		if (aux instanceof Chofer)
		{
			obj.put("chofer", ((Chofer) aux).choferGetFormatoJson());
		}
		if (aux instanceof Operador)
		{
			obj.put("operador", ((Operador) aux).operadorGetFormatoJson());
		}
		if (aux instanceof Empresa)
		{
			obj.put("empresa", ((Empresa) aux).empresaGetFormatoJson());
		}
		if (aux instanceof Particular)
		{
			obj.put("particular", ((Particular) aux).particularGetFormatoJson());
		}
		return obj;
	}

	/**
	 * Crea la coleccion a partir de un JSONArray
	 * 
	 * @return Coleccion con lectura del JSONArray
	 */
	private HashSet<Usuario> JSONToColeccion(JSONArray aux) throws JSONException
	{
		HashSet<Usuario> rta = new HashSet<Usuario>();
		int contador = 0;
		while (contador < aux.length())
		{
			rta.add(devuelveUsuario(aux.getJSONObject(contador)));
			contador++;
		}
		return rta;
	}

	/**
	 * Metodo auxiliar de constructor a partir de JSONArray (para modularizar
	 * tareas)
	 */
	private Usuario devuelveUsuario(JSONObject aux) throws JSONException
	{
		Usuario rta;
		if (aux.has("chofer"))
		{
			rta = new Chofer(aux.getJSONObject("chofer"));
		} else
		{
			if (aux.has("operador"))
			{
				rta = new Operador(aux.getJSONObject("operador"));
			} else
			{
				if (aux.has("empresa"))
				{
					rta = new Empresa(aux.getJSONObject("empresa"));
				} else
				{
					rta = new Particular(aux.getJSONObject("particular"));
				}
			}
		}
		return rta;
	}
}
