import java.util.Scanner;
import java.util.Random;

class driver2{
    public static void main(String[] args){
        Scanner input= new Scanner(System.in);
        int width,height,count;
        char dir,ch;
        System.out.println("Enter the size of board (height,width)");
        height = input.nextInt();
        width = input.nextInt();
        Tetris playground = new Tetris(height,width);
        while (true){
            System.out.println("Enter the tetromino type(I,O,T,J,L,S,Z) or Operation(R: for random, Q: to quit)");
            do{
                ch = input.next().trim().charAt(0);
                if (ch!='O'& ch!='J' & ch!='T' & ch!='I' & ch!='L' & ch!='Z' & ch!='S'& ch!='R'& ch!='Q'){
                    System.out.println("You can only enter the following inputs (CAPITALIZED ONLY) : I,O,T,J,L,S,Z,R,Q");
                }
                else break;
            }while (true);
            if (ch=='Q') System.exit(0);
            else if (ch=='R'){
                ch=playground.roll();
            }
            if (ch=='Q') System.exit(1);
            Tetromino t = new Tetromino(ch);

            if(!(playground.add(t.getVec()))) break;

            Random rand = new Random();

            int num= rand.nextInt(2)+1;
            if (num==1) dir='r';
            else dir ='l';

            count = rand.nextInt(5);
            t.rotate(dir,count);
            
            num= rand.nextInt(2)+1;
            if (num==1) dir='r';
            else dir ='l';

            count = rand.nextInt(width/2+1);

            playground.move(t.getVec(),dir,count);
        }
        System.out.print("\n----------------------------------------------\n******************GAME  OVER******************\n----------------------------------------------");
        input.close();
    }
}