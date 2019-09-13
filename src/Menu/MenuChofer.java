package Menu;

import java.util.Scanner;

import usuarios.Usuario;
/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public class MenuChofer {
	
	static Scanner scan;
	
	/**
	 * Menu de chofer.
	 */
	public static void menu(Usuario usuario) {
		scan = new Scanner(System.in);
		System.out.println("1. Opciones de usuario.");
		System.out.println("0. Menu principal.");
		int menu = (FuncionesSesiones.verificaValor(1));
		switch (menu)
		{
		case 1:
			OpcionesUsuario.menu(usuario);
			menu(usuario);
			break;
		case 0:
			break;
		}
	}
	
}
