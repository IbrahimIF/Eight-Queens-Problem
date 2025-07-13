import java.util.Random;

import javax.management.ValueExp;
import javax.swing.text.html.HTMLDocument.RunElement;

public class App {
    public static class Chessboard {
        char[][] board = {
            {'.', 'Q', '.', '.', '.', '.', '.', '.'}, //7
            {'.', '.', '.', '.', '.', 'Q', '.', '.'}, //6
            {'Q', '.', '.', '.', '.', '.', '.', '.'}, //5
            {'.', '.', '.', '.', '.', '.', 'Q', '.'}, //4
            {'.', '.', '.', 'Q', '.', '.', '.', '.'}, //3
            {'.', '.', '.', '.', '.', '.', '.', 'Q'}, //2
            {'.', '.', 'Q', '.', '.', '.', '.', '.'}, //1
            {'.', '.', '.', '.', 'Q', '.', '.', '.'}  //0
            //0  //1   //2  //3  //4  //5  //6  //7z
        };

        // Method 1
        public static boolean validInput(char x) {
            x = '.';

            if (x == '.' || x == 'Q') {
                return true;
            } else {
                return false;
            }
        }

        //Backup

        public static boolean validSquare(char piece){

            switch(piece){
                case 'Q':
                    return true;
            case '.':
                return true;
            default:
                return false;
            }
        }


        // Method 2
        public static boolean validBoard(char[][] board) {

            // checking if board is null
            if (board==null) {
                return false;
            }
    
            // checking board size
            int r = board.length;
            int c = board[0].length;
            if (r != 8 || c != 8) {
                return false;
            }

            // checking for total queens
            int tq = 0;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (!validInput(board[i][j])) {

                    }
                    if (board[i][j] == 'Q') {
                        tq++;
                    }
                }
            }
            return tq == 8;
        }

        //Backup 2

        public static boolean validBoard2 (char[][] board){
            
            int queenCount = 0;

            if(board == null){
                return false;
            }

            for(int i = 0; i < board.length; i++){
                for(int j = 0; j < board[i].length; j++){

                    if(board.length != 8 || board[i].length !=8){
                        return false;
                    }

                    if(validSquare(board[i][j]) == false) {
                        return false;
                    }
                    
                    if(board[i][j] == 'Q') {
                        queenCount = queenCount + 1;
                    }
                }
            }

            if(queenCount !=8) {
                return false;
            }

            return true;
        }
            

        // Method 3
        public static String convertBoardToBinary(char[][] board) {

            if (board == null){
                return null;
            }

            /*
            String answer = "";
            if(!validBoard(board)){
                return answer;
            }
            */

            String binaryString = "";
            // looking for 011000100110000111000101
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board[i][j] == 'Q') {
                        
                        if(board[i][j]=='Q'){
                            binaryString = String.format("%3s", Integer.toBinaryString(j)).replaceAll(" ","0") + binaryString; 
                        }
                    }
                }
        }
        return binaryString;
    }


    //Backup 3
    public static String toBinary (char[][] board) {

        String boardBinary = "";

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){

                if(board[i][j] == 'Q') {
                    int num = j;

                    String place = Integer.toBinaryString(num);

                    if (num == 2 || num == 3) {
                        place = "0" + place;
                    }

                    if(num < 2){
                        place = "00" + place;
                    }

                    boardBinary = boardBinary + place;

                    }
                }
            }

            return boardBinary;
        }

    }


    // Method 4 // 1
    public static String iStart(String istart){


        for(int i = 0; i < 8; i++){
            int inum = (int)(Math.random() * (8));
            System.out.println(inum);

            String iBinary = Integer.toBinaryString(inum);

            if(inum == 2 || inum == 3) {
                iBinary = "0" + iBinary;
            }

            if (inum < 2) {
                iBinary = "00" + iBinary;
            }

            istart = istart + iBinary;
        }
        return istart;
    }

    // Method 4 // 2
    public static String iStart2(){
        String istart = "";
        for(int i = 0; i < 24; i++){
            if(Math.random() < 0.5) {
                istart += "0";
            } else {
                istart += "1";
            }
        } 
        return istart;
    }

    // Method 5 // 1   
    public static Integer calculateFitness(String solutionBinary) {

        Integer[] queensArray = new Integer[8];
        Integer attacks = 0;

        for (int i = 0; i < solutionBinary.length(); i += 3) {
            int endIndex = Math.min(i + 3, solutionBinary.length());
            String currentQueen = solutionBinary.substring(i, endIndex);
            queensArray[(i/3)] = Integer.parseInt(currentQueen, 2);
        }

        for (int Q1 = 0; Q1 < queensArray.length; Q1++) {
            for (int Q2 = 0; Q2 < queensArray.length; Q2++) {
                if (Q1 != Q2) {
                    int differenceRow = Math.abs(Q1 - Q2);
                    int differenceCol = Math.abs(queensArray[Q1] - queensArray[Q2]);
                    if (differenceRow == 0 || differenceCol == 0) {
                        attacks++;
                    } else if (differenceRow == differenceCol) {
                        attacks++;
                    }
                }

            }
        }
        return 56 - attacks;
    }

    //Backup 5

    public static int features5 (String binary){
        int attack = 0;
        int[][] placement = new int[8][2];
        for(int i = 0; i<8; i++ ){
            String sub = binary.substring((i * 3),(i + 1)*3);
            int location = Integer.parseInt(sub, 2);
            placement[i][0] = 7-i;
            placement[i][1] = location;
        }
        for(int j = 0; j < 8; j++){
            int[] queen = placement[j];
        }

        for(int a = 0; a < 8; a++ ){
            for(int b = 0; b < 8; b++ ){
                if(a!=b){
                    if(placement[a][1] == placement[b][1]){
                        attack += 1;
                    }
                    if((Math.abs(placement[a][0] - placement[b][0])) == (Math.abs(placement[a][1] - placement[b][1]))){
                        attack += 1;
                    }
                }
            }
        }
        int fitness = 56 - attack;
        return fitness;
    }



        // Method 6 // 1
        public static String SmallChangeOperator(String board) {
            if(board==null) {
            return null;
            }
                
            Random random = new Random();
            int index = random.nextInt(board.length());
            String flip;
                
            if(board.charAt(index) == '1') {
            flip = "0";
            } else {
            flip = "1";
            }
            return board.substring(0, index) + flip + board.substring(index + 1);
            }

    







    public static void main(String[] args) {
        Chessboard chessboard = new Chessboard();
        char[][] board = chessboard.board;

        boolean isValidInput = Chessboard.validInput('Q');
        System.out.println("Board Square: " + isValidInput);

        boolean isValidBoard = Chessboard.validBoard(board);
        System.out.println("Is board valid? " + isValidBoard);

        String binaryString = Chessboard.convertBoardToBinary(board);
        System.out.println("Binary representation: " + binaryString);

        String iStringTest = Chessboard.iStart2();
        System.out.println("Initial String: " + iStringTest);

        int fitness = Chessboard.calculateFitness("011000100110000111000101");
        System.out.println("FitnessFunction: " + fitness);

        String sco = Chessboard.SmallChangeOperator("100010111011110000101001");
        System.out.println("Small Change: " + sco);

    }
}