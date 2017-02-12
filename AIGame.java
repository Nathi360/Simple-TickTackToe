import java.util.Random;
import java.util.Scanner;

public class AIGame extends Game
{
    private final String playereID;

    private int BlockRow;
    private int BlockColl;
    private int CheckRow;
    private int CheckColl;

    private Boolean cond1 = false;
    private Boolean cond2 = false;

    public AIGame(String P)
    {
        playereID = P;
        BlockColl = BlockRow = 0;
        CheckRow = CheckColl = 0;

        board = new Character[size][size];

        for(int K = 0; K < size; K++)
        {
            for(int G = 0; G < size; G++)
            {
                board[K][G] = '-';
            }
        }
    }

    public void gameplay(int count)
    {
        if(turn.equals(playereID))
        {
            if(count == 0)
            {
                cond1 = true;
            }

            opponent = "AI";
            PGP();
        }
        else if(turn.equals("AI"))
        {
            System.out.print("AI: ");

            if(count == 3 && CheckTraps())
            {
                cond2 = true;
            }

            opponent = playereID;
            AIGP();
        }
    }

    protected boolean checkState()
    {
        if(!FIN)
        {
            for (String aKontrol : kontrol)
            {
                if (gameOver(aKontrol))
                {
                    if (turn.equals(playereID))
                    {
                        System.out.println("\t\t\tWinner: " + turn + "!");
                    }

                    FIN = true;
                    return false;
                }
            }
        }
        else
        {
            return false;
        }

        turn = opponent;
        return true;
    }

    private boolean gameOver(String pass)
    {
        Character T1 = fetch(0, 0);
        Character T2 = fetch(0, 1);
        Character T3 = fetch(1, 0);
        Character T4 = fetch(0, 2);
        Character T5 = fetch(2, 0);
        //Character T6 = fetch(2, 2);

        switch (pass)
        {
            case "R1":
            {
                if(fetch(0, 0).equals(fetch(0, 1)) && fetch(0, 1).equals(fetch(0, 2)) && !T1.equals('-'))
                {
                    return true;
                }
                break;
            }
            case "R2":
            {
                if(fetch(1, 0).equals(fetch(1, 1)) && fetch(1, 1).equals(fetch(1, 2)) && !T3.equals('-'))
                {
                    return true;
                }
                break;
            }
            case "R3":
            {
                if(fetch(2, 0).equals(fetch(2, 1)) && fetch(2, 1).equals(fetch(2, 2)) && !T5.equals('-'))
                {
                    return true;
                }
                break;
            }
            case "C1":
            {
                if(fetch(0, 0).equals(fetch(1, 0)) && fetch(1, 0).equals(fetch(2, 0)) && !T1.equals('-'))
                {
                    return true;
                }
                break;
            }
            case "C2":
            {
                if(fetch(0, 1).equals(fetch(1, 1)) && fetch(1, 1).equals(fetch(2, 1)) && !T2.equals('-'))
                {
                    return true;
                }
                break;
            }
            case "C3":
            {
                if(fetch(0, 2).equals(fetch(1, 2)) && fetch(1, 2).equals(fetch(2, 2)) && !T4.equals('-'))
                {
                    return true;
                }
                break;
            }
            case "D1":
            {
                if(fetch(0, 0).equals(fetch(1, 1)) && fetch(1, 1).equals(fetch(2, 2)) && !T1.equals('-'))
                {
                    return true;
                }
                break;
            }
            case "D2":
            {
                if(fetch(0, 2).equals(fetch(1, 1)) && fetch(1, 1).equals(fetch(2, 0)) && !T4.equals('-'))
                {
                    return true;
                }
                break;
            }
            default:
            {
                return false;
            }
        }

        return false;
    }

    public void Fairness()
    {
        Random kootz = new Random();
        int D = kootz.nextInt(2);

        switch(D)
        {
            case 0:
            {
                turn = playereID;
                opponent = "AI";
                break;
            }
            case 1:
            {
                turn = "AI";
                opponent = playereID;
                break;
            }
            default:
            {
                //No-Entry
            }
        }
    }

    public  void tutorial()
    {
        System.out.println("\t\t\tBoard: [Row][Column]\n");

        for(int K = 0; K < size; K++)
        {
            for(int G = 0; G < size; G++)
            {
                if(G == 0)
                {
                    System.out.print("\t\t\t[" + (K+1) + "][" + (G+1) + "]");
                }
                else
                {
                    System.out.print(" [" + (K+1) + "][" + (G+1) + "]");
                }

                if(G == 2)
                {
                    System.out.println();
                }
            }
        }
        System.out.println();
    }

