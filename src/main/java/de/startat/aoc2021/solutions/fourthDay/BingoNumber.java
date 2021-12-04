package de.startat.aoc2021.solutions.fourthDay;

import lombok.Data;

@Data
public class BingoNumber {
    private Boolean marked;
    private Integer number;

    public BingoNumber(int number){
        this.marked = false;
        this.number = number;
    }

}
