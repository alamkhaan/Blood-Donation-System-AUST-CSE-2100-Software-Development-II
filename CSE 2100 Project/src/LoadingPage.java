
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Image.*;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;



public class LoadingPage {
    
    JFrame jFrame;
    JLabel jLabel;
    JLabel title;
    JProgressBar jProgressBar;
    BufferedImage img;
    
    public int WIDTH = 480;
    public int HEIGHT = 800;
    
    String url = "blood.png";
    ImageIcon icon;
    
    ResourceBundle rb;
    Locale locale;
    
    LoadingPage(){
       ;      
    }
    
    public void init(){
       jFrame = new JFrame();
       jFrame.setSize(WIDTH, HEIGHT);
       jFrame.setLayout(null);
       jFrame.getContentPane().setBackground(Color.white);
       jFrame.setVisible(true);
      
       icon = new ImageIcon(url);
       icon = new ImageIcon(icon.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_DEFAULT));   
       jLabel = new JLabel(icon, JLabel.CENTER);
       jLabel.setBounds( WIDTH/2 - icon.getIconWidth()/2 - 10, HEIGHT/2 - icon.getIconHeight(), 200, 200);
       
       //locale = new Locale("bn");
       //rb = ResourceBundle.getBundle("Language", locale);
       
       title = new JLabel();
       title.setText("Donate Blood Save lives.");
       title.setBounds(140, 570, 300, 20);
       title.setFont( new Font("Arial",Font.BOLD, 15) );
       title.setForeground(Color.red);
       
       
       jProgressBar = new JProgressBar();
       jProgressBar.setBounds(80,600, 300, 12);
       jProgressBar.setBorderPainted(false);
       jProgressBar.setBackground(Color.white);
       jProgressBar.setForeground(Color.red);
       jProgressBar.setValue(0);
       
       jFrame.add(jLabel);
       jFrame.add(jProgressBar);
       jFrame.add(title);
    }
    
    void stopResize(){
       //jLabel.setBounds( WIDTH/2 - icon.getIconWidth()/2, HEIGHT/2 - icon.getIconHeight(), 200, 200);
        jFrame.setSize(WIDTH, HEIGHT);
    }
    
    
    public static void main(String[] args) throws InterruptedException {
        
        LoadingPage lp1 = new LoadingPage();
        lp1.init();
        
        Thread x = new Thread(new Runnable(){
            @Override
            public void run() {
                while(true){
                    if( lp1.WIDTH == lp1.jFrame.getWidth() )
                    {
                        System.out.println(lp1.jFrame.getWidth()+" "+lp1.WIDTH);
                    }
                    else{
                        //lp1.WIDTH = lp1.jFrame.getWidth();     
                        lp1.stopResize();                       
                    }           
                    
                    if( lp1.HEIGHT == lp1.jFrame.getHeight() )
                    {
                        System.out.println(lp1.jFrame.getHeight()+" "+lp1.HEIGHT);
                    }
                    else{
                        //lp1.HEIGHT = lp1.jFrame.getHeight();     
                        lp1.stopResize();                       
                    }   
                }              
            }           
        });
        
        
        
        
        Thread y = new Thread(new Runnable(){
            @Override
            public void run() {
                for(int i = 0; i <= 100; i++){
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(LoadingPage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    lp1.jProgressBar.setValue(i);
                }
                new LoginPage().setVisible(true);               
                lp1.jFrame.setVisible(false);
               
            }
        });
        
        y.start(); 
        x.start();
        
        y.join();
        //x.join();
    }
    
}
