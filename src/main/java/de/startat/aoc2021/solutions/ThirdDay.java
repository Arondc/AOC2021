package de.startat.aoc2021.solutions;

import lombok.extern.java.Log;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Log
@Component
public class ThirdDay {

    @Autowired
    FileReadService fileReadService;

    public void run() throws Exception{
        List<String> lines = fileReadService.getFileLines("ThirdDay_Data1.txt");
        int bitLength = lines.get(0).length();
        List<List<Integer>> bits = lines.stream().map(s -> Arrays.stream(s.split("")).map(Integer::valueOf).collect(toList())).collect(toList());

        log.info(()->"Anzahl der Bits im String:" + bitLength);
        log.info(()->"Erste Zeile als BitFeld:" + bits.get(0));

        String mostCommonBits = buildMostCommonBitString(bits, bitLength);

        log.info(()->"most common bits: " + mostCommonBits);

        String leastCommonBits = invertBitString(mostCommonBits);

        log.info(()->"least common bits: " + leastCommonBits);

        long mostCommonNumeric = bitStringToNumeric(mostCommonBits);
        long leastCommonNumeric = bitStringToNumeric(leastCommonBits);

        log.info(()->"most common numeric: " + mostCommonNumeric);
        log.info(()->"least common numeric: " + leastCommonNumeric);
        log.info(()->"numerics multiplied: " + (mostCommonNumeric * leastCommonNumeric));
    }

    private String buildMostCommonBitString(List<List<Integer>> bits, int bitLength){
        StringBuilder result = new StringBuilder(Strings.repeat("0",bitLength));
        for(int i = 0; i<bitLength;i++){
            final int index = i;
            long zeroCountForCurrentBit = bits.stream().map(v -> v.get(index)).filter(v -> v.equals(0)).count();
            if(zeroCountForCurrentBit < bits.size()/2){
                result.setCharAt(index,'1');
            }
        }
        return result.toString();
    }

    private String invertBitString(String bitString){
        return Arrays.stream(bitString.split("")).map(v -> v.equals("0")?"1":"0").collect(Collectors.joining());
    }

    private Long bitStringToNumeric(String bitString){
        return Long.parseLong(bitString, 2);
    }

}
