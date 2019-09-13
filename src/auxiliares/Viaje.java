package auxiliares;

import org.json.JSONException;
import org.json.JSONObject;

import colecciones.ListaViajes;


/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public class Viaje
{
	private Direccion direccionInicial;
	private Direccion direccionFinal;
	private double pasajero;
	private String patenteMovil;
	private float distancia;
	private float monto;
	private String chofer;
	private int numeroViaje;
	private boolean cancelado; // True si se cancelo/fue cancelado
	private boolean finalizado; // True si esta completado, false en caso contrario.

	private static int numeroViajeAux = 1; // Se utiliza para definir el numero de viaje
	private static float tarifaPorKilometro = 15.40f;

	/**
	 * Consturctor vacio.
	 */
	public Viaje()
	{
		this.direccionInicial = new Direccion();
		this.direccionFinal = new Direccion();
		setPasajero(-1);
		setMovil("nn");
		setDistancia(0);
		setMonto(0);
		setChofer("-1");
		setNumeroViaje(numeroViajeAux++);
		setCancelado(false);
		setFinalizado(true);
	}

	/**
	 * Constructor que crea un viaje con los datos de partida.
	 * 
	 */
	public Viaje(Direccion direccion, double pasajero, String patente, float monto, float distancia, String chofer,
			boolean cancelado, boolean finalizado)
	{
		setDireccionInicial(direccion.getCalle(), direccion.getAltura(), direccion.getCiudad(), direccion.getUtmX(),
				direccion.getUtmY());
		this.direccionFinal = new Direccion();
		setPasajero(pasajero);
		setMovil(patente);
		setDistancia(distancia);
		setMonto(monto);
		setChofer(chofer);
		setNumeroViaje(numeroViajeAux++);
		setCancelado(cancelado);
		setFinalizado(finalizado);
	}

	/**
	 * Constructor que crea un viaje completo y finalizado.
	 * 
	 */
	public Viaje(Direccion direccionInicial, Direccion direccionFinal, double pasajero, String patenteMovil,
			float monto, float distancia, String chofer, boolean cancelado)
	{
		setDireccionInicial(direccionInicial.getCalle(), direccionInicial.getAltura(), direccionInicial.getCiudad(),
				direccionInicial.getUtmX(), direccionInicial.getUtmY());
		setDireccionFinal(direccionFinal.getCalle(), direccionFinal.getAltura(), direccionFinal.getCiudad(),
				direccionFinal.getUtmX(), direccionFinal.getUtmY());
		setPasajero(pasajero);
		setMovil(patenteMovil);
		setDistancia(distancia);
		setMonto(monto);
		setChofer(chofer);
		setNumeroViaje(numeroViajeAux++);
		setCancelado(cancelado);
		setFinalizado(true);
	}

	/**
	 * Constructor de Viaje a partir de un JSONObject
	 * 
	 */
	public Viaje(JSONObject viaje) throws JSONException
	{
		setDireccionInicial(viaje.getJSONObject("direccionInicial").getString("calle"), viaje.getJSONObject(
				"direccionInicial").getInt("altura"), viaje.getJSONObject("direccionInicial").getString("ciudad"),
				((float) viaje.getJSONObject("direccionInicial").getDouble("utmX")), ((float) viaje.getJSONObject(
						"direccionInicial").getDouble("utmY")));
		setDireccionFinal(viaje.getJSONObject("direccionFinal").getString("calle"), viaje.getJSONObject(
				"direccionFinal").getInt("altura"), viaje.getJSONObject("direccionFinal").getString("ciudad"),
				((float) viaje.getJSONObject("direccionFinal").getDouble("utmX")), ((float) viaje.getJSONObject(
						"direccionFinal").getDouble("utmY")));

		setPasajero(viaje.getDouble("pasajero"));
		setMovil(viaje.getString("patenteMovil"));
		setDistancia((float) viaje.getDouble("distancia"));
		setMonto((float) viaje.getDouble("monto"));
		setChofer(viaje.getString("chofer"));
		setNumeroViaje(viaje.getInt("numeroViaje"));
		setCancelado(viaje.getBoolean("cancelado"));
		setFinalizado(viaje.getBoolean("finalizado"));
	}

	public Direccion getDireccionInicial()
	{
		return direccionInicial;
	}

	private void setDireccionInicial(String calle, int altura, String ciudad, float utmX, float utmY)
	{
		this.direccionInicial = new Direccion(calle, altura, ciudad, utmX, utmY);
	}

	public Direccion getDireccionFinal()
	{
		return direccionFinal;
	}

	private void setDireccionFinal(String calle, int altura, String ciudad, float utmX, float utmY)
	{
		this.direccionFinal = new Direccion(calle, altura, ciudad, utmX, utmY);
	}

	public double getPasajero()
	{
		return pasajero;
	}

	public void setPasajero(double pasajero)
	{
		this.pasajero = pasajero;
	}

	public String getMovil()
	{
		return patenteMovil;
	}

	public void setMovil(String patente)
	{
		this.patenteMovil = patente;
	}

	public float getDistancia()
	{
		return distancia;
	}

	public void setDistancia(float distancia)
	{
		this.distancia = distancia;
	}

	public float getMonto()
	{
		return monto;
	}

	public void setMonto(float monto)
	{
		this.monto = monto;
	}

	public String getChofer()
	{
		return chofer;
	}

	public void setChofer(String chofer)
	{
		this.chofer = chofer;
	}

	public int getNumeroViaje()
	{
		return numeroViaje;
	}

	private void setNumeroViaje(int numeroViajeAux)
	{
		this.numeroViaje = numeroViajeAux;
	}

	public boolean getCancelado()
	{
		return cancelado;
	}

	public void setCancelado(boolean cancelado)
	{
		this.cancelado = cancelado;
	}

	public static float getTarifaPorKilometro()
	{
		return tarifaPorKilometro;
	}

	public static void setTarifaPorKilometro(float tarifaPorKilometro)
	{
		Viaje.tarifaPorKilometro = tarifaPorKilometro;
	}

	public boolean getFinalizado()
	{
		return finalizado;
	}

	public void setFinalizado(boolean finalizado)
	{
		this.finalizado = finalizado;
	}

	public static void actualizaContador(ListaViajes viaje)
	{
		Viaje.numeroViajeAux = (viaje.getViajes().size() + 1);
	}

	/**
	 * Transforma a un objeto de Viaje en formato apto para JSONObject
	 * 
	 * @return obj: Objeto listo para ser cargado al JSONArray de viajes
	 */
	public JSONObject viajeGetFormatoJson() throws JSONException
	{
		JSONObject obj = new JSONObject();
		obj.put("direccionInicial", getDireccionInicial().direccionGetFormatoJson());
		obj.put("direccionFinal", getDireccionFinal().direccionGetFormatoJson());
		obj.put("pasajero", getPasajero());
		obj.put("patenteMovil", getMovil());
		obj.put("distancia", getDistancia());
		obj.put("monto", getMonto());
		obj.put("chofer", getChofer());
		obj.put("numeroViaje", getNumeroViaje());
		obj.put("cancelado", getCancelado());
		obj.put("finalizado", getFinalizado());
		return obj;

	}

	@Override
	public String toString()
	{
		return "Viaje [direccionInicial=" + getDireccionInicial() + ", direccionFinal=" + getDireccionFinal()
				+ ", ID pasajero " + getPasajero() + ", movil=" + getMovil() + ", distancia=" + getDistancia()
				+ ", monto=" + getMonto() + ", Licencia chofer=" + getChofer() + ", numeroViaje=" + getNumeroViaje()
				+ ", cancelado=" + getCancelado() + "]";
	}


	/**
	 * Basado en el numero de Viaje
	 */
	@Override
	public boolean equals(Object obj)
	{

		boolean aux = false;
		if (obj instanceof Viaje) //// Solo ingresa si el objeto en cuestion es de la Clase Viaje
		{
			if (getNumeroViaje() == ((Viaje) obj).getNumeroViaje())
			{ // Conversion en linea del objeto a la clase
				aux = true; // Viaje (antes verificamos quee fectivamente sea de
			} // esa clase)
		}
		return aux;
	}
}
