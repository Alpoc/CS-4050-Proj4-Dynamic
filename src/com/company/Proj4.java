package com.company;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.*;

import static com.sun.org.apache.xalan.internal.lib.ExsltStrings.split;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.newObjectType;
import static sun.swing.MenuItemLayoutHelper.max;

public class Proj4
{

    static String gameBoard[][];
    static int baseBoard[][];
    //static Set<Integer> numSet = new HashSet<Integer>();
    static List<Integer> numSet = new ArrayList<Integer>();
    static int[] testArray= new int[] {3, 1, 7, 5, 8, 4};
    static int[] numbers;
    static String[] numberAsString;
    static Boolean player1 = false;

    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
	    System.out.print("Enter an even number for how many numbers you would like the game to consist of: ");
	    int gameLength = keyboard.nextInt();

        System.out.print("Enter in the numbers for the game separated by a space: ");
        String throwaway = keyboard.nextLine();
        String gameNumbers = keyboard.nextLine();

        numbers = new int[gameLength];

        // Help from another student on tokenizing input
        Scanner tokenizer = new Scanner(gameNumbers);
        int x = 0;
        while (tokenizer.hasNextInt())
        {
                numbers[x++] = tokenizer.nextInt();
        }

        gameBoard = new String[gameLength][gameLength];
        Random randomNum = new Random();

        int value = recurse(0, gameLength - 1);
        //System.out.println(value);
        //PrintBoard();
        //int sumvalue = SumRecurse(0, gameLength -1);
        //ystem.out.println("sum val: " + sumvalue);

        Moves();
    }

    public static void PrintBoard()
    {
        System.out.println();
        for (int k = 0; k < gameBoard.length; k++){
            System.out.print("\t" + numbers[k] + "\t");
        }
        System.out.print("\n\t-----------------------------------------------------");
        for (int i = 0; i < gameBoard.length; i++) {
            System.out.println();

            for (int j = 0; j < gameBoard[i].length; j++)
            {
                System.out.print("\t" + gameBoard[i][j] + "|");
            }
        }
    }

    public static void Moves()
    {
        Scanner keyboard = new Scanner(System.in);
        int row = 0;
        int column = gameBoard.length - 1;
        while (row != column)
        {
            // Users turn
            PrintBoard();
            System.out.println("\nYou are in cell (" + row + ", " + column + ") your optimal move is " +
                    getOptimalMove(row, column) + ", what is your move; F or L? ");
            String move = keyboard.next();

            if (move == "F")
            {
                row++;
            }
            else { column--; }

            // Computers turn
            //if (getOptimalMove(row, column) != "B")
                if (row != column){
                String nextMove = getOptimalMove(row, column);
                System.out.println(getOptimalMove(row, column));
                if (nextMove == "F")
                {
                    row++;
                } else { column--; }
            }
        }
        String hold = gameBoard[row][column];
        String[] values = hold.split(" ");
        int score = Integer.parseInt(values[0]);
        String player;
        if (score > 0)
        {
            player = "one";
        }
        else { player = "two"; }

        System.out.println("Game Over, player " + player + " won by " + score + " more than the other player" );
    }

    public static String getOptimalMove(int row, int column)
    {
        String hold = gameBoard[row][column];
        String[] values = hold.split(" ");
        String nextMove = values[1];
        return nextMove;
    }

    // Help from Brian on recurse
    public static int recurse(int x, int y)
    {
        if (gameBoard[x][y] != null)
        {
            String hold = gameBoard[x][y];
            String[] values = hold.split(" ");
            int sum = Integer.parseInt(values[0]);
            return sum;
        }

        else
        {
            if (x == y) {
                gameBoard[x][y] = "" + numbers[x] + " B";
                return numbers[x];
            }

            int left = numbers[x] - recurse(x + 1, y);
            int right = numbers[y] - recurse(x, y - 1);
            if (left > right) {
                gameBoard[x][y] = "" + left + " F";
                return left;
            }
            gameBoard[x][y] = "" + right + " L";
            return right;
        }
    }

    public static int SumRecurse(int x, int y)
    {
        player1 = !player1;

        if (gameBoard[x][y] != null)
        {
            String hold = gameBoard[x][y];
            String[] values = hold.split(" ");
            int sum = Integer.parseInt(values[0]);
            return sum;
        }
        else
        {
                if (x == y) {
                    gameBoard[x][y] = "" + numbers[x] + " B";
                    return numbers[x];
                }


            int left = numbers[x] + SumRecurse(x + 1, y);
            int right = numbers[y] + SumRecurse(x, y - 1);
            if (left > right) {
                gameBoard[x][y] = "" + left + " F";
                return left;
            }
            gameBoard[x][y] = "" + right + " L";
            return right;
//            }

        }
    }
}
