import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;



public class Main {
    public static void main(String[] args) throws NoSuchMethodException {

//      Room
        ArrayList<Room> rooms = generateObject(10, Room.class);

//      Threads
        ArrayList<Group> families = generateGroups();
        ArrayList<RoomCleaner> roomCleaners = generateObject(10, RoomCleaner.class);
        ArrayList<Receptionist> receptionists = generateObject(5, Receptionist.class);

        Hotel hotel = new Hotel(families, roomCleaners, receptionists, rooms);
    }

    public static <T> ArrayList<T> generateObject(int qtd, Class<T> clazz) throws NoSuchMethodException {
        ArrayList<T> objects = new ArrayList<>();

        for (int i = 1; i <= qtd; i++) {
            T newObj = null;
            try {
                newObj = clazz.getDeclaredConstructor().newInstance();

                if (newObj instanceof RoomCleaner) {}
                else if (newObj instanceof Receptionist) {
                    ((Receptionist) newObj).id = i;
                }
                else if (newObj instanceof Guest) {
                    ((Guest) newObj).id = i;
                    ((Guest) newObj).familyId = new Random().nextInt(qtd - 30);
                }
                else if (newObj instanceof Room) {
                    ((Room) newObj).roomNumber = i;
                }
                else if (newObj instanceof Group) {
                    ((Group) newObj).id = i;
                }

            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            objects.add(newObj);
        }

        return objects;
    }

    public static ArrayList<Group> generateGroups() throws NoSuchMethodException {
        Group group = new Group();
        group.id = 0;

        ArrayList<Group> groups = generateObject(50, Group.class);
        groups.add(group);

        ArrayList<Guest> guests = generateObject(50, Guest.class);

        for (Guest g : guests) {

           for (Group gp : groups) {
               if (g.familyId == gp.id) {
                   gp.members.add(g);
               }
           }
        }

        groups.removeIf(gp -> gp.members.isEmpty());

        for (Group g : groups) {

            System.out.println("Group: " + g.id);
            System.out.println(g.members.size());

            if (g.members.size() > 4){
                float multiplier = (float) (g.members.size() / 4.0);
                int qtdNewGroups = (g.members.size() / 4);
                
                System.out.println("qtd new groups needed " + qtdNewGroups);
            }

            System.out.println("\n");

        }
        return groups;
    }
}

