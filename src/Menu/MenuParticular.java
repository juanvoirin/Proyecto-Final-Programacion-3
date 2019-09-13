package Menu;

import java.util.Scanner;

import usuarios.Usuario;
/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public class MenuParticular {

	static Scanner scan;
	
	/**
	 * Menu de particular.
	 */
	public static void menu(Usuario usuario) {
		scan = new Scanner(System.in);
		System.out.println("1. Pagar saldo.");
		System.out.println("2. Cambiar direccion.");
		System.out.println("3. Opciones usuarios.");
		System.out.println("0. Menu principal.");
		int menu = (FuncionesSesiones.verificaValor(3));
		switch (menu)
		{
		case 1:
			FuncionesClientes.menuPagar(usuario);
			menu(usuario);
			break;
		case 2:
			FuncionesDirecciones.cambiarDireccion(usuario);
			menu(usuario);
			break;
		case 3:
			OpcionesUsuario.menu(usuario);
			menu(usuario);
			break;
		case 0:
			break;
		}
	}
}
