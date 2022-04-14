package Publisher;

import java.util.TimerTask;
import java.util.Timer;
import java.util.*;
import io.ably.lib.realtime.AblyRealtime;
import io.ably.lib.realtime.Channel;
import io.ably.lib.realtime.CompletionListener;
import io.ably.lib.types.AblyException;
import io.ably.lib.types.ErrorInfo;

public class F1TimerTask extends TimerTask{

    public String NAME = "NotSet"; 
    public String[] MESSAGES = {"NotSet", "StillNotSet" };
    public AblyRealtime ablyRealtime = null;
    public static void main(String args[]) {
        // Args 
        // Name String / Message Array / Hz in ms.

        F1TimerTask myTask = new F1TimerTask();
        Timer myTimer = new Timer();
        myTimer.schedule(myTask, 1000, 5000);
    
        try {
            // Stop the tread from closing
            Thread.sleep(5000);
        } catch (InterruptedException exc) {
        }
    
        myTimer.cancel();
      }

    public void run() {
        System.out.println(NAME + " Sending");
        
        try {
            // Use one connection and mutiple channels
            Channel ablychannel = ablyRealtime.channels.get(NAME);
            ablychannel.publish(NAME, NAME + ":" + MESSAGES[new Random().nextInt(MESSAGES.length)], new CompletionListener() {
                @Override
                public void onSuccess() {
                    // Show success message when message is sent 
                    System.out.println(NAME + " Message sent");
                }
    
                @Override
                public void onError(ErrorInfo reason) {
                    // Show error message when something goes wrong 
                    System.out.println(NAME + " Message not sent, error occurred: " + reason.message);
                }
            });  
        }
        catch (AblyException e) {
            //e.printStackTrace();
            System.out.println(NAME + " Excepton");
        } 

        
    }
    
    public void onSuccess() {
        // Show success message when message is sent 
        System.out.println(NAME + " Message sent");
    }

    
    public void onError(ErrorInfo reason) {
        // Show error message when something goes wrong 
        System.out.println(NAME + " Message not sent, error occurred: " + reason.message);
    }

    
}

