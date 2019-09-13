package Menu;
/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public class MenuAgregar {
	
	
	public static void menu() {
		System.out.println("1. Crear operador.");
		System.out.println("2. Crear chofer.");
		System.out.println("3. Crear cliente particular.");
		System.out.println("4. Crear cliente empresa.");
		System.out.println("5. Crear movil.");
		System.out.println("6. Crear direccion.");
		System.out.println("0. Salir.");
		int menu = FuncionesSesiones.verificaValor(6);
		switch (menu)
		{
		case 1:
			FuncionesAgregar.agregarOperador();
			break;
		case 2:
			FuncionesAgregar.agregarChofer();
			break;
		case 3:
			FuncionesAgregar.agregarParticular();
			break;
		case 4:
			FuncionesAgregar.agregarEmpresa();
			break;
		case 5:
			FuncionesAgregar.agregarMovil();
			break;
		case 6:
			FuncionesDirecciones.devuelveDireccion();
			break;
		case 0:
			break;
		}
	}
	
}
