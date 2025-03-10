import java.util.Random;

public class Guest extends Thread{
    public int id;
    public int familyId;
    public Desirer desirer;
    public Group group;
    private final Random random;

    public Guest(){
        this.desirer = Desirer.CHECK_IN;
        this.group = null;
        this.random = new Random();
    }

    public void run(){
        while((this.group != null) && (this.group.groupDesirer != Desirer.FINALIZED)){
//            System.out.println(this.group.room);

            int value = random.nextInt(2);
//            System.out.println(value);


        }
    }
}
