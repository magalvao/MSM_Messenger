/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msm.base;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import msm.contacts.view.ContactsFrame;
import msm.login.view.LoginFrame;
import msm.sounds.Sounds;

/**
 *
 * @author mgalvao3
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        LoginFrame window = new LoginFrame();
        window.setTitle("MSM Messenger - Login");
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        window.setLoginSucessfullListener(new LoginFrame.LoginSucessfullListener() {
            @Override
            public void onLoginSucessfull(String ip) {
                ContactsFrame contacts = new ContactsFrame();
                contacts.setIp(ip);
                contacts.initServer();
                contacts.setTitle("MSM Messenger");
                contacts.setLocationRelativeTo(null);
                contacts.setVisible(true);

                Sounds.play(Sounds.SoundName.LOGON);
                window.dispose();
            }
        });
    }
    
}
