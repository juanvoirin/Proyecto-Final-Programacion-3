package interfazGrafica;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

//ALAN MEDINA
/**
 * Clase que se crea para generar el frame de la pantalla principal necesaria para el chofer realizar su operaciones.
 */


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import org.json.JSONException;

import archivos.JsonUtiles;
import auxiliares.Viaje;
import colecciones.ListaUsuarios;
import colecciones.ListaViajes;
import hilos.HiloReloj;
import mapaDeCalor.Coordenadas;
import usuarios.Chofer;
import usuarios.Usuario;


/**
 * 
 * @author Alan Medina, Martin Gonzalez y Juan Voirin
 *
 */
public class UsuarioChofer extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 458379641990922727L;
	
	//private JPanel contentPane;
	public static JLabel lblHora;
	public static JLabel lblFecha;


	/**
	 * Crea el cuadro.
	 * 
	 */
	public UsuarioChofer(Chofer chofer) throws JSONException
	{

		//ListaUsuarios chofe= JsonUtiles.abrirArchivoUsuarios();
		ListaViajes viajes = JsonUtiles.abrirArchivoViajes();
		setBounds(100, 100, 350, 275);
		setLocationRelativeTo(null);
		JPanelBackground contentPane = new JPanelBackground();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground("C:\\Users\\Voirin\\OneDrive\\Documentos\\Facultad\\Programacion y Laboratorio 3\\Proyecto Final\\GitHub\\Proyecto-Final-Programacion-3\\image2.jpg");// Ruta del archivo de imagen.
		contentPane.setOpaque(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnLibre = new JButton("Libre");
		btnLibre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ListaViajes viajes= JsonUtiles.abrirArchivoViajes();
				Viaje actual= viajes.viajeEnCurso(chofer.getLicencia());
				if (actual.getFinalizado() != true)
				{
					chofer.aceptarViaje();
					btnLibre.setText("Con viaje: " + actual.getDireccionInicial());

				}
				
			}
			
		});
		btnLibre.setForeground(Color.WHITE);
		btnLibre.setBackground(new Color(0, 255, 0));
		btnLibre.setFont(new Font("Times New Roman", Font.BOLD, 10));
		btnLibre.setBounds(10, 10, 204, 50);
		contentPane.add(btnLibre);



		Button btnDisp = new Button("Disp.");
		btnDisp.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnDisp.setForeground(Color.WHITE);
		btnDisp.setBackground(new Color(51, 153, 51));
		btnDisp.setBounds(234, 10, 89, 50);
		contentPane.add(btnDisp);

		JButton btnNoDisp = new JButton("No Disp.");
		btnNoDisp.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{

				btnDisp.setBounds(125, 130, 89, 50);
				chofer.alternaDisponibilidad();
				btnDisp.addMouseListener(new MouseAdapter()
				{
					@Override
					public void mouseClicked(MouseEvent arg0)
					{

						btnDisp.setBounds(234, 10, 89, 50);
						chofer.alternaDisponibilidad();
						btnNoDisp.setBounds(125, 130, 89, 50);

					}
				});
				btnNoDisp.setBounds(234, 10, 89, 50);


			}
		});
		btnNoDisp.setForeground(Color.BLACK);
		btnNoDisp.setBackground(new Color(255, 0, 0));
		btnNoDisp.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNoDisp.setBounds(125, 130, 89, 50);
		contentPane.add(btnNoDisp);

		JButton btnUltViaje = new JButton("Ult. Viaje");
		btnUltViaje.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{

				JOptionPane.showMessageDialog(null, chofer.historial(viajes, chofer), "Ultimo viaje",
						JOptionPane.INFORMATION_MESSAGE);

			}
		});
		JButton btnOcupar = new JButton("Ocupar");

		JButton btnCobrar = new JButton("Cobrar");
		btnCobrar.setEnabled(false);
		btnCobrar.setBackground(new Color(255, 204, 0));
		btnCobrar.setForeground(Color.BLACK);
		btnCobrar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{

				InfoCobrar info = new InfoCobrar(chofer);
				info.setVisible(true);
				btnOcupar.setEnabled(true);
				btnLibre.setText("Libre");
				btnLibre.setBackground(new Color(0, 255, 0));
				btnDisp.setBounds(234, 10, 89, 50);
				btnNoDisp.setBounds(125, 130, 89, 50);
				btnCobrar.setEnabled(false);
			}
		});
		btnCobrar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnCobrar.setBounds(234, 69, 89, 50);
		contentPane.add(btnCobrar);


		btnOcupar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				btnLibre.setBackground(new Color(255, 102, 0));
				btnLibre.setText("Ocupado");
				btnOcupar.setEnabled(false);
				btnCobrar.setEnabled(true);
				btnDisp.setBounds(125, 130, 89, 50);
				btnNoDisp.setBounds(234, 10, 89, 50);
			}
		});
		btnOcupar.setBackground(new Color(255, 102, 0));
		btnOcupar.setForeground(new Color(0, 0, 0));
		btnOcupar.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnOcupar.setBounds(125, 69, 89, 50);
		contentPane.add(btnOcupar);
		btnUltViaje.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		btnUltViaje.setBounds(10, 130, 89, 50);
		contentPane.add(btnUltViaje);

		JButton btnDatos = new JButton("Datos");
		btnDatos.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				
				ListaUsuarios users= JsonUtiles.abrirArchivoUsuarios();
				Usuario aux2= users.buscaChofer(chofer.getLicencia());
				JOptionPane.showMessageDialog(null, "Total de viajes: " + ((Chofer)aux2).getCantViajes() + "/n Total: $ "
						+ ((Chofer)aux2).getRecaudado(), // Colocar el toString de chofer para que muestre totales...
						"Datos Laborales", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnDatos.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		btnDatos.setBounds(235, 130, 89, 50);
		contentPane.add(btnDatos);

		JButton btnZonas = new JButton("Zonas");
		btnZonas.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					JOptionPane.showMessageDialog(null, "El punto caliente de viajes es:" + Coordenadas.puntoCalor() + ".", // Definir parametro
							"Zona de calor", JOptionPane.INFORMATION_MESSAGE);
				} catch (HeadlessException | JSONException e)
				{

					e.printStackTrace();
				}
			}
		});
		btnZonas.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		btnZonas.setBounds(10, 69, 89, 50);
		contentPane.add(btnZonas);


		lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblFecha.setBounds(10, 202, 175, 23);
		lblFecha.setForeground(Color.WHITE);
		contentPane.add(lblFecha);


		lblHora = new JLabel("Hora");
		lblHora.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblHora.setBounds(195, 202, 129, 23);
		lblHora.setForeground(Color.WHITE);
		contentPane.add(lblHora);

		HiloReloj hiloReloj = new HiloReloj();
		hiloReloj.start();

	}
}