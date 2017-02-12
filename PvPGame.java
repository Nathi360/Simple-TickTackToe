import java.util.Random;
import java.util.Scanner;

public class PvPGame extends Game
{
    private final String[] players;

    public PvPGame(String p1, String p2)
    {
        players = new String[2];
        players[0] = p1;
        players[1] = p2;
        turn = p1;
        opponent = p2;

        board = new Character[size][size];

        for(int K = 0; K < size; K++)
        {
            for(int G = 0; G < size; G++)
            {
                board[K][G] = '-';
            }
        }
    }

    public void printBoard()
    {
        System.out.println();

        for(int K = 0; K < size; K++)
        {
            for(int G = 0; G < size; G++)
            {
                if(G == 0)
                {
                    if(board[K][G].equals('O'))
                    {
                        System.out.print((char)27 + "[33m\t\t\t" + board[K][G] + (char)27 + "[0m");
                    }
                    else if(board[K][G].equals('X'))
                    {
                        System.out.print((char)27 + "[36m\t\t\t" + board[K][G] + (char)27 + "[0m");
                    }
                    else
                    {
                        System.out.print("\t\t\t" + board[K][G]);
                    }
                }
                else
                {
                    if(board[K][G].equals('O'))
                    {
                        System.out.print((char)27 + "[33m " + board[K][G] + (char)27 + "[0m");
                    }
                    else if(board[K][G].equals('X'))
                    {
                        System.out.print((char)27 + "[36m " + board[K][G] + (char)27 + "[0m");
                    }
                    else
                    {
                        System.out.print(" " + board[K][G]);
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

    public void tutorial()
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

    public void Fairness()
    {
        Random fairness = new Random();
        turn = players[fairness.nextInt(2)];
    }

    protected void gameplay(int puck)
    {
        Scanner mov = new Scanner(System.in);
        int R, C;

        if(turn.equals(players[0]))
        {
            opponent = players[1];

            System.out.print(players[0] + "'s move: ");
            R = mov.nextInt();
            C = mov.nextInt();

            if(R <= size && C <= size && R > 0 && C > 0)
            {
                if(board[R - 1][C - 1].equals('-'))
                {
                    board[R - 1][C - 1] = 'O';
                }
                else
                {
                    System.out.println("\t\t\t\t\tInvalid-Move, try agin..");
                    gameplay(puck);
                }
            }
            else
            {
                System.out.println("\t\t\t\t\tInvalid-Move, try agin..");
                gameplay(puck);
            }
        }
        else if(turn.equals(players[1]))
        {
            opponent = players[0];

            System.out.print(players[1] + "'s move: ");
            R = mov.nextInt();
            C = mov.nextInt();

            if(R <= size && C <= size && R > 0 && C > 0)
            {
                if(board[R - 1][C - 1].equals('-'))
                {
                    board[R - 1][C - 1] = 'X';
                }
                else
                {
                    System.out.println("\t\t\t\t\tInvalid-Move, try agin..");
                    gameplay(puck);
                }
            }
            else
            {
                System.out.println("\t\t\t\t\tInvalid-Move, try agin..");
                gameplay(puck);
            }
        }

        checkState();
    }

    protected boolean checkState()
    {
        if(!FIN)
        {
            for (String aKontrol : kontrol)
            {
                if (gameOver(aKontrol))
                {
                    System.out.println("\t\t\tWinner: " + turn + "!");
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

    public Character fetch(int R, int C)
    {
        if(R < size && R >= 0 && C < size && C >= 0)
        {
            return board[R][C];
        }

        return null;
    }

    private boolean gameOver(String pass)
    {
        Character T1 = board[0][0];
        Character T2 = board[0][1];
        Character T3 = board[1][0];
        Character T4 = board[0][2];
        Character T5 = board[2][0];

        switch (pass)
        {
            case "R1":
            {
                if (board[0][0].equals(board[0][1]) && board[0][1].equals(board[0][2]) && !T1.equals('-'))
                {
                    return true;
                }
                break;
            }
            case "R2":
            {
                if (board[1][0].equals(board[1][1]) && board[1][1].equals(board[1][2]) && !T3.equals('-'))
                {
                    return true;
                }
                break;
            }
            case "R3":
            {
                if (board[2][0].equals(board[2][1]) && board[2][1].equals(board[2][2]) && !T5.equals('-'))
                {
                    return true;
                }
                break;
            }
            case "C1":
            {
                if (board[0][0].equals(board[1][0]) && board[1][0].equals(board[2][0]) && !T1.equals('-'))
                {
                    return true;
                }
                break;
            }
            case "C2":
            {
                if (board[0][1].equals(board[1][1]) && board[1][1].equals(board[2][1]) && !T2.equals('-'))
                {
                    return true;
                }
                break;
            }
            case "C3":
            {
                if (board[0][2].equals(board[1][2]) && board[1][2].equals(board[2][2]) && !T4.equals('-'))
                {
                    return true;
                }
                break;
            }
            case "D1":
            {
                if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !T1.equals('-'))
                {
                    return true;
                }
                break;
            }
            case "D2":
            {
                if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !T4.equals('-'))
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
}
