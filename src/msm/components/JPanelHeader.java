/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msm.components;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author mgalvao3
 */
public class JPanelHeader extends JPanel {
    
    private BufferedImage image;
    
    public JPanelHeader() {
       
    }
    
    public void setImage(String path) {
         try {                
          image = ImageIO.read(new File(path));
       } catch (IOException ex) {
            // handle exception...
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        if(image != null) {
            g.drawImage(image, 0, 0, this);
        }
    }
    
}
