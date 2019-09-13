package Menu;

public class MenuEliminar {
	
	
	////////FALTA TERMINARRRRR
	public static void menu() {
		System.out.println("1. Eliminar operador.");
		System.out.println("2. Eliminar chofer.");
		System.out.println("3. Eliminar cliente particular.");
		System.out.println("4. Eliminar cliente empresa.");
		System.out.println("5. Eliminar movil.");
		System.out.println("6. Eliminar direccion.");
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
