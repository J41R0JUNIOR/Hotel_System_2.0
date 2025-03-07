import java.util.ArrayList;

public class Group extends Thread {
    public int id;
    public ArrayList<Guest> members;
    public Desirer groupDesirer;

    public Group() {
        members = new ArrayList<>();
        groupDesirer = null;
    }

    public void start() {
        while (true){
            int check_out = 0;
            int check_in = 0;
            int get_out = 0;


            for (Guest g : members) {
                switch (g.desirer){
                    case CHECK_IN:
                        check_in ++;
                    case CHECK_OUT:
                        check_out ++;
                    case GET_OUT:
                        get_out ++;
                }
            }

            if (check_in > check_out && check_in > get_out) {
                this.groupDesirer = Desirer.CHECK_IN;

            } else if (get_out > check_in && get_out > check_out) {
                this.groupDesirer = Desirer.GET_OUT;

            } else {
                this.groupDesirer = Desirer.CHECK_OUT;

            }
        }
    }
}
