/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msm.model;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author mgalvao3
 */
public class ListEntry {
    private String value;
   private ImageIcon icon;
  
   public ListEntry(String value, ImageIcon icon) {
        this.value = value;
      
        Image image = icon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        this.icon = new ImageIcon(newimg);  // transform it back
   }
  
   public String getValue() {
      return value;
   }
  
   public ImageIcon getIcon() {
      return icon;
   }
  
   public String toString() {
      return value;
   }
}
