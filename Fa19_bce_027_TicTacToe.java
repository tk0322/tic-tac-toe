/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Fa19_bce_027_TicTacToe {
     public static final Random RANDOM=new Random();

    public static void main(String[] args) {

     Board b= new Board();
   
     Scanner sc=new Scanner(System.in);
     b.displayBoard();
        System.out.println("Choose turn: 1 or 2\n1.  COMPUTER(X)\n2.USER(0)\n=");
     int choice=sc.nextInt();
     
        if (choice==Board.PLAYER_X) {
            Point p=new Point(RANDOM.nextInt(3),RANDOM.nextInt(3));
            b.placeMove(p, Board.PLAYER_X);
            b.displayBoard();
        }
        
        while(!b.isGameOver()){
            boolean moveOk=true;
            do{
                if(!moveOk){
                    System.out.println("Cell already filled !");
                }
                System.out.println("Your move(enter row and column simultaneously) : ");
                System.out.println("//         Helpful indices\n" +
"         [0][0] , [0][1] , [0][2]\n" +
"         [1][0] , [1][1] , [1][2]\n" +
"         [2][0] , [2][1] , [2][2]\n" +
"        ");
                Point userMove= new Point(sc.nextInt(),sc.nextInt());
                moveOk=b.placeMove(userMove, Board.PLAYER_0);
            }while(!moveOk);
        
        b.displayBoard();
        if(b.isGameOver())
            break;

    Point p=new Point( RANDOM.nextInt(3),RANDOM.nextInt(3));
        System.out.println("Computer choose position: ");


        b.placeMove( p,Board.PLAYER_X);


        b.displayBoard();

      
    }
        if(b.hasPlayerWon(Board.PLAYER_X))
            System.out.println("You lost");
        else if(b.hasPlayerWon(Board.PLAYER_0)){
            System.out.println("You win");
           System.out.println("You have moved to the next level)");
           Minimax();
        }
        
        
        else 
            System.out.println("Draw");
        

    
}
static void Minimax(){
   
    Board b1= new Board();
   
     Scanner sc=new Scanner(System.in);
     b1.displayBoard();
        System.out.println("Choose turn: 1 or 2\n1.  COMPUTER(X)\n2.USER(0)\n=");
     int choice=sc.nextInt();
     
        if (choice==Board.PLAYER_X) {
            Point p=new Point(RANDOM.nextInt(3),RANDOM.nextInt(3));
            b1.placeMove(p, Board.PLAYER_X);
            b1.displayBoard();
        }
        
        while(!b1.isGameOver()){
            boolean moveOk=true;
            do{   
                if(!moveOk){
                    System.out.println("Cell already filled !");
                }
                System.out.println("Your move(enter row and column simultaneously) : ");
                Point userMove= new Point(sc.nextInt(),sc.nextInt());
                moveOk=b1.placeMove(userMove, Board.PLAYER_0);
            }while(!moveOk);
        
        b1.displayBoard();
        if(b1.isGameOver())
            break;
   

   

              b1.callMinimax(0, Board.PLAYER_X);
//            b1.minimax(0, Board.PLAYER_X);
//       for (PointAndScore pas : b1.rootsChildrenScores) {
//                System.out.println("Point: " + pas.point + " Score: " + pas.score);
//            }
             System.out.println("Computer choose position: ");
        b1.placeMove(b1.returnBestMove(), Board.PLAYER_X);
//        b1.placeMove(b1.computerMove, Board.PLAYER_X);
         b1.displayBoard();
     
   
    }


        if(b1.hasPlayerWon(Board.PLAYER_X))
            System.out.println("You lost");
        else if(b1.hasPlayerWon(Board.PLAYER_0)){
            System.out.println("You win");
           System.out.println("You have moved to the next level 3");}
        
        
        else 
            System.out.println("Draw");
        
}  }

class Point{
    public int x,y;
  

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString() {
        return "["+x+","+y+"]" ; 
    } 
}

class PointAndScore{
    public int score;
     /* Object creation */
    public Point point;


    public PointAndScore(int score, Point point) {
        this.score = score;
        this.point = point;
    }
    

}


