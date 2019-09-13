package usuarios;

import org.json.JSONException;
import org.json.JSONObject;

import colecciones.ListaUsuarios;


/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public abstract class Usuario
{
	private int id; // Numero usuario en sistema
	private int clave; // Numero de clave en sistema
	private String tipoUsuario; // Tipo de usuario, lleva nombre de clase a la cual pertenece

	/**
	 * Constructor vacio.
	 */
	public Usuario()
	{
		setId(-1);
		setClave(0);
		setTipoUsuario("nn");
	}

	/**
	 * Constructor id y tipoUsuario.
	 * 
	 */
	public Usuario(ListaUsuarios usuario, String tipoUsuario)
	{
		creaId(usuario);
		setClave(1111);
		setTipoUsuario(tipoUsuario);
	}

	/**
	 * Constructor que crea un objeto a partir de un JSONObject
	 */
	public Usuario(JSONObject usuario) throws JSONException
	{
		setId(usuario.getInt("id"));
		setClave(usuario.getInt("clave"));
		setTipoUsuario(usuario.getString("tipoUsuario"));
	}

	public int getId()
	{
		return id;
	}

	private void setId(int id)
	{
		this.id = id;
	}

	private int getClave()
	{
		return this.clave;
	}

	private void setClave(int clave)
	{
		this.clave = clave;
	}

	private void creaId(ListaUsuarios usuario)
	{
		setId(usuario.cantidadUsuario() + 1);
	}

	/**
	 * Modifica clave si sabe la vieja e ingresa una nueva.
	 * 
	 * @param Clave actual.
	 * @param Clave nueva.
	 * @return Resolucion de la operacion.
	 */
	public String modificarClave(int claveIngresada, int claveNueva)
	{
		if (compruebaClave(claveIngresada))
		{
			setClave(claveNueva);
			return "Clave modificada correctamente.";
		} else
		{
			return "Clave ingresada incorrecta.";
		}
	}

	/**
	 * Comprueba la clave asignada con la recibida por parametro
	 * 
	 * @return Retorna true si es y si no retorna false.
	 */
	public boolean compruebaClave(int clave)
	{
		if (getClave() == clave)
			return true;
		else
			return false;
	}

	private void setTipoUsuario(String tipoUsuario)
	{
		this.tipoUsuario = tipoUsuario;
	}

	public String getTipoUsuario()
	{
		return tipoUsuario;
	}

	/**
	 * Transforma los atributos de Usuario en formato apto para JSONObject
	 * 
	 * 
	 * @return obj: Se utiliza en sus subclases para formar el JSONObject completo
	 *         de la misma
	 */
	public JSONObject usuarioGetFormatoJson() throws JSONException
	{
		JSONObject obj = new JSONObject();
		obj.put("id", getId());
		obj.put("clave", getClave());
		obj.put("tipoUsuario", getTipoUsuario());
		return obj;
	}

	/**
	 * Usando el id como base para definir si 2 Usuarios son iguales
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj != null && obj instanceof Usuario)
		{
			if (getId() == ((Usuario) obj).getId())
				return true;
			else
				return false;
		} else
			return false;
	}


	@Override
	public String toString()
	{
		return "id=" + id;
	}
}
