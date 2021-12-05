package de.startat.aoc2021.solutions.fifthDay;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Coordinate implements Comparable<Coordinate> {
    @NonNull
    private Integer x;
    @NonNull
    private Integer y;

    @Override
    public int compareTo(Coordinate c2) {
        //diagonal falling
        if ((this.x < c2.x) && (this.y < c2.y)) {
            return -1;
        }
        //diagonal rising
        if ((this.x < c2.x) && (this.y > c2.y)) {
            return -1;
        }

        //vertical
        if((this.x.equals(c2.x)) && (this.y < c2.y)){
            return -1;
        }

        //horizontal
        if((this.y.equals(c2.y)) && (this.x < c2.x)){
            return -1;
        }

        return 1;
    }

    @Override
    public int hashCode() {
        return x*10000+y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        Coordinate c = (Coordinate) o;
        return x.equals(c.getX()) && y.equals(c.getY());
    }


}
