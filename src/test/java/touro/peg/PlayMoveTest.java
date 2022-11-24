package touro.peg;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayMoveTest {

    TriangleBoard board = new TriangleBoard(0);

    LegalMovesFactory factory = new LegalMovesFactory();
    PlayMove play = new PlayMove(board);

    @Test
    void move_isValid() {
        //given

        //when
        play.move(new Move(3, 1, 0), factory.legalMoves);

        //then
        assertTrue(board.getPegs()[0]);
        assertFalse(board.getPegs()[1]);
        assertFalse(board.getPegs()[3]);
    }

    @Test
    void move_isNotValid() {
        //given

        //when
        play.move(new Move(4, 1, 0), factory.legalMoves);

        //then
        assertFalse(board.getPegs()[0]);
        assertTrue(board.getPegs()[1]);
        assertTrue(board.getPegs()[4]);
    }

    @Test
    void isValidMove_possible() { //correct boolean values for given indices
        //given
        board.setPeg(0, true);
        board.setPeg(1, true);
        board.setPeg(3, false);

        Move validMove = new Move(0, 1, 3);
        Move invalidMove = new Move(3, 1, 0);

        //when
        boolean valid = play.isValidMove(validMove, factory.legalMoves);
        boolean invalid = play.isValidMove(invalidMove, factory.legalMoves);

        //then
        assertTrue(valid);
        assertFalse(invalid);
    }

    @Test
    void isValidMove_legal() { //correct combination of given indices
        //given
        Move validMove = new Move(3, 1, 0);
        Move invalidMove = new Move(0, 1, 4);
        Move invalidMove2 = new Move(0, 1, 3);

        //when
        boolean valid = play.isValidMove(validMove, factory.legalMoves);
        boolean invalid = play.isValidMove(invalidMove, factory.legalMoves);
        boolean invalid2 = play.isValidMove(invalidMove2, factory.legalMoves);

        //then
        assertTrue(valid);
        assertFalse(invalid);
        assertFalse(invalid2);
    }
}