/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msm.communication;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import msm.communication.model.Message;
import msm.communication.model.Ping;

/**
 *
 * @author mgalvao3
 */
public class Client implements Runnable {

    private Socket mClient;
    private Ping mPing;
    private Message mMsg;
    private String mIP; 
    private OnClientOffline mListener;

    public Client(String ip){
        mIP = ip;
    }
    
    public void send(Ping ping) throws IOException {
        mPing = ping;

        Thread t = new Thread(this);
        t.start();
    }
    
    public void send(Message msg) throws IOException {
        Socket socket = new Socket(msg.getDestIP(), Server.PORT);

        mMsg = msg;

        Thread t = new Thread(this);
        t.start();
    }
    
    @Override
    public void run() {
        try {
            if(mPing != null) {
                mClient = new Socket();
                mClient.connect(new InetSocketAddress(mIP, Server.PORT), 1000);
                PrintStream out = new PrintStream(mClient.getOutputStream());
                System.out.println("Writing ping");
                out.print("PING\n");
                out.print(mPing.getMyName() + "\n");
                out.print(mPing.getMyIp() + "\n");
                out.print(mPing.getMyStatus() + "\n");
                out.print(mPing.getMyStatusMsg() + "\n ");
                
                out.close();
                
                mClient.close();

            } else if(mMsg != null) {
                mClient = new Socket();
                mClient.connect(new InetSocketAddress(mIP, Server.PORT), 1000);
                PrintStream out = new PrintStream(mClient.getOutputStream());
                System.out.println("Writing message");
                out.print("MSG\n");
                out.print(mMsg.getDestIP() + "\n");
                out.print(mMsg.getSender() + "\n");
                out.print(mMsg.getMsg() + "\n\n");
                
                out.close();
                
                mClient.close();
            }
            
        } catch (ConnectException | SocketTimeoutException  ex) {
            
            if(mListener != null) {
                mListener.onClientOffline(mPing.getDestinationIp());
            }
                
            } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setOnClientOfflineListener(OnClientOffline listener) {
        mListener = listener;
    }

    public static interface OnClientOffline {
        void onClientOffline(String ip);
    }
    
}
