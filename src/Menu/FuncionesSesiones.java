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
public class FuncionesSesiones
{

	static Scanner scan;

	public static int verificaValor(int max)
	{
		scan = new Scanner(System.in);
		int rta = -1;
		while (rta < 0 || rta > max)
		{
			rta = scan.nextInt();
			scan.nextLine();
			if (rta < 0 || rta > max)
				System.out.println("Opcion no valida, ingrese denuevo.");
		}
		return rta;
	}

	/**
	 * Inicia sesion.
	 * 
	 * @return Si inicia devuelve el usuario, sino null.
	 */
	public static Usuario iniciarSesion()
	{
		scan = new Scanner(System.in);
		ListaUsuarios users = JsonUtiles.abrirArchivoUsuarios();
		String tipoUsuario = tipoUsuarioSeleccionado();
		System.out.println("\nIngrese id: ");
		int id = scan.nextInt();
		scan.nextLine();
		System.out.println("\nIngrese clave: ");
		int clave = scan.nextInt();
		scan.nextLine();
		Usuario rta = corroborarSesion(id, clave, tipoUsuario, users);

		if (rta == null)
		{
			System.out.println("Ha ingresado datos incorrectos. Intente nuevamente\n\n\n\n\n\n\n\n");
			MenuGeneral.menuInicio();
		}
		return rta;

	}

	/**
	 * Ofrece opciones para elegir que tipo de usuario va a iniciar.
	 * 
	 * @return
	 */
	private static String tipoUsuarioSeleccionado()
	{
		scan = new Scanner(System.in);
		String rta = "Cancelar";
		System.out.println("\nSeleccione tipo de usuario.");
		System.out.println("\n1. Operador.");
		System.out.println("\n2. Chofer.");
		System.out.println("\n3. Cliente Particular.");
		System.out.println("\n4. Cliente Empresa.");
		System.out.println("\n0. Cancelar.");
		int menu = verificaValor(4);
		switch (menu)
		{
		case 1:
			rta = "Operador";
			break;
		case 2:
			rta = "Chofer";
			break;
		case 3:
			rta = "Particular";
			break;
		case 4:
			rta = "Empresa";
			break;
		case 0:
			break;
		}
		return rta;
	}

	/**
	 * Corrobora los 3 datos para iniciar sesion.
	 * 
	 * @return Devuelve el Usuario si son correctos los datos, null si no.
	 */
	public static Usuario corroborarSesion(int id, int clave, String tipoUsuario, ListaUsuarios users)
	{
		Usuario rta = null;
		Usuario aux = users.buscaUsuario(id);
		if (aux != null)
			if (aux.compruebaClave(clave))
				if (tipoUsuario.equalsIgnoreCase(aux.getTipoUsuario()))
					rta = aux;
		return rta;
	}

	/**
	 * Accede al menu ingresado.
	 */
	public static void seleccionMenu(Usuario usuario)
	{
		if (!usuario.getTipoUsuario().equalsIgnoreCase("Cancelado"))
		{
			if (usuario.getTipoUsuario().equalsIgnoreCase("Operador"))
				MenuOperador.menu(usuario);
			else
			{
				if (usuario.getTipoUsuario().equalsIgnoreCase("Empresa"))
					MenuEmpresa.menu(usuario);
				else
				{
					if (usuario.getTipoUsuario().equalsIgnoreCase("Chofer"))
						MenuChofer.menu(usuario);
					else
						MenuParticular.menu(usuario);
				}
			}
		}
	}


}
