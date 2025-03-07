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

            try {
                sleep(rand.nextInt(2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void allocateGroup() {
        this.hotel.lock.lock();

        if (this.hotel.qtdFreeRooms > 0){
            this.hotel.qtdFreeRooms -= 1;

            for (Room room : this.hotel.rooms){
                if (room.group != null) {
                    if (room.group.members.isEmpty()) {

                        System.out.println("achou em");
                    }
                }
            }


        } else {
            System.out.println("sem quartos baby");
            this.hotel.groups.add(this.group);
        }
        System.out.println("qtd disponiveis" + this.hotel.qtdFreeRooms);

        this.hotel.lock.unlock();
    }
}
