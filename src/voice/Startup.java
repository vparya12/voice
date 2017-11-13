/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package voice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


/**
 *
 * @author Er. Ved Prakash Arya
 */
public class Startup {
    public static String loginName = null; 
    
    public static void copyPaste(){
        com.sun.security.auth.module.NTSystem NTSystem = new
        com.sun.security.auth.module.NTSystem();
        loginName=NTSystem.getName();
        String s= "C:\\Users\\Public\\Desktop\\Voice.lnk";
        String d= "C:\\Users\\"+loginName+"\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\Voice.lnk";
        File f1 = new File(s);
        File f2 = new File(d);
        try{
                try (
                    InputStream in = new FileInputStream(s);
                    OutputStream out = new FileOutputStream(d)
            ) {
                byte[] buf = new byte[1024];
                int length;
                while ((length = in.read(buf)) > 0) {
                    out.write(buf, 0, length);
                }
            }
                }catch(Exception e){
                    
        }        
    }
  
}
