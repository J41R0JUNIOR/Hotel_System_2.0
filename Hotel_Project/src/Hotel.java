import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Hotel {
    ArrayList<Guest> guests;
    ArrayList<Guest> guestsWantingSomething;
    ArrayList<RoomCleaner> roomCleaners;
    ArrayList<Receptionist> receptionists;

    ArrayList<Room> freeRooms;
    ArrayList<Room> fullRooms;
    ArrayList<Room> momentFreeRooms;

    public Lock lock = new ReentrantLock();

    public Hotel(ArrayList<Guest> guests, ArrayList<RoomCleaner> roomCleaners, ArrayList<Receptionist> receptionists, ArrayList<Room> rooms) {
        this.guests = guests;
        this.guestsWantingSomething = guests;

        this.roomCleaners = roomCleaners;
        this.receptionists = receptionists;

        this.freeRooms = rooms;
        this.fullRooms = new ArrayList<>();

        this.momentFreeRooms = new ArrayList<>();

        startAll();
    }

    public void startAll(){
//        for (Guest guest : guests) {
//            guest.start();
//        }

        for (Receptionist receptionist : receptionists) {
            receptionist.hotel = this;
            receptionist.start();
        }

        for (RoomCleaner roomCleaner : roomCleaners) {
            roomCleaner.start();
        }
    }
}
