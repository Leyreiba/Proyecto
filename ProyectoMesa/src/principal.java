import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import BasesDeDatos.BD;

import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

public class principal extends JFrame {

	private JPanel contentPane;
	private JTextField textField1;
	private JTextField textField2;
	public static BD bd;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					principal frame = new principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public principal() {
		bd = new BD();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 516, 392);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		/**
		 * Introducir el repositorio de un fichero en las listas y al seleccionar una cancion 
		 * pasar el nombre al textfield de abajo
		 */
		
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		 //CREAMOS LA LIST1
		             JList<String> list1 = new JList<String>(dlm);
		             list1.setToolTipText("");
		             list1.setBounds(37, 68, 140, 203);
		             contentPane.add(list1);  
		             
		             //Anyadir ScrollPane a la JList1
		                 list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		                 JScrollPane scrollPane = new JScrollPane(list1);
		                 scrollPane.setBounds(37, 68, 140, 203);
		                 contentPane.add(scrollPane);
		            
		                 
		               
		            
		 
		               //CREAMOS LA LIST2
		             JList<String> list2 = new JList<String>(dlm);
		             list2.setBounds(324, 68, 140, 203);
		             contentPane.add(list2);
		             //Anyadir ScrollPane2 a Jlist2
		               list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		               JScrollPane scrollPane2=new JScrollPane(list2);
		               scrollPane2.setBounds(324, 68, 140, 203);
		               contentPane.add(scrollPane2);
		               
		 	
		 		//Creamos el textField1
		 		textField1 = new JTextField();
		 		textField1.setColumns(10);
		 		textField1.setBounds(37, 286, 140, 26);
		 		//Anyadimos al textField1 el nombre del archivo seleccionado en list1
		 	
		 		//textField1.setText(list1.getSelectedValue().toString()); 
		 		contentPane.add(textField1);
		 		
		 		//Creamos el TextField2
		 		textField2 = new JTextField();
		 		textField2.setColumns(10);
		 		textField2.setBounds(324, 286, 140, 26);
		 		//Anyadimos al textField2 el nombre del archivo seleccionado en list2
		 		//textField2.setText(list2.getSelectedValue().toString());
		 		contentPane.add(textField2);
		 		
		JSlider slider = new JSlider();
		slider.setBounds(187, 286, 128, 26);
		contentPane.add(slider);
		
		JSlider slider_1 = new JSlider();
		slider_1.setOrientation(SwingConstants.VERTICAL);
		slider_1.setBounds(197, 97, 28, 156);
		contentPane.add(slider_1);
		
		JSlider slider_2 = new JSlider();
		slider_2.setOrientation(SwingConstants.VERTICAL);
		slider_2.setBounds(235, 97, 28, 156);
		contentPane.add(slider_2);
		
		JSlider slider_3 = new JSlider();
		slider_3.setOrientation(SwingConstants.VERTICAL);
		slider_3.setBounds(273, 97, 28, 156);
		contentPane.add(slider_3);
		
		JButton BotonPlayIzq = new JButton(">");
		BotonPlayIzq.setBounds(37, 36, 58, 23);
		contentPane.add(BotonPlayIzq);
		
		JButton BotonPausaIzq = new JButton("||");
		BotonPausaIzq.setBounds(119, 36, 58, 23);
		contentPane.add(BotonPausaIzq);
		
		JButton BotonPlayDrcha = new JButton(">");
		BotonPlayDrcha.setBounds(324, 36, 58, 23);
		contentPane.add(BotonPlayDrcha);
		
		JButton BotonPausaDrcha = new JButton("||");
		BotonPausaDrcha.setBounds(406, 36, 58, 23);
		contentPane.add(BotonPausaDrcha);
		
		JButton btnEcualizador = new JButton("ECUALIZADOR");
		btnEcualizador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//relacionar este bot�n con la clase ecualizador(reproducir el main)
				
				try {
		            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		        }
		        catch(Exception e1) {}
		        java.awt.EventQueue.invokeLater(new Runnable(){
		            
		        	@Override
		            public void run(){
		                try {
							new ecualizador().setVisible(true);
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            }
		        });
			}
		});
		

		btnEcualizador.setFont(new Font("Sylfaen", Font.ITALIC, 9));
		btnEcualizador.setBounds(197, 36, 104, 23);
		contentPane.add(btnEcualizador);
		
		JFrame v = this;
		JButton btnElegirCancin = new JButton("Elegir canci\u00F3n");
		btnElegirCancin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/**
				 * Creo una jfilechooser donde voy a abrir la ventana de donde voy a coger la m�sica.
				 * Activo la selecci�n multiple para poder elegir m�s de una canci�n.
				 * Introduzco las canciones seleccionadas(el nombre) en las jlist
				 * 
				 * */
				JFileChooser fc = new JFileChooser(new File("Users/MacOier/Desktop/MDownload"));
				fc.setMultiSelectionEnabled(true);
				int result = fc.showOpenDialog(v);
				if (result == JFileChooser.APPROVE_OPTION) {
					File[] cancionesSeleccionadas = fc.getSelectedFiles();
					DefaultListModel<String> dlm = (DefaultListModel<String>) list1.getModel(); 
					for(int i=0;i<cancionesSeleccionadas.length;i++){
						dlm.addElement(cancionesSeleccionadas[i].getName());
						//Aqui meter el insert a la base de datos y de esta manera conseguir que a la vez que guardamos el name 
						//de las canciones en la JList 
						System.out.println(cancionesSeleccionadas[i].getName());
					}
					list1.setModel(dlm);
				}
				
			}
		});
		btnElegirCancin.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 11));
		btnElegirCancin.setBounds(187, 69, 127, 23);
		contentPane.add(btnElegirCancin);
		
		
		
		
		
		
		
//		if(JList.cancion(n�).isSelected()){
//
//			JTextFiel.setText(cancion(n�));
//		}
		
		
		
//		//Creaci�n del panel, que contendra JList 
//		JPanel panel = new JPanel(); 
//		panel.setLayout(null); 
//
//		//creaci�n de los elememtos que componen la lista 
//		String[] nombres = {"Ana","Margarita","Daniela","Divian", 
//		"Leslie","Paz","Andrea","Macarena"}; 
//		//creaci�n del objeto lista 
//		JList lista = new JList(nombres); 
//		//se cambia la orientaci�n de presentaci�n y el ajuste 
//		lista.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION); 
//		//selecciona un elemento de la lista 
//		Object[] seleccion = lista.getSelectedValues(); 
//		//recoge el indice de los seleccionados 
//		int[] indices = lista.getSelectedIndices(); 
//		// aqu� se crea el objeto, es decir la barra de desplazamiento 
//		JScrollPane barraDesplazamiento = new JScrollPane(lista); 
//		barraDesplazamiento.setBounds(10,30,200,110); 
//		//Agrega la barra de desplazamiento al panel 
//		panel.add(barraDesplazamiento); 
//
//		ventana.add(panel); 
//		ventana.setVisible(true); 
//		} 
//		} 

		
		
		
		
	}
}
