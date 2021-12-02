package de.startat.aoc2021;

import java.util.List;
import java.util.stream.IntStream;

public class FirstDay {
    public static void main(String[] args) throws  Exception {
        new FirstDay().firstStar();
        new FirstDay().secondStar();
    }

    public void firstStar() throws Exception {
        List<Integer> lines = AOCUtil.getFileIntegers("FirstDay_Data1.txt");
        long increasingDepthCount = IntStream.range(1, lines.size()).filter(i -> lines.get(i - 1) < lines.get(i)).count();
        System.out.println("Increasing depth values:" + increasingDepthCount);
    }

    public void secondStar() throws Exception {
        List<Integer> lines = AOCUtil.getFileIntegers("FirstDay_Data1.txt");
        long increasingDepthCount = IntStream.range(3, lines.size())
                .filter(i -> lines.get(i - 3)+lines.get(i-2)+lines.get(i-1) < lines.get(i-2)+lines.get(i-1)+lines.get(i)).count();
        System.out.println("Increasing depth values (sliding window 3):" + increasingDepthCount);
    }
}
