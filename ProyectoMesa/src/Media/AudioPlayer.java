//package Media;
//
//import java.awt.*;
//
//import java.awt.event.ComponentAdapter;
//import java.awt.event.ComponentEvent;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.awt.image.BufferedImage;
//import java.io.File;
//
//import javax.sound.sampled.AudioFormat;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.DataLine.Info;
//import javax.sound.sampled.SourceDataLine;
//import javax.swing.*;
//
//import uk.co.caprica.vlcj.component.DirectAudioPlayerComponent;
//import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
//import uk.co.caprica.vlcj.discovery.NativeDiscovery;
//import uk.co.caprica.vlcj.player.MediaPlayer;
//import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
//import uk.co.caprica.vlcj.player.directaudio.AudioCallback;
//import uk.co.caprica.vlcj.player.directaudio.DefaultAudioCallbackAdapter;
//import uk.co.caprica.vlcj.player.directaudio.DirectAudioPlayer;
//import uk.co.caprica.vlcj.player.embedded.FullScreenStrategy;
//import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;
//
//
///** Ventana de reproductor de vídeo con visualización de audio
// * Utiliza la librería VLCj que debe estar instalada y configurada (http://www.capricasoftware.co.uk/projects/vlcj/index.html)
// * @author Andoni Eguíluz Morán
// * Facultad de Ingeniería - Universidad de Deusto
// */
//@SuppressWarnings("serial")
//public class AudioPlayer extends JFrame {
//
//	// Atributos de VLCj
//	MiEmbeddedMediaPlayerComponent mediaPlayerComponent;
//	MiDefaultEmbeddedMediaPlayer mediaPlayer;
//	MiAudioCallback ac;  // Audiocallback para reconocer el audio
//    GraficoDibujo grafico;  // Panel de dibujado
//
//		// Si se quisiera audioPlayer
//    private static class TestAudioCallbackAdapter extends DefaultAudioCallbackAdapter {
//	        public TestAudioCallbackAdapter(File output) { // throws IOException {
//	            super(4); // 4 is the block size for the audio samples
//	        }
//	        @Override
//	        protected void onPlay(DirectAudioPlayer mediaPlayer, byte[] data, int sampleCount, long pts) {
//            	// System.out.println( "  datos" + data.length );
//	        }
//	        @Override
//	        public void flush(DirectAudioPlayer mediaPlayer, long pts) {
//	            System.out.println("flush()");
//	        }
//	        @Override
//	        public void drain(DirectAudioPlayer mediaPlayer) {
//	            System.out.println("drain()");
//	        }
//	    }
//	    DirectAudioPlayer audioPlayer;
//	    
//	public AudioPlayer() {
//		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
//        ac = new MiAudioCallback();
//        mediaPlayerComponent = new MiEmbeddedMediaPlayerComponent(ac) {
//			private static final long serialVersionUID = 1L;
//			@Override
//            protected FullScreenStrategy onGetFullScreenStrategy() {
//                return new Win32FullScreenStrategy(AudioPlayer.this);
//            }
//        };
//        mediaPlayer = (MiDefaultEmbeddedMediaPlayer) mediaPlayerComponent.getMediaPlayer();
//        mediaPlayer.setRate( 1.0f );
//        add( mediaPlayerComponent, BorderLayout.CENTER );
//        grafico = new GraficoDibujo( 800, 150 );  // Panel de dibujado
//		grafico.setDimensiones( 0, Short.MAX_VALUE );
//        add( grafico, BorderLayout.SOUTH );
//        setSize( 800, 600 );
//        // Si se quisiera reproducir solo audio
//        // MediaPlayerFactory factory = new MediaPlayerFactory();
//        // audioPlayer = factory.newDirectAudioPlayer("S16N", 44100, 2, new TestAudioCallbackAdapter(new File("test.raw")));
//		// Eventos de reproducción de vídeo si se quieren usar
//		mediaPlayer.addMediaPlayerEventListener( 
//			new MediaPlayerEventAdapter() {
//				@Override
//				public void playing(MediaPlayer arg0) {  // Antes de videoOutput (quizás salvo que se iniciara la reproducción en pausa)
//				}
//				// El vídeo se acaba
//				@Override
//				public void finished(MediaPlayer mediaPlayer) {
//				}
//				// Hay error en el formato o en el fichero del vídeo
//				@Override
//				public void error(MediaPlayer mediaPlayer) {
//				}
//				// Evento que ocurre al cambiar el tiempo (cada 3 décimas de segundo aproximadamente)
//			    @Override
//			    public void timeChanged(MediaPlayer mediaPlayer, long newTime) {
//			    }
//		});
//		addWindowListener( new WindowAdapter() {
//			@Override
//			public void windowClosing(WindowEvent e) {
//				finish();
//			}
//		});
//	}
//	
//		public void finish() {
//			mediaPlayer.stop();
//			mediaPlayer.release();
//			ac.finish(null);
//		}
//		
//	//
//	// Métodos sobre el player de vídeo
//	//
//	
//	private void lanzaVideo( String fic ) {
//		if (mediaPlayer!=null) {
//			mediaPlayer.playMedia( fic ); 
//			// Si se quisiera un segundo player
//			audioPlayer.playMedia( ficVideo.file.getAbsolutePath() );
//			Date fechaVideo = new Date( ficVideo.getFile().lastModified() );
//		}
//	}
//
//	//
//	// Métodos de proceso de audio
//	//
//	
//    private static final String FORMAT = "S16N";
//    private static final int RATE = 44100;
//    private static final int CHANNELS = 2;
//	private class MiAudioCallback implements AudioCallback {
//		JavaSoundDirectAudioPlayerComponent js;
//		public MiAudioCallback() {
//			try {
//				js = new JavaSoundDirectAudioPlayerComponent(FORMAT, RATE, CHANNELS);
//				js.start();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		@Override public void resume(DirectAudioPlayer mediaPlayer, long pts) { js.resume(mediaPlayer, pts); }
//		@Override public void pause(DirectAudioPlayer mediaPlayer, long pts) { js.pause(mediaPlayer, pts); }
//		@Override public void flush(DirectAudioPlayer mediaPlayer, long pts) { js.flush(mediaPlayer, pts); }
//		@Override public void drain(DirectAudioPlayer mediaPlayer) { js.drain(mediaPlayer); }
//		@Override public void play(DirectAudioPlayer mediaPlayer, Pointer samples, int sampleCount, long pts) {
//			js.play( mediaPlayer, samples, sampleCount, pts );
//			// Cada sample son 2 shorts en stereo de los 32 bits
//			short[] sampl = samples.getShortArray(0, sampleCount*2);
//			// String datos = "";
//			// for (short i : sampl) datos += (i + " ");  // Para ver los datos en bruto
//			int max = 0;
//			for (short i : sampl) {
////				System.out.println( i );
//				int nuevo = i; if (i<0) nuevo = -nuevo; if (nuevo>max) max = nuevo;  // max es el máximo volumen del conjunto de muestras
//			}
//			grafico.nuevaLinea( max );
//			System.out.println( "*** "+ max );
//		}
//		public void finish(DirectAudioPlayer mediaPlayer) {
//			js.stop();
//		}
//		@Override
//		public void play(DirectAudioPlayer arg0, Pointer arg1, int arg2, long arg3) {
//			// TODO Auto-generated method stub
//			
//		}
//		@Override
//		public void play(DirectAudioPlayer arg0, Pointer arg1, int arg2, long arg3) {
//			// TODO Auto-generated method stub
//			
//		}
//	}
//	
//    private class JavaSoundDirectAudioPlayerComponent extends DirectAudioPlayerComponent {
//        private static final int BLOCK_SIZE = 4;
//        private static final int SAMPLE_BITS = 16; // BLOCK_SIZE * 8 / channels ???
//        private final AudioFormat audioFormat;
//        private final Info info;
//        private final SourceDataLine dataLine;
//        public JavaSoundDirectAudioPlayerComponent(String format, int rate, int channels) throws Exception {
//            super(format, rate, channels);
//            this.audioFormat = new AudioFormat(rate, SAMPLE_BITS, channels, true, false);
//            this.info = new Info(SourceDataLine.class, audioFormat);
//            this.dataLine = (SourceDataLine)AudioSystem.getLine(info);
//        }
//        public void start() throws Exception {
//            dataLine.open(audioFormat);
//            dataLine.start();
//        }
//        public void stop() {
//            dataLine.close();
//        }
//        @Override
//        public void play(DirectAudioPlayer mediaPlayer, Pointer samples, int sampleCount, long pts) {
//            int bufferSize = sampleCount * BLOCK_SIZE;
//            byte[] data = samples.getByteArray(0, bufferSize);
//            dataLine.write(data, 0, bufferSize);
//        }
//        @Override
//        public void drain(DirectAudioPlayer mediaPlayer) {
//            dataLine.drain();
//        }
//    }
//
//	
//	private static class GraficoDibujo extends JPanel {
//		private BufferedImage imagen;
//		private int ancho, alto;
//		private int min, max;
//		private Graphics2D g;
//		public GraficoDibujo(int width, int height) {
//			setPreferredSize( new Dimension( width, height ));
//			imagen = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB );
//			ancho = width; alto = height;
//			g = imagen.createGraphics();
//			addComponentListener( new ComponentAdapter() {
//				@Override
//				public void componentResized(ComponentEvent e) {
//					ancho = getWidth(); alto = getHeight();
//				}
//			});
//			g.setStroke( stroke );
//			borrar();
//		}
//		@Override
//		protected void paintComponent(Graphics g) {
//			g.drawImage(imagen, 0, 0, null);
//		}
//		public void setDimensiones( int min, int max ) {
//			this.min = min; this.max = max;
//		}
//		private int x = 0;
//		private int anchoLinea = 3;
//		private BasicStroke stroke = new BasicStroke(3.0f); 
//		public void nuevaLinea( int val ) {
//			double y = (alto - (val - min) * 1.0 / (max-min) * alto);
//			g.drawLine( x, alto, x, (int)(Math.round(y)) );
//			repaint();
//			x += anchoLinea;
//			if (x>=ancho) { 
//				x=0;
//				borrar();
//			}
//		}
//		public void borrar() {
//			g.setColor( Color.BLACK );
//			g.fillRect( 0, 0, ancho, alto );
//			g.setColor( Color.BLUE );
//			repaint();
//		}
// 	}
//
//	// Varible de ventana principal de la clase para el main
//	static AudioPlayer miVentana;
//	public static void main(String[] args) {
//		// Inicializar VLC.
//		// Probar con el buscador nativo...
//		boolean found = new NativeDiscovery().discover();
//    	// System.out.println( LibVlc.INSTANCE.libvlc_get_version() );  // Visualiza versión de VLC encontrada
//    	// Si no se encuentra probar otras opciones:
//		String vlcPath = System.getenv().get( "vlc" );
//    	if (!found) {
//			// Buscar vlc como variable de entorno
//			if (vlcPath==null) {  // Poner VLC a mano
//	        	System.setProperty("jna.library.path", "c:\\Program Files\\videolan\\VLC");
//			} else {  // Poner VLC desde la variable de entorno
//				System.setProperty( "jna.library.path", vlcPath );
//			}
//		}
//		if (vlcPath==null) // Poner VLC a mano en lugar de la variable de entorno
//			vlcPath = "c:\\Archivos de Programa\\VideoLAN\\VLC";
//		System.setProperty( "jna.library.path", vlcPath );
//		try {
//			@SuppressWarnings("unused")
//			EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();  // Para el chequeo de error de VLC
//		} catch (RuntimeException e) {
//			e.printStackTrace();
//			JOptionPane.showMessageDialog( null, "VLC no ha podido inicializarse. Revisa su path. Aparentemente era " + vlcPath, "Error grave", JOptionPane.ERROR_MESSAGE );
//			System.exit(1);
//		}
//		SwingUtilities.invokeLater( new Runnable() {
//			@Override
//			public void run() {
//				miVentana = new AudioPlayer();
//				miVentana.setVisible( false );
//				miVentana.lanzaVideo( "D:\\media\\videos\\AOrdenar\\Bruno Mars - Locked Out Of Heaven [OFFICIAL VIDEO].mp4" );  // Video de test
//			}
//		});
//	}
//	
//}
//
