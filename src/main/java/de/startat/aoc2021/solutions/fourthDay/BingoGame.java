package de.startat.aoc2021.solutions.fourthDay;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class BingoGame {

    List<BingoNumber> numbers;
    List<BingoBoard> boards;

    public BingoGame(List<BingoNumber> numbers, List<BingoBoard> boards) {
        this.numbers = numbers;
        this.boards = boards;
    }

    public List<BingoBoard> getActiveBoards() {
        return boards.stream().filter(b -> b.getFinishedByNumber() == null).collect(Collectors.toList());
    }
}
