package de.startat.aoc2021.solutions;

import de.startat.aoc2021.solutions.ninthDay.HeightCoordinate;
import de.startat.aoc2021.solutions.ninthDay.HeightMap;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Log
public class NinthDay {
    @Autowired
    FileReadService fileReadService;

    public void run() throws Exception {
        List<String> lines = fileReadService.getFileLines(
                //"NinthDay_TrainingData.txt"
                "NinthDay_Data1.txt"
                );

        HeightMap map = linesToHeightMap(lines);
        log.info(map.toString());

        List<HeightCoordinate> lowPoints = findLowPointValues(map);
        log.info(lowPoints.toString());

        Integer riskLevel = calculateRiskLevel(lowPoints);
        log.info("Risk Level = " + riskLevel);

        Map<HeightCoordinate, Set<HeightCoordinate>> basins = findBasins(map, lowPoints);

        for (Map.Entry<HeightCoordinate, Set<HeightCoordinate>> entry : basins.entrySet()){
            log.info(entry.getKey() + " BASIN SIZE " + entry.getValue().size());
            log.info(entry.getValue().toString());
        }

        List<Integer> sortedBasinSizes = basins.values().stream().map(Set::size).sorted((i1,i2) -> Integer.compare(i2,i1)).collect(Collectors.toList());
        log.info(sortedBasinSizes.toString());
        log.info("Three lagest basin size product = " + sortedBasinSizes.get(0)*sortedBasinSizes.get(1)*sortedBasinSizes.get(2));

    }

    private Map<HeightCoordinate, Set<HeightCoordinate>> findBasins(HeightMap map, List<HeightCoordinate> lowPoints) {
        Map<HeightCoordinate, Set<HeightCoordinate>> basins = new HashMap<>();

        for (HeightCoordinate lowPoint : lowPoints) {
            Set<HeightCoordinate> basinCoordinates = new HashSet<>();
            basinCoordinates.add(lowPoint);
            int oldBasinSize;
            do{
                oldBasinSize = basinCoordinates.size();
                Set<HeightCoordinate> newSet = basinCoordinates.stream()
                        .flatMap(c -> c.getNeighbors().stream().filter(n -> n.getHeight() > c.getHeight() && n.getHeight() < 9))
                        .collect(Collectors.toSet());
                basinCoordinates.addAll(newSet);
            }while(basinCoordinates.size() != oldBasinSize);

            basins.put(lowPoint,basinCoordinates);
        }
        return basins;
    }

    private Integer calculateRiskLevel(List<HeightCoordinate> lowPointValues) {
        return lowPointValues.size() + lowPointValues.stream().mapToInt(HeightCoordinate::getHeight).sum();
    }

    private List<HeightCoordinate> findLowPointValues(HeightMap map) {
        return map.getCoordinates().stream().filter(c -> c.getNeighbors().stream().allMatch(n -> n.getHeight() > c.getHeight())).collect(Collectors.toList());

    }

    private HeightMap linesToHeightMap(List<String> lines) {
        HeightMap map = new HeightMap();
        for(int lineNr = 0 ; lineNr < lines.size(); lineNr++){
            String line = lines.get(lineNr);
            String[] elements = line.split("");
            for(int columnNr = 0 ; columnNr < elements.length; columnNr++){
                int height = Integer.parseInt(elements[columnNr]);
                map.addCoordinate(lineNr,columnNr, height);
            }
        }
        map.connectNeighbors();
        return map;
    }


}
