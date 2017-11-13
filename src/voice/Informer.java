/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package voice;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


/**
 *
 * @author Er. Ved Prakash Arya
 */
    
public class Informer {
    
        static double index2;
        static int index=0; 
        public static int AC=0,BT=0;
        static String quote[] ={"welcome","Ready","Pleasure","Start","welcome","Ready","Pleasure","Start","welcome","Ready"};
       
        public static void main(String args[]){
            
            Informer Chetak = new Informer();
            index2 = Math.random();
            index = (int)(index2*9);
            Chetak.sound(quote[index]);      
            Startup.copyPaste();
            USB vl = new USB();
            vl.USB();
            try{
                while(true){    
                         vl.USBRecognizer();
                         AudioPlayer.sleep(1200);
                         Kernel32.SYSTEM_POWER_STATUS batteryStatus = new Kernel32.SYSTEM_POWER_STATUS();
                         Kernel32.INSTANCE.GetSystemPowerStatus(batteryStatus);
                         batteryStatus.checker();
                }
            }
            catch(Exception e){
            }
        }
    
        public void sound(String voiceName){
            
                String songfile= "C:\\Program Files (x86)\\Voice\\Voice\\"+voiceName+".wav";
                 
                    try {
                        
                        InputStream in = new FileInputStream(songfile);
                       AudioStream as = new AudioStream(in);
                       AudioPlayer.player.start(as);
                       AudioPlayer.sleep(600);
                    } catch (IOException | InterruptedException ex) {

                    }
             
     
        }
	
}

