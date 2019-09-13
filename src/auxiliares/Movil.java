package auxiliares;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public class Movil
{
	private String modelo;
	private String licencia;
	private String color;
	private String patente;

	/**
	 * Constructor vacio
	 */
	public Movil()
	{
		setModelo("nn");
		setLicencia("-1");
		setColor("nn");
		setPatente(" ");
	}

	/**
	 * Constructor con todos los atributos
	 */
	public Movil(String modelo, String color, String patente)
	{
		setModelo(modelo);
		setLicencia("0");
		setColor(color);
		setPatente(patente);
	}

	/**
	 * Constructor copia
	 */
	public Movil(Movil auto)
	{
		setModelo(auto.getModelo());
		setLicencia(auto.getLicencia());
		setColor(auto.getColor());
		setPatente(auto.getPatente());
	}

	/**
	 * Constructor que crea un movil a partir de un JSONObject
	 */
	public Movil(JSONObject movil) throws JSONException
	{
		setModelo(movil.getString("modelo"));
		setLicencia(movil.getString("licencia"));
		setColor(movil.getString("color"));
		setPatente(movil.getString("patente"));
	}

	public String getModelo()
	{
		return modelo;
	}

	private void setModelo(String modelo)
	{
		this.modelo = modelo;
	}

	public String getLicencia()
	{
		return licencia;
	}

	public void setLicencia(String licencia)
	{
		this.licencia = licencia;
	}

	public String getColor()
	{
		return color;
	}

	private void setColor(String color)
	{
		this.color = color;
	}

	public String getPatente()
	{
		return patente;
	}

	private void setPatente(String patente)
	{
		this.patente = patente;
	}

	/**
	 * Metodo que compara si la licencia por parametro es igual a la del objeto
	 * 
	 * @return true si son iguales, false en caso opuesto.
	 */
	public boolean comparaLicencia(String licencia)
	{
		boolean rta = false;
		if (getLicencia().equalsIgnoreCase(licencia))
			rta = true;
		return rta;
	}

	/**
	 * Transforma a un objeto de Movil en formato apto para JSONObject
	 * 
	 * 
	 * @return obj: Objeto listo para ser cargado al JSONArray de Movil
	 */
	public JSONObject movilGetFormatoJson() throws JSONException
	{
		JSONObject obj = new JSONObject();
		obj.put("modelo", getModelo());
		obj.put("licencia", getLicencia());
		obj.put("color", getColor());
		obj.put("patente", getPatente());

		return obj;
	}

	/**
	 * Equals basado en la patente para definir si 2 moviles son iguales
	 */
	@Override
	public boolean equals(Object obj)
	{

		boolean aux = false;
		if (obj instanceof Movil) //// Solo ingresa si el objeto en cuestion es de la Clase Movil
		{
			if (getPatente().equalsIgnoreCase(((Movil) obj).getPatente()))
			{ // Conversion en linea del objeto a la clase Movil
				// (antes verificamos que efectivamente sea de esa
				// clase)
				aux = true;
			}
		}
		return aux;
	}

	@Override
	public String toString()
	{
		return "Movil [modelo=" + getModelo() + ", licencia=" + getLicencia() + ", color=" + getColor() + ", patente="
				+ getPatente() + "]";
	}

}
