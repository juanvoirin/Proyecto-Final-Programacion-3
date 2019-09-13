package usuarios;

import org.json.JSONException;
import org.json.JSONObject;

import colecciones.ListaUsuarios;
import interfaces.IInformacion;


/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public abstract class Cliente extends Usuario implements IInformacion
{
	private float saldo; // Saldo registrado


	/**
	 * Constructor vacio.
	 */
	public Cliente()
	{
		super();
		setSaldo(0);
	}

	public Cliente(ListaUsuarios usuario, String tipoUsuario)
	{
		super(usuario, tipoUsuario);
		setSaldo(0);
	}

	public Cliente(ListaUsuarios usuario, String tipoUsuario, float saldo)
	{
		super(usuario, tipoUsuario);
		setSaldo(saldo);
	}

	/*
	 * Constructor que crea un cliente a partir de un JSONObject.
	 */
	public Cliente(JSONObject cliente, JSONObject usuario) throws JSONException
	{
		super(usuario);
		setSaldo((float) cliente.getDouble("saldo"));
	}

	public float getSaldo()
	{
		return saldo;
	}

	private void setSaldo(float saldo)
	{
		this.saldo = saldo;
	}

	public String agregaSaldo(float saldo)
	{
		setSaldo(getSaldo() + saldo);
		return "Transaccion realizada con exito. Nuevo saldo: " + getSaldo();
	}

	public String pagaSaldo(float saldo)
	{
		setSaldo(getSaldo() - saldo);
		return "Transaccion realizada con exito. Nuevo saldo: " + getSaldo();
	}

	/**
	 * Transforma los atributos de Cliente en formato apto para JSONObject
	 * 
	 * @return obj: Se utiliza en sus subclases para formar el JSONObject completo
	 *         de la misma
	 */
	public JSONObject clienteGetFormatoJson() throws JSONException
	{
		JSONObject obj = new JSONObject();

		obj.put("saldo", getSaldo());

		return obj;
	}

	@Override
	public String toString()
	{
		return super.toString() + ", saldo=" + saldo + "]";
	}

}
