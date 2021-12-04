package de.startat.aoc2021.solutions.fourthDay;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
public class BingoBoard {

    @NonNull
    List<List<BingoNumber>> numbers;

    BingoNumber finishedByNumber;

}
