/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msm.sounds;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author mgalvao3
 */
public class Sounds {
    public static void play(SoundName sound) {
        String soundFile;
        switch(sound) {
            case ONLINE:
                soundFile = "sounds/online.wav"; break;
            case MESSAGE:
                soundFile = "sounds/type.wav"; break;
            case NUDGE:
                soundFile = "sounds/nudge.wav"; break;
            case LOGON:
                soundFile = "sounds/logon.wav"; break;
            default:
                return;
        }

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFile));
            AudioFormat audioFormat = audioInputStream.getFormat();

            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);

            SourceDataLine sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(audioFormat);

            sourceLine.start();

            int nBytesRead = 0;
            byte[] abData = new byte[128000];
            while (nBytesRead != -1) {
                try {
                    nBytesRead = audioInputStream.read(abData, 0, abData.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (nBytesRead >= 0) {
                    sourceLine.write(abData, 0, nBytesRead);
                }
            }

            sourceLine.drain();
            sourceLine.close();
        } catch(UnsupportedAudioFileException ex) {
            Logger.getLogger(Sounds.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sounds.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Sounds.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public enum SoundName {
        ONLINE,
        MESSAGE,
        NUDGE,
        LOGON
    }
}
