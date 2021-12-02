package de.startat.aoc2021.secondDay;

public class Position{
        int depth = 0;
        int verticalPosition = 0;

        int aim = 0;

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

    public int getAim() {
        return aim;
    }

    public void increaseAim(Integer value){
            aim+=value;
    }

    public void decreaseAim(Integer value){
        increaseAim(-value);
    }


    public void moveVertically(Integer distance){
            verticalPosition += distance;
    }

    public void changeDepth(Integer distance){
            depth += distance;
    }

}