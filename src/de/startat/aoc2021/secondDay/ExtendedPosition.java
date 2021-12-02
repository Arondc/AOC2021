package de.startat.aoc2021.secondDay;

public class ExtendedPosition extends Position{
        int aim = 0;

        @Override
        public String toString() {
            return "Position{" +
                    "depth=" + depth +
                    ", verticalPosition=" + verticalPosition +
                    '}';
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
}