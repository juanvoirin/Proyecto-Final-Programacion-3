package Menu;

import java.util.Scanner;

import archivos.JsonUtiles;
import auxiliares.Direccion;
import colecciones.ListaDirecciones;
import usuarios.Empresa;
import usuarios.Particular;
import usuarios.Usuario;
/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public class FuncionesDirecciones
{

	static Scanner scan;

	/**
	 * Permite modificar la direccion de un usuario.
	 */
	public static void cambiarDireccion(Usuario usuario)
	{
		ListaDirecciones direcciones = JsonUtiles.abrirArchivoDirecciones();
		Direccion nueva = devuelveDireccion();

		if (usuario instanceof Empresa)
		{
			((Empresa) usuario).getDireccion().setCalle(nueva.getCalle());
			((Empresa) usuario).getDireccion().setAltura(nueva.getAltura());
			((Empresa) usuario).getDireccion().setCiudad(nueva.getCiudad());
			((Empresa) usuario).getDireccion().setUtmX(nueva.getUtmX());
			((Empresa) usuario).getDireccion().setUtmY(nueva.getUtmY());
			direcciones.existeDireccion(nueva, direcciones);
			System.out.println("Direccion cambiada a: " + nueva.toString() + " Exitosamente!");
		} else if (usuario instanceof Particular)
		{
			((Particular) usuario).getP().getResidencia().setCalle(nueva.getCalle());
			((Particular) usuario).getP().getResidencia().setAltura(nueva.getAltura());
			((Particular) usuario).getP().getResidencia().setCiudad(nueva.getCiudad());
			((Particular) usuario).getP().getResidencia().setUtmX(nueva.getUtmX());
			((Particular) usuario).getP().getResidencia().setUtmY(nueva.getUtmY());
			direcciones.existeDireccion(nueva, direcciones);
			System.out.println("Direccion cambiada a: " + nueva.toString() + " Exitosamente!");
		}
	}

	/**
	 * Pide datos necesarios para crear direccion completa
	 */
	public static Direccion devuelveDireccion()
	{
		scan = new Scanner(System.in);
		ListaDirecciones direcciones = JsonUtiles.abrirArchivoDirecciones();
		System.out.println("Ingrese direccion: ");
		System.out.println("Ingrese calle: ");
		String calle = scan.nextLine();
		System.out.println("Ingrese altura: ");
		int altura = scan.nextInt();
		scan.nextLine();
		System.out.println("Ingrese ciudad: ");
		String ciudad = scan.nextLine();
		Direccion completa;
		if (direcciones.buscaDireccion(calle, altura, ciudad))
		{
			completa = direcciones.completaDireccion(calle, altura, ciudad);
		} else
		{
			System.out.println("\nIngrese coordenada utumX: ");
			float utmX = scan.nextFloat();
			System.out.println("\nIngrese coordenada utmY: ");
			float utmY = scan.nextFloat();
			direcciones.agregarDireccion(completa = new Direccion(calle, altura, ciudad, utmX, utmY));
			JsonUtiles.guardarArchivoDirecciones(direcciones);
		}
		return completa;
	}


}
