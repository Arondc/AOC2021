package de.startat.aoc2021.solutions;


import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Log
@Component
public class SeventhDay {
    @Autowired
    FileReadService fileReadService;

    BiFunction<Long, List<Integer>, Long> distanceSumFunctionPart1 = (posChecked, l) -> (Long) l.stream().map(boatPos -> Math.abs(boatPos - posChecked)).mapToLong(a -> a).sum();

    BiFunction<Long, List<Integer>, Long> distanceSumFunctionPart2 = (posChecked, l) -> (Long) l.stream()
            .map(boatPos -> {
                long n = Math.abs(boatPos - posChecked);
                return (n * (n + 1)) / 2;
            }).mapToLong(a -> a).sum();

    Function<Map<Long, List<Long>>, Optional<Map.Entry<Long, List<Long>>>> findSmallestDistance = m -> m.entrySet().stream().min(Comparator.comparingLong(Map.Entry::getKey)).stream().findFirst();


    public void run() throws Exception {
        List<Integer> subPositions = fileReadService.getCSVFileAsIntegers("SeventhDay_Data1.txt" /*"SeventhDay_TrainingData.txt"*/);
        Long min = subPositions.stream().mapToLong(v -> v).min().getAsLong();
        Long max = subPositions.stream().mapToLong(v -> v).max().getAsLong();

        log.info("searching for optimum between " + min + " and " + max);

        Map<Long, List<Long>> distancesWithPositionsPart1 = calculateDistanceMap(subPositions, max, min, distanceSumFunctionPart1);
        Optional<Map.Entry<Long, List<Long>>> minDistancePosition1 = findSmallestDistance.apply(distancesWithPositionsPart1);
        log.info("Part 1 solution: " + minDistancePosition1.toString());

        Map<Long, List<Long>> distancesWithPositionsPart2 = calculateDistanceMap(subPositions, max, min, distanceSumFunctionPart2);
        Optional<Map.Entry<Long, List<Long>>> minDistancePosition2 = findSmallestDistance.apply(distancesWithPositionsPart2);
        log.info("Part 2 solution: " + minDistancePosition2.toString());

    }

    private Map<Long, List<Long>> calculateDistanceMap(List<Integer> subPositions, Long max, Long min, BiFunction<Long, List<Integer>, Long> distanceSumFunctionPart2) {
        return LongStream.range(min, max + 1)
                .boxed()
                .parallel()
                .collect(
                        Collectors.groupingBy(v -> distanceSumFunctionPart2.apply(v, subPositions))
                );
    }


}
