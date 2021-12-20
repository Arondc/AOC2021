package de.startat.aoc2021.solutions;

import de.startat.aoc2021.solutions.eigthDay.DisplayRepresentation;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Log
@Component
public class EigthDay {
    @Autowired
    FileReadService fileReadService;

    public void run() throws Exception {
        List<DisplayRepresentation> displayRepresentations = fileReadService.getFileObjects("EigthDay_Data1.txt" /* "EigthDay_TrainingData.txt"*/, DisplayRepresentation::new);

        long part1Solution = displayRepresentations.stream()
                .flatMap(d -> Arrays.stream(d.getOutputValue().split(" ")))
                .map(String::length)
                .filter(l -> l.equals(2) || l.equals(3) || l.equals(4) || l.equals(7))
                .count();

        log.info("" +part1Solution);
    }


}
