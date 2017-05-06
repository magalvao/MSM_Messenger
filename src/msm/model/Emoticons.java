/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msm.model;

/**
 *
 * @author mgalvao3
 */
public class Emoticons {
    private static String baseHtml = "<img style='vertical-align:middle' src='file:emoticons/%%%.png' />";
    
    public static String findReplace(String msg) {
        for(int i = 0; i < symbs.length; i++) {
            while(msg.indexOf(symbs[i]) != -1) {
                String emoticonPath = baseHtml.replace("%%%", files[i]);
                msg = msg.replace(symbs[i], emoticonPath);
            }
        }
        
        return msg;
    }
    
    private static String[] symbs = {":)", ":D", ";)", ":-O", ":P", "(H)", ":@", ":S", ":$", ":(", 
        ":'("};
    private static String[] files = {"smile", "grin", "wink", "surprised", "tongue", "sunglasses", "angry", "confused", "ashamed", "sad",
        "cry"};
}
