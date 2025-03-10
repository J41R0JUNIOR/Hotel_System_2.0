import java.util.Random;

public class Receptionist extends Thread {
    public int id;
    public Hotel hotel;
    public Group group;
    public Random rand;


    public Receptionist(){
        this.group = null;
        this.rand = new Random();

    }

    public void run() {
        while(!this.hotel.groups.isEmpty()){

            findGroups();

            this.group = null;

            try {
                sleep(rand.nextInt(1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void findGroups() {
        this.hotel.lock.lock();

        try {
            if (!this.hotel.groups.isEmpty()) {
                this.group = this.hotel.groups.remove(0);

                System.out.println("Receptionist " + this.id + " found a group " + this.group.id);

                this.group.qtdTried++;

            }
        } finally {
            this.hotel.lock.unlock();
        }

        allocateGroup();
    }

    public void allocateGroup() {
        this.hotel.lock.lock();

        try {
            if (this.hotel.qtdFreeRooms > 0) {
                for (Room room : this.hotel.rooms) {
                    if (room.group != null && room.group.members.isEmpty()) {

                        room.group = this.group;
                        this.group.groupDesirer = Desirer.ALLOCATED;
                        this.group.room = room;

                        System.out.println("Receptionist " + this.id + ", group " + this.group.id + " allocated to room " + room.roomNumber);

                        this.hotel.qtdFreeRooms -= 1;
                        break;
                    }
                }
            }
        } finally {
            this.hotel.lock.unlock();
        }

        if (this.hotel.qtdFreeRooms == 0 && this.group != null) {

            System.out.println("Receptionist " + this.id + " no room found for group " + this.group.id + "\n");

            if (this.group.qtdTried == 1){
                this.group.goOutWait();
            }
        }
    }
}
