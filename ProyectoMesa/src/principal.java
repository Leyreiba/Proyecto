import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import BasesDeDatos.BD;
import Datos.Cancion;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.FullScreenStrategy;
import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;

import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;

public class principal extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Atributo de VLCj
	// private EmbeddedMediaPlayerComponent mediaPlayerComponent;

	private Clip clip;
	private Clip clip2;

	private AudioInputStream audio;
	private AudioInputStream audio2;

	private JPanel contentPane;
	private JTextField textField1;
	private JTextField textField2;

	private int opcion = 1;
	

	public static BD bd = new BD();
	private JButton btncancion;

	EmbeddedMediaPlayerComponent mediaPlayerComponent;
	EmbeddedMediaPlayer mediaPlayer;

	EmbeddedMediaPlayerComponent mediaPlayerComponent2;
	EmbeddedMediaPlayer mediaPlayer2;
	static int ordenador = 0;// si es el de leyre a 0 si es el mio a 1

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		int opcion=0;
		
				
		if(opcion!=0){
		// Inicializar VLC.
		// Probar con el buscador nativo...
		boolean found = new NativeDiscovery().discover();
		// NativeLibrary.addSearchPath("vlc",
		// "/Users/MacOier/git/ProyectoMesaMezclas/ProyectoMesa/libsVLC-32bits");

		// System.out.println( LibVlc.INSTANCE.libvlc_get_version() ); //
		// Visualiza versi�n de VLC encontrada
		// Si no se encuentra probar otras opciones:
		if (!found) {
			// Buscar vlc como variable de entorno
			String vlcPath = System.getenv().get("vlc");
			if (vlcPath == null) {
				// Poner VLC a mano
				if (ordenador == 1) {
					System.setProperty("jna.library.path", "/Applications/VLC");
				} else {
					System.setProperty("jna.library.path", "c:\\Program Files\\videolan\\VLC");
				}
			} else { // Poner VLC desde la variable de entorno
				System.setProperty("jna.library.path", vlcPath);
			}
		}
		}
		principal frame = new principal();
		frame.setVisible(true);

	}

	/**
	 * Create the frame.
	 */

	public principal() {

		System.out.println("ABIERTA LA BD");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 516, 392);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Componente VLCJ

		if (opcion != 0) {
			mediaPlayerComponent = new EmbeddedMediaPlayerComponent() {
				private static final long serialVersionUID = 1L;

				@Override
				protected FullScreenStrategy onGetFullScreenStrategy() {
					return new Win32FullScreenStrategy(principal.this);
				}
			};
			mediaPlayer = mediaPlayerComponent.getMediaPlayer();

			mediaPlayerComponent2 = new EmbeddedMediaPlayerComponent() {
				private static final long serialVersionUID = 1L;

				//
				@Override
				protected FullScreenStrategy onGetFullScreenStrategy() {
					return new Win32FullScreenStrategy(principal.this);
				}
			};
			mediaPlayer2 = mediaPlayerComponent2.getMediaPlayer();

			mediaPlayerComponent.setBounds(0, 0, 0, 0);
			mediaPlayerComponent2.setBounds(0, 0, 0, 0);

			contentPane.add(mediaPlayerComponent);
			contentPane.add(mediaPlayerComponent2);
		}
		/**
		 * Introducir el repositorio de un fichero en las listas y al
		 * seleccionar una cancion pasar el nombre al textfield de abajo
		 */

		/**
		 * Creo un thread (lanzamiento de un hilo) para que mientras se ejecute
		 * el programa se siga ejecutando el dato del jTextField. Selecciono el
		 * texto de la lista y se escribe en el jTextField. Cada vez que
		 * selecciono una nueva canción, los threads anteriores se paran y se
		 * crea el de la nueva selección
		 */

		/**
		 * lista 1
		 */
		ArrayList<Thread> aThreads = new ArrayList<Thread>();
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		JList<String> list1 = new JList<String>(dlm);
		list1.setBounds(1, 1, 138, 336);
		list1.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				for (int i = 0; i < aThreads.size(); i++) {
					aThreads.get(i).stop();
					aThreads.remove(i);
				}
				textField1.setText(list1.getSelectedValue());
				aThreads.add(new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						char texto[] = textField1.getText().toCharArray();
						while (true) {
							/**
							 * Transformo el array a uno de string y desplazo el
							 * texto seleccionado hacia la izquierda
							 */
							textField1.setText(String.valueOf(texto));
							char primera = texto[0];
							for (int i = 0; i < texto.length - 1; i++)
								texto[i] = texto[i + 1];
							texto[texto.length - 1] = primera;
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

			// public void volcarBD(){
			//
			// }
		});
		list1.setToolTipText("");
		// list1.setBounds(37, 68, 140, 203);
		contentPane.add(list1);

		/**
		 * Añadimos una JScrollPane a la lista
		 */

		list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(list1);
		scrollPane.setBounds(37, 68, 140, 203);

		contentPane.add(scrollPane);

		/***
		 * lista2
		 */

		ArrayList<Thread> aThreads2 = new ArrayList<Thread>();
		DefaultListModel<String> dlm2 = new DefaultListModel<String>();
		JList<String> list2 = new JList<String>(dlm2);
		list2.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg1) {
				for (int a = 0; a < aThreads2.size(); a++) {
					aThreads2.get(a).stop();
					aThreads2.remove(a);
				}
				textField2.setText(list2.getSelectedValue());
				aThreads2.add(new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						char texto2[] = textField2.getText().toCharArray();
						while (true) {
							/**
							 * Transformo el array a uno de string y desplazo el
							 * texto seleccionado hacia la izquierda
							 */
							textField2.setText(String.valueOf(texto2));
							char primera = texto2[0];
							for (int a = 0; a < texto2.length - 1; a++)
								texto2[a] = texto2[a + 1];
							texto2[texto2.length - 1] = primera;
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
		slider.setMinimum(-50);
		slider.setMaximum(50);
		slider.setValue(0);
		slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				if (slider.getValue() > 0) {
					mediaPlayer2.setVolume(50 + slider.getValue());
					mediaPlayer.setVolume(50 - slider.getValue());
				} else {
					mediaPlayer2.setVolume(50 + slider.getValue());
					mediaPlayer.setVolume(50 - slider.getValue());

				}
			}
		});
		contentPane.add(slider);

		//
		JSlider slider_3 = new JSlider();
		slider_3.setOrientation(SwingConstants.VERTICAL);
		slider_3.setBounds(273, 115, 28, 156);
		slider_3.setMinimum(0);
		slider_3.setMaximum(100);
		slider_3.setValue(50);

		JSlider slider_2 = new JSlider();
		slider_2.setOrientation(SwingConstants.VERTICAL);
		slider_2.setBounds(235, 115, 28, 156);
		slider_2.setMinimum(0);
		slider_2.setMaximum(100);
		slider_2.setValue(50);

		JSlider slider_1 = new JSlider();
		slider_1.setOrientation(SwingConstants.VERTICAL);
		slider_1.setMinimum(0);
		slider_1.setMaximum(100);
		slider_1.setValue(50);
		slider_1.setBounds(197, 115, 28, 156);
		slider_3.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub

				mediaPlayer.setVolume(slider_3.getValue());

			}
		});
		contentPane.add(slider_1);

		slider_2.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub

				mediaPlayer2.setVolume(slider_2.getValue());
				mediaPlayer.setVolume(slider_2.getValue());

			}
		});

		contentPane.add(slider_2);

		slider_1.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub

				mediaPlayer2.setVolume(slider_1.getValue());
			}
		});
		contentPane.add(slider_3);

		// BOTON PLAY IZQUIERDA
		JButton BotonPlayIzq = new JButton(">");
		BotonPlayIzq.setBounds(37, 36, 58, 23);

		// BOTON PLAY DERECHA
		JButton BotonPlayDrcha = new JButton(">");
		BotonPlayDrcha.setBounds(324, 36, 58, 23);

		contentPane.add(BotonPlayIzq);
		BotonPlayDrcha.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (opcion != 0) {
					lanzaVideo(bd.obtenerRuta(list2.getSelectedValue()));
				} else {
					try {
						audio = AudioSystem.getAudioInputStream(new File(bd.obtenerRuta(list2.getSelectedValue())));
						clip = AudioSystem.getClip();
						clip.open(audio);
						clip.start();
						
					} catch (Exception h) {
					h.printStackTrace();
//				PARA CAMBIAR EL VOLUMEN
//				FloatControl volumen= (FloatControl) AQUI EL CLIP O CLIP2.getControl (javax.sound.sampled.FloatControl.Type.MASTER_GAIN);
//				volume.setValue(AQUI PONES LOS DECIBELIOS QUE QUIERES CAMBIAR + O -);
				
					//Si pones opcion a 0 coge lo de wav, si pones cualquier otro numero coge VLC
//					
					}
				}
			}
		});
		// BOTON PAUSA IZQUIERDA
		JButton BotonPausaIzq = new JButton("||");
		BotonPausaIzq.setBounds(119, 36, 58, 23);
		BotonPausaIzq.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(opcion!=0){
				mediaPlayer2.pause();}else{
					clip2.stop();
				}
			}
		});
		contentPane.add(BotonPausaIzq);

		BotonPlayIzq.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (opcion != 0) {
					lanzaVideo2(bd.obtenerRuta(list1.getSelectedValue()));
				} else {
				}
				try {
					audio2 = AudioSystem.getAudioInputStream(new File(bd.obtenerRuta(list1.getSelectedValue())));
					clip2 = AudioSystem.getClip();
					clip2.open(audio2);
					clip2.start();
				} catch (Exception h) {

				}

			}
		});
		contentPane.add(BotonPlayDrcha);

		// BOTON PAUSA DERECHA
		JButton BotonPausaDrcha = new JButton("||");
		BotonPausaDrcha.setBounds(406, 36, 58, 23);
		BotonPausaDrcha.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(opcion!=0){
				mediaPlayer.pause();}else{
					clip.stop();
				}
			}
		});
		contentPane.add(BotonPausaDrcha);

		JButton btnEcualizador = new JButton("ECUALIZADOR");
		btnEcualizador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// relacionar este botón con la clase ecualizador(reproducir el
				// main)

				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e1) {
				}
				java.awt.EventQueue.invokeLater(new Runnable() {

					@Override
					public void run() {
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

		btncancion = new JButton("Elegir canci\u00F3n");

		btncancion.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 11));
		btncancion.setBounds(187, 69, 127, 23);
		contentPane.add(btncancion);

		btncancion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/**
				 * Creo una jfilechooser donde voy a abrir la ventana de donde
				 * voy a coger la música. Activo la selección multiple para
				 * poder elegir más de una canción. Introduzco las canciones
				 * seleccionadas(el nombre) en las jlist
				 * 
				 */
				// JFileChooser fc = new JFileChooser(new
				// File("/Users/MacOier/Desktop"));
				JFileChooser fc = new JFileChooser();
				fc.setMultiSelectionEnabled(true);
				int result = fc.showOpenDialog(null);

				if (result == JFileChooser.APPROVE_OPTION) {
					File[] cancionesSeleccionadas = fc.getSelectedFiles();
					DefaultListModel<String> dlm = (DefaultListModel<String>) list1.getModel();
					for (int i = 0; i < cancionesSeleccionadas.length; i++) {
						System.out.println("VAMOS A INSERTAR UNA CANCION");

						Cancion c = new Cancion(cancionesSeleccionadas[i].getName(), bd.obtenerGeneroAleatorio(),
								cancionesSeleccionadas[i].getAbsolutePath());
						System.out.println(c.getRuta());
						System.out.println(c.getGenero());
						System.out.println(c.getTitulo());

						bd.insertarNuevaCancion(c);
						System.out.println("Cancion insertada");
						dlm.addElement(cancionesSeleccionadas[i].getName());
						System.out.println(cancionesSeleccionadas[i].getName());
					}

					dlm = (DefaultListModel<String>) list2.getModel();
					for (int i = 0; i < cancionesSeleccionadas.length; i++) {
						dlm.addElement(cancionesSeleccionadas[i].getName());
						System.out.println(cancionesSeleccionadas[i].getName());
					}

				}

			}
		});
		JButton btnGenero = new JButton("Elegir g\u00E9nero");
		btnGenero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		btnGenero.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 11));
		btnGenero.setBounds(187, 92, 127, 23);
		contentPane.add(btnGenero);

		// TODO: VENTANA CERRADA->Borrar BD

	}

	private void lanzaVideo(String ruta) {
		if (mediaPlayer != null) {
			File ficVideo = new File(ruta);
			mediaPlayer.playMedia(ficVideo.getAbsolutePath());
			mediaPlayer.setVolume(60);

		} else {

		}
	}

	private void lanzaVideo2(String ruta) {
		if (mediaPlayer2 != null) {
			File ficVideo = new File(ruta);
			mediaPlayer2.playMedia(ficVideo.getAbsolutePath());
			mediaPlayer2.setVolume(60);

		} else {

		}

		// if(JList.cancion(nº).isSelected()){
		//
		// JTextFiel.setText(cancion(nº));
		// }

		// //Creación del panel, que contendra JList
		// JPanel panel = new JPanel();
		// panel.setLayout(null);
		//
		// //creación de los elememtos que componen la lista
		// String[] nombres = {"Ana","Margarita","Daniela","Divian",
		// "Leslie","Paz","Andrea","Macarena"};
		// //creación del objeto lista
		// JList lista = new JList(nombres);
		// //se cambia la orientación de presentación y el ajuste
		// lista.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		// //selecciona un elemento de la lista
		// Object[] seleccion = lista.getSelectedValues();
		// //recoge el indice de los seleccionados
		// int[] indices = lista.getSelectedIndices();
		// // aquí se crea el objeto, es decir la barra de desplazamiento
		// JScrollPane barraDesplazamiento = new JScrollPane(lista);
		// barraDesplazamiento.setBounds(10,30,200,110);
		// //Agrega la barra de desplazamiento al panel
		// panel.add(barraDesplazamiento);
		//
		// ventana.add(panel);
		// ventana.setVisible(true);
		// }
		// }

	}
}