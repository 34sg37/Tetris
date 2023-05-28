import java.util.Random;

class Tetris{
    public Tetris(){
        this(10,10); //default constructor to call other constructor to generate 10x10 board
    }

    public Tetris(int h, int w){
        h=h+2;
        w=w+2;
        //dynamically allocate the board and set values, **board is stored in Tetris class private
        board = new char[h][w]; 

        for(int i=0; i<h; i++){
            for(int j=0; j<w; j++){
                if(i==0 || i==h-1) board[i][j]='*';
                else if(j==0 || j==w-1) board[i][j]='*';
                else board[i][j]=' ';
            }
        }
        
        height=h; //height of board, stored in Tetris class private
        width=w; //width of board, stored in Tetris class private
        center=findCenter(); //horizontal center index of board, stored in Tetris class private
    }

    public int findCenter(){
        if (width%2==0) return width/2;
        else return (width+1) /2 -1;
    }

    public void draw(){
        for (int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.print("\n");
        }
    }

    public boolean add(char[][] tetromino){
        ind_x=center-1;
        for(int y=0; y<4; y++){
            for (int x = 0; x <4; x++){
                if(tetromino[y][x]!= ' '){ //do not do the proccess for spaces in tetromino matrix
                    if(board[y+1][ind_x+x] !=' ') return false; //if cant add return false
                    else board[y+1][ind_x+x] = tetromino[y][x];
                }
            }
        }
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
        draw(); //draw the board with tetromino added on top
        try{
            Thread.sleep(50); //sleep 50ms
        } catch (Exception e) {}

        for(int y=0; y<4; y++){
            for (int x = 0; x <4; x++){
                if(tetromino[y][x]!= ' '){ //do not do the proccess for spaces in tetromino matrix
                    board[y+1][ind_x+x] = ' ';
                }
            }
        }
        return true;
    }

    public boolean checkRight(char[][] tetromino){
        for(int y=0; y<4; y++){
            for (int x = 0; x <4; x++){
                if(tetromino[y][x]!= ' '){
                    if(board[ind_y+y+1][ind_x+x+1] != ' ') return false;
                }
            }
        }
        return true;
    }

    public boolean checkLeft(char[][] tetromino){
        for(int y=0; y<4; y++){
            for (int x = 0; x <4; x++){
                if(tetromino[y][x]!= ' '){
                    if(board[ind_y+y+1][ind_x+x+1] != ' ') return false;
                }
            }
        }
        return true;
    }

    public boolean checkBottom(char[][] tetromino){
        for(int y=0; y<4; y++){
            for (int x = 0; x <4; x++){
                if(tetromino[y][x]!= ' '){
                    if(board[ind_y+y+2][ind_x+x+1] != ' ') return false;
                }
            }
        }
        return true;
    }

    public void animate(char[][] tetromino){
        for(int j=0; j<=ind_y; j++){ 
            for(int y=0; y<4; y++){
                for (int x = 0; x <4; x++){
                    if(tetromino[y][x]!= ' '){
                        if(board[j+y+1][ind_x+x+1]== ' ')
                            board[j+y+1][ind_x+x+1] = tetromino[y][x];
                    }
                }
            }
    
            draw();
            try{
                Thread.sleep(50); //sleep 50ms
            } catch (Exception e) {}

            if(j!=ind_y){ //erase the placed tetrominos until tetromino reaches its fitted position
                System.out.print("\033[H\033[2J");  
                System.out.flush(); 
                for(int y=0; y<4; y++){
                    for (int x = 0; x <4; x++){
                        if(tetromino[y][x]!= ' ' )
                            board[j+y+1][ind_x+x+1] = ' ';
                    }
                }
            }
        }
    }
    
    public char roll(){
        Random rand = new Random();
        int roll = rand.nextInt(7)+1;
        
        switch(roll){
            case 1:
                return('I');
            case 2:
                return('O');
            case 3:
                return('T');
            case 4:
                return('J');
            case 5:
                return('L');
            case 6:
                return('S');
            case 7:
                return('Z');
        }
        return('Q'); //if failed return Q and exit program
    }
    
    public boolean move(char[][] tetromino, char dir, int amount){
        ind_x=center-2;
        switch(dir){
            case 'r':
            case 'R':
                for (int i=0;i<amount; i++){
                    ind_x++;
                    if(!checkRight(tetromino)){
                        ind_x--;
                        break;
                    } //if cant add to wanted index go for the closest
                }
                break;
            case 'l':
            case 'L':
                for (int i=0;i<amount; i++){
                    ind_x--;
                    if(!checkLeft(tetromino)){
                        ind_x++;
                        break;
                    } //if cant add to wanted index go for the closest
                }
                break;
        }
        while(checkBottom(tetromino)) ind_y++; //go as deep as possible
        animate(tetromino);
        ind_y=0; //reset y index after the placing proccess is done
        return true;
    }

    
    private char[][] board;
    private int height,width,center,ind_x,ind_y;
}