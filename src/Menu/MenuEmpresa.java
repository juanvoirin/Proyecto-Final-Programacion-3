package Menu;

import java.util.Scanner;

import archivos.JsonUtiles;
import colecciones.ListaUsuarios;
import usuarios.Empresa;
import usuarios.Usuario;
/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public class MenuEmpresa {
	
	static Scanner scan;
	
	/**
	 * Menu de empresa.
	 */
	public static void menu(Usuario usuario) {
		scan = new Scanner(System.in);
		System.out.println("1. Pagar saldo.");
		System.out.println("2. Habilitados.");
		System.out.println("3. Cambiar direccion.");
		System.out.println("4. Opciones usuario.");
		System.out.println("0. Menu principal.");
		int menu = (FuncionesSesiones.verificaValor(4));
		switch (menu)
		{
		case 1:
			FuncionesClientes.menuPagar(usuario);
			menu(usuario);
			break;
		case 2:
			menuHabilitados(usuario);
			menu(usuario);
			break;
		case 3:
			FuncionesDirecciones.cambiarDireccion(usuario);
			menu(usuario);
			break;
		case 4:
			OpcionesUsuario.menu(usuario);
			menu(usuario);
			break;
		case 0:
			break;
		}
	}
	
	
	/**
	 * Menu de habilitados de la empresa
	 */
	private static void menuHabilitados(Usuario usuario) {
		scan = new Scanner(System.in);
		System.out.println("1. Agregar.");
		System.out.println("2. Listar.");
		System.out.println("3. Eliminar.");
		System.out.println("0. Salir.");
		int menu = (FuncionesSesiones.verificaValor(4));
		switch (menu)
		{
		case 1:
			agregarHabilitado(usuario);
			menuHabilitados(usuario);
			break;
		case 2:
			System.out.println(((Empresa)usuario).listarHabilitados());
			menuHabilitados(usuario);
			break;
		case 3:
			eliminarHabilitado(usuario);
			menuHabilitados(usuario);
			break;
		case 0:
			break;
		}
	}
	
	/**
	 * Agrega una persona a los habilitados de la empresa.
	 */
	private static void agregarHabilitado(Usuario user) {
		ListaUsuarios users = JsonUtiles.abrirArchivoUsuarios();
		Usuario aux = users.buscaUsuario(user.getId());
		((Empresa) aux).agregarHabilitado(FuncionesAgregar.crearPersona());
		JsonUtiles.guardarArchivoUsuarios(users);
	}
	
	/**
	 * Elimina una persona a los habilitados de la empresa.
	 */
	private static void eliminarHabilitado(Usuario user) {
		ListaUsuarios users = JsonUtiles.abrirArchivoUsuarios();
		Usuario aux = users.buscaUsuario(user.getId());
		System.out.println("\nIngrese el dni de la persona a eliminar: ");
		((Empresa) aux).eliminarHabilitado(scan.next());
		JsonUtiles.guardarArchivoUsuarios(users);
	}
}
