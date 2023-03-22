

import java.util.Random;
import java.util.Scanner;
import java.io.IOException;



public class Main
{

    public static void main(String[] args) throws IOException, InterruptedException
    {
        menu();
    }


    /**********************************************************************************************************************
     **************************************************~~~ Functions ~~~**************************************************++
     **********************************************************************************************************************+


     /************************************************* MEnu ************************************************************/
    public static void menu() throws IOException, InterruptedException
    {
        cls();
        Scanner input = new Scanner(System.in);

        System.out.println("\n\n\n\n\n\n\n");
        System.out.println("\t\t\t\t\t+ * * * * * * * * * * * * * * * * * * * +");
        System.out.println("\t\t\t\t\t*                 Welcome               *");
        System.out.println("\t\t\t\t\t*              [Tic Tac Toe]            *");
        System.out.println("\t\t\t\t\t*                                       *");
        System.out.println("\t\t\t\t\t*  please choose tour game mode         *");
        System.out.println("\t\t\t\t\t*                                       *");
        System.out.println("\t\t\t\t\t*  1)Single player                      *");
        System.out.println("\t\t\t\t\t*  2)Multi  player                      *");
        System.out.println("\t\t\t\t\t*  3)Info                               *");
        System.out.println("\t\t\t\t\t*                                       *");
        System.out.println("\t\t\t\t\t*                                       *");
        System.out.println("\t\t\t\t\t+ * * * * * * * * * * * * * * * * * * * +");

        int game_Mod;
        game_Mod = input.nextInt();

        //Select game mod
        while(true)
        {
            switch (game_Mod)
            {
                case 1:
                    single_Player();
                    break;

                case 2:
                    multi_Player();
                    break;

                    case 3:
                    info();
                    break;

                default:
                    System.out.println("invalid input , press any key to return :");
                    String pauseTrick = input.next();
                    menu();



            }

        }
    }






     /**************************************************  Single player  **************************************************/
    public static void single_Player() throws IOException, InterruptedException
    {
        cls();
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        String[] cells = new String[17];
        String inputTemp;
        int turnCounter = 2 , input=0;


        //Set cells names
        for (int i = 1; i <= 16; i++)
        {
            cells[i] = Integer.toString(i);
        }


        //Set locked cells
        int locked[] = {0,0,0};
        int ran_Num;

        for (int i = 0; i < 3; i++)
        {
            ran_Num = random.nextInt(1, 16);
            while( true )
            {
                //avoid same locked cell
                if (ran_Num != locked[0] && ran_Num != locked[1] && ran_Num != locked[2])
                {
                    locked[i] = ran_Num;
                    cells[ran_Num]="L";
                    break;
                }

                else
                    ran_Num = random.nextInt(1, 16);

            }

        }



        //repeat 13 rounds
        for (int i = 1; i <= 7; i++)
        {
            cls();

            //print game table
            System.out.println("\n\n\n\n\n\n\n\t\t\t\t\t             PLayer turn\n");
            print_Game_Table(cells);


            //get cells index
            inputTemp=scanner.next();
            if (inputTemp.equals("r"))
                single_Player();

            else
                input=Integer.valueOf(inputTemp);


            //check if the player choice has been used or not
            while( true )
            {

                if ( input >= 1 && input <= 16 && cells[input] != "X" && cells[input] != "O" && cells[input] != "L" )
                {
                    break;
                }

                else
                {
                    System.out.println("\t\t\t               Invalid input , choose another cell :");
                    inputTemp=scanner.next();
                    if (inputTemp.equals("r"))
                        single_Player();
                    else
                        input=Integer.valueOf(inputTemp);

                }
            }


            //print 'X' in the cell
                cells[input ] = "X";


            //method check if a player has Won
            if (winChecker(cells)==true)
                winPrint(turnCounter,true );

            turnCounter++;


            //set a random number as bots choice
            int botChoice;

            botChoice=random.nextInt(1,16);
            System.out.println("bot choice is :" + botChoice);


            //check if bot choice has been used or not
            //'if' is used to avoid the bot for playing in the last round
            if (i!=7)
            {
                while (true)
                {

                    if (cells[botChoice] != "X" && cells[botChoice] != "O" && cells[botChoice] != "L")
                    {
                        cells[botChoice] = "O";
                        turnCounter++;
                        break;
                    }

                    else
                    {
                        botChoice = random.nextInt(1, 16);
                    }

                }
            }

            //method check if a player has Won
            if (winChecker(cells)==true)
                winPrint(turnCounter,true );



        }

        //if no one win call draw method
        drawTable();

    }


