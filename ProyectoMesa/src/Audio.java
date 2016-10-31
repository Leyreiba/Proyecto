//import java.io.File;
//
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.Clip;
//
//public class Audio {
//
//    /**
//     * Abre un fichero de sonido wav y lo reproduce
//     * @param args
//     */
//    public static void main(String[] args) {
//        try {
//            
//            // Se obtiene un Clip de sonido
//            Clip sonido = AudioSystem.getClip();
//            
//            // Se carga con un fichero
//            sonido.open(AudioSystem.getAudioInputStream(new File("Cheat_Codes_x_Kris_Kross_Amsterdam_-_Sex_Official_.wav")));
//            
//            // Comienza la reproducción
//            sonido.start();
//            
//            // Espera mientras se esté reproduciendo.
//            while (sonido.isRunning())
//                Thread.sleep(1000);
//            
//            // Se cierra el clip.
//            sonido.close();
//        } catch (Exception e) {
//            System.out.println("" + e);
//        }
//    }
//
//}


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Audio {

    public static void main(String[] args) {

        try {
            FileInputStream fis;
            Player player;
            fis = new FileInputStream("C:\\Users\\Leyre\\Music\\house\\WHTKD - Say To Me (Official Video).mp3");
            BufferedInputStream bis = new BufferedInputStream(fis);

            player = new Player(bis); // Llamada a constructor de la clase Player
            player.play();          // Llamada al método play
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
