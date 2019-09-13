package Menu;

import java.util.Scanner;

import archivos.JsonUtiles;
import auxiliares.Direccion;
import auxiliares.Movil;
import colecciones.ListaDirecciones;
import colecciones.ListaMoviles;
import colecciones.ListaUsuarios;
import usuarios.Chofer;
import usuarios.Empresa;
import usuarios.Operador;
import usuarios.Particular;
import usuarios.Persona;
/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public class FuncionesAgregar
{

	static Scanner scan;

	/**
	 * Pide ingresar lo necesario para crear un nuevo movil.
	 */
	public static void agregarMovil()
	{
		scan = new Scanner(System.in);
		ListaMoviles moviles = JsonUtiles.abrirArchivoMoviles();
		System.out.println("Ingrese modelo: ");
		String modelo = scan.nextLine();
		System.out.println("Ingrese color: ");
		String color = scan.nextLine();
		System.out.println("Ingrese patente: ");
		String patente = scan.nextLine();
		moviles.agregarMovil(new Movil(modelo, color, patente));
		JsonUtiles.guardarArchivoMoviles(moviles);
	}

	/**
	 * Pide ingresar por pantalla lo necesario para crear un operador
	 */
	public static void agregarOperador()
	{
		ListaUsuarios users = JsonUtiles.abrirArchivoUsuarios();
		Persona p = crearPersona();
		Operador rta = new Operador(p, users);
		users.agregarUsuario(rta);
		JsonUtiles.guardarArchivoUsuarios(users);
		System.out.println("Usuario con id = " + (rta.getId()) + " y clave = 1111");
	}

	/**
	 * Pide ingresar por pantalla lo necesario para crear un chofer
	 */
	public static void agregarChofer()
	{
		scan = new Scanner(System.in);
		ListaUsuarios users = JsonUtiles.abrirArchivoUsuarios();
		Persona p = crearPersona();
		System.out.println("\nIngresar licencia: ");
		String licencia = scan.nextLine();
		Chofer rta = new Chofer(p, licencia, users);
		users.agregarUsuario(rta);
		JsonUtiles.guardarArchivoUsuarios(users);
		System.out.println("Usuario con id = " + (rta.getId()) + " y clave = 1111");
	}

	/**
	 * Crea una empresa cliente y la agrega a la lista de usuarios.
	 */
	public static void agregarEmpresa()
	{
		scan = new Scanner(System.in);
		ListaUsuarios users = JsonUtiles.abrirArchivoUsuarios();
		System.out.println("\nIngrese nombre de empresa: ");
		String nombre = scan.nextLine();
		Direccion direccion = FuncionesDirecciones.devuelveDireccion();
		ListaDirecciones direcciones = JsonUtiles.abrirArchivoDirecciones();
		direcciones.existeDireccion(direccion, direcciones);
//		System.out.println(direccion.toString());
		Empresa rta = new Empresa(users, nombre, direccion);
		users.agregarUsuario(rta);
		JsonUtiles.guardarArchivoDirecciones(direcciones);
		JsonUtiles.guardarArchivoUsuarios(users);
		System.out.println("Usuario con id = " + (rta.getId()) + " y Clave = 1111");
	}

	/**
	 * Crea un cliente particular y lo agrega a la lista de usuarios
	 * 
	 */
	public static void agregarParticular()
	{
		scan = new Scanner(System.in);
		ListaUsuarios users = JsonUtiles.abrirArchivoUsuarios();
		Persona p = crearPersona();
		Particular rta = new Particular(p, users);
		users.agregarUsuario(rta);
		JsonUtiles.guardarArchivoUsuarios(users);
		System.out.println("Usuario con id = " + (rta.getId()) + " y Clave = 1111");
	}

	/**
	 * Crea persona con todos sus atributos.
	 * 
	 * @return Retorna el objeto completo.
	 */
	public static Persona crearPersona()
	{
		scan = new Scanner(System.in);
		System.out.println("Ingrese nombre y apellido: ");
		String nombre = scan.nextLine();
		System.out.println("Ingrese dni: ");
		String dni = scan.next();
		scan.nextLine();
		System.out.println("Ingrese fecha de nacimiento: ");
		System.out.println("Dia: ");
		int dia = scan.nextInt();
		scan.nextLine();
		System.out.println("Mes: ");
		int mes = scan.nextInt();
		scan.nextLine();
		System.out.println("Anio: ");
		int anio = scan.nextInt();
		scan.nextLine();
		Direccion residencia = FuncionesDirecciones.devuelveDireccion();
		ListaDirecciones direcciones = JsonUtiles.abrirArchivoDirecciones();
		direcciones.existeDireccion(residencia, direcciones);
		JsonUtiles.guardarArchivoDirecciones(direcciones);
		Persona p = new Persona(nombre, dni, dia, mes, anio, residencia.getCalle(), residencia.getAltura(), residencia
				.getCiudad(), residencia.getUtmX(), residencia.getUtmY());
		return p;
	}

}
