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
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

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
		
		/**
		 * Creo un thread para que mientras se ejecute el programa se 
		 * siga ejecutando el dato del jTextField.
		 * Selecciono el texto de la lista y se escribe en el jTextField.
		 * Cada vez que selecciono una nueva canción, los threads anteriores se paran y 
		 * se crea el de la nueva selección
		 * */
		
		
		/**lista 1
		 * */
		ArrayList<Thread> aThreads = new ArrayList<Thread>();
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		JList<String> list1 = new JList<String>(dlm);
		list1.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				for(int i=0;i<aThreads.size();i++){
					aThreads.get(i).stop();
					aThreads.remove(i);
				}
				textField1.setText(list1.getSelectedValue());
				aThreads.add(new Thread ( new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
					    char texto[] = textField1.getText().toCharArray();
					    while(true){
					    	/**
					    	 * Transformo el array a uno de string y desplazo el texto seleccionado hacia 
					    	 * la izquierda
					    	 * */
					    	textField1.setText(String.valueOf(texto));
					    	char primera = texto[0];
					    	for(int i=0;i<texto.length-1;i++)
					    		texto[i] = texto[i+1];
					    	texto[texto.length-1]=primera;
					    	try {
								Thread.sleep(300);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					    }
					}
				}));
				aThreads.get(0).start();
			}
			
//			public void volcarBD(){
//			
//			}
		});
		list1.setToolTipText("");
//		list1.setBounds(37, 68, 140, 203);
		contentPane.add(list1);
		
	
		
		/**
		 * Añadimos una JScrollPane a la lista
		 * */
		
		 list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         JScrollPane scrollPane = new JScrollPane(list1);
         scrollPane.setBounds(37, 68, 140, 203);
         contentPane.add(scrollPane);
         
         
         
         /*** lista2
          * */
 		
 		ArrayList<Thread> aThreads2 = new ArrayList<Thread>();
 		DefaultListModel<String> dlm2 = new DefaultListModel<String>();
 		JList<String> list2 = new JList<String>(dlm2);
 		list2.addListSelectionListener(new ListSelectionListener() {
 			public void valueChanged(ListSelectionEvent arg1) {
 				for(int a=0;a<aThreads2.size();a++){
 					aThreads2.get(a).stop();
 					aThreads2.remove(a);
 				}
 				textField2.setText(list2.getSelectedValue());
 				aThreads2.add(new Thread ( new Runnable() {
 					
 					@Override
 					public void run() {
 						// TODO Auto-generated method stub
 					    char texto2[] = textField2.getText().toCharArray();
 					    while(true){
 					    	/**
 					    	 * Transformo el array a uno de string y desplazo el texto seleccionado hacia 
 					    	 * la izquierda
 					    	 * */
 					    	textField2.setText(String.valueOf(texto2));
 					    	char primera = texto2[0];
 					    	for(int a=0;a<texto2.length-1;a++)
 					    		texto2[a] = texto2[a+1];
 					    	texto2[texto2.length-1]=primera;
 					    	try {
 								Thread.sleep(300);
 							} catch (InterruptedException e) {
 								// TODO Auto-generated catch block
 								e.printStackTrace();
 							}
 					    }
 					}
 				}));
 				aThreads2.get(0).start();
 			}
 		});
 		list2.setToolTipText("");
 		list2.setBounds(37, 68, 140, 203);
 		contentPane.add(list2);
		
				
 		
		
		list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane2 = new JScrollPane(list2);
        scrollPane2.setBounds(324, 68, 140, 203);
        contentPane.add(scrollPane2);
		
	
				
		
		textField1 = new JTextField();
		textField1.setBounds(37, 286, 140, 26);
		contentPane.add(textField1);
		textField1.setColumns(10);
		 
		
		textField2 = new JTextField();
		textField2.setColumns(10);
		textField2.setBounds(324, 286, 140, 26);
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
				//relacionar este botón con la clase ecualizador(reproducir el main)
				
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
				 * Creo una jfilechooser donde voy a abrir la ventana de donde voy a coger la música.
				 * Activo la selección multiple para poder elegir más de una canción.
				 * Introduzco las canciones seleccionadas(el nombre) en las jlist
				 * 
				 * */
				JFileChooser fc = new JFileChooser(new File("C:\\Users\\Leyre\\Music"));
				fc.setMultiSelectionEnabled(true);
				int result = fc.showOpenDialog(v);
				if (result == JFileChooser.APPROVE_OPTION) {
					File[] cancionesSeleccionadas = fc.getSelectedFiles();
					DefaultListModel<String> dlm = (DefaultListModel<String>) list1.getModel(); 
					for(int i=0;i<cancionesSeleccionadas.length;i++){
						dlm.addElement(cancionesSeleccionadas[i].getName());
						System.out.println(cancionesSeleccionadas[i].getName());
					}
					dlm = (DefaultListModel<String>) list2.getModel(); 
					for(int i=0;i<cancionesSeleccionadas.length;i++){
						dlm.addElement(cancionesSeleccionadas[i].getName());
						System.out.println(cancionesSeleccionadas[i].getName());
					}
				}
				
			}
		});
		btnElegirCancin.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 11));
		btnElegirCancin.setBounds(187, 69, 127, 23);
		contentPane.add(btnElegirCancin);
		
		
				
		
		
		
		
		
//		if(JList.cancion(nº).isSelected()){
//
//			JTextFiel.setText(cancion(nº));
//		}
		
		
		
//		//Creación del panel, que contendra JList 
//		JPanel panel = new JPanel(); 
//		panel.setLayout(null); 
//
//		//creación de los elememtos que componen la lista 
//		String[] nombres = {"Ana","Margarita","Daniela","Divian", 
//		"Leslie","Paz","Andrea","Macarena"}; 
//		//creación del objeto lista 
//		JList lista = new JList(nombres); 
//		//se cambia la orientación de presentación y el ajuste 
//		lista.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION); 
//		//selecciona un elemento de la lista 
//		Object[] seleccion = lista.getSelectedValues(); 
//		//recoge el indice de los seleccionados 
//		int[] indices = lista.getSelectedIndices(); 
//		// aquí se crea el objeto, es decir la barra de desplazamiento 
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
