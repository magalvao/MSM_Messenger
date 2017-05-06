/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msm.contacts.presenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import msm.Message;
import msm.Popup;
import msm.base.BasePresenter;
import msm.communication.Client;
import msm.communication.Server;
import msm.communication.model.Ping;
import msm.components.ListEntryCellRenderer;
import msm.contacts.model.Contact;
import msm.contacts.view.ContactsFrame;
import msm.contacts.view.ContactsView;
import msm.dao.AppPreferences;
import msm.model.ListEntry;
import msm.model.Status;
import msm.model.StatusIcons;
import msm.sounds.Sounds;

/**
 *
 * @author mgalvao3
 */
public class ContactsPresenter extends BasePresenter {
    private ContactsView mView;
    private ArrayList<msm.contacts.model.Contact> mContacts;
    private ArrayList<Message> mMessages;
    private boolean pingingContacts = false;
    private String mMyName = "?????";
    private String mMyIp;
    
    public ContactsPresenter(ContactsView view) {
        mView = view;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMessages = new ArrayList<>();
    }
    
    

    public void startServer() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Starting server...");
                    Server server = new Server();
                    server.setOnContactUpdatedListener(new Server.OnServerReceived() {
                        @Override
                        public void onContactUpdated(String name, String ip, Status status, String statusMsg) {
                            setContactStatus(name, ip, status, statusMsg);
                        }

                        @Override
                        public void onMessageReceived(String fromIp, String msg, String fromName) {
                            
                            for(Message m : mMessages) {
                                if(m.getContactIP().equals(fromIp)) {
                                    m.messageReceived(msg, fromName);
                                    return;
                                }
                            }
                            
                            mView.openMessageWindow(fromIp, fromName);
                            
                            for(Message m : mMessages) {
                                if(m.getContactIP().equals(fromIp)) {
                                    m.messageReceived(msg, fromName);
                                    return;
                                }
                            }
                        }
                    });
                    server.start();
                } catch (IOException ex) {
                    System.out.println("IO Error");
                    Logger.getLogger(ContactsFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }

    public void startPingContacts() {
        ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
        timer.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if(!pingingContacts) {
                    System.out.println("Pinging contacts...");
                    pingContacts();
                }
            }
        }, 0, 5, TimeUnit.SECONDS);
    }

    public void readContactsFromDisk() {
        AppPreferences prefs = new AppPreferences();
        try {
            mContacts = prefs.loadContacts();
            mMyName = prefs.getMyName();
            mView.setUserName(mMyName);
            
        } catch (Exception ex) {
            mContacts = new ArrayList<>();
        }
    }
    
    private void pingContacts() {
        pingingContacts = true;
        
        for(Contact contact : mContacts) {
            try {
                Client client = new Client(contact.getIP());
                client.setOnClientOfflineListener(new Client.OnClientOffline() {
                    @Override
                    public void onClientOffline(String ip) {
                        setContactStatus(ip, Status.OFFLINE);
                    }
                });

                Ping ping = new Ping(contact.getIP(), this.mMyName, Status.ONLINE, "", mMyIp);
                System.out.println("Pinging " + contact.getName() + " (" + contact.getIP() + ")");
                client.send(ping);
            } catch (IOException ex) {
                Logger.getLogger(ContactsFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        pingingContacts = false;
    }
    
    private void setContactStatus(String ip, Status status) {
        for(msm.contacts.model.Contact contact : mContacts) {
            if(contact.getIP().equals(ip)) {
                contact.setStatus(status);
            }
        }
        
        refreshContacts();
    }

    public void refreshContacts() {
        if(mContacts != null) {
            DefaultListModel dlmOffline = new DefaultListModel();
            DefaultListModel dlmOnline = new DefaultListModel();
            
            for(msm.contacts.model.Contact contact : mContacts) {
                if(contact.getStatus() == Status.OFFLINE) {
                    dlmOffline.addElement(new ListEntry(contact.getName() + " [" + contact.getIP() + "]", 
                                             StatusIcons.getIcon(Status.OFFLINE)));
                } else {
                    dlmOnline.addElement(new ListEntry(contact.getName() + " [" + contact.getIP() + "]", 
                                             StatusIcons.getIcon(contact.getStatus())));
                }
            }
            
            mView.setOfflineList(dlmOffline);
            mView.setOnlineList(dlmOnline);

            saveContacts();
        }
    }
    
    private void setContactStatus(String name, String ip, Status status, String statusMsg) {
        for(Contact contact : mContacts) {
            if(contact.getIP().equals(ip)) {
                if(contact.getStatus() == Status.OFFLINE && status != Status.OFFLINE) {
                    Popup popup = new Popup(name, "acabou de entrar", Sounds.SoundName.ONLINE);
                    popup.setIp(ip);
                    popup.setOnClickListener(new Popup.PopupListener() {
                        @Override
                        public void OnPopupClicked(String userIp) {
                            for(Message m : mMessages) {
                                if(m.getContactIP().equals(userIp)) {
                                    java.awt.EventQueue.invokeLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            m.toFront();
                                            m.repaint();
                                        }
                                    });

                                    return;
                                }
                            }

                            mView.openMessageWindow(userIp, name);
                        }
                    });
                    popup.fadeIn();
                }
                
                contact.setName(name);
                contact.setStatus(status);
                contact.setStatusMessage(statusMsg);
            }
        }
        
        refreshContacts();
    }

    private void saveContacts() {
        AppPreferences pref = new AppPreferences();
        try {
            pref.saveContacts(mContacts);
        } catch (IOException ex) {
            Logger.getLogger(ContactsFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void openMessage(String contactIp, String name) {
        for(Message m : mMessages) {
            if(m.getContactIP().equals(contactIp)) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        m.toFront();
                        m.repaint();
                    }
                });

                return;
            }
        }

        mView.openMessageWindow(contactIp, name);
    }

    public void setIp(String ipAddress) {
        mMyIp = ipAddress;
    }

    public void addWindowToList(Message m) {
        mMessages.add(m);
    }

    public void addWindowListeners(Message m, String contactIp) {
        m.setWindowClosedListener(new Message.OnMessageListener() {
            @Override
            public void onWindowClosed(String ip) {
                Iterator<Message> it = mMessages.iterator();
                while(it.hasNext()) {
                    Message item = it.next();
                    if(item.getContactIP().equals(contactIp)) {
                        it.remove();
                    }
                }
            }

            @Override
            public void onMessageSent(String destinationIP, String msg) {
                try {
                    Client client = new Client(destinationIP);

                    msm.communication.model.Message message = new msm.communication.model.Message(destinationIP, msg, mMyName);
                    System.out.println("Messaging " + message.getDestIP());
                    client.send(message);
                } catch (IOException ex) {
                    Logger.getLogger(ContactsFrame.class.getName()).log(Level.SEVERE, null, ex);
                    //TODO: mensagem nao entregue
                } 
            }
        });
    }

    public String getMyName() {
        return mMyName;
    }

    public void setUserName(String name) {
        mMyName = name;
        mView.setUserName(name);
    }
}
