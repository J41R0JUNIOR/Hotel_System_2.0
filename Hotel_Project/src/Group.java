import java.util.ArrayList;

public class Group extends Thread {
    public int id;
    public ArrayList<Guest> members;
    public Desirer groupDesirer;
    public int qtdTried;
    public boolean allocated;
    public Hotel hotel;
    public boolean runLoop;
    public Room room;

    public Group() {
        this.members = new ArrayList<>();
        this.groupDesirer = Desirer.CHECK_IN;
        this.runLoop = true;
        this.room = null;
    }

    public void run() {
        while (runLoop) {
//            System.out.println("rodando em");
            if (groupDesirer == Desirer.CHECK_IN && room == null) {
                if (this.qtdTried == 2){
                    this.goHome();
                }
            }
            //just to finalize for a while
            else {
                runLoop = false;
                System.out.println("finalizando..." + this.id);
            }
        }
    }

    public void goHome() {
        runLoop = false;
        hotel.lock.lock();

        System.out.println("Group " + this.id + " is going home after " + this.qtdTried + " tries.\n");

        try {
            hotel.groups.remove(this);
        } finally {
            hotel.lock.unlock();
        }
    }

    public void goOutWait() {
        System.out.println("Group " + this.id + " is going out, will try again later!");

        try {
            hotel.lock.lock();
            hotel.groups.remove(this);
            hotel.groupsInWaitList.add(this);
        } finally {
            hotel.lock.unlock();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread interrupted while sleeping: " + e.getMessage());
            return;
        }

        try {
            hotel.lock.lock();
            hotel.groupsInWaitList.remove(this);
            hotel.groups.add(this);
        } finally {
            hotel.lock.unlock();
        }
    }
}