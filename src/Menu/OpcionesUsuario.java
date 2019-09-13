package Menu;

import java.util.Scanner;

import archivos.JsonUtiles;
import colecciones.ListaUsuarios;
import usuarios.Usuario;
/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public class OpcionesUsuario
{

	static Scanner scan;

	/**
	 * Menu de operador.
	 */
	public static void menu(Usuario usuario)
	{
		scan = new Scanner(System.in);
		System.out.println("1. Consultar ID.");
		System.out.println("2. Cambiar contraseña.");
		System.out.println("0. Menu principal.");
		int menu = scan.nextInt();
		scan.nextLine();
		switch (menu)
		{
		case 1:
			System.out.println(usuario.getId());
			break;
		case 2:
			cambiarContraseña(usuario);
			break;
		case 0:
			break;
		}
	}


	/**
	 * Menu cambiar contraseña general
	 */
	private static void cambiarContraseña(Usuario usuario)
	{
		scan = new Scanner(System.in);
		ListaUsuarios users = JsonUtiles.abrirArchivoUsuarios();
		Usuario aux = users.buscaUsuario(usuario.getId());
		System.out.println("\nIngrese contraseña actual: ");
		int vieja = scan.nextInt();
		scan.nextLine();
		System.out.println("\nIngrese contraseña nueva: ");
		int nueva = scan.nextInt();
		scan.nextLine();
		System.out.println(aux.modificarClave(vieja, nueva));
		JsonUtiles.guardarArchivoUsuarios(users);
	}
}
