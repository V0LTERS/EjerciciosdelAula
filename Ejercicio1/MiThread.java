import java.util.Random;

public class MiThread extends Thread{
    private String message;
    
    public MiThread(String threadName){
        this.message = "Hola mundo desde" + threadName;
    }
    public void run(){
        Random r = new Random();
        System.out.println(message);
        int timeToSleep = r.nextInt(1000);
        try{
            this.sleep(timeToSleep);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    {
        System.out.println(message);
    }
    public void sleep(int millis)throws InterruptedException{
        Thread.sleep(millis);
    }
    public static void main(String args[]){
        MiThread t1 = new MiThread( "t1");
        MiThread t2 = new MiThread( "t2");
        MiThread[] t = new MiThread[10];
        for (int i = 0; i<t.length; i++){
            t[i]=new MiThread("t" + i);
            t[i].start();
        }

        t1.start();
        try{
            t1.sleep(500);
        }catch(InterruptedException iex){
            iex.printStackTrace();
        }
        t2.start();

    }
}
