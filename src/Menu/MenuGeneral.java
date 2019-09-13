package Menu;

import java.util.Scanner;

import archivos.JsonUtiles;
import colecciones.ListaUsuarios;
import mapaDeCalor.Coordenadas;
import usuarios.Usuario;
/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public class MenuGeneral {

	static Scanner scan;
	
	/**
	 * Menu de inicio.
	 */
	public static void menuInicio() {
		scan = new Scanner(System.in);
		ListaUsuarios users = JsonUtiles.abrirArchivoUsuarios();
		if (!users.hayOperador()){
			System.out.println("Como no hay un operador, es necesario crear uno.");
			FuncionesAgregar.agregarOperador();
			menuInicio();
		}else {
			System.out.println("1. Iniciar sesion.");
			System.out.println("2. Ver zona de calor.");
			System.out.println("0. Salir.");
			int menu = (FuncionesSesiones.verificaValor(2));
			switch (menu)
			{
			case 1:
				Usuario user = FuncionesSesiones.iniciarSesion();
				FuncionesSesiones.seleccionMenu(user);
				menuInicio();
				break;
			case 2:
				try {
					System.out.println(Coordenadas.puntoCalor());
				} catch (Exception e) {
					System.out.println("No existen viajes realizados.");
				}
				menuInicio();
				break;
			case 0:
				System.out.println("Que tenga buen dia.");
				break;
			}
		}
	}
	
	
}
	
	
	
	
	
	
	
	

