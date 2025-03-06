import java.util.Random;

public class Guest extends Thread{
    int id;
    int familyId;

    public Guest(){

    }

    public void run(){

        System.out.println("Guest id" + this.id);
        System.out.println("Guest familyId" + this.familyId);
    }
}
