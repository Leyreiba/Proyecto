
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Main{
	public static void main(String args[]){
	    
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e) {}
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
        
        principal.getWindows();
    }

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		setVisible(true);
		
	}
}
