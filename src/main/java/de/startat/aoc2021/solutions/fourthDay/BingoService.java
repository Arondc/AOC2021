package de.startat.aoc2021.solutions.fourthDay;

import lombok.extern.java.Log;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log
public class BingoService {

    public BingoGame buildFrom(List<String> lines){
        NumberCache numberCache = new NumberCache();

        List<BingoNumber> numbers = buildNumbers(lines.get(0), numberCache);
        List<BingoBoard> boards = new ArrayList<>();

        List<String> currentBoardLines = new ArrayList<>();
        for(int i = 2; i < lines.size(); i++){
            if(!lines.get(i).equals(Strings.EMPTY)){
                currentBoardLines.add(lines.get(i));
            }

            if(lines.get(i).equals(Strings.EMPTY) || i == lines.size() - 1){
                BingoBoard board = buildBoard(currentBoardLines, numberCache);
                boards.add(board);
                currentBoardLines.clear();
            }

        }
        return new BingoGame(numbers,boards);
    }

    private List<BingoNumber> buildNumbers(String s, NumberCache numberCache) {
        return Arrays.stream(s.split(",")).map(Integer::valueOf).map(numberCache::get).collect(Collectors.toList());
    }

    private BingoBoard buildBoard(List<String> currentBoardLines, NumberCache numberCache) {
        List<List<BingoNumber>> boardNumberLines = new ArrayList<>();

        for(String line : currentBoardLines){
            List<BingoNumber> singleLine = Arrays.stream(line.trim().split("\\s++"))
                    .sequential()
                    .map(Integer::valueOf)
                    .map(numberCache::get)
                    .collect(Collectors.toList());
            boardNumberLines.add(singleLine);
        }
        return new BingoBoard(boardNumberLines);
    }


    public BingoBoard checkForFinishedBoard(BingoGame game) {
        for (BingoBoard board : game.getActiveBoards()) {
            if(boardIsFinished(board)){
                return board;
            }
        }
        return null;
    }

    private boolean boardIsFinished(BingoBoard board) {
        return checkLines(board) || checkColumns(board);
    }

    private boolean checkColumns(BingoBoard board) {
        for(int column = 0; column < board.getNumbers().get(0).size(); column++){
            boolean columnFinished = true;
            for(int line = 0; line < board.getNumbers().size(); line++){
                columnFinished &= board.getNumbers().get(line).get(column).getMarked();
            }
            if(columnFinished){
                return true;
            }

        }
        return false;
    }

    private boolean checkLines(BingoBoard board) {
        return board.getNumbers()
                .stream()
                .anyMatch(l -> l.stream().allMatch(BingoNumber::getMarked));
    }

    public Integer sumUnmarkedNumbers(BingoBoard finishedBoard) {
        return finishedBoard.getNumbers()
                .stream()
                .flatMap(Collection::stream)
                .filter(n -> !n.getMarked())
                .mapToInt(BingoNumber::getNumber)
                .sum();
    }
}
