import java.util.ArrayList;

public class Group extends Thread {
    public int id;
    public ArrayList<Guest> members;
    public Desirer groupDesirer;

    public Group() {
        members = new ArrayList<>();
        groupDesirer = null;

    }

    public void start() {
        while (true){

        }
    }
}
