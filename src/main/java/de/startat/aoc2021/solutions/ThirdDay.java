package de.startat.aoc2021.solutions;

import lombok.extern.java.Log;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
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
        List<List<Integer>> bitList = lines.stream().map(s -> Arrays.stream(s.split("")).map(Integer::valueOf).collect(toList())).collect(toList());

        log.info(()->"amount of bits per line:" + bitLength);

        String gammaRate = buildGammaRate(bitList);

        log.info(()->"gamma rate: " + gammaRate);

        String epsilonRate = invertBitString(gammaRate);

        log.info(()->"least common bits: " + epsilonRate);

        long gammaRateNumeric = bitStringToNumeric(gammaRate);
        long epsilonRateNumeric = bitStringToNumeric(epsilonRate);

        log.info(()->"gamma rate numeric: " + gammaRateNumeric);
        log.info(()->"epsilon rate numeric: " + epsilonRateNumeric);
        log.info(()->"gamma x epsilon: " + (gammaRateNumeric * epsilonRateNumeric));

        /*
            Solution to part 1
            --------------------------
            amount of bits per line:12
            gamma rate: 111011110011
            least common bits: 000100001100
            gamma rate numeric: 3827
            epsilon rate numeric: 268
            gamma x epsilon: 1025636
        */

        String oxygenGeneratorRating = findRatingForSecondStar(copyBitList(bitList), this::findMostCommonBit);
        log.info("oxygen generator rating: " + oxygenGeneratorRating);
        log.info("oxygen generator rating: " + bitStringToNumeric(oxygenGeneratorRating));
        String co2ScrubberRating = findRatingForSecondStar(copyBitList(bitList), (a, b) -> 1 - findMostCommonBit(a,b));
        log.info("CO2 scrubber rating: " + co2ScrubberRating);
        log.info("CO2 scrubber rating: " + bitStringToNumeric(co2ScrubberRating));
        log.info("life support rating: " + bitStringToNumeric(oxygenGeneratorRating) * bitStringToNumeric(co2ScrubberRating));

        /*
            Solution to part 2
            --------------------------
            oxygen generator rating: 110000010001
            oxygen generator rating: 3089
            CO2 scrubber rating: 000100000001
            CO2 scrubber rating: 257
            life support rating: 793873
         */
    }

    private String findRatingForSecondStar(List<List<Integer>> bitList, BiFunction<List<List<Integer>>,Integer, Integer> filterBitValueFunction ) {
        int index = 0;
        while(bitList.size() > 1){
            Integer filterBitValue = filterBitValueFunction.apply(bitList,index);
            final int finalIndex = index;
            bitList = bitList.stream().filter(v -> v.get(finalIndex).equals(filterBitValue)).collect(Collectors.toList());
            index++;

        }
        return bitList.stream().flatMap(v -> v.stream().map(String::valueOf)).collect(Collectors.joining());
    }

    private List<List<Integer>> copyBitList(List<List<Integer>> bitList) {
        return bitList.stream().map(ArrayList::new).collect(toList());
    }

    private String buildGammaRate(List<List<Integer>> bits/*, int bitLength*/){
        int bitStringLength = bits.get(0).size();
        StringBuilder result = new StringBuilder(Strings.repeat("0",bitStringLength));
        for(int i = 0; i< bitStringLength; i++){
            int mostCommonBitAtIndex = findMostCommonBit(bits,i);
            result.setCharAt(i, Character.forDigit(mostCommonBitAtIndex,10));
        }
        return result.toString();
    }

    private int findMostCommonBit(List<List<Integer>> bitList, int index){
        long zeroCountForCurrentBit = bitList.stream().map(v -> v.get(index)).filter(v -> v.equals(0)).count();
        return zeroCountForCurrentBit > bitList.size()/2 ? 0 : 1;
    }


    private String invertBitString(String bitString){
        return Arrays.stream(bitString.split("")).map(v -> v.equals("0")?"1":"0").collect(Collectors.joining());
    }

    private Long bitStringToNumeric(String bitString){
        return Long.parseLong(bitString, 2);
    }

}
