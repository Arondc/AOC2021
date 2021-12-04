package de.startat.aoc2021.solutions.secondDay;

public class Position{
        int depth = 0;
        int verticalPosition = 0;

        @Override
        public String toString() {
            return "Position{" +
                    "depth=" + depth +
                    ", verticalPosition=" + verticalPosition +
                    '}';
        }

    public int getDepth() {
        return depth;
    }

    public int getVerticalPosition() {
        return verticalPosition;
    }

    public void moveVertically(Integer distance){
            verticalPosition += distance;
    }

    public void changeDepth(Integer distance){
            depth += distance;
    }

}