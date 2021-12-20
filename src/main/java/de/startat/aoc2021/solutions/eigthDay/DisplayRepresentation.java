package de.startat.aoc2021.solutions.eigthDay;

import lombok.Data;

@Data
public class DisplayRepresentation {

    public DisplayRepresentation(String line){
        String[] represantations = line.split(" \\| ");
        uniqueSignalPatterns = represantations[0];
        outputValue = represantations[1];
    }

    private String uniqueSignalPatterns;
    private String outputValue;


}
