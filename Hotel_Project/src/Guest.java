import java.util.Random;

public class Guest extends Thread{
    public int id;
    public int familyId;
    public Desirer desirer;
    public boolean insideRoom;

    public Guest(){
        this.desirer = Desirer.CHECK_IN;
        this.insideRoom = false;
    }

    public void run(){

    }
}
