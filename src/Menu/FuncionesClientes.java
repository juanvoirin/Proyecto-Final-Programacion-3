package Menu;

import java.util.Scanner;

import archivos.JsonUtiles;
import colecciones.ListaUsuarios;
import usuarios.Cliente;
import usuarios.Usuario;
/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public class FuncionesClientes
{

	static Scanner scan;

	/**
	 * Devuelve id de cliente seleccionado, si no lo es devuelve 0.
	 */
	public static double esCliente()
	{
		scan = new Scanner(System.in);
		ListaUsuarios users = JsonUtiles.abrirArchivoUsuarios();
		double rta = 0;
		System.out.println("\nEl pasajero es cliente? s/n\n\n");
		if ((scan.next().charAt(0)) == 's')
		{
			System.out.println("Desea listar Clientes para saber id? s/n \n\n");
			if ((scan.next().charAt(0)) == 's')
			{
				System.out.println(users.listarClientes());
			}
			System.out.println("Ya puede ingresar el ID del Cliente:\n\n");
			rta = scan.nextDouble();
			scan.nextLine();
		}
		return rta;
	}

	/**
	 * Permite ingresar el monto a pagar y descontarlo del saldo. ////AJUSTAR PARA
	 * QUE SE GUARDE EN LA LISTA Y EL ARCHIVO TERMINADO DE PAGAR
	 */
	public static void menuPagar(Usuario usuario)
	{
		ListaUsuarios users = JsonUtiles.abrirArchivoUsuarios();
		Usuario aux = users.buscaUsuario(usuario.getId());
		scan = new Scanner(System.in);
		System.out.println("\nIngrese cuanto desea abonar: ");
		float saldo = scan.nextFloat();
		System.out.println(((Cliente) aux).pagaSaldo(saldo));
		JsonUtiles.guardarArchivoUsuarios(users);
	}

}
