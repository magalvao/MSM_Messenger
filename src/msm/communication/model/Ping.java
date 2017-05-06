/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msm.communication.model;

import msm.model.Status;

/**
 *
 * @author mgalvao3
 */
public class Ping {
    
    private String mDestinationIp;
    private String mMyName;
    private Status mMyStatus;
    private String mMyStatusMsg;
    private String mMyIp;
    
    public Ping(String destIp, String myName, Status status, String statusMsg, String myIP) {
        mDestinationIp = destIp;
        mMyName = myName;
        mMyStatus = status;
        mMyStatusMsg = statusMsg;
        mMyIp = myIP;
    }

    /**
     * @return the mDestinationIp
     */
    public String getDestinationIp() {
        return mDestinationIp;
    }

    /**
     * @return the mMyName
     */
    public String getMyName() {
        return mMyName;
    }

    /**
     * @return the mMyStatus
     */
    public Status getMyStatus() {
        return mMyStatus;
    }

    /**
     * @return the mMyStatusMsg
     */
    public String getMyStatusMsg() {
        return mMyStatusMsg;
    }

    /**
     * @return the mMyIp
     */
    public String getMyIp() {
        return mMyIp;
    }

    /**
     * @param mMyIp the mMyIp to set
     */
    public void setMyIp(String mMyIp) {
        this.mMyIp = mMyIp;
    }

    
    
}
