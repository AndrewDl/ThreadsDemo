import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Andrew on 04/10/17.
 */
public class Main {

    static int commonValue = 0;

    static Timer reconnectTimer = null;

    static boolean connected = true;

    static Object syncObject = new Object();

    static synchronized boolean getConnected(){
        return connected;
    }

    static synchronized void setConnected(boolean state){
        connected = state;
    }

    public static void main(String[] args) {

        reconnectTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setConnected(true);
                reconnectTimer.stop();
            }
        });

        System.out.println("Threads demo");

        SomethingLikeCamera camera = new SomethingLikeCamera();

        int receivedValue = 0;

        while (true) {

            while (getConnected()) {
                try {
                    receivedValue = camera.getValue();
                } catch (Exception e) {
                    //here start reinit process
                    e.printStackTrace();
                    setConnected(false);
                    reconnectTimer.start();
                    break;
                }

                System.out.println(receivedValue);

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
