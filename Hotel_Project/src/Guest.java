public class Guest extends Thread{

    public Guest(){
        System.out.println("Guest init");
    }
    public void run(){
        System.out.println("Guest run");
    }
}
