/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msm.contacts.view;

import javax.swing.DefaultListModel;
import msm.model.Status;

/**
 *
 * @author mgalvao3
 */
public interface ContactsView {

    public void openMessageWindow(String userIp, String name);

    public void setOfflineList(DefaultListModel dlmOffline);

    public void setOnlineList(DefaultListModel dlmOnline);

    public void setUserName(String myName);
    
}
