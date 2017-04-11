/**
 * Created by Andrew on 04/10/17.
 */
public class Main {

    static int commonValue = 0;

    public static void main(String[] args) {
        System.out.println("Threads demo");

        SomethingLikeCamera camera = new SomethingLikeCamera();

        int receivedValue = 0;

        while (true) {

            try {
                receivedValue = camera.getValue();
            } catch (Exception e) {
                //here start reinit process
                //e.printStackTrace();
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
