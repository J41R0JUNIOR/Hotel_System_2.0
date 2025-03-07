import java.util.ArrayList;

public class Group extends Thread {
    public int id;
    public ArrayList<Guest> members;
    public Desirer groupDesirer;
    public int qtdTried;
    public boolean allocated;
    public Hotel hotel;
    private boolean run;

    public Group() {
        this.members = new ArrayList<>();
        this.groupDesirer = Desirer.CHECK_IN;
        this.qtdTried = 0;
        this.run = true;
    }

    public void run() {
        while (run) {
            if (groupDesirer == Desirer.CHECK_IN) {

                if (this.qtdTried >= 2) {
                    System.out.println("Group " + this.id + " is going home after " + this.qtdTried + " tries.\n");

                    hotel.lock.lock();
                    try {
                        hotel.groups.remove(this);
                    } finally {
                        hotel.lock.unlock();
                    }

                    run = false;
                    break;
                }

                hotel.lock.lock();
                try {
                    if (!this.hotel.groups.contains(this)) {
                        this.hotel.groups.add(this);
                    }
                } finally {
                    hotel.lock.unlock();
                }
            }

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
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

        hotel.lock.lock();

        try {
            sleep(1000);
           
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        try {
            hotel.groupsInWaitList.remove(this);
            hotel.groups.add(this);
        } finally {
            hotel.lock.unlock();
        }
    }
}