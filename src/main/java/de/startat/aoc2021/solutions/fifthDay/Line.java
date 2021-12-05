package de.startat.aoc2021.solutions.fifthDay;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class Line {
    @NonNull
    Coordinate coordinate1;
    @NonNull
    Coordinate coordinate2;

    public Line(Coordinate coordinate1, Coordinate coordinate2){
        //leftmost uppermost point of the line is always coordinate1
        if(coordinate1.compareTo(coordinate2) < 0){
            this.coordinate1 = coordinate1;
            this.coordinate2 = coordinate2;
        } else {
            this.coordinate1 = coordinate2;
            this.coordinate2 = coordinate1;
        }
    }

    public List<Coordinate> getAllLineCoordinates() {
        if(coordinate1.getX().equals(coordinate2.getX())){
            int x = coordinate1.getX();
            return IntStream.range(coordinate1.getY(),coordinate2.getY()+1).mapToObj(y -> new Coordinate(x,y)).collect(Collectors.toList());
        } else {
            int y = coordinate1.getY();
            return IntStream.range(coordinate1.getX(),coordinate2.getX()+1).mapToObj(x -> new Coordinate(x,y)).collect(Collectors.toList());
        }
    }

    @ToString.Include
    public boolean isHorizontalOrVertical() {
        return coordinate1.getX().equals(coordinate2.getX()) || coordinate1.getY().equals(coordinate2.getY());
    }
}
