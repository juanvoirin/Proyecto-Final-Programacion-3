package auxiliares;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public class Direccion
{
	private String calle;
	private int altura;
	private String ciudad;
	private float utmX;
	private float utmY;

	/**
	 * Constructor vacio
	 */
	public Direccion()
	{
		setCalle("Desconocida");
		setAltura(-1);
		setCiudad("Desconocida");
		setUtmX(0);
		setUtmY(0);
	}

	/**
	 * Constructor completo
	 * 
	 */
	public Direccion(String calle, int altura, String ciudad, float utmX, float utmY)
	{
		setCalle(calle);
		setAltura(altura);
		setCiudad(ciudad);
		setUtmX(utmX);
		setUtmY(utmY);
	}

	/**
	 * Constructor copia
	 * 
	 * @param Objeto Direccion
	 */
	public Direccion(Direccion direccion)
	{
		setCalle(direccion.getCalle());
		setAltura(direccion.getAltura());
		setCiudad(direccion.getCiudad());
		setUtmX(direccion.utmX);
		setUtmY(direccion.utmY);
	}

	/**
	 * Constructor a partir de un JSONObject
	 * 
	 */
	public Direccion(JSONObject direccion) throws JSONException
	{
		setCalle(direccion.getString("calle"));
		setAltura(direccion.getInt("altura"));
		setCiudad(direccion.getString("ciudad"));
		setUtmX((float) direccion.getDouble("utmX"));
		setUtmY((float) direccion.getDouble("utmY"));
	}

	public String getCalle()
	{
		return calle;
	}

	public void setCalle(String calle)
	{
		this.calle = calle;
	}

	public int getAltura()
	{
		return altura;
	}

	public void setAltura(int altura)
	{
		this.altura = altura;
	}

	public String getCiudad()
	{
		return ciudad;
	}

	public void setCiudad(String ciudad)
	{
		this.ciudad = ciudad;
	}

	public float getUtmX()
	{
		return utmX;
	}

	public void setUtmX(float utmX)
	{
		this.utmX = utmX;
	}

	public float getUtmY()
	{
		return utmY;
	}

	public void setUtmY(float utmY)
	{
		this.utmY = utmY;
	}

	/**
	 * Comprueba si tienen la misma direccion.
	 * 
	 * @return True si son iguales, false en caso contrario.
	 */
	public boolean compruebaDireccion(String calle, int altura, String ciudad)
	{
		boolean rta = false;
		if (getCiudad().equalsIgnoreCase(ciudad))
		{
			if (getCalle().equalsIgnoreCase(calle))
			{
				if (getAltura() == altura)
					rta = true;
			}
		}
		return rta;
	}

	/**
	 * Transforma a un objeto de Direccion en formato apto para JSONObject
	 * 
	 * @return obj: Objeto listo para los metodos que requieran direcciones en
	 *         JSONObject o para la el JSONArray de direcciones.
	 */
	public JSONObject direccionGetFormatoJson() throws JSONException
	{
		JSONObject obj = new JSONObject();
		obj.put("calle", getCalle());
		obj.put("altura", getAltura());
		obj.put("ciudad", getCiudad());
		obj.put("utmX", getUtmX());
		obj.put("utmY", getUtmY());

		return obj;
	}

	@Override
	public String toString()
	{
		return "Calle:" + getCalle() + " al " + getAltura() + ", " + getCiudad();
	}

	/**
	 * Devuelve true si son iguales.
	 */
	@Override
	public boolean equals(Object obj)
	{
		boolean rta = false;
		if (obj != null && obj instanceof Direccion)
		{
			if (getUtmX() == ((Direccion) obj).getUtmX())
			{
				if (getUtmY() == ((Direccion) obj).getUtmY())
					rta = true;
			}
		}
		return rta;
	}


}