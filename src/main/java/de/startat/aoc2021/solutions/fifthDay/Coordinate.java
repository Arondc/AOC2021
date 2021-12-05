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
        if(this.x < c2.x || this.y < c2.y){
            return -1;
        }
        return 1;
    }


}
