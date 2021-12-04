package de.startat.aoc2021.solutions;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Log
@Component
public class FirstDay {

    @Autowired
    FileReadService fileReadService;

    public void run() throws Exception{
        new FirstDay().firstStar();
        new FirstDay().secondStar();
    }

    public void firstStar() throws Exception {
        List<Integer> lines = fileReadService.getFileIntegers("FirstDay_Data1.txt");
        long increasingDepthCount = IntStream.range(1, lines.size()).filter(i -> lines.get(i - 1) < lines.get(i)).count();
        log.info("Increasing depth values:" + increasingDepthCount);
    }

    public void secondStar() throws Exception {
        List<Integer> lines = fileReadService.getFileIntegers("FirstDay_Data1.txt");
        long increasingDepthCount = IntStream.range(3, lines.size())
                .filter(i -> lines.get(i - 3)+lines.get(i-2)+lines.get(i-1) < lines.get(i-2)+lines.get(i-1)+lines.get(i)).count();
        log.info("Increasing depth values (sliding window 3):" + increasingDepthCount);
    }
}
