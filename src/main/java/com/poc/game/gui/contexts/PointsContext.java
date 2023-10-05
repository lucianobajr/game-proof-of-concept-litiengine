package com.poc.game.gui.contexts;
public class PointsContext {
    private int points;

    public PointsContext(){
        this.points = 0;
    }

    public int getPoints() {
        return this.points;
    }

    public void addPoints(int pointsToAdd) {
        this.points += pointsToAdd;
    }
}