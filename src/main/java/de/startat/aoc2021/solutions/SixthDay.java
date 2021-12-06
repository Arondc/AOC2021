package de.startat.aoc2021.solutions;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Log
public class SixthDay {

    @Autowired
    FileReadService fileReadService;

    public void run() throws Exception {
        List<Integer> fishes = fileReadService.getCSVFileAsIntegers("SixthDay_Data1.txt" /*"SixthDay_TrainingData.txt"*/);
        log.info(fishes.toString());

        Map<Integer, Long> fishBuckets = fishes.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        log.info("Initial State: " + buildBucketPrintString(fishBuckets));


        for(int i = 0; i < 256; i++){
            log.info("Day " + (i+1));
            simulateDay(fishBuckets);
        }
        log.info("Now there are " + sumFishes(fishBuckets) + " fishes.");
    }

    private void simulateDay(Map<Integer, Long> fishBuckets)
    {
        long newFishesCount = fishBuckets.getOrDefault(0,0L);
        for(int i = 0; i<8; i++){
            fishBuckets.put(i, fishBuckets.getOrDefault(i + 1, 0L));
        }

        fishBuckets.put(6, fishBuckets.getOrDefault(6,0L) + newFishesCount); //Reset parents to 6
        fishBuckets.put(8, newFishesCount); //Add newborns

        log.info(buildBucketPrintString(fishBuckets));
    }

    private String buildBucketPrintString(Map<Integer, Long> fishBuckets){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer,Long> bucket: fishBuckets.entrySet()) {
            sb.append(bucket.getKey()).append(":").append(bucket.getValue()).append(" ");
        }
        return sb.toString();
    }

    private long sumFishes(Map<Integer, Long> fishBuckets){
        return fishBuckets.values().stream().mapToLong(v -> v).sum();
    }
}
