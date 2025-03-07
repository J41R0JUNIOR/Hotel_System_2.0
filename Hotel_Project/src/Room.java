import java.util.ArrayList;

public class Room {
    public int roomNumber;
    public Group group;
    public boolean isClean;

    public Room() {
        this.group = new Group();
        this.isClean = false;
    }
}
