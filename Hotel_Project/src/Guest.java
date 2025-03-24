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

            if (this.group.room != null) {
                int value = random.nextInt(2);
                if (value == 0) {
                    this.desirer = Desirer.CHECK_OUT;
                } else {
                    this.desirer = Desirer.GO_OUT;
                }

                try {
                    Thread.sleep(3000);

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Thread interrupted while sleeping: " + e.getMessage());
                    return;
                }

//            System.out.println(value);
            }
        }
    }
}
