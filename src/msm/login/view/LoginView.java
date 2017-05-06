/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msm.login.view;

import java.util.List;

/**
 *
 * @author mgalvao3
 */
public interface LoginView {

    void populateIPs(List<String> ips);

    void showIpError();

    void loginSuccessfull(String userIp);

    void setBackgroundColor(String bgColor);

    void setAvatarIcon(String imagePath);
    
}
