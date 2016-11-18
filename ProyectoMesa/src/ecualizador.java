import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;

public class ecualizador extends JFrame {

	private JPanel contentPane;
	private ArrayList<String> titulosVentana;
	

	 public ecualizador() throws InterruptedException{
		 
		 /**
		  * El nombre de la ventana va cambiando y va introduciendo aleatoriamente en un array de strings
		  * comentarios que yo he añadido
		  */
	
		 titulosVentana = new ArrayList<String>();
		 titulosVentana.add("titulo 1");
         titulosVentana.add("titulo 2");
         titulosVentana.add("titulo 3");
         titulosVentana.add("titulo 4");
         titulosVentana.add("titulo 5");
         
//         //array de colores para el fondo
//         colorBackground = new ArrayList<int>();
//         colorBackground.add(Color.BLACK);
//         colorBackground.add(Color.GREEN);
//         colorBackground.add(Color.BLUE);
//         colorBackground.add(Color.CYAN);
//         colorBackground.add(Color.DARK_GRAY);
//         colorBackground.add(Color.MAGENTA);
         

         
         //creo un hilo para que el array de nombres se vaya ejecutando a la vez que se ejecuta el resto del programa
         Thread threadTitulos = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//genero un bucle infinito para los titulos
				while(true){
					int num = (int) Math.floor(Math.random()*(titulosVentana.size()-1+1)+0);
					setTitle(titulosVentana.get(num));
					repaint();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
        threadTitulos.start();
	        setVisible(true);
	        //dimensiones de la ventana donde va a salir el ecualizador
	        setBounds(150,150,500,300);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        //filas y columnas que va a tener el ecualizador
	        setLayout(new GridLayout(1, 10));
	        //creo un array de barras
	        Barra[]b = new Barra[10];
	        //creo un thread
	        Thread[]Barra = new Thread[10];
	        //recorro el array y le voy asignando una barra a cada posicion
	        for(int i = 0; i<b.length; i++){
	            b[i] = new Barra();
	            //lanzo un thread por cada barra para que comienze el movimiento
	            Barra[i] = new Thread(b[i]);
	            Barra[i].start();
	            //voy añadiendo todas las barras del array
	            this.add(b[i]);
	        }
	    }
	    class Barra extends JPanel implements Runnable{
	    		   	 	    	
	        private int limite;
	        private int inferior;
	        private Rectangle rectangulo;
	        public Barra(){
	            setBackground(Color.BLACK);
	            Random r = new Random();
	            // en vez de hacer un random deberiamos coordinarlo con las canciones que van sonando
	            //crea un random desde 0 hasta el numero que le metamos entre()
	            //el limite de la barra va a ser ese numero entre()
	            limite = r.nextInt(300);
	            inferior = 0;
	            //Creamos un rectangulo con unas dimensiones
	            rectangulo = new Rectangle(0,400,250,limite);
	        }
	        public void paint(Graphics g){
	            super.paint(g);
	            g.setColor(Color.GREEN);
	            //rellenamos el rectagulo con sus dimensiones(0, 400, 250, inferior)
	            g.fillRect((int) rectangulo.getX(), (int) rectangulo.getY(), 
	            (int) rectangulo.getWidth(), (int) rectangulo.getHeight());
	        }
	        public void run(){
	            try{
	                while(true){
	                	//colocamos los rectangulos en el eje x e y(moviendose en 8 milisegundos)
	                    rectangulo.setLocation((int) rectangulo.getX(),
	                    (int) rectangulo.getY()-8);
	                    //velocidad de 8 milisegundos
	                    inferior += 8;
	                    if(inferior > limite){
	                        inferior = 0;
	                        rectangulo.setLocation((int) rectangulo.getX(), 300);
	                    }
	                    rectangulo.setSize(48, inferior);
	            		
				        repaint();
	                    //lanza un thread para controlar la velocidad de cambio de los rectangulos
	                    Thread.sleep(15);
	                }
	            } catch(InterruptedException e) {}
	            
	        }
	    }
		
			
			
		
	}
