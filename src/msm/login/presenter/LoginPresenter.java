/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msm.login.presenter;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import msm.base.BasePresenter;
import msm.communication.IP;
import msm.communication.Server;
import msm.login.view.LoginFrame;
import msm.login.view.LoginView;

/**
 *
 * @author mgalvao3
 */
public class LoginPresenter extends BasePresenter {

    private final LoginView mView;

    public LoginPresenter(LoginView view) {
        mView = view;
    }
    
    public void listIpAddresses() {
        ArrayList<String> ips;
        try {
            ips = (ArrayList<String>) Server.getNetworkInterfacesAddresses();
            mView.populateIPs(ips);
        } catch (SocketException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onEnterClicked(String userIp) {
        if(IP.isValid(userIp)) {
            mView.loginSuccessfull(userIp);
        } else {
            mView.showIpError();
        }
    }

    public void setUpWindow() {
        listIpAddresses();
        mView.setBackgroundColor("#ecf6f9");
        mView.setAvatarIcon("images/avatar_login.png");
    }
    
    
}
