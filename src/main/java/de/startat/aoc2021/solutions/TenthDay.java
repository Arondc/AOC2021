package de.startat.aoc2021.solutions;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
@Log
public class TenthDay {
    public static final Predicate<LineScoringInformation> KEEP_INCOMPLETE_LINES = s -> s.getCorruptnessScore() == 0 && !s.getRemainingStack().isEmpty();
    @Autowired
    FileReadService fileReadService;

    public void run() throws Exception {
        List<String> lines = fileReadService.getFileLines(
                //"TenthDay_TrainingData.txt"
                "TenthDay_Data1.txt"
                );

        log.info(lines.toString());
        checkLines(lines);

    }

    private void checkLines(List<String> lines){
        List<LineScoringInformation> scoringInformation = lines.stream().map(this::checkSingleLine).collect(Collectors.toList());

        for(LineScoringInformation s : scoringInformation){
            log.info(s.toString());
        }

        log.info("" + scoringInformation.stream().mapToLong(LineScoringInformation::getCorruptnessScore).sum());

        List<Long> incList = scoringInformation.stream().filter(KEEP_INCOMPLETE_LINES).mapToLong(LineScoringInformation::getIncompletenessScore).boxed().sorted().collect(Collectors.toList());
        log.info("" + incList.get(incList.size()/2));
    }

    private long calculateIncompletenessScore(LineScoringInformation lineScoringInformation) {
        long score = 0;
        Deque<String> remainingStack = new ArrayDeque<>(lineScoringInformation.getRemainingStack());
        while(!remainingStack.isEmpty()){
            score *= 5;
            long singleParenthesisScore = determineSingleParenthesisScore(remainingStack.pop());
            score += singleParenthesisScore;
        }
        return score;
    }

    private long determineSingleParenthesisScore(String parenthesis) {
        switch (parenthesis){
            case "(": return 1;
            case "[": return 2;
            case "{": return 3;
            case "<": return 4;
            default: return 0;
        }
    }

    private LineScoringInformation checkSingleLine(String line){
        Deque<String> stack = new ArrayDeque<>();

        String corruptParenthesis = null;
        for(String c : line.split("")){
            if(isOpeningParenthesis(c)){
                stack.push(c);
            } else {
                if(isCorruptParenthesis(stack, c)){
                    corruptParenthesis = c;
                    break;
                }
            }
        }
        return new LineScoringInformation(corruptnessScore(corruptParenthesis), stack, line);
    }

    private long corruptnessScore(String corruptParenthesis) {
        if(corruptParenthesis == null){
            return 0;
        }
        switch (corruptParenthesis){
            case ")": return 3;
            case "]": return 57;
            case "}": return 1197;
            case ">": return 25137;
            default: return 0;
        }
    }

    private boolean isCorruptParenthesis(Deque<String> stack, String c) {
        switch(c){
            case ")":
                if(!stack.pop().equals("("))
                    return true;
                break;
            case "]":
                if(!stack.pop().equals("["))
                    return true;
                break;
            case "}":
                if(!stack.pop().equals("{"))
                    return true;
                break;
            case ">":
                if(!stack.pop().equals("<"))
                    return true;
                break;
        }
        return false;
    }

    private boolean isOpeningParenthesis(String c) {
        return c.equals("(") || c.equals("[") || c.equals("{") || c.equals("<");
    }

    @Getter
    @ToString
    private class LineScoringInformation{
        long corruptnessScore;
        Deque<String> remainingStack;
        String line;
        long incompletenessScore;

        public LineScoringInformation(long corruptnessScore,Deque<String> remainingStack, String line){
            this.corruptnessScore = corruptnessScore;
            this.remainingStack = remainingStack;
            this.line = line;

            if(this.corruptnessScore == 0 && !this.remainingStack.isEmpty()){
                incompletenessScore = calculateIncompletenessScore(this);
            }
        }
    }
}
