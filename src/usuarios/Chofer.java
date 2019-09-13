package usuarios;

import org.json.JSONException;
import org.json.JSONObject;

import auxiliares.Direccion;
import auxiliares.Viaje;
import colecciones.ListaUsuarios;
import colecciones.ListaViajes;
import interfaces.IHerramientasViaje;
import interfaces.IInformacion;
import mapaDeCalor.Coordenadas;


/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public class Chofer extends Persona implements IInformacion, IHerramientasViaje
{
	private float recaudado; // Se baja se vuelve 0.
	private int cantViajes; // Se baja se vuelve 0.
	private boolean ocupado;
	private boolean disponible; // True o false segun su disponibilidad
	private String licencia; // Numero de licencia del chofer.
	private boolean asignado; // True o false si tiene asignado un movil.


	/**
	 * Constructor vacio
	 */
	public Chofer()
	{
		super();
		setRecaudado(0);
		setCantViajes(0);
		setOcupado(false);
		setDisponible(false);
		setLicencia("");
	}

	/**
	 * Constructor en caso que se quiera crear un chofer que esta trabajando
	 * actualmente.
	 * 
	 */
	public Chofer(String nombreApellido, String dni, int dia, int mes, int anio, String calle, int altura,
			String ciudad, float utmX, float utmY, float recaudado, int cantViajes, boolean ocupado, boolean disponible,
			String licencia, ListaUsuarios usuario)
	{
		super(nombreApellido, dni, dia, mes, anio, calle, altura, ciudad, utmX, utmY, usuario, "Chofer");
		setRecaudado(recaudado);
		setCantViajes(cantViajes);
		setOcupado(ocupado);
		setDisponible(disponible);
		setLicencia(licencia);
	}

	/**
	 * Constructor que crea un chofer pero no se le asigno trabajo.
	 * 
	 */
	public Chofer(String nombreApellido, String dni, int dia, int mes, int anio, String calle, int altura,
			String ciudad, float utmX, float utmY, String licencia, ListaUsuarios usuario)
	{
		super(nombreApellido, dni, dia, mes, anio, calle, altura, ciudad, utmX, utmY, usuario, "Chofer");
		setRecaudado(0);
		setCantViajes(0);
		setOcupado(false);
		setDisponible(false);
		setLicencia(licencia);
	}

	/**
	 * Constructor que crea un chofer pero no se le asigno trabajo.
	 */
	public Chofer(Persona p, String licencia, ListaUsuarios usuario)
	{
		super(p, usuario, "Chofer");
		setRecaudado(0);
		setCantViajes(0);
		setOcupado(false);
		setDisponible(false);
		setLicencia(licencia);
	}

	/**
	 * Constructor que crea un chofer desde JSON
	 * 
	 */
	public Chofer(JSONObject chofer) throws JSONException
	{
		super(chofer.getJSONObject("persona"), chofer.getJSONObject("usuario"));
		setRecaudado((float) chofer.getLong("recaudado"));
		setCantViajes(chofer.getInt("cantViajes"));
		setOcupado(chofer.getBoolean("ocupado"));
		setDisponible(chofer.getBoolean("disponible"));
		setLicencia(chofer.getString("licencia"));
		setAsignado(chofer.getBoolean("asignado"));
	}

	public float getRecaudado()
	{
		return recaudado;
	}

	public void setRecaudado(float recaudado)
	{
		this.recaudado = recaudado;
	}

	/**
	 * Se suma el valor ingresado por parametro a lo ya recaudado
	 * 
	 * @param recuadado: valor a sumar
	 */
	public void sumarRecaudado(float recaudado)
	{
		this.recaudado = getRecaudado() + recaudado;
	}

	/**
	 * Vuelve valores acumulativos a 0 y quita auto asignado
	 */
	public void finalTurno()
	{
		setRecaudado(0);
		setCantViajes(0);
		setOcupado(false);
		setDisponible(false);
	}

	public int getCantViajes()
	{
		return cantViajes;
	}

	public void setCantViajes(int viajes)
	{
		this.cantViajes = viajes;
	}

	/**
	 * Agrega 1 a la cantidad de viajes realizados
	 */
	public void sumaViaje()
	{
		this.cantViajes = getCantViajes() + 1;
	}

	public boolean getDisponible()
	{
		return disponible;
	}

	public void setDisponible(boolean disponible)
	{
		this.disponible = disponible;
	}

	/**
	 * Alterna disponibilidad, si esta disponible deja de estarlo y viceversa
	 */
	public void alternaDisponibilidad()
	{
		if (getDisponible() == true)
			setDisponible(false);
		else
			setDisponible(true);
	}

	public String getLicencia()
	{
		return licencia;
	}

	public void setLicencia(String licencia)
	{
		this.licencia = licencia;
	}

	public boolean getOcupado()
	{
		return ocupado;
	}

	public void setOcupado(boolean ocupado)
	{
		this.ocupado = ocupado;
	}

	/**
	 * Alterna ocupado, si esta disponible deja de estarlo y viceversa
	 */
	public void alternaOcupado()
	{
		if (getOcupado() == true)
			setOcupado(false);
		else
			setOcupado(true);
	}

	public boolean getAsignado()
	{
		return asignado;
	}

	public void setAsignado(boolean asignado)
	{
		this.asignado = asignado;
	}

	/**
	 * Se vuelve ocupado.
	 * 
	 */
	public void aceptarViaje()
	{
		setOcupado(true);
	}

	/**
	 * Ingresa los datos finales para terminar el viaje. Se calcula monto Se retorna
	 * el viaje terminado (para ingresarlo a la lista)
	 * 
	 */
	public Viaje finalizarViaje(Viaje rta, Direccion direccion)
	{
		System.out.println("MIRAD EL NULL!!!!" + "PAUSITA \n");
		float distancia = Coordenadas.distanciaMetros(rta.getDireccionInicial().getUtmX(), rta.getDireccionInicial()
				.getUtmX(), direccion.getUtmX(), direccion.getUtmY());
		rta.getDireccionFinal().setCalle(direccion.getCalle());
		rta.getDireccionFinal().setAltura(direccion.getAltura());
		rta.getDireccionFinal().setCiudad(direccion.getCiudad());
		rta.getDireccionFinal().setUtmX(direccion.getUtmX());
		rta.getDireccionFinal().setUtmY(direccion.getUtmY());
		rta.setDistancia(distancia);
		rta.setMonto(distancia * Viaje.getTarifaPorKilometro());
		setOcupado(false);
		sumaViaje();
		sumarRecaudado(rta.getMonto());
		return rta;
	}

	/**
	 * Transforma a un objeto de Chofer en formato apto para JSONObject
	 * 
	 * @return obj: Objeto listo para ser cargado al JSONArray de usuarios
	 */
	public JSONObject choferGetFormatoJson() throws JSONException
	{

		JSONObject obj = new JSONObject();
		obj.put("usuario", usuarioGetFormatoJson());
		obj.put("persona", personaGetFormatoJson());
		obj.put("recaudado", getRecaudado());
		obj.put("cantViajes", getCantViajes());
		obj.put("ocupado", getOcupado());
		obj.put("disponible", getDisponible());
		obj.put("licencia", getLicencia());
		obj.put("asignado", getAsignado());
		return obj;
	}

	/**
	 * Compara segun la licencia del chofer. Devuelve true si son iguales.
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj != null && obj instanceof Chofer)
		{
			if (getLicencia().equalsIgnoreCase(((Chofer) obj).getLicencia()))
				return true;
			else
				return false;
		} else
			return false;
	}

	@Override
	public String toString()
	{
		return "Chofer [recaudado=" + getRecaudado() + ", cantViajes=" + getCantViajes() + ", disponible="
				+ getDisponible() + ", licencia=" + getLicencia() + super.toString() + "]";
	}

	/**
	 * Muestra informacion para cliente.
	 * 
	 */
	public String toStringParaCliente()
	{
		return "Chofer [ " + super.toString() + "]";
	}

	@Override
	public Viaje cancelarViaje(Viaje viaje)
	{
		viaje.setCancelado(true);
		return null;
	}

	/**
	 * Muestra el historial de viajes que realizo el chofer
	 */
	@Override
	public StringBuilder historial(ListaViajes viajes, Usuario user)
	{
		StringBuilder sb = new StringBuilder();
		for (Viaje aux : viajes.getViajes())
		{
			if (((Chofer) user).getLicencia().equalsIgnoreCase(aux.getChofer()) == true)
			{

				sb.append(aux.toString() + "\n");
			}
		}
		return sb;
	}

	/**
	 * Muestra informacion del cliente del viaje actual
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
		return msj;
	}
}