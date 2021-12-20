package de.startat.aoc2021.solutions.ninthDay;


import lombok.Data;

import java.util.ArrayList;

@Data
public class HeightMap {
    ArrayList<HeightCoordinate> coordinates = new ArrayList<>();

    public void addCoordinate(int line, int column, int height) {
        coordinates.add(new HeightCoordinate(line, column, height));
    }

    public void connectNeighbors(){
        for(final HeightCoordinate coordinate : coordinates){
            ArrayList<HeightCoordinate> neighbors = new ArrayList<>();
            coordinates.stream().filter(c -> (c.getColumn().equals(coordinate.getColumn())) && (c.getLine().equals(coordinate.getLine() - 1))).findFirst().ifPresent(neighbors::add);
            coordinates.stream().filter(c -> c.getColumn().equals(coordinate.getColumn()) && c.getLine().equals(coordinate.getLine() + 1)).findFirst().ifPresent(neighbors::add);
            coordinates.stream().filter(c -> c.getColumn().equals(coordinate.getColumn() - 1) && c.getLine().equals(coordinate.getLine())).findFirst().ifPresent(neighbors::add);
            coordinates.stream().filter(c -> c.getColumn().equals(coordinate.getColumn() + 1) && c.getLine().equals(coordinate.getLine())).findFirst().ifPresent(neighbors::add);
            coordinate.setNeighbors(neighbors);
        }
    }
}