    /*************************************************  Multi player  *****************************************************/


    public static void multi_Player() throws IOException, InterruptedException
    {
        cls();
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        String[] cells = new String[17];
        String inputTemp;
        int turnCounter = 2 , input=0;


        //Set cells names
        for (int i = 1; i <= 16; i++)
        {
            cells[i] = Integer.toString(i);
        }


        //Set locked cells
        int locked[] = {0,0,0};
        int ran_Num;

        for (int i = 0; i < 3; i++)
        {
            ran_Num = random.nextInt(1, 16);
            while( true )
            {
                //avoid same locked cell
                if (ran_Num != locked[0] && ran_Num != locked[1] && ran_Num != locked[2])
                {
                    locked[i] = ran_Num;
                    cells[ran_Num]="L";
                    break;
                }

                else
                    ran_Num = random.nextInt(1, 16);

            }
        }



        //repeat 13 rounds
        for (int i = 0; i < 13; i++)
        {
            cls();


            //show which player turn is
            showTurn(turnCounter);


            //print game table
            print_Game_Table(cells);


            //check if the cell has been used or not
            while( true )
            {

                //get cells index and check if its 'r'
                inputTemp=scanner.next();
                if (inputTemp.equals("r"))
                    multi_Player();
                else
                    input=Integer.valueOf(inputTemp);

                if ( input >= 1 && input <= 16 && cells[input] != "X" && cells[input] != "O" && cells[input] != "L" )
                {
                    break;
                }

                else
                {
                    System.out.println("\t\t\t               Invalid input , choose another cell :");
                }
            }


            //print 'O' in the cell
            if (turnCounter % 2 == 0)
            {
                cells[input ] = "X";
            }


            //print 'X' in the cell
            else
            {
                cells[input ] = "O";
            }

            //method check if a player has Won
            if (winChecker(cells)==true)
                winPrint(turnCounter,false );

            turnCounter++;
        }

        //if no one win call this method
        drawTable();

    }

    /************************************************* CLear Screen *******************************************************/


