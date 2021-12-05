package de.startat.aoc2021.solutions;

import de.startat.aoc2021.solutions.fifthDay.Coordinate;
import de.startat.aoc2021.solutions.fifthDay.Line;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Log
@Component
public class FifthDay {

    @Autowired
    FileReadService fileReadService;

    Function<String,Line> lineBuilderFunction = s -> {
        String[] coordinates = s.split(" -> ");
        int x1 = Integer.parseInt(coordinates[0].split(",")[0]);
        int y1 = Integer.parseInt(coordinates[0].split(",")[1]);

        int x2 = Integer.parseInt(coordinates[1].split(",")[0]);
        int y2 = Integer.parseInt(coordinates[1].split(",")[1]);

        Line line = new Line(new Coordinate(x1, y1), new Coordinate(x2, y2));
        log.info("build line from \"" + s + "\" -> " + line );
        return line;

    };

    public void run() throws URISyntaxException, IOException {
        List<Line> lines = fileReadService.getFileObjects(/*"FifthDay_TrainingData.txt"*/ "FifthDay_Data1.txt", lineBuilderFunction);

        log.info(lines.size() + " lines");
        log.info( lines.stream().filter(Line::isHorizontalOrVertical).count() + " horizontal/vertical lines");

        Map<Coordinate, Long> coordinateCountingMap = lines.stream()
                //.filter(Line::isHorizontalOrVertical) //comment in for part1 solution
                .map(Line::getAllLineCoordinates)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        long countOfCrossings = coordinateCountingMap.entrySet().stream().filter(e-> e.getValue() > 1).peek(e -> log.info((e.toString()))).count();

        log.info("The given lines cross in " + countOfCrossings + " points");
    }

}
