package com.bogdanorzea.pingpongscore;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PingPongUnitTest {

    @Test
    public void newGame_hasBlankScore() throws Exception {
        PingPongGame test = new PingPongGame();
        assertArrayEquals(new int[]{0, 0}, test.getScore());
    }

    @Test
    public void newGame_hasBlankSet() throws Exception {
        PingPongGame test = new PingPongGame();
        assertArrayEquals(new int[]{0, 0}, test.getSets());
    }

    @Test
    public void newGame_addPointPlayer1() throws Exception {
        PingPongGame test = new PingPongGame();
        test.addPointPlayer1();
        assertArrayEquals(new int[]{1, 0}, test.getScore());
    }

    @Test
    public void newGame_addPointPlayer2() throws Exception {
        PingPongGame test = new PingPongGame();
        test.addPointPlayer2();
        assertArrayEquals(new int[]{0, 1}, test.getScore());
    }

    @Test
    public void newGame_addPointBothPlayers() throws Exception {
        PingPongGame test = new PingPongGame();
        test.addPointPlayer1();
        test.addPointPlayer2();
        assertArrayEquals(new int[]{1, 1}, test.getScore());
    }

    @Test
    public void newGame_setSets() throws Exception {
        PingPongGame test = new PingPongGame(1, 1);
        assertArrayEquals(new int[]{1, 1}, test.getSets());
    }

    @Test
    public void newGame_setPoints() throws Exception {
        PingPongGame test = new PingPongGame(0, 0, 5, 3);
        assertArrayEquals(new int[]{5, 3}, test.getScore());
    }

    @Test
    public void newGame_resetRound() throws Exception {
        PingPongGame test = new PingPongGame(0, 0, 5, 3);
        test.resetRound();
        assertArrayEquals(new int[]{0, 0}, test.getScore());
    }

    @Test
    public void newGame_resetGame() throws Exception {
        PingPongGame test = new PingPongGame(2, 4, 5, 3);
        test.resetGame();
        assertArrayEquals(new int[]{0, 0}, test.getScore());
        assertArrayEquals(new int[]{0, 0}, test.getSets());
    }

    @Test
    public void newGame_addPointToIncreaseSetPlayer1() throws Exception {
        PingPongGame test = new PingPongGame(0, 0, 10, 3);
        test.addPointPlayer1();
        assertArrayEquals(new int[]{0, 0}, test.getScore());
        assertArrayEquals(new int[]{1, 0}, test.getSets());
    }

    @Test
    public void newGame_addPointToIncreaseSetPlayer2() throws Exception {
        PingPongGame test = new PingPongGame(0, 0, 9, 10);
        test.addPointPlayer2();
        assertArrayEquals(new int[]{0, 0}, test.getScore());
        assertArrayEquals(new int[]{0, 1}, test.getSets());
    }

    @Test
    public void newGame_addPointNotToIncreaseSetPlayer1() throws Exception {
        PingPongGame test = new PingPongGame(0, 0, 10, 10);
        test.addPointPlayer1();
        assertArrayEquals(new int[]{11, 10}, test.getScore());
        assertArrayEquals(new int[]{0, 0}, test.getSets());
    }

    @Test
    public void newGame_addPointNotToIncreaseSetPlayer1_Case2() throws Exception {
        PingPongGame test = new PingPongGame(0, 0, 13, 12);
        test.addPointPlayer1();
        assertArrayEquals(new int[]{0, 0}, test.getScore());
        assertArrayEquals(new int[]{1, 0}, test.getSets());
    }

    @Test
    public void newGame_addPointNotToIncreaseSetPlayer2_Case2() throws Exception {
        PingPongGame test = new PingPongGame(0, 0, 13, 12);
        test.addPointPlayer2();
        assertArrayEquals(new int[]{13, 13}, test.getScore());
        assertArrayEquals(new int[]{0, 0}, test.getSets());
    }

    @Test
    public void newGame_addSetToFinishGame() throws Exception {
        PingPongGame test = new PingPongGame(4, 0, 10, 0);
        test.addPointPlayer1();
        assertTrue(test.hasGameEnded());
    }

    @Test
    public void newGame_addSetNotToFinishGame() throws Exception {
        PingPongGame test = new PingPongGame(3, 0, 10, 0);
        test.addPointPlayer1();
        assertFalse(test.hasGameEnded());
    }

    @Test
    public void newGame_addPlayer1Name() throws Exception {
        PingPongGame test = new PingPongGame();
        test.setPlayer1Name("Test");
        assertTrue("Test".equals(test.getName()[0]));
    }

    @Test
    public void newGame_addPlayer2Name() throws Exception {
        PingPongGame test = new PingPongGame();
        test.setPlayer2Name("Test");
        assertTrue("Test".equals(test.getName()[1]));
    }
}