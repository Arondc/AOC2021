package de.startat.aoc2021.solutions;

import de.startat.aoc2021.solutions.fourthDay.BingoBoard;
import de.startat.aoc2021.solutions.fourthDay.BingoGame;
import de.startat.aoc2021.solutions.fourthDay.BingoNumber;
import de.startat.aoc2021.solutions.fourthDay.BingoService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log
@Component
public class FourthDay {

    @Autowired
    FileReadService fileReadService;

    @Autowired
    BingoService bingoService;

    public void run() throws Exception {
        List<String> lines = fileReadService.getFileLines("FourthDay_Data1.txt");
        BingoGame bingoGame = bingoService.buildFrom(lines);
        log.info("New game with " + bingoGame.getNumbers().size() + " numbers and " + bingoGame.getBoards().size() + " boards.");

        runGame(bingoGame);
    }

    private void runGame(BingoGame game){
        for(BingoNumber number : game.getNumbers()){
            long round = game.getNumbers().stream().filter(BingoNumber::getMarked).count() + 1;
            log.info("marking number # "+ round +":" + number.getNumber());
            number.setMarked(true);

            List<BingoBoard> finishedBoards = bingoService.checkForFinishedBoard(game);
            for(BingoBoard finishedBoard : finishedBoards){
                finishedBoard.setFinishedByNumber(number);
                log.info("finished a board with drawing number: " + number.getNumber() + " having a score of " + bingoService.sumUnmarkedNumbers(finishedBoard) * number.getNumber());
            }
        }
    }

}
