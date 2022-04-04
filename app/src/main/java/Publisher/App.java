/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Publisher;

import java.util.*;
import io.ably.lib.realtime.AblyRealtime;
import io.ably.lib.realtime.Channel;
import io.ably.lib.realtime.CompletionListener;
import io.ably.lib.types.AblyException;
import io.ably.lib.types.ErrorInfo;
import io.ably.lib.types.Message;

public class App {
   
    private final static String API_KEY = "F_Nmww.L3IGVw:0PQ51gLrUCh7rhneXSCxWiE-TY5Z0EVQ6HT_AICaKAw"; /* Sign up at ably.io to get your API key */
    /* RuntimeException will be thrown if API_KEY will not be set to a proper one */
    static {
        if (API_KEY.contains("INSERT")) {
            throw new RuntimeException("API key is not set, sign up at ably.io to get yours");
        }
    }
    // Create a MPH String Array
    private static String[] MPH_ARRAY = {"180", "13", "55", "100", "160" };

    // Create a EngineTemp Array
    private static final String[] ENGINE_ARRAY = {"435", "234", "300", "342", "354"};

     // Create a GeForce Array
     private static final String[] GEFORCE_ARRAY = {"2.5", "1.0", "2.4", "-1.2", "3.23"};

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        try {
			initAbly();
		} catch (AblyException e) {
			//e.printStackTrace();
		}
    }

    public String getGreeting() {
        return "Starting Publisher...";
    }

    /* Add AblyException to method signature as AblyRealtime constructor can throw one */
	private static void initAbly() throws AblyException{
		AblyRealtime ablyRealtime = new AblyRealtime(API_KEY); 
       
        // Start of Publisher
        Channel channel = ablyRealtime.channels.get("START");     
        Date now = new Date();
        System.out.println("Publishing: " + now);
        channel.publish("update", "now: " + now, new CompletionListener() {
            @Override
            public void onSuccess() {
                /* Show success message when message is sent */
                System.out.println("Message sent: " + now);
            }

            @Override
            public void onError(ErrorInfo reason) {
                /* Show error message when something goes wrong */
            	System.out.println("Message not sent, error occurred: " + reason.message);
            }
        });

        // Start a threads

        Channel MPHchannel = ablyRealtime.channels.get("MPH");
        Channel ENGINEchannel = ablyRealtime.channels.get("ENGINE");
        Channel GEFORCEchannel = ablyRealtime.channels.get("MPH");

        // MPH_ARRAY
        Timer t1 = new Timer( );
        t1.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                System.out.println("T1 Sending");
                try {
                    MPHchannel.publish("update", "T1:" + MPH_ARRAY[new Random().nextInt(MPH_ARRAY.length)], new CompletionListener() {
                        @Override
                        public void onSuccess() {
                            // Show success message when message is sent 
                            System.out.println("T1 Message sent");
                        }
            
                        @Override
                        public void onError(ErrorInfo reason) {
                            // Show error message when something goes wrong 
                            System.out.println("T1 Message not sent, error occurred: " + reason.message);
                        }
                    }); 
                }
                catch (AblyException e) {
                    //e.printStackTrace();
                    System.out.println("T1 Excepton");
                } 
                
            }
        }, 1000,1000); 
  
        // ENGINE_ARRAY
        Timer t2 = new Timer( );
        t2.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                System.out.println("T2 Sending");
                try {
                    ENGINEchannel.publish("update", "T2:" + ENGINE_ARRAY[new Random().nextInt(ENGINE_ARRAY.length)] , new CompletionListener() {
                        @Override
                        public void onSuccess() {
                            // Show success message when message is sent 
                            System.out.println("T2 Message sent");
                        }
            
                        @Override
                        public void onError(ErrorInfo reason) {
                            // Show error message when something goes wrong 
                            System.out.println("T2 Message not sent, error occurred: " + reason.message);
                        }
                    }); 
                }
                catch (AblyException e) {
                    //e.printStackTrace();
                    System.out.println("T2 Excepton");
                } 
            }
        }, 1000,2000);

        // GEFORCE_ARRAY
        Timer t3 = new Timer( );
        t3.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                System.out.println("T3 Sending");
                try {
                    GEFORCEchannel.publish("update", "T3:" + GEFORCE_ARRAY[new Random().nextInt(GEFORCE_ARRAY.length)] , new CompletionListener() {
                        @Override
                        public void onSuccess() {
                            // Show success message when message is sent 
                            System.out.println("T3 Message sent");
                        }
            
                        @Override
                        public void onError(ErrorInfo reason) {
                            // Show error message when something goes wrong 
                            System.out.println("T3 Message not sent, error occurred: " + reason.message);
                        }
                    }); 
                }
                catch (AblyException e) {
                    //e.printStackTrace();
                    System.out.println("T3 Excepton");
                } 
            }
        }, 1000,3000);
         
        // Prevent the app from quiting
        for (int i = 0; i < 1; ) {
            System.out.println("Main App Sleeping.." + i);
            try {
             Thread.sleep(10000);
            } catch (InterruptedException e) {
             System.out.println("Thread has been interrupted");
            }
        }
         
	}

    
}