class Board {
    public static final int  NO_PLAYER=0;
    public  static final int  PLAYER_X=1;//to call in main we have to put static
    public static final int   PLAYER_0=2;
    
    
 private  int[][] board=new int[3][3];
    
    public  Point computerMove;

    //game will be over if player has won or the board is full
    public boolean isGameOver(){
        return hasPlayerWon(PLAYER_X) || hasPlayerWon(PLAYER_0) || 
                getAvailableCells().isEmpty();
    }
  
    public boolean hasPlayerWon(int player){
     
   
        if((board[0][0]==board[1][1] && board[0][0]==board[2][2]&& board[0][0]==player)||
      
           (board[0][2]==board[1][1] && board[0][2]==board[2][0]&& board[0][2]==player)){
            return true;
        }
   
        for (int i = 0; i < board.length; i++) {
           
            if((board[i][0]==board[i][1] && board[i][0]==board[i][2]&& board[i][0]==player)||
                
           (board[0][i]==board[1][i] && board[0][i]==board[2][i]&& board[0][i]==player)){
            return true;
        }  }
        return false;
   } 
    
    
    public List<Point> getAvailableCells(){
    
        List<Point> availableCells=new ArrayList();
     
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if(board[i][j]==NO_PLAYER){
               
                    availableCells.add(new Point(i,j));//adds in arraylist
                }
            }
  
        }
        return availableCells ;
    }

    public boolean placeMove(Point point,int player){
     
        if(board[point.x][point.y]!=NO_PLAYER){
            return false;
        }
        
            board[point.x][point.y]=player;
            return true;
    }
    public void displayBoard(){
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
               String value ="-";
               
               if(board[i][j]==PLAYER_X){
                   value="X";
               }
               else if(board[i][j]==PLAYER_0) 
                   value="0";
                   System.out.print(value+"");
               
            }
            System.out.println();
            
        }
        System.out.println();
    }

        

    
    
 
  public int returnMin(List<Integer> list) {
        int min = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) < min) {
                min = list.get(i);
                index = i;
            }
        }
        return list.get(index);
    }

    public int returnMax(List<Integer> list) {
        int max = Integer.MIN_VALUE;
        int index = -1;
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) > max) {
                max = list.get(i);
                index = i;
            }
        }
        return list.get(index);
    }

    List<PointAndScore> rootsChildrenScores;
 
    public void callMinimax(int depth, int turn){
        rootsChildrenScores = new ArrayList<>();
        minimax(depth, turn);
    }
    
    public int minimax(int depth, int turn) {

      if(hasPlayerWon(PLAYER_X))
            return 1;
         if(hasPlayerWon(PLAYER_0))
             return -1;

        List<Point> pointsAvailable = getAvailableCells();
        if (pointsAvailable.isEmpty()) return 0; 

        List<Integer> scores = new ArrayList<>(); 

        for (int i = 0; i < pointsAvailable.size(); ++i) {
            Point point = pointsAvailable.get(i);  

            if (turn == PLAYER_X) { 
                placeMove(point, PLAYER_X); 
                int currentScore = minimax(depth + 1, PLAYER_0);
                scores.add(currentScore);

                if (depth == 0) 
                    rootsChildrenScores.add(new PointAndScore(currentScore, point));
                // System.out.println("Computer score for position"+point+"="+currentScore);          
            } else if (turn == PLAYER_0) {
                placeMove(point,PLAYER_0); 
                scores.add(minimax(depth + 1, PLAYER_X));
            }
            board[point.x][point.y] = NO_PLAYER; //Reset this point
        }
        if(turn==PLAYER_X){
   return returnMax(scores);
}
else{
   return returnMin(scores) ; } }

  public Point returnBestMove() {
        int MAX = -100000;
        int best = -1;

        for (int i = 0; i < rootsChildrenScores.size(); ++i) { 
            if (MAX < rootsChildrenScores.get(i).score) {
                MAX = rootsChildrenScores.get(i).score;
                best = i;
            }
        }

        return rootsChildrenScores.get(best).point;
    }

}