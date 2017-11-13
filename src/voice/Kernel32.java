/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package voice;


import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.win32.StdCallLibrary;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.AudioPlayer;

public interface Kernel32 extends StdCallLibrary {

    public Kernel32 INSTANCE = (Kernel32) Native.loadLibrary("Kernel32", Kernel32.class);

/**
 *
 * @author Er. Ved Prakash Arya
 */
 public class SYSTEM_POWER_STATUS extends Structure {
        public byte ACLineStatus;
        public byte BatteryFlag;
        public byte BatteryLifePercent;
        public byte Reserved1;
        public int BatteryLifeTime;
        public int BatteryFullLifeTime;
        
        Informer Chetak = new Informer();
        /**
         * The AC power status
         * @return 
         */
        public String getACLineStatusString() {
            switch (ACLineStatus) {
                case (0): return "Offline";
                case (1): return "Online";
                default: return "Unknown";
            }
        }

        /**
         * The battery charge status
         * @return 
         */
        public String getBatteryFlagString() {
            switch (BatteryFlag) {
                case (1): return "66percent";
                case (2): return "33percent";
                case (4): return "5percent";
                case (8): return "Charging";
                case ((byte) 128): return "No system battery";
                default: return "Unknown";
            }
        }

        /**
         * The percentage of full battery charge remaining
         */
        public String getBatteryLifePercent() {
            return (BatteryLifePercent == (byte) 255) ? "Unknown" : BatteryLifePercent + "%";
        }

        /**
         * The number of seconds of battery life remaining
         */
        public String getBatteryLifeTime() {
            return (BatteryLifeTime == -1) ? "Unknown" : BatteryLifeTime + " seconds";
        }

        /**
         * The number of seconds of battery life when at full charge
         */
        public String getBatteryFullLifeTime() {
            return (BatteryFullLifeTime == -1) ? "Unknown" : BatteryFullLifeTime + " seconds";
        }

     
       
       public void checker(){
           if(getACLineStatusString()=="Offline" && Informer.AC==0 && Informer.BT==0){
               Chetak.sound("pcrunbatt");
               Informer.BT=1; Informer.AC=0;  
               if(getBatteryFlagString()=="66percent"){
                   try {
                       AudioPlayer.sleep(1000);
                   } catch (InterruptedException ex) {
                       Logger.getLogger(Kernel32.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   Chetak.sound("morethan66p");
               }
               if(getBatteryFlagString()=="33percent"){
                   try {
                       AudioPlayer.sleep(1000);
                   } catch (InterruptedException ex) {
                       Logger.getLogger(Kernel32.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   Chetak.sound("morethan33p");
               }
               if(getBatteryFlagString()=="5percent"){
                   try {
                       AudioPlayer.sleep(1000);
                   } catch (InterruptedException ex) {
                       Logger.getLogger(Kernel32.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   Chetak.sound("lowbattery");
               }
           }
           if(getACLineStatusString()=="Offline" && Informer.AC==1 && Informer.BT==0){
               Chetak.sound("pluggedout");
               Informer.BT=1; Informer.AC=0;
               if(getBatteryFlagString()=="66percent"){
                   try {
                       AudioPlayer.sleep(1000);
                   } catch (InterruptedException ex) {
                       Logger.getLogger(Kernel32.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   Chetak.sound("morethan66p");
               }
               if(getBatteryFlagString()=="33percent"){
                   try {
                       AudioPlayer.sleep(1000);
                   } catch (InterruptedException ex) {
                       Logger.getLogger(Kernel32.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   Chetak.sound("morethan33p");
               }
               if(getBatteryFlagString()=="5percent"){
                   try {
                       AudioPlayer.sleep(1000);
                   } catch (InterruptedException ex) {
                       Logger.getLogger(Kernel32.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   Chetak.sound("lowbattery");
               }
               
           }
           if(getACLineStatusString()=="Online" && Informer.AC==0 && Informer.BT==0){
               Chetak.sound("pcrunpow");
               Informer.AC=1; Informer.BT=0;
           }
           if(getACLineStatusString()=="Online" && Informer.AC==0 && Informer.BT==1){
               Chetak.sound("pluggedin");
               Informer.AC=1; Informer.BT=0;
           }
           if(getACLineStatusString()=="Unknown"){
               
           }
           //Battery Flag
           
       }
    }

    /**
     * Fill the structure.
     */
    public int GetSystemPowerStatus(SYSTEM_POWER_STATUS result);
}
