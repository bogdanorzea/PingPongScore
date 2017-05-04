package com.bogdanorzea.pingpongscore;

class PingPongPlayer {
    private int score = 0;
    private int sets = 0;
    private String name = null;

    int getSets() {
        return sets;
    }

    void setSets(int sets) {
        this.sets = sets;
    }

    int getScore() {
        return score;
    }

    void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    void addPoint() {
        score++;
    }

    void resetRound() {
        score = 0;
    }

    void resetGame() {
        score = 0;
        sets = 0;
        name = null;
    }

    void increaseSet() {
        sets++;
    }
}