    public  void printBoard()
    {
        System.out.println();

        for(int K = 0; K < size; K++)
        {
            for(int G = 0; G < size; G++)
            {
                if(G == 0)
                {
                    if(fetch(K, G).equals('O'))
                    {
                        System.out.print((char)27 + "[33m\t\t\t" + fetch(K, G) + (char)27 + "[0m");
                    }
                    else if(fetch(K, G).equals('X'))
                    {
                        System.out.print((char)27 + "[36m\t\t\t" + fetch(K, G) + (char)27 + "[0m");
                    }
                    else
                    {
                        System.out.print("\t\t\t" + fetch(K, G));
                    }
                }
                else
                {
                    if(fetch(K, G).equals('O'))
                    {
                        System.out.print((char)27 + "[33m " + fetch(K, G) + (char)27 + "[0m");
                    }
                    else if(fetch(K, G).equals('X'))
                    {
                        System.out.print((char)27 + "[36m " + fetch(K, G) + (char)27 + "[0m");
                    }
                    else
                    {
                        System.out.print(" " + fetch(K, G));
                    }
                }

                if(G == 2)
                {
                    System.out.println();
                }
            }
        }

        System.out.println();
    }

    private void PGP()
    {
        Scanner mov = new Scanner(System.in);
        int R, C;

        opponent = "AI";

        System.out.print(playereID + "'s move: ");
        R = mov.nextInt();
        C = mov.nextInt();

        if(R <= size && C <= size && R > 0 && C > 0)
        {
            if(fetch((R - 1), (C - 1)).equals('-'))
            {
                makeMove((R - 1), (C - 1), 'O');
            }
            else
            {
                System.out.println("\t\t\t\t\tInvalid-Move, try again..");

                try
                {
                    this.PGP();
                }
                catch(StackOverflowError feel)
                {
                    System.out.println(feel.getMessage());
                }
            }
        }
        else
        {
            System.out.println("\t\t\t\t\tInvalid-Move, try again..");

            try
            {
                this.PGP();
            }
            catch(StackOverflowError feel)
            {
                System.out.println(feel.getMessage());
            }
        }

        checkState();
    }

    private void AIGP()
    {
        if(checkMate())
        {
            System.out.println("You just lost to a machine!");
            makeMove(CheckRow, CheckColl, 'X');
        }
        else if(threat())
        {
            System.out.println("Threat detected!");
            makeMove(BlockRow, BlockColl, 'X');
        }
        else
        {
            if(cond1 && cond2)
            {
                avoidTrapOne();
                return;
            }

            if(fetch(1, 1).equals('-'))
            {
                makeMove(1, 1, 'X');
            }
            else if(fetch(1, 1).equals('O'))
            {
                Random flip = new Random();
                int diagBlock = flip.nextInt(4);

                if(fetch(0, 0).equals('-') && diagBlock == 0)
                {
                    makeMove(0, 0, 'X');
                }
                else if(fetch(0, 2).equals('-') && diagBlock == 1)
                {
                    makeMove(0, 2, 'X');
                }
                else if(fetch(2, 0).equals('-') && diagBlock == 2)
                {
                    makeMove(2, 0, 'X');
                }
                else if(fetch(2, 2).equals('-') && diagBlock == 3)
                {
                    makeMove(2, 2, 'X');
                }
                else
                {
                    try
                    {
                        this.AIGP();
                    }
                    catch(StackOverflowError over)
                    {
                        System.out.println(over.getMessage());
                    }
                }
            }
            else
            {
                Random heck = new Random();
                int R = heck.nextInt(3);
                int C = heck.nextInt(3);

                if((R < 3 && R >= 0) && (C < 3 && C >= 0))
                {
                    if(fetch(R, C).equals('-') || fetch(R, C).equals('-'))
                    {
                        if(fetch(R, C).equals('-'))
                        {
                            makeMove(R, C, 'X');
                        }
                        else
                        {
                            makeMove(C, R, 'X');
                        }
                    }
                    else
                    {
                        try
                        {
                            this.AIGP();
                        }
                        catch(StackOverflowError over)
                        {
                            System.out.println(over.getMessage());
                        }
                    }
                }
                else
                {
                    try
                    {
                        this.AIGP();
                    }
                    catch(StackOverflowError over)
                    {
                        System.out.println(over.getMessage());
                    }
                }
            }
        }
    }

