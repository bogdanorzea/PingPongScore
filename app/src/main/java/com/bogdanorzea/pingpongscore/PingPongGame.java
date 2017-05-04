package com.bogdanorzea.pingpongscore;

class PingPongGame {
    private int SET_LENGTH = 11;
    private int MATCH_LENGTH = 5;
    private boolean gameEnded = false;
    private PingPongPlayer player1;
    private PingPongPlayer player2;

    PingPongGame() {
        this.player1 = new PingPongPlayer();
        this.player2 = new PingPongPlayer();
    }

    PingPongGame(int player1Sets, int player2Sets) {
        this();
        player1.setSets(player1Sets);
        player2.setSets(player2Sets);
    }

    PingPongGame(int player1Sets, int player2Sets, int player1Points, int player2Points) {
        this(player1Sets, player2Sets);
        player1.setScore(player1Points);
        player2.setScore(player2Points);
    }

    boolean hasGameEnded() {
        return gameEnded;
    }

    void addPointPlayer1() {
        player1.addPoint();
        int p1Score = player1.getScore();
        int p2Score = player2.getScore();

        if (p1Score >= SET_LENGTH && (p1Score - p2Score > 1)) {
            player1.increaseSet();
            if (player1.getSets() == MATCH_LENGTH) {
                gameEnded = true;
                return;
            }
            resetRound();
        }
    }

    void addPointPlayer2() {
        player2.addPoint();
        int p1Score = player1.getScore();
        int p2Score = player2.getScore();

        if (p2Score >= SET_LENGTH && (p2Score - p1Score > 1)) {
            player2.increaseSet();
            if (player2.getSets() == MATCH_LENGTH) {
                gameEnded = true;
                return;
            }
            resetRound();
        }
    }

    int[] getSets() {
        return new int[]{player1.getSets(), player2.getSets()};
    }

    int[] getScore() {
        return new int[]{player1.getScore(), player2.getScore()};
    }

    void resetRound() {
        player1.resetRound();
        player2.resetRound();
    }

    void resetGame() {
        this.gameEnded = false;
        player1.resetGame();
        player2.resetGame();
    }

    String[] getPlayerNames() {
        return new String[]{player1.getName(), player2.getName()};
    }

    void setPlayer1Name(String name) {
        player1.setName(name);
    }

    void setPlayer2Name(String name) {
        player2.setName(name);
    }
}
