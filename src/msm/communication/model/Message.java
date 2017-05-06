/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msm.communication.model;

/**
 *
 * @author mgalvao3
 */
public class Message {
    private String mDestIP;
    private String mMsg;
    private String mSender;
    
    public Message(String ip, String msg, String sender) {
        mDestIP = ip;
        mMsg = msg;
        mSender = sender;
    }

    /**
     * @return the mDestIP
     */
    public String getDestIP() {
        return mDestIP;
    }

    /**
     * @return the mMsg
     */
    public String getMsg() {
        return mMsg;
    }

    public String getSender() {
        return mSender;
    }
    
    
}
