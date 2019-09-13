package interfazGrafica;
/**
 * Clase creada con el fin de abrir una ventana de dialogo que solicita al chofer el ingreso de los datos del destino final del viaje.
 */


import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import archivos.JsonUtiles;
import auxiliares.Direccion;
import auxiliares.Viaje;
import colecciones.ListaDirecciones;
import colecciones.ListaUsuarios;
import colecciones.ListaViajes;
import usuarios.Chofer;
import usuarios.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public class InfoCobrar extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7802758118868048908L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txfDireccion;
	private JTextField txfAltura;
	private JTextField txfCiudad;


	public String getDireccion()
	{
		return txfDireccion.getText();
	}

	public int getAltura()
	{
		return (int) Float.parseFloat(txfAltura.getText());
	}

	public String getCiudad()
	{
		return txfCiudad.getText();

	}

	// ListaDirecciones direcciones = new ListaDirecciones(); // CONSTRUCTOR VACIO

	/**
	 * Crea el dialogo.
	 */
	public InfoCobrar(Chofer chofer)
	{
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setTitle("Informacion de Viaje");
		setBounds(100, 100, 300, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblDireccion = new JLabel("Direccion:");
			lblDireccion.setFont(new Font("Times New Roman", Font.BOLD, 14));
			lblDireccion.setBounds(10, 30, 90, 25);
			contentPanel.add(lblDireccion);
		}
		{
			JLabel lblAltura = new JLabel("Altura:");
			lblAltura.setFont(new Font("Times New Roman", Font.BOLD, 14));
			lblAltura.setBounds(10, 66, 90, 25);
			contentPanel.add(lblAltura);
		}
		{
			JLabel lblCiudad = new JLabel("Ciudad:");
			lblCiudad.setFont(new Font("Times New Roman", Font.BOLD, 14));
			lblCiudad.setBounds(10, 102, 90, 25);
			contentPanel.add(lblCiudad);
		}

		txfDireccion = new JTextField();
		txfDireccion.setBounds(124, 31, 150, 25);
		contentPanel.add(txfDireccion);
		txfDireccion.setColumns(10);
		{
			txfAltura = new JTextField();
			txfAltura.setColumns(10);
			txfAltura.setBounds(124, 69, 150, 25);
			contentPanel.add(txfAltura);
		}
		{
			txfCiudad = new JTextField();
			txfCiudad.setColumns(10);
			txfCiudad.setBounds(124, 105, 150, 25);
			contentPanel.add(txfCiudad);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						ListaViajes aux = JsonUtiles.abrirArchivoViajes();
						Direccion direccion = devuelveDireccion();
						ListaUsuarios users= JsonUtiles.abrirArchivoUsuarios();
						Usuario aux2= users.buscaChofer(chofer.getLicencia());
						Viaje travel = ((Chofer) aux2).finalizarViaje(aux.viajeEnCurso(chofer.getLicencia()), direccion);
						travel.setFinalizado(true);
						JsonUtiles.guardarArchivoViajes(aux);
						JsonUtiles.guardarArchivoUsuarios(users);
						JOptionPane.showMessageDialog(null, travel.toString(), "Cobrar",
								JOptionPane.INFORMATION_MESSAGE);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}

		}

	}

	/**
	 * 
	 * @return Direccion Completa
	 */
	private Direccion devuelveDireccion()
	{
		ListaDirecciones direcciones = JsonUtiles.abrirArchivoDirecciones();
		String calle = txfDireccion.getText();
		int altura = Integer.parseInt(txfAltura.getText());
		String ciudad = txfCiudad.getText();
		Direccion completa = null;
		try
		{
			if (direcciones.buscaDireccion(calle, altura, ciudad) == true)
			{
				completa = direcciones.completaDireccion(calle, altura, ciudad);

			}
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Direccion invalida, Compruebe los datos ingresados.",
					"Error de Direccion", JOptionPane.INFORMATION_MESSAGE);
		}
		return completa;

	}


}

