package Menu;

import archivos.JsonUtiles;
/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public class MenuMostrar
{

	/**
	 * Menu para mostrar distintos datos.
	 */
	public static void menu()
	{
		System.out.println("1. Mostrar usuarios.");
		System.out.println("2. Mostrar moviles.");
		System.out.println("3. Mostrar viajes.");
		System.out.println("4. Mostrar direcciones.");
		System.out.println("0. Salir.");
		int menu = FuncionesSesiones.verificaValor(6);
		switch (menu)
		{
		case 1:
			System.out.println(JsonUtiles.abrirArchivoUsuarios().listarUsuarios());
			break;
		case 2:
			System.out.println(JsonUtiles.abrirArchivoMoviles().listarMoviles());
			break;
		case 3:
			System.out.println(JsonUtiles.abrirArchivoViajes().listarViajes());
			break;
		case 4:
			System.out.println(JsonUtiles.abrirArchivoDirecciones().listarDirecciones());
			break;
		case 0:
			break;
		}
	}
}
