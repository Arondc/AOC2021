package de.startat.aoc2021.solutions;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class FileReadService {

    public List<String> getFileLines(String filename) throws URISyntaxException, IOException {
        URL url = getClass().getClassLoader().getResource(filename);
        File f = new File(url.toURI());
        return Files.readAllLines(f.toPath());
    }

    public List<String> getCSVFileAsStrings(String filename) throws URISyntaxException, IOException{
        URL url = getClass().getClassLoader().getResource(filename);
        File f = new File(url.toURI());
        List<String> lines = Files.readAllLines(f.toPath());
        assert(lines.size() == 1);
        return Arrays.asList(lines.get(0).split(","));
    }

    public List<Integer> getCSVFileAsIntegers(String filename) throws URISyntaxException, IOException {
        return getCSVFileAsStrings(filename).stream().map(Integer::valueOf).collect(Collectors.toList());
    }



    public List<Integer> getFileIntegers(String filename) throws URISyntaxException, IOException {
        return getFileLines(filename).stream().map(Integer::parseInt).collect(Collectors.toList());
    }

    public <T> List<T> getFileObjects(String filename, Function<String,T> builderFunction) throws URISyntaxException, IOException{
        return getFileLines(filename).stream().map(builderFunction).collect(Collectors.toList());
    }
}
