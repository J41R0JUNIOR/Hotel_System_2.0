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

            if (groupDesirer == Desirer.CHECK_IN && room == null) {

                if (this.qtdTried == 2){
                    this.goHome();
                }

//                if (this.qtdTried == 1 && lastQtd != qtdTried){
//                    goOutWait();
//                    lastQtd = qtdTried;
//                }
            }
            //just to finalize for a while
            else {
                runLoop = false;
//                this.interrupt();
        
            }
        }
    }

    public void goHome() {
        System.out.println("Group " + this.id + " is going home after " + this.qtdTried + " tries.\n");

        hotel.lock.lock();
        try {
            hotel.groups.remove(this);
        } finally {
            hotel.lock.unlock();
        }

        runLoop = false;

    }

    public void goOutWait() {
        System.out.println("Group " + this.id + " is going out, is gonna try again later!\n");

        hotel.lock.lock();

        try {
            hotel.groups.remove(this);
            hotel.groupsInWaitList.add(this);
        } finally {
            hotel.lock.unlock();
        }

//        try {
//            sleep(5000);
//
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }

        hotel.lock.lock();

        try {
            hotel.groupsInWaitList.remove(this);
            hotel.groups.add(this);
        } finally {
            hotel.lock.unlock();
        }
    }
}