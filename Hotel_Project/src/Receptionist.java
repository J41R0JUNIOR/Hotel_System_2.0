import java.util.ArrayList;
import java.util.Random;

public class Receptionist extends Thread {
    int id;
    Hotel hotel;
    ArrayList<Guest> guests;


    public Receptionist(){
        guests = new ArrayList<>();

    }

    public void run(){
        while(hotel.guests.isEmpty() == false){
            hotel.lock.lock();

            System.out.println("reeptionist: " + this.id);

            Random rand = new Random();
            if (rand.nextBoolean() == true){
//              attend some guests
                tryGettingGuests();
                tryFindingRoom();

            } else{
//                guest at waitList waiting to get the keys, give the keys or getting out
            }

            hotel.lock.unlock();

            rand = new Random();
            try {
                sleep(rand.nextInt(5000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void tryGettingGuests(){
        if(hotel.guests.isEmpty() == false) {
            this.guests.add(hotel.guests.get(0));
            hotel.guests.remove(0);

            for (Guest guest : hotel.guests) {

                if (guest.id == this.guests.indexOf(0) && guests.size() <= 4) {
                    this.guests.add(guest);
                    hotel.guests.remove(guest);
                }
            }
        }
    }

    public void tryFindingRoom(){
        if(hotel.freeRooms.isEmpty() == true){
            hotel.guests.addAll(guests);
            System.out.println("no room found");
            return;
        }

       Room room = hotel.freeRooms.get(0);
        hotel.fullRooms.add(room);
        hotel.freeRooms.remove(room);

        linkGuestToRoom(room);
    }

    public void linkGuestToRoom(Room room){
        Guest linkGuest = this.guests.get(0);
        linkGuest.isRoomLeader = true;
        linkGuest.hotel = hotel;
        linkGuest.run();


        this.guests = new ArrayList<>();
    }
}
