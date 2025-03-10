import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Hotel {
    public ArrayList<Group> groups;
    public ArrayList<Group> groupsInWaitList;

    public ArrayList<RoomCleaner> roomCleaners;
    public ArrayList<Receptionist> receptionists;
    public ArrayList<Room> rooms;
    public int qtdFreeRooms;

    public Lock lock = new ReentrantLock();

    public Hotel(ArrayList<Group> groups, ArrayList<RoomCleaner> roomCleaners, ArrayList<Receptionist> receptionists, ArrayList<Room> rooms) {
        this.groups = groups;
        this.groupsInWaitList = new ArrayList<>();
        this.roomCleaners = roomCleaners;
        this.receptionists = receptionists;
        this.rooms = rooms;
        this.qtdFreeRooms = rooms.size();

        startAll();
    }

    public void startAll() {
        for (Group g : groups) {
            g.hotel = this;
            g.start();
        }

        for (Receptionist receptionist : receptionists) {
            receptionist.hotel = this;
            receptionist.start();
        }
    }

}
