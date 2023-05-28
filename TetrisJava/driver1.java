import java.util.Random;
import java.util.Scanner;

class driver1{
    public static void main(String[] args){
        int test=1;
        char dir;
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        int num= rand.nextInt(2)+1;
        if (num==1) dir='r';
        else dir ='l';
        int count = rand.nextInt(6);

        System.out.println("Generate Tetris object with default constructor (10x10) and draw it. (PRESS ENTER)");
        scan.nextLine();
        Tetris playground = new Tetris();
        playground.draw();

        System.out.println("\nGenerate a random tetromino and add it to top. (PRESS ENTER)");
        scan.nextLine();
        Tetromino t = new Tetromino(playground.roll());
        playground.add(t.getVec());

        System.out.println("Move and animate it to a random place. (PRESS ENTER)");
        scan.nextLine();
        playground.move(t.getVec(),dir,count);

        System.out.println("\nRotate a tetromino with same shape and add it to top. (PRESS ENTER) (Note: Rotated version can be same with old version.)");
        scan.nextLine();
        t.rotate(dir, count);
        playground.add(t.getVec());

        System.out.println("PRESS ENTER TO START SIMULATION");
        scan.nextLine();
        
        for(;test<6;test++){
            playground = new Tetris();
            for(int i=0;i<100;i++){
                t = new Tetromino(playground.roll());
                if(!(playground.add(t.getVec()))){
                    if(test!=5)System.out.println("Add function couldn't add more tetrominos. Game over for Test "+test+", press ENTER to continue with Test "+(test+1));
                    else System.out.println("Game over for Test 5.\nSimulation ended successfully! Press ENTER to quit.");
                    scan.nextLine();
                    break;
                }
                num= rand.nextInt(2)+1;
                if (num==1) dir='r';
                else dir ='l';
                count = rand.nextInt(6);
                t.rotate(dir,count);
                playground.move(t.getVec(),dir,count);
            }
        }
    }

}