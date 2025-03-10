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

        startMembers();

        while (runLoop) {
            groupDesirerCont();

            if (groupDesirer == Desirer.CHECK_IN && room == null) {
                if (this.qtdTried == 2){
                    this.goHome();
                }
            }

            if (groupDesirer == Desirer.GET_OUT) {
                System.out.println("Group" + this.id + "We are moving to reception, we want to take a little ride");

                this.hotel.lock.lock();
                try {
                    this.room.group = null;
                } finally {
                    this.hotel.lock.unlock();
                }
            }

            //just to finalize for a while

            else {
                finalizeGroup();
            }
        }
    }

    private void groupDesirerCont() {
        int getOut = 0;
        int checkOut = 0;

        for (Guest g: this.members){
            switch (g.desirer){
                case CHECK_OUT -> {
                    checkOut++;
                }
                case GET_OUT -> {
                    getOut++;
                }
            }
        }

        if (checkOut > getOut){
            this.groupDesirer = Desirer.CHECK_OUT;
        }else {
            this.groupDesirer = Desirer.GET_OUT;
        }
    }

    private void finalizeGroup() {
        this.runLoop = false;
        this.room = null;
        this.groupDesirer = Desirer.FINALIZED;

        System.out.println("\n" + "Group " + this.id + " Finalizing..." + "\n");
    }


    private void startMembers(){
        for (Guest g: this.members){
            g.group = this;
            g.start();
        }
    }

    public void goHome() {
        runLoop = false;
        hotel.lock.lock();

        System.out.println("Group " + this.id + ":  -Going home after " + this.qtdTried + " tries.");

        try {
            hotel.groups.remove(this);
        } finally {
            hotel.lock.unlock();
        }

        finalizeGroup();
    }

    public void goOutWait() {
        System.out.println("Group " + this.id + ": - Going out, we will try again later!");

        try {
            hotel.lock.lock();
            hotel.groups.remove(this);
            hotel.groupsInWaitList.add(this);
        } finally {
            hotel.lock.unlock();
        }

        try {
            Thread.sleep(3000);
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