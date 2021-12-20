package de.startat.aoc2021.solutions.ninthDay;

import lombok.*;

import java.util.ArrayList;
import java.util.Random;

@Data
public class HeightCoordinate {
    //Workaround for bad Hashing in Lombok ^^
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private long justARandomValue;

    Integer line;
    Integer column;
    Integer height;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    ArrayList<HeightCoordinate> neighbors;

    public HeightCoordinate(int line, int column, int height) {
        this.line = line;
        this.column = column;
        this.height = height;
        justARandomValue = new Random(line * (column* 10007L) * (height* 19997L)).nextLong();
    }

}
