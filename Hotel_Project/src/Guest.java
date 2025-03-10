import java.util.Random;

public class Guest extends Thread{
    public int id;
    public int familyId;
    public Desirer desirer;
    public Group group;

    public Guest(){
        this.desirer = Desirer.CHECK_IN;
        this.group = null;
    }

    public void run(){
        if(this.group != null && this.group.room != null){
            System.out.println(this.group.room);
        }
    }
}
