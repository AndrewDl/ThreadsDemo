/**
 * Created by Andrew on 04/11/17.
 */
public class SomethingLikeCamera {
    Thread T = null;

    private int value=0;
    private boolean connectionLost = false;

    private void initThread(){
        T = new Thread(new fetchInteger());
        System.out.println("Start Thread");
    }

    public int getValue() throws Exception{

        if ((T == null)) {
            initThread();
            connectionLost = false;
        }

        if (!T.isAlive())
            try {
                T.start();
            } catch (IllegalThreadStateException ex){
                connectionLost = false;
                T = null;
                value = 0;
                throw new Exception("Oops...");
            }
        return value;

    }

    private class fetchInteger implements Runnable{

        @Override
        public void run() {
            while (true) {
                value = (value < 2000) ? value + 1 : 0;

                //типу обірвало поток
                if (value == 30) {
                    connectionLost = true;
                    break;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
