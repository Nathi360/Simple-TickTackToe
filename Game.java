abstract class Game
{
    Character[][] board;
    String turn;
    String opponent;
    Boolean FIN = false;

    final String[] kontrol = {"R1", "R2", "R3", "C1", "C2", "C3", "D1", "D2",};
    final int size = 3;
    
    protected abstract void gameplay(int X);
    protected abstract boolean checkState();
    protected abstract void Fairness();
    protected abstract void tutorial();
    protected abstract void printBoard();

    protected Character fetch(int R, int C)
    {
        if(R < size && R >= 0 && C < size && C >= 0)
        {
            return board[R][C];
        }
        return null;
    }
}
