/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msm.contacts.model;

import java.io.Serializable;
import msm.model.Status;

/**
 *
 * @author mgalvao3
 */
public class Contact implements Serializable {
    private String mName;
    private String mStatusMessage;
    private Status mStatus;
    private String mIP;
    private String mAvatar;
    
    public static String EMPTY_AVATAR = "data:image/jpeg;base64,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b2wAAAAAAEVtbERib29sAAAAAABJbnRyYm9vbAAAAAAAQmNrZ09iamMAAAABAAAAAAAAUkdCQwAAAAMAAAAAUmQgIGRvdWJAb+AAAAAAAAAAAABHcm4gZG91YkBv4AAAAAAAAAAAAEJsICBkb3ViQG/gAAAAAAAAAAAAQnJkVFVudEYjUmx0AAAAAAAAAAAAAAAAQmxkIFVudEYjUmx0AAAAAAAAAAAAAAAAUnNsdFVudEYjUHhsQFIAAAAAAAAAAAAKdmVjdG9yRGF0YWJvb2wBAAAAAFBnUHNlbnVtAAAAAFBnUHMAAAAAUGdQQwAAAABMZWZ0VW50RiNSbHQAAAAAAAAAAAAAAABUb3AgVW50RiNSbHQAAAAAAAAAAAAAAABTY2wgVW50RiNQcmNAWQAAAAAAAAAAABBjcm9wV2hlblByaW50aW5nYm9vbAAAAAAOY3JvcFJlY3RCb3R0b21sb25nAAAAAAAAAAxjcm9wUmVjdExlZnRsb25nAAAAAAAAAA1jcm9wUmVjdFJpZ2h0bG9uZwAAAAAAAAALY3JvcFJlY3RUb3Bsb25nAAAAAAA4QklNA+0AAAAAABAASAAAAAEAAgBIAAAAAQACOEJJTQQmAAAAAAAOAAAAAAAAAAAAAD+AAAA4QklNBA0AAAAAAAQAAAB4OEJJTQQZAAAAAAAEAAAAHjhCSU0D8wAAAAAACQAAAAAAAAAAAQA4QklNJxAAAAAAAAoAAQAAAAAAAAACOEJJTQP1AAAAAABIAC9mZgABAGxmZgAGAAAAAAABAC9mZgABAKGZmgAGAAAAAAABADIAAAABAFoAAAAGAAAAAAABADUAAAABAC0AAAAGAAAAAAABOEJJTQP4AAAAAABwAAD/////////////////////////////A+gAAAAA/////////////////////////////wPoAAAAAP////////////////////////////8D6AAAAAD/////////////////////////////A+gAADhCSU0EAAAAAAAAAgABOEJJTQQCAAAAAAAEAAAAADhCSU0EMAAAAAAAAgEBOEJJTQQtAAAAAAAGAAEAAAACOEJJTQQIAAAAAAAQAAAAAQAAAkAAAAJAAAAAADhCSU0EHgAAAAAABAAAAAA4QklNBBoAAAAAA0sAAAAGAAAAAAAA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VDE1OjExOjU2LTAzOjAwIiB4bXA6TWV0YWRhdGFEYXRlPSIyMDE3LTAzLTI3VDE1OjExOjU2LTAzOjAwIiB4bXA6TW9kaWZ5RGF0ZT0iMjAxNy0wMy0yN1QxNToxMTo1Ni0wMzowMCIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDpFQTA5MkNEQTM3MjA2ODExQTY2N0ZDMEE5QTVDMjFBQyIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDpFOTA5MkNEQTM3MjA2ODExQTY2N0ZDMEE5QTVDMjFBQyIgeG1wTU06T3JpZ2luYWxEb2N1bWVudElEPSJ4bXAuZGlkOkU5MDkyQ0RBMzcyMDY4MTFBNjY3RkMwQTlBNUMyMUFDIiBwaG90b3Nob3A6Q29sb3JNb2RlPSIzIiBwaG90b3Nob3A6SUNDUHJvZmlsZT0iRGlzcGxheSIgZGM6Zm9ybWF0PSJpbWFnZS9qcGVnIj4gPHhtcE1NOkhpc3Rvcnk+IDxyZGY6U2VxPiA8cmRmOmxpIHN0RXZ0OmFjdGlvbj0iY3JlYXRlZCIgc3RFdnQ6aW5zdGFuY2VJRD0ieG1wLmlpZDpFOTA5MkNEQTM3MjA2ODExQTY2N0ZDMEE5QTVDMjFBQyIgc3RFdnQ6d2hlbj0iMjAxNy0wMy0yN1QxNToxMTo1Ni0wMzowMCIgc3RFdnQ6c29mdHdhcmVBZ2VudD0iQWRvYmUgUGhvdG9zaG9wIENTNiAoTWFjaW50b3NoKSIvPiA8cmRmOmxpIHN0RXZ0OmFjdGlvbj0ic2F2ZWQiIHN0RXZ0Omluc3RhbmNlSUQ9InhtcC5paWQ6RUEwOTJDREEzNzIwNjgxMUE2NjdGQzBBOUE1QzIxQUMiIHN0RXZ0OndoZW49IjIwMTctMDMtMjdUMTU6MTE6NTYtMDM6MDAiIHN0RXZ0OnNvZnR3YXJlQWdlbnQ9IkFkb2JlIFBob3Rvc2hvcCBDUzYgKE1hY2ludG9zaCkiIHN0RXZ0OmNoYW5nZWQ9Ii8iLz4gPC9yZGY6U2VxPiA8L3htcE1NOkhpc3Rvcnk+IDxwaG90b3Nob3A6RG9jdW1lbnRBbmNlc3RvcnM+IDxyZGY6QmFnPiA8cmRmOmxpPnhtcC5kaWQ6RUE3MTg4ODgyRTIwNjgxMUE2NjdGQzBBOUE1QzIxQUM8L3JkZjpsaT4gPC9yZGY6QmFnPiA8L3Bob3Rvc2hvcDpEb2N1bWVudEFuY2VzdG9ycz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+ICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgPD94cGFja2V0IGVuZD0idyI/Pv/iDwRJQ0NfUFJPRklMRQABAQAADvRhcHBsAhAAAG1udHJSR0IgWFlaIAfhAAMAFwAPABoALWFjc3BBUFBMAAAAAEFQUEwAAAAAAAAAAAAAAAAAAAABAAD21gABAAAAANMtYXBwbAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEWRlc2MAAAFQAAAAYmRzY20AAAG0AAAD2mNwcnQAAAWQAAAAI3d0cHQAAAW0AAAAFHJYWVoAAAXIAAAAFGdYWVoAAAXcAAAAFGJYWVoAAAXwAAAAFHJUUkMAAAYEAAAIDGFhcmcAAA4QAAAAIHZjZ3QAAA4wAAAAMG5kaW4AAA5gAAAAPmNoYWQAAA6gAAAALG1tb2QAAA7MAAAAKGJUUkMAAAYEAAAIDGdUUkMAAAYEAAAIDGFhYmcAAA4QAAAAIGFhZ2cAAA4QAAAAIGRlc2MAAAAAAAAACERpc3BsYXkAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABtbHVjAAAAAAAAACIAAAAMaHJIUgAAABQAAAGoa29LUgAAABIAAAG8bmJOTwAAABQAAAHOaWQAAAAAABIAAAHiaHVIVQAAABYAAAH0Y3NDWgAA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";

    public Contact() {
    
    }
    
    public Contact(String name, String ip) {
        mName = name;
        mIP = ip;
        mStatus = Status.OFFLINE;
        mStatusMessage = "";
        mAvatar = EMPTY_AVATAR;
    }

    /**
     * @return the mName
     */
    public String getName() {
        return mName;
    }

    /**
     * @param mName the mName to set
     */
    public void setName(String mName) {
        this.mName = mName;
    }

    /**
     * @return the mStatusMessage
     */
    public String getStatusMessage() {
        return mStatusMessage;
    }

    /**
     * @param mStatusMessage the mStatusMessage to set
     */
    public void setStatusMessage(String mStatusMessage) {
        this.mStatusMessage = mStatusMessage;
    }

    /**
     * @return the mStatus
     */
    public Status getStatus() {
        return mStatus;
    }

    /**
     * @param mStatus the mStatus to set
     */
    public void setStatus(Status mStatus) {
        this.mStatus = mStatus;
    }

    /**
     * @return the mIP
     */
    public String getIP() {
        return mIP;
    }

    /**
     * @param mIP the mIP to set
     */
    public void setIP(String mIP) {
        this.mIP = mIP;
    }
    
    
}