/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msm.model;

import javax.swing.ImageIcon;

/**
 *
 * @author mgalvao3
 */
public class StatusIcons {
   
    public static ImageIcon getIcon(Status status) {
        switch(status) {
            case ONLINE:
                return new ImageIcon("images/msn_online.png"); 
            case AWAY:
                return new ImageIcon("images/msn_away.png"); 
            case BUSY:
                return new ImageIcon("images/msn_busy.png"); 
            case OFFLINE:
            default:
                return new ImageIcon("images/msn_offline.png"); 
        }
    }
}