    public static void cls() throws IOException, InterruptedException
    {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    /************************************************* Show Turn  *********************************************************/


    public static void  showTurn(int turn)
    {
        if (turn % 2 == 0)
        {
            System.out.println("\n\n\n\n\n\n\n\t\t\t\t\t            PLayer ONE turn\n");
        }


        else
        {
            System.out.println("\n\n\n\n\n\n\n\t\t\t\t\t            PLayer TWO turn;\n");

        }

    }

    /************************************************* Game Table  ********************************************************/


    public static void print_Game_Table(String cells[])
    {

        System.out.printf("\t\t\t\t\t+ * * * * * * * * * * * * * * * * * +\n");
        System.out.printf("\t\t\t\t\t*           [Tic Tac Toe]           *\n");
        System.out.printf("\t\t\t\t\t*                                   *\n");
        System.out.printf("\t\t\t\t\t*                                   *\n");
        System.out.printf("\t\t\t\t\t*\t %2s | %2s | %2s | %2s  \t    *\n", cells[1], cells[2], cells[3], cells[4]);
        System.out.printf("\t\t\t\t\t*       --------------------\t    *\n");
        System.out.printf("\t\t\t\t\t*\t %2s | %2s | %2s | %2s  \t    *\n", cells[5], cells[6], cells[7], cells[8]);
        System.out.printf("\t\t\t\t\t*       --------------------\t    *\n");
        System.out.printf("\t\t\t\t\t*\t %2s | %2s | %2s | %2s  \t    *\n", cells[9], cells[10], cells[11], cells[12]);
        System.out.printf("\t\t\t\t\t*       --------------------\t    *\n");
        System.out.printf("\t\t\t\t\t*\t %2s | %2s | %2s | %2s  \t    *\n", cells[13], cells[14], cells[15], cells[16]);
        System.out.printf("\t\t\t\t\t*                                   *\n");
        System.out.printf("\t\t\t\t\t*                                   *\n");
        System.out.printf("\t\t\t\t\t*                                   *\n");
        System.out.printf("\t\t\t\t\t+ * * * * * * * * * * * * * * * * * +\n\n\n");
        System.out.printf("\t\t\t\t       Hint: cells assigned with 'l' are locked.\n\n");
        System.out.printf("\t\t\t\t       Choose a cell :\n");

    }
    /************************************************* winChecker ************************************************************/

    public static boolean winChecker(String cells[] ) throws IOException, InterruptedException
    {
        boolean flag=false;


        if(( cells[1].equals(cells[2] ))  && ( cells[2].equals(cells[3] )) )
            return true;
        else if (( cells[2].equals(cells[3] ))  && ( cells[3].equals(cells[4] )) )
            return true;
        else if (( cells[5].equals(cells[6] ))  && ( cells[6].equals(cells[7] )) )
            return true;
        else if (( cells[6].equals(cells[7] ))  && ( cells[7].equals(cells[8] )) )
            return true;
        else if (( cells[9].equals(cells[10] ))  && ( cells[10].equals(cells[11] )) )
            return true;
        else if (( cells[10].equals(cells[11] ))  && ( cells[11].equals(cells[12] )) )
            return true;
        else if (( cells[13].equals(cells[14] ))  && ( cells[14].equals(cells[15] )) )
            return true;
        else if (( cells[14].equals(cells[15] ))  && ( cells[15].equals(cells[16] )) )
            return true;
        else if (( cells[1].equals(cells[5] ))  && ( cells[5].equals(cells[9] )) )
            return true;
        else if (( cells[5].equals(cells[9] ))  && ( cells[9].equals(cells[13] )) )
            return true;
        else if (( cells[2].equals(cells[6] ))  && ( cells[6].equals(cells[10] )) )
            return true;
        else if (( cells[6].equals(cells[10] ))  && ( cells[10].equals(cells[14] )) )
            return true;
        else if (( cells[3].equals(cells[7] ))  && ( cells[7].equals(cells[11] )) )
            return true;
        else if (( cells[7].equals(cells[11] ))  && ( cells[11].equals(cells[15] )) )
            return true;
        else if (( cells[4].equals(cells[8] ))  && ( cells[8].equals(cells[12] )) )
            return true;
        else if (( cells[8].equals(cells[12] ))  && ( cells[12].equals(cells[16] )) )
            return true;
        else if (( cells[1].equals(cells[6] ))  && ( cells[6].equals(cells[11] )) )
            return true;
        else if (( cells[2].equals(cells[7] ))  && ( cells[7].equals(cells[12] )) )
            return true;
        else if (( cells[5].equals(cells[10] ))  && ( cells[10].equals(cells[15] )) )
            return true;
        else if (( cells[6].equals(cells[1] ))  && ( cells[11].equals(cells[16] )) )
            return true;
        else if (( cells[3].equals(cells[6] ))  && ( cells[6].equals(cells[9] )) )
            return true;
        else if (( cells[4].equals(cells[7] ))  && ( cells[7].equals(cells[10] )) )
            return true;
        else if (( cells[7].equals(cells[10] ))  && ( cells[10].equals(cells[13] )) )
            return true;
        else if (( cells[8].equals(cells[11] ))  && ( cells[11].equals(cells[14] )) )
            return true;
        return false;

    }

    /************************************************* winPrint ************************************************************/
    public static void winPrint(int turnCounter , boolean mode ) throws IOException, InterruptedException
    {
        cls();
        Scanner scanner = new Scanner(System.in);

        if ( turnCounter%2==0 )
        {
                System.out.println("\n\n\n\n\n\n\n\t\t\t\t\tPlayer One WON   ");
        }

        else
        {
            if (mode == false)
                System.out.println("\n\n\n\n\n\n\n\t\t\t\t\tPlayer Two WON !");

            else
                System.out.println("\n\n\n\n\n\n\n\t\t\t\t\t Computer WON! :)");
        }


        System.out.println("\n\n\n\n\n\t\t\t\t\tpress any key to turn back\n\t\t\t\t\tor press R to reset the game...");
        String temp=scanner.next();

        if (temp.equals("r") || temp.equals("R"))
        {
            if (mode == false)
                multi_Player();

            else if (mode == true)
                single_Player();
        }

        else
            menu();


    }

/************************************************* Info ************************************************************/

public static void info() throws IOException, InterruptedException
{
    cls();
    System.out.println("\n\n\n\n\n\n\n");
    System.out.println("\t\t\t\t\t+ * * * * * * * * * * * * * * * * * * * * * +");
    System.out.println("\t\t\t\t\t*                 Welcome                   *");
    System.out.println("\t\t\t\t\t*              [Tic Tac Toe]                *");
    System.out.println("\t\t\t\t\t*                                           *");
    System.out.println("\t\t\t\t\t*        Created by Shayan Chaleshi         *");
    System.out.println("\t\t\t\t\t*        Date 2023.03.06                    *");
    System.out.println("\t\t\t\t\t*        Tel  +989136527723                 *");
    System.out.println("\t\t\t\t\t*        Email raziahamini@Gmail.com        *");
    System.out.println("\t\t\t\t\t*                                           *");
    System.out.println("\t\t\t\t\t*        Be in touch :)                     *");
    System.out.println("\t\t\t\t\t*                                           *");
    System.out.println("\t\t\t\t\t*        press any key to return...         *");
    System.out.println("\t\t\t\t\t+ * * * * * * * * * * * * * * * * * * * * * +");

    Scanner scanner = new Scanner(System.in);
    scanner.next();
    menu();

}
/******************************************************* Draw_Table *********************************************************************/
public static void drawTable() throws IOException, InterruptedException
{
    cls();
    System.out.println("\n\n\n\n\n\n\n");
    System.out.printf("\t\t\t\t\t+ * * * * * * * * * * * * * * * * * +\n");
    System.out.printf("\t\t\t\t\t*                                   *\n");
    System.out.printf("\t\t\t\t\t*                                   *\n");
    System.out.printf("\t\t\t\t\t*                                   *\n");
    System.out.printf("\t\t\t\t\t*                                   *\n");
    System.out.printf("\t\t\t\t\t*                                   *\n");
    System.out.printf("\t\t\t\t\t*                                   *\n");
    System.out.printf("\t\t\t\t\t*             Draw !                *\n");
    System.out.printf("\t\t\t\t\t*                                   *\n");
    System.out.printf("\t\t\t\t\t*                                   *\n");
    System.out.printf("\t\t\t\t\t*                                   *\n");
    System.out.printf("\t\t\t\t\t*                                   *\n");
    System.out.printf("\t\t\t\t\t*  Press any key to return...       *\n");
    System.out.printf("\t\t\t\t\t*                                   *\n");
    System.out.printf("\t\t\t\t\t+ * * * * * * * * * * * * * * * * * +\n");
    Scanner scanner=new Scanner(System.in);
    scanner.next();
    menu();
}

}