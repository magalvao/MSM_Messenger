/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msm.communication;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;
import msm.model.Status;

/**
 *
 * @author mgalvao3
 */
public class Server implements Runnable {
    
    public Socket mClient;
    public static int PORT = 2236;
    private OnServerReceived mUpdateListener;
    
    public Server() {
        
    }
    
    public void start() throws IOException {
        ServerSocket srv = new ServerSocket(PORT);
        
        System.out.println("Listening port " + PORT + "...");
        while (true) {
          Socket client = srv.accept();
          mClient = client;
          Thread t = new Thread(this);
          t.start();
        }
    }

    @Override
    public void run() {   
        System.out.println("Incoming package...");
        try {
            Scanner s = new Scanner(mClient.getInputStream());

            if(s.hasNext("PING")) {
                s.nextLine();
                String name = s.nextLine();
                String ip = s.nextLine();
                String statusString = s.nextLine();
                String statusMsg = s.nextLine();
                s.nextLine();
                
                System.out.println("PING from " + name + " (" + ip + ") with status " + statusString);
                
                if(mUpdateListener != null) {
                    Status status = Status.valueOf(statusString);
                    mUpdateListener.onContactUpdated(name, ip, status, statusMsg);
                }
                
                
            } else if (s.hasNext("MSG")) {
                s.nextLine();
                String destIp = s.nextLine();
                String sender = s.nextLine();
                String msg = s.nextLine();
                s.nextLine();
                
                String ip = mClient.getRemoteSocketAddress().toString().substring(1, mClient.getRemoteSocketAddress().toString().lastIndexOf(':'));
                
                System.out.println("MSG from " + sender + " (" + destIp + ") msg: " + msg);
                
                if(mUpdateListener != null) {
                    mUpdateListener.onMessageReceived(ip, msg, sender);
                }
            }

            //Finaliza objetos
            s.close();
            mClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static List<String> getNetworkInterfacesAddresses() throws SocketException {
        ArrayList<String> ips = new ArrayList<>();
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        ArrayList<NetworkInterface> list = Collections.list(nets);
        int i = 0;
        for (NetworkInterface netint : list) {
            Enumeration<InetAddress> addresses = netint.getInetAddresses();
            ArrayList<InetAddress> listAddresses = Collections.list(addresses);
            for(InetAddress address : listAddresses) {
                ips.add(address.toString());
            }
        }
        
        return ips;
    }
    
    public void setOnContactUpdatedListener(OnServerReceived listener) {
        mUpdateListener = listener;
    }

    public static interface OnServerReceived {
        void onContactUpdated(String name, String ip, Status status, String statusMsg);
        void onMessageReceived(String fromIp, String msg, String fromName);
    }
}
