import java.util.ArrayList;
import java.util.Random;

public class Group extends Thread {
    public int id;
    public ArrayList<Guest> members;
    public Desirer groupDesirer;
    public int qtdTried;
    public Random rand;
    public Hotel hotel;

    public Group() {
        this.members = new ArrayList<>();
        this.groupDesirer = null;
        this.qtdTried = 0;
    }

    public void run() {
        while (true){

            if (groupDesirer == Desirer.NOT_ACCEPTED_GOING_OUT_TRY_AGAIN){

                try {
                    this.rand = new Random();
                    sleep(rand.nextInt(1000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                this.groupDesirer = Desirer.CHECK_IN;
                this.hotel.groups.add(this);
                System.out.println("going back group " + this.id);
            }
        }
    }
}