    private Boolean threat()
    {
        int countOpp = 0;
        int countVac = 0;

        for(int R = 0; R < 3; R++)//Left-Right
        {
            for(int C = 0; C < 3; C++)
            {
                if(fetch(R, C).equals('O'))
                {
                    countOpp++;
                }
                else if(fetch(R, C).equals('-'))
                {
                    BlockRow = R;
                    BlockColl = C;
                    countVac++;
                }

                if(C == 2)
                {
                    if(countOpp == 2 && countVac == 1)
                    {
                        return true;
                    }
                }
            }

            countOpp = countVac = 0;
        }

        for(int C = 0; C < 3; C++)//Up-Down
        {
            for(int R = 0; R < 3; R++)
            {
                if(fetch(R, C).equals('O'))
                {
                    countOpp++;
                }
                else if(fetch(R, C).equals('-'))
                {
                    BlockRow = R;
                    BlockColl = C;
                    countVac++;
                }

                if(R == 2)
                {
                    if(countOpp == 2 && countVac == 1)
                    {
                        return true;
                    }
                }
            }
            countOpp = countVac = 0;
        }

        for(int D = 0; D < 3; D++)//Diag-1
        {
            if(fetch(D, D).equals('O'))
            {
                countOpp++;
            }
            else if(fetch(D, D).equals('-'))
            {
                BlockRow = BlockColl = D;
                countVac++;
            }

            if(D == 2)
            {
                if(countOpp == 2 && countVac == 1)
                {
                    return true;
                }
            }
        }

        countOpp = countVac = 0;

        for(int R = 0; R < 3; R++)//Diag-2
        {
            for(int C = 0; C < 3; C++)
            {
                if((R + C) == 2)
                {
                    if(fetch(R, C).equals('O'))
                    {
                        countOpp++;
                    }
                    else if(fetch(R, C).equals('-'))
                    {
                        BlockRow = R;
                        BlockColl = C;
                        countVac++;
                    }
                }

                if(R == 2)
                {
                    if(countOpp == 2 && countVac == 1)
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private Boolean checkMate()
    {
        int countMe = 0;
        int countVac = 0;

        for(int R = 0; R < 3; R++)//Left-Right
        {
            for(int C = 0; C < 3; C++)
            {
                if(fetch(R, C).equals('X'))
                {
                    countMe++;
                }
                else if(fetch(R, C).equals('-'))
                {
                    CheckRow = R;
                    CheckColl = C;
                    countVac++;
                }

                if(C == 2)
                {
                    if(countMe == 2 && countVac == 1)
                    {

                        return true;
                    }
                }
            }
            countMe = countVac = 0;
        }

        for(int C = 0; C < 3; C++)//Up-Down
        {
            for(int R = 0; R < 3; R++)
            {
                if(fetch(R, C).equals('X'))
                {
                    countMe++;
                }
                else if(fetch(R, C).equals('-'))
                {
                    CheckRow = R;
                    CheckColl = C;
                    countVac++;
                }

                if(R == 2)
                {
                    if(countMe == 2 && countVac == 1)
                    {
                        return true;
                    }
                }
            }
            countMe = countVac = 0;
        }

        for(int D = 0; D < 3; D++)//Diag-1
        {
            if(fetch(D, D).equals('X'))
            {
                countMe++;
            }
            else if(fetch(D, D).equals('-'))
            {
                CheckRow = CheckColl = D;
                countVac++;
            }

            if(D == 2)
            {
                if(countMe == 2 && countVac == 1)
                {
                    return true;
                }
            }
        }

        countMe = countVac = 0;

        for(int R = 0; R < 3; R++)//Diag-2
        {
            for(int C = 0; C < 3; C++)
            {
                if((R + C) == 2)
                {
                    if(fetch(R, C).equals('X'))
                    {
                        countMe++;
                    }
                    else if(fetch(R, C).equals('-'))
                    {
                        CheckRow = R;
                        CheckColl = C;
                        countVac++;
                    }
                }

                if(R == 2)
                {
                    if(countMe == 2 && countVac == 1)
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private Boolean CheckTraps()
    {
        return (fetch(2, 2).equals('O') && fetch(0, 0).equals('O')) || (fetch(0, 2).equals('O') && fetch(2, 0).equals('O'));
    }

    private void avoidTrapOne()
    {
        Random punt = new Random();
        int roll = punt.nextInt(3);

        if(roll == 0)
        {
            makeMove(0, 1, 'X');
        }
        else if(roll == 1)
        {
            makeMove(1, 2, 'X');
        }
        else if(roll == 2)
        {
            makeMove(1, 0, 'X');
        }
        else if(roll == 3)
        {
            makeMove(2, 1, 'X');
        }

        cond1 = cond2 = false;
    }

    protected Character fetch(int R, int C)
    {
        if(R < size && R >= 0 && C < size && C >= 0)
        {
            return board[R][C];
        }
        return null;
    }

    private void makeMove(int R, int C, Character dudd)
    {
        if(R < size && R >= 0 && C < size && C >= 0)
        {
            if(fetch(R, C).equals('-'))
            {
                board[R][C] = dudd;
            }
        }
    }
}
