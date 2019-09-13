package archivos;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONException;

import colecciones.ListaDirecciones;
import colecciones.ListaMoviles;
import colecciones.ListaUsuarios;
import colecciones.ListaViajes;


/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public class JsonUtiles
{
	public static final String archivoDirecciones = "direcciones.txt";
	public static final String archivoUsuarios = "usuarios.txt";
	public static final String archivoMoviles = "moviles.txt";
	public static final String archivoViajes = "viajes.txt";


	/**
	 * Crea un archivo de texto plano en la ruta de la carpeta del proyecto, el
	 * String de un archivo JSON con el nombre que se le de por parametro.
	 * 
	 */
	private static void grabar(JSONArray obj, String nombreArchivo)
	{
		try
		{
			FileWriter aux = new FileWriter(nombreArchivo);
			aux.write(obj.toString());
			aux.flush();
			aux.close();
		} catch (IOException e)
		{
		}
	}

	/**
	 * Lee y retorna el contenido (en forma de string) de un archivo de texto plano.
	 * El nombre del archivo se pasa por parametro
	 * 
	 */
	private static JSONArray archiToJSON(String nombreArchivo) throws JSONException
	{
		String contenido = "";
		try
		{
			contenido = new String(Files.readAllBytes(Paths.get(nombreArchivo)));
		} catch (IOException e)
		{
		}
		JSONArray rta = new JSONArray(contenido);
		return rta;
	}


	public static ListaUsuarios abrirArchivoUsuarios()
	{
		try
		{
			return new ListaUsuarios(archiToJSON(archivoUsuarios));
		} catch (JSONException e)
		{
			return new ListaUsuarios();
		}
	}

	public static ListaMoviles abrirArchivoMoviles()
	{
		try
		{
			return new ListaMoviles(archiToJSON(archivoMoviles));
		} catch (JSONException e)
		{
			return new ListaMoviles();
		}
	}

	public static ListaDirecciones abrirArchivoDirecciones()
	{
		try
		{
			return new ListaDirecciones(archiToJSON(archivoDirecciones));
		} catch (JSONException e)
		{
			return new ListaDirecciones();
		}
	}

	public static ListaViajes abrirArchivoViajes()
	{
		try
		{
			return new ListaViajes(archiToJSON(archivoViajes));
		} catch (JSONException e)
		{
			return new ListaViajes();
		}
	}

	public static void guardarArchivoUsuarios(ListaUsuarios users)
	{
		try
		{
			grabar(users.coleccionUsuariosAJSON(), archivoUsuarios);
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
	}

	public static void guardarArchivoMoviles(ListaMoviles moviles)
	{
		try
		{
			grabar(moviles.coleccionMovilesAJSON(), archivoMoviles);
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
	}

	public static void guardarArchivoViajes(ListaViajes viajes)
	{
		try
		{
			grabar(viajes.coleccionViajesAJSON(), archivoViajes);
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
	}

	public static void guardarArchivoDirecciones(ListaDirecciones direcciones)
	{
		try
		{
			grabar(direcciones.coleccionDireccionesAJSON(), archivoDirecciones);
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
	}
}
