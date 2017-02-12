import java.util.Scanner;

class TickTackToe
{
    public static void main(String[] args)
    {
        int count = 0, maxMoves = 9;
        Boolean chosen;

        Scanner vend = new Scanner(System.in);
        System.out.println("\n=============================");
        System.out.println("\tTick-Tack-Toe");
        System.out.println("=============================\n");

        do
        {

            System.out.println("Game-Modes:");
            System.out.println("\tA. Player vs Player");
            System.out.print("\tB. Player vs AI\t\t\t\tSelect: ");
            String mode = vend.next().toUpperCase();


            switch(mode)
            {
                case "A":
                {
                    System.out.print("\nPlayer-1: ");
                    String P1 = vend.next();

                    System.out.print("Player-2: ");
                    String P2 = vend.next();

                    System.out.println();

                    Game tack = new PvPGame(P1, P2);
                    tack.tutorial();
                    tack.Fairness();

                    while(tack.checkState() && count < maxMoves)
                    {
                        tack.gameplay(count);
                        tack.printBoard();
                        count++;
                    }

                    chosen = false;
                    break;
                }
                case "B":
                {
                    System.out.print("\nPlayer-Id: ");
                    String P = vend.next();

                    Game nack = new AIGame(P);
                    nack.tutorial();
                    nack.Fairness();

                    while(count < maxMoves && nack.checkState())
                    {
                        nack.gameplay(count);
                        nack.printBoard();
                        count++;
                    }

                    chosen = false;
                    break;
                }
                default:
                {
                    System.out.println("\n\t\t\t\t\t\t\t\tInvalid mode selected: Try again..\n");
                    chosen = true;
                }
            }

        }while (chosen);
    }
}