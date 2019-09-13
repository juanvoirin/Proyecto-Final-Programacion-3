package Menu;

import java.util.Scanner;

import archivos.JsonUtiles;
import auxiliares.Direccion;
import auxiliares.Movil;
import auxiliares.Viaje;
import colecciones.ListaDirecciones;
import colecciones.ListaMoviles;
import colecciones.ListaUsuarios;
import colecciones.ListaViajes;
import usuarios.Chofer;
import usuarios.Operador;
import usuarios.Usuario;
/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public class MenuOperador
{

	static Scanner scan;

	/**
	 * Menu de operador.
	 */
	public static void menu(Usuario usuario)
	{
		scan = new Scanner(System.in);
		System.out.println("1. Asignar viaje.");
		System.out.println("2. Agregar.");
		System.out.println("3. Eliminar.");
		System.out.println("4. Listar.");
		System.out.println("5. Asignar chofer a un movil.");
		System.out.println("6. Opciones de usuario.");
		System.out.println("0. Menu principal.");
		int menu = (FuncionesSesiones.verificaValor(6));
		switch (menu)
		{
		case 1:
			asignarViaje(usuario);
			menu(usuario);
			break;
		case 2:
			MenuAgregar.menu();
			menu(usuario);
			break;
		case 3:
			//MenuEliminar.menu();
			System.out.println("Lo sentimos, solo para usuarios premium.");
			menu(usuario);
			break;
		case 4:
			MenuMostrar.menu();
			menu(usuario);
			break;
		case 5:
			asignarChoferAMovil();
			menu(usuario);
			break;
		case 6:
			OpcionesUsuario.menu(usuario);
			menu(usuario);
			break;
		case 0:
			break;
		}
	}

	/**
	 * Crear un viaje. REVISAR METODO Y AJUSTAR.
	 */
	public static void asignarViaje(Usuario usuario)
	{
		scan = new Scanner(System.in);
		ListaUsuarios users = JsonUtiles.abrirArchivoUsuarios();
		ListaMoviles moviles = JsonUtiles.abrirArchivoMoviles();
		ListaViajes viajes = JsonUtiles.abrirArchivoViajes();
		Direccion partida = FuncionesDirecciones.devuelveDireccion();
		ListaDirecciones direcciones = JsonUtiles.abrirArchivoDirecciones();
		double pasajero = FuncionesClientes.esCliente();
		Movil movil = moviles.seleccionMovil();
		direcciones.existeDireccion(partida, direcciones);
		Viaje aux = ((Operador) usuario).asignarViaje(partida, pasajero, movil.getPatente(), movil.getLicencia(),
				false);
		viajes.agregarViaje(aux);
		((Chofer) users.buscaChofer(movil.getLicencia())).aceptarViaje();
		System.out.println("Se ha asignado el viaje con direccion a "+ partida.toString() + ", para el chofer de Licencia: "+ movil.getLicencia()+"\n\n\n\n\n\n\n\n");
		JsonUtiles.guardarArchivoDirecciones(direcciones);
		JsonUtiles.guardarArchivoViajes(viajes);
		JsonUtiles.guardarArchivoUsuarios(users);
	}

	/**
	 * Asigna un chofer a un movil, la licencia del movil se vuelve la del chofer.
	 */
	public static void asignarChoferAMovil()
	{
		try
		{
			ListaUsuarios users = JsonUtiles.abrirArchivoUsuarios();
			ListaMoviles moviles = JsonUtiles.abrirArchivoMoviles();
			Usuario aux = users.choferLibre();
			Movil libre = moviles.movilesSinChofer();
			libre.setLicencia(((Chofer) aux).getLicencia());
			System.out.println(aux.toString() + "asignado al " + libre.toString() + "\n\n\n\n\n\n\n\n");
			JsonUtiles.guardarArchivoMoviles(moviles);
			JsonUtiles.guardarArchivoUsuarios(users);
		} catch (NullPointerException e)
		{
			System.out.println("No existe Chofer o Movil libre actualmente.\n\n\n\n\n\n\n\n");
		}
	}
}
