import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException {

//      Room
        ArrayList<Room> rooms = generateObject(10, Room.class);
        
//      Threads
        ArrayList<Guest> guests = generateObject(10, Guest.class);
        ArrayList<Receptionist> receptionists = generateObject(10, Receptionist.class);
        ArrayList<RoomCleaner> roomCleaners = generateObject(10, RoomCleaner.class);


    }

    public static <T> ArrayList<T> generateObject(int qtd, Class<T> clazz) throws NoSuchMethodException {
        ArrayList<T> objects = new ArrayList<>();

        for (int i = 0; i < qtd; i++) {
            T newObj = null;
            try {
                newObj = clazz.getDeclaredConstructor().newInstance();

                if (newObj instanceof RoomCleaner) {}
                else if (newObj instanceof Receptionist) {}
                else if (newObj instanceof Guest) {}
                else if (newObj instanceof Room) {}

            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            objects.add(newObj);
        }

        return objects;
    }
}

