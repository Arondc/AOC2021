package de.startat.aoc2021.solutions.fourthDay;

import java.util.HashMap;
import java.util.Map;

public class NumberCache {
    Map<Integer, BingoNumber> cache = new HashMap<>();

    public BingoNumber get(Integer key) {
        if(!cache.containsKey(key)){
            cache.put(key, new BingoNumber(key));
        }

        return cache.get(key);
    }


}
