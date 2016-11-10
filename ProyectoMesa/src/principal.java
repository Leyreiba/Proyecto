import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import BasesDeDatos.BD;

import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		
		
		JList list1 = new JList();
		list1.setToolTipText("");
		list1.setBounds(37, 68, 140, 203);
		contentPane.add(list1);
		//DefaultListModel<principal> dlmlist1 = (DefaultListModel<principal>)list1.getModel();
//		JScrollPane barraDesplazamiento = new JScrollPane(list); 
//		barraDesplazamiento.setBounds(10,30,200,110); 
//		list.add(barraDesplazamiento); 
		
		JList list2 = new JList();
		list2.setBounds(324, 68, 140, 203);
		contentPane.add(list2);
		
		
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
