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
//        System.out.println("Receptionist " + this.id + " started");
        while(!this.hotel.groups.isEmpty()){
//            this.hotel.lock.lock();
//            this.hotel.lock.unlock();

            findGroups();

        }
    }

    public void findGroups() {
        if (hotel.groups != null){
            this.hotel.lock.lock();

            this.group = this.hotel.groups.remove(0);
            this.hotel.lock.unlock();

            if (this.group != null){
            System.out.println("Receptionist " + this.id + " found a group " + this.group.id);
                this.group.qtdTried ++;



            try {
                sleep(rand.nextInt(1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            allocateGroup();
            }
        }
    }

    public void allocateGroup() {
//        this.hotel.lock.lock();

        if (this.hotel.qtdFreeRooms > 0){

            for (Room room : this.hotel.rooms){
                if (room.group != null && room.group.members.isEmpty()) {
                    room.group = this.group;
                    this.group.groupDesirer = Desirer.ALLOCATED;

                    System.out.println("Receptionist " + this.id + "group " + this.group.id + " allocated to room " + room.roomNumber);


                    for (Guest guest : room.group.members){
                        guest.insideRoom = true;
                    }

                    this.hotel.qtdFreeRooms -= 1;
                    break;
                }
            }
        } else {
//            System.out.println("Receptionist " + this.id + " no room found for group " + this.group.id);

            if (this.group.qtdTried == 1){
                this.group.goOutWait();
            }
        }

//        this.hotel.lock.unlock();


        this.group = null;

    }
}
