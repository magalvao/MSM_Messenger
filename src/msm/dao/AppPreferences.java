/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msm.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import java.util.regex.Pattern;
import msm.AddContact;
import msm.contacts.model.Contact;
import msm.model.Status;

/**
 *
 * @author mgalvao3
 */
public class AppPreferences {
    
    private static final String FILENAME_CONTACTS = "contacts.dat";
    private static final String FILENAME_SETTINGS = "settings.dat";
    private static final String KEY_CONTACTS = "CONTACTS";
    
    BufferedWriter bw = null;
    BufferedReader br = null;
    FileWriter fw = null;
    FileReader fr = null;

    final String PREF_NAME = "MSM_PREFERENCES";
    
    public AppPreferences() {

    }
    
    public Boolean addNewContact(String ip) throws IOException, ClassNotFoundException {
        
        Contact contact = new Contact("?????", ip);
        contact.setStatus(Status.OFFLINE);
        contact.setStatusMessage("");
        
        ArrayList<Contact> contactList = loadContacts();
        
        if(userAlreadyExists(contact, contactList)) {
            return false;
        } 
        
        contactList.add(contact);
        
        StringBuilder sBuilder = new StringBuilder();
        for(Contact c : contactList) {
            sBuilder.append(c.getName()).append("|||")
                    .append(c.getIP()).append("|||")
                    .append(c.getStatus().name()).append("|||")
                    .append(c.getStatusMessage()).append("\r\n");
        }
        
        writeFile(sBuilder.toString(), FILENAME_CONTACTS);
        
        return true;
    }
    
    public ArrayList<Contact> loadContacts()  {
        String fileContents = null;
        try {
            fileContents = readFile(FILENAME_CONTACTS);
        } catch(Exception ex) {
            return new ArrayList<>();
        }
        
        ArrayList<Contact> contactList = new ArrayList<>();
        String[] lines = fileContents.split("\\r?\\n");
        for(String line : lines) {
            if(!line.isEmpty()) {
                String[] contactArray = line.split(Pattern.quote("|||"));
                Contact contact = new Contact(contactArray[0], contactArray[1]);
                contactList.add(contact);
            }
        }
        
        return contactList;
    }
    
     public void saveContacts(ArrayList<Contact> contacts) throws IOException {
        StringBuilder sBuilder = new StringBuilder();
        for(Contact c : contacts) {
            sBuilder.append(c.getName()).append("|||")
                    .append(c.getIP()).append("|||")
                    .append(c.getStatus().name()).append("|||")
                    .append(c.getStatusMessage()).append("\r\n");
        }
        
        writeFile(sBuilder.toString(), FILENAME_CONTACTS);
     }

    private void writeFile(String content, String filename) throws IOException {
        fw = new FileWriter(filename);
        bw = new BufferedWriter(fw);
        bw.write(content);
        
        bw.close();
        fw.close();
    }
    
    private String readFile(String filename) throws FileNotFoundException, IOException {
        fr = new FileReader(filename);
        br = new BufferedReader(fr);

        String sCurrentLine;

        br = new BufferedReader(new FileReader(filename));

        StringBuilder sb = new StringBuilder();
        while ((sCurrentLine = br.readLine()) != null) {
                sb.append(sCurrentLine)
                        .append("\r\n");
        }
        
        return sb.toString();
    }

    private boolean userAlreadyExists(Contact contact, ArrayList<Contact> contactList) {
        for(Contact c : contactList) {
            if(c.getIP().equals(contact.getIP())) {
                return true;
            }
        }
        
        return false;
    }

    public String getMyName() {
        try {
            String settings = readFile(FILENAME_SETTINGS);
            return settings.split("\n")[0];
        } catch (IOException ex) {
            return "?????";
        }
    }
    
    public void setMyName(String name) {
        try {
            writeFile(name, FILENAME_SETTINGS);
        } catch (IOException ex) {
            Logger.getLogger(AppPreferences.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
