import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;



public class Main {
    public static void main(String[] args) throws NoSuchMethodException {

//      Room
        ArrayList<Room> rooms = generateObject(10, Room.class);

//      Threads
        ArrayList<Guest> guests = generateObject(50, Guest.class);
        ArrayList<RoomCleaner> roomCleaners = generateObject(10, RoomCleaner.class);
        ArrayList<Receptionist> receptionists = generateObject(5, Receptionist.class);

        Hotel hotel = new Hotel(guests, roomCleaners, receptionists, rooms);
    }

    public static <T> ArrayList<T> generateObject(int qtd, Class<T> clazz) throws NoSuchMethodException {
        ArrayList<T> objects = new ArrayList<>();

        for (int i = 0; i < qtd; i++) {
            T newObj = null;
            try {
                newObj = clazz.getDeclaredConstructor().newInstance();

                if (newObj instanceof RoomCleaner) {}
                else if (newObj instanceof Receptionist) {
                    ((Receptionist) newObj).id = i;
                }

                else if (newObj instanceof Guest) {
                    ((Guest) newObj).id = i;
                    ((Guest) newObj).familyId = new Random().nextInt(qtd);
                }
                else if (newObj instanceof Room) {
                    ((Room) newObj).roomNumber = i;
                }

            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            objects.add(newObj);
        }

        return objects;
    }
}

