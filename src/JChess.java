import com.chess.engine.board.Board;
import com.google.common.collect.ObjectArrays;

public class JChess {
    public static void main(String[] args) {
        Board board = Board.createStandardBoard();
        System.out.println(board);
    }
}