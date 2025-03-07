import java.util.Random;

public class Guest extends Thread{
    int id;
    int familyId;
    boolean isRoomLeader = false;
    Desirer desirer = Desirer.NOTHING;
    Hotel hotel;

    public void run(){

//        System.out.println("Guest id" + this.id);
//        System.out.println("Guest familyId" + this.familyId);
        while(true){
            if(isRoomLeader){

                Random rand = new Random();
                try {
                    sleep(rand.nextInt(10000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                int val = rand.nextInt(1);
                switch(val){
                    case 0:
                        desirer = Desirer.CHECK_OUT;
                        break;
                    case 1:
                        desirer = Desirer.GET_OUT;
                        break;
                }

                hotel.guestsWantingSomething.add(this);
            }
            stop();
        }
    }
}
