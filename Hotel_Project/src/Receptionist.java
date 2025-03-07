import java.util.ArrayList;
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
        System.out.println("Receptionist " + this.id + " started");
        while(!this.hotel.groups.isEmpty()){
//            this.hotel.lock.lock();
//            this.hotel.lock.unlock();

            findGroups();
            
        }
    }

    public void findGroups() {
        if (!hotel.groups.isEmpty()){
            this.hotel.lock.lock();

            this.group = hotel.groups.remove(0);

            this.hotel.lock.unlock();

            System.out.println("Receptionist " + this.id + " found a group " + this.group.id);

            allocateGroup();
        }
    }

    public void allocateGroup() {
        if (this.hotel.qtdFreeRooms > 0){
            this.hotel.lock.lock();

            for (Room room : this.hotel.rooms){
                if (room.group != null) {
                    if (room.group.members.isEmpty()) {

                    }
                }
            }

            this.hotel.lock.lock();
        }
    }
}
