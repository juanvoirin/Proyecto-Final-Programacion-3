package interfaces;

import colecciones.ListaViajes;
import usuarios.Usuario;

/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public interface IInformacion
{

	StringBuilder historial(ListaViajes viajes, Usuario user);

	String pedirInformacion(Usuario user);

}
