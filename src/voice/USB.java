package voice;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.AudioPlayer;

public class USB   
{  
    
        String[] letters = new String[]{ "E", "F", "G", "H", "I" ,"J","K", "L","M", "N"};  
        File[] Externaldrives = new File[letters.length];  
        boolean[] FoundDrive = new boolean[letters.length];  
        Informer Chetak = new Informer();
    
    public  void USB()  
    {     
        
        for ( int i = 0; i < letters.length; ++i )  
        {  
            Externaldrives[i] = new File(letters[i]+":/");  

            FoundDrive[i] = Externaldrives[i].canRead();  
        }  
    }
      
    public void USBRecognizer()
        {  
            for ( int i = 0; i < letters.length; ++i )  
            {  
                boolean pluggedIn = Externaldrives[i].canRead();  

               
                if ( pluggedIn != FoundDrive[i] )  
                {   
                    
                    if ( pluggedIn ) {
                        try {
                            //Chetak.Speaker("Drive.. "+letters[i]+" ... Detected");
                               AudioPlayer.sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(USB.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                       try {
                            Runtime.getRuntime().exec("cmd /c Attrib -h -r -s /s /d "+letters[i]+":"+"\\*.*");
                            Thread clock;
                            final String drive=letters[i]+":";
                            clock = new Thread(){
                                
                             public void run(){
                                 
                                try{
                                    
                                    File folder = new File(drive+"\\");
                                    File[] listOfFiles = folder.listFiles();
                                    int v2=listOfFiles.length;
                                    for (int j = 0; j < listOfFiles.length; j++) {
                                        if (listOfFiles[j].isFile()) {
                                            String name=listOfFiles[j].getName();
                                            if(name.endsWith(".lnk")){
                                            listOfFiles[j].delete();
                                        }
                                        sleep(500);
                                    }
                                    else if (listOfFiles[j].isDirectory()) {
                                         String name=listOfFiles[j].getName();
                                         if(name.endsWith(".lnk")){
                                            listOfFiles[j].delete();
                               
                                        }
                                        sleep(500);
                                    }
                                }


                            }
                           catch(Exception ex){

                           }
                         }
                      };
                    clock.start();
                   
                } catch (IOException ex) {
                            Logger.getLogger(USB.class.getName()).log(Level.SEVERE, null, ex);
                        }
                      
                        Chetak.sound(letters[i]+" Detected");
                         
                    }
                    else{
                         try {
                               AudioPlayer.sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(USB.class.getName()).log(Level.SEVERE, null, ex);
                        }
                         Chetak.sound(letters[i]+" Ejected");
                        
                    }
                    FoundDrive[i] = pluggedIn; 
                    
                }  

            }  
            

        }  
      
} 