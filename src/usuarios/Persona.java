package usuarios;

import org.json.JSONException;
import org.json.JSONObject;

import auxiliares.Direccion;
import colecciones.ListaUsuarios;


/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 */
public class Persona extends Usuario
{
	private String nombreApellido;
	private String dni;
	private String fechaNacimiento;
	private Direccion residencia;

	/**
	 * Constructor vacio
	 */
	public Persona()
	{
		super();
		setNombreApellido("nn");
		setDni("-1");
		setFechaNacimiento(0, 0, 0);
		residencia = new Direccion();
	}

	/**
	 * Constructor completo
	 */
	public Persona(String nombreApellido, String dni, int dia, int mes, int anio, String calle, int altura,
			String ciudad, float utmX, float utmY, ListaUsuarios usuario, String tipoUsuario)
	{
		super(usuario, tipoUsuario);
		setNombreApellido(nombreApellido);
		setDni(dni);
		setFechaNacimiento(dia, mes, anio);
		setResidencia(calle, altura, ciudad, utmX, utmY);
	}

	/**
	 * Constructor de persona
	 * 
	 * @param Dia    Nacimiento
	 * @param Mes    Nacimiento
	 * @param Anio   Nacimiento
	 * @param Calle  Residencia
	 * @param Altura Residencia
	 * @param Ciudad Residencia
	 * 
	 */
	public Persona(String nombreApellido, String dni, int dia, int mes, int anio, String calle, int altura,
			String ciudad, float utmX, float utmY)
	{
		super();
		setNombreApellido(nombreApellido);
		setDni(dni);
		setFechaNacimiento(dia, mes, anio);
		setResidencia(calle, altura, ciudad, utmX, utmY);
	}

	/**
	 * Constructor copia.
	 */
	public Persona(Persona p, ListaUsuarios usuario, String tipoUsuario)
	{
		super(usuario, tipoUsuario);
		setNombreApellido(p.getNombreApellido());
		setDni(p.getDni());
		copiaFechaNacimiento(p.getFechaNacimiento());
		this.residencia = new Direccion(p.residencia);
	}

	/**
	 * Constructor persona.
	 */
	public Persona(Persona p)
	{
		setNombreApellido(p.getNombreApellido());
		setDni(p.getDni());
		copiaFechaNacimiento(p.getFechaNacimiento());
		this.residencia = new Direccion(p.residencia);
	}

	/**
	 * Constructor que crea una persona a partir de un JSONObject
	 * 
	 */
	public Persona(JSONObject persona, JSONObject usuario) throws JSONException
	{
		super(usuario);
		setNombreApellido(persona.getString("nombreApellido"));
		setDni(persona.getString("dni"));
		copiaFechaNacimiento(persona.getString("fechaNacimiento"));
		setResidencia(persona.getJSONObject("residencia").getString("calle"), persona.getJSONObject("residencia")
				.getInt("altura"), persona.getJSONObject("residencia").getString("ciudad"), ((float) persona
						.getJSONObject("residencia").getDouble("utmX")), ((float) persona.getJSONObject("residencia")
								.getDouble("utmY")));
	}

	/**
	 * Constructor que crea una persona a partir de un JSONObject
	 */
	public Persona(JSONObject persona) throws JSONException
	{
		setNombreApellido(persona.getString("nombreApellido"));
		setDni(persona.getString("dni"));
		copiaFechaNacimiento(persona.getString("fechaNacimiento"));
		setResidencia(persona.getJSONObject("residencia").getString("calle"), persona.getJSONObject("residencia")
				.getInt("altura"), persona.getJSONObject("residencia").getString("ciudad"), ((float) persona
						.getJSONObject("residencia").getDouble("utmX")), ((float) persona.getJSONObject("residencia")
								.getDouble("utmY")));
	}

	public String getNombreApellido()
	{
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido)
	{
		this.nombreApellido = nombreApellido;
	}

	public String getDni()
	{
		return dni;
	}

	private void setDni(String dni)
	{
		this.dni = dni;
	}

	public String getFechaNacimiento()
	{
		return fechaNacimiento;
	}

	private void setFechaNacimiento(int dia, int mes, int anio)
	{
		this.fechaNacimiento = dia + "/" + mes + "/" + anio + ".";
	}

	private void copiaFechaNacimiento(String fecha)
	{
		this.fechaNacimiento = fecha;
	}

	public Direccion getResidencia()
	{
		return residencia;
	}

	private void setResidencia(String calle, int altura, String ciudad, float utmX, float utmY)
	{
		this.residencia = new Direccion(calle, altura, ciudad, utmX, utmY);
	}

	/**
	 * Transforma los atributos de Persona en formato apto para JSONObject
	 * 
	 * @return obj: Se utiliza en sus subclases para formar el JSONObject completo
	 *         de la misma
	 */
	public JSONObject personaGetFormatoJson() throws JSONException
	{
		JSONObject obj = new JSONObject();

		obj.put("nombreApellido", getNombreApellido());
		obj.put("dni", getDni());
		obj.put("fechaNacimiento", getFechaNacimiento());
		obj.put("residencia", getResidencia().direccionGetFormatoJson());

		return obj;
	}

	/**
	 * Usando como base el dni para determinar si 2 personas son la misma
	 */
	public boolean equals(Object obj)
	{
		if (obj != null && obj instanceof Persona)
		{
			if (getDni().equalsIgnoreCase(((Persona) obj).getDni()))
				return true;
			else
				return false;
		} else
			return false;
	}

	@Override
	public String toString()
	{
		return "Persona [nombreApellido=" + getNombreApellido() + ", dni=" + getDni() + ", fechaNacimiento="
				+ getFechaNacimiento() + ". " + getResidencia().toString();
	}


}