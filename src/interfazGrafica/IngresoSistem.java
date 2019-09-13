package interfazGrafica;



import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Menu.FuncionesSesiones;
import archivos.JsonUtiles;
import colecciones.ListaUsuarios;
import usuarios.Chofer;


/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public class IngresoSistem extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5036625077104224768L;
	
	//private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;

	/**
	 * Crea la aplicacion
	 */
	public IngresoSistem()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Chofer");
		initialize();
	}

	


	/**
	 * Inicializa los contenidos del cuadro
	 */
	private void initialize()
	{

		setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 300, 250);
		setLocationRelativeTo(null);
		JPanelBackground contentPane = new JPanelBackground();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground("C:\\Users\\Voirin\\OneDrive\\Documentos\\Facultad\\Programacion y Laboratorio 3\\Proyecto Final\\GitHub\\Proyecto-Final-Programacion-3\\image.PNG");// Ruta del archivo de imagen.
		contentPane.setOpaque(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setForeground(SystemColor.textHighlightText);
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setBackground(Color.WHITE);
		lblUsuario.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblUsuario.setBounds(40, 40, 94, 22);
		contentPane.add(lblUsuario);

		textField = new JTextField();
		textField.setBounds(144, 42, 110, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblContraseña = new JLabel("Contrase\u00F1a:");
		lblContraseña.setForeground(SystemColor.textHighlightText);
		lblContraseña.setHorizontalAlignment(SwingConstants.CENTER);
		lblContraseña.setBackground(Color.WHITE);
		lblContraseña.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblContraseña.setBounds(40, 73, 94, 22);
		contentPane.add(lblContraseña);

		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setBounds(144, 73, 110, 20);
		contentPane.add(passwordField);

		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					Chofer profesional = iniciarSesion();
					JOptionPane.showMessageDialog(null, "Usted ha ingresado correctamente al sistema.", "Bienvenido",
							JOptionPane.INFORMATION_MESSAGE);
					UsuarioChofer user = new UsuarioChofer(profesional);
					user.setVisible(true);
				} catch (Exception e)
				{
					JOptionPane.showMessageDialog(null,
							"Algunos de los datos son erroneos, verifique por favor e intente nuevamente.",
							"Error al ingresar", JOptionPane.INFORMATION_MESSAGE);
				}

				// FuncionesMenu.iniciarSesion(users)

			}

		});
		btnIngresar.setBounds(45, 170, 89, 23);
		contentPane.add(btnIngresar);

		JLabel lblLicencia = new JLabel("Licencia:");
		lblLicencia.setForeground(SystemColor.textHighlightText);
		lblLicencia.setHorizontalAlignment(SwingConstants.CENTER);
		lblLicencia.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblLicencia.setBounds(40, 106, 94, 14);
		contentPane.add(lblLicencia);

		textField_1 = new JTextField();
		textField_1.setBounds(144, 104, 110, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(DO_NOTHING_ON_CLOSE);
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSalir.setBounds(144, 170, 89, 23);
		contentPane.add(btnSalir);


	}

	/**
	 * Inicia sesion solo chofer.
	 * 
	 * @return Devuelve el chofer.
	 */
	public Chofer iniciarSesion()
	{
		ListaUsuarios users = JsonUtiles.abrirArchivoUsuarios();
		int id = (int) Float.parseFloat(textField.getText());
		String contra = new String(passwordField.getPassword());
		int clave = (int) Float.parseFloat(contra);
		Chofer rta = (Chofer) FuncionesSesiones.corroborarSesion(id, clave, "chofer", users);
		return rta;
	}
}




