import java.util.ArrayList;
import java.util.Random;

public class Receptionist extends Thread {
    public int id;
    public Hotel hotel;
    public ArrayList<Guest> guests;
    public Random rand;


    public Receptionist(){
        this.guests = new ArrayList<>();
        this.rand = new Random();

    }

    public void run() {
        System.out.println("Receptionist started");
        while(true){
//            this.hotel.lock.lock();

//            this.hotel.lock.unlock();
        }
    }


}
